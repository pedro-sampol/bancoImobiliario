package bancoImobiliario;

import java.awt.*;
import java.awt.image.*;

import javax.swing.JFrame;

public class ScreenData extends Canvas {
	
	public static JFrame frame;				// janela
	
	private final int LENGTH = 1760;		// eixo x
	private final int HEIGHT = 990;			// eixo y
	
	private BufferedImage image;
	
	private Spritesheet spritesheetMap;
	private BufferedImage map;
	
	public ScreenData () {
		spritesheetMap = new Spritesheet ("/tabuleiro.png");
		map = spritesheetMap.getSpritesheet ();
		setPreferredSize (new Dimension (LENGTH, HEIGHT));
		initFrame ();
		image = new BufferedImage (LENGTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	}
	
	// inicializa janela do jogo
	public void initFrame () {
		frame = new JFrame ("BANCO MOBILHARO");
		frame.add (this);
		frame.setResizable (false);
		frame.pack ();
		frame.setLocationRelativeTo (null);
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.setVisible (true);
	}
	
	public BufferedImage getImage () {
		return this.image;
	}
	
	public BufferedImage getMap () {
		return this.map;
	}
	
	public int getLength () {
		return this.LENGTH;
	}
	
	public int getHeight () {
		return this.HEIGHT;
	}
	
}
