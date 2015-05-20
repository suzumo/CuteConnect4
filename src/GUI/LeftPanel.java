package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

public class LeftPanel extends JPanel{
	
	public LeftPanel(JFrame frame) {
		setMinimumSize(new Dimension(215,750));
		initialise();
		validateToMainFrame(frame);
	}
	
	public void initialise() {
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(new GridBagLayout());
		gbc.insets = new Insets(0,0,0,0);
		gbc.fill = GridBagConstraints.BOTH;	
		this.setBackground(Color.blue);
		
		JLabel label = new JLabel(new ImageIcon(this.getClass().getResource("resource/bg-leftpanel.png"))); 
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(label, gbc);
	}
	
	private void validateToMainFrame(JFrame mainFrame) {
		//creating new GridBagConstraints for the panel
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		mainFrame.getContentPane().add(this, gbc);
		mainFrame.validate();
	}
	
}