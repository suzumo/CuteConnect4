package GUI;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.*;

public class SidePanel extends JPanel{

	public SidePanel(JFrame frame) {

		buttons = new ArrayList<JButton>();
		initialise();
		validateToMainFrame(frame);
		
	}
	
	public void initialise() {
		GridBagConstraints gbc = new GridBagConstraints();

		setLayout(new GridBagLayout());
		gbc.insets = new Insets(0,0,0,0);		
		this.setBackground(Color.BLACK);
		
		//quit button
		JButton exitButton = new JButton(new ImageIcon(getClass().getResource("resource/exitButton.png")));
		exitButton.setActionCommand("Quit");
		exitButton.setToolTipText("Quit this game");
		exitButton.setContentAreaFilled(false);
		//gbc.anchor = GridBagConstraints.NORTH;
		gbc.gridx = 1;
		gbc.gridy = 3;
		this.add(exitButton, gbc);
		buttons.add(exitButton);
		
	}

	public void validateToMainFrame(JFrame frame) {
		
	}
	
	private ArrayList<JButton> buttons;	

}