package GUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PlayPanel extends JPanel {
	
	/**
	 * @field buttons			The list of buttons in PlayPanel.
	 * @field background_image	The image file for the background of this PlayPanel.
	 * @field soundButton_on	Sound-On button
	 * @field soundButton_off	Sound-Off button
	 */
	private ArrayList<JButton> buttons;
	private BufferedImage background_image;
	private JButton sound_on, sound_off;
	
	/**
	 * Constructor for PlayPanel.
	 * @pre		JFrame for this PlayPanel object must exist.
	 * @param mainFrame	The JFrame where this PlayPanel is set.
	 * @post	JFrame contains an initialised PlayPanel.
	 */
	public PlayPanel(JFrame mainFrame, int sound_status){	
		buttons = new ArrayList<JButton>();
		initialize(sound_status);
		validateToMainFrame(mainFrame);
	}

	/**
	 * Initialises the parameters for PlayPanel with specific size, layout.
	 * @pre		None.
	 * @post	PlayPanel is initialised with specified size and layout.
	 */
	private void initialize(int sound_status) {
		JButton button;
		JLabel label;

		//set background and layout
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		this.setBackground(Color.BLACK);
		
		//game options label
		label = new JLabel(new ImageIcon(this.getClass().getResource("resource/game-options.png")));
		gbc.insets = new Insets(50, 0, 50, 0);
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(label, gbc);
		
		//PvAI (single player) button
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
		
		//PvP (two player) button
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
		
		//Information button
		button = new JButton(new ImageIcon(getClass().getResource("resource/menu-howto.png")));
		button.setActionCommand("Help");
		button.setToolTipText("Read about rules of the game");
		button.setSelectedIcon(new ImageIcon(getClass().getResource("resource/menu-howto-hover.png")));
		gbc.insets = new Insets(1,10,0,0);
		gbc.gridx = 0;
		gbc.gridy = 4;
		button.setContentAreaFilled(false);
		add(button,gbc);
		buttons.add(button);
				
		//Return to title button
		button = new JButton(new ImageIcon(getClass().getResource("resource/menu-returnmain.png")));
		button.setActionCommand("TitleScreen");
		button.setToolTipText("Return to title screen");
		button.setSelectedIcon(new ImageIcon(getClass().getResource("resource/menu-returnmain-hover.png")));
		gbc.insets = new Insets(1,10,0,0);
		gbc.gridx = 0;
		gbc.gridy = 6;
		button.setContentAreaFilled(false);
		add(button,gbc);
		buttons.add(button);
		
		//sound on button
		sound_on = new JButton(new ImageIcon(getClass().getResource("resource/menu-sound-on.png")));
		sound_on.setActionCommand("Sound Off");
		sound_on.setToolTipText("Turn sound off");
		sound_on.setSelectedIcon(new ImageIcon(getClass().getResource("resource/menu-sound-on-hover.png")));
		gbc.insets = new Insets(1,10,0,0);
		gbc.gridx = 0;
		gbc.gridy = 5;
		sound_on.setContentAreaFilled(false);
		add(sound_on,gbc);
		buttons.add(sound_on);
		
		//sound off button
		sound_off = new JButton(new ImageIcon(getClass().getResource("resource/menu-sound-off.png")));
		sound_off.setActionCommand("Sound On");
		sound_off.setToolTipText("Turn sound on");
		sound_off.setSelectedIcon(new ImageIcon(getClass().getResource("resource/menu-sound-off-hover.png")));
		gbc.insets = new Insets(1,10,0,0);
		gbc.gridx = 0;
		gbc.gridy = 5;
		sound_off.setContentAreaFilled(false);
		add(sound_off,gbc);
		buttons.add(sound_off);
		
		if (sound_status == 1) {
			sound_off.setVisible(false);
		} else {
			sound_on.setVisible(false);
		}
	}
	
	/**
	 * Sets this PlayPanel in the JFrame.
	 * @pre		JFrame must exist.
	 * @param mainframe	JFrame that this PlayPanel is set in.
	 * @post	JFrame sets this PlayPanel in the center.
	 */
	private void validateToMainFrame(JFrame mainframe) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		mainframe.getContentPane().add(this, gbc);
		mainframe.validate();
	}
	
	/**
	 * Paints the background of this JPanel.
	 * @pre		background_image must not be null.
	 * @param g	Inherited Graphics object.
	 * @post	PlayPanel is painted with background_image.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background_image, 0, 0, this);
	}
	
	/**
	 * Returns the list of buttons in PlayPanel.
	 * @pre		None.
	 * @return 	Returns the list of buttons.
	 */
	public ArrayList<JButton> getButtons() {
		return buttons;
	}

	/** 
	 * Changes visibility of Sound Off button on.
	 * @pre		None.
	 * @post	Turns visibility of Sound-on button off and Sound-off button on.
	 */
	public void setSoundOffButton() {
		sound_off.setVisible(true);
		sound_on.setVisible(false);
	}
	
	/** 
	 * Changes visibility of Sound On button on.
	 * @pre		None.
	 * @post	Turns visibility of Sound-off button off and Sound-on button on.
	 */
	public void setSoundOnButton() {
		sound_off.setVisible(false);
		sound_on.setVisible(true);
	}
}
