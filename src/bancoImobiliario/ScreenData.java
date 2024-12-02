package bancoImobiliario;

import java.awt.*;
import java.awt.image.*;

import javax.swing.JFrame;

public class ScreenData extends Canvas {
	
	private static final long serialVersionUID = 1L;

	public static JFrame frame;				// janela
	public BufferStrategy bs;
	
	private final int LENGTH = 1760;		// eixo x
	private final int HEIGHT = 990;			// eixo y
	
	private BufferedImage image;
	
	private Spritesheet spritesheetMap;
	private BufferedImage map;
	
	private Spritesheet spritesheetProperty;
	private BufferedImage property;
	
	public ScreenData () {
		setPreferredSize (new Dimension (LENGTH, HEIGHT));
		initFrame ();
		this.createBufferStrategy (3);
		image = new BufferedImage (LENGTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		spritesheetMap = new Spritesheet ("/tabuleiro.png");
		spritesheetProperty = new Spritesheet ("/telaImoveis.png");
		
		map = spritesheetMap.getSpritesheet ();
		property = spritesheetProperty.getSpritesheet ();
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
	
	public BufferedImage getProperty () {
		return this.property;
	}
	
	public int getLength () {
		return this.LENGTH;
	}
	
	public int getHeight () {
		return this.HEIGHT;
	}
	
	public void getBS() {
		bs = getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy (3);
		}
	}
}
