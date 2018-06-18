package org.iMage.shutterpile.impl.supplier;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * create watermark images out of text
 * @author rusheeliyer
 *
 */
public class TextWatermarkSupplier extends AbstractWatermarkSupplier{

	private String watermarkText;
	
	/**
	 * constructor
	 * @param text the text to be converted into a watermark
	 */
	public TextWatermarkSupplier(String text) {
		this.watermarkText = text;
	}
	
	@Override
	public BufferedImage getWatermark() {
		
		BufferedImage watermark = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = watermark.createGraphics();
		Font font = new Font("Arial", Font.PLAIN, 48);
		g2d.setFont(font);
		FontMetrics fm = g2d.getFontMetrics();
		int width = fm.stringWidth(this.watermarkText);
		g2d.dispose();
		
		watermark = new BufferedImage(width, 100, BufferedImage.TYPE_INT_ARGB);
		g2d = watermark.createGraphics();
		fm = g2d.getFontMetrics();
		g2d.setColor(Color.BLACK);
		g2d.drawString(this.watermarkText, 0, fm.getAscent());
		g2d.dispose();
		return watermark;
		
	}

}
