package GUI;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import Game.Cell;
import Game.BoardMechanics;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	
	private JButton[][] slots;
	private ArrayList<JButton> buttons;
	
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
	 * Adds the gamePanel to the mainframe
	 * @param mainFrame
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
		return returnColumn; 
	}

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
	

	public void restart(MainFrame mainFrame) {
		this.removeAll();
		drawBoard();	
		validateToMainFrame(mainFrame);
	}
	
	public ArrayList<JButton> getButtons() {
		return buttons;
	}
	
}
