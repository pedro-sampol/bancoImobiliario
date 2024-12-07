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
		commands = new Commands(screen.getScreenData(), dice, g, p, bank);
		c = new Constants();
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
		Player current_player;
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
		
		
		boolean load = false;
		int player_index = 0;
		//current_player = p.getPlayer(0);
		current_player = null;
		
		while (isRunning) {
			now = System.nanoTime ();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			
			if (delta >= 1) {
				screen.gameScreen(p, commands);			// funcao render
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
				int currentState = g.getCurrentState();
				switch(currentState) {
					// START_GAME
					case 0:
						save.loadPlayers(p);
						save.loadSquares(board);
						g.setCurrentState(c.START_TURN);
						break;
						
					// DID_GAME_END
					case 6:
						boolean has_ended = false;
						
						// verifica se o jogo acabou
					
						if (has_ended) {
							g.endGame();
						}
						else {
							g.setCurrentState(c.START_TURN);
						}
						break;
						
					// START_TURN
					case 1:
						current_player = p.getPlayer(player_index);
						g.setCurrentState(c.ABLE_TO_PLAY);
						break;
					
					// ABLE_TO_PLAY
					case 5:
						if (current_player.getNoPlayTime() > 0) {
							current_player.setNoPlayTime(current_player.getNoPlayTime() - 1);
							g.setCurrentState(c.END_TURN);
						}
						else {
							g.setCurrentState(c.IS_IN_JAIL);
						}
						break;
						
					// IS_IN_JAIL
					case 7:
						if (current_player.isInPrision()) {
							if (current_player.getTimeInPrision() < 3) {
								g.setCurrentState(c.ROLL_PRISION_DICE);
							}
							else {
								g.setCurrentState(c.PRISION_CHOICE);
							}
						}
						else {
							g.setCurrentState(c.ROLL_DICE);
						}
						break;
						
					// PRISION_CHOICE
					case 10:
						// espera decisao do player
						boolean roll_dice = false;
						boolean chose = false;
						if (chose) {
							if (roll_dice) {
								g.setCurrentState(c.ROLL_PRISION_DICE);
							}
							else {
								// desconta 50
								g.setCurrentState(c.END_TURN);
							}
						}
						break;
					
					// ROLL_PRISION_DICE
					case 9:
						// espera player rodar dados
						boolean rolled_dice = false;
						if (rolled_dice) {
							// decide se o player conseguiu sair da cadeia
							g.setCurrentState(c.END_TURN);
						}
	
					// ROLL_DICE
					case 8:
						break;
						
					case 3:
						save.saveGame(p, board);
						break;
					
					default:
						break;	
	
				}
			}
			
		}	
		stop ();
	}
	
	public static void main (String[] args) {
		Game game = new Game ();
		game.start ();
	}
}
