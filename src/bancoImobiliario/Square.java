package bancoImobiliario;

public class Square {
	private int index;
	private int type;
	
	public Square() {
		
	}
	public Square(int index) {
		this.index = index;
	}
	public Square(int index, int type) {
		this.index = index;
		this.type = type;
	}
	
	public int getIndex() {
		return index;
	}
	public int getType() {
		return type;
	}
}
