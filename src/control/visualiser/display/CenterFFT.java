package control.visualiser.display;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import org.apache.commons.math3.analysis.function.Sqrt;

import control.IO.audioIn.AudioAnalyser;

public class CenterFFT extends Visualisation {

	Sqrt sqrt = new Sqrt();
	
	public CenterFFT(JFrame parent) {
		super(parent);
	}
	
	public void render(BufferStrategy bs) throws IllegalStateException {
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

		g2d.setFont(new Font("Serif", Font.PLAIN, 8));

		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, getParent().getWidth(), getParent().getHeight());

		AffineTransform trans = AffineTransform.getRotateInstance(Math.toRadians(angle), getParent().getWidth()/2, getParent().getHeight()/2);
		g2d.transform(trans);
		

		//FFT OUTPUT
		int lineThickness = 1;
		g2d.setStroke(new BasicStroke(lineThickness));

		int yScale = 2;
		double cutOff = 2.5;

		int yPos = getParent().getHeight()/2;
		int xPos = getParent().getWidth()/2;
		double xScale = (Double.valueOf(AudioAnalyser.getTransformRealOutput().length)/Double.valueOf(xPos));
		if (xScale <= 0) {
			xScale = 1;
		}


		if (AudioAnalyser.getTransformRealOutput() != null) {
			for (int i = 0; i < getParent().getWidth(); i++) {
				if (i * xScale < AudioAnalyser.getTransformRealOutput().length) {
					if (yScale*sqrt.value(AudioAnalyser.getTransformRealOutput()[(int) (i*xScale)]) > cutOff) {
						g2d.setColor(Color.YELLOW);
						g2d.drawLine(xPos+lineThickness*i, yPos, xPos+lineThickness*i, 
								(int) (yPos-yScale*sqrt.value(AudioAnalyser.getTransformRealOutput()[(int) (i*xScale)])));
						g2d.drawLine(xPos+lineThickness*i*-1, yPos, xPos+lineThickness*i*-1, 
								(int) (yPos-yScale*sqrt.value(AudioAnalyser.getTransformRealOutput()[(int) (i*xScale)])));

						g2d.drawLine(xPos+lineThickness*i, yPos, xPos+lineThickness*i, 
								(int) (yPos+yScale*sqrt.value(AudioAnalyser.getTransformRealOutput()[(int) (i*xScale)])));
						g2d.drawLine(xPos+lineThickness*i*-1, yPos, xPos+lineThickness*i*-1, 
								(int) (yPos+yScale*sqrt.value(AudioAnalyser.getTransformRealOutput()[(int) (i*xScale)])));

						g2d.setColor(Color.RED);
						g2d.drawLine(xPos+lineThickness*i, yPos, xPos+lineThickness*i, 
								(int) (yPos-0.6*yScale*sqrt.value(AudioAnalyser.getTransformRealOutput()[(int) (i*xScale)])));
						g2d.drawLine(xPos+lineThickness*i*-1, yPos, xPos+lineThickness*i*-1, 
								(int) (yPos-0.6*yScale*sqrt.value(AudioAnalyser.getTransformRealOutput()[(int) (i*xScale)])));

						g2d.drawLine(xPos+lineThickness*i, yPos, xPos+lineThickness*i, 
								(int) (yPos+0.6*yScale*sqrt.value(AudioAnalyser.getTransformRealOutput()[(int) (i*xScale)])));
						g2d.drawLine(xPos+lineThickness*i*-1, yPos, xPos+lineThickness*i*-1, 
								(int) (yPos+0.6*yScale*sqrt.value(AudioAnalyser.getTransformRealOutput()[(int) (i*xScale)])));

						g2d.setColor(Color.BLUE.brighter().brighter());
						g2d.drawLine(xPos+lineThickness*i, yPos, xPos+lineThickness*i, 
								(int) (yPos-0.2*yScale*sqrt.value(AudioAnalyser.getTransformRealOutput()[(int) (i*xScale)])));
						g2d.drawLine(xPos+lineThickness*i*-1, yPos, xPos+lineThickness*i*-1, 
								(int) (yPos-0.2*yScale*sqrt.value(AudioAnalyser.getTransformRealOutput()[(int) (i*xScale)])));

						g2d.drawLine(xPos+lineThickness*i, yPos, xPos+lineThickness*i, 
								(int) (yPos+0.2*yScale*sqrt.value(AudioAnalyser.getTransformRealOutput()[(int) (i*xScale)])));
						g2d.drawLine(xPos+lineThickness*i*-1, yPos, xPos+lineThickness*i*-1, 
								(int) (yPos+0.2*yScale*sqrt.value(AudioAnalyser.getTransformRealOutput()[(int) (i*xScale)])));


					}
				}
			}
		}

		AffineTransform negTrans = AffineTransform.getRotateInstance(Math.toRadians(-angle), getParent().getWidth()/2, getParent().getHeight()/2);
		g2d.transform(negTrans);
		
		

		g.dispose();
		g2d.dispose();


		bs.show();
	}

	@Override
	public JFrame getParent() {
		return parent;
	}
}
