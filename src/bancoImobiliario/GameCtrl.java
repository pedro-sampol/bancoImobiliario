package bancoImobiliario;

public class GameCtrl {
	private int currentState;
	private boolean startingGame;
	private boolean startingTurn;
	private boolean endingTurn;
	private boolean isGameOn;
	private boolean isPaused;
	private int playerIndex;
	private int playersOn;
	
	
	public GameCtrl() {
		currentState = 0;
		startingGame = false;
		startingTurn = true;
		endingTurn = false;
		isGameOn = false;
		playerIndex = 0;
		playersOn = 6;
	}
	
	public void startGame() {
		isGameOn = true;
		startingGame = true;
	}
	public void endGame() {
		isGameOn = false;
	}
	public void stopStartingGame() {
		startingGame = false;
	}
	public void endTurn() {
		startingTurn = true;
		playerIndex = (playerIndex+1)%6;
	}
	public void removePlayer(Player p) {
		playersOn--;
		p.setBankrupt(true);
	}
	public void setCurrentState(int state) {
		currentState = state;
	}
	
	public int getCurrentState() {
		return currentState;
	}
	public boolean isGameOn() {
		return isGameOn;
	}
	public boolean isPaused() {
		return isPaused;
	}
	public boolean isStartingGame() {
		return startingGame;
	}
	public boolean isStartingTurn() {
		return startingTurn;
	}
	public int getPlayersOn() {
		return playersOn;
	}
	
}
