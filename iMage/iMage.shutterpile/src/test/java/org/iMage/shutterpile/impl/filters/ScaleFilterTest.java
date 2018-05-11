package org.iMage.shutterpile.impl.filters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.junit.Before;
import org.junit.Test;

/**
 * Test the scale filter
 * 
 * @author rusheeliyer
 *
 */
public class ScaleFilterTest {

	private ScaleFilter filter;
	private BufferedImage testImage;
	private File testFile;
	
	/**
	 * Set up before each method
	 * 
	 * @throws Exception expected exceptions
	 */
	@Before
	public void setUp() throws Exception {
		filter = new ScaleFilter();
		testFile = new File("src/test/resources/tichyWatermark_input_no_alpha.png");
		testImage = ImageIO.read(testFile);
	}

	/**
	 * Test the scale filter application without specific scale value
	 */
	@Test
	public void testApplyBufferedImage() {
		BufferedImage newImage = filter.apply(testImage);
		assertEquals(testImage.getWidth() / 2, newImage.getWidth());
		assertEquals(testImage.getHeight() / 2, newImage.getHeight());
	}

	/**
	 * Test the scale filter application with specific scale value
	 */
	@Test
	public void testApplyBufferedImageValue() {
		BufferedImage newImage = filter.apply(testImage, 2.01f);
		assertEquals(testImage.getWidth() * 2, newImage.getWidth());
		assertEquals(testImage.getHeight() * 2, newImage.getHeight());
	}

}
