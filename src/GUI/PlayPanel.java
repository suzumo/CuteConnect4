package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;


public class PlayPanel extends JPanel {
	private ArrayList<JButton> buttons;
	private ArrayList<JCheckBox> checkBoxes;
	private ArrayList<JRadioButton> radioButtons;
	private ButtonGroup diffGroup;
	
	
	public PlayPanel(JFrame mainFrame){
		buttons = new ArrayList<JButton>();
		checkBoxes = new ArrayList<JCheckBox>();
		radioButtons = new ArrayList<JRadioButton>();
		
		//creating a gridbaglayout
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; //11
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		this.setBackground(Color.BLACK);
		
		initialize();
		validateToMainFrame(mainFrame);
	}

	private void initialize() {
		GridBagConstraints gbc = new GridBagConstraints();
		JButton button;
		JCheckBox checkBox;
		JLabel label;
		JRadioButton radioButton;
		
		diffGroup = new ButtonGroup();	//creating radio button group
		
		//select label
		label = new JLabel(new ImageIcon(this.getClass().getResource("resource/selectLabel.png")));
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(label, gbc);
		
		//difficulty label
		label = new JLabel(new ImageIcon(this.getClass().getResource("resource/difficultyLabel.png")));
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(label, gbc);

		//easy radio button
		radioButton = new JRadioButton("Easy");
		radioButton.setFont(new Font("Luciada Grande", Font.PLAIN, 25));
		radioButton.setForeground(Color.WHITE);
		radioButton.setActionCommand("Easy");
		gbc.gridx = 0;
		gbc.gridy = 3;
		diffGroup.add(radioButton);	//adding to button group
		add(radioButton, gbc);
		radioButtons.add(radioButton);
		
		//medium radio button
		radioButton = new JRadioButton("Medium");
		radioButton.setFont(new Font("Luciada Grande", Font.PLAIN, 25));
		radioButton.setForeground(Color.WHITE);
		radioButton.setActionCommand("Medium");
		gbc.gridx = 0;
		gbc.gridy = 5;
		diffGroup.add(radioButton);	//adding to button group
		add(radioButton, gbc);
		radioButtons.add(radioButton);
		
		//hard radio button
		radioButton = new JRadioButton("Hard");
		radioButton.setFont(new Font("Luciada Grande", Font.PLAIN, 25));
		radioButton.setForeground(Color.WHITE);
		radioButton.setActionCommand("Hard");
		gbc.gridx = 0;
		gbc.gridy = 7;
		diffGroup.add(radioButton);	//adding to button group
		add(radioButton, gbc);
		radioButtons.add(radioButton);

		//extreme radio button
		radioButton = new JRadioButton("Extreme");
		radioButton.setFont(new Font("Luciada Grande", Font.PLAIN, 25));
		radioButton.setForeground(Color.WHITE);
		radioButton.setActionCommand("Extreme");
		gbc.gridx = 0;
		gbc.gridy = 9;
		diffGroup.add(radioButton);	//adding to button group
		add(radioButton, gbc);
		radioButtons.add(radioButton);
		
		//game label
		label = new JLabel(new ImageIcon(this.getClass().getResource("resource/gameLabel.png")));
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.gridx = 15;
		gbc.gridy = 0;
		add(label, gbc);
		
		//options label
		label = new JLabel(new ImageIcon(this.getClass().getResource("resource/optionsLabel.png")));
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.gridx = 15;
		gbc.gridy = 1;
		add(label, gbc);
		
		//enable monsters checkbox
		checkBox = new JCheckBox("Enable Monsters");
		checkBox.setFont(new Font("Luciada Grande", Font.PLAIN, 25));
		checkBox.setForeground(Color.WHITE);
		checkBox.setActionCommand("EnableMonsters");
		gbc.gridx = 15;
		gbc.gridy = 3;
		add(checkBox,gbc);
		checkBoxes.add(checkBox);
		
		//enable teleporters checkbox
		checkBox = new JCheckBox("Enable Teleporters");
		checkBox.setFont(new Font("Luciada Grande", Font.PLAIN, 25));
		checkBox.setForeground(Color.WHITE);
		checkBox.setActionCommand("EnableTeleporters");
		gbc.gridx = 15;
		gbc.gridy = 5;
		add(checkBox,gbc);
		checkBoxes.add(checkBox);
		
		//begin button
		button = new JButton(new ImageIcon(getClass().getResource("resource/beginButton.png")));
		button.setActionCommand("Begin");
		button.setSelectedIcon(new ImageIcon(getClass().getResource("resource/beginHover.png")));
		button.setPressedIcon(new ImageIcon(getClass().getResource("resource/beginPressed.png")));
		button.setContentAreaFilled(false);
		gbc.gridx = 15;
		gbc.gridy = 25;
		gbc.ipadx = 8;
		gbc.ipady = 10;
		add(button,gbc);
		buttons.add(button);
	}
	
	private void validateToMainFrame(JFrame frame) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 3;
		gbc.gridy = 2;
		frame.getContentPane().add(this, gbc);
		frame.validate();	
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
	
	/**
	 * Getting the list of checkboxes in this panel
	 * 
	 * @return checkbBoxes
	 */
	public ArrayList<JCheckBox> getCheckBox() {
		return checkBoxes;
	}
	
	/**
	 * Getting the list of radio buttons in this panel
	 * 
	 * @return radioButtons
	 */
	public ArrayList<JRadioButton> getRadioButtons() {
		return radioButtons;
	}
}
