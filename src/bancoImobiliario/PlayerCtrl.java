package bancoImobiliario;

import java.awt.image.BufferedImage;

public class PlayerCtrl {
	
	Player[] p;
	
	private Spritesheet spritesheet;
	private BufferedImage spritePlayer;
	
	public PlayerCtrl () {
		p = new Player[6];
		spritesheet = new Spritesheet("/personagens.png");
		
		for (int i = 0; i < 6; i++) {
			spritePlayer = spritesheet.getSprite(0+(i*520), 0, 520, 600);
			p[i] = new Player(spritePlayer, 0, 0);
		}
	}
	
	public BufferedImage playerSprite (int i) {
		return p[i].getSprite();
	}
	
	public int playerX (int i) {
		return p[i].getX();
	}
	
	public int playerY (int i) {
		return p[i].getY();
	}
	
}