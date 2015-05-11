import java.util.ArrayList;


public class Board {
	private ArrayList<ArrayList<Cell>> board;
	private int turn;
	private int curr_row;
	
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
		curr_row = 0;
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
					curr_row = i;
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
	
	/***
	 * Checks for win at each coin drop at column, col.
	 * @pre			turn is greater than 6.
	 * @param col	column number
	 * @return		0 if there is no win, player number (i.e. 1, 2...) if there is a win.
	 */
	public int checkForWin(int col){

		int x = col;
		int y = curr_row;
		int player_val = board.get(col).get(curr_row).getValue();

		int count = 3;
		
		// check down
		check_down:
		if (y >= 3) {
			y--; //checking the cell below insertion
			while (y >= 0) {
				// if the cell is not equal to player_val, then stop check_down
				if (board.get(x).get(y).getValue() != player_val) {
					count = 3;
					y = curr_row;
					x = col;
					break check_down;
				}
				count--;
				
				// if count is zero then there are 4 cells with same value
				if (count == 0)
					return player_val;
			}
		}
		
		// check sideways - first left
		check_left:
		if (x-1 >= 0) {
			x--; //checking the cell below insertion

			// if the cell is not equal to player_val, then stop check_left
			if (board.get(x).get(y).getValue() != player_val) {
				x = col;
				y = curr_row;
				break check_left;
			}
			count--;
				
			// if count is zero then there are 4 cells with same value
			if (count == 0)
				return player_val;
		}

		// check sideways - second right
		check_right:
		if (x+1 <= 7) {
			x++; //checking the cell below insertion

			// if the cell is not equal to player_val, then stop check_right
			if (board.get(x).get(y).getValue() != player_val) {
				//reset values as there are no wins
				count = 3;
				y = curr_row;
				x = col;
				break check_right;
			}
			count--;
					
			// if count is zero then there are 4 cells with same value
			if (count == 0)
				return player_val;
		}
		
		// check ascending diagonal
		check_left_ascending_diagonal:
		if (x-1 >= 0 && y-1 >=0) {
			x--; 
			y--;
			
			if (board.get(x).get(y).getValue() != player_val) {
				x = col;
				y = curr_row;
				break check_left_ascending_diagonal;
			}
			count--;
					
			// if count is zero then there are 4 cells with same value
			if (count == 0)
				return player_val;
		}

		check_right_ascending_diagonal:
		if (x+1 <= 7 && y+1 <= 6) {
			x++; 
			y++;
			
			if (board.get(x).get(y).getValue() != player_val) {
				count = 3;
				x = col;
				y = curr_row;
				break check_right_ascending_diagonal;
			}

			count--;

			if (count == 0)
				return player_val;
		}

		// check descending diagonal
		check_left_descending_diagonal:
		if (x-1 >= 0 && y+1 <= 6) {
			x--;
			y++;
			
			if (board.get(x).get(y).getValue() != player_val) {
				x = col;
				y = curr_row;
				break check_left_descending_diagonal;
			}
			count--;
					
			if (count == 0)
				return player_val;
		}

		check_right_descending_diagonal:
		if (x+1 <= 7 && y-1 >= 0) {
			x++; 
			y--;
			
			if (board.get(x).get(y).getValue() != player_val) {
				count = 3;
				x = col;
				y = curr_row;
				break check_right_descending_diagonal;
			}

			count--;

			if (count == 0)
				return player_val;
		}

		return 0;
		
	}

	
}
