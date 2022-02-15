/**
 * CreateRandomPattern will create a random pattern to be used 
 * for creating our random dot autostereogram.
 * 
 */

package magiceye;

import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class CreateRandomPattern {
	public static final int HEIGHT = CreateDepthMap.HEIGHT;
	public static final int WIDTH = CreateDepthMap.WIDTH / 4;
	private static final int WHITE = 0xFFFFFFFF;
	private static final int BLACK = 0xFF000000;
	
	private static boolean getRandomBoolean() {
		Random rd = new Random();
	    return rd.nextBoolean();
	}
	
	public static BufferedImage CreateImage() {
		BufferedImage randomImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		int width = randomImage.getWidth();
		int height = randomImage.getHeight();
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				if(getRandomBoolean()) {
					randomImage.setRGB(col, row, WHITE);
				}
				else {
					randomImage.setRGB(col, row, BLACK);
				}
			}
		}
		return randomImage;
	}
	
	public static void writeImage(BufferedImage image) {
		String path = "random.png";
		File ImageFile = new File(path);
		try {
			ImageIO.write(image, "png", ImageFile);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		BufferedImage randomImage = CreateImage();
		writeImage(randomImage);
	}
	
}
