package bancoImobiliario;

import java.util.Random;

public class Destiny extends Square {
	public Destiny(int index, int type) {
		super(index, type);
	}
	
	public int getRandomDestiny() {
		Random random = new Random();
		return random.nextInt(6) + 1;
	}
}
