package bancoImobiliario;

import java.io.FileWriter;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;

public class Save {
	
	public File save;
	private Constants c = new Constants();
	
	public Save (){
		try {
			//cria um novo File caso nao exista
			this.save = new File("./save/save.txt");
			if(!save.exists())
				this.save.createNewFile();
		} catch (IOException e) { //verifica se nao ouve algum erro
			e.printStackTrace();
		}
	}
	
	
	public void saveGame(Player players[], Square squares[]) {
		try {
			//cria um FileWriter
			FileWriter writer = new FileWriter("./save/save.txt");
			
			//salva os players
			for(int i = 0; i < 6; i++) {
				writer.append(String.valueOf(players[i].getX() + " "));
				writer.append(String.valueOf(players[i].getY() + " "));
				writer.append(String.valueOf(players[i].getPosition() + " "));
				writer.append(String.valueOf(players[i].getMoney() + " "));
				writer.append(String.valueOf(players[i].isBankrupt() + " "));
				writer.append(String.valueOf(players[i].getNumProprieties() + " "));
				writer.append(String.valueOf(players[i].isInPrision() + " "));
				writer.append(String.valueOf(players[i].getTimeInPrision() + " "));
				writer.append(String.valueOf(players[i].getGetOutCards() + " "));
				writer.append(String.valueOf(players[i].getNoPlayTime() + " "));
				writer.append(String.valueOf(players[i].getRepeatedDice() + " "));
				
				//grava o arquivo
				writer.flush();
				
				//salva o vetor que controla as propriedades que o player possui
				for(int j = 0; j < 36; j++)
					writer.append(String.valueOf(players[i].getOwned(j)));
				writer.append("\n");
				
				writer.flush();
			}
			
			//percorre todas as casas e verifica quais podem necessitar de alteração
			for(int i = 0; i< 36; i++) {
				
				//salva as companhias
				if(squares[i].getType() == c.COMPANY) {
					
					Company aux = (Company)squares[i]; //squares[i] é uma company
					
					writer.append(String.valueOf(aux.isBought() + " "));
					writer.append(String.valueOf(aux.getOwner() + "\n"));
				} else if(squares[i].getType() == c.PROPERTY) { //salva as propriedades
					Property aux = (Property)squares[i]; //squares[i] é uma property
					
					writer.append(String.valueOf(aux.isBought() + " "));
					writer.append(String.valueOf(aux.getOwner()  + " "));
					writer.append(String.valueOf(aux.getNHouses()  + "\n"));
					
				}
			}
			writer.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadPLayers (Player players[], Spritesheet sprite) {
		try {
			
			//abre a save com as informações dos players
			BufferedReader reader = new BufferedReader(new FileReader("./save/save.txt"));
			
			String linha; //linha com as informações de cada player
			BufferedImage spritePlayer; //sprite de cada player
			for(int i = 0; i < 6; i++) {
				linha = reader.readLine(); 										//obtem as informações do arquivo
				spritePlayer = sprite.getSprite(0+(i*520), 0, 520, 600);		//obtem a parte correta da sprite de cada player
				players[i] = new Player(linha, spritePlayer); 					//cria o player a partir dessas informações
			}
			
			reader.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadSquares (Square squares[]) {
		//abre a save com as informações dos players
		try {
			BufferedReader reader = new BufferedReader(new FileReader("./save/save.txt"));
			
			String linha; //linha com as informações
			for(int i = 0; i < 6; i++)
 					linha = reader.readLine(); //pula as informações dos players
			
			String info[]; //strings separadas com a info de cada linha
			String separador = " "; //separador das informações nas linhas
			
			//percorre todas as casas e verifica quais podem necessitar de alteração
			for(int i = 0; i< 36; i++) {
				if(squares[i].getType() == c.COMPANY) {
					linha = reader.readLine(); //linha com todas as infos dessa casa
					
					info = linha.split(separador); //infos separadas
					
					Company aux = (Company)squares[i]; //squares[i] é uma company
					
					//atualiza os valores
					aux.setBought(Boolean.valueOf(info[0]));
					aux.setOwner(Integer.valueOf(info[1]));
				} else if(squares[i].getType() == c.PROPERTY) {
					linha = reader.readLine();		//linha com todas as infos dessa casa
					
					info = linha.split(separador);	//infos separadas
					
					Property aux = (Property)squares[i]; //squares[i] é uma property
					
					//atualiza os valores
					aux.setBought(Boolean.valueOf(info[0]));
					aux.setOwner(Integer.valueOf(info[1]));
					aux.setNHouses(Integer.valueOf(info[2]));
					
				}
				
				reader.close();
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
