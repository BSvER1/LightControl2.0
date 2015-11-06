package control.IO.audioIn;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

import control.main.Driver;

@SuppressWarnings("serial")
public class FFTOutputDisplay extends Canvas implements Runnable {


	Thread agentsPreview;

	static boolean running;
	public static boolean drawGradientMap = false;

	double scale;
	int xOffset = 40;
	int yOffset = 15;

	static int numBars;
	static int barTimeout = 3000; // 3 seconds
	static long lastBarTime, secLastTap;
	static double avgTime = 0;
	static double avgMillis;

	public FFTOutputDisplay() {
		
		setSize(800, 800);
		setMaximumSize(new Dimension(800, 800));
		setMinimumSize(getMaximumSize());
		setPreferredSize(getMaximumSize());

		Driver.trace("Starting preview window thread.");
	}

	public void start() {
		// start thread
		agentsPreview = new Thread(this);
		agentsPreview.setName("Agents Preview Updater");
		running = true;
		agentsPreview.start();

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

			//scale = 800 / Universe.getBounds(0);

			long now = System.nanoTime();
			// System.out.println((double) ((now-lastTime)/timePerTick));
			delta += ((now - lastTime) / timePerTick);
			// System.out.println(delta);
			lastTime = now;

			while (delta >= 1) {

				delta--;
			}

			render(bs);
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
		g2d.setColor(Color.RED);
		
		int lineThickness = 1;
		if (AudioAnalyser.getTransformOutput() != null) {
			for (int i = 0; i < AudioAnalyser.getTransformRealOutput().length; i++) {
				g2d.drawLine(5+lineThickness*i, 100, 5+lineThickness*i, (int) (100+0.005*(AudioAnalyser.getTransformRealOutput()[i])));
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
