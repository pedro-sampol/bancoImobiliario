package bancoImobiliario;

import java.awt.image.BufferedImage;

public class PlayerCtrl {
	
	Player[] p;								//vetor com todos os players do jogo
	
	private Spritesheet spritesheet;		//spritesheet com todos os players
	private BufferedImage spritePlayer;		//spritesheet de apenas um player
	
	public PlayerCtrl () {
		p = new Player[6];
		spritesheet = new Spritesheet("/personagens.png");
	
		//cria os players
		for (int i = 0; i < 6; i++) {
			spritePlayer = spritesheet.getSprite(0+(i*520), 0, 520, 600);
			p[i] = new Player(spritePlayer, 0, 0);
		}
	}
	
	/*************************************************************************************************/
	
	//aumenta o tempo sem jogar do player em num
	public void increseNoPlayTime(Player player, int num) {
		player.setNoPlayTime(num + player.getNoPlayTime()); 
	}
	
	//diminui o numero de turnos sem jogar do player
	public void wasteTurn(Player player) {
		player.setNoPlayTime(player.getNoPlayTime() - 1);
	}
	
	/*************************************************************************************************/
	
	public Player getPlayer (int index) {
		return p[index];
	}

}
