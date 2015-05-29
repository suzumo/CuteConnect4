package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

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
	private ArrayList<ArrayList<Cell>> board;
	private ArrayList<Cell> winningTokens;
	private int state; // 0 for game over, 1 in play
	private boolean isMonoChrome;
	private int music_on;
	private javax.swing.Timer hint;
	private int curr_row;
	private int helpButtonPressedNumber;
	private int fullColumnPressedNumber;
	private int numHints; // number of hints left
	private int hintFlag; // 1 for current hint active, 0 otherwise
	private boolean isListenerActive;

	private int current_player;	// 1 for player 1, 2 for player 2, etc
	private int players; // 2 minimum
	private int winning_player;
	private int moves_made;
	private HashMap<Integer, Boolean> cpu_players;	// player -> isAI?
	private int ai_difficulty;
	private AI ai;
	
	/**
	 * Constructor.
	 * @param connectFourGame	ConnectFourGame JFrame object.
	 * @param mFrame			Main JFrame to set objects in.
	 * @param diff				AI difficulty level.
	 * @param cpu_players		Whether there are AI players.
	 * @param players 			Number of players in this game.
	 * @param isMChrome			True if game is in Monochrome, false otherwise.
	 */
	public BoardMechanics(ConnectFourGame connectFourGame, MainFrame mFrame, int diff, HashMap<Integer, Boolean> cpu_players, int players, boolean isMChrome) {

		//Initializing board
		initialise();
		
		this.mainFrame = mFrame;
		this.c4Game = connectFourGame;
		this.isMonoChrome = isMChrome;
		this.players = players;	
		
		//setting music status
		music_on = connectFourGame.getMusicStatus();
		
		// AI setting up
		this.cpu_players = cpu_players;
		ai_difficulty = diff;
		ai = new AI(diff);

		// set up number of hints depending on ai difficulty
		if (ai_difficulty == 1) //easy difficulty get 3 hints
			numHints = 3;
		else if (ai_difficulty == 2 && !isMonoChrome) // normal diff get 1 hint
			numHints = 1;
		else
			numHints = 0; // otherwise 0
		hintFlag = 0; // 0 for no hints activated, 1 for one active atm 
		
		//Activating the Mouse Listeners
		isListenerActive = true;
		
		gamePanel = new GamePanel(mainFrame);
		leftPanel = new LeftPanel(mainFrame);
		rightPanel = new SidePanel(mainFrame, music_on, numHints);
		listener = new ConnectFourListener(this, this.gamePanel);
		for(JButton button : gamePanel.getButtons()){
			button.addActionListener(this);
		}
		for (JButton button : rightPanel.getButtons()) {
			button.addActionListener(this);
		}
		
		helpButtonPressedNumber = 0;
		fullColumnPressedNumber = 0;
		rightPanel.updateTurnDisplay(getCurrentPlayer());
		if (current_player == 2 && isCPU()) doAIMove();
	}
	
	/**
	 * Initialise board state.
	 * @pre		None.
	 * @post	Board state is initialised to start.
	 */
	private void initialise(){
		board = new ArrayList<ArrayList<Cell>>();
		winningTokens = new ArrayList<Cell>();
		//Populating the board with cells
		for(int row = 0; row < 6; row++){
			board.add(new ArrayList<Cell>());
			for(int col = 0; col < 7;col++){
				board.get(row).add(new Cell(row,col,0));
			}
		}
		
		current_player = (int) Math.round(Math.random() +1);
		winning_player = 0;
		moves_made = 0;
		curr_row = 0;
		state = 1;
		fullColumnPressedNumber = 0;
		helpButtonPressedNumber = 0;
	}
	
	
	/**
	 * Drops in a token into the board.
	 * @pre			None.
	 * @param col 	Column to insert token
	 * @return		Puts in a coin for the current player in the board and returns the row number.
	 */
	public int dropToken(int col) {
		int dRow = -1;
		if(checkMoveValid(col)){
			for(int i = 5; i >=0; i--){
				if(board.get(i).get(col).getValue() == 0){
					board.get(i).get(col).setValue(current_player);
					//System.out.println("Dropping token player_num "+current_player +"#move: "+moves_made+ "("+i+","+col+")");
					curr_row = i;
					break;
				}
			}
			moves_made++;
			nextPlayer();
			helpButtonPressedNumber = 0;
			return curr_row;
		}
		return dRow;
	}
	
	/**
	 * Switch players.
	 * @pre		None.
	 * @post	Current player is switched to the other player.
	 */
	public void nextPlayer() {
		current_player++;
		if (current_player > players)
			current_player = 1;
	}
	
	/**
	 * Return the previous player number.
	 * @pre		None.
	 * @return	Returns previous player's number.
	 */
	private int getPreviousPlayer(){
		int p = current_player-1;
		if (p == 0)
			return players;
		return p;
	}

	/**
	 * Use currently set up AI to drop token.
	 * @pre		None.
	 * @return	Returns column number of AI move.
	 */
	public int aiDropToken(){
		int row = this.ai.makeAIMove(this);
		return row;
	}
	
	/**
	 * Use currently set up AI to do move.
	 * @pre		None.
	 * @post	AI fills a slot on board.
	 */
	public void doAIMove(){
		
		int col = aiDropToken();
		int row = dropToken(col);
		//System.out.println("AI col "+ col +" row "+ row + " next player: " + current_player);
		if (row != -1)
			gamePanel.set(col, row, current_player, checkMonoChrome());
		update();
	}
	
	/**
	 * Check if current player is AI.
	 * @pre		None.
	 * @param p Player number.
	 * @return	Returns true if current player is AI, false otherwise.
	 */
	public boolean isPlayerAI(int p){
		if (cpu_players.get(p) != null && cpu_players.get(p) == true)
			return true;
		return false;
	}
	
	/**
	 * Calculates the current player with turns.
	 * @pre		None.
	 * @return	Returns the current player number.
	 */
	public int getCurrentPlayer() {
		return current_player;
	}
	
//	/**
//	 * Prints current board state.
//	 * @pre		None.
//	 * @post	Prints the current board state.
//	 */
//	public void print() {
//		for (int row = 0; row < 6; row++) {
//		    for (int col = 0; col < 7; col++) {
//		    	System.out.print(board.get(row).get(col).getValue() + " ");
//		    }
//		    System.out.println();
//		}
//		System.out.println();
//	}
	
	/**
	 * Check if current game mode is Player versus Computer.
	 * @pre		None.
	 * @return	Returns true if it is PvE, false otherwise.
	 */
	public boolean isCPU(){
		boolean isCPU = false;
		if(this.ai_difficulty != -1){
			isCPU = true;
		}
		return isCPU;
	}

	/**
	 * Checks if the current move attempt is valid.
	 * @param col      The column that the player or computer is attempting to drop a token in.
	 * @return valid   True if current move is valid, false otherwise.
	 */
	public boolean checkMoveValid(int col){
		boolean valid = true;
		
		if(col > 6 || col < 0){
			valid = false;
		} else {
			
			if(board.get(0).get(col).getValue() != 0){
				
				//System.out.println("Full"); //Need to put a label after adding side panels to indicate that the column is full
				if(getCurrentPlayer() == 1){
					switch (fullColumnPressedNumber) {
					case 0: JOptionPane.showMessageDialog(gamePanel,"This Column is Full!\n"); break;
					case 3: JOptionPane.showMessageDialog(gamePanel,"Didn't you see the first message?\n" + "This Column is Full!!!\n"); break;
					case 6: JOptionPane.showMessageDialog(gamePanel,"Please stop. The column has no space for you\n"); break;
					case 10: JOptionPane.showMessageDialog(gamePanel,"PLEASE. STOP. YOU MONKEY.\n"); break;
					}
					fullColumnPressedNumber += 1;
				}
				valid = false;
			}
		}
		return valid;
	}
	
	/***
	 * Checks for win at each coin drop at column, col.
	 * @pre			turn is greater than 6.
	 * @param isCheck	Whether or not it has been currently checked for win; false for not yet.
	 * @return		0 if there is no win, player number (i.e. 1, 2...) if there is a win.
	 */
	public boolean checkForWin(boolean isCheck){
		
		if (winning_player == -1) return false;
		if (moves_made < 5) return false;		
		// check for a horizontal win 
	    for (int row =0; row<6; row++) { 
	    	for (int column=0; column<4; column++) { 
	    		if (board.get(row).get(column).getValue()!= 0 &&
	    			board.get(row).get(column).getValue() == board.get(row).get(column+1).getValue() && 
	    			board.get(row).get(column).getValue() == board.get(row).get(column+2).getValue() && 
	    			board.get(row).get(column).getValue() == board.get(row).get(column+3).getValue()) { 
	    			
	    			//adding winningTokens to arrayList so we can display them
	    			if(!isCheck){
		    			winningTokens.add(board.get(row).get(column));
		    			winningTokens.add(board.get(row).get(column+1));
		    			winningTokens.add(board.get(row).get(column+2));
		    			winningTokens.add(board.get(row).get(column+3));
	    			}
		    		
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
	        	 
	        	  if(!isCheck){
		        	  winningTokens.add(board.get(row).get(column));
		        	  winningTokens.add(board.get(row+1).get(column));
		        	  winningTokens.add(board.get(row+2).get(column));
		        	  winningTokens.add(board.get(row+3).get(column));
	        	  }
		        	  
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
		           
	    			if(!isCheck){
		    			winningTokens.add(board.get(row).get(column));
		    			winningTokens.add(board.get(row+1).get(column+1));
		    			winningTokens.add(board.get(row+2).get(column+2));
		    			winningTokens.add(board.get(row+3).get(column+3));
	    			}
	    			
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

	    		  if(!isCheck){
						winningTokens.add(board.get(row).get(column));
						winningTokens.add(board.get(row-1).get(column+1));
						winningTokens.add(board.get(row-2).get(column+2));
						winningTokens.add(board.get(row-3).get(column+3));
	    		  }
	    		  	winning_player = board.get(row).get(column).getValue();
	    		  	return true; 
		      }        
	      }      
	    }    
	    
	    return false; 
	}  
	
	
	/**
	 * Procedures to follow when game is won.
	 * @pre		Game is won by a player.
	 * @param player Player who won the game.
	 * @post	Finishes up the game when game is won; asks if player should be replayed.
	 */
	public void win(int player) {
		
		if (winning_player == 0 || state == 0) return;
		isListenerActive = false;
		state = 0;
		int playAgain = 1;
		ImageIcon icon = null;
		gamePanel.showWinningTokens(winningTokens);
		
		if(isCPU() == true){
			if (isMonoChrome) // if game was Monochrome, reveal board status upon ending game
				revealBoard();
			if (winning_player == 1) {
				icon = new ImageIcon(getClass().getResource("../GUI/resource/player1-won.png"));
				playAgain = JOptionPane.showConfirmDialog(gamePanel,"You Won!!!\n" 
						+ "Would you like to play again?",
						"WINNER", 0, JOptionPane.YES_NO_OPTION, icon);
			} else if (winning_player == 2){
				icon = new ImageIcon(getClass().getResource("../GUI/resource/player2-won.png"));
				playAgain = JOptionPane.showConfirmDialog(gamePanel,"The Computer Won!!!\n" 
						+ "Would you like to play again?",
						"WINNER", 0, JOptionPane.YES_NO_OPTION, icon);
			}
		} else {
			if (winning_player == 1) {
				icon = new ImageIcon(getClass().getResource("../GUI/resource/player1-won.png"));
			} else {
				icon = new ImageIcon(getClass().getResource("../GUI/resource/player2-won.png"));
			}
			playAgain = JOptionPane.showConfirmDialog(gamePanel,"Player " 
					+ winning_player + " Won!!!\n" 
					+ "Would you like to play again?",
					"WINNER", 0, JOptionPane.YES_NO_OPTION, icon);
		}

		if(playAgain == 0){		//yes
			isListenerActive = true;
			restart();
		} else if(playAgain == 1){		//no
			isListenerActive = true;
			c4Game.viewPlayPanel(mainFrame);
			gamePanel.setVisible(false);
			leftPanel.setVisible(false);
			rightPanel.setVisible(false);
		}
	}

	/**
	 * Reveals board status, coin colours (used for Monochrome game).
	 * @pre		Game must be in Monochrome difficulty.
	 * @post	Reveals coin colours across entire board.
	 */
	private void revealBoard() {
		for (int row = 0; row < 6; row++) {
		    for (int col = 0; col < 7; col++) {
		    	if (board.get(row).get(col).getValue() == 2)
		    		gamePanel.set(col, row, 1, false);
		    	else if (board.get(row).get(col).getValue() == 1)
		    		gamePanel.set(col, row, 2, false);
		    }
		}
	}
	
	/**
	 * Procedures to follow if the game is finished in a tie.
	 * @pre		Game is finished with a tie.
	 * @post	Finishes up the game when game is finished in a tie; asks if player should be replayed.
	 */
	private void tie() {
		state = 0;
		ImageIcon icon = null;
		if (isMonoChrome)
			revealBoard();
		icon = new ImageIcon(getClass().getResource("../GUI/resource/player1-lost.png"));
		int playAgain = JOptionPane.showConfirmDialog(gamePanel,"IT'S A TIE!!!\n" 
				+ "Would you like to play again?",
				"No Winners", 0, JOptionPane.YES_NO_OPTION, icon);
		
		if(playAgain == 0){		//yes
			restart();
		} else if(playAgain == 1){		//no
			//think about installing glassPane and doing nothing
			c4Game.viewPlayPanel(mainFrame);
			gamePanel.setVisible(false);
			leftPanel.setVisible(false);
			rightPanel.setVisible(false);
		}
	}
	
	/**
	 * Restarting the entire game.
	 * @pre		None
	 * @post	Restarts the game to initial state.
	 */
	public void restart(){
		initialise();
		gamePanel.setEnabled(true);
		gamePanel.setVisible(true);
		gamePanel.setFocusable(true);
		gamePanel.requestFocusInWindow();
		gamePanel.restart(mainFrame);
		listener = new ConnectFourListener(this, gamePanel);
		rightPanel.updateTurnDisplay(getCurrentPlayer());
		state = 1;
		if (ai_difficulty == 1) //easy difficulty get 3 hints
			numHints = 3;
		else if (ai_difficulty == 2 && !isMonoChrome) // normal diff get 1 hint
			numHints = 1;
		else
			numHints = 0; // otherwise 0
		rightPanel.updateHintButtonImage(numHints);
		hintFlag = 0; // 0 for no hints activated, 1 for one active atm 
		//disable any turned on hints
		if (hint != null && hint.isRunning()) {
			hint.stop();
			gamePanel.repaintHintCell();
			hintFlag = 0;
		}
		if (current_player == 2 && isCPU()) doAIMove();
	}
	
	
	/**
	 * Allows key pressed to enter in chips by players.
	 * @pre		None.
	 * @post	Player makes a move by keyboard.
	 */
	public void keyPressed(KeyEvent e) {
		//System.out.println("Press");
		int k = e.getKeyCode() - 48;
		boolean win = false;
		// numbers one to seven
		if (k >= 0 && k < 8 ) {
			if(checkMoveValid(k)){
				int row = dropToken(k);  
			    if(row != -1){
		        	gamePanel.set(k, row, getCurrentPlayer(), checkMonoChrome());
			    }
			    win = checkForWin(false);
			    if(win == true){
			    	win(getCurrentPlayer());
			    }
			}
		}		
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}
	
	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		
		if (event.getActionCommand().equals("GameOptions")) {
			//check to make sure user really wants to quit
			int quit = JOptionPane.showConfirmDialog(mainFrame,
					"You sure you want to quit this game\n and return to Game Options?",
					"Quit Message", JOptionPane.YES_NO_OPTION);
			if(quit == 0) { //yes
				c4Game.showPlayPanel();
				gamePanel.setVisible(false);
				rightPanel.setVisible(false);
				leftPanel.setVisible(false);
				if (c4Game.helpDialogOn())
					c4Game.hideHelpDialog();
			}
		} else if (event.getActionCommand().equals("NewGame")) {
			int quit = JOptionPane.showConfirmDialog(mainFrame,
					"Start a new game?", "New Game", JOptionPane.YES_NO_OPTION);
			if(quit == 0) { //yes
				if (c4Game.helpDialogOn())
					c4Game.hideHelpDialog();
				restart();
			}
		} else if (event.getActionCommand().equals("Help")) {
			if (c4Game.helpDialogOn()) {
				if (!c4Game.isHelpDialogVisible()) {
					c4Game.positionHelpDialog();
					c4Game.setHelpDialogVisible(true);
				} else
					c4Game.hideHelpDialog();
			} else {
				c4Game.viewHelpDialog();
			}
		} else if (event.getActionCommand().equals("Difficulty")) {
			//check to make sure user really wants to quit
			int quit = JOptionPane.showConfirmDialog(mainFrame,
					"You sure you want to quit this game\n and choose a different difficulty?",
					"Quit Message", JOptionPane.YES_NO_OPTION);
			if(quit == 0) { //yes
				c4Game.viewPlayPanel(mainFrame);
				gamePanel.setVisible(false);
				rightPanel.setVisible(false);
				leftPanel.setVisible(false);
				if (c4Game.helpDialogOn())
					c4Game.hideHelpDialog();
			}
		} else if (event.getActionCommand().equals("Sound On")) {
			c4Game.startMusic();
			rightPanel.setSoundOnButton();
		} else if (event.getActionCommand().equals("Sound Off")) {
			c4Game.stopMusic();
			rightPanel.setSoundOffButton();
		}  else if (event.getActionCommand().equals("Hint")) {
			if (hintFlag == 1) { //only enables 1 hint at a time
				JOptionPane.showMessageDialog(gamePanel, "You already have a hint!");
			} else if (numHints > 0) {
				numHints--;
				hintFlag = 1;
				int col = getHint();
				int row;
				for (row = 5; row >= 0; row--) {
					if (board.get(row).get(col).getValue() == 0)
						break;
				}
				hint = gamePanel.highlightHint(row, col);
				rightPanel.updateHintButtonImage(numHints);
			} else { // no hints
				String message;
				if (isMonoChrome || ai_difficulty == 3)
					message = "There are no hints at this level.";
				else if (!isCPU())
					message = "Hints are disabled in 2 Player mode.";
				else
					message = "There are no more hints!";
				JOptionPane.showMessageDialog(gamePanel, message + "\nNo cookies for youuu!", 
						"MOAR HINTS?", JOptionPane.WARNING_MESSAGE, new ImageIcon(getClass().getResource("../GUI/resource/red-chip-ai-selected.png")));
			}
		} else if (event.getActionCommand().equalsIgnoreCase("Quit")) {
				int quit = JOptionPane.showConfirmDialog(mainFrame,"Are you sure you want to quit?",
							"Quit Message",JOptionPane.YES_NO_OPTION);
				if(quit == 0)
					System.exit(0);
		} else {
			update();
		}
	}
	
	/***
	 * Returns true if a game is in progress.
	 * @pre		None.
	 * @return	Returns true if game is in progress, false otherwise.
	 */
	public boolean isInPlay() {
		if (state == 1)
			return true;
		return false;
	}
	
	/**
	 * Regular checks and updates to game.
	 * @pre		None.
	 * @post	Updates the game status.
	 */
	public void update() {
		//System.out.println("update running.....current move: " + moves_made);
	
		//disable any turned on hints
		if (hint != null && hint.isRunning()) {
			hint.stop();
			gamePanel.repaintHintCell();
			hintFlag = 0;
		}
		
		//win checking
	    if(checkForWin(false)){
	    	win(getCurrentPlayer());
	    }
	    
	    if(boardFull())
	    	tie();
	    
		//check if need to do player move
		if ( isPlayerAI(getCurrentPlayer()) && isCPU() ) {
			doAIMove();
		}
		
		rightPanel.updateTurnDisplay(current_player);
	}
	
	private boolean boardFull() {
		for (int col = 0; col < 7; col++) {
			if (board.get(0).get(col).getValue() == 0)
				return false;
		}
		return true;
	}

	/**
	 *	Checks if a game is in monochrome mode.
	 *	@pre	None.
	 *	@return	True if a game is in monochrome mode, false otherwise.
	 */
	public boolean checkMonoChrome() {
		return isMonoChrome;
	}
	
	
	/**
	 * Checks if Listener is active.
	 * @pre		None.
	 * @return	True if the listener object is active, false otherwise.
	 */
	public boolean isListenerActive(){
		return isListenerActive;
	}
	
	/***
	 * Returns the game board.
	 * @pre		None.
	 * @return	Returns the game board object.
	 */
	public ArrayList<ArrayList<Cell>> getBoard() {
		return board;
	}
	
	/**
	 * Switches current player.
	 * @pre		None.
	 * @post	Switches game play to the other player.
	 */
	public void switchCurrentPlayer() {
		if (current_player == 1) current_player = 2;
		else if (current_player == 2) current_player = 1;
	}
	
	/**
	 * Inserts token into open position (can be floating).
	 * @param row	X-coordinate.
	 * @param col	Y-coordinate.
	 * @param player	Current player.
	 */
	public void customDropToken(int row, int col, int player) {
		board.get(row).get(col).setValue(player);
	}
	
	/**
	 * Undoes last dropped token.
	 * @param col Y-coordinate.
	 * @post	Undoes last move.
	 */
	public void undoDropToken(int col) {
		for (int i = 5; i >= 0; i--) {
			if(board.get(i).get(col).getValue() == 0) {
				board.get(i+1).get(col).setValue(0);
				break;
			}
		}
		board.get(0).get(col).setValue(0);
	}
	
	/**
	 * Resets the win (used in AI).
	 */
	public void resetWin() {
		winning_player = 0;
	}
	
	/**
	 * Gets a hint from AI (hard mode).
	 * @return	Returns the Y-coordinate given by hard mode AI.
	 */
	public int getHint() {
		if (helpButtonPressedNumber == 1) {
			dropToken(ai.getHint(this));
		}
		helpButtonPressedNumber = 1;
		return ai.getHint(this);
	}
	
	/**
	 * Reset button presses.
	 * @pre		None.
	 * @post	Sets helpbutton and fullcolumn indicators to 0.
	 */
	public void resetButtonPresses() {
		helpButtonPressedNumber = 0;
		fullColumnPressedNumber = 0;
	}
}