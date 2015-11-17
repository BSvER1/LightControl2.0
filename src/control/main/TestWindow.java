package control.main;

import javax.swing.JFrame;

import control.IO.audioIn.MicIn;


public class TestWindow {

	JFrame fftFrame;
	
	DisplayRenderer renderer;
	
	public TestWindow() {
		new MicIn();
		showFFTOutput();
	}
	
	private void showFFTOutput() {
	
		fftFrame = new JFrame("Visualiser");
		fftFrame.setBounds(100, 100, 560, 550);
		fftFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.getContentPane().setLayout(new MigLayout("",
		//		"[0px,growprio 50,grow,shrinkprio 50][800px][0px,growprio 50,grow,shrinkprio 50]",
		//		"[0px,growprio 50,grow,shrinkprio 50][800px][0px,growprio 50,grow,shrinkprio 50]"));
		renderer = new DisplayRenderer();
		fftFrame.add(renderer);
		fftFrame.setVisible(true);
		fftFrame.setAlwaysOnTop(true);
		//frame.setAlwaysOnTop(false);

		renderer.start();
	}
	
}
