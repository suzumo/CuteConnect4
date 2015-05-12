package GUI;


import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

import javax.swing.*;

import Game.Cell;
import Game.Board;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
    private ArrayList<ArrayList<Cell>> gameBoard;
    private Board board;

    public GamePanel(JFrame mainFrame){
    	board = new Board();
    	gameBoard = board.getBoard();
		setFocusable(true);				//sets the focus of the keypress events to the game board
		setMinimumSize(new Dimension(700,700));
		setSize(600,600);
		drawBoard();
		this.setBackground(Color.BLACK);
		validateToMainFrame(mainFrame);
		this.setFocusable(true);
		this.requestFocusInWindow();
    }

	private void drawBoard() {
		JButton button;
		setLayout(new GridLayout(6,7));
		
		for(int y = 0; y < 7; y++){
			for(int x = 0; x < 6; x++){
				button = new JButton(new ImageIcon(this.getClass().getResource("resource/Cell.png")));
				this.add(button);
			}
		}
		
	}

	private void validateToMainFrame(JFrame mainFrame) {
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
}
