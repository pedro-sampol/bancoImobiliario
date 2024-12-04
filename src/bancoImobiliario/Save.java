package bancoImobiliario;

import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;

public class Save {
	
	public File save;
	public BufferedReader reader;
	
	public Save (){
		try {
			//cria um novo File caso nao exista
			this.save = new File("./save/save.txt");
			if(!save.exists())
				this.save.createNewFile();
			
			this.reader = new BufferedReader(new FileReader("./save/save.txt"));

			
		} catch (IOException e) { //verifica se nao ouve algum erro
			e.printStackTrace();
		}
		
	}
	public void saveGame(Player players[]) {
		try {
			//cria um FileWriter
			FileWriter writer = new FileWriter("./save/save.txt");
			
			//salva os players
			for(int i = 0; i < 6; i++) {
				writer.append(String.valueOf(players[i].getPosition() + " "));
				writer.append(String.valueOf(players[i].getMoney() + " "));
				writer.append(String.valueOf(players[i].isInJail() + " "));
				writer.append(String.valueOf(players[i].getTimeInPrision() + " "));
				writer.append(String.valueOf(players[i].getGetOutCards() + " "));
				writer.append(String.valueOf(players[i].getNoPlayTime() + " "));
				writer.append(String.valueOf(players[i].getRepeatedDice() + "\n"));
			}
			writer.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
