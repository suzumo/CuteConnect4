package Game;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import Audio.Music;
import Audio.Sounds;
import GUI.GamePanel;
import GUI.HelpPanel;
import GUI.MenuPanel;
import GUI.PlayPanel;
import GUI.MainFrame;
import GUI.DifficultyPanel;
import GUI.SidePanel;


public class ConnectFourGame extends JFrame implements ActionListener{
	private MainFrame mainFrame;
	private GamePanel gamePanel;
	private JDialog helpDialog;
	private MenuPanel menuPanel;
	private PlayPanel playPanel;
	private DifficultyPanel diffPanel;
	private SidePanel rightPanel;
	private BoardMechanics boardMechanics;
	private Music music;
	private Sounds sound;
	private int music_on;
	
	public ConnectFourGame() {
		//start music
		mainFrame = new MainFrame();	//creating new main JFrame
		viewMenuPanel(mainFrame);
		//set up music
		music = new Music();
		sound = new Sounds();
		music.playTrack();
		music_on = 1; //if music on = 1, otherwise 0
	}
	
	/**
	 * Creating the menu panel and adding it to the JFrame
	 * 
	 * @param mainFrame		JFrame of the game
	 */
	public void viewMenuPanel(JFrame mainFrame)
	{
		if(menuPanel == null)
		{
			menuPanel = new MenuPanel(mainFrame);
			//adding actions listeners to the buttons within the menu panel
			for (JButton button : menuPanel.getButtons())
				button.addActionListener(this);
		}
		//centralise panel in screen
		int screen_width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int screen_height = (int)(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		int x = 0, y = 0;
		if (screen_height > 750) {
			y = (screen_height - 750)/2;
		}
		if (screen_width > 465) {
			x = (screen_width - 465)/2;
		}
		mainFrame.setBounds(x, y, 465, 750);
		menuPanel.setVisible(true);
	}
	
	public void viewPlayPanel(JFrame mainFrame){
		if(playPanel == null)
		{
			playPanel = new PlayPanel(mainFrame, music_on);
			//adding action listeners to buttons
			for(JButton button : playPanel.getButtons()){
				button.addActionListener(this);
			}
		}
		showPlayPanel();
	}
	
	public void viewHelpDialog() {
		helpDialog = new JDialog();
		HelpPanel helpPanel = new HelpPanel(mainFrame);
		helpDialog = new JDialog();
		helpDialog.setContentPane(helpPanel);
		helpDialog.pack();
		//put panel to the right of main panel
		int x = 0;
		int top_x = (int)mainFrame.getLocationOnScreen().getX();
		if (mainFrame.getWidth() > 465)
			x = top_x;
		else
			x = top_x + mainFrame.getWidth() + 5;
		int top_y = (int)mainFrame.getLocationOnScreen().getY();
		helpDialog.setBounds(x, top_y, 450, 750);
		helpDialog.setVisible(true);
	}
	
	public void hideHelpDialog() {
		helpDialog.dispose();
		helpDialog = null;
	} 
	
	public JDialog getHelpDialogStatus() {
		return helpDialog;
	}
	
	public void viewDiffPanel(JFrame mainFrame){
		if(diffPanel == null)
		{
			diffPanel = new DifficultyPanel(mainFrame);
			//adding action listeners to buttons
			for(JButton button : diffPanel.getButtons()){
				button.addActionListener(this);
			}
		}
		//centralise panel in screen
		int screen_width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int screen_height = (int)(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		int x = 0, y = 0;
		if (screen_height > 750) {
			y = (screen_height - 750)/2;
		}
		if (screen_width > 465) {
			x = (screen_width - 465)/2;
		}
		mainFrame.setBounds(x, y, 465, 750);
		showDiffPanel();
	}

	public void viewGamePanel(MainFrame mainFrame, int diff, boolean isMonoChrome){
		//centralise frame in screen
		int screen_width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int screen_height = (int)(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		int x = 0, y = 0;
		if (screen_height > 750) {
			y = (screen_height - 750)/2;
		}
		if (screen_width > 1200) {
			x = (screen_width - 1200)/2;
		}
		mainFrame.setBounds(x, y, 1200, 750);
		HashMap<Integer, Boolean> cpus = new HashMap<Integer, Boolean>();
		cpus.put(2, true);
		boardMechanics = new BoardMechanics(this, mainFrame, diff, cpus, 2, isMonoChrome);
	}
	
	public void startMusic() {
		if (music_on == 0) {
			music.playTrack();
			music_on = 1;
			menuPanel.setSoundOnButton();
			if (playPanel != null)
				playPanel.setSoundOnButton();
			if (rightPanel != null)
				rightPanel.setSoundOnButton();
		}
	}
	
	public void stopMusic() {
		if (music_on == 1) {
			music.stop();
			music_on = 0;
			menuPanel.setSoundOffButton();
			if (playPanel != null)
				playPanel.setSoundOffButton();
			if (rightPanel != null)
				rightPanel.setSoundOffButton();
		}
	}
	
	public int getMusicStatus() {
		return music_on;
	}
	
	/**
	 * Enable the play panel
	 * 
	 */
	public void	showPlayPanel() 
	{
		//centralise panel in screen
		int screen_width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int screen_height = (int)(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		int x = 0, y = 0;
		if (screen_height > 750) {
			y = (screen_height - 750)/2;
		}
		if (screen_width > 465) {
			x = (screen_width - 465)/2;
		}
		mainFrame.setBounds(x, y, 465, 750);
		playPanel.setVisible(true);
	}
	
	
	/**
	 *Hide the play panel
	 * 
	 */
	public void hidePlayPanel()
	{
		playPanel.setVisible(false);
	}
	
	public void showGamePanel(){
		gamePanel.setVisible(true);
	}
	
	public void hideGamePanel(){
		gamePanel.setVisible(false);
	}
	
	public void showDiffPanel(){
		diffPanel.setVisible(true);
	}
	
	public void hideDiffPanel(){
		diffPanel.setVisible(false);
	}
	
	public void actionPerformed(ActionEvent event) {
		if(event.getActionCommand().equalsIgnoreCase("Start")){
			menuPanel.setVisible(false);
			viewPlayPanel(mainFrame);
			if (diffPanel != null)
				hideDiffPanel();
		} else if (event.getActionCommand().equalsIgnoreCase("TitleScreen")){
			if (playPanel != null)
				hidePlayPanel();
			if (gamePanel != null)
				hideGamePanel();
			if (diffPanel != null)
				hideDiffPanel();
			viewMenuPanel(mainFrame);
		} else if(event.getActionCommand().equalsIgnoreCase("PvP")){
			hidePlayPanel();
			viewGamePanel(mainFrame, -1, false);
		} else if(event.getActionCommand().equalsIgnoreCase("PvAI")){
			hidePlayPanel();
			viewDiffPanel(mainFrame);
		} else if(event.getActionCommand().equalsIgnoreCase("Easy")){
			hideDiffPanel();
			viewGamePanel(mainFrame, 1, false);
		} else if(event.getActionCommand().equalsIgnoreCase("Normal")){
			hideDiffPanel();
			viewGamePanel(mainFrame, 2, false);
		} else if(event.getActionCommand().equalsIgnoreCase("Monochrome")){
			hideDiffPanel();
			viewGamePanel(mainFrame, 2, true);
		} else if (event.getActionCommand().equalsIgnoreCase("Sound Off")) {
			stopMusic();
			if (menuPanel != null)
				menuPanel.setSoundOffButton();
			if (playPanel != null)
				playPanel.setSoundOffButton();
		} else if (event.getActionCommand().equalsIgnoreCase("Sound On")) {
			startMusic();
			if (menuPanel != null)
				menuPanel.setSoundOnButton();
			if (playPanel != null)
				playPanel.setSoundOnButton();
		} else if (event.getActionCommand().equalsIgnoreCase("Help")) {
			if (helpDialog != null) {
				hideHelpDialog();
			} else {
				viewHelpDialog();
			}
		} else if(event.getActionCommand().equalsIgnoreCase("Quit")) {
			//when quit button is pressed
			int quit = JOptionPane.showConfirmDialog(mainFrame,"Are you sure you want to quit?",
						"Quit Message",JOptionPane.YES_NO_OPTION);

			//yes, user really wants to quit
			if(quit == 0){		
				System.exit(0);
			}
		}
	}
	
}
