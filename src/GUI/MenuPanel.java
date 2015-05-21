package GUI;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class MenuPanel extends JPanel {
	
	/**
	 * @param buttons	List of buttons in this MenuPanel.
	 * @param background_image	File that stores the background_image for this JPanel.
	 */
	private ArrayList<JButton> buttons;
	private BufferedImage background_image;
	
	/**
	 * Constructor for MenuPanel.
	 * @pre		JFrame for this MenuPanel must exist.
	 * @param mainframe	The JFrame that MenuPanel will be set in.
	 * @post	JFrmae sets this MenuPanel object in it.
	 */
	public MenuPanel(JFrame mainframe) {
		buttons = new ArrayList<JButton>();
		initialize();
		validateToMainFrame(mainframe);
	}

	/**
	 * Initialises the layout, background and size of this MenuPanel object.
	 * @pre		None.
	 * @post	Initiliases the layout, background and size of this MenuPanel with specified details.
	 */
	private void initialize() {		
		GridBagConstraints gbc = new GridBagConstraints();
		this.setPreferredSize(new Dimension(465,750));
		setLayout(new GridBagLayout());
		//set background
		try {
			background_image = ImageIO.read(getClass().getResource("resource/titlescreen.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		//game title label
		JLabel label = new JLabel(new ImageIcon(this.getClass().getResource("resource/title.png"))); 
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = new Insets(60,0,50,0);
		this.add(label, gbc);	
		
		//start button
		JButton startButton = new JButton(new ImageIcon(getClass().getResource("resource/startButton.png")));
		startButton.setActionCommand("Start");
		startButton.setSelectedIcon(new ImageIcon(getClass().getResource("resource/startHover.png")));
		startButton.setPressedIcon(new ImageIcon(getClass().getResource("resource/startPressed.png")));
		startButton.setContentAreaFilled(false);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.insets = new Insets(0,0,0,0);
		this.add(startButton, gbc);
		buttons.add(startButton);
			
		//exit button
		JButton quitButton = new JButton(new ImageIcon(getClass().getResource("resource/quitButton.png")));
		quitButton.setActionCommand("Quit");
		quitButton.setSelectedIcon(new ImageIcon(getClass().getResource("resource/quitHover.png")));
		quitButton.setPressedIcon(new ImageIcon(getClass().getResource("resource/quitPressed.png")));
		quitButton.setContentAreaFilled(false);
		gbc.gridx = 1;
		gbc.gridy = 3;
		this.add(quitButton, gbc);
		buttons.add(quitButton);
		
		//info button
		JButton helpButton = new JButton(new ImageIcon(getClass().getResource("resource/aboutButton.png")));
		helpButton.setActionCommand("Help");
		helpButton.setToolTipText("Read about the game!");
		helpButton.setSelectedIcon(new ImageIcon(getClass().getResource("resource/aboutHover.png")));
		helpButton.setPressedIcon(new ImageIcon(getClass().getResource("resource/aboutPressed.png")));
		helpButton.setContentAreaFilled(false);
		gbc.gridx = 1;
		gbc.gridy = 2;
		this.add(helpButton, gbc);
		buttons.add(helpButton);
	}
	
	/**
	 * Sets this MenuPanel in JFrame.
	 * @pre		JFrame must exist for this object.
	 * @param frame	JFrame for which this MenuPanel will be set in.
	 * @post	MenuPanel is set in JFrame with specified size and in the center.
	 */
	private void validateToMainFrame(JFrame frame) {		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;		
		frame.getContentPane().add(this, gbc);
		frame.validate();
	}
	
	/**
	 * Paint the background of the MenuPanel with background_image.
	 * @pre		background_image must not be null.
	 * @param g	Inherited Graphics object.
	 * @post	MenuPanel background is painted with background_image.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background_image, 0, 0, getWidth(), getHeight(), null);
	}
	
	/**
	 * Returns the list of buttons in MenuPanel.
	 * @pre		None.
	 * @return	Returns the list of buttons.
	 */
	public ArrayList<JButton> getButtons() {
		return buttons;
	}
	
}
