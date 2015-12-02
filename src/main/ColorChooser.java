package main;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JColorChooser;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ColorChooser {
	public Color fillColor ; 
	
	static JFrame frame;
	public ColorChooser() {
		initialize();
	
	}
	public Color getFillColor() {
		return fillColor ; 
	}
	public void setFillColor(Color c) {
		fillColor = c ; 
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 629, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JColorChooser colorChooser = new JColorChooser();
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				
			}
		});
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		JButton btnCan = new JButton("Cancel");
		btnCan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		btnCan.setFont(new Font("Tahoma", Font.PLAIN, 30));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(colorChooser, GroupLayout.PREFERRED_SIZE, 615, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(132, Short.MAX_VALUE)
					.addComponent(btnOk, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
					.addGap(43)
					.addComponent(btnCan, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
					.addGap(131))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(colorChooser, GroupLayout.PREFERRED_SIZE, 329, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnOk, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCan, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
		Color c = JColorChooser.showDialog(null, null, null);
		setFillColor(c);
	}
}
