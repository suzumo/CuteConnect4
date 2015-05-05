
public class Board {
	private int[][] board;
	
	/**
	 * Constructor of boredom
	 * unsure if I'm making this one size too big
	 */
	public Board() {
		int[][] board = new int[7][6];
		int x;
		int y;
		for (x = 0; x < 7; x += 1) {
			for (y = 0; y < 6; y += 1) {
				board[x][y] = 0;
			}
		}
	}
	
	/**
	 * Drops in a token into the board
	 * @param col 		column to insert token
	 * @param player	owner of token -> can be replaced by turn or whatever
	 */
	public void dropToken(int col, int player) {
		int row = 0;
		while (board[col][row] == 0) row += 1;
		board[col][row] = player;
	}
	
	public int[][] getBoard() {
		return board;
	}
}
