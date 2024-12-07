package bancoImobiliario;

public class Local extends Square {
	private boolean bought;
	private int owner;		// -1 se não tiver
	private int value;
	private boolean mortgage;
	
	public Local() {
		owner = -1;
	}
	public Local(int index, int type, int value) {
		super(index, type);
		owner = -1;
		this.value = value;
		mortgage = false;
	}
	
	public void setOwner(int owner) {
		this.owner = owner;
	}
	public void setBought(boolean bought) {
		this.bought = bought;
	}
	public void setMortgage(boolean mortgage) {
		this.mortgage = mortgage;
	}
	
	public boolean isBought() {
		return bought;
	}
	public boolean isMortgaged() {
		return mortgage;
	}
	public int getOwner() {
		return owner;
	}
	public boolean getMortgage() {
		return mortgage;
	}
	public int getValue() {
		return value;
	}
}
