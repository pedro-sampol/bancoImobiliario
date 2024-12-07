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
	
	
	public void saveGame(PlayerCtrl control, Board board) {
		try {
			//cria um FileWriter
			FileWriter writer = new FileWriter("./save/save.txt");
			
			Player player;
			
			//salva os players
			for(int i = 0; i < 6; i++) {
				player = control.getPlayer(i);
				writer.append(String.valueOf(player.getX() + " "));
				writer.append(String.valueOf(player.getY() + " "));
				writer.append(String.valueOf(player.getPosition() + " "));
				writer.append(String.valueOf(player.getMoney() + " "));
				writer.append(String.valueOf(player.isBankrupt() + " "));
				writer.append(String.valueOf(player.getNumProprieties() + " "));
				writer.append(String.valueOf(player.isInPrision() + " "));
				writer.append(String.valueOf(player.getTimeInPrision() + " "));
				writer.append(String.valueOf(player.getGetOutCards() + " "));
				writer.append(String.valueOf(player.getNoPlayTime() + " "));
				writer.append(String.valueOf(player.getRepeatedDice() + " "));
				
				//grava o arquivo
				writer.flush();
				
				//salva o vetor que controla as propriedades que o player possui
				for(int j = 0; j < player.getNumProprieties(); j++)
					writer.append(String.valueOf(player.getOwned(j)) + ' ');
				writer.append("\n");
				
				writer.flush();
			}
			
			Square square;
			
			//percorre todas as casas e verifica quais podem necessitar de alteração
			for(int i = 0; i < 36; i++) {
				square = board.getSquare(i);
				
				//salva as companhias
				if(square.getType() == c.COMPANY) {
					
					Company aux = (Company)square; //squares[i] é uma company
					
					writer.append(String.valueOf(aux.isBought() + " "));
					writer.append(String.valueOf(aux.getOwner() + "\n"));
				} else if(square.getType() == c.PROPERTY) { //salva as propriedades
					Property aux = (Property)square; //squares[i] é uma property
					
					writer.append(String.valueOf(aux.isBought() + " "));
					writer.append(String.valueOf(aux.getOwner()  + " "));
					writer.append(String.valueOf(aux.getNHouses()  + "\n"));
					
				}
				
				writer.flush();
			}
			writer.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//realiza o load do player a partir do arquivo save
	public void loadPlayers (PlayerCtrl control) {
		try {
			
			//abre a save com as informações dos players
			BufferedReader reader = new BufferedReader(new FileReader("./save/save.txt"));
			
			String linha; //linha com as informações de cada player
			for(int i = 0; i < 6; i++) {
				linha = reader.readLine(); 										//obtem as informações do arquivo
				control.getPlayer(i).updateLoad(linha); 					//cria o player a partir dessas informações
			}
			
			reader.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadSquares (Board board) {
		//abre a save com as informações dos players
		try {
			BufferedReader reader = new BufferedReader(new FileReader("./save/save.txt"));
			
			String linha; //linha com as informações
			for(int i = 0; i < 6; i++)
 					linha = reader.readLine(); //pula as informações dos players
			
			String info[]; //strings separadas com a info de cada linha
			String separador = " "; //separador das informações nas linhas
			
			Square square;
			//percorre todas as casas e verifica quais podem necessitar de alteração
			for(int i = 0; i< 36; i++) {
				square = board.getSquare(i);
				if(square.getType() == c.COMPANY) {
					linha = reader.readLine(); //linha com todas as infos dessa casa
					
					info = linha.split(separador); //infos separadas
					
					Company aux = (Company)square; //squares[i] é uma company
					
					//atualiza os valores
					aux.setBought(Boolean.valueOf(info[0]));
					aux.setOwner(Integer.valueOf(info[1]));
				} else if(square.getType() == c.PROPERTY) {
					linha = reader.readLine();		//linha com todas as infos dessa casa
					
					info = linha.split(separador);	//infos separadas
					
					Property aux = (Property)square; //squares[i] é uma property
					
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
