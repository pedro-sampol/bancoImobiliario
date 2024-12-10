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
	private Save save;
	
	boolean buyAction;				// evita que se compre mais de uma vez por clique
	boolean buyHouse;				// evita que se compre casas no mesmo turno de compra de um local

/*************************************************************************************************/
	
	public Commands (ScreenData sData, Dice dice, GameCtrl gCtrl, PlayerCtrl pCtrl, Bank bank, Board board, Prision prision, Save save) {
		c = new Constants();
		sData.frame.addKeyListener(this);
		this.dice = dice;
		this.gCtrl = gCtrl;
		this.pCtrl = pCtrl;
		this.bank = bank;
		this.board = board;
		this.prision = prision;
		this.save = save;
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
			payToLeavePrision(e);
			endTurn(e);
		} else if (gCtrl.getScreenSelected() == c.PROPERTIES) {
			if (!gCtrl.getEditProperty())
				hubScreen(e);
			swapSides(e);
			sell(e);
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
				gCtrl.setMenuButton(gCtrl.getMenuButton()+1);
			}
		
		// selecao de botao
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			gCtrl.setLastScreen(gCtrl.getScreenSelected());
			if (gCtrl.getMenuButton() == 0) {

				//realiza o load do game
				save.loadGame(pCtrl, board, gCtrl);
				
				gCtrl.setScreenSelected(c.BOARD);	
			} else if (gCtrl.getMenuButton() == 1) {
				gCtrl.setScreenSelected(c.BOARD);	
				
				//garante que o jogo inicia com os valores padroes
				save.resetGame(pCtrl, board, gCtrl);
			} else if (gCtrl.getMenuButton() == 2) {
				gCtrl.setScreenSelected(c.RULES);
			} 
		}
	}
	
/*************************************************************************************************/
	
	public void payToLeavePrision(KeyEvent e) {
		Player p;
		
		if (e.getKeyCode() == KeyEvent.VK_P) {
			p = pCtrl.getPlayer(gCtrl.getPlayerIndex());
			if (p.isInPrision() && p.getMoney() >= 150 && !gCtrl.diceUsed()) {
				bank.takeMoney(p, 150);
				prision.getOutOfPrision(p);
				gCtrl.setDiceState(true);
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
	// e pausa do jogo, impossivel girar se estiver vendendo casas
	public void swapSides (KeyEvent e) {
		if (gCtrl.getEditProperty()) return;
	
		// pausa com esc
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			gCtrl.setLastScreen(gCtrl.getScreenSelected());
			gCtrl.setScreenSelected(c.PAUSE);
		// gira entre imoveis e tabuleiro com espaco
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			gCtrl.setLastScreen(gCtrl.getScreenSelected());
			if (gCtrl.getScreenSelected() == c.BOARD) {
				gCtrl.setScreenSelected(c.PROPERTIES);
			} else if (gCtrl.getScreenSelected() == c.PROPERTIES) {
				gCtrl.setScreenSelected(c.BOARD);
			}
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
					bank.buyLocal(p, board, p.getPosition(), gCtrl.getPlayerIndex());
					buyHouse = false;
				}
			
			// compra companhia
			} else if (s.getType() == c.COMPANY) {
				bank.buyLocal(p, board, p.getPosition(), gCtrl.getPlayerIndex());
			} 
			buyAction = false;
		}
	}
	
/*************************************************************************************************/

	// rola os dados e atualiza posicao do player
	public void executeDice (KeyEvent e) {
		Player p = pCtrl.getPlayer(gCtrl.getPlayerIndex());		// pega o player do turno atual
		Square s; 
		
		if (e.getKeyCode() == KeyEvent.VK_D && !p.isInPrision()) {
			if (gCtrl.diceUsed()) return;						// se o dado ja foi rodado retorna
			
			dice.rollDice();									// rola os dados
			
			p.move(dice.getTotalValue());						// soma a posicao no tabuleiro
			p.pickCoords(gCtrl.getPlayerIndex());				// atualiza as coordenadas

			
			// trata a casa em que o player caiu
			s = board.getSquare(p.getPosition());
			if (s.getType() == c.PROPERTY || s.getType() == c.COMPANY) {
				Local local = (Local) s;
				if (local.isBought()) {
					Player owner = pCtrl.getPlayer(local.getOwner());
					int value = bank.payRent(p, owner, local, dice);
					bank.giveMoney(owner, value);
					if (value != 0) gCtrl.lockSelling(p, value);
				}
			} else if (s.getType() == c.DESTINY) {
				Destiny destiny = (Destiny) s;
				destiny.randomize();
				int value = destiny.getValue();
				if (value > 5) {
					bank.giveMoney(p, destiny.getPayValue());
				} else {
					if(bank.takeMoney(p, -(destiny.getPayValue())) == 1) gCtrl.lockSelling(p, -(destiny.getPayValue()));
				}
			} else if (s.getIndex() == 9) { // casa de va para a prisao
				prision.sendToPrision(p, gCtrl.getPlayerIndex());
				gCtrl.setDiceState(true);
			} else if (s.getIndex() == 10) {													  // loteria
				bank.giveMoney(p, 200);
			} else if (s.getIndex() == 29) {													  // impostos
				if(bank.takeMoney(p, 200) == 1) gCtrl.lockSelling(p, 200);
			}
			
			if (!dice.getIsEqual()) {							// se os dados tiveram valores iguais
				gCtrl.setDiceState(true);						// o player joga de novo
			}
		} else if (e.getKeyCode() == KeyEvent.VK_D && p.isInPrision()) {
			if (gCtrl.diceUsed()) return;
			
			dice.rollDice();
			
			gCtrl.setDiceState(true);
			
			if (dice.getIsEqual()) {
				prision.getOutOfPrision(p);
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

				// verifica fim de jogo
				gCtrl.setGameWinner(pCtrl.winner(pCtrl, board));
				if (gCtrl.getGameWinner() != -1) {
					gCtrl.setScreenSelected(c.WIN);
					return;
				}
				
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
	
	// usando a tela de pause
	public void pauseScreen (KeyEvent e) {
		// move para cima
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (gCtrl.getPauseButton() > 0) {
				gCtrl.setPauseButton(gCtrl.getPauseButton()-1);
			}
		
		// move para baixo
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (gCtrl.getPauseButton() < 2) {
				gCtrl.setPauseButton(gCtrl.getPauseButton()+1);
			}
			
		// seleciona o botao
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			gCtrl.setLastScreen (gCtrl.getScreenSelected());
			if (gCtrl.getPauseButton() == 0) {
				gCtrl.setScreenSelected(c.BOARD);	
			} else if (gCtrl.getPauseButton() == 1) {
				gCtrl.setScreenSelected(c.RULES);
			} else  if (gCtrl.getPauseButton() == 2) {
				save.saveGame(pCtrl, board, gCtrl.getPlayerIndex());
				gCtrl.setScreenSelected(c.MAINMENU);
			} 
		}
	}

/*************************************************************************************************/

	public void sell (KeyEvent e) {
		Player p = pCtrl.getPlayer(gCtrl.getPlayerIndex());
		int index = p.getOwned(gCtrl.getCardSelected());
		Square s = board.getSquare(index);
		
		// ativar modo de venda
		if (e.getKeyCode() == KeyEvent.VK_N) {
			if (p.getNumProprieties() == 0) {
				if (gCtrl.isSellingLocked()) {
					p.setBankrupt(true);
				}
				return;
			}
			
			if (!gCtrl.getEditProperty()) {
				gCtrl.setEditProperty(true);
				gCtrl.setCardSelected(0);
			} else if (!gCtrl.isSellingLocked() || p.getMoney() >= gCtrl.getAmountToUnlock()) {
				gCtrl.flipEditProperty();
				gCtrl.setCardSelected(0);
				bank.takeMoney(p, gCtrl.getAmountToUnlock());
				gCtrl.setAmountToUnlock(0);
				gCtrl.setSellingLocked(false);
			}
		// hipotecar
		} else if (e.getKeyCode() == KeyEvent.VK_H) {
			if (gCtrl.getEditProperty()) {
				Local local = (Local) s;
				if (local.getMortgage()) {
					bank.payMortgage(s, p);
				} else {
					bank.mortgage(s, p);
				}
			}
	
		// vender imovel
		} else if (e.getKeyCode() == KeyEvent.VK_V) {
			if (gCtrl.getEditProperty()) {
				if (s.getType() == c.PROPERTY) {
					bank.sellProperty(p, board, index);
				} else if (s.getType() == c.COMPANY) {
					Company company = (Company) s;
					bank.sellCompany(p, company);
				}
				
				if (p.getNumProprieties() == 0) {
					if (gCtrl.isSellingLocked()) {
						if (p.getMoney() < gCtrl.getAmountToUnlock()) {
							p.setBankrupt(true);
						} else {
							bank.takeMoney(p, gCtrl.getAmountToUnlock());
						}
					}
					gCtrl.setAmountToUnlock(0);
					gCtrl.setSellingLocked(false);
					gCtrl.flipEditProperty();
					gCtrl.setCardSelected(0);
				}
			}
			
		// vender casa
		} else if (e.getKeyCode() == KeyEvent.VK_C) {
			if (gCtrl.getEditProperty()) {
				if (s.getType() == c.PROPERTY) {
					Property property = (Property) s;
					bank.sellHouse(p, property);
				} 
			}
			
		// movimenta para cima
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (gCtrl.getCardSelected()-5 >= 0) {
				gCtrl.setCardSelected(gCtrl.getCardSelected()-5);
			}
			
		// movimenta para baixo
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (gCtrl.getCardSelected()+5 < p.getNumProprieties()) {
				gCtrl.setCardSelected(gCtrl.getCardSelected()+5);
			}
			
		// movimenta para direita
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if ((gCtrl.getCardSelected()%5)+1 <= 4 && gCtrl.getCardSelected()+1 < p.getNumProprieties()) {
				gCtrl.setCardSelected(gCtrl.getCardSelected()+1);
			}
			
		// movimenta para esquerda
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if ((gCtrl.getCardSelected()%5)-1 >= 0) {
				gCtrl.setCardSelected(gCtrl.getCardSelected()-1);
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