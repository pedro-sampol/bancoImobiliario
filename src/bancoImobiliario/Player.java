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
	private int noPlayTime;				// Player não pode jogar n rodadas
	private int repeatedDice;			// quantas vezes seguidas os dados foram iguais
	private BufferedImage sprite;		// sprite do personagem
	private int proprietiesOwned[] = new int[36];	// vetor indicando quais as propriedades que o player possui
	
	//cria o player com os valores iniciais basicos do jogo
	public Player(BufferedImage p, int index) {
		position = 0;
		pickCoords(index);
		money = 2150;
		bankrupt = false;
		numProprieties = 0;
		inPrision = false;
		timeInPrision =  0;
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
	
	// muda as coordenadas do player no tabuleiro
	public void setCoords(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	// altera o tempo que o jogador esta impedido de jogar
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
	
	public void pickCoords (int index) {
		int x, y;
		int walk = position % 9;
		
		// coordenadas para a fileira de baixo
		if (position <= 8) {
			x = 1632;
			y = 882;
			if (index == 1) {
				x += 37;
			} else if (index == 2) {
				y += 37;
			} else if (index == 3) {
				x += 37;
				y += 37;
			} else if (index == 4) {
				y += 74;
			} else if (index == 5) {
				x += 37;
				y += 74;
			}
			setCoords (x-(89*walk), y);	
		
		// coordenadas para a fileira da esquerda
		} else if (position <= 17) {
			x = 848;
			y = 862;
			if (index == 1) {
				y += 37;
			} else if (index == 2) {
				x -= 37;
			} else if (index == 3) {
				x -= 37;
				y += 37;
			} else if (index == 4) {
				x -= 74;
			} else if (index == 5) {
				x -= 74;
				y += 37;
			}
			setCoords (x, y-(89*walk));
			
		// coordenadas para a fileira de cima
		} else if (position <= 26) {
			x = 868;
			y = 78;
			if (index == 1) {
				x -= 37;
			} else if (index == 2) {
				y -= 37;
			} else if (index == 3) {
				x -= 37;
				y -= 37;
			} else if (index == 4) {
				y -= 74;
			} else if (index == 5) {
				x -= 37;
				y -= 74;
			}
			setCoords (x+(89*walk), y);	
			
		// coordenadas para a fileira da direita
		} else if (position <= 35) {
			x = 1652;
			y = 98;
			if (index == 1) {
				y -= 37;
			} else if (index == 2) {
				x += 37;
			} else if (index == 3) {
				x += 37;
				y -= 37;
			} else if (index == 4) {
				x += 74;
			} else if (index == 5) {
				x += 74;
				y -= 37;
			}
			setCoords (x, y+(89*walk));
		}
	}
	
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
		noPlayTime = Integer.valueOf(info[8]);
		repeatedDice = Integer.valueOf(info[9]);
		
		//atualiza o vetor que controla as propriedades do player
		for(int i = 0; i < numProprieties; i++)
			proprietiesOwned[i] = Integer.valueOf(info[10 + i]);
	}
}
