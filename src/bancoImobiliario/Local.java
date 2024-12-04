package bancoImobiliario;

public class Local extends Square {
	boolean bought;
	int owner;		// -1 se não tiver
	int value;
	
	
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
}
