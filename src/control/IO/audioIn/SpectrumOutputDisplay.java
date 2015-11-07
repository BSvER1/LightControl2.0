package control.IO.audioIn;


import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import control.main.Driver;

import org.apache.commons.math3.analysis.function.Sqrt;

@SuppressWarnings("serial")
public class SpectrumOutputDisplay extends Canvas implements Runnable {


	Thread spectrumDisplay;

	private static boolean running;
	
	//private double data[][];
	
	private BufferedImage data;
	
	int historyLength = (int) Math.pow(2, 8);
	
	

//	double scale;
//	int xOffset = 40;
//	int yOffset = 15;

	static int numBars;
	static int barTimeout = 3000; // 3 seconds
	static long lastBarTime, secLastTap;
	static double avgTime = 0;
	static double avgMillis;
	
	Sqrt sqrt;

	public SpectrumOutputDisplay() {
		
//		setSize(400, 250);
//		setMaximumSize(new Dimension(400, 250));
//		setMinimumSize(getMaximumSize());
//		setPreferredSize(getMaximumSize());
		
		sqrt = new Sqrt();
		
		data = new BufferedImage(historyLength, SoundCaptureThread.length/4, BufferedImage.TYPE_3BYTE_BGR);
		
		for (int i = 0; i < data.getWidth(); i++) {
			for (int j = 0; j < data.getHeight(); j++) {
				data.setRGB(i, j, Color.BLACK.getRGB());
			}
		}

	}

	public void start() {
		
		Driver.trace("Starting Spectrum Display Thread");
		
		
		
		spectrumDisplay = new Thread(this);
		spectrumDisplay.setName("SpectrumDisplay");
		running = true;
		spectrumDisplay.start();

		invalidate();
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 30.0;// per second
		double timePerTick = 1000000000 / amountOfTicks;
		double delta = 0;


		while (getParent() == null);

		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {
			this.createBufferStrategy(3);
			bs = this.getBufferStrategy();
		}

		while (running) {


			long now = System.nanoTime();
			
			delta += ((now - lastTime) / timePerTick);
			//System.out.println(delta);
			lastTime = now;

			if (delta >= 5) {
				delta = 1.2;
				//Driver.trace("dropping main ticks");
			}
			
			while (delta >= 1) {
				shiftDown();
				fillNext();
				render(bs);
				delta--;
			}

			
			//System.out.println("render complete");
		}

		bs.dispose();
	}

	private void shiftDown() {
		for (int i = 1; i < data.getWidth(); i++) {
			for (int j = 0; j < data.getHeight(); j++) {
				data.setRGB(i-1, j, data.getRGB(i, j));
			}
		}
	}
	
	private void fillNext() {
		double maxValue = 0;
		for (int i = 0; i < data.getHeight(); i++) {
			data.setRGB(historyLength-1, i, (int) (sqrt.value(AudioAnalyser.getTransformRealOutput()[i]))+50);
			if (data.getRGB(historyLength-1, i) > maxValue) {
				maxValue = data.getRGB(historyLength-1,i);
			}
		}
		
	}
	
	public void render(BufferStrategy bs) {

		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.setFont(new Font("Serif", Font.PLAIN, 8));

		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		
		int yScale = 1;
		while (data.getHeight()/yScale > getParent().getHeight()) {
			yScale++;
		}
		
		
		g2d.drawImage(data, 0, 0, data.getWidth(), data.getHeight()/yScale, null);
		//g2d.drawImage(data, 0, 0, null);

		g.dispose();
		g2d.dispose();

		bs.show();
	}

	public void calcTime() {
		if (System.currentTimeMillis() - lastBarTime > barTimeout) {
			lastBarTime = System.currentTimeMillis();
			numBars = 1;
		} else if (numBars > 600) {
			numBars = 600;
		}
		avgTime = ((numBars - 1) * avgTime + (System.currentTimeMillis() - lastBarTime)) / numBars;
		lastBarTime = System.currentTimeMillis();
		numBars++;
	}

	public static void setRunning(boolean newRunning) {
		running = newRunning;
	}
}
