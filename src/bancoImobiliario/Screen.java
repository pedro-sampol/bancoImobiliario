package bancoImobiliario;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Screen extends Canvas {
	
	private static final long serialVersionUID = 1L;	//controle

	public Constants cnst = new Constants();
	
	private Font fontBig;								//fonte das letras em tamanho grande
	private Font fontMedium;							//fonte das letras em tamanho medio
	private Font fontSmall;								//fonte das letras em tamanho pequeno
	
/*************************************************************************************************/
	
	public Screen () {
		try {
			File arquivo = new File ("./res/OPTIFranklinGothic-Medium.otf");
			fontBig = Font.createFont(Font.TRUETYPE_FONT, arquivo);
			fontBig = fontBig.deriveFont(Font.PLAIN, 90);
			fontMedium = fontBig.deriveFont(Font.PLAIN, 55);
			fontSmall = fontBig.deriveFont(Font.PLAIN, 30);
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
			fontBig = new Font("Serif", Font.PLAIN, 90);
		} 
	}
	
/*************************************************************************************************/
	
	public void printGame (ScreenData screenData, PlayerCtrl pCtrl, GameCtrl gCtrl, Dice dice, Board board) {
		Graphics graphics;
		Player player = pCtrl.getPlayer(gCtrl.getPlayerSelected());
		graphics = screenData.getImage().getGraphics ();
	
		// renderiza menu principal
		if (gCtrl.getScreenSelected() == cnst.MAINMENU) {
			drawMainMenu(screenData, graphics, gCtrl);
			
		//renderiza regras
		} else if (gCtrl.getScreenSelected() == cnst.RULES) {
			drawRules (screenData, graphics, gCtrl);
			
		// renderiza tabuleiro
		} else if (gCtrl.getScreenSelected() == cnst.BOARD) {
			drawHub(screenData, graphics, player);
			textHubMap(graphics);
			playerInfo (screenData, graphics, player);
			drawMap(screenData, graphics, pCtrl, gCtrl, dice);
			drawBigCard (screenData, graphics, pCtrl.getPlayer(gCtrl.getPlayerIndex()), board, gCtrl);
			
		// renderiza imoveis
		} else if (gCtrl.getScreenSelected() == cnst.PROPERTIES) {
			drawHub(screenData, graphics, player);
			textHubProperty(graphics);
			playerInfo(screenData, graphics, player);
			drawProperty(screenData, graphics, player, board);
			sellingMode(screenData, graphics, gCtrl);
			
		// renderiza tela de pause
		} else if (gCtrl.getScreenSelected() == cnst.PAUSE) {
			drawPause (screenData, graphics, gCtrl);
			
		// renderiza tela de vitoria
		} else if (gCtrl.getScreenSelected() == cnst.WIN) {
			drawWin (screenData, graphics, player);
		}

		graphics.dispose ();
		graphics = screenData.bs.getDrawGraphics ();
		graphics.drawImage (screenData.getImage(), 0, 0, screenData.getLength(), screenData.getHeight(), null);
		screenData.bs.show ();
	}
	
/*************************************************************************************************/
	
	// desenha o menu principal completo
	public void drawMainMenu (ScreenData s, Graphics g, GameCtrl gCtrl) {
		// cor do fundo
		g.setColor (Color.BLACK);
		g.fillRect (0, 0, s.getLength(), s.getHeight());
		
		// titulo
		g.setColor (Color.RED);
		g.setFont (fontBig);
		g.drawString("Banco Imobiliario", 200, 200);
		
		// opcoes de clique
		g.setColor (Color.WHITE);
		g.setFont(fontMedium);
		g.drawString("Continuar Jogo Salvo", 200, 535);
		g.drawString("Novo Jogo", 200, 647);
		g.drawString("Regras", 200, 760);	
		
		// bolinhas de selecao
		g.setColor (Color.YELLOW);
		if (gCtrl.getMenuButton() == 0) {
			g.fillOval(150, 500, 30, 30);
		} else if (gCtrl.getMenuButton() == 1) {
			g.fillOval(150, 612, 30, 30);
		} else if (gCtrl.getMenuButton() == 2) {
			g.fillOval(150, 725, 30, 30);
		}
	}
	
/*************************************************************************************************/
	
	// desenha as regras
	public void drawRules (ScreenData s, Graphics g, GameCtrl gCtrl) {
		// cor de fundo
		g.setColor (Color.BLACK);
		g.fillRect (0, 0, s.getLength(), s.getHeight());
		
		// imagem das regras
		g.drawImage(s.getRules(), 0, 0, null);
	}
	
/*************************************************************************************************/
	
	// imagem da hub com personagem escolhido
	public void drawHub (ScreenData s, Graphics g, Player player) {
		g.drawImage(s.getHub(), 0, 0, null);
		g.drawImage(player.getSprite(), 0, 210, null);
	}
	
	// controles disponiveis na tela MAP
	public void textHubMap (Graphics g) {
		g.setColor (Color.BLACK);
		g.setFont (fontSmall);
		g.drawString("- D para rolar os dados", 20, 860);
		g.drawString("- C para comprar locais e casas", 20, 890);
		g.drawString("- ENTER para passar o turno", 20, 920);
	}

	// controles disponiveis na tela PROPERTY
	public void textHubProperty (Graphics g) {
		g.setColor (Color.BLACK);
		g.setFont (fontSmall);
		g.drawString("- N abre e fecha negociações com o banco", 20, 860);
		g.drawString("- V para vender seus locais", 20, 890);
		g.drawString("- H para hipotecar seus locais", 20, 920);
		g.drawString("- C para vender suas casas", 20, 950);
		g.drawString("use as setas", 20, 980);
	}
	
	// informacoes do player na tela
	public void playerInfo (ScreenData s, Graphics g, Player player) {
		g.setFont (fontSmall);
		if (player.isBankrupt()) {
			g.setColor (Color.RED);
			g.drawString("FALIU", 530, 250);
			return;
		}
	
		g.setColor (Color.BLACK);
		g.drawString("Dinheiro:", 530, 250);
		g.drawString("Monopólios:", 530, 350);
		g.drawString("Empresas:", 530, 450);
		g.drawString("- ESPAÇO", 530, 590);
		g.drawString("- ESC", 530, 690);
		
		g.setColor (Color.WHITE);
		g.drawString(""+player.getMoney(), 530, 290);
		g.drawString(""+player.getMonopolies(), 530, 390);
		g.drawString(""+player.getNumCompanies(), 530, 490);
		g.drawString("troca tela", 530, 630);
		g.drawString("pause", 530, 730);
	}
	
/*************************************************************************************************/ 
	
	public void drawProperty (ScreenData s, Graphics g, Player player, Board board) {		
		// desenha imagem
		g.drawImage(s.getProperty(), 770, 0, null);
		
		//desenha cartas
		int height = 0;
		for (int i = 0; i < player.getNumProprieties(); i++) {
			Square square = board.getSquare(player.getOwned(i));
			height = i/5;
			
			// imprime cartas de propriedade
			if (square.getType() == cnst.PROPERTY) {
				Property property = (Property) square;
				g.drawImage(property.getSprite(), 770+198*(i%5), 102+(222*height), null);
				if (property.getMortgage()) {
					g.setColor(Color.RED);
					g.setFont(fontMedium);
					g.drawString("H", 869+198*(i%5), 213+(222*height));
				}
				
			// imprime cartas de companhia
			} else if (square.getType() == cnst.COMPANY) {
				Company company = (Company) square;
				g.drawImage(company.getSprite(), 770+198*(i%5), 102+(222*height), null);
				if (company.getMortgage()) {
					g.setColor(Color.RED);
					g.setFont(fontMedium);
					g.drawString("H", 869+198*(i%5), 213+(222*height));
				}
			}
		}
	}
	
/*************************************************************************************************/ 
	
	public void sellingMode (ScreenData s, Graphics g, GameCtrl gCtrl) {		
		if (gCtrl.getEditProperty()) {
			int height = (gCtrl.getCardSelected()/5);
			int length = (gCtrl.getCardSelected()%5);
			g.setColor(Color.BLACK);
			g.fillOval(869+(198*length), 213+(222*height), 30, 30);
			
			if (gCtrl.isSellingLocked()) {
				g.setFont(fontSmall);
				g.drawString("Dívida: "+gCtrl.getAmountToUnlock(), 1200, 50);
			}
		}
		
	}
	
/*************************************************************************************************/
	
	public void drawMap (ScreenData s, Graphics g, PlayerCtrl pCtrl, GameCtrl gCtrl, Dice dice) {
		Player p;
		String str = "erro";
		
		// mapa
		g.drawImage(s.getMap(), 770, 0, null);
		
		// de quem eh o turno
		switch (gCtrl.getPlayerIndex()) {
			case 0: str = "vermelho"; break;
			case 1: str = "azul"; break;
			case 2: str = "verde"; break;
			case 3: str = "roxo"; break;
			case 4: str = "amarelo"; break;
			case 5: str = "laranja"; break;
		}
		g.setColor (Color.BLACK);
		g.setFont (fontMedium);
		g.drawString("turno do "+str, 1040, 240);
		
		// valor dos dados
		g.setColor (Color.BLACK);
		g.setFont (fontSmall);
		g.drawString("Dado 1: "+dice.getValue1(), 1400, 670);
		g.drawString("Dado 2: "+dice.getValue2(), 1400, 720);
		
		// players
		p = pCtrl.getPlayer(0);
		if (!p.isBankrupt()) {
			g.setColor (new Color (255,0,0));
			g.fillRect(p.getX(), p.getY(), 30, 30);
		}
		
		p = pCtrl.getPlayer(1);
		if (!p.isBankrupt()) {
			g.setColor (new Color (38,0,255));
			g.fillRect(p.getX(), p.getY(), 30, 30);
		}
		
		p = pCtrl.getPlayer(2);
		if (!p.isBankrupt()) {
			g.setColor (new Color (30,255,0));
			g.fillRect(p.getX(), p.getY(), 30, 30);
		}
		
		p = pCtrl.getPlayer(3);
		if (!p.isBankrupt()) {
			g.setColor (new Color (236,0,255));
			g.fillRect(p.getX(), p.getY(), 30, 30);
		}
		
		p = pCtrl.getPlayer(4);
		if (!p.isBankrupt()) {
			g.setColor (new Color (244,255,0));
			g.fillRect(p.getX(), p.getY(), 30, 30);
		}
		
		p = pCtrl.getPlayer(5);
		if (!p.isBankrupt()) {
			g.setColor (new Color (255,167,0));
			g.fillRect(p.getX(), p.getY(), 30, 30);
		}

	}
	
/*************************************************************************************************/
	
	public void drawBigCard (ScreenData s, Graphics g, Player player, Board board, GameCtrl gCtrl) {		
		Square square = board.getSquare(player.getPosition());
		String str = "erro";
		
		g.setColor (Color.BLACK);
		g.setFont (fontSmall);
		
		// se o quadrado for uma propriedade imprime o sprite e diz quantas casas estao contruidas la
		if (square.getType() == cnst.PROPERTY) {
			Property property = (Property) square;
			if (property.getOwner() == -1) {
				g.drawString("sem proprietário", 970, 400);
			} else {
				// quem eh o dono da carta na tela
				switch (property.getOwner()) {
				case 0: str = "vermelho"; break;
				case 1: str = "azul"; break;
				case 2: str = "verde"; break;
				case 3: str = "roxo"; break;
				case 4: str = "amarelo"; break;
				case 5: str = "laranja"; break;
				default: break;
				}
				g.drawString(str+" tem "+property.getNHouses()+ " casas nesse lugar", 970, 400);
			}
			g.drawImage(property.getBigSprite(), 970, 440, null);
			
		// se o quadrado for uma companhia imprime o sprite
		} else if (square.getType() == cnst.COMPANY) {
			Company company = (Company) square;
			if (company.getOwner() == -1) {
				g.drawString("sem proprietário", 970, 400);
			} else {
			// quem eh o dono da carta na tela
				switch (company.getOwner()) {
					case 0: str = "vermelho"; break;
					case 1: str = "azul"; break;
					case 2: str = "verde"; break;
					case 3: str = "roxo"; break;
					case 4: str = "amarelo"; break;
					case 5: str = "laranja"; break;
					default: break;
				}
				g.drawString("empresa do "+str, 970, 400);
			}
			g.drawImage(company.getBigSprite(), 970, 440, null);
			
		// se o quadrado for um destino imprime o texto do destino
		} else if (square.getType() == cnst.DESTINY) {
			Destiny destiny = (Destiny) square;
			g.drawString(destiny.texts[destiny.getValue()], 970, 400);

		// recebe 200 passando pelo inicio
		} else if (player.isInPrision()) {
			g.drawString("prisão pra você ladrão", 970, 400);
		
		// loteria 
		} else if (square.getIndex() == 10) {
			g.drawString("loteria: +200", 970, 400);
		
		// ferias
		} else if (square.getIndex() == 18) {
			g.drawString("é ferias, descanse", 970, 400);
		
		// impostos
		} else if (square.getIndex() == 29) {
			g.drawString("impostos: -200", 970, 400);
		}
	}
	
/*************************************************************************************************/
	
	// desenha a tela de pause
	public void drawPause (ScreenData s, Graphics g, GameCtrl gCtrl) {
		// cor do fundo
		g.setColor (Color.BLACK);
		g.fillRect (0, 0, s.getLength(), s.getHeight());
				
		// titulo
		g.setColor (Color.RED);
		g.setFont (fontBig);
		g.drawString("Pausado", 200, 200);
				
		// opcoes de clique
		g.setColor (Color.WHITE);
		g.setFont(fontMedium);
		g.drawString("Continuar", 200, 535);
		g.drawString("Regras", 200, 647);
		g.drawString("Salvar e Sair", 200, 760);
				
		// bolinhas de selecao
		g.setColor (Color.YELLOW);
		if (gCtrl.getPauseButton() == 0) {
			g.fillOval(150, 500, 30, 30);
		} else if (gCtrl.getPauseButton() == 1) {
			g.fillOval(150, 612, 30, 30);
		} else if (gCtrl.getPauseButton() == 2) {
			g.fillOval(150, 725, 30, 30);
		}
	}

/*************************************************************************************************/
	
	// desenha a tela de vitoria
	public void drawWin (ScreenData s, Graphics g, Player player) {
		// cor do fundo
		g.setColor (Color.BLACK);
		g.fillRect (0, 0, s.getLength(), s.getHeight());
				
		// titulo
		g.setColor (Color.RED);
		g.setFont (fontBig);
		g.drawString("Vitória", 740, 250);
				
		// opcoes de clique
		g.setColor (Color.WHITE);
		g.setFont(fontMedium);
		g.drawString("Voltar", 1500, 900);
				
		// foto do vencedor
		g.drawImage(player.getSprite(), 610, 390, null);
	}
	
}
