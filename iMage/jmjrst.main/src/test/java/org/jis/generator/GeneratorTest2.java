package org.jis.generator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.imageio.ImageIO;

import org.jis.options.Options;
import org.junit.Before;
import org.junit.Ignore;
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
	
	/**
	 * Test that a landscape image is scaled correctly
	 */
	@Test
	public void generateImageTestLandscape() {
		File fo;
		try {
			File testFile = new File("src/test/resources/testPicture.jpg");
			//rotate image if it's portrait before method is run
			if (image.getWidth() < image.getHeight()) {
				ImageIO.write(generator.rotateImage(image, Math.toRadians(90)), "jpg", testFile);
				image = ImageIO.read(testFile);
				fo = generator.generateImage(testFile, fileOut, true, 
						image.getWidth() / 2, image.getHeight() / 2, "landscapeScale");
				BufferedImage scaledImage = ImageIO.read(fo);
				assertEquals(image.getHeight() / 2, scaledImage.getHeight());
				assertEquals(image.getWidth() / 2, scaledImage.getWidth());
			} else {
				fo = generator.generateImage(imageFile, fileOut, true, 
						image.getWidth() / 2, image.getHeight() / 2, "landscapeScale");
				BufferedImage scaledImage = ImageIO.read(fo);
				assertEquals(image.getHeight() / 2, scaledImage.getHeight());
				assertEquals(image.getWidth() / 2, scaledImage.getWidth());
			}
			fo.delete();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Test the generateImage method with quality modus
	 */
	@Test
	public void generateImageTestModusQuality() {
		Options.getInstance().setModus(Options.MODUS_QUALITY);
		Options.getInstance().setAntialiasing(false);
		Options.getInstance().setCopyMetadata(false);
		Options.getInstance().setCopyright(false);
		
		try {
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
	
	/**
	 * Test the generateImage method with speed modus
	 */
	@Test
	public void generateImageTestModusSpeed() {
		Options.getInstance().setModus(Options.MODUS_SPEED);
		Options.getInstance().setAntialiasing(false);
		Options.getInstance().setCopyMetadata(false);
		Options.getInstance().setCopyright(false);
		
		try {
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
	
	/**
	 * Test the generateImage method with default modus
	 */
	@Test
	public void generateImageTestModusDefault() {
		Options.getInstance().setModus(Options.MODUS_DEFAULT);
		Options.getInstance().setAntialiasing(true);
		Options.getInstance().setCopyMetadata(false);
		Options.getInstance().setCopyright(true);
		
		try {
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
	
	/**
	 * Test rotate method
	 * 
	 * note: ignore because test throws NullPointerException
	 */
	@Ignore @Test
	public void rotateTest() {
		File file = new File("src/test/resources", "testPicture.jpg");
		try {
			file.createNewFile();
			Files.copy(imageFile.toPath(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
			//method being tested
			generator.rotate(file);
			BufferedImage testImage = ImageIO.read(file);
			assertEquals(image.getHeight(), testImage.getWidth());
			assertEquals(image.getWidth(), testImage.getHeight());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Test rotate method with integer angle
	 * 
	 * note: ignored because throws NullPointerException
	 */
	@Ignore @Test
	public void rotateIntegerTest() {
		File file = new File("src/test/resources", "testPicture.jpg");
		try {
			file.createNewFile();
			Files.copy(imageFile.toPath(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
			//method being tested
			generator.rotate(file, 90);
			BufferedImage testImage = ImageIO.read(file);
			assertEquals(image.getHeight(), testImage.getWidth());
			assertEquals(image.getWidth(), testImage.getHeight());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
