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
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
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
	private String choosenOption = "line"; // line , circle , ellipse , polygon
											// , fillcolor
	JLabel mainLabel = new JLabel("");
	private JTextField txtXLineOfSourcePoint;
	private JTextField txtYLineOfSourcePoint;
	private JTextField txtXLineOfDestinationPoint;
	private JTextField txtYLineOfDestionationPoint;
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

	public static Color lineColor = new Color(179, 237, 252);
	public static Color axisColor = new Color(104, 104, 104);
	public static Color drawColor = new Color(0, 0, 0);

	private Polygon polygon;
	private Line line;

	private JRadioButton radioLineDDAAlgorithm;
	private JRadioButton radioLineBresenhamAlgorithm;
	private JRadioButton radioLineMidPointAlgorithm;
	private JRadioButton radioCircleWithBresenham;
	private JRadioButton radioCircleMidPoint;
	private JRadioButton radioEsclipseBresenham ; 
	private JRadioButton radioEllipseMidPoint;
	private JRadioButton radioFillColorRed;
	private JRadioButton radioFillColorGreen;
	private JRadioButton radioFillColorBlue;
	private JRadioButton radioFillColorWhite ; 
	/**
	 * Launch the application.
	 */
	private int changeXFromRealToDevice(int x) {
		return x + width / 2;
	}

	private int changeYFromRealToDevice(int y) {
		return height / 2 - y;
	}

	private int changeXFromDeviceToReal(int x) {
		return x - width / 2;
	}

	private int changeYFromDeviceToReal(int y) {
		return height / 2 - y;
	}

	private final ButtonGroup buttonGroup_Color = new ButtonGroup();

	public void loadImageToLabel() {
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

		mainLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				int x = e.getX() ; 
				int y = e.getY() ; 
				if (choosenOption.equals("line")) {
					callLine(e);
				}
				else if (choosenOption.equals("circle")) {
					callCircle(x, y);
				} 
				else if(choosenOption.equals("ellipse")) {
					callEllipseClass(x, y);
				}
				else if (choosenOption.equals("polygon")) {
				
					callPolygonClass(e);
				} else if (choosenOption.equals("fillcolor")) {
					callFillColorClass(e.getX(), e.getY());
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
				choosenOption = "line";
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

		txtXLineOfSourcePoint = new JTextField();
		txtXLineOfSourcePoint.setText("0");
		txtXLineOfSourcePoint.setBounds(45, 36, 86, 20);
		panel.add(txtXLineOfSourcePoint);
		txtXLineOfSourcePoint.setColumns(10);

		JLabel lblY = new JLabel("y:");
		lblY.setBounds(20, 74, 20, 14);
		panel.add(lblY);

		txtYLineOfSourcePoint = new JTextField();
		txtYLineOfSourcePoint.setText("0");
		txtYLineOfSourcePoint.setColumns(10);
		txtYLineOfSourcePoint.setBounds(45, 71, 86, 20);
		panel.add(txtYLineOfSourcePoint);

		JLabel lblDestinationPoint = new JLabel("Destination Point:");
		lblDestinationPoint.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDestinationPoint.setBounds(155, 11, 111, 14);
		panel.add(lblDestinationPoint);

		txtXLineOfDestinationPoint = new JTextField();

		txtXLineOfDestinationPoint.setText("100");
		txtXLineOfDestinationPoint.setColumns(10);
		txtXLineOfDestinationPoint.setBounds(190, 36, 86, 20);
		panel.add(txtXLineOfDestinationPoint);

		JLabel label = new JLabel("x:");
		label.setBounds(165, 36, 20, 14);
		panel.add(label);

		txtYLineOfDestionationPoint = new JTextField();
		txtYLineOfDestionationPoint.setText("150");
		txtYLineOfDestionationPoint.setColumns(10);
		txtYLineOfDestionationPoint.setBounds(190, 71, 86, 20);
		panel.add(txtYLineOfDestionationPoint);

		JLabel label_1 = new JLabel("y:");
		label_1.setBounds(165, 74, 20, 14);
		panel.add(label_1);

		JLabel lblType = new JLabel("Type:");
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblType.setBounds(304, 8, 45, 20);
		panel.add(lblType);

		radioLineDDAAlgorithm = new JRadioButton("DDA");
		buttonGroup_1.add(radioLineDDAAlgorithm);
		radioLineDDAAlgorithm.setSelected(true);
		radioLineDDAAlgorithm.setBounds(314, 32, 47, 23);
		panel.add(radioLineDDAAlgorithm);

		radioLineBresenhamAlgorithm = new JRadioButton("Bresenham");
		buttonGroup_1.add(radioLineBresenhamAlgorithm);
		radioLineBresenhamAlgorithm.setBounds(377, 32, 86, 23);
		panel.add(radioLineBresenhamAlgorithm);

		radioLineMidPointAlgorithm = new JRadioButton("Mid Point");
		buttonGroup_1.add(radioLineMidPointAlgorithm);
		radioLineMidPointAlgorithm.setBounds(476, 32, 69, 23);
		panel.add(radioLineMidPointAlgorithm);

		JButton btnDrawLine = new JButton("Apply");
		btnDrawLine.setFont(new Font("Arial", Font.PLAIN, 16));
		btnDrawLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int x_1 = Integer.parseInt(txtXLineOfSourcePoint.getText());
				int y_1 = Integer.parseInt(txtYLineOfSourcePoint.getText());
				int x_2 = Integer.parseInt(txtXLineOfDestinationPoint.getText());
				int y_2 = Integer.parseInt(txtYLineOfDestionationPoint.getText());
				if (isInvalidXValue(x_1) || isInvalidXValue(x_2)) {
					JOptionPane.showMessageDialog(null, "X value is in range [-320,320] !", "ERROR",
							JOptionPane.WARNING_MESSAGE);
					return;
				} else if (isInvalidYValue(y_1) || isInvalidYValue(y_2)) {
					JOptionPane.showMessageDialog(null, "Y value is in range [-240,240] !", "ERROR",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				Point sourcePoint = new Point(changeXFromRealToDevice(x_1), changeYFromRealToDevice(y_1));
				Point destinationPoint = new Point(changeXFromRealToDevice(x_2), changeYFromRealToDevice(y_2));

				Line line = new Line(sourcePoint, destinationPoint, outputImage);

				if (radioLineDDAAlgorithm.isSelected()) {
					line.DDAAlgorithm();
					loadImageToLabel();
				} else if (radioLineBresenhamAlgorithm.isSelected()) {
					line.bresenhamAlgorithm();
					loadImageToLabel();
				} else if (radioLineMidPointAlgorithm.isSelected()) {
					line.midPointAlgorithm();
					loadImageToLabel();
				}
			}
		});
		btnDrawLine.setBounds(600, 10, 80, 80);
		panel.add(btnDrawLine);

		JPanel panel_1 = new JPanel();
		panel_1.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				choosenOption = "circle";
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

		radioCircleWithBresenham = new JRadioButton("Bresenham");
		buttonGroup.add(radioCircleWithBresenham);
		radioCircleWithBresenham.setSelected(true);
		radioCircleWithBresenham.setBounds(270, 46, 86, 23);
		panel_1.add(radioCircleWithBresenham);

		radioCircleMidPoint = new JRadioButton("Mid Point");
		buttonGroup.add(radioCircleMidPoint);
		radioCircleMidPoint.setBounds(370, 46, 86, 23);
		panel_1.add(radioCircleMidPoint);

		JButton btnCircleApply = new JButton("Apply");
		btnCircleApply.setFont(new Font("Arial", Font.PLAIN, 16));
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

				Point centralPoint = new Point(changeXFromRealToDevice(x0), changeYFromRealToDevice(y0));

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
		btnCircleApply.setBounds(600, 10, 80, 80);
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
		txtCircleRadius.setText("20");
		txtCircleRadius.setColumns(10);
		txtCircleRadius.setBounds(149, 45, 86, 20);
		panel_1.add(txtCircleRadius);

		JPanel panel_2 = new JPanel();
		panel_2.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				choosenOption = "ellipse";
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

		radioEsclipseBresenham = new JRadioButton("Bresenham");
		buttonGroup_2.add(radioEsclipseBresenham);
		radioEsclipseBresenham.setSelected(true);
		radioEsclipseBresenham.setBounds(331, 41, 86, 23);
		panel_2.add(radioEsclipseBresenham);

		radioEllipseMidPoint = new JRadioButton("Mid Point");
		buttonGroup_2.add(radioEllipseMidPoint);
		radioEllipseMidPoint.setBounds(431, 41, 86, 23);
		panel_2.add(radioEllipseMidPoint);

		JButton btnEllipseApply = new JButton("Apply");
		btnEllipseApply.setFont(new Font("Arial", Font.PLAIN, 16));
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

				Point centralPoint = new Point(changeXFromRealToDevice(x0), changeYFromRealToDevice(y0));

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
		btnEllipseApply.setBounds(600, 10, 80, 80);
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

		JPanel panel_4 = new JPanel();
		panel_4.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent arg0) {
				choosenOption = "fillcolor";
			}

			public void ancestorMoved(AncestorEvent arg0) {
			}

			public void ancestorRemoved(AncestorEvent arg0) {
			}
		});
		tabbedPane.addTab("Fill Color", null, panel_4, null);
		panel_4.setLayout(null);

		JLabel lblPressLeft = new JLabel("* Press left mouse button to fill color.");
		lblPressLeft.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPressLeft.setBounds(10, 11, 266, 28);
		panel_4.add(lblPressLeft);

		JLabel lblColor = new JLabel("Color:");
		lblColor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblColor.setBounds(20, 38, 43, 28);
		panel_4.add(lblColor);

		radioFillColorRed = new JRadioButton("");
		buttonGroup_Color.add(radioFillColorRed);
		radioFillColorRed.setSelected(true);
		radioFillColorRed.setBounds(70, 43, 20, 23);
		panel_4.add(radioFillColorRed);

		radioFillColorGreen = new JRadioButton("");
		buttonGroup_Color.add(radioFillColorGreen);
		radioFillColorGreen.setBounds(120, 43, 20, 23);
		panel_4.add(radioFillColorGreen);

		radioFillColorBlue = new JRadioButton("");
		buttonGroup_Color.add(radioFillColorBlue);
		radioFillColorBlue.setBounds(170, 43, 20, 23);
		panel_4.add(radioFillColorBlue);
		
		JLabel lblRedColor = new JLabel("");
		lblRedColor.setIcon(new ImageIcon("src\\main\\color-icon\\red.jpg"));
		lblRedColor.setBounds(90, 45, 20, 20);
		lblRedColor.setBorder(BorderFactory.createLineBorder(Color.red));
		panel_4.add(lblRedColor);
		
		JLabel lblGreenColor = new JLabel("");
		lblGreenColor.setIcon(new ImageIcon("src\\main\\color-icon\\green.jpg"));
		lblGreenColor.setBounds(140, 45, 20, 20);
		lblGreenColor.setBorder(BorderFactory.createLineBorder(Color.green));
		panel_4.add(lblGreenColor);
		
		JLabel lblBlueColor = new JLabel("");
		lblBlueColor.setIcon(new ImageIcon("src\\main\\color-icon\\blue.jpg"));
		lblBlueColor.setBounds(190, 45, 20, 20);
		lblBlueColor.setBorder(BorderFactory.createLineBorder(Color.blue));
		panel_4.add(lblBlueColor);
		
		radioFillColorWhite = new JRadioButton("");
		buttonGroup_Color.add(radioFillColorWhite);
		radioFillColorWhite.setBounds(220, 43, 20, 23);
		panel_4.add(radioFillColorWhite);
		
		JLabel lblWhiteColor = new JLabel("");
		lblWhiteColor.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		lblWhiteColor.setIcon(new ImageIcon("src\\main\\color-icon\\white.jpg"));
		lblWhiteColor.setBounds(240, 45, 20, 20);
		panel_4.add(lblWhiteColor);

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
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser save = new JFileChooser();
				save.setFileFilter(new ImageTypeFilter());
				save.setDialogTitle("Lưu ảnh");

				int result = save.showSaveDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					File file = save.getSelectedFile();

					String fileName = file.getName();
					// nếu không có phần mở rộng thì thêm vào đuôi png
					if (!(fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith(".bmp"))) {
						file = new File(file.getAbsolutePath() + ".png");
					}
					try {
						ImageIO.write(outputImage, "png", file);
					} catch (IOException ex) {
						System.out.println("ERROR: " + ex.getMessage());
					}
				}
			}
		});
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
		graphic2d.setColor(new Color(255, 255, 250));
		graphic2d.fillRect(0, 0, width, height);
		Point point = new Point(outputImage);
		// draw line every 10 px
		point.setColor(lineColor);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (i % 10 == 0 || j % 10 == 0) {
					point.setXY(i, j);
					point.drawPixel();
				}
				// vẽ đường biên
				if (i == 0 || j == 0 || i == width - 1 || j == height - 1) {
					point.setColor(drawColor);
					point.setXY(i, j);
					point.drawPixel();
					point.setColor(lineColor);
				}
			}
		}
		// draw axis Oxy
		point.setColor(axisColor);
		for (int i = 0; i < height; i++) {
			point.setXY(width / 2, i);
			point.drawPixel();
		}
		for (int j = 0; j < width; j++) {
			point.setXY(j, height / 2);
			point.drawPixel();
		}
		loadImageToLabel();

	}

	Point sourcePointOfLine;

	private void callLine(MouseEvent e) {
		Point destinationPoint;
		if (line == null) {
			// draw first point with a special circle
			sourcePointOfLine = new Point(e.getX(), e.getY());
			Circle circle = new Circle(sourcePointOfLine, 1, outputImage);
			circle.bresenhamAlgorithm();
			loadImageToLabel();
			// Initialize Line object
			line = new Line(outputImage);

		} else {
			destinationPoint = new Point(e.getX(), e.getY());
			line.setSourcePoint(sourcePointOfLine);
			line.setDestinationPoint(destinationPoint);
			if (radioLineDDAAlgorithm.isSelected()) {
				line.DDAAlgorithm();
			} else if (radioLineBresenhamAlgorithm.isSelected()) {
				line.bresenhamAlgorithm();
			} else if (radioLineMidPointAlgorithm.isSelected()) {
				line.midPointAlgorithm();
			}
			// set text field is same of e.getX , e.getY
			txtXLineOfSourcePoint.setText(String.valueOf(changeXFromDeviceToReal(sourcePointOfLine.getX())));
			txtYLineOfSourcePoint.setText(String.valueOf(changeYFromDeviceToReal(sourcePointOfLine.getY())));
			txtXLineOfDestinationPoint.setText(String.valueOf(changeXFromDeviceToReal(destinationPoint.getX())));
			txtYLineOfDestionationPoint.setText(String.valueOf(changeYFromDeviceToReal(destinationPoint.getY())));
			loadImageToLabel();
			line = null;

		}
	}

	private void callCircle(int x, int y) {
		Point centralPoint = new Point(x, y, outputImage);
		int radius = Integer.parseInt(txtCircleRadius.getText());
		Circle circle = new Circle(centralPoint, radius, outputImage);

		if (radioCircleWithBresenham.isSelected()) {
			circle.bresenhamAlgorithm();
			loadImageToLabel();
		} else if (radioCircleMidPoint.isSelected()) {
			circle.midPointAlgorithm();
			loadImageToLabel();
		}
		txtCircleXValue.setText(String.valueOf( changeXFromDeviceToReal(x)));
		txtCircleYValue.setText(String.valueOf( changeYFromDeviceToReal(y)));

	}
	private void callEllipseClass(int x, int y) {
		Point centralPoint = new Point(x, y, outputImage);
		int a = Integer.parseInt(txtEllipseAValue.getText());
		int b = Integer.parseInt(txtEllipseBValue.getText());
		Ellipse ellipse = new Ellipse(centralPoint, a,b, outputImage);

		if (radioEsclipseBresenham.isSelected()) {
			ellipse.bresenham();
			loadImageToLabel();
		} else if (radioEllipseMidPoint.isSelected()) {
			ellipse.midPointAlgorithm();
			loadImageToLabel();
		}
		txtEllipseXValue.setText(String.valueOf( changeXFromDeviceToReal(x)));
		txtEllipseYValue.setText(String.valueOf( changeYFromDeviceToReal(y)));
	}
	private void callPolygonClass(MouseEvent e) {
		// left click with mouse
		if (e.getButton() == MouseEvent.BUTTON1) {
			if (polygon == null) {
				// draw first point
				Point startPoint = new Point(outputImage);
				startPoint.setXY(e.getX(), e.getY());
				Circle circle = new Circle(startPoint, 1, outputImage);
				circle.bresenhamAlgorithm();
				loadImageToLabel();
				// Initialize polygon object
				polygon = new Polygon(startPoint, outputImage);

			} else {
				Point detinationPoint = new Point(outputImage);
				detinationPoint.setXY(e.getX(), e.getY());
				polygon.drawDestinationPoint(detinationPoint);
				loadImageToLabel();
			}
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			// right click mouse button event
			if (polygon != null) {
				polygon.endDrawing();
				loadImageToLabel();
				polygon = null;
			}
		}

	}

	private void callFillColorClass(int x, int y) {
		Point startPoint = new Point(x, y);
		Color color = new Color(255, 0, 0);
		if (radioFillColorRed.isSelected()) {
			color = new Color(255, 0, 0);
		}
		else if (radioFillColorGreen.isSelected()) {
			color = new Color(0, 255, 0);
		}
		else if (radioFillColorBlue.isSelected()) {
			color = new Color(0, 0, 255);
		}else if( radioFillColorWhite.isSelected() ) {
			color = new Color(255, 255, 255);
		}
		FillColor fillColor = new FillColor(startPoint, outputImage, color);
		fillColor.oilSpillWithStack();
		loadImageToLabel();

	}
}
