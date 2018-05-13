package org.iMage.shutterpile.impl;

import java.awt.image.BufferedImage;

import org.iMage.shutterpile.impl.filters.WatermarkFilter;
import org.iMage.shutterpile.port.IWatermarkSupplier;
import org.iMage.shutterpile.port.IWatermarker;

/**
 * This class realizes a {@link IWatermarker} which uses {@link BufferedImage BufferedImages} as
 * watermark.
 *
 * @author Dominic Wolff
 *
 */
public class Watermarker implements IWatermarker {

	private BufferedImage watermark;
	
  /**
   * Create the watermarker by {@link IWatermarkSupplier}.
   *
   * @param iws
   *          the watermark supplier
   */
  public Watermarker(IWatermarkSupplier iws) {
	  
	  this.watermark = iws.getWatermark();
	  
  }

  @Override
  public BufferedImage generate(BufferedImage input, int watermarksPerRow) {
    WatermarkFilter filter = new WatermarkFilter(this.watermark, watermarksPerRow);
    return filter.apply(input);
  }

}
