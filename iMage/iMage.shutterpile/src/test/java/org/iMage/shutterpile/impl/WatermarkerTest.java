package org.iMage.shutterpile.impl;

import static org.junit.Assert.assertEquals;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.iMage.shutterpile.impl.supplier.ImageWatermarkSupplier;
import org.junit.Test;

/**
 * Watermarker test cases
 * 
 * @author rusheeliyer
 *
 */
public class WatermarkerTest {

	private Watermarker watermarker;
	private ImageWatermarkSupplier supplier;
	private BufferedImage baseImage;
	private BufferedImage watermark;
	private File baseFile;
	private File watermarkFile;

	/**
	 * test that the generation of the watermarked image returns a picture of the same size os hte original base image
	 */
	@Test
	public void generateSizeTest() {
		
		baseFile = new File("src/test/resources/colorfulPicture_alpha.png");
		watermarkFile = new File("src/test/resources/pearWatermark.png");
		try {
			baseImage = ImageIO.read(baseFile);
			watermark = ImageIO.read(watermarkFile);
			supplier = new ImageWatermarkSupplier(watermark);
			watermarker = new Watermarker(supplier);
			BufferedImage finalPic = watermarker.generate(baseImage, 10);
			File outputFile = new File("src/test/resources/final.png");
			ImageIO.write(finalPic, "png", outputFile);
			assertEquals(baseImage.getWidth(), finalPic.getWidth());
			assertEquals(baseImage.getHeight(), finalPic.getHeight());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
