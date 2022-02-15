/**
 * Create the depth map image we want to hide in our 
 * random dot autostereogram
 * 
 * This depth map just generates a 70x100 black and white image of a 5
 * ... or an S. Just a white shape on a black backdrop.
 */
package magiceye;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CreateDepthMap {
	public static final int HEIGHT = 70;
	public static final int WIDTH = 100;
	
	public static BufferedImage CreateImage() {
		BufferedImage depthMap = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);

		int width = depthMap.getWidth();
		int height = depthMap.getHeight();
		
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				depthMap.setRGB(col, row, Util.BLACK);
			}
		}
		
		for (int row = 10; row < 20; row++) {
			for (int col = 25; col < 75; col++) {
				depthMap.setRGB(col, row, Util.WHITE);
			}
		}
		
		for (int row = 20; row < 30; row++) {
			for (int col = 25; col < 50; col++) {
				depthMap.setRGB(col, row, Util.WHITE);
			}
		}
		for (int row = 30; row < 40; row++) {
			for (int col = 25; col < 75; col++) {
				depthMap.setRGB(col, row, Util.WHITE);
			}
		}
		for (int row = 40; row < 50; row++) {
			for (int col = 50; col < 75; col++) {
				depthMap.setRGB(col, row, Util.WHITE);
			}
		}
		for (int row = 50; row < 60; row++) {
			for (int col = 25; col < 75; col++) {
				depthMap.setRGB(col, row, Util.WHITE);
			}
		}
		
		return depthMap;
	}
	
	public static void main(String[] args) {
		BufferedImage depthMap = CreateImage();
		Util.writeImage(depthMap, "depthMap.png");
	}
	
}
