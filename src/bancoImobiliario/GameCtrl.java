package bancoImobiliario;

public class GameCtrl {

	private int currentState;		//estado atual do jogo
	private boolean isGameOn;		//jogo esta sendo rodado?
	private boolean isPaused;		//jogo esta pausado?
	private int playerIndex;		//indice do player atual
	private int playersOn;			//numero de players no jogo
	private boolean diceUsed;		//player ainda nao jogou os dados
	private boolean sellingLocked;  //player precisa vender
	
	private int playerSelected;		//player selecionado atualmente
	private int screenSelected;		//tela selecionada atualmente
	private int menuButton;			//botao do menu
	private int pauseButton;		//botao de pause
	private int lastScreen;			//tela anterior
	private boolean editProperty;	//abre menu de vendas
	private int cardSelected;		//carta selecionada no menu de vendas
	private int gameWinner;			//indice do vencedor
	private int amountToUnlock;     //quantidade para desbloquear a tela de vendas
	
	public GameCtrl() {
		sellingLocked = false;
		currentState = 0;
		diceUsed = false;
		isGameOn = false;
		playerIndex = 0;
		playersOn = 6;
		playerSelected = 0;
		screenSelected = 0;
		menuButton = 0;
		pauseButton = 0;
		lastScreen = 0;
		cardSelected = 0;
		editProperty = false;
		amountToUnlock = 0;
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
	
	public void setGameWinner(int gameWinner) {
		this.gameWinner = gameWinner;
	}

	public int getGameWinner() {
		return gameWinner;
	}
	
	/*************************************************************************************************/
	
	public boolean getEditProperty () {
		return editProperty;
	}
	
	public void flipEditProperty () {
		editProperty = !editProperty;
	}
	
	public void setEditProperty(boolean b) {
		editProperty = b;
	}
	
	/*************************************************************************************************/
	
	public int getCardSelected () {
		return cardSelected;
	}
	
	public void setCardSelected (int cardSelected) {
		this.cardSelected = cardSelected;
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
	public boolean isSellingLocked() {
		return sellingLocked;
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
	public int getAmountToUnlock() {
		return amountToUnlock;
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
	public void setAmountToUnlock(int amount) {
		amountToUnlock = amount;
	}
	public void setSellingLocked(boolean b) {
		sellingLocked = b;
	}
	public void lockSelling(int amount) {
		screenSelected = 3; //PROPERTIES
		sellingLocked = true;
		amountToUnlock = amount;
		editProperty = true;
	}

}
