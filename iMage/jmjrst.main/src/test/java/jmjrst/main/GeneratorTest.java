package jmjrst.main;

import static org.junit.Assert.fail;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.jis.generator.Generator;
import org.junit.Before;
import org.junit.Test;

public class GeneratorTest {

	private Generator generator;
	private BufferedImage image;
	
	@Before
	public void setUp() throws Exception {
		
		generator = new Generator(null, 0);
		File imageFile = new File("src/test/resources/picture.jpg");
		image = ImageIO.read(imageFile);
		
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
