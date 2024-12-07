package bancoImobiliario;

public class GameCtrl {
	private boolean load;
	private int currentState;
	private boolean isGameOn;
	private boolean isPaused;
	private int playerIndex;
	private int playersOn;
	private boolean diceUsed;
	
	public GameCtrl() {
		currentState = 0;
		diceUsed = false;
		isGameOn = false;
		load = false;
		playerIndex = 0;
		playersOn = 6;
	}
	
	public void startGame() {
		isGameOn = true;
	}
	public void endGame() {
		isGameOn = false;
	}
	public void removePlayer(Player p) {
		playersOn--;
		p.setBankrupt(true);
	}
	public void setCurrentState(int state) {
		currentState = state;
	}
	public void setPlayerIndex(int index) {
		playerIndex = index;
	}
	public void setDiceState(boolean state) {
		diceUsed = state;
	}

	public int getCurrentState() {
		return currentState;
	}
	public int getPlayerIndex() {
		return playerIndex;
	}
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
	public boolean diceUsed() {
		return diceUsed;
	}
	
}
