package bancoImobiliario;

public class GameCtrl {
	private boolean startingTurn;
	private boolean endingTurn;
	private boolean isGameOn;
	private boolean isPaused;
	private int playerIndex;
	
	
	public GameCtrl() {
		startingTurn = true;
		endingTurn = false;
		isGameOn = false;
		playerIndex = 0;
	}
	
	public void startGame() {
		isGameOn = true;
	}
	public void endGame() {
		isGameOn = false;
	}
	
	public boolean isGameOn() {
		return isGameOn;
	}
	public boolean isPaused() {
		return isPaused;
	}
	public void endTurn() {
		startingTurn = true;
		playerIndex = (playerIndex+1)%6;
	}
	
}
