package bancoImobiliario;

import java.awt.*;

public class Screen extends Canvas {
	
	private static final long serialVersionUID = 1L;

	public void printGame (ScreenData s, PlayerCtrl pCtrl, Commands com) {
		Graphics g;
		Player p = pCtrl.getPlayer(com.getPlayerID());
	
		g = s.getImage().getGraphics ();				//acessa image
		g.setColor (new Color (0, 0, 0));
		g.fillRect (0, 0, s.getLength(), s.getHeight());
		
		/* renderizando o jogo */
		
		g.drawImage(s.getHub(), 0, 0, null);
		drawScreen(s, g, com);
		g.drawImage(p.getSprite(), 0, 210, null);
		
		//g.fillOval(ALLBITS, ABORT, WIDTH, HEIGHT);
		
		/**/
		g.dispose ();
		g = s.bs.getDrawGraphics ();
		g.drawImage (s.getImage(), 0, 0, s.getLength(), s.getHeight(), null);
		s.bs.show ();
	}
	
	public void drawScreen (ScreenData s, Graphics g, Commands com) {
		if (com.getScreenID() == com.c.BOARD) {
			g.drawImage(s.getMap(), 770, 0, null);
		} else if (com.getScreenID() == com.c.PROPERTIES) {
			g.drawImage(s.getProperty(), 770, 0, null);
		}
	}
}
