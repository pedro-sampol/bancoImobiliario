package bancoImobiliario;

import java.awt.*;
import java.awt.image.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class ScreenData extends Canvas {
	
	private static final long serialVersionUID = 1L;

	public JFrame frame;				// janela
	public BufferStrategy bs;
	
	private final int LENGTH = 1760;		// eixo x
	private final int HEIGHT = 990;			// eixo y
	
	private BufferedImage image;
	
	private BufferedImage map;
	private BufferedImage property;
	private BufferedImage hub;
	
	public boolean screenSelector;
	public int menuSelector;
	
	public ScreenData () {
		setPreferredSize (new Dimension (LENGTH, HEIGHT));
		initFrame ();
		this.createBufferStrategy (3);
		image = new BufferedImage (LENGTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		initMaps ();
		screenSelector = false;
	}
	
	// inicializa janela do jogo
	public void initFrame () {
		frame = new JFrame("Banco Imobiliario");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo (null);
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.setVisible (true);
	}
	
	// inicializa sprites dos mapas
	public void initMaps () {
		try {
			map = ImageIO.read (getClass().getResource("/tabuleiro.png"));
		} catch (IOException e) {
			e.printStackTrace ();
		}
		try {
			property = ImageIO.read (getClass().getResource("/telaImoveis.png"));
		} catch (IOException e) {
			e.printStackTrace ();
		}
		try {
			hub = ImageIO.read (getClass().getResource("/hub.png"));
		} catch (IOException e) {
			e.printStackTrace ();
		}
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
	
	public BufferedImage getHub () {
		return this.hub;
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
	
	public boolean getScreen () {
		return this.screenSelector;
	}
	
}
