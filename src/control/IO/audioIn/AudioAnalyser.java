package control.IO.audioIn;

import java.util.Arrays;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;

import control.main.Driver;

public class AudioAnalyser implements Runnable {

	private boolean running;
	
	private SoundCaptureThread mic;
	private FastFourierTransformer fft;
	
	private static Complex[] fftOutput;
	private static double[] fftOutputReal;
	
	int length = SoundCaptureThread.length;
	
	public AudioAnalyser(SoundCaptureThread mic) {
		this.mic = mic;
		fft = new FastFourierTransformer(DftNormalization.UNITARY);
		
		fftOutput = new Complex[length];
		
		for (int i = 0; i < fftOutput.length; i++) {
			fftOutput[i] = new Complex(0.0, 0.0);
		}
	}
	
	@Override
	public void run() {
		
		long lastTime = System.nanoTime();
		double amountOfTicks = 20.0;//per second
		double timePerTick = 1.0e9/amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int ticks = 0;
		
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
			
			if (delta >= 2) { // half a tick behind
				System.err.println("FFT ticks skipped");
				delta = 1.1;
			}
			while(delta>=1){
				
				//System.out.println("bin 0: " +Game.getCaptureThread().getOut().size());
				//System.out.println("bin 1: "+Game.getCaptureThread().getOut2().size());
				
				//mic.flipBin(); //starts at 0
				
//				if (mic.getBin() == 0) {
//					//Driver.trace("" + mic.getOut2AsDouble().length);
//					if (mic.getOut2AsDouble().length >= length) {
//						fftOutput = fft.transform(Arrays.copyOf(mic.getOut2AsDouble(), length), TransformType.FORWARD);
//						mic.resetOut2();
//					}
//				} else {
					//if (mic.getOutAsDouble().length >= length) {
						fftOutput = fft.transform(Arrays.copyOf(mic.getOutAsDouble(), length), TransformType.FORWARD);
						mic.resetOut();

						fftOutputReal = new double[fftOutput.length/4];
						for (int i = 1; i < 1 + fftOutput.length/4; i++) {
							fftOutputReal[i-1] = Math.sqrt(Math.pow(fftOutput[i].getReal(), 2) + Math.pow(fftOutput[i].getImaginary(), 2));
						}
					//}
				
//				}
				
				ticks++;
				delta--;
			}
			
			if(System.currentTimeMillis()-timer>1000){
				timer+=1000;
				//Game.setTPS(ticks);
				ticks = 0;
			}
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
	

}
