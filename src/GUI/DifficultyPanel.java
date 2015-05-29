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

public class DifficultyPanel extends JPanel {

	private ArrayList<JButton> buttons;
	private BufferedImage background_image;

	/**
	 * Constructor for DifficultyPanel.
	 * @pre				JFrame for this JPanel must exist.
	 * @param mainFrame	JFrame for which DifficultyPanel is set in.
	 * @post			DifficultyPanel is set in center of JFrame.
	 */
	public DifficultyPanel(JFrame mainFrame){
		buttons = new ArrayList<JButton>();
		initialize();
		validateToMainFrame(mainFrame);
	}

	/**
	 * Initialises the setting for DifficultyPanel with specified layout.
	 * @pre		None.
	 * @post	DifficultyPanel is initialised with specified layout.
	 */
	private void initialize() {
		JButton button;
		JLabel label;
		
		//set background and layout
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		this.setBackground(Color.BLACK);

		//select difficulty label
		label = new JLabel(new ImageIcon(this.getClass().getResource("resource/select-difficulty.png")));
		gbc.insets = new Insets(0, 0, 60, 0);
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(label, gbc);		
		
		//easy difficulty button
		button = new JButton(new ImageIcon(this.getClass().getResource("resource/difficulty-easy.png")));
		button.setActionCommand("Easy");
		button.setToolTipText("For beginners, with 3 hints");
		button.setSelectedIcon(new ImageIcon(getClass().getResource("resource/difficulty-easy-hover.png")));
		button.setPressedIcon(new ImageIcon(getClass().getResource("resource/difficulty-easy-pressed.png")));
		gbc.insets = new Insets(0,0,0,0);
		gbc.gridx = 0;
		gbc.gridy = 1;
		button.setContentAreaFilled(false);
		add(button,gbc);
		buttons.add(button);
		
		//normal difficulty button
		button = new JButton(new ImageIcon(this.getClass().getResource("resource/difficulty-normal.png")));
		button.setActionCommand("Normal");
		button.setToolTipText("Standard game (1 hint included)");
		button.setSelectedIcon(new ImageIcon(getClass().getResource("resource/difficulty-normal-hover.png")));
		button.setPressedIcon(new ImageIcon(getClass().getResource("resource/difficulty-normal-pressed.png")));
		gbc.insets = new Insets(0,0,0,0);
		gbc.gridx = 0;
		gbc.gridy = 2;
		button.setContentAreaFilled(false);
		add(button,gbc);
		buttons.add(button);
		
		//hard difficulty button
		button = new JButton(new ImageIcon(this.getClass().getResource("resource/difficulty-hard.png")));
		button.setActionCommand("Hard");
		button.setToolTipText("For the experienced players, who need no hints!");
		button.setSelectedIcon(new ImageIcon(getClass().getResource("resource/difficulty-hard-hover.png")));
		button.setPressedIcon(new ImageIcon(getClass().getResource("resource/difficulty-hard-pressed.png")));
		gbc.insets = new Insets(0,0,0,0);
		gbc.gridx = 0;
		gbc.gridy = 3;
		button.setContentAreaFilled(false);
		add(button,gbc);
		buttons.add(button);
		
		//monochrome difficulty button
		button = new JButton(new ImageIcon(this.getClass().getResource("resource/difficulty-monochrome.png")));
		button.setActionCommand("Monochrome");
		button.setToolTipText("The ultimate challenge!");
		button.setSelectedIcon(new ImageIcon(getClass().getResource("resource/difficulty-monochrome-hover.png")));
		button.setPressedIcon(new ImageIcon(getClass().getResource("resource/difficulty-monochrome-pressed.png")));
		gbc.insets = new Insets(0,0,0,0);
		gbc.gridx = 0;
		gbc.gridy = 4;
		button.setContentAreaFilled(false);
		add(button,gbc);
		buttons.add(button);
		
		//back to menu button
		button = new JButton(new ImageIcon(this.getClass().getResource("resource/difficulty-goback.png")));
		button.setActionCommand("Start");
		button.setToolTipText("Go back to previous menu");
		button.setSelectedIcon(new ImageIcon(getClass().getResource("resource/difficulty-goback-hover.png")));
		gbc.insets = new Insets(40,0,0,0);
		gbc.gridx = 0;
		gbc.gridy = 5;
		button.setContentAreaFilled(false);
		add(button,gbc);
		buttons.add(button);
	}
	
	/**
	 * Sets DifficultyPanel in JFrame.
	 * @pre			JFrame must exist.
	 * @param frame	JFrame for which DifficultyPanel is set.
	 * @post		JFrame is set with DifficultyPanel.
	 */
	private void validateToMainFrame(JFrame frame) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		frame.getContentPane().add(this, gbc);
		frame.validate();	
	}

	/**
	 * Sets background with background_image.
	 * @pre		background_image must exist.
	 * @param g	Inherited Graphics object.
	 * @post	DifficultyPanel is set with background_image.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background_image, 0, 0, this);
	}
	
	/**
	 * Returns the list of buttons in this panel.
	 * @pre		None
	 * @return	List of buttons in DifficultyPanel.
	 */
	public ArrayList<JButton> getButtons() {
		return buttons;
	}

}