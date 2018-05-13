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
	  this.watermarkInput = threshold.apply(grayscale.apply(watermarkInput));
	  
	  if (!this.watermarkInput.getColorModel().hasAlpha()) {
			BufferedImage newImage = new BufferedImage(this.watermarkInput.getWidth(), 
					this.watermarkInput.getHeight(), BufferedImage.TYPE_INT_ARGB);
			for (int x = 0; x < newImage.getWidth(); x++) {
				for (int y = 0; y < newImage.getHeight(); y++) {
					Color watermarkColor = new Color(this.watermarkInput.getRGB(x, y), true);
					int red = watermarkColor.getRed();
					int green = watermarkColor.getGreen();
					int blue = watermarkColor.getBlue();
					int alpha = 255;
					int argb = (alpha << 24) | (red << 16) | (green << 8) | (blue);
					newImage.setRGB(x, y, argb);
				}
			}
			this.watermarkInput = newImage;
		}
	  
  }

  @Override
  public BufferedImage getWatermark() {
    
	  BufferedImage watermark = this.watermarkInput;
	  for (int x = 0; x < watermark.getWidth(); x++) {
			for (int y = 0; y < watermark.getHeight(); y++) {
				Color pixel = new Color(watermark.getRGB(x, y), true);
				int red = pixel.getRed();
				int green = pixel.getGreen();
				int blue = pixel.getBlue();
				int newAlpha = pixel.getAlpha() / 2;
				Color newColor = new Color(red, green, blue, newAlpha);
				watermark.setRGB(x, y, newColor.getRGB());
			}
		}
	  return watermark;
  }

}
