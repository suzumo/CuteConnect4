package GUI;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import Game.ConnectFourGame;

public class Main {

	public static void main(String[] args) {
		System.out.println("connect four game 2015");
	
//		ConnectFourGame cfg = new ConnectFourGame();
//		cfg.setVsCPU();
////		cfg.setVsPlayer();
//		
//		// draw then update loop
//		while( true ){
//			cfg.update();
//			
//			try {
//				Thread.sleep(40);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			
//			cfg.draw();
//			
//		}
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
