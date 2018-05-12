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
		for (int x = 0; x < arg0.getWidth(); x++) {
			for (int y = 0; y < arg0.getHeight(); y++) {
				Color pixelColor = new Color(arg0.getRGB(x, y));
				int newValue = ((pixelColor.getRed() + pixelColor.getGreen() + pixelColor.getBlue()) / 3);
				pixelColor = new Color(newValue, newValue, newValue);
				arg0.setRGB(x, y, pixelColor.getRGB());
			}
		}
		return arg0;
	}

}
