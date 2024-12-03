package bancoImobiliario;

public class Board {
	Square[] squares;
	Player[] players;
	Spritesheet players_sprite = new Spritesheet("../../res/personagens.png");
	
	public Board() {
		players = new Player[6];
		squares = new Square[36];
		
		for (int i=0; i<6; i++) {
			players[i] = new Player(players_sprite);
		}
		
	}
}
