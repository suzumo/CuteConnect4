package GUI;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class MenuPanel extends JPanel {
	
	private ArrayList<JButton> buttons;
	
	public MenuPanel(JFrame frame) {
		buttons = new ArrayList<JButton>();
		initialize();
		validateToMainFrame(frame);
	}

	private void initialize() {
		
		GridBagConstraints gbc = new GridBagConstraints();

		setLayout(new GridBagLayout());
		gbc.insets = new Insets(0,0,0,0);
		this.setBackground(Color.BLACK);
		
		JLabel label = new JLabel(new ImageIcon(this.getClass().getResource("resource/title.png"))); 
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = new Insets(50,0,50,0);
		this.add(label, gbc);	
		
		//start button
		JButton startButton = new JButton(new ImageIcon(getClass().getResource("resource/startButton.png")));
		startButton.setActionCommand("Start");
		startButton.setToolTipText("Start playing!");
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
		quitButton.setToolTipText("Exit game");
		quitButton.setSelectedIcon(new ImageIcon(getClass().getResource("resource/quitHover.png")));
		quitButton.setPressedIcon(new ImageIcon(getClass().getResource("resource/quitPressed.png")));
		quitButton.setContentAreaFilled(false);
		gbc.gridx = 1;
		gbc.gridy = 2;
		this.add(quitButton, gbc);
		buttons.add(quitButton);
		
		//Help Button
		JButton helpButton = new JButton(new ImageIcon(getClass().getResource("resource/aboutButton.png")));
		helpButton.setActionCommand("Help");
		helpButton.setToolTipText("How to play");
		helpButton.setSelectedIcon(new ImageIcon(getClass().getResource("resource/aboutHover.png")));
		helpButton.setPressedIcon(new ImageIcon(getClass().getResource("resource/aboutPressed.png")));
		helpButton.setContentAreaFilled(false);
		gbc.gridx = 1;
		gbc.gridy = 3;
		this.add(helpButton, gbc);
		buttons.add(helpButton);
		
	}	
	
	private void validateToMainFrame(JFrame frame) {
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 3;
		gbc.gridy = 2;
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		
		frame.getContentPane().add(this, gbc);
		frame.validate();
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
