package bancoImobiliario;

import java.awt.image.BufferedImage;

public class PlayerCtrl {
	
	Player[] p;
	
	private Spritesheet spritesheet;
	private BufferedImage spritePlayer;
	
	public PlayerCtrl (boolean loadSave, Save save) {
		p = new Player[6];
		spritesheet = new Spritesheet("/personagens.png");
		
		//cria players a partir do arquivo de save
		if(loadSave)
			save.loadPLayers(p, spritesheet);
		else { //cria os players da forma padão
			for (int i = 0; i < 6; i++) {
				spritePlayer = spritesheet.getSprite(0+(i*520), 0, 520, 600);
				p[i] = new Player(spritePlayer, 0, 0);
			}
		}
	}
	
	public Player getPlayer (int index) {
		return p[index];
	}
	
}
