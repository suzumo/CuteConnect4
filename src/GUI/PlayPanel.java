package GUI;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

public class PlayPanel extends JPanel {
	
	private ArrayList<JButton> buttons;
	private BufferedImage background_image;
	
	public PlayPanel(JFrame mainFrame){	
		buttons = new ArrayList<JButton>();
		initialize();
		validateToMainFrame(mainFrame);
	}

	private void initialize() {
		JButton button;
		JLabel label;

		//set background and layout
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		this.setBackground(Color.BLACK);
//		try {
//			background_image = ImageIO.read(getClass().getResource("resource/bg-dark4a.png"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
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
		
		//How to play
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
				
		//Return to title
		button = new JButton(new ImageIcon(getClass().getResource("resource/menu-returnmain.png")));
		button.setActionCommand("TitleScreen");
		button.setToolTipText("Return to title screen");
		button.setSelectedIcon(new ImageIcon(getClass().getResource("resource/menu-returnmain-hover.png")));
		gbc.insets = new Insets(1,10,0,0);
		gbc.gridx = 0;
		gbc.gridy = 5;
		button.setContentAreaFilled(false);
		add(button,gbc);
		buttons.add(button);
		
		//Exit game
		button = new JButton(new ImageIcon(getClass().getResource("resource/menu-exitgame.png")));
		button.setActionCommand("Quit");
		button.setSelectedIcon(new ImageIcon(getClass().getResource("resource/menu-exitgame-hover.png")));
		gbc.insets = new Insets(1,10,0,0);
		gbc.gridx = 0;
		gbc.gridy = 6;
		button.setContentAreaFilled(false);
		add(button,gbc);
		buttons.add(button);
	}
	
	private void validateToMainFrame(JFrame frame) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		frame.getContentPane().add(this, gbc);
		frame.validate();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background_image, 0, 0, this);
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
