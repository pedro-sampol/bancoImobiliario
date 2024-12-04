package bancoImobiliario;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class RenderScreen extends Canvas implements KeyListener {
	
	private static final long serialVersionUID = 1L;
	private Board board;
	private ScreenData sData;
	private Screen s;
	private int playerSelected;
	
	public RenderScreen () {
		sData = new ScreenData ();
		s = new Screen ();
		sData.frame.addKeyListener(this);
		board = new Board();
		playerSelected = 0;
	}
	
	public void gameScreen () {
		sData.getBS();
		s.printGame (sData, board, playerSelected);
	}
	
	public void keyTyped(KeyEvent e) {}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			sData.screenSelector = !sData.screenSelector;
		} else if (e.getKeyCode() == KeyEvent.VK_1) {
			playerSelected = 0;
		} else if (e.getKeyCode() == KeyEvent.VK_2) {
			playerSelected = 1;
		} else if (e.getKeyCode() == KeyEvent.VK_3) {
			playerSelected = 2;
		} else if (e.getKeyCode() == KeyEvent.VK_4) {
			playerSelected = 3;
		} else if (e.getKeyCode() == KeyEvent.VK_5) {
			playerSelected = 4;
		} else if (e.getKeyCode() == KeyEvent.VK_6) {
			playerSelected = 5;
		} 
	}

	public void keyReleased(KeyEvent e) {

	}

}
