package bancoImobiliario;

import java.awt.*;
import java.awt.image.*;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {
	
	public static JFrame frame;				// janela
	private BufferedImage image;			// imagem na janela
	private Thread thread;
	//private RenderScreen screen;
	
	private boolean isRunning = true;		// condicao de parada do game
	
	private final int LENGTH = 1760;		// eixo x
	private final int HEIGHT = 990;			// eixo y
	
	private Spritesheet spritesheetMap;
	
	private BufferedImage map;
	
	public Game () {
		spritesheetMap = new Spritesheet ("/tabuleiro.png");
		map = spritesheetMap.getSpritesheet ();
		//screen = new RenderScreen();
		setPreferredSize (new Dimension (LENGTH, HEIGHT));
		initFrame ();
		image = new BufferedImage (LENGTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	}
	
	public void initFrame () {
		frame = new JFrame ("BANCO MOBILHARO");
		frame.add (this);
		frame.setResizable (false);
		frame.pack ();
		frame.setLocationRelativeTo (null);
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.setVisible (true);
	}
	
	public synchronized void start () {
		thread = new Thread (this);
		isRunning = true;					
		thread.start ();					// chama o metodo run
	}
	
	public synchronized void stop () {
		isRunning = false;
		try {
			thread.join ();
		} catch (InterruptedException e) {
			e.printStackTrace ();
		}
	}
	
	public void render () {
		BufferStrategy bs = this.getBufferStrategy ();
		Graphics g;
		if (bs == null) {
			this.createBufferStrategy (3);		// melhora a performance de renderizacao
			return;
		}
		g = image.getGraphics ();
		g.setColor (new Color (255, 0, 155));
		g.fillRect (0, 0, LENGTH, HEIGHT);
		
		/* renderizando o jogo */
		
		g.drawImage(map, 770, 0, null);
		
		/**/
		g.dispose ();
		g = bs.getDrawGraphics ();
		g.drawImage (image, 0, 0, LENGTH, HEIGHT, null);
		bs.show ();
	}
	
	// laço principal do jogo
	public void run () {
		int frames;
		long now, lastTime;
		double amountOfTicks, nsPerTick, delta, timer;
		
		lastTime = System.nanoTime ();
		amountOfTicks = 60.0;
		nsPerTick = 1000000000 / amountOfTicks;
		delta = 0;
		frames = 0;
		timer = System.currentTimeMillis ();
		
		while (isRunning) {
			now = System.nanoTime ();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			
			if (delta >= 1) {
				render ();
				frames++;
				delta--;
			}
			
			if (System.currentTimeMillis() - timer >= 1000) {
				System.out.println ("FPS: " + frames);
				frames = 0;
				timer += 1000;
			}
			
		}
		
		stop ();
	}
	
	public static void main (String[] args) {
		Game game = new Game ();
		game.start ();
	}
}
