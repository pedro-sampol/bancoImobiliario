package bancoImobiliario;

public class Game implements Runnable {
	
	private Thread thread;
	private RenderScreen screen;
	
	private boolean isRunning = true;		//condicao de parada do game
	
	private Dice dice;
	
	public Game () {
		screen = new RenderScreen();
		dice = new Dice();
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
				screen.gameScreen();
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
