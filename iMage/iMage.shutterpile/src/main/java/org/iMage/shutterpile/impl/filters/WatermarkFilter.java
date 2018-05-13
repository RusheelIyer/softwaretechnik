package org.iMage.shutterpile.impl.filters;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
    
	BufferedImage watermarkedImage = input;
    ScaleFilter scale = new ScaleFilter();
    double watermarkWidth = input.getWidth() / this.watermarksPerRow;
    double scaleValue = watermarkWidth / this.watermark.getWidth();
    BufferedImage newWatermark = scale.apply(this.watermark, scaleValue);
    
    BufferedImage tiledWatermark = 
    		new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_INT_ARGB);
    Graphics g = tiledWatermark.getGraphics();
    for (int y = 0; y < tiledWatermark.getHeight(); y = y + newWatermark.getHeight()) {
    	for (int x = 0; x < tiledWatermark.getWidth(); x = x + newWatermark.getWidth()) {
    		g.drawImage(newWatermark, x, y, null);
    	}
    }
    g.dispose();
    
    Graphics2D g2d = watermarkedImage.createGraphics();
    AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER);
    g2d.setComposite(ac);
    int x = (watermarkedImage.getWidth() - tiledWatermark.getWidth()) / 2;
    int y = (watermarkedImage.getHeight() - tiledWatermark.getHeight()) / 2;
    g2d.drawImage(tiledWatermark, x, y, null);
    g2d.dispose();
    
    return watermarkedImage;
  }

}
