package org.iMage.shutterpile.impl.filters;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import org.iMage.shutterpile.port.IFilter;

/**
 * Scale filter implementation
 * @author rusheeliyer
 *
 */
public class ScaleFilter implements IFilter {

	@Override
	public BufferedImage apply(BufferedImage arg0) {
		//scale by half as default
		Image scaledImage = arg0.getScaledInstance(arg0.getWidth() / 2, arg0.getHeight() / 2, Image.SCALE_SMOOTH);
		int width = scaledImage.getWidth(null);
		int height = scaledImage.getHeight(null);
		
		BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics imageGraphics = newImage.getGraphics();
		imageGraphics.drawImage(scaledImage, 0, 0, null);
		imageGraphics.dispose();
		return newImage;
	}
	
	/**
	 * Method overload incase of input scale hint
	 * @param arg0 the image to which the filter is to be applied
	 * @param arg1 the scale value
	 * @return the scaled image
	 */
	public BufferedImage apply(BufferedImage arg0, float arg1) {
		if (arg1 < 1) {
			int scale = Math.round((1 / arg1));
			Image scaledImage = 
					arg0.getScaledInstance(arg0.getWidth() / scale, arg0.getHeight() / scale, Image.SCALE_SMOOTH);
			int width = scaledImage.getWidth(null);
			int height = scaledImage.getHeight(null);
			
			BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			Graphics imageGraphics = newImage.getGraphics();
			imageGraphics.drawImage(scaledImage, 0, 0, null);
			imageGraphics.dispose();
			return newImage;
		} else {
			int scale = Math.round(arg1); 
			Image scaledImage = 
					arg0.getScaledInstance(arg0.getWidth() * scale, arg0.getHeight() * scale, Image.SCALE_SMOOTH);
			int width = scaledImage.getWidth(null);
			int height = scaledImage.getHeight(null);
			
			BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			Graphics imageGraphics = newImage.getGraphics();
			imageGraphics.drawImage(scaledImage, 0, 0, null);
			imageGraphics.dispose();
			return newImage;
		}
		
	}

}
