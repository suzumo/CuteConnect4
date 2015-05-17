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
		label = new JLabel(new ImageIcon(this.getClass().getResource("resource/game-options.png")));
		gbc.insets = new Insets(50, 0, 50, 0);
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(label, gbc);
		
		//PvAI
		button = new JButton(new ImageIcon(getClass().getResource("resource/menu-players1.png")));
		button.setActionCommand("PvAI");
		button.setToolTipText("Play versus the computer!");
		button.setSelectedIcon(new ImageIcon(getClass().getResource("resource/menu-players1-hover.png")));
		gbc.insets = new Insets(0,10,0,0);
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.gridx = 0;
		gbc.gridy = 2;
		button.setContentAreaFilled(false);
		add(button,gbc);
		buttons.add(button);
		
		//PvP
		button = new JButton(new ImageIcon(getClass().getResource("resource/menu-players2.png")));
		button.setActionCommand("PvP");
		button.setToolTipText("Play with your friend!");
		button.setSelectedIcon(new ImageIcon(getClass().getResource("resource/menu-players2-hover.png")));
		gbc.insets = new Insets(1,10,0,0);
		gbc.gridx = 0;
		gbc.gridy = 3;
		button.setContentAreaFilled(false);
		add(button,gbc);
		buttons.add(button);
		
		//Exit game
		button = new JButton(new ImageIcon(getClass().getResource("resource/menu-exitgame.png")));
		button.setActionCommand("Quit");
		button.setToolTipText("Exit game");
		button.setSelectedIcon(new ImageIcon(getClass().getResource("resource/menu-exitgame-hover.png")));
		gbc.insets = new Insets(1,10,0,0);
		gbc.gridx = 0;
		gbc.gridy = 4;
		button.setContentAreaFilled(false);
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
