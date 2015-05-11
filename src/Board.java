import java.util.ArrayList;


public class Board {
	private ArrayList<ArrayList<Cell>> board;
	private int turn;
	/**
	 * Constructor of boredom
	 * unsure if I'm making this one size too big
	 */
	public Board() {
		board = new ArrayList<ArrayList<Cell>>();
		
		//Populating the board with cells
		for(int i = 0; i < 7 ; i++){
			board.add(new ArrayList<Cell>());
			for(int j = 0; j < 6;j++){
				board.get(i).add(new Cell(i,j,0));
			}
		}
		
		turn = 0;
	}
	
	/**
	 * Drops in a token into the board
	 * @param col 		column to insert token
	 * @param player	owner of token -> can be replaced by turn or whatever
	 */
	public void dropToken(int col, int player) {
//		int row = 0;
//		while (board[col][row] == 0) row += 1;
//		board[col][row] = player;
		if(checkMoveValid(col)){
			for(int i = 0; i < 6; i++){
				if(board.get(col).get(i).getValue() == 0){
					board.get(col).get(i).setValue(player);
					break;
				}
			}
			turn++;
		}
	}
	
	/**
	 * Checks if the current move attempt is valid 
	 * @param col      : The column that the player or computer is attempting to drop a token in
	 * @return valid   : If the move is valid or not
	 */
	public boolean checkMoveValid(int col){
		boolean valid = true;
		
		if(col > 7){
			valid = false;
		}
		if(board.get(col).get(5).getValue() != 0){
			valid = false;
		}
		
		
		return valid;
	}
	
	public ArrayList<ArrayList<Cell>> getBoard() {
		return board;
	}
	
	
	/**
	 * Calculates which player's turn it is
	 * 
	 * Note: 1 = player1 2 = player 2
	 * @return player     : The which player's turn it is
	 */
	public int calcTurn(){
		int player = 0;
		if((turn%2) == 0){
			player = 2;
		} else{
			player = 1;
		}
		return player;
	}
	
	/**
	 * Returns the current turn
	 * @return turn   : The current turn
	 */
	public int getTurn(){
		return turn;
	}

	/**
	 * Prints out the board
	 */
	public void print() {
		for (int i = 5; i >= 0; i--) {
		    for (int j = 6; j >= 0; j--) {
		    	System.out.print(board.get(j).get(i).getValue() + " ");
		    }
		    System.out.println();
		}
		System.out.println();
	}
	
}
