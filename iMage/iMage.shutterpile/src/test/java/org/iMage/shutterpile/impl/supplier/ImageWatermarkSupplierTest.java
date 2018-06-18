package org.iMage.shutterpile.impl.supplier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.iMage.shutterpile.impl.filters.GrayscaleFilter;
import org.iMage.shutterpile.impl.filters.ThresholdFilter;
import org.junit.Ignore;
import org.junit.Test;

/**
 * ImageWatermarkSupplier test cases
 * @author rusheeliyer
 *
 */
public class ImageWatermarkSupplierTest {

	private ImageWatermarkSupplier iws;
	private File watermarkFile;
	private BufferedImage watermark;
	private GrayscaleFilter grayscale;
	private ThresholdFilter threshold;
	

	/**
	 * test that alpha channel is created for watermark with no alpha
	 */
	@Test
	public void testAlpha() {
		
		watermarkFile = new File("src/test/resources/tichyWatermark_input_no_alpha.png");
		try {
			watermark = ImageIO.read(watermarkFile);
			//confirming that the image does not have an alpha prior to change
			assertFalse(watermark.getColorModel().hasAlpha());
			iws = new ImageWatermarkSupplier(watermark);
			assertTrue(iws.getWatermark().getColorModel().hasAlpha());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * test that alpha channel is reduced by half
	 */
	@Test @Ignore 
	public void testAlpha2() {
		grayscale = new GrayscaleFilter();
		threshold = new ThresholdFilter();
		watermarkFile = new File("src/test/resources/pearWatermark_input_alpha.png");
		try {
			watermark = ImageIO.read(watermarkFile);
			iws = new ImageWatermarkSupplier(watermark);
			BufferedImage oldWatermark = threshold.apply(grayscale.apply(watermark));
			watermark = iws.getWatermark();
			for (int x = 0; x < watermark.getWidth(); x++) {
				for (int y = 0; y < watermark.getHeight(); y++) {
					Color color = new Color(oldWatermark.getRGB(x, y), true);
					Color watermarkColor = new Color(watermark.getRGB(x, y), true);
					assertEquals(color.getAlpha() / 2, watermarkColor.getAlpha());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
