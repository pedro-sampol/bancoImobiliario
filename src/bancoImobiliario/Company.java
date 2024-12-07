package bancoImobiliario;

import java.awt.image.BufferedImage;

public class Company extends Local {
	private int multiplier;				//Multiplicador para calculo da taxa
	private int price;					//Preco de compra
	private BufferedImage sprite;		//sprite da carta
	private BufferedImage spriteBig;	//sprite grande da carta
	
	public Company() {
		multiplier = 1;
	}
	
	public Company(BufferedImage sprite, BufferedImage spriteBig, int index, int type, int value, int multiplier, int price) {
		super(index, type, value);
		this.sprite = sprite;
		this.spriteBig = spriteBig;
		this.multiplier = multiplier;
		this.price = price;
	}
	
	/*************************************************************************************************/
	
	public int getMultiplier() {
		return multiplier;
	}
	public int getPrice() {
		return price;
	}
	public BufferedImage getSprite() {
		return this.sprite;
	}
	public BufferedImage getBigSprite() {
		return this.spriteBig;
	}
}
