package bancoImobiliario;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Screen extends Canvas {
	
	private static final long serialVersionUID = 1L;

	private Font fontBig;
	private Font fontMedium;
	private Font fontSmall;
	
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
	
	public void printGame (ScreenData screenData, PlayerCtrl pCtrl, Commands commands) {
		Graphics graphics;
		Player player = pCtrl.getPlayer(commands.getPlayerID());
				
		graphics = screenData.getImage().getGraphics ();				//acessa image
	
		// renderiza menu principal
		if (commands.getScreenID() == commands.c.MAINMENU) {
			drawMainMenu(screenData, graphics, commands);
			
		//renderiza regras
		} else if (commands.getScreenID() == commands.c.RULES) {
			drawRules (screenData, graphics, commands);
			
		// renderiza tabuleiro
		} else if (commands.getScreenID() == commands.c.BOARD) {
			graphics.drawImage(screenData.getHub(), 0, 0, null);
			graphics.drawImage(player.getSprite(), 0, 210, null);
			graphics.drawImage(screenData.getMap(), 770, 0, null);
			
		// renderiza imoveis
		} else if (commands.getScreenID() == commands.c.PROPERTIES) {
			graphics.drawImage(screenData.getHub(), 0, 0, null);
			graphics.drawImage(player.getSprite(), 0, 210, null);
			graphics.drawImage(screenData.getProperty(), 770, 0, null);
			
		// renderiza tela de pause
		} else if (commands.getScreenID() == commands.c.PAUSE) {
			drawPause (screenData, graphics, commands);
			
		// renderiza tela de vitoria
		} else if (commands.getScreenID() == commands.c.WIN) {
			drawWin (screenData, graphics, commands, player);
		}

		graphics.dispose ();
		graphics = screenData.bs.getDrawGraphics ();
		graphics.drawImage (screenData.getImage(), 0, 0, screenData.getLength(), screenData.getHeight(), null);
		screenData.bs.show ();
	}
	
/*************************************************************************************************/
	
	// desenha o menu principal completo
	public void drawMainMenu (ScreenData s, Graphics g, Commands commands) {
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
		if (commands.getMenuButton() == 0) {
			g.fillOval(150, 500, 30, 30);
		} else if (commands.getMenuButton() == 1) {
			g.fillOval(150, 612, 30, 30);
		} else if (commands.getMenuButton() == 2) {
			g.fillOval(150, 725, 30, 30);
		}
	}
	
/*************************************************************************************************/
	
	// desenha as regras
	public void drawRules (ScreenData s, Graphics g, Commands commands) {
		// cor de fundo
		g.setColor (Color.BLACK);
		g.fillRect (0, 0, s.getLength(), s.getHeight());
		
		// imagem das regras
		g.drawImage(s.getRules(), 0, 0, null);
	}
	
/*************************************************************************************************/
	
	// desenha a tela de pause
	public void drawPause (ScreenData s, Graphics g, Commands commands) {
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
		if (commands.getPauseButton() == 0) {
			g.fillOval(150, 500, 30, 30);
		} else if (commands.getPauseButton() == 1) {
			g.fillOval(150, 612, 30, 30);
		} else if (commands.getPauseButton() == 2) {
			g.fillOval(150, 725, 30, 30);
		}
	}
	
/*************************************************************************************************/
	
	// desenha a tela de vitoria
	public void drawWin (ScreenData s, Graphics g, Commands commands, Player player) {
		// cor do fundo
		g.setColor (Color.BLACK);
		g.fillRect (0, 0, s.getLength(), s.getHeight());
				
		// titulo
		g.setColor (Color.RED);
		g.setFont (fontBig);
		g.drawString("Vitória", 740, 27);
				
		// opcoes de clique
		g.setColor (Color.WHITE);
		g.setFont(fontMedium);
		g.drawString("Voltar", 1500, 800);
				
		// foto do vencedor
		g.drawImage(player.getSprite(), 665, 200, null);
	}
	
}
