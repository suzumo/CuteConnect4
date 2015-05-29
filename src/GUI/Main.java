package GUI;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import Game.ConnectFourGame;

public class Main {

	public static void main(String[] args) {
		//System.out.println("Connect Four Game 2015");

		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run() 
			{
				try
				{
					for(LookAndFeelInfo theme : UIManager.getInstalledLookAndFeels())
					{
						if(theme.getName().equalsIgnoreCase("Nimbus"))
						{
							UIManager.setLookAndFeel(theme.getClassName());
							break;
						}
					}
					new ConnectFourGame();

				}
				catch (Exception e)
				{
					System.err.println(e.getMessage());
				}
			}
		});
	}

}
