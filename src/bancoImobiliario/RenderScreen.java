package bancoImobiliario;

import java.awt.*;
import java.awt.image.*;

public class RenderScreen extends Canvas {
	
	private ScreenData sData;
	private Screen s;
	
	public RenderScreen () {
		sData = new ScreenData ();
	}
	
	public void gameScreen () {
		BufferedImage image = sData.getImage();
		BufferedImage map = sData.getMap();
		int h = sData.getHeight();
		int l = sData.getLength();
		s.printGame (image, map, l, h);
	}

}
