package Game;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

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
	private HelpPanel helpPanel;
	private MenuPanel menuPanel;
	private PlayPanel playPanel;
	private DifficultyPanel diffPanel;
	private SidePanel rightPanel;
	private BoardMechanics boardMechanics;

	
	public ConnectFourGame() {
		mainFrame = new MainFrame();	//creating new main JFrame	
		viewMenuPanel(mainFrame);	
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
		menuPanel.setVisible(true);		//showing the menu panel
	}
	
	public void viewPlayPanel(JFrame mainFrame){
		if(playPanel == null)
		{
			playPanel = new PlayPanel(mainFrame);
			//adding action listeners to buttons
			for(JButton button : playPanel.getButtons()){
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
		showPlayPanel();
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
			viewGamePanel(mainFrame, 0, false);
		} else if(event.getActionCommand().equalsIgnoreCase("Normal")){
			hideDiffPanel();
			viewGamePanel(mainFrame, 2, false);
		} else if(event.getActionCommand().equalsIgnoreCase("Monochrome")){
			hideDiffPanel();
			viewGamePanel(mainFrame, 0, true);
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
