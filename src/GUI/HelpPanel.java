package GUI;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HelpPanel extends JPanel {
	
	/**
	 * Constructor for HelpPanel.
	 * @pre		None.
	 * @param frame JFrame for which this LeftPanel will be set in.
	 * @post	JFrame sets HelpPanel at center.
	 */
	public HelpPanel(JFrame frame) {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		this.setBackground(Color.BLACK);
		JLabel label = new JLabel(new ImageIcon(this.getClass().getResource("resource/help.png")));
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(label, gbc);
	}

}
