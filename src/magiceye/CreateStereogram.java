package magiceye;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

public class CreateStereogram {
	private static final int N_STRIPS = 8;
	
	public static void main(String[] args) {
		
		//BufferedImage depthMap = CreateDepthMap.CreateImage();
        BufferedImage depthMap;
        try{
        	depthMap = ImageIO.read(new File("squirrel.png"));
        } catch (Exception e) {
        	System.err.println("Unable to read input file");
        	System.err.println(e.getMessage());
;        	return;
        }
        for(int row = 0; row < depthMap.getHeight(); row++) {
        	for(int col = 0 ; col < depthMap.getWidth(); col++) {
        		int pixel = depthMap.getRGB(col,  row);
        		if(pixel < 0xFF000000) {
        			System.err.println("found transparent pixel");
        			return;
        		}
        	}
        }
        
		
		if(depthMap.getWidth() % N_STRIPS != 0) {
			System.err.println("ERROR: Need a depthmap with width divisible by " + N_STRIPS + ".");
			return;
		}
		
		final int STRIP_WIDTH = depthMap.getWidth() / N_STRIPS;
		
		BufferedImage randomNoise = CreateRandomPattern.CreateImage(depthMap.getHeight(), STRIP_WIDTH);
		
		
		BufferedImage output = new BufferedImage(randomNoise.getWidth() * (N_STRIPS + 1),
												depthMap.getHeight(), 
												BufferedImage.TYPE_INT_ARGB);
		
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
		final int LIGHT_PIXEL_SHIFT = -4;
		final int MEDIUM_PIXEL_SHIFT = -3;
		final int DARK_PIXEL_SHIFT = -2;
		
		for(int iter = 1; iter < N_STRIPS + 1; iter++) {
			for(int row = 0; row < output.getHeight(); row++) {
				for(int col = iter * STRIP_WIDTH; col < (( iter + 1) * STRIP_WIDTH); col++) {
					int sourcePixel = output.getRGB(col - STRIP_WIDTH, row);
					if(depthMap.getRGB(col - STRIP_WIDTH, row) < 0xFF333333) {
						output.setRGB(col, row, sourcePixel);
					}
					else if(depthMap.getRGB(col - STRIP_WIDTH, row) < 0xFF888888) {
						output.setRGB(col + DARK_PIXEL_SHIFT, row, sourcePixel);
						output.setRGB(col, row, Util.getRandomPixel());
					}
					else if(depthMap.getRGB(col - STRIP_WIDTH, row) < 0xFFBBBBBB) {
						output.setRGB(col + MEDIUM_PIXEL_SHIFT, row, sourcePixel);
						output.setRGB(col, row, Util.getRandomPixel());
					}
					else {
						output.setRGB(col + LIGHT_PIXEL_SHIFT, row, sourcePixel);
						output.setRGB(col, row, Util.getRandomPixel());
					}
					
				}
			}
		}
		
		Util.writeImage(depthMap, "depthMap.png");
		Util.writeImage(randomNoise, "randomNoise.png");
		Util.writeImage(output, "stereogram2.png");
		
		
	}
}
