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
	
	public Player getPlayer (int index) {
		return p[index];
	}
	
	/*************************************************************************************************/
	
	
	//retorna true se o player tem 3 ou mais monopolios
	//retorna false se não
	public boolean playerWon(Player player, Board board) {
		
		//player ganhou o jogo por numero de monopolios ou monopolio de companhias
		if(player.getMonopolies() >= 3 || player.getNumCompanies() == 5)
			return true;
				
			return false;
	}
	
	//verifica se algum player ganhou o jogo
	//retorna o index do player que ganhou
	//retorna -1 se nenhum player ganhou
	public int winner(PlayerCtrl control, Board board) {
		int numInTheGame = 0;
		int indexInTheGame = 0;
		
		Player player;
		for(int i = 0; i < 6; i++) {
			player = control.getPlayer(i);
			
			//verifica se algum player ganhou por monopolio
			if(this.playerWon(player, board))
				return i;
			
			//verifica o numero de players que nao faliram
			if(!player.isBankrupt()) {
				numInTheGame++;
				indexInTheGame = i;
			}
		}
		
		//se apenas um player nao faliu retorna seu indice
		if(numInTheGame == 1)
			return indexInTheGame;
		
		//nenhum player ganhou
		return -1;
	}
	
}
