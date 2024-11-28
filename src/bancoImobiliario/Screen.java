package bancoImobiliario;

import java.awt.*;
import java.awt.image.*;

public class Screen extends Canvas{
	
	public void printGame (BufferedImage image, BufferedImage map, int l, int h) {
		BufferStrategy bs = this.getBufferStrategy ();
		Graphics g;
		
		if (bs == null) {
			this.createBufferStrategy (3);		//performance de render
			return;
		}
	
		g = image.getGraphics ();				//acessa image
		g.setColor (new Color (255, 0, 155));
		g.fillRect (0, 0, l, h);
		
		/* renderizando o jogo */
		
		g.drawImage(map, 770, 0, null);
		
		/**/
		g.dispose ();
		g = bs.getDrawGraphics ();
		g.drawImage (image, 0, 0, l, h, null);
		bs.show ();
	}
}
