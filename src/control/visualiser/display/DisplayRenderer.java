package control.visualiser.display;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.apache.commons.math3.analysis.function.Sqrt;

import control.IO.audioIn.AudioAnalyser;
import control.main.Driver;

@SuppressWarnings("serial")
public class DisplayRenderer extends Canvas implements Runnable {

	JFrame parent;

	private ArrayList<Visualisation> visualisers;
	private int currentVisualisation = 1;
	
	private Thread visRenderer;

	private static boolean running;


	//	double scale;
	//	int xOffset = 40;
	//	int yOffset = 15;

	static int numBars;
	static int barTimeout = 3000; // 3 seconds
	static long lastBarTime, secLastTap;
	static double avgTime = 0;
	static double avgMillis;

	static double angle = 45;

	Sqrt sqrt;

	public DisplayRenderer(JFrame parent) {

		//		setSize(400, 250);
		//		setMaximumSize(new Dimension(400, 250));
		//		setMinimumSize(getMaximumSize());
		//		setPreferredSize(getMaximumSize());

		this.parent = parent;
		
		sqrt = new Sqrt();

		visualisers = new ArrayList<Visualisation>();
		
	}

	public void addVisualisers() {
		visualisers.add(new RainbowOscillioscope(parent));
		visualisers.add(new CenterFFT(parent));
	}

	public void start() {

		Driver.trace("Starting FFT Previewer Thread");

		visRenderer = new Thread(this);
		visRenderer.setName("Visualiser Renderer");
		running = true;
		visRenderer.start();

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
		
		addVisualisers();
		
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
				try {
					visualisers.get(currentVisualisation).render(bs);
				} catch (IllegalStateException e) {}
				delta--;


				if (visualisers.get(currentVisualisation).getAngle() < 180)
					visualisers.get(currentVisualisation).setAngle(visualisers.get(currentVisualisation).getAngle() + 1);
				else
					visualisers.get(currentVisualisation).setAngle(0);
			}

			//System.out.println("render complete");
		}

		bs.dispose();
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
