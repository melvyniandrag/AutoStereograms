package magiceye;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CreateStereogram {

	public static void main(String[] args) {
		BufferedImage depthMap = CreateDepthMap.CreateImage();
		BufferedImage randomNoise = CreateRandomPattern.CreateImage(depthMap.getHeight(), depthMap.getWidth()/4);
		
		
		BufferedImage output = new BufferedImage(randomNoise.getWidth() * 5, CreateDepthMap.HEIGHT, BufferedImage.TYPE_INT_ARGB);
		
		// step 0: set background of output to all black.
		for(int row = 0; row < output.getHeight(); row++) {
			for(int col = 0; col < output.getWidth(); col++) {
				output.setRGB(col, row, Util.BLACK);
			}
		}
		
		
		// step 1: copy the noise to the first vertical strip of the output.
		for(int row = 0; row < output.getHeight(); row++) {
			for(int col=0; col < randomNoise.getWidth(); col++) {
				output.setRGB(col, row, randomNoise.getRGB(col,  row));				
			}
		}
		
		// step 2, 3, 4, 5: shift the pixels of the previous strip according to the whiteness or blackness
		// of the pixel in the depth map.
		final int PIXEL_SHIFT = -2;
		
		for(int iter = 1; iter < 5; iter++) {
			for(int row = 0; row < output.getHeight(); row++) {
				for(int col = iter * 25; col < (( iter + 1) * 25); col++) {
					int sourcePixel = output.getRGB(col - 25, row);
					if(depthMap.getRGB(col - 25, row) == Util.WHITE) {
						output.setRGB(col + PIXEL_SHIFT, row, sourcePixel);
					}
					else {
						output.setRGB(col, row, sourcePixel);
					}
					
				}
			}
		}
		
		Util.writeImage(output, "stereogram2.png");
		
		
	}
}
