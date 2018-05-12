package org.iMage.shutterpile.impl.filters;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import org.iMage.shutterpile.impl.supplier.ImageWatermarkSupplier;
import org.iMage.shutterpile.port.IFilter;
import org.iMage.shutterpile.port.IWatermarkSupplier;

/**
 * This {@link IFilter Filter} adds a watermark ({@link BufferedImage}) to an image.
 *
 * @author Dominic Wolff
 *
 */
public final class WatermarkFilter implements IFilter {
	
	private BufferedImage watermark;
	private int watermarksPerRow;
  /**
   * Create a the WatermarkFilter.
   *
   * @param watermark
   *          the watermark image as provided by a {@link IWatermarkSupplier}.
   * @param watermarksPerRow
   *          the number of watermarks in a line (this is meant as desired value. the possible
   *          surplus is drawn)
   */
  public WatermarkFilter(BufferedImage watermark, int watermarksPerRow) {
	  
	  this.watermark = watermark;
	  this.watermarksPerRow = watermarksPerRow;
	  
	  ImageWatermarkSupplier watermarkSupplier = new ImageWatermarkSupplier(this.watermark);
	  this.watermark = watermarkSupplier.getWatermark();
	  
  }

  @Override
  public BufferedImage apply(BufferedImage input) {
    
    ScaleFilter scale = new ScaleFilter();
    double watermarkWidth = input.getWidth() / this.watermarksPerRow;
    double scaleValue = watermarkWidth / this.watermark.getWidth();
    BufferedImage newWatermark = scale.apply(this.watermark, scaleValue);
    
    BufferedImage watermarkedImage = 
    		new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_INT_ARGB);
    Graphics g = watermarkedImage.getGraphics();
    Image scaled = newWatermark;
    for (int y = 0; y < watermarkedImage.getHeight(); y = y + scaled.getHeight(null)) {
    	for (int x = 0; x < watermarkedImage.getWidth(); x = x + scaled.getWidth(null)) {
    		g.drawImage(scaled, x, y, null);
    	}
    }
    g.dispose();
    
    return watermarkedImage;
  }

}
