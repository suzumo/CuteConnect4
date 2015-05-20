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

public class DifficultyPanel extends JPanel {
	
	private ArrayList<JButton> buttons;
	private BufferedImage background_image;
	
	public DifficultyPanel(JFrame mainFrame){
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
//			background_image = ImageIO.read(getClass().getResource("resource/bg-dark6a.png"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		//Select Difficulty label
		label = new JLabel(new ImageIcon(this.getClass().getResource("resource/select-difficulty.png")));
		gbc.insets = new Insets(0, 0, 60, 0);
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(label, gbc);		
		
		//Easy
		button = new JButton(new ImageIcon(this.getClass().getResource("resource/difficulty-easy.png")));
		button.setActionCommand("Easy");
		button.setSelectedIcon(new ImageIcon(getClass().getResource("resource/difficulty-easy-hover.png")));
		button.setPressedIcon(new ImageIcon(getClass().getResource("resource/difficulty-easy-pressed.png")));
		gbc.insets = new Insets(0,0,0,0);
		gbc.gridx = 0;
		gbc.gridy = 1;
		button.setContentAreaFilled(false);
		add(button,gbc);
		buttons.add(button);
		
		//Normal
		button = new JButton(new ImageIcon(this.getClass().getResource("resource/difficulty-normal.png")));
		button.setActionCommand("Normal");
		button.setSelectedIcon(new ImageIcon(getClass().getResource("resource/difficulty-normal-hover.png")));
		button.setPressedIcon(new ImageIcon(getClass().getResource("resource/difficulty-normal-pressed.png")));
		gbc.insets = new Insets(0,0,0,0);
		gbc.gridx = 0;
		gbc.gridy = 2;
		button.setContentAreaFilled(false);
		add(button,gbc);
		buttons.add(button);
		
		//Monochrome
		button = new JButton(new ImageIcon(this.getClass().getResource("resource/difficulty-monochrome.png")));
		button.setActionCommand("Monochrome");
		button.setSelectedIcon(new ImageIcon(getClass().getResource("resource/difficulty-monochrome-hover.png")));
		button.setPressedIcon(new ImageIcon(getClass().getResource("resource/difficulty-monochrome-pressed.png")));
		gbc.insets = new Insets(0,0,0,0);
		gbc.gridx = 0;
		gbc.gridy = 3;
		button.setContentAreaFilled(false);
		add(button,gbc);
		buttons.add(button);
		
		//Back to Menu
		button = new JButton(new ImageIcon(this.getClass().getResource("resource/difficulty-goback.png")));
		button.setActionCommand("Start");
		button.setSelectedIcon(new ImageIcon(getClass().getResource("resource/difficulty-goback-hover.png")));
		button.setPressedIcon(new ImageIcon(getClass().getResource("resource/difficulty-goback-pressed.png")));
		gbc.insets = new Insets(40,0,0,0);
		gbc.gridx = 0;
		gbc.gridy = 4;
		button.setContentAreaFilled(false);
		add(button,gbc);
		buttons.add(button);
	}
	
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

