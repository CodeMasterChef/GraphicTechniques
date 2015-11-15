package main;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.UIManager;

import javax.swing.JTabbedPane;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class MainFrame {

	private JFrame frame;
	private int width = 641 ; 
	private int height  = 481 ; 
	BufferedImage outputImage = new BufferedImage(width, height, Image.SCALE_DEFAULT);

	JLabel mainLabel = new JLabel("");
	private JTextField txtXValueInSourcePointInLineClass;
	private JTextField txtYValueInSourcePointInLineClass;
	private JTextField txtXValueInDestinationPointInLineClass;
	private JTextField txtYValueInDestinationPointInLineClass;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	private void loadImageToLabel() {
		Image image = outputImage.getScaledInstance(outputImage.getWidth(), outputImage.getHeight(),
				Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(image);
		mainLabel.setIcon(imageIcon);
	}

	public static void main(String[] args) {
		// load 'look and feel' packet
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

		} catch (Exception ex) {
		}
		// ------

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	int changeX(int x) {
		return x + 320;
	}

	int changeY(int y) {
		return 240 - y;
	}

	boolean isInvalidXValue(int x) {
		if (x >= -320 && x <= 320) {
			return false;
		} else {
			return true;
		}
	}

	boolean isInvalidYValue(int y) {
		if (y >= -240 && y <= 240) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public static BufferedImage ci(BufferedImage source) {
		BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
		Graphics g = b.getGraphics();
		g.drawImage(source, 0, 0, null);
		g.dispose();
		return b;
	}

	public void doihinh(BufferedImage p, BufferedImage q) {
		p.createGraphics();
		q.createGraphics();
		p = q;
	}
	public void createNewImage() { 
		Graphics2D graphic2d = outputImage.createGraphics();
		graphic2d.setColor(Color.WHITE);
		graphic2d.fillRect(0, 0, width, height);
		Point point = new Point() ; 
		for (int i = 0; i < height; i++) {
			point = new Point(320, i);
			point.drawPoint(outputImage);
		}
		for (int j = 0; j < width; j++) {
			point = new Point(j, 240);
			point.drawPoint(outputImage);

		}
		loadImageToLabel();
		
		
	}
	

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.WHITE);
		frame.setBounds(150, 0, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel mainPanel = new JPanel();
		mainPanel.setForeground(Color.WHITE);
		mainPanel.setBounds(10, 160, 641, 481);
		mainPanel.setBorder(null);

		
		mainLabel.setBorder(null);
		mainLabel.setBounds(0, 0, 641, 481);
		mainLabel.setForeground(Color.WHITE);
		
		// initilize white papter with black Oxy axis 
		createNewImage(); 
	
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		mainPanel.add(mainLabel);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 20, 990, 130);
		frame.getContentPane().add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Line", null, panel, null);
		panel.setLayout(null);

		JLabel lblSourcePoint = new JLabel("Source Point:");
		lblSourcePoint.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSourcePoint.setBounds(10, 11, 111, 14);
		panel.add(lblSourcePoint);

		JLabel lblX = new JLabel("x:");
		lblX.setBounds(20, 36, 20, 14);
		panel.add(lblX);

		txtXValueInSourcePointInLineClass = new JTextField();
		txtXValueInSourcePointInLineClass.setText("0");
		txtXValueInSourcePointInLineClass.setBounds(45, 36, 86, 20);
		panel.add(txtXValueInSourcePointInLineClass);
		txtXValueInSourcePointInLineClass.setColumns(10);

		JLabel lblY = new JLabel("y:");
		lblY.setBounds(20, 74, 20, 14);
		panel.add(lblY);

		txtYValueInSourcePointInLineClass = new JTextField();
		txtYValueInSourcePointInLineClass.setText("0");
		txtYValueInSourcePointInLineClass.setColumns(10);
		txtYValueInSourcePointInLineClass.setBounds(45, 71, 86, 20);
		panel.add(txtYValueInSourcePointInLineClass);

		JLabel lblDestinationPoint = new JLabel("Destination Point:");
		lblDestinationPoint.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDestinationPoint.setBounds(155, 11, 111, 14);
		panel.add(lblDestinationPoint);

		txtXValueInDestinationPointInLineClass = new JTextField();

		txtXValueInDestinationPointInLineClass.setText("100");
		txtXValueInDestinationPointInLineClass.setColumns(10);
		txtXValueInDestinationPointInLineClass.setBounds(190, 36, 86, 20);
		panel.add(txtXValueInDestinationPointInLineClass);

		JLabel label = new JLabel("x:");
		label.setBounds(165, 36, 20, 14);
		panel.add(label);

		txtYValueInDestinationPointInLineClass = new JTextField();
		txtYValueInDestinationPointInLineClass.setText("100");
		txtYValueInDestinationPointInLineClass.setColumns(10);
		txtYValueInDestinationPointInLineClass.setBounds(190, 71, 86, 20);
		panel.add(txtYValueInDestinationPointInLineClass);

		JLabel label_1 = new JLabel("y:");
		label_1.setBounds(165, 74, 20, 14);
		panel.add(label_1);

		JLabel lblType = new JLabel("Type:");
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblType.setBounds(304, 8, 45, 20);
		panel.add(lblType);

		final JRadioButton radioButtonDDAAlgorithmInLineClass = new JRadioButton("DDA");
		buttonGroup.add(radioButtonDDAAlgorithmInLineClass);
		radioButtonDDAAlgorithmInLineClass.setSelected(true);
		radioButtonDDAAlgorithmInLineClass.setBounds(314, 32, 47, 23);
		panel.add(radioButtonDDAAlgorithmInLineClass);

		final JRadioButton radioButtonBresenhamAlgorithmInLineClass = new JRadioButton("Bresenham");
		buttonGroup.add(radioButtonBresenhamAlgorithmInLineClass);
		radioButtonBresenhamAlgorithmInLineClass.setBounds(363, 32, 86, 23);
		panel.add(radioButtonBresenhamAlgorithmInLineClass);

		JRadioButton radioButtonMidPointAlgorithmInLineClass = new JRadioButton("Mid Point");
		buttonGroup.add(radioButtonMidPointAlgorithmInLineClass);
		radioButtonMidPointAlgorithmInLineClass.setBounds(451, 32, 69, 23);
		panel.add(radioButtonMidPointAlgorithmInLineClass);

		JButton btnDrawLine = new JButton("Apply");
		btnDrawLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int x_1 = Integer.parseInt(txtXValueInSourcePointInLineClass.getText());
				int y_1 = Integer.parseInt(txtYValueInSourcePointInLineClass.getText());
				int x_2 = Integer.parseInt(txtXValueInDestinationPointInLineClass.getText());
				int y_2 = Integer.parseInt(txtYValueInDestinationPointInLineClass.getText());
				if (isInvalidXValue(x_1) || isInvalidXValue(x_2)) {
					JOptionPane.showMessageDialog(null, "X value is in range [-320,320] !", "ERROR",
							JOptionPane.WARNING_MESSAGE);
					return;
				} else if (isInvalidYValue(y_1) || isInvalidYValue(y_2)) {
					JOptionPane.showMessageDialog(null, "Y value is in range [-240,240] !", "ERROR",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
			//	System.out.println(x_1+" " + y_1  +" " + x_2+" " +y_2) ;
				Point sourcePoint = new Point(changeX(x_1), changeY(y_1));
				Point destinationPoint = new Point(changeX(x_2), changeY(y_2));
				Line line = new Line(sourcePoint, destinationPoint, outputImage);
				
				if (radioButtonDDAAlgorithmInLineClass.isSelected()) {
					line.DDAAlgorithm();
					loadImageToLabel();
				} 
				else if ( radioButtonBresenhamAlgorithmInLineClass.isSelected()) { 
					line.bresenhamAlgorithm(); 
					loadImageToLabel();
				}
			}
		});
		btnDrawLine.setBounds(532, 32, 89, 23);
		panel.add(btnDrawLine);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 980, 21);
		frame.getContentPane().add(menuBar);

		JMenu mnFile = new JMenu("FILE");
		menuBar.add(mnFile);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createNewImage();
			}
		});
		mnFile.add(mntmNew);

		JMenuItem mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
	}
}
