package org.iMage.shutterpile.impl.filters;

import java.awt.Color;
import java.awt.image.BufferedImage;

import org.iMage.shutterpile.port.IFilter;

/**
 * Threshold filter implementation
 * @author rusheeliyer
 *
 */
public class ThresholdFilter implements IFilter {

	@Override
	public BufferedImage apply(BufferedImage arg0) {
		
		for (int x = 0; x < arg0.getWidth(); x++) {
			for (int y = 0; y < arg0.getHeight(); y++) {
				Color pixel = new Color(arg0.getRGB(x, y));
				int average = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
				if (average > 127) {
					arg0.setRGB(x, y, 0);
				}
			}
		}
		return arg0;
	}

	/**
	 * Method overload incase of input threshold value
	 * @param arg0 the image to which the filter is to be applied
	 * @param arg1 the threshold value
	 * @return the image with the filter
	 */
	public BufferedImage apply(BufferedImage arg0, int arg1) {
		if ((arg1 < 0) || (arg1 > 255)) {
			throw new IllegalArgumentException("Please enter a valid threshold value");
		} else {
			for (int x = 0; x < arg0.getWidth(); x++) {
				for (int y = 0; y < arg0.getHeight(); y++) {
					Color pixel = new Color(arg0.getRGB(x, y));
					int average = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
					if (average > arg1) {
						arg0.setRGB(x, y, 0);
					}
				}
			}
		}
		return arg0;
	}
	
}