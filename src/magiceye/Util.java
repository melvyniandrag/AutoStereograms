package magiceye;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Util {
	
	public static final int BLACK = 0xFF000000;
	public static final int WHITE = 0xFFFFFFFF;
	
	private static boolean getRandomBoolean() {
		Random rd = new Random();
	    return rd.nextBoolean();
	}
	
	public static int getRandomPixel() {
		if(getRandomBoolean()) {
			return BLACK;
		}else {
			return WHITE;
		}
	}
	
	public static void writeImage(BufferedImage image, String path) {
		File ImageFile = new File(path);
		try {
			ImageIO.write(image, "png", ImageFile);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
