package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

public class SidePanel extends JPanel {

	/**
	 * @param turnDisplay	JLabel object that displays player turn.
	 * @param buttons		List of buttons in this SidePanel.
	 * @param background_image	The image file of background image.
	 */
	JLabel turnDisplay;
	private ArrayList<JButton> buttons;
	private BufferedImage background_image;
	
	/**
	 * Constructor.
	 * @pre				Mainframe for this JPanel must exist.
	 * @param mainframe	JFrame for which this JPanel will be set in.
	 * @post			A SidePanel object is created with specified size and layout.
	 */
	public SidePanel(JFrame mainframe) {
		buttons = new ArrayList<JButton>();
		initialise();
		validateToMainFrame(mainframe);
	}
	
	/**
	 * Initialises the SidePanel with specific size and layout.
	 * @pre		None.
	 * @post	The SidePanel is initialised with buttons, background, and layout.
	 */
	public void initialise() {		
		//set background, size and layout style
		setLayout(new GridBagLayout());
		setMinimumSize(new Dimension(250,750));
		this.setBackground(Color.white);
		try {
			background_image = ImageIO.read(getClass().getResource("resource/bg-sidepanel.png"));
		} catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0,0,0,0);

		//back to menu button
		JButton exitButton = new JButton(new ImageIcon(getClass().getResource("resource/sidePanel-backmenu.png")));
		exitButton.setToolTipText("Quit this game and return to Main Menu");
		exitButton.setSelectedIcon(new ImageIcon(getClass().getResource("resource/sidePanel-backmenu-hover.png")));
		exitButton.setPressedIcon(new ImageIcon(getClass().getResource("resource/sidePanel-backmenu-pressed.png")));
		exitButton.setContentAreaFilled(false);
		exitButton.setActionCommand("MainMenu");
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(50,0,0,0);
		add(exitButton, gbc);
		buttons.add(exitButton);
		
		//replay button
		JButton newGameButton = new JButton(new ImageIcon(getClass().getResource("resource/sidePanel-newgame.png")));
		newGameButton.setToolTipText("Start a new game");
		newGameButton.setSelectedIcon(new ImageIcon(getClass().getResource("resource/sidePanel-newgame-hover.png")));
		newGameButton.setPressedIcon(new ImageIcon(getClass().getResource("resource/sidePanel-newgame-pressed.png")));
		newGameButton.setContentAreaFilled(false);
		newGameButton.setActionCommand("NewGame");
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(0,0,50,0);
		add(newGameButton, gbc);
		buttons.add(newGameButton);
		
		//player 1/2 turn display
		turnDisplay = new JLabel(new ImageIcon(getClass().getResource("resource/player1-turn.png")));
		turnDisplay.setToolTipText("Player turn");
		turnDisplay.setBorder(null);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(100,0,100,0);
		add(turnDisplay, gbc);
		
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
		difficultyButton.setToolTipText("Quit this game and go back to Game Menu");
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

	/**
	 * Sets SidePanel to the mainframe.
	 * @pre			JFrame for this SidePanel object must exist.
	 * @param frame	The JFrame for this SidePanel.
	 * @post		SidePanel is set to the right of the JFrame, with specified size.
	 */
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
	
	/**
	 * Returns the list of buttons in SidePanel.
	 * @pre		None.
	 * @return	Returns the list of buttons.
	 */
	public ArrayList<JButton> getButtons() {
		return buttons;
	}
	
	/**
	 * Changes the display of player turn, green for Player 1 and red for Player 2.
	 * @pre		There must be two players, indicated by 1 or 2.
	 * @param playerNum	The current player number.
	 * @post	The turn highlights green is it's player 1 turn and red if it's player 2.
	 */
	public void updateTurnDisplay(int playerNum) {
		if (playerNum == 1)
			turnDisplay.setIcon(new ImageIcon(getClass().getResource("resource/player1-turn.png")));
		else
			turnDisplay.setIcon(new ImageIcon(getClass().getResource("resource/player2-turn.png")));
	}

	/**
	 * Paints the background of this JPanel.
	 * @pre		background_image must not be null.
	 * @param g	Inherited Graphics object.
	 * @post	SidePanel is painted with background_image.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background_image, 0, 0, this);
	}
	
	public void toggleSound () {
		//TO IMPLEMENT?
	}
	
}