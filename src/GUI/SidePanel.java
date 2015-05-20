package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

public class SidePanel extends JPanel {

	JLabel turnDisplay;
	private ArrayList<JButton> buttons;
	private BufferedImage background_image;
	
	public SidePanel(JFrame mainframe) {
		buttons = new ArrayList<JButton>();
		initialise();
		validateToMainFrame(mainframe);
	}
	
	public void initialise() {
		
		//set background
		setLayout(new GridBagLayout());
		setMinimumSize(new Dimension(250,750));
		this.setBackground(Color.white);
		try {
			background_image = ImageIO.read(getClass().getResource("resource/bg-sky.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0,0,0,0);

		//quit button
		JButton exitButton = new JButton(new ImageIcon(getClass().getResource("resource/sidePanel-quit.png")));
		exitButton.setToolTipText("Quit this game and return to Main Menu");
		exitButton.setSelectedIcon(new ImageIcon(getClass().getResource("resource/sidePanel-quit-hover.png")));
		exitButton.setPressedIcon(new ImageIcon(getClass().getResource("resource/sidePanel-quit-pressed.png")));
		exitButton.setContentAreaFilled(false);
		exitButton.setActionCommand("Quit");
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(50,0,100,0);
		add(exitButton, gbc);
		buttons.add(exitButton);
		
		
		//player 1 or 2 turn display
		turnDisplay = new JLabel(new ImageIcon(getClass().getResource("resource/player1-turn.png")));
		turnDisplay.setToolTipText("Player turn");
		turnDisplay.setBorder(null);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(100,0,100,0);
		add(turnDisplay, gbc);
		
		// TO DO - ADD MORE FUNCTIONS/BUTTONS HERE
//		JLabel padding1 = new JLabel();
//		Border padding1Border = BorderFactory.createEmptyBorder(500,0,0,0);
//		padding1.setBorder(BorderFactory.createCompoundBorder(getBorder(), padding1Border));
//		add(padding1);

		//info button
		JButton infoButton = new JButton(new ImageIcon(getClass().getResource("resource/button-info.png")));
		infoButton.setToolTipText("How to play");
		infoButton.setSelectedIcon(new ImageIcon(getClass().getResource("resource/button-info-hover.png")));
		infoButton.setPressedIcon(new ImageIcon(getClass().getResource("resource/button-info-pressed.png")));
		infoButton.setContentAreaFilled(false);
		infoButton.setActionCommand("Help");
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.weightx = 0.33;
		gbc.anchor = GridBagConstraints.LAST_LINE_START;
		add(infoButton, gbc);
		buttons.add(infoButton);
	
		//difficulty button
		JButton difficultyButton = new JButton(new ImageIcon(getClass().getResource("resource/button-diff.png")));
		difficultyButton.setToolTipText("Change difficulty");
		difficultyButton.setSelectedIcon(new ImageIcon(getClass().getResource("resource/button-diff-hover.png")));
		difficultyButton.setPressedIcon(new ImageIcon(getClass().getResource("resource/button-diff-pressed.png")));
		difficultyButton.setContentAreaFilled(false);
		difficultyButton.setActionCommand("Difficulty");
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = 0.33;
		add(difficultyButton, gbc);
		buttons.add(difficultyButton);
		
		//sound button
		JButton soundButton = new JButton(new ImageIcon(getClass().getResource("resource/button-sound.png")));
		soundButton.setToolTipText("Turn on/off sound");
		soundButton.setSelectedIcon(new ImageIcon(getClass().getResource("resource/button-sound-hover.png")));
		soundButton.setPressedIcon(new ImageIcon(getClass().getResource("resource/button-sound-pressed.png")));
		soundButton.setContentAreaFilled(false);
		soundButton.setActionCommand("Sound");
		gbc.gridx = 0;
		gbc.gridy = 3;
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
	
	public void updateTurnDisplay(int playerNum) {
		
		if (playerNum == 1) {
			turnDisplay.setIcon(new ImageIcon(getClass().getResource("resource/player1-turn.png")));
		} else {
			turnDisplay.setIcon(new ImageIcon(getClass().getResource("resource/player2-turn.png")));
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background_image, 0, 0, this);
	}
	
	public void toggleSound () {
		
		//TURN OFF ON SOUND SOMEHOW
	}
	
}