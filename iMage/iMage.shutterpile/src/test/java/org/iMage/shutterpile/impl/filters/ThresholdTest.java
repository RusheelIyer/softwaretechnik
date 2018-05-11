package org.iMage.shutterpile.impl.filters;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

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
		testFile = new File("src/test/resources/tichyWatermark_input_no_alpha.png");
		testImage = ImageIO.read(testFile);
	}

	/**
	 * Test the threshold filter application without specific threshold value
	 */
	@Test
	public void testApply() {
		BufferedImage newImage = filter.apply(testImage);
		for (int x = 0; x < testImage.getWidth(); x++) {
			for (int y = 0; y < testImage.getHeight(); y++) {
				Color pixel = new Color(testImage.getRGB(x, y));
				int average = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
				if (average > 127) {
					assertEquals(0, newImage.getRGB(x, y));
				} else {
					assertEquals(pixel.getRGB(), newImage.getRGB(x, y));
				}
			}
		}
	}

	/**
	 * Test the threshold filter application without specific threshold value
	 */
	@Test
	public void testApplyValue() {
		BufferedImage newImage = filter.apply(testImage, 100);
		for (int x = 0; x < testImage.getWidth(); x++) {
			for (int y = 0; y < testImage.getHeight(); y++) {
				Color pixel = new Color(testImage.getRGB(x, y));
				int average = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
				if (average > 100) {
					assertEquals(0, newImage.getRGB(x, y));
				} else {
					assertEquals(pixel.getRGB(), newImage.getRGB(x, y));
				}
			}
		}
	}
	
	/**
	 * Test that illegal values (> 255) are not excepted
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testApplyIllegalValuePositive() {
		filter.apply(testImage, 256);
	}
	
	/**
	 * Test that illegal values (< 0) are not excepted
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testApplyIllegalValueNegative() {
		filter.apply(testImage, -2);
	}

}
