package bancoImobiliario;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Commands implements KeyListener {
	
	public Constants c;
	private int playerSelected;
	private int screenSelected;
	
	public Commands (ScreenData sData) {
		c = new Constants();
		sData.frame.addKeyListener(this);
		playerSelected = 0;
		screenSelected = c.BOARD;
	}
	
	public void keyPressed(KeyEvent e) {		
		if (screenSelected == c.MAINMENU) {
			
		} else if (screenSelected == c.RULES) {
			
		} else if (screenSelected == c.BOARD) {
			hubScreen(e);
			swapSides (e);
		} else if (screenSelected == c.PROPERTIES) {
			hubScreen(e);
			swapSides (e);
		} else if (screenSelected == c.PAUSE) {
			
		} else if (screenSelected == c.WIN) {
		
		}
	}
	
	public void hubScreen (KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_1) {
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
	
	public void swapSides (KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (screenSelected == c.BOARD) {
				screenSelected = c.PROPERTIES;
			} else if (screenSelected == c.PROPERTIES) {
				screenSelected = c.BOARD;
			}
		}
	}
	
	public int getPlayerID () {
		return this.playerSelected;
	}
	
	public int getScreenID () {
		return this.screenSelected;
	}
	
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
}