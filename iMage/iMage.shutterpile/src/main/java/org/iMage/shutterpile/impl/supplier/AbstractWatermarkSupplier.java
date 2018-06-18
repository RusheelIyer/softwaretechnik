package org.iMage.shutterpile.impl.supplier;

import java.awt.image.BufferedImage;

import org.iMage.shutterpile.port.IWatermarkSupplier;

/**
 * abstract watermark supplier
 * @author rusheeliyer
 *
 */
public abstract class AbstractWatermarkSupplier implements IWatermarkSupplier {

	@Override
	public abstract BufferedImage getWatermark();

}
