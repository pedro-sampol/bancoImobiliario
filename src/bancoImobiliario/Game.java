package bancoImobiliario;

public class Game implements Runnable {
	
	private Thread thread;
	private RenderScreen screen;
	private PlayerCtrl p;
	private Board board;
	private Save save;
	private GameCtrl g;
	public Constants c;
	
	private Spritesheet player_sprite;
	
	
	private boolean isRunning = true;		//condicao de parada do game
	
	private Dice dice;
	
	public Game () {		
		save = new Save();
		screen = new RenderScreen();
		dice = new Dice();
		p = new PlayerCtrl(false, save);
		board = new Board(false, save);
		g = new GameCtrl();
		c = new Constants();
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
		Player current_player;
		int frames;
		long now, lastTime;
		double amountOfTicks, nsPerTick, delta, timer;
		
		lastTime = System.nanoTime ();
		amountOfTicks = 60.0;
		nsPerTick = 1000000000 / amountOfTicks;
		delta = 0;
		frames = 0;
		timer = System.currentTimeMillis ();
		
		
		boolean load = false;
		int player_index = 0;
		current_player = p.getPlayer(0);
		
		while (isRunning) {
			now = System.nanoTime ();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			
			if (delta >= 1) {
				screen.gameScreen(p);			// funcao render
				frames++;
				delta--;
			}
			
			if (System.currentTimeMillis() - timer >= 1000) {
				System.out.println ("FPS: " + frames);
				frames = 0;
				timer += 1000;
			}
			
			// no momento certo chama startGame()
			
			// jogo em si
			if (g.isGameOn() && !g.isPaused()) {
				
				// inicializa o game
				if (g.getCurrentState() == c.START_GAME) {
					p = new PlayerCtrl(load, save);
					g.setCurrentState(c.MIDDLE_GAME);
				}
				
				// pega o proximo player
				if (g.getCurrentState() == c.GET_NEXT_PLAYER) {
					current_player = p.getPlayer(player_index);
					g.setCurrentState(c.MIDDLE_GAME);
				}
				
				// caso em que o player esta impedido de jogar
				if (current_player.getNoPlayTime() > 0) {
					current_player.setNoPlayTime(current_player.getNoPlayTime() - 1);
					g.setCurrentState(c.END_TURN);
				}
				
				// caso em que o player esta na cadeia
				else if (current_player.isInJail()) {
					
				}
				
			}
			
		}
		
		// jogo em si
		while (g.getPlayersOn() > 1) {
			// pega o player atual
			player_index = (player_index + 1)%6;
			current_player = board.getPlayer(player_index);
			
			// caso em que o player esta impedido de jogar
			if (current_player.getNoPlayTime() > 0) {
				// current_player.noPlayTime--;
				continue;
			}
			// caso em que o player esta na cadeia
			if (current_player.isInJail()) {
				if (current_player.getTimeInPrision() < 3) {
					dice.rollDice();
					if (dice.getIsEqual()) {
						// current_player.inJail = false
						// current_player.timeInPrison = 0
					}
				}
				else {
					// escolhe se quer pagar para sair ou rolar os dados
				}
			}
			// verifica se saiu da cadeia
			if (current_player.isInJail()) {
				// current_player.timeInPrison++
				continue;
			}
			
			// acoes normais
			dice.rollDice();
			if (current_player.repeatedDice(dice)) {
				// move player para a cadeia
				continue;
			}
			
		}
		
		stop ();
	}
	
	public static void main (String[] args) {
		Game game = new Game ();
		game.start ();
	}
}
