package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 7425601781473840073L;

	/**
	 * Constructor to create the frame.
	 */
	public MainFrame() {
		initialize();
	}
	
	/**
	 * Intialising the main frame GUI
	 * 
	 */
	public void initialize()
	{
		setTitle("Connect Four");
		setBounds(100, 100, 1200, 750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);	
		setVisible(true);
		setLocationRelativeTo(null);
		setIconImage(new ImageIcon(getClass().getResource("resource/icon.png")).getImage());
		//setting background
		try {
			this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(getClass().getResource("resource/bg-dark5.png")))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found.");
			e.printStackTrace();
		}
		
		//adding a gridbaglayout to the frame
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[]{10, 0, 0, 0, 0};
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
	}
	
}