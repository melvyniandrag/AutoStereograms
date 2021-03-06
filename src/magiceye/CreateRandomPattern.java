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
	

	
	public static BufferedImage CreateImage(int height, int width) {
		BufferedImage randomImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		int nCols = randomImage.getWidth();
		int nRows = randomImage.getHeight();
		for (int row = 0; row < nRows; row++) {
			for (int col = 0; col < nCols; col++) {
				randomImage.setRGB(col, row, Util.getRandomPixel());
			}
		}
		return randomImage;
	}

	
	public static void main(String[] args) {
		BufferedImage randomImage = CreateImage(CreateDepthMap.HEIGHT, CreateDepthMap.WIDTH/4);
		Util.writeImage(randomImage, "randomNoise.png");
	}
	
}
