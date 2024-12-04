package bancoImobiliario;

import java.awt.*;

public class Screen extends Canvas {
	
	private static final long serialVersionUID = 1L;

	public void printGame (ScreenData s, Board b, int i) {
		Graphics g;
	
		g = s.getImage().getGraphics ();				//acessa image
		g.setColor (new Color (0, 0, 0));
		g.fillRect (0, 0, s.getLength(), s.getHeight());
		
		/* renderizando o jogo */
		
		
		g.drawImage(s.getHub(), 0, 0, null);

		if (s.getScreen()) {
			g.drawImage(s.getMap(), 770, 0, null);
		} else if (!s.getScreen()) {
			g.drawImage(s.getProperty(), 770, 0, null);
		}
		
		if (i == 0) {
			g.drawImage(b.playerSprite(i), 0, 210, null);
		} else if (i == 1) {
			g.drawImage(b.playerSprite(i), 0, 210, null);
		} else if (i == 2) {
			g.drawImage(b.playerSprite(i), 0, 210, null);
		} else if (i == 3) {
			g.drawImage(b.playerSprite(i), 0, 210, null);
		} else if (i == 4) {
			g.drawImage(b.playerSprite(i), 0, 210, null);
		} else if (i == 5) {
			g.drawImage(b.playerSprite(i), 0, 210, null);
		}
		
		//g.fillOval(ALLBITS, ABORT, WIDTH, HEIGHT);
		
		/**/
		g.dispose ();
		g = s.bs.getDrawGraphics ();
		g.drawImage (s.getImage(), 0, 0, s.getLength(), s.getHeight(), null);
		s.bs.show ();
	}
}
