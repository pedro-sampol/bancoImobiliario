package bancoImobiliario;

public class Company extends Local {
	int multiplier;
	
	public Company() {
		multiplier = 1;
	}
	
	public Company(int multiplier, int index, int type, int value) {
		super(index, type, value);
		this.multiplier = multiplier;
	}
	
	public int getMultiplier() {
		return multiplier;
	}
}
