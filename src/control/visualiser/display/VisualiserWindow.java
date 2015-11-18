package control.visualiser.display;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import control.IO.audioIn.MicIn;
import control.main.Driver;


public class VisualiserWindow implements KeyListener, MouseListener {

    private GraphicsDevice gd[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
    private int currentScreen = -1;
	
	JFrame visFrame;
	
	DisplayRenderer renderer;
	
	boolean isMaximised = false;
	
	int posX, posY, width, height;
	
	public VisualiserWindow() {
		new MicIn();
		showFFTOutput();
	}
	
	private void showFFTOutput() {
	
		visFrame = new JFrame("Visualiser");
		visFrame.setBounds(100, 100, 560, 550);
		visFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.getContentPane().setLayout(new MigLayout("",
		//		"[0px,growprio 50,grow,shrinkprio 50][800px][0px,growprio 50,grow,shrinkprio 50]",
		//		"[0px,growprio 50,grow,shrinkprio 50][800px][0px,growprio 50,grow,shrinkprio 50]"));
		renderer = new DisplayRenderer(visFrame);
		visFrame.add(renderer);
		visFrame.setVisible(true);
		visFrame.setAlwaysOnTop(true);
		renderer.addMouseListener(this);
		renderer.addKeyListener(this);
		
		//frame.setAlwaysOnTop(false);
		

		renderer.start();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2 && !e.isConsumed()) {
		     e.consume();
		     //handle double click event.
		     Driver.trace("double click");
		     if (isMaximised) {
		    	 isMaximised = false;
		    	 visFrame.setVisible(false);
		         visFrame.dispose();
		    	 visFrame.setUndecorated(false);
		    	 //gd[currentScreen].setFullScreenWindow(null);
		    	 visFrame.setExtendedState(JFrame.NORMAL);
		    	 visFrame.setBounds(posX, posY, width, height);
		    	 visFrame.setVisible(true);
		    	 
		     } else {
		    	 isMaximised = true;
		    	 posX = visFrame.getX();
		    	 posY = visFrame.getY();
		    	 width = visFrame.getWidth();
		    	 height = visFrame.getHeight();
		    	 visFrame.setVisible(false);
		         visFrame.dispose();
		    	 visFrame.setUndecorated(true);
		    	 //currentScreen = getScreen();
		    	 //gd[currentScreen].setFullScreenWindow(visFrame);
		    	 visFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		    	 visFrame.setVisible(true);
		    	 visFrame.validate();
		     }
		     visFrame.revalidate();
		}
		
	}
	
	public int getScreen() {
		GraphicsConfiguration config = visFrame.getGraphicsConfiguration();
		GraphicsDevice myScreen = config.getDevice();
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		// AFAIK - there are no guarantees that screen devices are in order... 
		// but they have been on every system I've used.
		GraphicsDevice[] allScreens = env.getScreenDevices();
		int myScreenIndex = -1;
		for (int i = 0; i < allScreens.length; i++) {
		    if (allScreens[i].equals(myScreen))
		    {
		        myScreenIndex = i;
		        break;
		    }
		}
		System.out.println("window is on screen" + myScreenIndex);
		return myScreenIndex;
	}
	

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		Driver.trace("entered");
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		Driver.trace("exited");
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
