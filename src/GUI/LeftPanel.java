package GUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LeftPanel extends JPanel{
	
	/**
	 * Constructor for LeftPanel.
	 * @pre		None.
	 * @param frame JFrame for which this LeftPanel will be set in.
	 * @post	JFrame sets LeftPanel at left.
	 */
	public LeftPanel(JFrame frame) {
		initialise();
		validateToMainFrame(frame);
	}

	/**
	 * Initialises LeftPanel with specified size, layout.
	 * @pre		None.
	 * @post	LeftPanel is set with specified size and layout.
	 */
	private void initialise() {
		//sets layout, size and GridBagLayout
		setMinimumSize(new Dimension(215,750));
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0,0,0,0);
		gbc.fill = GridBagConstraints.BOTH;	
		
		//sets background
		JLabel label = new JLabel(new ImageIcon(this.getClass().getResource("resource/bg-leftpanel.png"))); 
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(label, gbc);
	}

	/**
	 * Sets this JPanel to JFrame.
	 * @pre		JFrame where this JPanel will be set must exist.
	 * @param mainFrame	JFrame where this object will be set.
	 * @post	JFrame is set with this JPanel to its left.
	 */
	private void validateToMainFrame(JFrame mainFrame) {
		//setting GridBagConstraints
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		//set JPanel to JFrame
		mainFrame.getContentPane().add(this, gbc);
		mainFrame.validate();
	}
	
}