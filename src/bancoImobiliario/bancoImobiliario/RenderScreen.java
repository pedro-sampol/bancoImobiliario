package bancoImobiliario;

import java.awt.*;

public class RenderScreen extends Canvas {
	
	private static final long serialVersionUID = 1L;
	private ScreenData sData;
	private Screen s;
	
	public RenderScreen () {
		sData = new ScreenData ();
		s = new Screen ();
	}
	
	public void gameScreen () {
		sData.getBS();
		s.printGame (sData);
	}

}
