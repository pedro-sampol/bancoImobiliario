package bancoImobiliario;

import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Spritesheet {

	private BufferedImage spritesheet;
	
	public Spritesheet (String path) {
		try {
			spritesheet = ImageIO.read (getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace ();
		}
		
	}
	
	public BufferedImage getSprite (int x, int y, int length, int height) {
		return spritesheet.getSubimage (x, y, length, height);
	}
	
}
