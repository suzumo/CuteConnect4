import java.util.ArrayList;


public class Board {
	private ArrayList<ArrayList<Cell>> board;
	
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
		for(int i = 0; i < 6; i++){
			if(board.get(col).get(i).getValue() == 0){
				board.get(col).get(i).setValue(player);
				break;
			}
		}
	}
	
	
	public ArrayList<ArrayList<Cell>> getBoard() {
		return board;
	}

	/**
	 * Prints out the board
	 */
	public void print() {
		System.out.println("Bottom");
		for(int i = 0; i < 6 ; i++){
			for(int j = 0; j < 7;j++){
				System.out.print(board.get(j).get(i).getValue() + " ");
			}
			System.out.println();
		}
		System.out.println("Top");
		System.out.println();
	}
}
