package bancoImobiliario;

import java.awt.*;

public class RenderScreen extends Canvas {
	
	private static final long serialVersionUID = 1L;
	private ScreenData sData;
	private Screen s;
	private Commands commands;
	
	public RenderScreen () {
		sData = new ScreenData ();
		s = new Screen ();
		commands = new Commands(sData);
	}
	
	public void gameScreen (PlayerCtrl p) {
		sData.getBS();
		s.printGame (sData, p, commands);
	}

}
