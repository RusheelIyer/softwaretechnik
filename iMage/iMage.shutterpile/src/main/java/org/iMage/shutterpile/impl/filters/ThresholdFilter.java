package org.iMage.shutterpile.impl.filters;

import java.awt.Color;
import java.awt.Graphics;
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
		
		BufferedImage newImage = new BufferedImage(arg0.getWidth(), arg0.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics g = newImage.getGraphics();
		g.drawImage(arg0, 0, 0, newImage.getWidth(), newImage.getHeight(), null);
		g.dispose();
		for (int x = 0; x < arg0.getWidth(); x++) {
			for (int y = 0; y < arg0.getHeight(); y++) {
				Color pixel = new Color(arg0.getRGB(x, y));
				int average = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
				if (average > 127) {
					newImage.setRGB(x, y, 0);
				}
			}
		}
		return newImage;
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
			BufferedImage newImage = new BufferedImage(arg0.getWidth(), arg0.getHeight(), BufferedImage.TYPE_INT_ARGB);
			Graphics g = newImage.getGraphics();
			g.drawImage(arg0, 0, 0, newImage.getWidth(), newImage.getHeight(), null);
			g.dispose();
			for (int x = 0; x < arg0.getWidth(); x++) {
				for (int y = 0; y < arg0.getHeight(); y++) {
					Color pixel = new Color(arg0.getRGB(x, y));
					int average = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
					if (average > arg1) {
						newImage.setRGB(x, y, 0);
					}
				}
			}
			return newImage;
		}
	}
	
}
