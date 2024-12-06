package bancoImobiliario;

import java.util.Random;

public class Dice {
	private boolean isEqual;
	private int value1;
	private int value2;
	
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
	
	public boolean getIsEqual() {
		return isEqual;
	}
	public int getValue1() {
		return value1;
	}
	public int getValue2() {
		return value2;
	}
	public int getTotalValue() {
		return value1 + value2;
	}
}
