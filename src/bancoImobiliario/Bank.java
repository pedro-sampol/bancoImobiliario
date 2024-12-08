package bancoImobiliario;

public class Bank {
	
	Constants c = new Constants();
	
	/*************************************************************************************************/
	
	//dá amount Money para o player
	public void giveMoney(Player player, int amount) {
		player.setMoney(player.getMoney() + amount);
	}
	
	/*************************************************************************************************/
	
	//retira amount Money do player
	// se o player não tiver o dinheiro
	//		-Tenta hipotecar/vender suas propriedades e companhias
	//		-Se ainda não tiver dinheiro
	//			-Player vai a falencia
	// se o player tiver dinheir
	public int takeMoney(Player player, int amount) {
		//player sem dinheiro suficinete -> falencia
		if(!player.hasMoney(amount)) {
			//player sem imoveis -> falencia
			if (player.getNumProprieties() == 0) {
				player.setBankrupt(true);
				return 0;
			} else {
				return 1;
			}	
		}	
		//player com dinheiro suficiente -> remove amount do dinheiro que ele possui
		player.setMoney(player.getMoney() - amount);
		return 2;
	}
	
	/*************************************************************************************************/
	
	//retira dinheiro de p2 e adiciona para p1
	//se p2 não tiver o suficiente
	//	P2 vai a falencia
	//	p1 recebe todo o restante do dinheiro de p2
	//retorna true se o p2 faliu
	public void exchangeMoney(Player p1, Player p2, int amount) {
		
		//player2 sem dinheiro suficiente para a troca
		this.takeMoney(p2, amount);
		if(p2.isBankrupt())
			this.giveMoney(p1, p2.getMoney());
		else
			this.giveMoney(p1, amount);
	}
	
	/*************************************************************************************************/
	
	//vende uma casa de uma propriedade e entrega o dinheiro para o player
	public void sellHouse(Player player, Property property) {
		this.giveMoney(player, property.getHousePrice());
		property.setNHouses(property.getNHouses() - 1);
	}
	
	//compra uma casa de uma propriedade e retira o dinheiro do player
	public void buyHouse(Player player, Property property) {
		if(!player.hasMoney(property.getHousePrice()) || property.getNHouses() == 5)
			return;
		
		property.setNHouses(property.getNHouses() + 1);
		this.takeMoney(player, property.getHousePrice());
	}
	
	/*************************************************************************************************/
	
	//vende uma propriedade (e todas as suas casas) e entrega o dinheiro para o player
		public void sellProperty(Player player, Board board, int index) {
			Property aux = (Property) board.getSquare(index);
			
			//vende todas as casas da propriedade
			while(aux.getNHouses() > 0)
				this.sellHouse(player, aux);
			
			//player perde a propriedade
			player.removeLocal(aux.getIndex());
			
			//player ganha o dinheiro da propriedade
			this.giveMoney(player, aux.getValue());
			
			//propiedade sem dono
			aux.setOwner(-1);
			aux.setBought(false);
			
			Square square;
			
			int monopoly = 0;
			
			int cores[] = {0, 0, 0, 0, 0, 0};
			
			//conta o numero de propriedades de cada cor
			for(int i = 0; i < player.getNumProprieties(); i++) {
				square = board.getSquare(player.getOwned(i));
				
				if(square.getType() == c.PROPERTY) {
					Property p = (Property) square;
					cores[p.getColor()] ++;
				}
			}
			
			//verifica o numero de monopolios com base na quantidade de propriedades de cada cor
			for(int i = 0; i < 6; i++) {
				if(i == c.PURPLE && cores[i] == 4)
					monopoly++;
				else if(cores[i] == 3)
					monopoly++;
			}
			
			//atualiza o numero de monopolios do player
			player.setMonopoly(monopoly);
			
		}
		
		//vende uma companhia e entrega o dinheiro ao player
		public void sellCompany(Player player, Square comp) {
			Company aux = (Company) comp;

			//player perde a company
			player.removeLocal(aux.getIndex());
			player.setNumCompanies(player.getNumCompanies() + 1);
			
			//player ganha o dinheiro da company
			this.giveMoney(player, aux.getValue());
			
			//company sem dono
			aux.setOwner(-1);
			aux.setBought(false);
		}
	
	/*************************************************************************************************/
	
		//Player compra uma company
		public void buyCompany(Player player, Company comp, int id) {
			Company aux = (Company) comp;
			
			// player ja eh dono da companhia
			if(id == aux.getOwner()) 
				return;
			
			//player sem dinheiro para a compra
			if(!player.hasMoney(aux.getValue())) 
				return;
				
			if (aux.getOwner() != -1) {
				return;
			}
			
			//player compra a company
			player.newLocal(aux.getIndex());
			player.setNumCompanies(player.getNumCompanies() - 1);
			
			//player perde o dinheiro da company
			this.takeMoney(player, aux.getValue());
			
			//company com dono
			aux.setOwner(id);
			aux.setBought(true);
			
			return;
		}
		
		//Player compra uma property
		public void buyProperty(Player player, Property property, int id) {
			//player sem dinheiro para a compra ou propriedade ja tem dono
			if(!player.hasMoney(property.getValue()) || property.getOwner() != -1 || id == property.getOwner())
				return;
			
			//player compra a propriedade
			player.newLocal(property.getIndex());
			
			//player perde o dinheiro da propriedade
			this.takeMoney(player, property.getValue());
			
			//propiedade com dono
			property.setOwner(id);
			property.setBought(true);
		}
		
		//player compra uma companhia ou uma propriedade se possuir dinheiro
		public void buyLocal(Player player, Board board, int indexSquare, int id) {
			Square square = board.getSquare(indexSquare);
			
			if(square.getType() == c.COMPANY) { //compra de companhia
				Company aux = (Company) square;
				this.buyCompany(player, aux, id);
			} else if(square.getType() == c.PROPERTY) { //compra de propriedade
				Property aux = (Property) square;
				this.buyProperty(player, aux, id);
			} else {
				return;
			}
			
			int monopoly = 0;
			
			int cores[] = {0, 0, 0, 0, 0, 0};
			
			//conta o numero de propriedades de cada cor
			for(int i = 0; i < player.getNumProprieties(); i++) {
				square = board.getSquare(player.getOwned(i));
				
				if(square.getType() == c.PROPERTY) {
					Property p = (Property) square;
					cores[p.getColor()] ++;
				}
			}
		
			//verifica o numero de monopolios com base na quantidade de propriedades de cada cor
			for(int i = 0; i < 6; i++) {
				if(i == c.PURPLE && cores[i] == 4)
					monopoly++;
				else if(cores[i] == 3)
					monopoly++;
			}
			
			player.setMonopoly(monopoly);
		}
	
	/*************************************************************************************************/
	
	//hipoteca um local
	public void mortgage(Square square, Player player) {
		Local local = (Local) square;
		
		//local já está hipotecado
		if(local.getMortgage())
			return;
		
		//local hipotecado
		local.setMortgage(true);
		
		//player recebe o dinheiro da hipoteca
		this.giveMoney(player, local.getMortgageValue());
	}
	
	//player paga 110% do valor do local e ele não está mais hipotecado
	public void payMortgage(Square square, Player player) {
		Local local = (Local) square;
		
		//verifica se o player tem dinheiro para a transação
		if(!player.hasMoney(11 * local.getMortgageValue()/10))
			return;
		
		//retira o dinheiro do player
		this.takeMoney(player, 11 * local.getMortgageValue()/10);
		
		//local não está mais hipotecado
		local.setMortgage(false);
	}
	
	/*************************************************************************************************/
	
	//player payer paga ao owner o valor por ter caido em um de seus locais
	public void payRent(Player payer, Player owner, Square square, Dice dice) {
		int value = 0;
		
		//calcula o valor caso o player caia em uma company
		//valor tirado nos dados vezes o multiplicador dessa company
		if(square.getType() == c.COMPANY) {
			Company company = (Company) square;
			value = dice.getTotalValue() * company.getMultiplier();
		} else {
			
			//valor com base no numero de casas na propriedade
			Property property = (Property) square;
			value = property.getRent(property.getNHouses());
		}
		
		//realiza a troca de dinheiro entre os players
		this.exchangeMoney(payer, owner, value);
	}
}