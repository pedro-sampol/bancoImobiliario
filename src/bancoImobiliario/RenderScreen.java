package bancoImobiliario;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class RenderScreen extends Canvas implements KeyListener {
	
	private static final long serialVersionUID = 1L;
	private ScreenData sData;
	private Screen s;
	
	public RenderScreen () {
		sData = new ScreenData ();
		s = new Screen ();
		sData.frame.addKeyListener(this);
	}
	
	public void gameScreen () {
		sData.getBS();
		s.printGame (sData);
	}
	
	public void keyTyped(KeyEvent e) {}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			sData.screenSelector = !sData.screenSelector;
		}

	}

	public void keyReleased(KeyEvent e) {

	}



}
