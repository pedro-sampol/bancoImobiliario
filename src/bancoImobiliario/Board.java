package bancoImobiliario;

public class Board {
	private Constants cnst = new Constants();
	private Square[] squares;
	
	public Board(boolean loadSave, Save save) {
		squares = new Square[36];
		
		squares[0] = new Square(0, cnst.CORNER);
		squares[1] = new Property(1, cnst.PROPERTY, cnst.ORANGE, 70, 10, 50, 150, 450, 625, 750, 100);
		squares[2] = new Destiny(2, cnst.DESTINY);
		squares[3] = new Property(3, cnst.PROPERTY, cnst.ORANGE, 80, 12, 60, 180, 500, 700, 900, 100);
		squares[4] = new Company(4, cnst.COMPANY, 100, 50, 200);
		squares[5] = new Destiny(5, cnst.DESTINY);
		squares[6] = new Property(6, cnst.PROPERTY, cnst.BLUE, 30, 4, 20, 60, 180, 320, 450, 50);
		squares[7] = new Property(7, cnst.PROPERTY, cnst.BLUE, 50, 6, 30, 90, 270, 400, 500, 50);
		squares[8] = new Company(8, cnst.COMPANY, 75, 40, 150);
		squares[9] = new Square(9, cnst.CORNER);
		squares[10] = new Square(10);
		squares[11] = new Property(11, cnst.PROPERTY, cnst.BLUE, 30, 2, 10, 30, 90, 160, 250, 50);
		squares[12] = new Destiny(12, cnst.DESTINY);
		squares[13] = new Property(13, cnst.PROPERTY, cnst.YELLOW, 100, 16, 80, 220, 600, 800, 1000, 100);
		squares[14] = new Company(14, cnst.COMPANY, 100, 50, 200);
		squares[15] = new Property(15, cnst.PROPERTY, cnst.YELLOW, 90, 14, 70, 200, 550, 750, 950, 100);
		squares[16] = new Property(16, cnst.PROPERTY, cnst.YELLOW, 90, 14, 70, 200, 550, 750, 950, 100);
		squares[17] = new Destiny(17, cnst.DESTINY);
		squares[18] = new Square(18, cnst.CORNER);
		squares[19] = new Property(19, cnst.PROPERTY, cnst.RED, 110, 18, 90, 250, 700, 875, 1050, 150);
		squares[20] = new Property(20, cnst.PROPERTY, cnst.RED, 110, 18, 90, 250, 700, 875, 1050, 150);
		squares[21] = new Company(21, cnst.COMPANY, 75, 40, 150);
		squares[22] = new Property(22, cnst.PROPERTY, cnst.RED, 120, 20, 100, 300, 750, 925, 1100, 150);
		squares[23] = new Destiny(23, cnst.DESTINY);
		squares[24] = new Property(24, cnst.PROPERTY, cnst.PURPLE, 150, 26, 130, 390, 900, 1100, 1275, 200);
		squares[25] = new Property(25, cnst.PROPERTY, cnst.PURPLE, 130, 22, 110, 330, 800, 975, 1150, 150);
		squares[26] = new Property(26, cnst.PROPERTY, cnst.PURPLE, 150, 26, 130, 390, 900, 1100, 1275, 200);
		squares[27] = new Square(27, cnst.CORNER);
		squares[28] = new Property(28, cnst.PROPERTY, cnst.PURPLE, 130, 22, 110, 330, 800, 975, 1150, 150);
		squares[29] = new Square(29);
		squares[30] = new Property(30, cnst.PROPERTY, cnst.GREEN, 50, 6, 30, 90, 270, 400, 500, 50);
		squares[31] = new Property(31, cnst.PROPERTY, cnst.GREEN, 60, 8, 40, 100, 300, 450, 600, 50);
		squares[32] = new Property(32, cnst.PROPERTY, cnst.GREEN, 60, 8, 40, 100, 300, 450, 600, 50);
		squares[33] = new Destiny(33, cnst.DESTINY);
		squares[34] = new Property(34, cnst.PROPERTY, cnst.ORANGE, 70, 10, 50, 150, 450, 625, 750, 100);
		squares[35] = new Company(35, cnst.COMPANY, 100, 50, 200);
		
		//atualiza os quadrados para retornar ao estado salvo na save
		if(loadSave)
			save.loadSquares(squares);
	}
	
	public Square getSquare(int index) {
		return squares[index];
	}

}
