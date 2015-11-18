package control.IO.audioIn;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.math3.analysis.function.Pow;
import org.apache.commons.math3.analysis.function.Sqrt;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;

public class AudioAnalyser implements Runnable {

	private boolean running;
	
	private static SoundCaptureThread mic;
	private FastFourierTransformer fft;
	
	private static Complex[] fftOutput;
	private static double[] fftOutputReal;
	
	private static ArrayList<Double> micOutput;
	private int numSamples = 2048;
	
	Sqrt sqrt;
	Pow pow;
	
	int length = SoundCaptureThread.length;
	
	public AudioAnalyser(SoundCaptureThread mic) {
		this.mic = mic;
		fft = new FastFourierTransformer(DftNormalization.UNITARY);
		sqrt = new Sqrt();
		pow = new Pow();
		
		fftOutput = new Complex[length];
		
		for (int i = 0; i < fftOutput.length; i++) {
			fftOutput[i] = new Complex(0.0, 0.0);
		}
		micOutput = new ArrayList<Double>();
	}
	
	@Override
	public void run() {
		
		long lastTime = System.nanoTime();
		double amountOfTicks = 30.0;//per second
		double timePerTick = 1.0e9/amountOfTicks;
		double delta = 0;
		//long timer = System.currentTimeMillis();
		//int ticks = 0;
		
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		while(running){
			long now = System.nanoTime();
			delta += ((now-lastTime)/timePerTick);
			lastTime = now;
			
			if (delta >= 2) { 
				System.err.println("FFT ticks skipped");
				delta = 1.1;
			}
			while(delta>=1){
				double[] tempMicOutput = mic.getOutAsDouble();
				
				
				//store mic data
				for (int i = Math.min(tempMicOutput.length-1, 1000); i >= 0; i--) {
						micOutput.add(0, tempMicOutput[i]);
				}
				
				while (micOutput.size() > numSamples) {
					micOutput.remove(micOutput.size()-1);
				}
				
				
				//calculate fft data
				tempMicOutput = Arrays.copyOf(mic.getOutAsDouble(), length);
				fftOutput = fft.transform(tempMicOutput, TransformType.FORWARD);
				mic.resetOut();
				
				fftOutputReal = new double[fftOutput.length/4];
				for (int i = 1; i < 1 + fftOutput.length/4; i++) {
					fftOutputReal[i-1] = sqrt.value(pow.value(fftOutput[i].getReal(), 2) + pow.value(fftOutput[i].getImaginary(), 2));
				}
				
//				for (int i = 1; i < 1 + fftOutput.length/4; i++) {
//					fftOutputReal[i-1] -= fftOutputReal[fftOutput.length/4 -1];
//					if (fftOutputReal[i-1] < 0) {
//						fftOutputReal[i-1] = 0;
//					}
//				}

				//ticks++;
				delta--;
			}
			
//			if(System.currentTimeMillis()-timer>1000){
//				timer+=1000;
//				//Game.setTPS(ticks);
//				ticks = 0;
//			}
		}
		
	}
	
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	public static Complex[] getTransformOutput() {
		return fftOutput;
	}
	
	public static double[] getTransformRealOutput() {
		return fftOutputReal;
	}
	
	public static Double[] getMic() {
		return micOutput.toArray(new Double[] {});
	}
	

}
