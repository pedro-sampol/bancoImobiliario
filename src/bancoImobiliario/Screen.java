package bancoImobiliario;

import java.awt.*;
import java.awt.image.*;

public class Screen extends Canvas{
	
	private static final long serialVersionUID = 1L;

	public void printGame (ScreenData s) {
		Graphics g;
	
		g = s.getImage().getGraphics ();				//acessa image
		g.setColor (new Color (255, 0, 155));
		g.fillRect (0, 0, s.getLength(), s.getHeight());
		
		/* renderizando o jogo */
		
		//g.drawImage(s.getMap(), 770, 0, null);
		g.drawImage(s.getProperty(), 770, 0, null);
		
		//g.fillOval(ALLBITS, ABORT, WIDTH, HEIGHT);
		
		/**/
		g.dispose ();
		g = s.bs.getDrawGraphics ();
		g.drawImage (s.getImage(), 0, 0, s.getLength(), s.getHeight(), null);
		s.bs.show ();
	}
}
