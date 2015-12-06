package control.lighting.display;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import control.main.Driver;
import net.miginfocom.swing.MigLayout;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class LedControlGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtCurrent;
	private JTextField txtQueued;
	private JTextField txtBpm;


	/**
	 * Create the frame.
	 */
	public LedControlGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmSetPatternDirectory = new JMenuItem("Set Pattern Directory");
		mnFile.add(mntmSetPatternDirectory);
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mnFile.add(mntmQuit);
		
		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);
		splitPane.setEnabled(false);
		splitPane.setResizeWeight(1.0);
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setEnabled(false);
		splitPane_1.setResizeWeight(1.0);
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setRightComponent(splitPane_1);
		
		Canvas canvas = new Canvas();
		canvas.setSize(new Dimension(400, 400));
		canvas.setMinimumSize(canvas.getSize());
		canvas.setMaximumSize(canvas.getSize());
		canvas.setPreferredSize(canvas.getSize());
		splitPane_1.setRightComponent(canvas);
		canvas.setBackground(Color.BLACK);
		
		JSplitPane splitPane_2 = new JSplitPane();
		splitPane_2.setEnabled(false);
		splitPane_2.setResizeWeight(1.0);
		splitPane_1.setLeftComponent(splitPane_2);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane_2.setLeftComponent(scrollPane);
		
		JTree tree = new JTree();
		scrollPane.setViewportView(tree);
		
		JSplitPane splitPane_3 = new JSplitPane();
		splitPane_3.setEnabled(false);
		splitPane_3.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_2.setRightComponent(splitPane_3);
		
		JPanel panel = new JPanel();
		splitPane_3.setLeftComponent(panel);
		panel.setLayout(new MigLayout("", "[100px:100px:100px][100px:100px:100px,grow]", "[25px:25px:25px][25px:25px:25px][25px:25px:25px][25px:25px:25px]"));
		
		JLabel lblCurrent = new JLabel("Current");
		panel.add(lblCurrent, "cell 0 0,alignx trailing");
		
		txtCurrent = new JTextField();
		panel.add(txtCurrent, "cell 1 0,growx");
		txtCurrent.setColumns(10);
		
		JLabel lblQueued = new JLabel("Queued");
		panel.add(lblQueued, "cell 0 1,alignx trailing");
		
		txtQueued = new JTextField();
		panel.add(txtQueued, "cell 1 1,growx");
		txtQueued.setColumns(10);
		
		JLabel lblBpm = new JLabel("BPM");
		panel.add(lblBpm, "cell 0 2,alignx trailing");
		
		txtBpm = new JTextField();
		panel.add(txtBpm, "cell 1 2,growx");
		txtBpm.setColumns(10);
		
		JButton btnQueue = new JButton("Queue");
		panel.add(btnQueue, "cell 0 3,grow");
		
		JButton btnKillAll = new JButton("Kill All");
		panel.add(btnKillAll, "cell 1 3,grow");
		
		this.setVisible(true);
		
		
		
		
		
	}
}

class FileTreeModel implements TreeModel {

    private File root;

    public FileTreeModel(File root) {
        this.root = root;
    }

    @Override
    public void addTreeModelListener(javax.swing.event.TreeModelListener l) {
        //do nothing
    }

    @Override
    public Object getChild(Object parent, int index) {
        File f = (File) parent;
        return f.listFiles()[index];
    }

    @Override
    public int getChildCount(Object parent) {
        File f = (File) parent;
        if (!f.isDirectory()) {
            return 0;
        } else {
            return f.list().length;
        }
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        File par = (File) parent;
        File ch = (File) child;
        return Arrays.asList(par.listFiles()).indexOf(ch);
    }

    @Override
    public Object getRoot() {
        return root;
    }

    @Override
    public boolean isLeaf(Object node) {
        File f = (File) node;
        return !f.isDirectory();
    }

    @Override
    public void removeTreeModelListener(javax.swing.event.TreeModelListener l) {
        //do nothing
    }

    @Override
    public void valueForPathChanged(javax.swing.tree.TreePath path, Object newValue) {
        //do nothing
    }

}
