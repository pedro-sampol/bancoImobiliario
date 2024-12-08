package bancoImobiliario;

public class Prision {
	
	//envia um player para a prisao
	void sendToPrision(Player player, int index){
		player.setInPrision(true);
		player.setTimeInPrision(0);
		player.setPosition(27);
		player.pickCoords(index);
	}
	
	/*************************************************************************************************/
	
	//aumenta o tempo preso
	void increaseTimeInPrision(Player player) {
		player.setTimeInPrision(player.getTimeInPrision() + 1);
	}
	
	/*************************************************************************************************/
		
	//player saiu da prisao por qualquer motivo
	void getOutOfPrision(Player player) {
		player.setInPrision(false);
	}
	
	//player sai da prisao pagando a taxa de 150
	void getOutwithBail(Player player, Bank bank) {
		bank.takeMoney(player, 150);
		this.getOutOfPrision(player);
	}
}