package bancoImobiliario;

public class Game implements Runnable {
	
	private Thread thread;
	
	private RenderScreen screen;			//tela do jogo a ser impressa
	private PlayerCtrl p;					//controlador do player
	private Board board;					//tabuleiro com as casas
	private Dice dice;						//dados
	private Commands commands;				//comandos do jogo
	private Save save;						//save/load do jogo
	private GameCtrl g;						//controle de fluxo do jogo
	private Prision prision;				//controle da prisao
	private boolean isRunning = true;		//condicao de parada do game
	private Bank bank;						//controle de dinheiro do jogo
	
	public Constants c;

	/*************************************************************************************************/
	
	public Game () {
		save = new Save();
		screen = new RenderScreen();
		dice = new Dice();
		g = new GameCtrl();
		p = new PlayerCtrl();
		board = new Board();
		bank = new Bank();
		prision = new Prision();
		commands = new Commands(screen.getScreenData(), dice, g, p, bank, board, prision);
		c = new Constants();
		save.loadPlayers(p);
		save.loadSquares(board);
	}
	
	/*************************************************************************************************/
	
	public synchronized void start () {
		thread = new Thread (this);
		isRunning = true;					
		thread.start ();					// chama o metodo run
	}
	
	/*************************************************************************************************/
	
	public synchronized void stop () {
		isRunning = false;
		try {
			thread.join ();
		} catch (InterruptedException e) {
			e.printStackTrace ();
		}
	}
	
	/*************************************************************************************************/
	
	// laço principal do jogo
	public void run () {
		int frames;
		long now, lastTime;
		double amountOfTicks, nsPerTick, delta, timer;
		save.saveGame(p, board);
		
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
				screen.gameScreen(p, g, dice, board);			// funcao render
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
