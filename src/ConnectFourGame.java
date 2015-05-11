import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;


public class ConnectFourGame extends JFrame implements KeyListener{
	
	Graphics g;
	Board board;
	
	int width = 700;
	int height = 700;
	int cellwidth = 70; // assume square cells
	int currentplayer; 	// 1 or 2
	int playermode;		// 1 for pvp, 2 for computer
	
	public ConnectFourGame() {
		
		// set up frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(this);
		setVisible(true);
		setSize(width, height);	// may need to make this dynamic
		setTitle("Connect Four");
		setBackground(Color.BLACK);

		g = getGraphics();
		board = new Board();
		
		currentplayer = 1;
	}
	
	public void paint(Graphics g){
		
		// black background
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		
		
		// draw cells 
		g.translate(100, 100);
		for (ArrayList a: board.getBoard()){
			for (Cell c: (ArrayList<Cell>) a){
				if (c.getValue() == 0){
					g.setColor(Color.white);
					g.drawRect(c.getCol() * cellwidth, cellwidth*(6-c.getRow() ), cellwidth, cellwidth);
				} else if (c.getValue() == 1){
					g.setColor(Color.BLUE);
					g.fillRect(c.getCol() * cellwidth, (6-c.getRow() ) * cellwidth, cellwidth, cellwidth);
				} else if (c.getValue() == 2){
					g.setColor(Color.RED);
					g.fillRect(c.getCol() * cellwidth, (6-c.getRow() ) * cellwidth, cellwidth, cellwidth);			
				}
			}
		}
		g.translate(-100, -100);
		
		
		
	}

		
	
	public static void main(String[] args) {
		System.out.println("connect four game 2015");
	
		ConnectFourGame cfg = new ConnectFourGame();
		cfg.setVsCPU();
//		cfg.setVsPlayer();
		
		// draw then update loop
		while( true ){
			cfg.update();
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			cfg.draw();
			
		}
	}


	public void setVsCPU() {
		playermode = 2;
	}
	
	public void setVsPlayer() {
		playermode = 1;
	}

	private void draw() {
		// TODO Auto-generated method stub
		paint(g);
	}

	private void update() {
		
		// if in vs CPU, drop coin
		if (playermode == 2 && currentplayer == 2){
			board.dropToken((int) Math.floor(Math.random()* 7), currentplayer);
			switchPlayer();
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int k = e.getKeyCode() - 48;
		
		// numbers one to seven
		if (k > 0 && k < 8 ) {
			// drop a token if valid turn
			
			//for vsCPU mode, only drop token if player 1's turn
			if (isVsCPU()){
				if ( currentplayer == 1){
					board.dropToken(k - 1, currentplayer);
				}
			}
			
			//for vsPlayer mode, can drop at any time
			if (isVsPlayer()){
				board.dropToken(k - 1, currentplayer);
			}
			
			
			// Handle turns
			switchPlayer();
		}
	}

	public boolean isVsCPU() {
		return playermode == 2;
	}

	public boolean isVsPlayer() {
		return playermode == 1;
	}

	private void switchPlayer() {
		if (currentplayer == 1)
			currentplayer = 2;
		else 
			currentplayer = 1;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
