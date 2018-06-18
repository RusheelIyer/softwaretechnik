package org.iMage.shutterpile.impl.filters;

import java.awt.image.BufferedImage;

import org.iMage.shutterpile.impl.supplier.ImageWatermarkSupplier;
import org.iMage.shutterpile.port.IFilter;

/**
 * alpha filter IFilter
 * @author rusheeliyer
 *
 */
public class AlphaFilter implements IFilter {

	@Override
	public BufferedImage apply(BufferedImage arg0) {
		BufferedImage image = arg0;
		for (int i = 0; i < image.getWidth(); i++) {
		      for (int q = 0; q < image.getHeight(); q++) {
		        int color = image.getRGB(i, q);
		        int alpha = color >> 24 & 0x000000FF;
		        alpha = (alpha * ImageWatermarkSupplier.DEFAULT_FACTOR) / 100;
		        image.setRGB(i, q, (color & 0x00FFFFFF) | (alpha << 24));
		      }
		    }
		    image.flush();
		    return image;
	}

}
