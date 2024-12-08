package bancoImobiliario;

import java.awt.image.BufferedImage;

public class PlayerCtrl {
	
	Player[] p;								//vetor com todos os players do jogo
	
	private Spritesheet spritesheet;		//spritesheet com todos os players
	private BufferedImage spritePlayer;		//spritesheet de apenas um player
	
	private Constants c = new Constants();
	
	public PlayerCtrl () {
		p = new Player[6];
		spritesheet = new Spritesheet("/personagens.png");

		//cria os players
		for (int i = 0; i < 6; i++) {
			spritePlayer = spritesheet.getSprite(0+(i*520), 0, 520, 600);
			p[i] = new Player(spritePlayer, i);
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
	
	/*************************************************************************************************/
	
	
	//retorna true se o player tem 3 ou mais monopolios
	//retorna false se não
	public boolean playerGanhou(Player player, Board board) {
		
		Square aux;
		
		int monopoly = 0;
		
		int cores[] = {0, 0, 0, 0, 0, 0};
		
		//conta o numero de propriedades de cada cor
		for(int i = 0; i < player.getNumProprieties(); i++) {
			aux = board.getSquare(player.getOwned(i));
			
			if(aux.getType() == c.PROPERTY) {
				Property p = (Property) aux;
				cores[p.getColor()] ++;
			}
		}
		
		//verifica o numero de monopolios com base na quantidade de propriedades de cada cor
		for(int i = 0; i < 6; i++) {
			if(i == c.PURPLE && cores[i] == 4)
				monopoly++;
			else if(cores[i] == 3)
				monopoly++;
		}
		
		//player ganhou o jogo por numero de monopolios
		if(monopoly >= 3)
			return true;
		
		return false;
	}
	
}
