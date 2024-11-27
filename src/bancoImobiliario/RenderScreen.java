package bancoImobiliario;

import java.awt.*;
import java.awt.image.*;

public class RenderScreen {
	
	private final int LENGTH = 1760;		// eixo x
	private final int HEIGHT = 990;			// eixo y
	
	private BufferedImage image;
	
	private Spritesheet spritesheetMap;
	private BufferedImage map;
	
	public RenderScreen () {
		spritesheetMap = new Spritesheet ("/tabuleiro.png");
		map = spritesheetMap.getSpritesheet ();
		image = new BufferedImage (LENGTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	}
	
	public void gameScreen () {
		BufferStrategy bs = this.getBufferStrategy ();
		Graphics g;
		if (bs == null) {
			this.createBufferStrategy (3);		// melhora a performance de renderizacao
			return;
		}
		Graphics g;
		g = image.getGraphics ();
		g.setColor (new Color (255, 0, 155));
		g.fillRect (0, 0, LENGTH, HEIGHT);
		
		/* renderizando o jogo */
		
		g.drawImage(map, 770, 0, null);
		
		
		g.dispose ();
		g = bs.getDrawGraphics ();
		g.drawImage (image, 0, 0, LENGTH, HEIGHT, null);
		bs.show ();
	}

}
