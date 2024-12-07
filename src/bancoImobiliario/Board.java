package bancoImobiliario;

import java.awt.image.BufferedImage;

public class Board {
	private Constants cnst = new Constants();
	private Square[] squares;
	
	private Spritesheet spritesheet;
	private Spritesheet spritesheetBig;
	
	public Board(boolean loadSave, Save save) {
		BufferedImage sprite;
		BufferedImage spriteBig;
		spritesheet = new Spritesheet("/cartas.png");
		spritesheetBig = new Spritesheet("/cartasGrandes.png");
		squares = new Square[36];
		
		// inicializando casas
		squares[0] = new Square(0, cnst.CORNER);
		
		sprite = spritesheet.getSprite(198*1, 0, 198, 222);
		spriteBig = spritesheetBig.getSprite(297*1, 0, 297, 333);
		squares[1] = new Property(sprite, spriteBig, 1, cnst.PROPERTY, cnst.ORANGE, 70, 10, 50, 150, 450, 625, 750, 100);
		
		squares[2] = new Destiny(2, cnst.DESTINY);
		
		sprite = spritesheet.getSprite(198*2, 0, 198, 222);
		spriteBig = spritesheetBig.getSprite(297*2, 0, 297, 333);
		squares[3] = new Property(sprite, spriteBig, 3, cnst.PROPERTY, cnst.ORANGE, 80, 12, 60, 180, 500, 700, 900, 100);
		
		sprite = spritesheet.getSprite(198*19, 0, 198, 222);
		spriteBig = spritesheetBig.getSprite(297*19, 0, 297, 333);
		squares[4] = new Company(sprite, spriteBig, 4, cnst.COMPANY, 100, 50, 200);
		
		squares[5] = new Destiny(5, cnst.DESTINY);
		
		sprite = spritesheet.getSprite(198*3, 0, 198, 222);
		spriteBig = spritesheetBig.getSprite(297*3, 0, 297, 333);
		squares[6] = new Property(sprite, spriteBig, 6, cnst.PROPERTY, cnst.BLUE, 30, 4, 20, 60, 180, 320, 450, 50);
		
		sprite = spritesheet.getSprite(198*4, 0, 198, 222);
		spriteBig = spritesheetBig.getSprite(297*4, 0, 297, 333);
		squares[7] = new Property(sprite, spriteBig, 7, cnst.PROPERTY, cnst.BLUE, 50, 6, 30, 90, 270, 400, 500, 50);
		
		sprite = spritesheet.getSprite(198*20, 0, 198, 222);
		spriteBig = spritesheetBig.getSprite(297*20, 0, 297, 333);
		squares[8] = new Company(sprite, spriteBig, 8, cnst.COMPANY, 75, 40, 150);
		
		squares[9] = new Square(9, cnst.CORNER);
		
		squares[10] = new Square(10);
		
		sprite = spritesheet.getSprite(198*5, 0, 198, 222);
		spriteBig = spritesheetBig.getSprite(297*5, 0, 297, 333);
		squares[11] = new Property(sprite, spriteBig, 11, cnst.PROPERTY, cnst.BLUE, 30, 2, 10, 30, 90, 160, 250, 50);
		
		squares[12] = new Destiny(12, cnst.DESTINY);
		
		sprite = spritesheet.getSprite(198*6, 0, 198, 222);
		spriteBig = spritesheetBig.getSprite(297*6, 0, 297, 333);
		squares[13] = new Property(sprite, spriteBig, 13, cnst.PROPERTY, cnst.YELLOW, 100, 16, 80, 220, 600, 800, 1000, 100);
		
		sprite = spritesheet.getSprite(198*21, 0, 198, 222);
		spriteBig = spritesheetBig.getSprite(297*21, 0, 297, 333);
		squares[14] = new Company(sprite, spriteBig, 14, cnst.COMPANY, 100, 50, 200);

		sprite = spritesheet.getSprite(198*7, 0, 198, 222);
		spriteBig = spritesheetBig.getSprite(297*7, 0, 297, 333);
		squares[15] = new Property(sprite, spriteBig, 15, cnst.PROPERTY, cnst.YELLOW, 90, 14, 70, 200, 550, 750, 950, 100);
		
		sprite = spritesheet.getSprite(198*8, 0, 198, 222);
		spriteBig = spritesheetBig.getSprite(297*8, 0, 297, 333);
		squares[16] = new Property(sprite, spriteBig, 16, cnst.PROPERTY, cnst.YELLOW, 90, 14, 70, 200, 550, 750, 950, 100);
		
		squares[17] = new Destiny(17, cnst.DESTINY);
		
		squares[18] = new Square(18, cnst.CORNER);
		
		sprite = spritesheet.getSprite(198*9, 0, 198, 222);
		spriteBig = spritesheetBig.getSprite(297*9, 0, 297, 333);
		squares[19] = new Property(sprite, spriteBig, 19, cnst.PROPERTY, cnst.RED, 110, 18, 90, 250, 700, 875, 1050, 150);
		
		sprite = spritesheet.getSprite(198*10, 0, 198, 222);
		spriteBig = spritesheetBig.getSprite(297*10, 0, 297, 333);
		squares[20] = new Property(sprite, spriteBig, 20, cnst.PROPERTY, cnst.RED, 110, 18, 90, 250, 700, 875, 1050, 150);
		
		sprite = spritesheet.getSprite(198*22, 0, 198, 222);
		spriteBig = spritesheetBig.getSprite(297*22, 0, 297, 333);
		squares[21] = new Company(sprite, spriteBig, 21, cnst.COMPANY, 75, 40, 150);
		
		sprite = spritesheet.getSprite(198*11, 0, 198, 222);
		spriteBig = spritesheetBig.getSprite(297*11, 0, 297, 333);
		squares[22] = new Property(sprite, spriteBig, 22, cnst.PROPERTY, cnst.RED, 120, 20, 100, 300, 750, 925, 1100, 150);
		
		squares[23] = new Destiny(23, cnst.DESTINY);
		
		sprite = spritesheet.getSprite(198*12, 0, 198, 222);
		spriteBig = spritesheetBig.getSprite(297*12, 0, 297, 333);
		squares[24] = new Property(sprite, spriteBig, 24, cnst.PROPERTY, cnst.PURPLE, 150, 26, 130, 390, 900, 1100, 1275, 200);
		
		sprite = spritesheet.getSprite(198*13, 0, 198, 222);
		spriteBig = spritesheetBig.getSprite(297*13, 0, 297, 333);
		squares[25] = new Property(sprite, spriteBig, 25, cnst.PROPERTY, cnst.PURPLE, 130, 22, 110, 330, 800, 975, 1150, 150);
		
		sprite = spritesheet.getSprite(198*14, 0, 198, 222);
		spriteBig = spritesheetBig.getSprite(297*14, 0, 297, 333);
		squares[26] = new Property(sprite, spriteBig, 26, cnst.PROPERTY, cnst.PURPLE, 150, 26, 130, 390, 900, 1100, 1275, 200);
		
		squares[27] = new Square(27, cnst.CORNER);
		
		sprite = spritesheet.getSprite(198*15, 0, 198, 222);
		spriteBig = spritesheetBig.getSprite(297*15, 0, 297, 333);
		squares[28] = new Property(sprite, spriteBig, 28, cnst.PROPERTY, cnst.PURPLE, 130, 22, 110, 330, 800, 975, 1150, 150);
		
		squares[29] = new Square(29);
		
		sprite = spritesheet.getSprite(198*16, 0, 198, 222);
		spriteBig = spritesheetBig.getSprite(297*16, 0, 297, 333);
		squares[30] = new Property(sprite, spriteBig, 30, cnst.PROPERTY, cnst.GREEN, 50, 6, 30, 90, 270, 400, 500, 50);
		
		sprite = spritesheet.getSprite(198*17, 0, 198, 222);
		spriteBig = spritesheetBig.getSprite(297*17, 0, 297, 333);
		squares[31] = new Property(sprite, spriteBig, 31, cnst.PROPERTY, cnst.GREEN, 60, 8, 40, 100, 300, 450, 600, 50);
		
		sprite = spritesheet.getSprite(198*18, 0, 198, 222);
		spriteBig = spritesheetBig.getSprite(297*18, 0, 297, 333);
		squares[32] = new Property(sprite, spriteBig, 32, cnst.PROPERTY, cnst.GREEN, 60, 8, 40, 100, 300, 450, 600, 50);
		
		squares[33] = new Destiny(33, cnst.DESTINY);
		
		sprite = spritesheet.getSprite(198*0, 0, 198, 222);
		spriteBig = spritesheetBig.getSprite(297*0, 0, 297, 333);
		squares[34] = new Property(sprite, spriteBig, 34, cnst.PROPERTY, cnst.ORANGE, 70, 10, 50, 150, 450, 625, 750, 100);
		
		sprite = spritesheet.getSprite(198*23, 0, 198, 222);
		spriteBig = spritesheetBig.getSprite(297*23, 0, 297, 333);
		squares[35] = new Company(sprite, spriteBig, 35, cnst.COMPANY, 100, 50, 200);
		
		//atualiza os quadrados para retornar ao estado salvo na save
		if(loadSave) save.loadSquares(squares);
	}
	
	public Square getSquare(int index) {
		return squares[index];
	}

}
