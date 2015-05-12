package Game;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import GUI.GamePanel;
import GUI.MainFrame;

public class BoardMechanics implements ActionListener, KeyListener {

	private ConnectFourGame connectFourGame;
	private MainFrame mainFrame;
	private GamePanel board;
	private Timer timer;
	private int checkTime;
	
	public BoardMechanics(ConnectFourGame c4, MainFrame mainFrame){
		this.connectFourGame = c4;
		this.mainFrame = mainFrame;
		board = new GamePanel(mainFrame);
	}
	
	
	
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
