
public class Cell {

	/***
	 * Constructor for Cell object.
	 * @pre			Cell must be contained in a valid Board object. 
	 * @param ix	The x-coordinate of this Cell.
	 * @param iy	The y-coordinate of this Cell.
	 * @param iv	The value of this Cell.
	 * @post		Creates a Cell object with the relevant input information.
	 */
	public Cell(int ix, int iy, int iv){
		x = ix;
		y = iy;
		val = iv;
	}

	/***
	 * Returns the x-coordinate of this Cell.
	 * @pre		None.
	 * @return	The x-coordinate of this Cell.
	 */
	public int getX() {
		return x;
	}
	
	/***
	 * Returns the y-coordinate of this Cell.
	 * @pre		None.
	 * @return	The y-coordinate of this Cell.
	 */
	public int getY() {
		return y;
	}
	
	/***
	 * Returns the value of this Cell.
	 * @pre		None.
	 * @return	The value of this Cell; e.g. 1 for Player1, 2 for Player2.
	 */
	public int getValue() {
		return val;
	}
	
	/***
	 * Fields
	 * @param x		The x-coordinate of this Cell.
	 * @param y		The y-coordinate of this Cell.
	 * @param value	The value of this Cell.
	 */
	private int x;
	private int y;
	private int val;

}
