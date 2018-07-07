package org.iMage.shutterpile.parallel.impl.filters;

import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.iMage.shutterpile.impl.filters.WatermarkFilter;
import org.iMage.shutterpile.impl.util.ImageUtils;
import org.iMage.shutterpile.port.IFilter;

/**
 * Parallel WatermarkFilter implementation
 * @author rusheelIyer
 *
 */
public class ParallelWatermarkFilter implements IFilter, Runnable {
	
	private BufferedImage watermark;
	private int watermarksPerRow;
	private int numThreads;
	private int startX = 0;
	private int startY = 0;
	private int endX = 0;
	private int endY = 0;
	
	/**
	 * constructor with manual number of threads
	 * @param watermark watermark image
	 * @param watermarksPerRow desired number of watermarks per row
	 * @param numThreads desired number of threads
	 */
	public ParallelWatermarkFilter(BufferedImage watermark, int watermarksPerRow, int numThreads) {
		this.watermark = watermark;
		this.watermarksPerRow = watermarksPerRow;
		this.numThreads = numThreads;
	}
	
	/**
	 * constructor with automatically determined thread number 
	 * @param watermark watermark image
	 * @param watermarksPerRow desired number of watermarks per row
	 */
	public ParallelWatermarkFilter(BufferedImage watermark, int watermarksPerRow) {
		this.watermark = watermark;
		this.watermarksPerRow = watermarksPerRow;
		this.numThreads = Runtime.getRuntime().availableProcessors();
	}

	@Override
	public BufferedImage apply(BufferedImage arg0) {
		
		if (this.numThreads == 1) {
			WatermarkFilter filter = new WatermarkFilter(watermark, watermarksPerRow);
			return filter.apply(arg0);
		}
		
		ParallelWatermarkFilter[] workers = new ParallelWatermarkFilter[this.numThreads];
		Thread[] threads = new Thread[this.numThreads];
		int watermarkWidth = arg0.getWidth() / this.watermarksPerRow;
		BufferedImage scaledWatermark = ImageUtils.scaleWidth(watermark, watermarkWidth);
		
		for (int y = 0; y < arg0.getHeight(); y += scaledWatermark.getHeight()) {
			for (int x = 0; x < arg0.getWidth(); x += watermarkWidth) {
				for (int f = 0; f < this.numThreads; f++) {
					
					int endX = Math.min(x + watermarkWidth, arg0.getWidth());
					int endY = Math.min(y + scaledWatermark.getHeight(), arg0.getHeight());
					
					workers[f] = new ParallelWatermarkFilter(this.watermark, 1, 1);
					threads[f] = new Thread(workers[f]);
					threads[f].start();
				}
			}
		}
		
		return null;
	}

	@Override
	public void run() {
		
		BufferedImage[][] images = new BufferedImage[this.watermarksPerRow][]
		
	}

}
