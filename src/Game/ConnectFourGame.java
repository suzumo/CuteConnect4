package Game;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Audio.Music;
import GUI.GamePanel;
import GUI.HelpPanel;
import GUI.MenuPanel;
import GUI.PlayPanel;
import GUI.MainFrame;
import GUI.DifficultyPanel;
import GUI.SidePanel;


public class ConnectFourGame extends JFrame implements ActionListener, MouseListener{
	
	/***
	 * @field mainFrame		Main frame of the game.
	 * @field gamePanel		Connect4 board panel.
	 * @field helpDialog	Pop up help panel.
	 * @field menuPanel		Title screen panel.
	 * @field playPanel		Game options panel.
	 * @field diffPanel		Difficulty choice panel.
	 * @field rightPanel	Connect4 board main options panel.
	 * @field boardMechanics Controls Connect4 board mechanisms.
	 * @field music			Game music.
	 * @field music_on		Flag for controlling game music on/off across different panels. 
	 */
	private MainFrame mainFrame;
	private GamePanel gamePanel;
	private JDialog helpDialog;
	private MenuPanel menuPanel;
	private PlayPanel playPanel;
	private DifficultyPanel diffPanel;
	private SidePanel rightPanel;
	private BoardMechanics boardMechanics;
	private Music music;
	private int music_on;
	
	/***
	 * Constructor for ConnectFourGame.
	 * @pre		None.
	 * @post	Creates a ConnectFourGame instance.
	 */
	public ConnectFourGame() {
		mainFrame = new MainFrame();	//creating new main JFrame
		viewMenuPanel(mainFrame);
		music = new Music();		//set up music
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
			mainFrame.setSize(465, 750);
			menuPanel = new MenuPanel(mainFrame);
			//adding actions listeners to the buttons within the menu panel
			for (JButton button : menuPanel.getButtons())
				button.addActionListener(this);
			//centralise panel in screen if opened for first time//this is title screen
			int screen_width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
			int screen_height = (int)(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
			int x = 0, y = 0;
			if (screen_height > 750)
				y = (screen_height - 750)/2;
			if (screen_width > 465)
				x = (screen_width - 465)/2;
			mainFrame.setLocation(x,y);
		} else {
			mainFrame.setSize(465, 750);
			menuPanel.setVisible(true);
		}
	}
	
	public void viewPlayPanel(JFrame mainFrame){
		if(playPanel == null) {
			playPanel = new PlayPanel(mainFrame, music_on);
			//adding action listeners to buttons
			for(JButton button : playPanel.getButtons()){
				button.addActionListener(this);
			}
		}
		showPlayPanel();
	}

	/**
	 * Generates a Help Panel.
	 * @pre		None.
	 * @post	Generates or turns visibility of Help Panel on.
	 */
	public void viewHelpDialog() {
		helpDialog = new JDialog();
		helpDialog.setTitle("How to Play Connect4");
		HelpPanel helpPanel = new HelpPanel(mainFrame);
		helpPanel.addMouseListener(this);
		helpDialog = new JDialog();
		helpDialog.setContentPane(helpPanel);
		helpDialog.pack();
		positionHelpDialog();
		helpDialog.setVisible(true);
	}
	
	/**
	 * Positions Help Panel dynamically to the Main game panel.
	 * @pre		None.
	 * @post	Positions help panel that it will appear to the right, left or on top of game panel depending on game panel location.
	 */
	public void positionHelpDialog() {
		//put panel to the right of main panel
		helpDialog.setSize(450,750);
		int x = 0;
		int top_x = (int)mainFrame.getLocationOnScreen().getX();
		int screen_width = Toolkit.getDefaultToolkit().getScreenSize().width;
		// if there's enough space to the right, put it at right of mainframe
		if ((screen_width - top_x - mainFrame.getWidth()) >= 455)
			x = top_x + mainFrame.getWidth() + 5;
		else if (top_x > 455)
			x = top_x - 455; // otherwise put it to left
		else 
			x = top_x; // else just put it overlapping
		int top_y = (int)mainFrame.getLocationOnScreen().getY();
		helpDialog.setLocation(x, top_y);
	}
	
	/**
	 * Destroys Help panel.
	 * @pre		Help panel must exist.
	 * @post	Help panel is destroyed.
	 */
	public void hideHelpDialog() {
		helpDialog.dispose();
		helpDialog = null;
	} 
	
	/**
	 * Returns game help panel status.
	 * @pre		None.
	 * @return	Returns true if help panel object exists, false otherwise.
	 */
	public boolean helpDialogOn() {
		return (helpDialog != null);
	}
	
	/***
	 * Returns state of helpDialog; true if visible, false otherwise.
	 * @pre		Help panel must be exist.
	 * @return	True if help panel is visible, false otherwise.
	 */
	public boolean isHelpDialogVisible() {
		return helpDialog.isVisible();
	}

	/***
	 * Turns on/off visibility of helpDialog.
	 * @pre		Help panel must exist.
	 * @param b	True if help panel is to be setVisible, false otherwise.
	 * @post	Turns visibility of Help panel on or off.
	 */
	public void setHelpDialogVisible(boolean b) {
		helpDialog.setVisible(b);
	}
	
	/**
	 * Turns visibility of Difficulty panel on or creates it if it doesn't exist.
	 * @pre		None.
	 * @param mainFrame	JFrame in which this panel is component of.
	 * @post	Turns visibility of Difficulty panel on, and turns off everything else.
	 */
	public void viewDiffPanel(JFrame mainFrame){
		if(diffPanel == null)
		{
			diffPanel = new DifficultyPanel(mainFrame);
			//adding action listeners to buttons
			for(JButton button : diffPanel.getButtons()){
				button.addActionListener(this);
			}
		}
		mainFrame.setSize(465, 750);
		showDiffPanel();
	}

	/**
	 * Creates Game panel with specific options.
	 * @pre		None.
	 * @param mainFrame	JFrame in which this panel is component of.
	 * @param diff		The level of game difficulty; 1 = easy, 2 = normal, 3 = hard.
	 * @param isMonoChrome	True if it is a Monochrome game, false otherwise.
	 * @post	Creates a Game panel, and turns visibility of everything else off.
	 */
	public void viewGamePanel(MainFrame mainFrame, int diff, boolean isMonoChrome){
		mainFrame.setSize(1200, 750);
		//centralise frame in screen
		int screen_width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int screen_height = (int)(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		int top_x = (int)mainFrame.getLocationOnScreen().getX();
		int top_y = (int)mainFrame.getLocationOnScreen().getY();
		int x = 0, y = 0;
		//re-adjust big screen positioning that if it sticks out of screen
		//then re-adjust to center of screen, otherwise leave the position as is
		if (screen_height > 750) {
			if (top_y + 750 > screen_height)
				y = (screen_height - 750)/2;
			else
				y = top_y;
		}
		if (screen_width > 1200) {
			if (top_x + 1200 > screen_width)
				x = (screen_width - 1200)/2;
			else
				x = top_x;
		}
		mainFrame.setBounds(x, y, 1200, 750);
		HashMap<Integer, Boolean> cpus = new HashMap<Integer, Boolean>();
		cpus.put(2, true);
		boardMechanics = new BoardMechanics(this, mainFrame, diff, cpus, 2, isMonoChrome);
	}
	
	/**
	 * Starts game music if it was on.
	 * @pre		Game music was turned on.
	 * @post	Game music turns off.
	 */
	public void startMusic() {
		if (music_on == 0) {
			music.playTrack();
			music_on = 1;
			if (menuPanel != null)
				menuPanel.setSoundOnButton();
			if (playPanel != null)
				playPanel.setSoundOnButton();
			if (rightPanel != null)
				rightPanel.setSoundOnButton();
		}
	}
	
	/**
	 * Turns off game music if it was on.
	 * @pre		Game music was on.
	 * @post	Game music turns off.
	 */
	public void stopMusic() {
		if (music_on == 1) {
			music.stop();
			music_on = 0;
			if (menuPanel != null)
				menuPanel.setSoundOffButton();
			if (playPanel != null)
				playPanel.setSoundOffButton();
			if (rightPanel != null)
				rightPanel.setSoundOffButton();
		}
	}
	
	/**
	 * Returns game music status; 0 = music off, 1 = music on.
	 * @pre		None.
	 * @return	0 = music off, 1 = music on.
	 */
	public int getMusicStatus() {
		return music_on;
	}
	
	/**
	 * Enable the play panel.
	 * @pre		Play panel exists.
	 * @post	Play panel is visible.
	 */
	public void	showPlayPanel() {
		mainFrame.setSize(465, 750);
		playPanel.setVisible(true);
	}
	
	
	/**
	 *Hide the play panel.
	 * @pre		Play panel exists.
	 * @post	Play panel is not visible.
	 */
	public void hidePlayPanel() {
		playPanel.setVisible(false);
	}

	/**
	 * Enable the game panel.
	 * @pre		Game panel exists.
	 * @post	Game panel is visible.
	 */
	public void showGamePanel() {
		gamePanel.setVisible(true);
	}

	/**
	 * Hide the game panel.
	 * @pre		Game panel exists.
	 * @post	Game panel is not visible.
	 */
	public void hideGamePanel() {
		gamePanel.setVisible(false);
	}
	
	/**
	 * Enable the Difficulty panel.
	 * @pre		Difficulty panel exists.
	 * @post	Difficulty panel is visible.
	 */
	public void showDiffPanel() {
		diffPanel.setVisible(true);
	}
	
	/**
	 * Hide the Difficulty panel.
	 * @pre		Difficulty panel exists.
	 * @post	Difficulty panel is not visible.
	 */
	public void hideDiffPanel() {
		diffPanel.setVisible(false);
	}

	/**
	 * Action performed by game depending on user choice.
	 * @pre			User action required in game.
	 * @param event	User action.
	 * @post		Depending on user choice, the game will operate various things (self explanatory).
	 */
	public void actionPerformed(ActionEvent event) {
		if(event.getActionCommand().equalsIgnoreCase("Start")){
			menuPanel.setVisible(false);
			viewPlayPanel(mainFrame);
			if (diffPanel != null)
				hideDiffPanel();
			if (helpDialog != null)
				hideHelpDialog();
		} else if (event.getActionCommand().equalsIgnoreCase("TitleScreen")){
			if (playPanel != null)
				hidePlayPanel();
			if (gamePanel != null)
				hideGamePanel();
			if (diffPanel != null)
				hideDiffPanel();
			if (helpDialog != null)
				hideHelpDialog();
			viewMenuPanel(mainFrame);
		} else if(event.getActionCommand().equalsIgnoreCase("PvP")){
			hidePlayPanel();
			if (helpDialog != null)
				hideHelpDialog();
			viewGamePanel(mainFrame, -1, false);
		} else if(event.getActionCommand().equalsIgnoreCase("PvAI")){
			hidePlayPanel();
			if (helpDialog != null)
				hideHelpDialog();
			viewDiffPanel(mainFrame);
		} else if(event.getActionCommand().equalsIgnoreCase("Easy")){
			hideDiffPanel();
			viewGamePanel(mainFrame, 1, false);
		} else if(event.getActionCommand().equalsIgnoreCase("Normal")){
			hideDiffPanel();
			viewGamePanel(mainFrame, 2, false);
		} else if(event.getActionCommand().equalsIgnoreCase("Hard")){
			hideDiffPanel();
			viewGamePanel(mainFrame, 3, false);
		} else if(event.getActionCommand().equalsIgnoreCase("Monochrome")){
			hideDiffPanel();
			viewGamePanel(mainFrame, 2, true);
		} else if (event.getActionCommand().equalsIgnoreCase("Sound Off")) {
			stopMusic();
		} else if (event.getActionCommand().equalsIgnoreCase("Sound On")) {
			startMusic();
		} else if (event.getActionCommand().equalsIgnoreCase("Help")) {
			if (helpDialog != null) {
				if (!helpDialog.isVisible()) {
					positionHelpDialog();
					helpDialog.setVisible(true);
				} else
					hideHelpDialog();
			} else
				viewHelpDialog();
		} else if(event.getActionCommand().equalsIgnoreCase("Quit")) {
			int quit = JOptionPane.showConfirmDialog(mainFrame,"Are you sure you want to quit?",
						"Quit Message",JOptionPane.YES_NO_OPTION);
			if(quit == 0){		
				System.exit(0);
			}
		}
	}

	/**
	 * Used to enable Help Dialog panel close on mouse click.
	 * @pre		Mouse click on Help Dialog.
	 * @param arg0	Mouse event (mouse click).
	 * @post	Help Dialog closes if ouse click was inside Help Dialog panel.
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (helpDialog != null) {
			if (!helpDialog.isVisible()) {
				positionHelpDialog();
				helpDialog.setVisible(true);
			} else
				hideHelpDialog();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
	
}
