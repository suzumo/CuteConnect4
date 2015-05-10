import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;


public class ConnectFourGame extends JFrame implements KeyListener{
	
	Graphics g;
	
	public ConnectFourGame() {
		
		// set up frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(this);
		setVisible(true);
		setSize(400, 400);	// may need to make this dynamic
		setTitle("Connect Four");
		setBackground(Color.BLACK);

		
	}
	
	public void paint(Graphics g){
		
		
		g.setColor(Color.WHITE);
		
		
	}
	
	public static void main(String[] args) {
		System.out.println("connect four game 2015");
		//Testing board functions
		Board board = new Board();
		board.print();
		board.dropToken(3, 1);
		board.print();
		
		
		
		ConnectFourGame cfg = new ConnectFourGame();
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

}
