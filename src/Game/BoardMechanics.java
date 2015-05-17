package Game;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import GUI.ConnectFourListener;
import GUI.GamePanel;
import GUI.LeftPanel;
import GUI.MainFrame;
import GUI.SidePanel;
import Game.ConnectFourGame;


public class BoardMechanics implements ActionListener, KeyListener{
	private ConnectFourGame c4Game;
	private MainFrame mainFrame;
	private GamePanel gamePanel;
	private SidePanel rightPanel;
	private LeftPanel leftPanel;
	private ConnectFourListener listener;
	
	//players
	private int current_player;	// 1 for player 1, 2 for player 2, etc
	private int players; // 2 minimum
	
	private int winning_player;
	
	private int moves_made;
	
	private HashMap<Integer, Boolean> cpu_players;	// player -> isAI?
	private int ai_difficulty;
	AI ai;
	
	private ArrayList<ArrayList<Cell>> board;
	private int curr_row;
	 
	
	
	/*
	 * 0	game over
	 * 1	in play
	 */
	private int state;	
	
	
	/**
	 * @param connectFourGame
	 * @param mFrame
	 * @param diff
	 * @param cpu_players
	 * @param players number of players in this game
	 */

	public BoardMechanics(ConnectFourGame connectFourGame, MainFrame mFrame, int diff, HashMap<Integer, Boolean> cpu_players, int players) {

		
		//Initializing board
		initialise();
		
		this.mainFrame = mFrame;
		this.c4Game = connectFourGame;
		
		this.players = players;	
		
		// AI setting up
		this.cpu_players = cpu_players;
		ai_difficulty = diff;
		ai = new AI(diff);

		gamePanel = new GamePanel(mainFrame);
		leftPanel = new LeftPanel(mainFrame);
		rightPanel = new SidePanel(mainFrame);
		listener = new ConnectFourListener(this, this.gamePanel);
		for(JButton button : gamePanel.getButtons()){
			button.addActionListener(this);
		}
		for (JButton button : rightPanel.getButtons()) {
			button.addActionListener(this);
		}
		
	}
	
	private void initialise(){
		board = new ArrayList<ArrayList<Cell>>();
		
		//Populating the board with cells
		for(int row = 0; row < 6; row++){
			board.add(new ArrayList<Cell>());
			for(int col = 0; col < 7;col++){
				board.get(row).add(new Cell(row,col,0));
			}
			
			
		}
		//this.print();
		
		current_player = 1;
		winning_player = 0;
		
		moves_made = 0;
		
		curr_row = 0;
		
		state = 1;
		
	}
	
	/**
	 * Drops in a token into the board
	 * @param col 		column to insert token
	 * @param player	owner of token -> can be replaced by turn or whatever
	 */
	public int dropToken(int col) {
		int dRow = -1;
		if(checkMoveValid(col)){
			for(int i = 5; i >=0; i--){
				if(board.get(i).get(col).getValue() == 0){
					board.get(i).get(col).setValue(current_player);
					curr_row = i;
					break;
				}
			}
			nextPlayer();
			moves_made++;
			
			return curr_row;
		}
		return dRow;
	}
	
	/**
	 * 
	 */
	private void nextPlayer() {
		current_player++;
		if (current_player > players)
			current_player = 1;
	}
	
	private int getPreviousPlayer(){
		int p = current_player-1;
		if (p == 0)
			return players;
		return p;
	}

	public int aiDropToken(){
		int row = this.ai.makeAIMove(this);
		return row;
	}
	public void doAIMove(){
		
		int col = aiDropToken();
		int row = dropToken(col);
		System.out.println("col: "+ col +" Row "+ row + " current player: " + current_player);
		if (row != -1)
			gamePanel.set(col, row, current_player);
	}
	
	public boolean isPlayerAI(int p){
		if (cpu_players.get(p) != null && cpu_players.get(p) == true)
			return true;
		return false;
	}
	
	/**
	 * Calculates the current player with turns
	 */
	public int getCurrentPlayer() {
		return current_player;
	}
	
	public void print() {
		for (int row = 0; row < 6; row++) {
		    for (int col = 0; col < 7; col++) {
		    	System.out.print(board.get(row).get(col).getValue() + " ");
		    }
		    System.out.println();
		}
		System.out.println();
	}
	
	public boolean isCPU(){
		boolean isCPU = false;
		if(this.ai_difficulty != -1){
			isCPU = true;
		}
		return isCPU;
	}

	/**
	 * Checks if the current move attempt is valid 
	 * @param col      : The column that the player or computer is attempting to drop a token in
	 * @return valid   : If the move is valid or not
	 */
	public boolean checkMoveValid(int col){
		boolean valid = true;
		
		if(col > 6 || col < 0){
			valid = false;
		} else {
			if(board.get(0).get(col).getValue() != 0){
				System.out.println("Full"); //Need to put a label after adding side panels to indicate that the column is full
				valid = false;
			}
		}
		
		
		return valid;
	}
	
	/***
	 * Checks for win at each coin drop at column, col.
	 * @pre			turn is greater than 6.
	 * @param col	column number
	 * @return		0 if there is no win, player number (i.e. 1, 2...) if there is a win.
	 */
	public boolean checkForWin(){
		
		if (moves_made < 7) return false;		
		
		// check for a horizontal win 
	    for (int row =0; row<6; row++) { 
	    	for (int column=0; column<4; column++) { 
	    		if (board.get(row).get(column).getValue()!= 0 && board.get(row).get(column).getValue() == board.get(row).get(column+1).getValue() && board.get(row).get(column).getValue() == board.get(row).get(column+2).getValue() && board.get(row).get(column).getValue() == board.get(row).get(column+3).getValue()) { 
	    		
		        	  winning_player = board.get(row).get(column).getValue();

	    			return true; 
	            }        
	    	}      
	    }
		  // check for a vertical win 
	    for (int row=0; row<3; row++) { 
	       for (int column=0; column<7; column++) { 
	          if (board.get(row).get(column).getValue() != 0 && 
	        	 board.get(row).get(column).getValue() == board.get(row+1).get(column).getValue() && 
	        	 board.get(row).get(column).getValue() == board.get(row+2).get(column).getValue() && 
	        	 board.get(row).get(column).getValue() == board.get(row+3).get(column).getValue()) { 
	        	 
	        	  winning_player = board.get(row).get(column).getValue();
	        	  return true; 
	          }  
	       }       
	    }    
		     
	    // check for a diagonal win (positive slope) 
	    for (int row=0; row<3; row++) { 
	    	for (int column=0; column<4; column++) { 
	    		if (board.get(row).get(column).getValue() != 0 && 
    				board.get(row).get(column).getValue() == board.get(row+1).get(column+1).getValue() && 
					board.get(row).get(column).getValue() == board.get(row+2).get(column+2).getValue() && 
					board.get(row).get(column).getValue() == board.get(row+3).get(column+3).getValue()) { 
		           
	    			winning_player = board.get(row).get(column).getValue();
	    			return true; 
		        }        
	    	}      
		}    
		     
	    // check for a diagonal win (negative slope) 
	    for (int row=3; row<6; row++) { 
	      for (int column=0; column<4; column++) { 
	    	  if (board.get(row).get(column).getValue() != 0 && 
				  board.get(row).get(column).getValue() == board.get(row-1).get(column+1).getValue() && 
				  board.get(row).get(column).getValue() == board.get(row-2).get(column+2).getValue() && 
				  board.get(row).get(column).getValue() == board.get(row-3).get(column+3).getValue()) { 

	    		  winning_player = board.get(row).get(column).getValue();

	    		  return true; 
		      }        
	      }      
	    }    
	    
	    return false; 
	}  
		
	
	public void win(int player) {
		
		state = 0;
		int playAgain = JOptionPane.showConfirmDialog(gamePanel,"Player " + winning_player + " Won!!!\n" + "Would you like to play again?","Winner",JOptionPane.YES_NO_OPTION);
		if(playAgain == 0){		//yes
			restart();
		} else if(playAgain == 1){		//no
			c4Game.viewMenuPanel(mainFrame);
			gamePanel.setVisible(false);
			rightPanel.setVisible(false);
			leftPanel.setVisible(false);
		}
	}
	
	/**
	 * restarting the entire game
	 * 
	 */
	public void restart(){
		initialise();
		
		gamePanel.setVisible(true);
		gamePanel.setFocusable(true);
		gamePanel.requestFocusInWindow();
		gamePanel.restart(mainFrame);
		listener = new ConnectFourListener(this, gamePanel);
//		new BoardMechanics(c4Game, mainFrame);
	}
	
	
	/**
	 * TODO: Need to look over this
	 */
	public void keyPressed(KeyEvent e) {
		System.out.println("Press");
		int k = e.getKeyCode() - 48;
		boolean win = false;
		// numbers one to seven
		if (k >= 0 && k < 8 ) {
			if(checkMoveValid(k)){
				int row = dropToken(k);  
//			    print();
			    if(row != -1){
		        	gamePanel.set(k, row, getCurrentPlayer());
			    }
			    win = checkForWin();
//				System.out.println("Player " + win + " Wins!");
//			    win = true;
			    if(win == true){
			    	win(getCurrentPlayer());
			    }
			}
		}
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// 
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// 
		
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
					
		if (event.getActionCommand().equals("Quit")) {
			//check to make sure user really wants to quit
			int quit = JOptionPane.showConfirmDialog(mainFrame,
					"You want to quit this game and return to title screen?",
					"Quit Message", JOptionPane.YES_NO_OPTION);
			if(quit == 0) { //yes
				c4Game.viewMenuPanel(mainFrame);
				gamePanel.setVisible(false);
				rightPanel.setVisible(false);
				leftPanel.setVisible(false);
			}
		} else if (event.getActionCommand().equals("Help")) { 
			// CREATE HELP SCREEN
		} else if (event.getActionCommand().equals("Difficulty")) {
			// CREATE ADJUST DIFFICULTY SCREEN? POP UP?
		} else if (event.getActionCommand().equals("Sound")) {
			// CREATE TOGGLE SOUND
		} else {
			update();
		}
		
//		int row = -1;
//		int col = -1;
//		boolean win = false;
//		if(event.getActionCommand().equalsIgnoreCase("Drop")){
//			System.out.println("AI");
//			if(isCPU() && getCurrentPlayer() == 2){
//	    		col = aiDropToken();
//	    		row = dropToken(col);
//	    		System.out.println("move "+ col +" Row "+ row);
//	    		if(row != -1){
//	    			gamePanel.set(col, row, getCurrentPlayer());
//	    		}
//	    		win = checkForWin();
//			    if(win == true){
//				    win(getCurrentPlayer());
//			    }
//			}
//		}
	}

	public boolean isInPlay() {
		if (state == 1)
			return true;
		return false;
	}
	
	/**
	 * regular checks and updates to game
	 * 
	 * @return
	 */
	public void update() {
		
		System.out.println("update running.....");
		rightPanel.updateTurnDisplay(getCurrentPlayer());
		//check if need to do player move
		if ( isPlayerAI(getCurrentPlayer()) ) {
			doAIMove();
			rightPanel.updateTurnDisplay(getCurrentPlayer());
		}
		
		//win checking
	    if(checkForWin())
		    win(getCurrentPlayer());		
	}
}
