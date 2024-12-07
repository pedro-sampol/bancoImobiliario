package bancoImobiliario;

public class Local extends Square {
	private boolean bought;        // local já possui dono
	private int owner;		       // -1 se não tiver
	private int value;			   // valor de compra e venda
	private int mortgageValue;     // valor da hipoteca 
	private boolean mortgage;      // se esse lugar está hipotecado
	
	public Local() {
		owner = -1;
	}
	public Local(int index, int type, int value) {
		super(index, type);
		owner = -1;
		this.value = value + 50;
		mortgageValue = value;
		mortgage = false;
	}
	
	/*************************************************************************************************/
	
	public void setOwner(int owner) {
		this.owner = owner;
	}
	public void setBought(boolean bought) {
		this.bought = bought;
	}
	public void setMortgage(boolean mortgage) {
		this.mortgage = mortgage;
	}
	
	/*************************************************************************************************/
	
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
	public int getMortgageValue() {
		return mortgageValue;
	}
	public int getValue() {
		return value;
	}
}
