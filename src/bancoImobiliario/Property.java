package bancoImobiliario;

public class Property extends Local {
	private int color;
	private int n_houses;
	private int house_price;
	private int[] rent = new int[6];
	
	public Property(int index, int type, int value, int color, int c0, int c1, int c2, int c3, int c4, int c5, int house_price) {
		super(index, type, value);
		this.color = color;
		this.house_price = house_price;
		n_houses = 0;
		rent[0] = c0;
		rent[1] = c1;
		rent[2] = c2;
		rent[3] = c3;
		rent[4] = c4;
		rent[5] = c5;
	}
	
	public int getColor() {
		return color;
	}
	public int getNHouses() {
		return n_houses;
	}
	public int getHousePrice() {
		return house_price;
	}
	public int getRent(int index) {
		return rent[index];
	}
	
	public void setNHouses(int n) {
		this.n_houses = n;
	}
}
