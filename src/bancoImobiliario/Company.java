package bancoImobiliario;

import java.awt.image.BufferedImage;

public class Company extends Local {
	private int multiplier;				//Multiplicador para calculo da taxa
	private BufferedImage sprite;		//sprite da carta
	private BufferedImage spriteBig;	//sprite grande da carta
	
	public Company() {
		multiplier = 1;
	}
	
	public Company(BufferedImage sprite, BufferedImage spriteBig, int index, int type,  int multiplier, int value, int mortgage) {
		super(index, type, value, mortgage);
		this.sprite = sprite;
		this.spriteBig = spriteBig;
		this.multiplier = multiplier;
	}
	
	/*************************************************************************************************/
	
	public int getMultiplier() {
		return multiplier;
	}
	public BufferedImage getSprite() {
		return this.sprite;
	}
	public BufferedImage getBigSprite() {
		return this.spriteBig;
	}
}
