package GUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

import GUI.GamePanel;
import Game.BoardMechanics;

public class ConnectFourListener implements MouseListener {

	private GamePanel gui; 
	private BoardMechanics game; 
	
	public ConnectFourListener(BoardMechanics game, GamePanel gui) { 
		this.game = game; 
		this.gui = gui; 
		gui.addListener(this); 
	}
	
	@Override
	public void mouseClicked(MouseEvent event) {
		if(game.isListenerActive()){
			JButton button = (JButton) event.getComponent();
			int row = -1;
		    int column = gui.getColumn(button); 
	    	row = game.dropToken(column);
	    	System.out.println("row: " + row + " col " + column);
		    if(row != -1){
	        	gui.set(column, row, game.getCurrentPlayer(), game.checkMonoChrome());
		    }
		    
			game.update();
		}
	}

	
	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

}
