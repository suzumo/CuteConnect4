package GUI;

import javax.swing.*; 

import java.awt.event.*;

import GUI.GamePanel;
import Game.BoardMechanics;

public class ConnectFourListener implements MouseListener{
	private boolean win;
	GamePanel gui; 
	BoardMechanics game; 
	public ConnectFourListener(BoardMechanics game, GamePanel gui) { 
		win = false;
		this.game = game; 
		this.gui = gui; 
		gui.addListener(this); 
	}
	
	@Override
	public void mouseClicked(MouseEvent event) {
//		System.out.println("Click");
		JButton button = (JButton) event.getComponent();
		int row = -1;
	    int column = gui.getColumn(button); 
    	row = game.dropToken(column);
    	System.out.println("row: " + row + " col " + column);
	    if(row != -1){
        	gui.set(column, row, game.getCurrentPlayer());
	    }
	    win = game.checkForWin();
	    if(win == true){
	    	game.win(game.getCurrentPlayer());
	    }
	}

	
//	if(game.getCurrentPlayer() == 2 && game.isCPU()){
//    	move = game.aiDropToken();
//		row = game.dropToken(move);
//		System.out.println("move "+ move +" Row "+ row);
//		if(row != -1){
//        	gui.set(move, row, game.getCurrentPlayer());
//	    }
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

}
