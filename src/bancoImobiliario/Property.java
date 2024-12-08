package bancoImobiliario;

import java.awt.image.BufferedImage;

public class Property extends Local {
	private int color;					//cor da propriedade
	private int n_houses;				//numero de casas construidas neste local
	private int house_price;			//custo de cada casa (compra e venda)
	private int[] rent = new int[6];	//custo do aluguel com base no numero de casas
	private BufferedImage sprite;		//sprite da carta
	private BufferedImage spriteBig;	//sprite grande da carta
	
	public Property(BufferedImage sprite, BufferedImage spriteBig,int index, int type, int color,  int mortgage, int value, int c0, int c1, int c2, int c3, int c4, int c5, int house_price) {
		super(index, type, value, mortgage);
		this.sprite = sprite;
		this.spriteBig = spriteBig;
		this.color = color;
		this.house_price = house_price;
		n_houses = 0;
		rent[0] = c0;
		rent[1] = c1;
		rent[2] = c2;
		rent[3] = c3;
		rent[4] = c4;
		rent[5] = c5;
	}
	
	/*************************************************************************************************/
	
	public int getColor() {
		return color;
	}
	public int getNHouses() {
		return n_houses;
	}
	public int getHousePrice() {
		return house_price;
	}
	public int getRent(int index) {
		return rent[index];
	}
	
	public BufferedImage getSprite() {
		return this.sprite;
	}
	
	public BufferedImage getBigSprite() {
		return this.spriteBig;
	}
	
	/*************************************************************************************************/
	
	public void setNHouses(int n) {
		this.n_houses = n;
	}

}
