package bancoImobiliario;

import java.util.Random;

public class Destiny extends Square {
	
	String texts[] = new String[12];												//textos que vao aparecer na tela
	int valores[] = {-150, -100, -75, -50, -30, -15, 150, 100, 75, 50, 30, 15};		//valor que deve ser pago
	
	public Destiny(int index, int type) {
		super(index, type);
		texts[0] = new String("Pague 150");
		texts[1] = new String("Pague 100");
		texts[2] = new String("Pague 75");
		texts[3] = new String("Pague 50");
		texts[4] = new String("Pague 30");
		texts[5] = new String("Pague 15");
		texts[6] = new String("Pague 150");
		texts[7] = new String("Pague 100");
		texts[8] = new String("Pague 75");
		texts[9] = new String("Pague 50");
		texts[10] = new String("Pague 30");
		texts[11] = new String("Pague 15");

	}
	
	/*************************************************************************************************/
	
	public int getRandomDestiny() {
		Random random = new Random();
		
		return random.nextInt(6) + 1;
	}
}