package bancoImobiliario;

public class GameCtrl {

	private boolean load;			//jogo precisa dar load?
	private int currentState;		//estado atual do jogo
	private boolean isGameOn;		//jogo esta sendo rodado?
	private boolean isPaused;		//jogo esta pausado?
	private int playerIndex;		//indice do player atual
	private int playersOn;			//numero de players no jogo
	private boolean diceUsed;		//player ainda nao jogou os dados
	
	private int playerSelected;		//player selecionado atualmente
	private int screenSelected;		//tela selecionada atualmente
	private int menuButton;			//botao do menu
	private int pauseButton;		//botao de pause
	private int lastScreen;			//tela anterior
	
	public GameCtrl() {
		currentState = 0;
		diceUsed = false;
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
	
	/*************************************************************************************************/
	
	public void setPlayerIndex(int index) {
		playerIndex = index;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}
	
	/*************************************************************************************************/
	
	public void setDiceState(boolean state) {
		diceUsed = state;
	}

	public boolean diceUsed() {
		return diceUsed;
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
	
	/*************************************************************************************************/

	public int getPlayerSelected() {
		return playerSelected;
	}
	public int getScreenSelected() {
		return screenSelected;
	}
	public int getMenuButton() {
		return menuButton;
	}
	public int getPauseButton() {
		return pauseButton;
	}
	public int getLastScreen() {
		return lastScreen;
	}
	
	public void setPlayerSelected(int playerSelected) {
		this.playerSelected = playerSelected;
	}
	public void setScreenSelected(int screenSelected) {
		this.screenSelected = screenSelected;
	}
	public void setMenuButton(int menuButton) {
		this.menuButton = menuButton;
	}
	public void setPauseButton(int pauseButton) {
		this.pauseButton = pauseButton;
	}
	public void setLastScreen(int lastScreen) {
		this.lastScreen = lastScreen;
	}
	
}
