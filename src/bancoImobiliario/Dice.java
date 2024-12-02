package bancoImobiliario;

import java.util.Random;

public class Dice {
	boolean isEqual;
	int value1;
	int value2;
	
	public void rollDice() {
		Random random = new Random();
		
		value1 = random.nextInt(6) + 1;
		value2 = random.nextInt(6) + 1;
		if (value1 == value2) {
			isEqual = true;
		}
		else {
			isEqual = false;
		}
	}
	
	public boolean get_isEqual() {
		return isEqual;
	}
}
