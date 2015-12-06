package control.lighting.display;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import control.main.Driver;

public class FancySlider extends JProgressBar {
	
	public FancySlider() {
		super();
		setMaximum(1000);
		
		addListeners();
		//setOrientation(orientation);
		//setValue(50);
	}
	
	public FancySlider(int maxValue) {
		super();
		setMaximum(maxValue);
		
		addListeners();
	}
	
	private void modValue(MouseEvent e) {
		Point compLoc = getLocationOnScreen();
		Point mouseLoc = e.getLocationOnScreen();
		if (getOrientation() == SwingConstants.VERTICAL) {
			setValue(getMaximum() - (int) ((mouseLoc.getY() - compLoc.getY())/getBounds().getHeight() * getMaximum()));
		} else if (getOrientation() == SwingConstants.HORIZONTAL) {
			setValue((int) ((mouseLoc.getX() - compLoc.getX())/getBounds().getWidth() * getMaximum()));
		} else {
			Driver.trace("got a strange orientation value on fancy slider");
		}
	}
	
	private void addListeners() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Driver.trace("click");
				modValue(e);
			}
		});
		
		addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				modValue(e);
			}
		});
	}
	
	public double getPercent() {
		return ((double) getValue())/((double) getMaximum());
	}
	
	public double getScaledValue(int maxVal) {
		return getPercent() * maxVal;
	}
}
