package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OptionForm {

	private JFrame frmOption;
	private JTextField txtOptionFormWidth;
	private JTextField txtOptionFormHeight;

	/**
	 * Launch the application.
	 */
	public void showFrame() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OptionForm window = new OptionForm();
					window.frmOption.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OptionForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmOption = new JFrame();
		frmOption.setTitle("Option");
		frmOption.setBounds(100, 100, 334, 307);
		
		
		JLabel lblWidtth = new JLabel("Width:");
		lblWidtth.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel lblHeight = new JLabel("Height:");
		lblHeight.setFont(new Font("Arial", Font.PLAIN, 14));
		
		txtOptionFormWidth = new JTextField();
		txtOptionFormWidth.setText("640");
		txtOptionFormWidth.setColumns(10);
		
		txtOptionFormHeight = new JTextField();
		txtOptionFormHeight.setText("480");
		txtOptionFormHeight.setColumns(10);
		
		JButton btnOptionFormApply = new JButton("Apply");
		btnOptionFormApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.width = Integer.parseInt(txtOptionFormWidth.getText()) ; 
				MainFrame.height = Integer.parseInt(txtOptionFormHeight.getText()) ; 
				frmOption.setVisible(false);
			}
		});
		GroupLayout groupLayout = new GroupLayout(frmOption.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblWidtth)
							.addGap(10)
							.addComponent(txtOptionFormWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(btnOptionFormApply)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblHeight)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(txtOptionFormHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(158, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(47)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblWidtth)
						.addComponent(txtOptionFormWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHeight, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtOptionFormHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnOptionFormApply)
					.addContainerGap(134, Short.MAX_VALUE))
		);
		frmOption.getContentPane().setLayout(groupLayout);
	}
}
