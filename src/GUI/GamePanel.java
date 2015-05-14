package GUI;


import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

import javax.swing.*;

import Game.Cell;
import Game.BoardMechanics;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	private JButton[][] slots; 
	
	public GamePanel(MainFrame mainFrame) {
		//setFocusable(true);				//sets the focus of the keypress events to the game board
		setMinimumSize(new Dimension(700,700));
		setSize(600,600);
		slots = new JButton[6][7];
		drawBoard();
		this.setBackground(Color.BLACK);
		validateToMainFrame(mainFrame);
	}
	
	private void drawBoard(){
		JButton button;
		setLayout(new GridLayout(6,7));
		
		for(int row = 0; row < 6; row++){
			for(int col = 0; col < 7; col++){
				button = new JButton(new ImageIcon(this.getClass().getResource("resource/Cell.png")));
				button.setSelectedIcon(new ImageIcon(getClass().getResource("resource/CellHover.png")));
				button.setPressedIcon(new ImageIcon(getClass().getResource("resource/CellSelect.png")));
				slots[row][col] = button;
				this.add(button);
			}
		}
	}
	
	/**
	 * Adds the gamePanel to the mainframe
	 * @param mainFrame
	 */
	private void validateToMainFrame(MainFrame mainFrame) {
		GridBagConstraints gbc = new GridBagConstraints();		//creating new gridbagconstraints for the panel
		gbc.insets = new Insets(60, 60, 60, 60);
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		mainFrame.getContentPane().add(this, gbc);
		mainFrame.validate();
	}

	public void addListener(ConnectFourListener connectFourListener) {
	    for (int row=0; row<6; row++) { 
	        for (int col = 0; col < 7; col++){ 
	          slots[row][col].addMouseListener(connectFourListener); 
	        }      
	   } 		
	}

	public int getColumn(JButton button) {
		int returnColumn = -1; 
		for (int row=0; row<6; row++) { 
			for (int col = 0; col < 7; col++) { 
				if (slots[row][col] == button) { 
					returnColumn = col; 
				}        
			}      
		}   
		System.out.println("returnColumn: " + returnColumn);
		return returnColumn; 
	}

	
	
	public void set(int column, int row, int player) {
	    if (player == 1) { 
	      slots[row][column].setIcon(new ImageIcon(this.getClass().getResource("resource/player1.png")));
	 
	    }  
	    else { 
	      slots[row][column].setIcon(new ImageIcon(this.getClass().getResource("resource/player2.png")));
	    }    		
	}
	
}
