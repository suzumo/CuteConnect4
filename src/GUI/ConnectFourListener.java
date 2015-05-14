package GUI;

import javax.swing.*; 

import java.awt.event.*;

import GUI.GamePanel;
import Game.BoardMechanics;

public class ConnectFourListener implements MouseListener{

	GamePanel gui; 
	BoardMechanics game; 
	public ConnectFourListener(BoardMechanics game, GamePanel gui) { 
		this.game = game; 
		this.gui = gui; 
		gui.addListener(this); 
	}
	
	@Override
	public void mouseClicked(MouseEvent event) {
	    JButton button = (JButton) event.getComponent(); 
	    int column = gui.getColumn(button); 
	    int row = game.dropToken(column);  
	    game.print();
        gui.set(column, row, game.getCurrentPlayer());
	    
	}

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
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
