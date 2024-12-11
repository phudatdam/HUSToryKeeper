package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class UtilityTool {
	private static BufferedImage scaleImage(BufferedImage original, int width, int height) {
		BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
		Graphics2D g2 = scaledImage.createGraphics();
		g2.drawImage(original, 0, 0, width, height, null);
		g2.dispose();
		
		return scaledImage;
	}
	
	public static BufferedImage setup(String imagePath, int width, int height) {
		BufferedImage image = null;
		try {
			// Fetch ảnh gốc
			image = ImageIO.read(UtilityTool.class.getResourceAsStream(imagePath + ".png"));
			// Scale ảnh gốc
			image = scaleImage(image, width, height);
		} catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}
}
