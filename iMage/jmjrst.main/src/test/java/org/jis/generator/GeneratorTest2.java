package org.jis.generator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author rusheeliyer
 *
 */
public class GeneratorTest2 {

	private Generator generator;
	private File imageFile;
	private BufferedImage image;
	private File fileOut;

	/**
	 * Set up method executed before test cases
	 * @throws Exception exceptions
	 */
	@Before
	public void setUp() throws Exception {
		generator = new Generator(null, 0);
		imageFile = new File("src/test/resources/picture.jpg");
		image = ImageIO.read(imageFile);
		fileOut = new File("target/dataTest");
	}

	/**
	 * Test that a portrait image is scaled correctly
	 */
	@Test
	public void generateImageTestPortrait() {
		try {
			//Method being tested
			File fo = generator.generateImage(imageFile, fileOut, true, 
					image.getWidth() / 2, image.getHeight() / 2, "portraitScale");
			BufferedImage scaledImage = ImageIO.read(fo);
			assertEquals(image.getHeight() / 2, scaledImage.getHeight());
			assertEquals(image.getWidth() / 2, scaledImage.getWidth());
			fo.delete();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
