package Game;


public class Cell {

	/***
	 * Fields
	 * @field x		The x-coordinate of this Cell.
	 * @field y		The y-coordinate of this Cell.
	 * @field value	The value of this Cell.
	 */
	private int col;
	private int row;
	private int val;
	
	/***
	 * Constructor for Cell object.
	 * @pre			Cell must be contained in a valid Board object. 
	 * @param ix	The x-coordinate of this Cell.
	 * @param iy	The y-coordinate of this Cell.
	 * @param iv	The value of this Cell.
	 * @post		Creates a Cell object with the relevant input information.
	 */
	public Cell(int iRow, int iCol, int iv){
		col = iCol;
		row = iRow;
		val = iv;
	}

	/***
	 * Returns the col index of this Cell.
	 * @pre		None.
	 * @return	The col index of this Cell.
	 */
	public int getCol() {
		return col;
	}
	
	/***
	 * Returns the row index of this Cell.
	 * @pre		None.
	 * @return	The row index of this Cell.
	 */
	public int getRow() {
		return row;
	}
	
	/***
	 * Returns the value of this Cell.
	 * @pre		None.
	 * @return	The value of this Cell; e.g. 1 for Player1, 2 for Player2 and 0 for empty Cell.
	 */
	public int getValue() {
		return val;
	}
	
	/***
	 * Sets the value of this Cell.
	 * @pre				None.
	 * @param newVal	New value of this Cell.
	 * @post			The value of this Cell; e.g. 1 for Player1, 2 for Player2 and 0 for empty Cell.
	 */
	public void setValue(int newVal) {
		val = newVal;
	}

}
