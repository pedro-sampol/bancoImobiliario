package bancoImobiliario;

public class Local extends Square {
	private boolean bought;
	private int owner;		// -1 se não tiver
	private int value;
	
	public Local() {
		owner = -1;
	}
	public Local(int index, int type, int value) {
		super(index, type);
		owner = -1;
		this.value = value;
	}
	
	public void setOwner(int owner) {
		this.owner = owner;
	}
	public void setBought(boolean bought) {
		this.bought = bought;
	}
	
	public boolean isBought() {
		return bought;
	}
	public int getOwner() {
		return owner;
	}
	public int getValue() {
		return value;
	}
}
