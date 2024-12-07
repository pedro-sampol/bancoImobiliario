package bancoImobiliario;

public class Square {
	private int index;	//indice no tabuleiro
	private int type;	//tipo de casa (company, property, destiny, outro)
	
	public Square() {
		
	}
	public Square(int index) {
		this.index = index;
	}
	public Square(int index, int type) {
		this.index = index;
		this.type = type;
	}
	
	/*************************************************************************************************/
	
	public int getIndex() {
		return index;
	}
	public int getType() {
		return type;
	}
}
