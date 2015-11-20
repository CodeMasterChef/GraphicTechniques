package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class MainFrame {

	private JFrame frame;
	public static int width = 641;
	public static int height = 481;
	public BufferedImage outputImage = new BufferedImage(width, height, Image.SCALE_DEFAULT);
	private String choosenOption = "line"; // 0 = line , 1 = circle , 2 =
											// ellipse , 3 = polygon
	JLabel mainLabel = new JLabel("");
	private JTextField txtXValueInSourcePointInLineClass;
	private JTextField txtYValueInSourcePointInLineClass;
	private JTextField txtXValueInDestinationPointInLineClass;
	private JTextField txtYValueInDestinationPointInLineClass;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel mainPanel;
	private JTextField txtCircleXValue;
	private JTextField txtCircleYValue;
	private JTextField txtCircleRadius;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JTextField txtEllipseXValue;
	private JTextField txtEllipseYValue;
	private JTextField txtEllipseAValue;
	private JTextField txtEllipseBValue;
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();

	
	
	/**
	 * Launch the application.
	 */
	private int changeX(int x) {
		return x + width / 2;
	}

	private int changeY(int y) {
		return height / 2 - y;
	}

	Polygon polygon;

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

	

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.WHITE);
		frame.setBounds(150, 0, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainPanel = new JPanel();
		mainPanel.setForeground(Color.WHITE);
		mainPanel.setBounds(10, 160, 641, 481);
		mainPanel.setBorder(null);

		// initilize white papter with black Oxy axis
		createNewImage();

		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		mainLabel.setBorder(null);
		mainLabel.setForeground(Color.WHITE);
		mainLabel.setBackground(Color.WHITE);
		mainLabel.setBounds(0, 0, 641, 481);
		mainLabel.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				if(choosenOption.equals("polygon")) {
					// left click with mouse 
					if(e.getButton() == MouseEvent.BUTTON1) { 
						if (polygon == null) {
							// draw first point
							Point startPoint = new Point(outputImage);
							startPoint.setXY(e.getX(), e.getY());
							Circle circle  = new Circle(startPoint, 1, outputImage) ; 
							circle.bresenhamAlgorithm();
							loadImageToLabel();
							// Initialize polygon object
							polygon = new Polygon(startPoint, outputImage);
						
						}else {
							Point detinationPoint = new Point(outputImage);
							detinationPoint.setXY(e.getX(), e.getY());
							polygon.drawDestinationPoint(detinationPoint);
							loadImageToLabel();
						}
					} else if( e.getButton() == MouseEvent.BUTTON3) {  // right click with mouse
						if(polygon!=null) { 
							polygon.endDrawing();
							loadImageToLabel();
							polygon = null ; 
						}
					}
					
				}
				
			}
		});
		mainPanel.add(mainLabel);
		

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 20, 990, 130);
		frame.getContentPane().add(tabbedPane);

		JPanel panel = new JPanel();
		panel.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent arg0) {
				choosenOption = "line" ; 
			}
			public void ancestorMoved(AncestorEvent arg0) {
			}
			public void ancestorRemoved(AncestorEvent arg0) {
			}
		});
		
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
		txtYValueInDestinationPointInLineClass.setText("150");
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
		buttonGroup_1.add(radioButtonDDAAlgorithmInLineClass);
		radioButtonDDAAlgorithmInLineClass.setSelected(true);
		radioButtonDDAAlgorithmInLineClass.setBounds(314, 32, 47, 23);
		panel.add(radioButtonDDAAlgorithmInLineClass);

		final JRadioButton radioButtonBresenhamAlgorithmInLineClass = new JRadioButton("Bresenham");
		buttonGroup_1.add(radioButtonBresenhamAlgorithmInLineClass);
		radioButtonBresenhamAlgorithmInLineClass.setBounds(377, 32, 86, 23);
		panel.add(radioButtonBresenhamAlgorithmInLineClass);

		final JRadioButton radioButtonMidPointAlgorithmInLineClass = new JRadioButton("Mid Point");
		buttonGroup_1.add(radioButtonMidPointAlgorithmInLineClass);
		radioButtonMidPointAlgorithmInLineClass.setBounds(476, 32, 69, 23);
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

				Point sourcePoint = new Point(changeX(x_1), changeY(y_1));
				Point destinationPoint = new Point(changeX(x_2), changeY(y_2));

				Line line = new Line(sourcePoint, destinationPoint, outputImage);

				if (radioButtonDDAAlgorithmInLineClass.isSelected()) {
					line.DDAAlgorithm();
					loadImageToLabel();
				} else if (radioButtonBresenhamAlgorithmInLineClass.isSelected()) {
					line.bresenham();
					loadImageToLabel();
				} else if (radioButtonMidPointAlgorithmInLineClass.isSelected()) {
					line.midPointAlgorithm();
					loadImageToLabel();
				}
			}
		});
		btnDrawLine.setBounds(614, 32, 103, 42);
		panel.add(btnDrawLine);

		JPanel panel_1 = new JPanel();
		panel_1.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				choosenOption.equals("circle") ; 
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		tabbedPane.addTab("Circle", null, panel_1, null);
		panel_1.setLayout(null);

		JLabel lblCentralPoint = new JLabel("Central Point:");
		lblCentralPoint.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCentralPoint.setBounds(10, 21, 111, 14);
		panel_1.add(lblCentralPoint);

		JLabel lblX_1 = new JLabel("x:");
		lblX_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblX_1.setBounds(10, 46, 13, 14);
		panel_1.add(lblX_1);

		JLabel lblY_1 = new JLabel("y:");
		lblY_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblY_1.setBounds(10, 71, 13, 20);
		panel_1.add(lblY_1);

		txtCircleXValue = new JTextField();
		txtCircleXValue.setText("0");
		txtCircleXValue.setBounds(35, 46, 86, 20);
		panel_1.add(txtCircleXValue);
		txtCircleXValue.setColumns(10);

		txtCircleYValue = new JTextField();
		txtCircleYValue.setText("0");
		txtCircleYValue.setBounds(35, 73, 86, 20);
		panel_1.add(txtCircleYValue);
		txtCircleYValue.setColumns(10);

		final JRadioButton radioCircleWithBresenham = new JRadioButton("Bresenham");
		buttonGroup.add(radioCircleWithBresenham);
		radioCircleWithBresenham.setSelected(true);
		radioCircleWithBresenham.setBounds(270, 46, 86, 23);
		panel_1.add(radioCircleWithBresenham);

		final JRadioButton radioCircleMidPoint = new JRadioButton("Mid Point");
		buttonGroup.add(radioCircleMidPoint);
		radioCircleMidPoint.setBounds(370, 46, 86, 23);
		panel_1.add(radioCircleMidPoint);

		JButton btnCircleApply = new JButton("Apply");
		btnCircleApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int x0 = Integer.parseInt(txtCircleXValue.getText());
				int y0 = Integer.parseInt(txtCircleYValue.getText());

				if (isInvalidXValue(x0)) {
					JOptionPane.showMessageDialog(null, "X value is in range [-320,320] !", "ERROR",
							JOptionPane.WARNING_MESSAGE);
					return;
				} else if (isInvalidYValue(y0)) {
					JOptionPane.showMessageDialog(null, "Y value is in range [-240,240] !", "ERROR",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				int radius = Integer.parseInt(txtCircleRadius.getText());

				Point centralPoint = new Point(changeX(x0), changeY(y0));

				Circle circle = new Circle(centralPoint, radius, outputImage);

				if (radioCircleWithBresenham.isSelected()) {
					circle.bresenhamAlgorithm();
					loadImageToLabel();
				} else if (radioCircleMidPoint.isSelected()) {
					circle.midPointAlgorithm();
					loadImageToLabel();
				}
			}

		});
		btnCircleApply.setBounds(499, 20, 89, 70);
		panel_1.add(btnCircleApply);

		JLabel lblType_1 = new JLabel("Type:");
		lblType_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblType_1.setBounds(248, 16, 111, 24);
		panel_1.add(lblType_1);

		JLabel lblRadius = new JLabel("Radius:");
		lblRadius.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRadius.setBounds(144, 16, 53, 24);
		panel_1.add(lblRadius);

		txtCircleRadius = new JTextField();
		txtCircleRadius.setText("100");
		txtCircleRadius.setColumns(10);
		txtCircleRadius.setBounds(149, 45, 86, 20);
		panel_1.add(txtCircleRadius);

		JPanel panel_2 = new JPanel();
		panel_2.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				choosenOption = "ellipse" ; 
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		tabbedPane.addTab("Ellipse", null, panel_2, null);
		panel_2.setLayout(null);

		JLabel label_2 = new JLabel("Central Point:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_2.setBounds(10, 11, 111, 14);
		panel_2.add(label_2);

		JLabel label_3 = new JLabel("Radius:");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_3.setBounds(158, 6, 53, 24);
		panel_2.add(label_3);

		JLabel label_4 = new JLabel("x:");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_4.setBounds(10, 36, 13, 14);
		panel_2.add(label_4);

		txtEllipseXValue = new JTextField();
		txtEllipseXValue.setText("0");
		txtEllipseXValue.setColumns(10);
		txtEllipseXValue.setBounds(35, 36, 86, 20);
		panel_2.add(txtEllipseXValue);

		txtEllipseYValue = new JTextField();
		txtEllipseYValue.setText("0");
		txtEllipseYValue.setColumns(10);
		txtEllipseYValue.setBounds(35, 67, 86, 20);
		panel_2.add(txtEllipseYValue);

		JLabel lblY_2 = new JLabel("y:");
		lblY_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblY_2.setBounds(10, 61, 13, 23);
		panel_2.add(lblY_2);

		JLabel lblA = new JLabel("a:");
		lblA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblA.setBounds(158, 41, 13, 14);
		panel_2.add(lblA);

		JLabel lblB = new JLabel("b:");
		lblB.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblB.setBounds(158, 68, 13, 14);
		panel_2.add(lblB);

		txtEllipseAValue = new JTextField();
		txtEllipseAValue.setText("50");
		txtEllipseAValue.setColumns(10);
		txtEllipseAValue.setBounds(191, 36, 86, 20);
		panel_2.add(txtEllipseAValue);

		txtEllipseBValue = new JTextField();
		txtEllipseBValue.setText("100");
		txtEllipseBValue.setColumns(10);
		txtEllipseBValue.setBounds(191, 67, 86, 20);
		panel_2.add(txtEllipseBValue);

		JLabel label_5 = new JLabel("Type:");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_5.setBounds(309, 11, 111, 24);
		panel_2.add(label_5);

		final JRadioButton radioEsclipseBresenham = new JRadioButton("Bresenham");
		buttonGroup_2.add(radioEsclipseBresenham);
		radioEsclipseBresenham.setSelected(true);
		radioEsclipseBresenham.setBounds(331, 41, 86, 23);
		panel_2.add(radioEsclipseBresenham);

		final JRadioButton radioEllipseMidPoint = new JRadioButton("Mid Point");
		buttonGroup_2.add(radioEllipseMidPoint);
		radioEllipseMidPoint.setBounds(431, 41, 86, 23);
		panel_2.add(radioEllipseMidPoint);

		JButton btnEllipseApply = new JButton("Apply");
		btnEllipseApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int x0 = Integer.parseInt(txtEllipseXValue.getText());
				int y0 = Integer.parseInt(txtEllipseYValue.getText());

				if (isInvalidXValue(x0)) {
					JOptionPane.showMessageDialog(null, "X value is in range [-320,320] !", "ERROR",
							JOptionPane.WARNING_MESSAGE);
					return;
				} else if (isInvalidYValue(y0)) {
					JOptionPane.showMessageDialog(null, "Y value is in range [-240,240] !", "ERROR",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				int a = Integer.parseInt(txtEllipseAValue.getText());
				int b = Integer.parseInt(txtEllipseBValue.getText());

				Point centralPoint = new Point(changeX(x0), changeY(y0));

				Ellipse ellipse = new Ellipse(centralPoint, a, b, outputImage);

				if (radioEsclipseBresenham.isSelected()) {
					ellipse.bresenham();
					loadImageToLabel();
				} else if (radioEllipseMidPoint.isSelected()) {
					ellipse.midPointAlgorithm();
					loadImageToLabel();
				}
			}
		});
		btnEllipseApply.setBounds(533, 11, 89, 76);
		panel_2.add(btnEllipseApply);

		JPanel panel_3 = new JPanel();
		panel_3.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent arg0) {
				choosenOption = "polygon";

			}

			public void ancestorMoved(AncestorEvent arg0) {
			}

			public void ancestorRemoved(AncestorEvent arg0) {
			}
		});
		tabbedPane.addTab("Polygon", null, panel_3, null);
		panel_3.setLayout(null);
		
		JLabel lblClickChutTri = new JLabel("* Press left mouse button to  start drawing.");
		lblClickChutTri.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblClickChutTri.setBounds(10, 11, 266, 28);
		panel_3.add(lblClickChutTri);
		
		JLabel lblPressRight = new JLabel("* Press right mouse button to finish drawing.");
		lblPressRight.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPressRight.setBounds(10, 49, 311, 28);
		panel_3.add(lblPressRight);

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
	
	
	/*
	 * My code here!
	 * 
	 */
	boolean isInvalidXValue(int x) {
		if (x >= -width / 2 && x <= width / 2) {
			return false;
		} else {
			return true;
		}
	}

	boolean isInvalidYValue(int y) {
		if (y >= -height / 2 && y <= height / 2) {
			return false;
		} else {
			return true;
		}
	}

	public void createNewImage() {
		Graphics2D graphic2d = outputImage.createGraphics();
		graphic2d.setColor(Color.WHITE);
		graphic2d.fillRect(0, 0, width, height);
		Point point = new Point(outputImage);
		for (int i = 0; i < height; i += 2) { // i+=2 to create dot line (.....)
			point.setXY(width / 2, i);
			point.drawPixel();
			// draw milestones 10, 20, ...
			if (i % 10 == 0) {
				for (int k = -2; k <= 2; k++) {
					point.setXY(width / 2 - k, i);
					point.drawPixel();
				}
			}
		}
		for (int j = 0; j < width; j += 2) {
			point.setXY(j, height / 2);
			point.drawPixel();
			// draw milestones 10, 20, ...
			if (j % 10 == 0) {
				for (int k = -2; k <= 2; k++) {
					point.setXY(j, height / 2 - k);
					point.drawPixel();
				}
			}

		}
		loadImageToLabel();

	}
	
}
