package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;

public class SidePanel extends JPanel {

	private ArrayList<JButton> buttons;
	
	public SidePanel(JFrame mainframe) {

		buttons = new ArrayList<JButton>();
		initialise();
		validateToMainFrame(mainframe);
		
	}
	
	public void initialise() {
		
		setLayout(new GridBagLayout());
		setMinimumSize(new Dimension(250,750));	
		this.setBackground(Color.white);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0,0,0,0);

		//cat button - this is temporary holder for player1/player2 turn
		JButton catButton = new JButton(new ImageIcon(getClass().getResource("resource/button-cat-player-turn.png")));
		//exitButton.setActionCommand("Exit");
		catButton.setToolTipText("Random button");
		catButton.setSelectedIcon(new ImageIcon(getClass().getResource("resource/button-cat-pc.png")));
		catButton.setContentAreaFilled(false);
		catButton.setBorder(null);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(catButton, gbc);
		buttons.add(catButton);
		
		// this is where other buttons should go
		JLabel padding1 = new JLabel();
		Border padding1Border = BorderFactory.createEmptyBorder(500,0,0,0);
		padding1.setBorder(BorderFactory.createCompoundBorder(getBorder(), padding1Border));
		add(padding1);
		
		//quit button
		JButton exitButton = new JButton(new ImageIcon(getClass().getResource("resource/button-back-dk.png")));
		exitButton.setActionCommand("Quit");
		exitButton.setToolTipText("Quit this game and return to Main Menu");
		exitButton.setSelectedIcon(new ImageIcon(getClass().getResource("resource/button-back-hl.png")));
		exitButton.setPressedIcon(new ImageIcon(getClass().getResource("resource/button-back-pressed.png")));
		exitButton.setContentAreaFilled(false);
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(exitButton, gbc);
		buttons.add(exitButton);
		
		//info button
		JButton infoButton = new JButton(new ImageIcon(getClass().getResource("resource/button-info.png")));
		infoButton.setActionCommand("Help");
		infoButton.setToolTipText("How to play");
		infoButton.setSelectedIcon(new ImageIcon(getClass().getResource("resource/button-info-hover.png")));
		infoButton.setPressedIcon(new ImageIcon(getClass().getResource("resource/button-info-pressed.png")));
		infoButton.setContentAreaFilled(false);
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.weightx = 0.33;
		gbc.anchor = GridBagConstraints.LAST_LINE_START;
		add(infoButton, gbc);
		buttons.add(infoButton);
	
		//difficulty button
		JButton difficultyButton = new JButton(new ImageIcon(getClass().getResource("resource/button-diff.png")));
		difficultyButton.setActionCommand("Difficulty");
		difficultyButton.setToolTipText("Change difficulty");
		difficultyButton.setSelectedIcon(new ImageIcon(getClass().getResource("resource/button-diff-hover.png")));
		difficultyButton.setPressedIcon(new ImageIcon(getClass().getResource("resource/button-diff-pressed.png")));
		difficultyButton.setContentAreaFilled(false);
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = 0.33;
		add(difficultyButton, gbc);
		buttons.add(difficultyButton);
		
		//sound button
		JButton soundButton = new JButton(new ImageIcon(getClass().getResource("resource/button-sound.png")));
		soundButton.setActionCommand("Sound");
		soundButton.setToolTipText("Turn on/off sound");
		soundButton.setSelectedIcon(new ImageIcon(getClass().getResource("resource/button-sound-hover.png")));
		soundButton.setPressedIcon(new ImageIcon(getClass().getResource("resource/button-sound-pressed.png")));
		soundButton.setContentAreaFilled(false);
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.weightx = 0.33;
		gbc.anchor = GridBagConstraints.LAST_LINE_END;
		add(soundButton, gbc);
		buttons.add(soundButton);
		
	}

	public void validateToMainFrame(JFrame frame) {
		//creating new GridBagConstraints for the panel
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.anchor = GridBagConstraints.EAST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.VERTICAL;
		frame.getContentPane().add(this, gbc);	
		frame.validate();
	}
	
	public ArrayList<JButton> getButtons() {
		return buttons;
	}
	
}