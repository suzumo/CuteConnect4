package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import Game.Cell;
import Game.BoardMechanics;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	
	/**
	 * @param slots		Game cells.
	 * @param buttons	List of buttons in GamePanel.
	 */
	private JButton[][] slots;
	private ArrayList<JButton> buttons;

	/**
	 * Constructor for GamePanel.
	 * @pre		JFrame for which this GamePanel is set must exist.
	 * @param mainFrame	JFrame where this GamePanel will be set.
	 * @post	JFrame is set with GamePanel.
	 */
	public GamePanel(MainFrame mainFrame) {
		buttons = new ArrayList<JButton>();
		slots = new JButton[6][7];

		//setMinimumSize(new Dimension(700,700));
		//setSize(600,600);
		//sets the focus of the keypress events to the game board
		//setFocusable(true);
		drawBoard();
		validateToMainFrame(mainFrame);
	}
	
	/**
	 * Creates the connect 4 game board.
	 * @pre		None.
	 * @post	A Connect 4 game board is created in GamePanel.
	 */
	private void drawBoard(){
		
		JButton button;
		setLayout(new GridLayout(6,7));
		
		for(int row = 0; row < 6; row++){
			for(int col = 0; col < 7; col++){
				button = new JButton(new ImageIcon(this.getClass().getResource("resource/Cell.png")));
				button.setActionCommand("Drop");
				button.setBorderPainted(true);
				button.setSelectedIcon(new ImageIcon(getClass().getResource("resource/CellHover.png")));
				button.setPressedIcon(new ImageIcon(getClass().getResource("resource/CellSelect.png")));
				button.setMinimumSize(new Dimension(90,90));
				slots[row][col] = button;
				buttons.add(button);
				this.add(button);
			}
		}

	}
	
	/**
	 * Adds the GamePanel to the mainframe.
	 * @pre		JFrame where GamePanel is to be set must exist.
	 * @param mainFrame	JFrame to which this GamePanel will be set.
	 * @post	JFrame is set in the center with this GamePanel.
	 */
	private void validateToMainFrame(MainFrame mainFrame) {		
		GridBagConstraints gbc = new GridBagConstraints();		//creating new gridbagconstraints for the panel
		gbc.insets = new Insets(80, 270, 80, 250);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		mainFrame.getContentPane().add(this, gbc);
		mainFrame.validate();
	}

	/**
	 * Add ConnectFourListener object to every cell.
	 * @param	ConnectFourListener must exist.
	 * @param connectFourListener	ConnectFourListener which will listen to activities in GamePanel.
	 * @post	GamePanel adds listener object.
	 */
	public void addListener(ConnectFourListener connectFourListener) {
	    for (int row=0; row<6; row++) { 
	        for (int col = 0; col < 7; col++){ 
	          slots[row][col].addMouseListener(connectFourListener); 
	        }      
	    } 		
	}

	/**
	 * Returns the column number for a certain cell in game board.
	 * @pre		Button parameter must be valid and within limits of game board.
	 * @param button	Cell for which column information is required of.
	 * @return	Returns the column number of cell.
	 */
	public int getColumn(JButton button) {
		int returnColumn = -1; 
		for (int row=0; row<6; row++) { 
			for (int col = 0; col < 7; col++) { 
				if (slots[row][col] == button) { 
					returnColumn = col; 
				}        
			}      
		}   
		return returnColumn; 
	}

	/**
	 * Sets the cell with coin for specified player number.
	 * @pre		Inputs must be within cell board array and player number must be valid.
	 * @param column	Cell column number.
	 * @param row		Cell row number.
	 * @param player	Player number who is putting in the coin at this location.
	 * @isMonoChrome	Boolean flag for Monochrome game.
	 * @post			The Cell with specified column and row is highlighted with player's coin.
	 */
	public void set(int column, int row, int player, boolean isMonoChrome) {
	    if(isMonoChrome == true){
	    	slots[row][column].setBackground(Color.DARK_GRAY);
	    } else {
			if (player == 1) { 
		    	slots[row][column].setBackground(Color.RED); 
		    } else {
		    	slots[row][column].setBackground(Color.GREEN);
		    }    		
	    }
	}
	
	/**
	 * Displays the winning tokens
	 */
	public void showWinningTokens(ArrayList<Cell> tokens){
		for(Cell token : tokens){
			System.out.print("row: " + token.getRow() + " col: " + token.getCol() + "/ ");
			slots[token.getRow()][token.getCol()].setIcon(new ImageIcon(this.getClass().getResource("resource/CellWin.png")));
			slots[token.getRow()][token.getCol()].setSelectedIcon(new ImageIcon(this.getClass().getResource("resource/CellWin.png")));
		}
	}
	
	/**
	 * Restarts the game by re-drawing the GamePanel.
	 * @pre		JFrame for which this GamePanel is set must exist.
	 * @param mainFrame	JFrame where this GamePanel is set.
	 * @post	JFrame re-draws GamePanel.
	 */
	public void restart(MainFrame mainFrame) {
		this.removeAll();
		drawBoard();	
		validateToMainFrame(mainFrame);
	}

	/**
	 * Returns all the buttons in GamePanel.
	 * @pre		None.
	 * @return	List of buttons in GamePanel.
	 */
	public ArrayList<JButton> getButtons() {
		return buttons;
	}
	
}