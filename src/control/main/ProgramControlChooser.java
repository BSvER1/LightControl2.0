package control.main;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import control.lighting.display.LedControlGUI;
import control.visualiser.display.VisualiserWindow;

public class ProgramControlChooser extends JFrame {

	private JPanel contentPane;
	
	private String productName = "LightControl";
	private String version = "v2.01-alpha";
	
	VisualiserWindow vis = null;
	LedControlGUI led = null;

	/**
	 * Create the frame.
	 */
	public ProgramControlChooser() {
		try {
			UIManager.setLookAndFeel(
			        UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		
		setTitle(productName + " "+ version);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		setMinimumSize(new Dimension(200, 320));
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(10, 1, 0, 0));
		
		JLabel lblTitle = new JLabel(productName);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTitle);
		
		JToggleButton tglbtnVisualiser = new JToggleButton("Visualiser");
		tglbtnVisualiser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tglbtnVisualiser.isSelected()) {
					if (vis == null) {
						vis = new VisualiserWindow();
					} else {
						vis.setVisible(true);
					}
				} else {
					if (vis == null) {
						vis = new VisualiserWindow();
					} else {
						vis.setVisible(false);
					}
				}
			}
		});
		contentPane.add(tglbtnVisualiser);
		tglbtnVisualiser.setFocusable(false);
		
		JToggleButton tglbtnLaserControl = new JToggleButton("Laser Control");
		contentPane.add(tglbtnLaserControl);
		tglbtnLaserControl.setFocusable(false);
		
		JToggleButton tglbtnLedDisplayControl = new JToggleButton("LED Display Control");
		tglbtnLedDisplayControl.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tglbtnLedDisplayControl.isSelected()) {
					if (led == null) {
						led = new LedControlGUI();
					} else {
						led.setVisible(true);
					}
				} else {
					if (led == null) {
						led = new LedControlGUI();
					} else {
						led.setVisible(false);
					}
				}
				
			}
		});
		contentPane.add(tglbtnLedDisplayControl);
		this.setVisible(true);
		tglbtnLedDisplayControl.setFocusable(false);
		
		
		this.setDefaultLookAndFeelDecorated(true);
		
//		this.addComponentListener(new ComponentAdapter() {
//		    public void componentResized(ComponentEvent e) {
//		        // do stuff
//		    	Driver.trace(e.toString());
//		    }
//		});
	}

}
