package bancoImobiliario;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Commands implements KeyListener {
	
	public Constants c;
	private int playerSelected;
	private int screenSelected;
	private int menuButton;
	
	public Commands (ScreenData sData) {
		c = new Constants();
		sData.frame.addKeyListener(this);
		playerSelected = 0;
		screenSelected = 0;
		menuButton = 0;
	}
	
	public void keyPressed(KeyEvent e) {		
		if (screenSelected == c.MAINMENU) {
			menuScreen (e);
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
	
	// permuta entre os 4 botoes do menu principal e
	// seleciona proxima tela
	public void menuScreen (KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (menuButton > 0) {
				menuButton--;
			}
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (menuButton < 3) {
				menuButton++;
			}
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (menuButton == 0 || menuButton == 1) {
				screenSelected = c.BOARD;	
			} else if (menuButton == 2) {
				screenSelected = c.RULES;
			} else if (menuButton == 3) {
				
			}
		}
	}
	
	// permuta entre os 6 jogadores no tabuleiro
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
	
	// gira entre o tabuleiro e os imoveis comprados pelos players
	public void swapSides (KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (screenSelected == c.BOARD) {
				screenSelected = c.PROPERTIES;
			} else if (screenSelected == c.PROPERTIES) {
				screenSelected = c.BOARD;
			}
		}
	}
	
	// funcoes de get
	public int getPlayerID () {
		return this.playerSelected;
	}
	public int getScreenID () {
		return this.screenSelected;
	}
	public int getMenuButton () {
		return this.menuButton;
	}
	
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
}