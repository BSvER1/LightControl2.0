package control.IO.audioIn;


import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

import org.apache.commons.math3.analysis.function.Sqrt;

import control.main.Driver;

@SuppressWarnings("serial")
public class FFTOutputDisplay extends Canvas implements Runnable {


	Thread fftPreview;

	private static boolean running;
	

//	double scale;
//	int xOffset = 40;
//	int yOffset = 15;

	static int numBars;
	static int barTimeout = 3000; // 3 seconds
	static long lastBarTime, secLastTap;
	static double avgTime = 0;
	static double avgMillis;
	
	Sqrt sqrt;

	public FFTOutputDisplay() {
		
//		setSize(400, 250);
//		setMaximumSize(new Dimension(400, 250));
//		setMinimumSize(getMaximumSize());
//		setPreferredSize(getMaximumSize());
		
		sqrt = new Sqrt();

	}

	public void start() {
		
		Driver.trace("Starting FFT Previewer Thread");
		
		fftPreview = new Thread(this);
		fftPreview.setName("FFT Previewer");
		running = true;
		fftPreview.start();

		invalidate();
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;// per second
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
				Driver.trace("dropping main ticks");
			}
			
			while (delta >= 1) {
				render(bs);
				delta--;
			}

			
			//System.out.println("render complete");
		}

		bs.dispose();
	}

	public void render(BufferStrategy bs) {

		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.setFont(new Font("Serif", Font.PLAIN, 8));

		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		
		//FFT OUTPUT
		int lineThickness = 1;
		g2d.setStroke(new BasicStroke(lineThickness));
		
		int yPos = getParent().getHeight()-20;
		double xScale = (Double.valueOf(AudioAnalyser.getTransformRealOutput().length)/Double.valueOf(getParent().getWidth()-40));
		if (xScale <= 0) {
			xScale = 1;
		}
		//Driver.trace(""+xScale);
		
		
		if (AudioAnalyser.getTransformRealOutput() != null) {
			for (int i = 0; i < getParent().getWidth(); i++) {
				if (i * xScale < AudioAnalyser.getTransformRealOutput().length) {
					g2d.setColor(Color.YELLOW);
					g2d.drawLine(15+lineThickness*i, yPos, 15+lineThickness*i, 
							(int) (yPos-sqrt.value(AudioAnalyser.getTransformRealOutput()[(int) (i*xScale)])));

					g2d.setColor(Color.RED);
					g2d.drawLine(15+lineThickness*i, yPos, 15+lineThickness*i, 
							(int) (yPos-0.6*sqrt.value(AudioAnalyser.getTransformRealOutput()[(int) (i*xScale)])));

					g2d.setColor(Color.BLUE.brighter().brighter());
					g2d.drawLine(15+lineThickness*i, yPos, 15+lineThickness*i, 
							(int) (yPos-0.2*sqrt.value(AudioAnalyser.getTransformRealOutput()[(int) (i*xScale)])));
				}
			}
		}
		

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
