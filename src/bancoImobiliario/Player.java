package bancoImobiliario;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;

public class Player {
	private int x,y;					// coordenadas
	private int position;				// indice no mapa
	private int money;					// dinheiro atual
	private boolean bankrupt;			// player faliu ou nao
	private int numProprieties;			// numero de propriedades/companhias que o player possui
	private boolean inPrision;			// esta preso
	private int timeInPrision;			// tempo em turnos preso
	private int getOutCards;			// quantidade cartas de sair da prisao
	private int noPlayTime;				// Player não pode jogar n rodadas
	private int repeatedDice;			// quantas vezes seguidas os dados foram iguais
	private BufferedImage sprite;		// sprite do personagem
	private int proprietiesOwned[] = new int[36];	// vetor indicando quais as propriedades que o player possui
	
	//cria o player com os valores iniciais basicos do jogo
	public Player(BufferedImage p, int x, int y) {
		this.x = x;
		this.y = y;
		position = 0;
		money = 200;
		bankrupt = false;
		numProprieties = 0;
		inPrision = false;
		timeInPrision =  0;
		getOutCards = 0;
		noPlayTime = 0;
		repeatedDice = 0;
		sprite = p;
	}
	
	/*************************************************************************************************/
	
	//funcoes que retornam informações do estado atual de uma variavel do player
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getPosition() {
		return position;
	}
	public int getMoney() {
		return money;
	}
	public boolean isBankrupt() {
		return bankrupt;
	}
	public int getNumProprieties() {
		return numProprieties;
	}
	
	public boolean isInPrision() {
		return inPrision;
	}
	public int getTimeInPrision() {
		return timeInPrision;
	}
	public int getGetOutCards() {
		return getOutCards;
	}
	public int getNoPlayTime() {
		return noPlayTime;
	}	
	public BufferedImage getSprite() {
		return sprite;
	}
	public int getRepeatedDice() {
		return repeatedDice;
	}
	public int getOwned(int index) {
		if(index > numProprieties)
			return -1;
		return proprietiesOwned[index];
	}
	
	/*************************************************************************************************/
	
	//funcoes que alteram diretamente as variaveis do player
	public void setNoPlayTime(int time) {
		noPlayTime = time;
	}
	public void setBankrupt(boolean state) {
		bankrupt = state;
	}
	public void setMoney(int valor) {
		money = valor;
	}
	
	public void setInPrision(boolean bool) {
		inPrision = bool;
	}
	public void setTimeInPrision(int time) {
		timeInPrision = time;
	}
	public void setPosition(int id) {
		position = id;
	}
	public void setGetOutCards(int count) {
		getOutCards = count;
	}
	
	/*************************************************************************************************/
	
	//player recebe um novo lugar
	public void newLocal(int index) {
		//salva o index da nova prorpiedade
		proprietiesOwned[numProprieties] = index;
		
		//aumenta o num de propriedades que o player possui
		numProprieties++;
	}
	
	//player perde posse de um local com indice = index
	public void removeLocal(int index) {
		boolean achou = false;
		for(int i = 0; i < numProprieties; i++) {
			
			//encontra o local a ser retirado
			if(proprietiesOwned[i] == index)
				achou = true;
			
			//retira o local e puxa o resto do vetor um para frente
			if(achou)
				proprietiesOwned[i] = proprietiesOwned[i + 1];
		}
		
		//se encontrou o local é diminuido um do num de propriedades que o player possui
		if(achou)
			numProprieties--;
	}
	
	/*************************************************************************************************/
	
	// verifica se o player tem dinheiro suficiente para uma compra
	// retorna true se sim, false se não
	public boolean hasMoney(int valor) {
		if(money >= valor)
			return true;
		else
			return false;
	}
	
	/*************************************************************************************************/
	
	//move o player num casas para frente
	//retorna true se o player passou ou parou na casa inicial, false se nao
	public boolean move(int num) {
		boolean retorno;
		
		//player passou ou parou no inicio
		if(position + num >= 36)
			retorno = true;
		else
			retorno = false;	
			
		int pos_ant = position;
		//atualiza posicao do player (tabuleiro é circular com 36 casas)
		position = (position + num) % 36;
		
		//player parou ou passou do inicio
		if(pos_ant + num != position)
			money += 200;
		
		return retorno;
	}
	
	/*************************************************************************************************/
	

	
	//caso os dados sejam repetidos, aumenta a contagem de dados
	//retorna true se foram jogados dados repetidos 3 vezes seguidas em um turno
	public Boolean repeatedDice(Dice dado) {
		if(dado.getIsEqual()) {
			if(repeatedDice == 2) {
				resetRepeatedDice();
				return true;
			}
			repeatedDice++;
		}
		return false;
	}
	
	//reseta o num de dados repetidos (usado no fim de um turno)
	public void resetRepeatedDice() {
		repeatedDice = 0;
	}
	
	/*************************************************************************************************/
	
	//atualiza o player ppara  as iformações passadas em linha
	public void updateLoad(String linha) {
		String info[];
		String separador = " ";
		
		info = linha.split(separador);
		
		x = Integer.valueOf(info[0]);
		y = Integer.valueOf(info[1]);
		position = Integer.valueOf(info[2]);
		money = Integer.valueOf(info[3]);
		bankrupt = Boolean.valueOf(info[4]);
		numProprieties = Integer.valueOf(info[5]);
		inPrision = Boolean.valueOf(info[6]);
		timeInPrision =  Integer.valueOf(info[7]);
		getOutCards = Integer.valueOf(info[8]);
		noPlayTime = Integer.valueOf(info[9]);
		repeatedDice = Integer.valueOf(info[10]);
		
		//atualiza o vetor que controla as propriedades do player
		for(int i = 0; i < numProprieties; i++)
			proprietiesOwned[i] = Integer.valueOf(info[11 + i]);
	}
}
