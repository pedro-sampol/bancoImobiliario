package bancoImobiliario;

import java.awt.*;

public class RenderScreen extends Canvas {
	
	private static final long serialVersionUID = 1L;
	private ScreenData sData;
	private Screen s;
	
	public RenderScreen () {
		this.sData = new ScreenData ();
		this.s = new Screen ();
	}
	
	public void gameScreen (PlayerCtrl p, Commands commands) {
		sData.getBS();
		s.printGame (sData, p, commands);
	}
	
	public ScreenData getScreenData () {
		return this.sData;
	}

}
