package bancoImobiliario;

import java.awt.*;

public class Screen extends Canvas {
	
	private static final long serialVersionUID = 1L;

	public void printGame (ScreenData s, PlayerCtrl p, Commands commands) {
		Graphics g;
	
		g = s.getImage().getGraphics ();				//acessa image
		
		/* renderizando o jogo */
		
		g.drawImage(s.getHub(), 0, 0, null);
		drawScreen(s, g, p, commands);
				
		
		
		/**/
		g.dispose ();
		g = s.bs.getDrawGraphics ();
		g.drawImage (s.getImage(), 0, 0, s.getLength(), s.getHeight(), null);
		s.bs.show ();
	}
	
	public void drawScreen (ScreenData s, Graphics g, PlayerCtrl p, Commands commands) {
		if (commands.getScreenID() == commands.c.MAINMENU) {
			g.setColor (new Color (0, 0, 0));
			g.fillRect (0, 0, s.getLength(), s.getHeight());
			g.setColor (new Color (255, 255, 255));
			if (commands.getMenuButton() == 0) {
				g.fillOval(400, 100, 30, 30);
			} else if (commands.getMenuButton() == 1) {
				g.fillOval(400, 200, 30, 30);
			} else if (commands.getMenuButton() == 2) {
				g.fillOval(400, 300, 30, 30);
			} else if (commands.getMenuButton() == 3) {
				g.fillOval(400, 400, 30, 30);
			}
		} else if (commands.getScreenID() == commands.c.BOARD) {
			drawHub(s, g, p, commands);
			g.drawImage(s.getMap(), 770, 0, null);
		} else if (commands.getScreenID() == commands.c.PROPERTIES) {
			drawHub(s, g, p, commands);
			g.drawImage(s.getProperty(), 770, 0, null);
		}
	}
	
	public void drawHub (ScreenData s, Graphics g, PlayerCtrl p, Commands commands) {
		if (commands.getPlayerID() == 0) {
			g.drawImage(p.playerSprite(commands.getPlayerID()), 0, 210, null);
		} else if (commands.getPlayerID() == 1) {
			g.drawImage(p.playerSprite(commands.getPlayerID()), 0, 210, null);
		} else if (commands.getPlayerID() == 2) {
			g.drawImage(p.playerSprite(commands.getPlayerID()), 0, 210, null);
		} else if (commands.getPlayerID() == 3) {
			g.drawImage(p.playerSprite(commands.getPlayerID()), 0, 210, null);
		} else if (commands.getPlayerID() == 4) {
			g.drawImage(p.playerSprite(commands.getPlayerID()), 0, 210, null);
		} else if (commands.getPlayerID() == 5) {
			g.drawImage(p.playerSprite(commands.getPlayerID()), 0, 210, null);
		}
	}
	
}
