package bancoImobiliario;

public class Company extends Local {
	int multiplier;
	int price;
	
	public Company() {
		multiplier = 1;
	}
	
	public Company(int index, int type, int value, int multiplier, int price) {
		super(index, type, value);
		this.multiplier = multiplier;
		this.price = price;
	}
	
	public int getMultiplier() {
		return multiplier;
	}
}
