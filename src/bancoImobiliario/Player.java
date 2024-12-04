package bancoImobiliario;


import java.io.File;
import java.util.Scanner;
import java.io.IOException;

public class Player {
	private int position;
	private int money;
	private Boolean inJail;
	private int timeInPrision;
	private int getOutCards;
	private int noPlayTime;
	private int repeatedDice;
	private Spritesheet sprite;
	
	public Player() {
		this.position = 0;
		this.money = 200;
		this.inJail = Boolean.valueOf(false);
		this.timeInPrision =  0;
		this.getOutCards = 0;
		this.noPlayTime = 0;
		this.repeatedDice = 0;
	}
	
	public Player(String linha) {
		String info[];
		String separador = " ";
		
		info = linha.split(separador);
			
		this.position = Integer.valueOf(info[0]);
		this.money = Integer.valueOf(info[1]);
		this.inJail = Boolean.valueOf(info[2]);
		this.timeInPrision =  Integer.valueOf(info[3]);
		this.getOutCards = Integer.valueOf(info[4]);
		this.noPlayTime = Integer.valueOf(info[5]);
		this.repeatedDice = Integer.valueOf(info[6]);
	}
	
		//funcoes que retornam informações do estado atual de uma variavel do player
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
	public Spritesheet getSprite() {
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