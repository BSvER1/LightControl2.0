package control.IO.audioIn;


import javax.swing.JFrame;

import org.apache.commons.math3.complex.Complex;

import control.main.Driver;

public class MicIn {

	private AudioAnalyser aa;
	private SoundCaptureThread mic;
	
	private Thread audioAnalysisRunner;
	private Thread micInRunner;
	
	private JFrame frame;
	private FFTOutputDisplay fftDisplay;
	
	public MicIn() {
		
		mic = new SoundCaptureThread();
		aa = new AudioAnalyser(mic);
		
		startThreads();
		
		showFFTOutput();
	}
	
	public void startThreads() {
		micInRunner = new Thread(mic);
		micInRunner.setName("MicSoundCapture");
		mic.setRunning(true);
		micInRunner.start();
		
		audioAnalysisRunner = new Thread(aa);
		audioAnalysisRunner.setName("AudioAnalysisProvider");
		aa.setRunning(true);
		audioAnalysisRunner.start();
		
		Driver.trace("threads started");
	}
	
	public void stopThreads() {
		mic.setRunning(false);
		aa.setRunning(false);
	}
	
	public Complex[] getTransformOutput() {
		return AudioAnalyser.getTransformOutput();
	}
	
	public double getBPM() {
		return 0.0;
	}
	
	private void showFFTOutput() {
		
		
		Driver.trace("starting fft output threads");

		frame = new JFrame("FFT output");
		frame.setBounds(100, 100, 850, 850);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.getContentPane().setLayout(new MigLayout("",
		//		"[0px,growprio 50,grow,shrinkprio 50][800px][0px,growprio 50,grow,shrinkprio 50]",
		//		"[0px,growprio 50,grow,shrinkprio 50][800px][0px,growprio 50,grow,shrinkprio 50]"));
		fftDisplay = new FFTOutputDisplay();
		frame.add(fftDisplay);
		frame.setVisible(true);
		frame.setAlwaysOnTop(true);
		//frame.setAlwaysOnTop(false);

		fftDisplay.start();


	}
	
}
