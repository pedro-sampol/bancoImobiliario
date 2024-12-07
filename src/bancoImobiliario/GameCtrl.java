package bancoImobiliario;

public class GameCtrl {
	private boolean load;			//jogo precisa dar load?
	private int currentState;		//estado atual do jogo
	private boolean isGameOn;		//jogo esta sendo rodado?
	private boolean isPaused;		//jogo esta pausado?
	private int playerIndex;		//indice do player atual
	private int playersOn;			//numero de players no jogo
	private boolean isWaitingDice;	//player ainda nao jogou os dados
	
	
	public GameCtrl() {
		currentState = 0;
		isWaitingDice = false;
		isGameOn = false;
		load = false;
		playerIndex = 0;
		playersOn = 6;
	}
	
	/*************************************************************************************************/
	
	public void startGame() {
		isGameOn = true;
	}
	public void endGame() {
		isGameOn = false;
	}
	
	/*************************************************************************************************/
	
	public void removePlayer(Player p) {
		playersOn--;
		p.setBankrupt(true);
	}
	
	
	public void setPlayerIndex(int index) {
		playerIndex = index;
	}
	
	/*************************************************************************************************/
	
	public void setCurrentState(int state) {
		currentState = state;
	}
	
	public int getCurrentState() {
		return currentState;
	}
	
	/*************************************************************************************************/
	
	public boolean isGameOn() {
		return isGameOn;
	}
	public boolean isPaused() {
		return isPaused;
	}
	public int getPlayersOn() {
		return playersOn;
	}
	public boolean getLoad() {
		return load;
	}
	
}
