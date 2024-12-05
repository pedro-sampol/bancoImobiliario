package bancoImobiliario;

import java.awt.image.BufferedImage;

public class Player {
	private int x,y;					// coordenadas
	private int position;				// indice no mapa
	private int money;					// dinheiro atual
	private Boolean inJail;				// esta preso
	private int timeInPrision;			// tempo em turnos preso
	private int getOutCards;			// quantidade cartas de sair da prisao
	private int noPlayTime;				// Player não pode jogar n rodadas
	private int repeatedDice;			// quantas vezes seguidas os dados foram iguais
	private BufferedImage sprite;		// sprite do personagem
	
	//cria o player com os valores iniciais basicos do jogo
	public Player(BufferedImage p, int x, int y) {
		this.x = x;
		this.y = y;
		this.position = 0;
		this.money = 200;
		this.inJail = Boolean.valueOf(false);
		this.timeInPrision =  0;
		this.getOutCards = 0;
		this.noPlayTime = 0;
		this.repeatedDice = 0;
		this.sprite = p;
	}
	
	//cria o player a partir de uma save do jogo, com valores passados pela linha
	public Player(String linha, BufferedImage p) {
		String info[];
		String separador = " ";
		
		info = linha.split(separador);
		
		this.x = Integer.valueOf(info[0]);
		this.y = Integer.valueOf(info[1]);
		this.position = Integer.valueOf(info[2]);
		this.money = Integer.valueOf(info[3]);
		this.inJail = Boolean.valueOf(info[4]);
		this.timeInPrision =  Integer.valueOf(info[5]);
		this.getOutCards = Integer.valueOf(info[6]);
		this.noPlayTime = Integer.valueOf(info[7]);
		this.repeatedDice = Integer.valueOf(info[8]);
		this.sprite = p;
	}
	
	//funcoes que retornam informações do estado atual de uma variavel do player
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public int getPosition() {
		return this.position;
	}
	public int getMoney() {
		return this.money;
	}
	public Boolean isInJail() {
		return this.inJail;
	}
	public int getTimeInPrision() {
		return this.timeInPrision;
	}
	public int getGetOutCards() {
		return this.getOutCards;
	}
	public int getNoPlayTime() {
		return this.noPlayTime;
	}	
	public BufferedImage getSprite() {
		return this.sprite;
	}
	public int getRepeatedDice() {
		return this.repeatedDice;
	}
	
	// verifica se o player tem dinheiro suficiente para uma compra
	// retorna true se sim, false se não
	public Boolean hasMoney(int valor) {
		if(this.money >= valor)
			return Boolean.valueOf(true);
		else
			return Boolean.valueOf(false);
	}
	
	// altera a quantidade de dinheiro que o player possui
	public void changeMoney(int valor) {
		this.money += valor;
	}
	
	//move o player num casas para frente
	//retorna true se o player passou ou parou na casa inicial, false se nao
	public Boolean move(int num) {
		Boolean retorno;
		
		//player passou ou parou no inicio
		if(this.position + num >= 36)
			retorno = Boolean.valueOf(true);
		else
			retorno = Boolean.valueOf(false);	
			
		//atualiza posicao do player (tabuleiro é circular com 36 casas)
		this.position = (this.money + num) % 36;
		return retorno;
	}
	
	//player recebe uma carta de fuga da prisao
	public void increaseGetOutCards() {
		this.getOutCards++;
	}
	
	//player saiu da prisao
	//se carta == true -> remove uma carta de fuga da prisao
	public void getOutOfPrision(Boolean carta) {
		if(carta.equals(true))
			this.getOutCards--;
		
		this.inJail = Boolean.valueOf(false);
	}
	
	//caso os dados sejam repetidos, aumenta a contagem de dados
	//retorna true se foram jogados dados repetidos 3 vezes seguidas em um turno
	public Boolean repeatedDice(Dice dado) {
		if(dado.isEqual) {
			if(this.repeatedDice == 2) {
				this.resetRepeatedDice();
				return true;
			}
			this.repeatedDice++;
		}
		return false;
	}
	
	//reseta o num de dados repetidos (usado no fim de um turno)
	public void resetRepeatedDice() {
		this.repeatedDice = 0;
	}
}