package org.iMage.shutterpile.impl.filters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Before;
import org.junit.Test;

/**
 * test the implementation of the grayscale filter
 * 
 * @author rusheeliyer
 *
 */
public class GrayscaleFilterTest {

	private GrayscaleFilter filter;
	
	/**
	 * Set up before each method
	 * 
	 * @throws Exception expected exceptions
	 */
	@Before
	public void setUp() throws Exception {
		
		filter = new GrayscaleFilter();
	}

	/**
	 * Test implementation for picture without alpha
	 */
	@Test
	public void applyTestNoAlpha() {
		File testFile = new File("src/test/resources/tichyWatermark_input_no_alpha.png");
		try {
			BufferedImage testImage = ImageIO.read(testFile);
			BufferedImage newImage = filter.apply(testImage);
			for (int x = 0; x < testImage.getWidth(); x++) {
				for (int y = 0; y < testImage.getHeight(); y++) {
					Color pixelColor = new Color(testImage.getRGB(x, y));
					int newValues = (pixelColor.getRed() + pixelColor.getGreen() + pixelColor.getBlue()) / 3;
					pixelColor = new Color(newValues, newValues, newValues);
					assertEquals(pixelColor.getRGB(), newImage.getRGB(x, y));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			fail("Image read error");
		}
	}
	
	/**
	 * Test implementation for picture with alpha
	 */
	@Test
	public void applyTestAlpha() {
		File testFile = new File("src/test/resources/pearWatermark_input_alpha_small.png");
		try {
			BufferedImage testImage = ImageIO.read(testFile);
			BufferedImage newImage = filter.apply(testImage);
			for (int x = 0; x < testImage.getWidth(); x++) {
				for (int y = 0; y < testImage.getHeight(); y++) {
					Color pixelColor = new Color(testImage.getRGB(x, y));
					int newValues = (pixelColor.getRed() + pixelColor.getGreen() + pixelColor.getBlue()) / 3;
					pixelColor = new Color(newValues, newValues, newValues);
					assertEquals(pixelColor.getRGB(), newImage.getRGB(x, y));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			fail("Image read error");
		}
	}

}
