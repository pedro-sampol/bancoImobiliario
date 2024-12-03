package bancoImobiliario;

public class Property extends Local {
	int color;
	int n_houses;
	int mortgage;	// hipoteca
	int[] rent = new int[6];
	
	public Property(int index, int type, int value, int color, int mortgage, int c0, int c1, int c2, int c3, int c4, int c5) {
		super(index, type, value);
		this.color = color;
		n_houses = 0;
		rent[0] = c0;
		rent[1] = c1;
		rent[2] = c2;
		rent[3] = c3;
		rent[4] = c4;
		rent[5] = c5;
		this.mortgage = mortgage;
	}
}
