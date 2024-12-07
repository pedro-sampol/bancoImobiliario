package bancoImobiliario;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Commands implements KeyListener {
	
	public Constants c;
	private int playerSelected;		//player selecionado atualmente
	private int menuButton;			//botao do menu
	private int pauseButton;		//botao de pause
	private int lastScreen;			//tela anterior
	private int screenSelected;		//tela selecionada atualmente

/*************************************************************************************************/
	
	public Commands (ScreenData sData) {
		c = new Constants();
		sData.frame.addKeyListener(this);
		playerSelected = 0;
		screenSelected = 0;
		menuButton = 0;
		pauseButton = 0;
	}
	
/*************************************************************************************************/
	
	public void keyPressed(KeyEvent e) {		
		if (screenSelected == c.MAINMENU) {
			menuScreen (e);
		} else if (screenSelected == c.RULES) {
			rulesScreen (e);
		} else if (screenSelected == c.BOARD) {
			hubScreen(e);
			swapSides (e);
		} else if (screenSelected == c.PROPERTIES) {
			hubScreen(e);
			swapSides (e);
		} else if (screenSelected == c.PAUSE) {
			pauseScreen(e);
		} else if (screenSelected == c.WIN) {
			winScreen(e);
		}
	}
	
/*************************************************************************************************/

	// permuta entre os 3 botoes do menu principal e
	// seleciona proxima tela
	public void menuScreen (KeyEvent e) {
		// movimento para cima
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (menuButton > 0) {
				menuButton--;
			}
		
		// movimento para baixo
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (menuButton < 2) {
				menuButton++;
			}
		
		// selecao de botao
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			lastScreen = screenSelected;
			if (menuButton == 0 || menuButton == 1) {
				screenSelected = c.BOARD;	
			} else if (menuButton == 2) {
				screenSelected = c.RULES;
			} 
		}
	}
	
/*************************************************************************************************/
	
	// tela de leitura, saida com esc
	public void rulesScreen (KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			screenSelected = lastScreen;
		}
	}
	
/*************************************************************************************************/
	
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
	
/*************************************************************************************************/

	// gira entre o tabuleiro e os imoveis comprados pelos players
	// e pausa do jogo
	public void swapSides (KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			lastScreen = screenSelected;
			if (screenSelected == c.BOARD) {
				screenSelected = c.PROPERTIES;
			} else if (screenSelected == c.PROPERTIES) {
				screenSelected = c.BOARD;
			}
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			lastScreen = screenSelected;
			screenSelected = c.PAUSE;
		}
	}
	
/*************************************************************************************************/
	
	public void pauseScreen (KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (pauseButton > 0) {
				pauseButton--;
			}
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (pauseButton < 2) {
				pauseButton++;
			}
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			lastScreen = screenSelected;
			if (pauseButton == 0) {
				screenSelected = c.BOARD;	
			} else if (pauseButton == 1) {
				screenSelected = c.RULES;
			} else  if (pauseButton == 2) {
				screenSelected = c.MAINMENU;
			} 
		}
	}
	
/*************************************************************************************************/

	public void winScreen (KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			screenSelected = c.MAINMENU;
		}
	}
	
/*************************************************************************************************/

	
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
	public int getPauseButton () {
		return this.pauseButton;
	}
	
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
}