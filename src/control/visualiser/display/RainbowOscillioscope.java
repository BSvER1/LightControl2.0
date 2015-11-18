package control.visualiser.display;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.util.Random;

import javax.swing.JFrame;

import org.apache.commons.math3.analysis.function.Sqrt;

import control.IO.audioIn.AudioAnalyser;
import control.main.Driver;

public class RainbowOscillioscope extends Visualisation {

	Sqrt sqrt = new Sqrt();
	Random r = new Random();
	
//	Color col[];

	public RainbowOscillioscope(JFrame parent) {
		super(parent);
		
//		genColTable();
	}
	
//	private void genColTable() {
//		col = new Color[1024];
		
//		for (int i = 0; i < 1024; i++) {
//			col[i] = new Color((int)(Math.sin(0.01*i+4*Math.PI/3)*128 + 127), 
//					(int)(Math.sin(0.01*i)*128 + 127), 
//					(int)(Math.sin(0.01*i+2*Math.PI/3)*128 + 127));
//		}
//	}

	public void render(BufferStrategy bs) throws IllegalStateException {
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

		g2d.setFont(new Font("Serif", Font.PLAIN, 8));

		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, getParent().getWidth(), getParent().getHeight());
		

		//		AffineTransform trans = AffineTransform.getRotateInstance(Math.toRadians(angle), getParent().getWidth()/2, getParent().getHeight()/2);
		//		g2d.transform(trans);


		//FFT OUTPUT
		int lineThickness = 1;
		g2d.setStroke(new BasicStroke(lineThickness));

		int xOffset = 0;
		double yScale = 0.01;
		double cutOff = 2.5;

		int yPos = getParent().getHeight()/2;
		double xScale = Double.valueOf(getParent().getWidth())/999.0;
		if (xScale <= 0) {
			xScale = 1;
		}
		//Driver.trace(""+1000*xScale+":"+getParent().getWidth());


		if (AudioAnalyser.getMic() != null) {
			for (int i = 0; i < Math.min(getParent().getWidth(), 999); i++) {
			//for (int i = 1001; i < Math.min(AudioAnalyser.getMic().length-1, 2024); i++) {
				try {
//					col[i] = new Color((int)(Math.sin(0.01*i+4*Math.PI/3)*128 + 127), 
//							(int)(Math.sin(0.01*i)*128 + 127), 
//							(int)(Math.sin(0.01*i+2*Math.PI/3)*128 + 127));
					g2d.setColor(getColor(angle*0.0001,-angle*0.00015,-angle*0.00003,0.0,2*Math.PI/3,4*Math.PI/3,128,127,i));
					
					g2d.drawLine((int)(i*xScale), (int) (yPos-yScale*(AudioAnalyser.getMic()[xOffset+i])), (int)((i+1)*xScale), (int) (yPos-yScale*(AudioAnalyser.getMic()[xOffset+i+2])));
				} catch (ArrayIndexOutOfBoundsException e) {
				} catch (NullPointerException e) {
				}
			}
		}

		//		AffineTransform negTrans = AffineTransform.getRotateInstance(Math.toRadians(-angle), getParent().getWidth()/2, getParent().getHeight()/2);
		//		g2d.transform(negTrans);



		g.dispose();
		g2d.dispose();


		bs.show();
	}
	
	public Color getColor(double freq1, double freq2, double freq3, double phase1, 
			double phase2, double phase3, int center, int width, int pos) {
		return new Color((int) (Math.sin(freq1 * pos + phase1) * width + center), 
				(int) (Math.sin(freq2 * pos + phase2) * width + center), 
				(int) (Math.sin(freq3 * pos + phase3) * width + center));
	}

	@Override
	public JFrame getParent() {
		return parent;
	}
}
