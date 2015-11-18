package control.visualiser.display;

import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public abstract class Visualisation {

	double angle;
	JFrame parent;
	
	public Visualisation(JFrame parent) {
		this.parent = parent;
	}
	
	public abstract void render(BufferStrategy bs) throws IllegalStateException;
	
	public JFrame getParent() {
		return parent;
	}
	
	public double getAngle() {
		return angle;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
}
