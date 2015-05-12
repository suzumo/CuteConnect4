package GUI;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

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
		setBounds(100, 100, 1000, 750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);	
		setVisible(true);
		setLocationRelativeTo(null);
		setIconImage(new ImageIcon(getClass().getResource("resource/icon.png")).getImage());

		getContentPane().setBackground(Color.BLACK);
		
		//adding a gridbaglayout to the frame
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[]{10, 0, 0, 0, 0};
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
	}
}