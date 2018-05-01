package jmjrst.main;

import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.jis.generator.Generator;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing Generator Class code
 * @author rusheel iyer
 *
 */
public class GeneratorTest {

	private Generator generator;
	private BufferedImage image;
	
	/**
	 * set up before each test case
	 * @throws Exception exceptions
	 */
	@Before
	public void setUp() throws Exception {
		
		generator = new Generator(null, 0);
		File imageFile = new File("src/test/resources/picture.jpg");
		image = ImageIO.read(imageFile);
		
	}

	/**
	 * check that an image remains unchanged when the value it is to be rotated by = 0
	 */
	@Test
	public void rotateImageTest1() {
		//check if same image reference, because new image created when image is rotated.
		assertTrue(generator.rotateImage(image, 0.0) == image);
	}
	
	/**
	 * check that a null image does not affect the outcome of the rotate image method
	 */
	@Test
	public void rotateImageTest2() {
		assertTrue(generator.rotateImage(null, 0.0) == null);
	}
	
	

}
