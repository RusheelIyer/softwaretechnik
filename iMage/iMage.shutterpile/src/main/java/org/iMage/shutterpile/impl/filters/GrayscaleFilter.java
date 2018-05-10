package org.iMage.shutterpile.impl.filters;

import java.awt.Color;
/**
 * import BufferedImage
 */
import java.awt.image.BufferedImage;

import org.iMage.shutterpile.port.IFilter;

/**
 * Grayscale filter implementation
 * 
 * @author rusheeliyer
 *
 */
public class GrayscaleFilter implements IFilter {

	@Override
	public BufferedImage apply(BufferedImage arg0) {
		BufferedImage image = arg0;
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				Color pixelColor = new Color(image.getRGB(x, y));
				int newRGB = (pixelColor.getRed() + pixelColor.getGreen() + pixelColor.getBlue()) / 3;
				image.setRGB(x, y, newRGB);
			}
		}
		return image;
	}

}
