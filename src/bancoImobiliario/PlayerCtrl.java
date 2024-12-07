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
	
	
	//verifica se o player tem 3 ou mais monopolios
	//retorna true se sim, false se nao
	public boolean countMonopoly(Player player) {
		int total = 0;
		
		//monopolio laranja
		if(player.getOwned(1) && player.getOwned(3) && player.getOwned(34))
			total++;
		
		//monopolio azul
		if(player.getOwned(6) && player.getOwned(7) && player.getOwned(11))
			total++;
		
		//monopolio amarelo
		if(player.getOwned(13) && player.getOwned(15) && player.getOwned(16))
			total++;
		
		//monopolio vermelho
		if(player.getOwned(19) && player.getOwned(20) && player.getOwned(22))
			total++;
		
		//monopolio rosa
		if(player.getOwned(24) && player.getOwned(25) && player.getOwned(26) 
			&& player.getOwned(28))
			total++;
		
		//monopolio verde
		if(player.getOwned(30) && player.getOwned(31) && player.getOwned(32))
			total++;
		
		//numero de monopolios >= 3
		if(total >= 3)
			return true;
		return false;
	}
	
	//aumenta o tempo sem jogar do player em num
	public void increseNoPlayTime(Player player, int num) {
		player.setNoPlayTime(num + player.getNoPlayTime()); 
	}
	
	//diminui o numero de turnos sem jogar do player
	public void wasteTurn(Player player) {
		player.setNoPlayTime(player.getNoPlayTime() - 1);
	}
	
	public Player getPlayer (int index) {
		return p[index];
	}

}
