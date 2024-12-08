package bancoImobiliario;

import java.awt.*;

public class RenderScreen extends Canvas {
	
	private static final long serialVersionUID = 1L;	//controle
	private ScreenData screenData;						//informaçoes da tela
	private Screen screen;								//tela do jogo
	
	public RenderScreen () {
		screenData = new ScreenData ();
		screen = new Screen ();
	}
	
	/*************************************************************************************************/
	
	public void gameScreen (PlayerCtrl pCtrl, GameCtrl g, Dice dice, Board board) {
		screenData.getBS();
		screen.printGame (screenData, pCtrl, g, dice, board);
	}
	
	/*************************************************************************************************/
	
	public ScreenData getScreenData () {
		return this.screenData;
	}

}
