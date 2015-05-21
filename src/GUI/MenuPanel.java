package GUI;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class MenuPanel extends JPanel {
	
	private ArrayList<JButton> buttons;
	private BufferedImage background_image;
	
	public MenuPanel(JFrame frame) {
		buttons = new ArrayList<JButton>();
		initialize();
		validateToMainFrame(frame);
	}

	private void initialize() {
		
		GridBagConstraints gbc = new GridBagConstraints();
		this.setPreferredSize(new Dimension(465,750));
		setLayout(new GridBagLayout());
		
		//set background
		try {
			background_image = ImageIO.read(getClass().getResource("resource/titlescreen.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
			
		//Exit Button
		JButton quitButton = new JButton(new ImageIcon(getClass().getResource("resource/quitButton.png")));
		quitButton.setActionCommand("Quit");
		quitButton.setSelectedIcon(new ImageIcon(getClass().getResource("resource/quitHover.png")));
		quitButton.setPressedIcon(new ImageIcon(getClass().getResource("resource/quitPressed.png")));
		quitButton.setContentAreaFilled(false);
		gbc.gridx = 1;
		gbc.gridy = 3;
		this.add(quitButton, gbc);
		buttons.add(quitButton);
		
		//Help Button
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
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background_image, 0, 0, getWidth(), getHeight(), null);
	}
	
	/**
	 * List of buttons in this panel
	 * 
	 * @return buttons
	 */
	public ArrayList<JButton> getButtons() {
		return buttons;
	}
	
}
