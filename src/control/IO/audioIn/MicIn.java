package control.IO.audioIn;


import javax.swing.JFrame;

import org.apache.commons.math3.complex.Complex;

import control.main.Driver;

public class MicIn {

	private boolean showFFT = true;
	private boolean showSpectrum = false;
	
	private AudioAnalyser aa;
	private SoundCaptureThread mic;
	
	private Thread audioAnalysisRunner;
	private Thread micInRunner;
	
	private JFrame fftFrame;
	private JFrame spectrumFrame;
	private FFTOutputDisplay fftDisplay;
	private SpectrumOutputDisplay specDisplay;
	
	public MicIn() {
		
		mic = new SoundCaptureThread();
		aa = new AudioAnalyser(mic);
		
		startThreads();
		
		showFFTOutput();
	}
	
	public void startThreads() {
		Driver.trace("Starting MicSoundCapture Thread");
		micInRunner = new Thread(mic);
		micInRunner.setName("MicSoundCapture");
		mic.setRunning(true);
		micInRunner.start();
		
		Driver.trace("Starting AudioAnalysisProvider Thread");
		audioAnalysisRunner = new Thread(aa);
		audioAnalysisRunner.setName("AudioAnalysisProvider");
		aa.setRunning(true);
		audioAnalysisRunner.start();
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
		//Driver.trace("starting fft output threads");

		if (showFFT) {
			fftFrame = new JFrame("FFT output");
			fftFrame.setBounds(100, 100, 560, 550);
			fftFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//frame.getContentPane().setLayout(new MigLayout("",
			//		"[0px,growprio 50,grow,shrinkprio 50][800px][0px,growprio 50,grow,shrinkprio 50]",
			//		"[0px,growprio 50,grow,shrinkprio 50][800px][0px,growprio 50,grow,shrinkprio 50]"));
			fftDisplay = new FFTOutputDisplay();
			fftFrame.add(fftDisplay);
			fftFrame.setVisible(true);
			fftFrame.setAlwaysOnTop(true);
			//frame.setAlwaysOnTop(false);
			
			fftDisplay.start();
		}
		
		if (showSpectrum) {
			spectrumFrame = new JFrame("Spectrum output");
			spectrumFrame.setBounds(100, 100, 560, 550);
			spectrumFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//frame.getContentPane().setLayout(new MigLayout("",
			//		"[0px,growprio 50,grow,shrinkprio 50][800px][0px,growprio 50,grow,shrinkprio 50]",
			//		"[0px,growprio 50,grow,shrinkprio 50][800px][0px,growprio 50,grow,shrinkprio 50]"));
			specDisplay = new SpectrumOutputDisplay();
			spectrumFrame.add(specDisplay);
			spectrumFrame.setVisible(true);
			spectrumFrame.setAlwaysOnTop(true);
			//frame.setAlwaysOnTop(false);
	
			
			specDisplay.start();
		}

	}
	
}
