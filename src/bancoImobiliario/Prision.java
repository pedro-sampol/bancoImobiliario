package bancoImobiliario;

public class Prision {
	
	//envia um player para a prisao
	void sendToPrision(Player player){
		player.setInPrision(true);
		player.setTimeInPrision(0);
		player.setPosition(27);
	}
	
	//aumenta o tempo preso
	void increaseTimeInPrision(Player player) {
		player.setTimeInPrision(player.getTimeInPrision() + 1);
	}
	
	//saindo da prisao utilziando a carta de fuga da prisao
	void getOutOfJailCard(Player player) {
		player.setGetOutCards(player.getGetOutCards() - 1);
		player.setInPrision(false);
	}
	
	//player saiu da prisao por qualquer motivo
	void getOutOfPrision(Player player) {
		player.setInPrision(false);
	}
	
	//player sai da prisao pagando a taxa de 50
	void getOutwithBail(Player player, Bank bank) {
		bank.takeMoney(player, 50);
		this.getOutOfPrision(player);
	}
}