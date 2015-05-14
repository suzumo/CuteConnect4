package GUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;



public class PlayPanel extends JPanel {
	private ArrayList<JButton> buttons;
	
	
	public PlayPanel(JFrame mainFrame){
		buttons = new ArrayList<JButton>();

		
		//creating a gridbaglayout
		GridBagLayout gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);
		this.setBackground(Color.BLACK);
		
		initialize();
		validateToMainFrame(mainFrame);
	}

	private void initialize() {
		GridBagConstraints gbc = new GridBagConstraints();
		JButton button;
		JLabel label;
		
		
		//game label
		label = new JLabel(new ImageIcon(this.getClass().getResource("resource/gameLabel.png")));
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(label, gbc);
		
		//options label
		label = new JLabel(new ImageIcon(this.getClass().getResource("resource/optionsLabel.png")));
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(label, gbc);
		
		
		//PvP
		button = new JButton("PvP");
		button.setActionCommand("PvP");
		gbc.insets = new Insets(1,1,1,1);
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(button,gbc);
		buttons.add(button);
		
		//PvAI
		button = new JButton("PvAI");
		button.setActionCommand("PvAI");
		gbc.insets = new Insets(1,1,1,1);
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(button,gbc);
		buttons.add(button);
	}
	
	private void validateToMainFrame(JFrame frame) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 3;
		gbc.gridy = 2;
		frame.getContentPane().add(this, gbc);
		frame.validate();	
	}
	
	/**
	 * Getting the list of buttons in this panel
	 * 
	 * @return buttons
	 */
	public ArrayList<JButton> getButtons() 
	{
		return buttons;
	}

}
