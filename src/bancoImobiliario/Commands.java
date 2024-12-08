package bancoImobiliario;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Commands implements KeyListener {
	
	public Constants c;
	public Dice dice;
	private GameCtrl gCtrl;
	private PlayerCtrl pCtrl;
	private Bank bank;
	private Board board;
	private Prision prision;
	
	boolean buyAction;				// evita que se compre mais de uma vez por clique
	boolean buyHouse;				// evita que se compre casas no mesmo turno de compra de um local

/*************************************************************************************************/
	
	public Commands (ScreenData sData, Dice dice, GameCtrl gCtrl, PlayerCtrl pCtrl, Bank bank, Board board, Prision prision) {
		c = new Constants();
		sData.frame.addKeyListener(this);
		this.dice = dice;
		this.gCtrl = gCtrl;
		this.pCtrl = pCtrl;
		this.bank = bank;
		this.board = board;
		this.prision = prision;
		buyAction = true;
		buyHouse = false;
	}
	
/*************************************************************************************************/
	
	public void keyPressed(KeyEvent e) {		
		if (gCtrl.getScreenSelected() == c.MAINMENU) {
			menuScreen (e);
		} else if (gCtrl.getScreenSelected() == c.RULES) {
			rulesScreen (e);
		} else if (gCtrl.getScreenSelected() == c.BOARD) {
			hubScreen(e);
			swapSides(e);
			buy(e);
			executeDice(e);
			endTurn(e);
		} else if (gCtrl.getScreenSelected() == c.PROPERTIES) {
			hubScreen(e);
			swapSides(e);
		} else if (gCtrl.getScreenSelected() == c.PAUSE) {
			pauseScreen(e);
		} else if (gCtrl.getScreenSelected() == c.WIN) {
			winScreen(e);
		}
	}
	
/*************************************************************************************************/

	// permuta entre os 3 botoes do menu principal e
	// seleciona proxima tela
	public void menuScreen (KeyEvent e) {
		// movimento para cima
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (gCtrl.getMenuButton() > 0) {
				gCtrl.setMenuButton(gCtrl.getMenuButton()-1);
			}
		
		// movimento para baixo
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (gCtrl.getMenuButton() < 2) {
				gCtrl.setMenuButton(gCtrl.getMenuButton()+1);;;
			}
		
		// selecao de botao
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			gCtrl.setLastScreen(gCtrl.getScreenSelected());
			if (gCtrl.getMenuButton() == 0 || gCtrl.getMenuButton() == 1) {
				gCtrl.setScreenSelected(c.BOARD);	
			} else if (gCtrl.getMenuButton() == 2) {
				gCtrl.setScreenSelected(c.RULES);
			} 
		}
	}
	
/*************************************************************************************************/
	
	// tela de leitura, saida com esc
	public void rulesScreen (KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			gCtrl.setScreenSelected(gCtrl.getLastScreen());
		}
	}
	
/*************************************************************************************************/
	
	// permuta entre os 6 jogadores no tabuleiro
	public void hubScreen (KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_1) {
			gCtrl.setPlayerSelected(0);
		} else if (e.getKeyCode() == KeyEvent.VK_2) {
			gCtrl.setPlayerSelected(1);
		} else if (e.getKeyCode() == KeyEvent.VK_3) {
			gCtrl.setPlayerSelected(2);
		} else if (e.getKeyCode() == KeyEvent.VK_4) {
			gCtrl.setPlayerSelected(3);
		} else if (e.getKeyCode() == KeyEvent.VK_5) {
			gCtrl.setPlayerSelected(4);
		} else if (e.getKeyCode() == KeyEvent.VK_6) {
			gCtrl.setPlayerSelected(5);
		} 
	}
	
/*************************************************************************************************/

	// gira entre o tabuleiro e os imoveis comprados pelos players
	// e pausa do jogo
	public void swapSides (KeyEvent e) {
		// gira entre imoveis e tabuleiro com espaco
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			gCtrl.setLastScreen(gCtrl.getScreenSelected());
			if (gCtrl.getScreenSelected() == c.BOARD) {
				gCtrl.setScreenSelected(c.PROPERTIES);
			} else if (gCtrl.getScreenSelected() == c.PROPERTIES) {
				gCtrl.setScreenSelected(c.BOARD);
			}
		
		// pausa com esc
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			gCtrl.setLastScreen(gCtrl.getScreenSelected());
			gCtrl.setScreenSelected(c.PAUSE);
		}
	}
	
/*************************************************************************************************/
	
	// compra imovel
	public void buy (KeyEvent e) {
		Player p = pCtrl.getPlayer(gCtrl.getPlayerIndex());
		Square s = board.getSquare(p.getPosition());
		
		if (e.getKeyCode() == KeyEvent.VK_C && buyAction) {
			// compra local ou casa no local
			if (s.getType() == c.PROPERTY) {
				Property property = (Property) s;
				if (gCtrl.getPlayerIndex() == property.getOwner() && buyHouse) {
					bank.buyHouse(p, property);
				} else {
					bank.buyLocal(p, board, gCtrl.getPlayerIndex());
					buyHouse = false;
				}
				
			// compra companhia
			} else if (s.getType() == c.COMPANY) {
				bank.buyLocal(p, board, gCtrl.getPlayerIndex());
				
			// compra saida da prisao
			} else if (p.isInPrision()) {
				prision.getOutwithBail(p, bank);
			}
			buyAction = false;
		}
	}
	
/*************************************************************************************************/
	
	// rola os dados e atualiza posicao do player
	public void executeDice (KeyEvent e) {
		Player p;
		Square s; 
		
		if (e.getKeyCode() == KeyEvent.VK_D) {
			if (gCtrl.diceUsed()) return;						// se o dado ja foi rodado retorna
			
			dice.rollDice();									// rola os dados
			p = pCtrl.getPlayer(gCtrl.getPlayerIndex());		// pega o player do turno atual
			p.move(dice.getTotalValue());						// soma a posicao no tabuleiro
			p.pickCoords(gCtrl.getPlayerIndex());				// atualiza as coordenadas
			
			s = board.getSquare(p.getPosition());
			if (s.getType() == c.PROPERTY) {
				//Property property = (Property) s;
			} else if (s.getType() == c.COMPANY) {
				//Company company = (Company) s;
			} else if (s.getType() == c.DESTINY) {
				Destiny destiny = (Destiny) s;
				destiny.randomize();
			}
			if (!dice.getIsEqual()) {							// se os dados tiveram valores iguais
				gCtrl.setDiceState(true);						// o player joga de novo
			}
		}
	}
	
/*************************************************************************************************/
	
	// rola os dados e atualiza posicao do player
	public void endTurn (KeyEvent e) {		
		Player next;
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			
			// se o player ja rolou o dado ele pode passar o turno
			if (gCtrl.diceUsed()) {
				gCtrl.setPlayerIndex((gCtrl.getPlayerIndex()+1)%6);
				next = pCtrl.getPlayer(gCtrl.getPlayerIndex());
				
				// verifica se o proximo player esta falido e passa pro proximo
				while (next.isBankrupt()) {
					gCtrl.setPlayerIndex((gCtrl.getPlayerIndex()+1)%6);
					next = pCtrl.getPlayer(gCtrl.getPlayerIndex());
				}

				gCtrl.setPlayerSelected(gCtrl.getPlayerIndex());
				gCtrl.setDiceState(false);
				buyHouse = true;
			}
		}
	}
	
/*************************************************************************************************/
	
	public void pauseScreen (KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (gCtrl.getPauseButton() > 0) {
				gCtrl.setPauseButton(gCtrl.getPauseButton()-1);
			}
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (gCtrl.getPauseButton() < 2) {
				gCtrl.setPauseButton(gCtrl.getPauseButton()+1);
			}
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			gCtrl.setLastScreen (gCtrl.getScreenSelected());
			if (gCtrl.getPauseButton() == 0) {
				gCtrl.setScreenSelected(c.BOARD);	
			} else if (gCtrl.getPauseButton() == 1) {
				gCtrl.setScreenSelected(c.RULES);
			} else  if (gCtrl.getPauseButton() == 2) {
				gCtrl.setScreenSelected(c.MAINMENU);
			} 
		}
	}
	
/*************************************************************************************************/

	public void winScreen (KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			gCtrl.setScreenSelected(c.MAINMENU);
		}
	}
	
/*************************************************************************************************/
	
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_C) {
			buyAction = true;
		}
	}
	
	
	public void keyTyped(KeyEvent e) {}
}