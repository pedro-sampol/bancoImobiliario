package bancoImobiliario;

import java.awt.*;

public class RenderScreen extends Canvas {
	
	private static final long serialVersionUID = 1L;
	private ScreenData screenData;
	private Screen screen;
	
	public RenderScreen () {
		screenData = new ScreenData ();
		screen = new Screen ();
	}
	
	public void gameScreen (PlayerCtrl pCtrl, Commands commands) {
		screenData.getBS();
		screen.printGame (screenData, pCtrl, commands);
	}
	
	public ScreenData getScreenData () {
		return this.screenData;
	}

}
