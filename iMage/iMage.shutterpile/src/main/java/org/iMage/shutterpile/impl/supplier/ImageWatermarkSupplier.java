package org.iMage.shutterpile.impl.supplier;

import java.awt.Color;
import java.awt.image.BufferedImage;

import org.iMage.shutterpile.impl.filters.GrayscaleFilter;
import org.iMage.shutterpile.impl.filters.ThresholdFilter;
import org.iMage.shutterpile.port.IWatermarkSupplier;

/**
 * This class realizes a {@link IWatermarkSupplier} which uses a {@link BufferedImage} to generate
 * the watermark.
 *
 * @author Dominic Wolff
 *
 */
public final class ImageWatermarkSupplier implements IWatermarkSupplier {
	
	private BufferedImage watermarkInput;

  /**
   * Create the {@link IWatermarkSupplier} by base image of watermark.
   *
   * @param watermarkInput
   *          the base image to create the watermark
   */
  public ImageWatermarkSupplier(BufferedImage watermarkInput) {
	  GrayscaleFilter grayscale = new GrayscaleFilter();
	  ThresholdFilter threshold = new ThresholdFilter();
	  watermarkInput = threshold.apply(grayscale.apply(watermarkInput));
	  
	  if (!this.watermarkInput.getColorModel().hasAlpha()) {
			for (int x = 0; x < this.watermarkInput.getWidth(); x++) {
				for (int y = 0; y < this.watermarkInput.getHeight(); y++) {
					this.watermarkInput.setRGB(x, y, this.watermarkInput.getRGB(x, y) & 0x00FFFFFF);
				}
			}
		}
	  
  }

  @Override
  public BufferedImage getWatermark() {
    
	  BufferedImage watermark = this.watermarkInput;
	  for (int x = 0; x < watermark.getWidth(); x++) {
			for (int y = 0; y < watermark.getHeight(); y++) {
				Color pixel = new Color(watermark.getRGB(x, y));
				int newAlpha = pixel.getAlpha() / 2;
				pixel = new Color(pixel.getRed(), pixel.getGreen(), pixel.getBlue(), newAlpha);
				watermark.setRGB(x, y, pixel.getRGB());
			}
		}
	  
	  return watermark;
	  
  }

}
