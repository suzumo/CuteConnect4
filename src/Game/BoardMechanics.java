package Game;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import GUI.ConnectFourListener;
import GUI.GamePanel;
import GUI.MainFrame;
import Game.ConnectFourGame;


public class BoardMechanics implements ActionListener, KeyListener{
	private ConnectFourGame c4Game;
	private MainFrame mainFrame;
	private GamePanel gamePanel;
	private ConnectFourListener listener;
	private int currentPlayer;
	private ArrayList<ArrayList<Cell>> board;
	private int turn;
	private int curr_row;
	
	public BoardMechanics(ConnectFourGame connectFourGame, MainFrame mFrame) {
		//Initializing board
		board = new ArrayList<ArrayList<Cell>>();
		
		//Populating the board with cells
		for(int row = 0; row < 6; row++){
			board.add(new ArrayList<Cell>());
			for(int col = 0; col < 7;col++){
				board.get(row).add(new Cell(row,col,0));
			}
		}
		this.print();
		
		turn = 0;
		this.mainFrame = mFrame;
		this.c4Game = connectFourGame;
		gamePanel = new GamePanel(mainFrame);
		listener = new ConnectFourListener(this, this.gamePanel);
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
					board.get(i).get(col).setValue(this.getCurrentPlayer());
					curr_row = i;
					break;
				}
			}
			turn++;
			System.out.println("Row: " + curr_row);
			return curr_row;
		}
		return dRow;
	}
	
	/**
	 * Calculates the current player with turns
	 */
	public int getCurrentPlayer() {
		currentPlayer = (turn%2)+1;
		return currentPlayer;
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

	/**
	 * Checks if the current move attempt is valid 
	 * @param col      : The column that the player or computer is attempting to drop a token in
	 * @return valid   : If the move is valid or not
	 */
	public boolean checkMoveValid(int col){
		boolean valid = true;
		
		if(col > 7 || col < 0){
			valid = false;
		}
		if(board.get(0).get(col).getValue() != 0){
			System.out.println("Full");
			valid = false;
		}
		
		
		return valid;
	}
	
	/***
	 * Checks for win at each coin drop at column, col.
	 * @pre			turn is greater than 6.
	 * @param col	column number
	 * @return		0 if there is no win, player number (i.e. 1, 2...) if there is a win.
	 */
	public int checkForWin(int col, int row){
		
		if (turn < 7) return 0;
		return 0;
		
		
		
	}
	
	public void win(int win) {
		int playAgain = JOptionPane.showConfirmDialog(gamePanel,"You Won!!\n" + "Would you like to play again?","Winner",JOptionPane.YES_NO_OPTION);
		if(playAgain == 0){		//yes
			restart();
		} else if(playAgain == 1){		//no
			c4Game.viewMenuPanel(mainFrame);
			gamePanel.setVisible(false);
		}
	}
	
	/**
	 * restarting the entire game
	 * 
	 */
	public void restart()
	{
		gamePanel.removeAll();
		gamePanel.setVisible(true);
		gamePanel.setFocusable(true);
		gamePanel.requestFocusInWindow();
		new BoardMechanics(c4Game, mainFrame);

	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}










	
	
	
}
