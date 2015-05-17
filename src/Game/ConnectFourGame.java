package Game;

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


public class ConnectFourGame extends JFrame implements ActionListener{
	private GamePanel gamePanel;
	private HelpPanel helpPanel;
	private MenuPanel menuPanel;
	private MainFrame mainFrame;
	private PlayPanel playPanel;
	private DifficultyPanel diffPanel;
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
			for(JButton button : menuPanel.getButtons()){
				button.addActionListener(this);
			}
		} else {
			menuPanel.setVisible(true);		//showing the menu panel
		}
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
		else
			showPlayPanel();		//show play panel
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
		else
			showDiffPanel();		//show play panel
	}

	public void viewGamePanel(MainFrame mainFrame, int diff){
		HashMap<Integer, Boolean> cpus = new HashMap<Integer, Boolean>();
		cpus.put(2, true);
		boardMechanics = new BoardMechanics(this, mainFrame, diff, cpus, 2);
	}
	
	/**
	 * Enable the play panel
	 * 
	 */
	public void	showPlayPanel() 
	{
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
			//viewGamePanel(mainFrame);
		} else if(event.getActionCommand().equalsIgnoreCase("PvP")){
			hidePlayPanel();
			viewGamePanel(mainFrame, -1);
		} else if(event.getActionCommand().equalsIgnoreCase("PvAI")){
			hidePlayPanel();
			viewDiffPanel(mainFrame);
		} else if(event.getActionCommand().equalsIgnoreCase("Easy")){
			hideDiffPanel();
			viewGamePanel(mainFrame, 0);
		} else if(event.getActionCommand().equalsIgnoreCase("Quit")) {				//when quit button is pressed
			int quit = JOptionPane.showConfirmDialog(mainFrame,"Are you sure you want to quit?","Quit Message",JOptionPane.YES_NO_OPTION);		//check to make sure user really wants to quit
			
			if(quit == 0){		//yes
				System.exit(0);
			}
		}
	} 

}
