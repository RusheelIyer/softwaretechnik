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
 * Test the threshold filter
 * 
 * @author rusheeliyer
 *
 */
public class ThresholdTest {

	private ThresholdFilter filter;
	private BufferedImage testImage;
	private File testFile;
	
	/**
	 * Set up before each method
	 * 
	 * @throws Exception expected exceptions
	 */
	@Before
	public void setUp() throws Exception {
		filter = new ThresholdFilter();
	}

	@Test
	public void testApply() {
		testFile = new File("src/test/resources/tichyWatermark_input_no_alpha.png");
		try {
			testImage = ImageIO.read(testFile);
			BufferedImage newImage = filter.apply(testImage);
			for (int x = 0; x < testImage.getWidth(); x++) {
				for (int y = 0; y < testImage.getHeight(); y++) {
					Color pixel = new Color(testImage.getRGB(x, y));
					int average = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
					File outputFile = new File("src/test/resources/tichy.png");
					ImageIO.write(newImage, "png", outputFile);
					if (average > 127) {
						assertEquals(0, newImage.getRGB(x, y));
					} else {
						assertEquals(pixel.getRGB(), newImage.getRGB(x, y));
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			fail("Image read error");
		}
	}

	@Test
	public void testApplyValue() {
		testFile = new File("src/test/resources/tichyWatermark_input_no_alpha.png");
		try {
			testImage = ImageIO.read(testFile);
			BufferedImage newImage = filter.apply(testImage, 50);
			for (int x = 0; x < testImage.getWidth(); x++) {
				for (int y = 0; y < testImage.getHeight(); y++) {
					Color pixelColor = new Color(testImage.getRGB(x, y));
					int average = (pixelColor.getRed() + pixelColor.getGreen() + pixelColor.getBlue()) / 3;
					File outputFile = new File("src/test/resources/tichy1.png");
					ImageIO.write(newImage, "png", outputFile);
					if (average > 128) {
						assertEquals(0, newImage.getRGB(x, y));
					} else {
						assertEquals(testImage.getRGB(x, y), newImage.getRGB(x, y));
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			fail("Image read error");
		}
	}

}
