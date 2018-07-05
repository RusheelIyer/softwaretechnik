package org.iMage.shutterpile.parallel.impl.filters;

import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.iMage.shutterpile.impl.filters.WatermarkFilter;
import org.iMage.shutterpile.port.IFilter;

public class ParallelWatermarkFilter implements IFilter, Runnable{
	
	private BufferedImage watermark;
	private int watermarksPerRow;
	private int numThreads;
	
	public ParallelWatermarkFilter(BufferedImage watermark, int watermarksPerRow, int numThreads) {
		this.watermark = watermark;
		this.watermarksPerRow = watermarksPerRow;
		this.numThreads = numThreads;
	}
	
	public ParallelWatermarkFilter(BufferedImage watermark, int watermarksPerRow) {
		this.watermark = watermark;
		this.watermarksPerRow = watermarksPerRow;
		this.numThreads = Runtime.getRuntime().availableProcessors();
	}

	@Override
	public BufferedImage apply(BufferedImage arg0) {
		
		if (this.numThreads == 1) {
			WatermarkFilter filter = new WatermarkFilter(watermark, watermarksPerRow);
			filter.apply(arg0);
		}
		
		ParallelWatermarkFilter[] workers = new ParallelWatermarkFilter[this.numThreads];
		
		return null;
	}

	@Override
	public void run() {
		
		
	}

}
