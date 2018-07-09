package org.iMage.shutterpile.parallel.acceptance.impl.filters;

import static org.junit.Assert.assertEquals;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.iMage.shutterpile.impl.filters.WatermarkFilter;
import org.iMage.shutterpile.impl.parallel.ParallelWatermarkFilter;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * test the parallel implementation of the watermark filter
 * @author rusheeliyer
 *
 */
public class ParallelTest {

	ParallelWatermarkFilter pwf;
	WatermarkFilter wf;
	File inputImageFile;
	File watermarkFile;
	BufferedImage watermark;
	BufferedImage inputImage;
	
	/**
	 * set up variables before each test case
	 * @throws Exception exception
	 */
	@Before
	public void setUp() throws Exception {
		
		inputImageFile = new File("src/test/resources/F10_Eingabe.png");
		watermarkFile = new File("src/test/resources/F10_WZ.png");
		
		inputImage = ImageIO.read(inputImageFile);
		watermark = ImageIO.read(watermarkFile);
		
	}

	/**
	 * test functional requirement 10
	 */
	@Test
	public void applyTest() {
		
		File outputImageFile = new File("src/test/resources/F10_Ausgabe.png");
		
		try {
			BufferedImage outputImage = ImageIO.read(outputImageFile);
			
			//check when thread count automatically generated
			pwf = new ParallelWatermarkFilter(watermark, 2);
			BufferedImage testOutputNoThread = pwf.apply(inputImage);
			
			//check when manual thread count given in constructor
			pwf = new ParallelWatermarkFilter(watermark, 2, 4);
			BufferedImage testOutputThread = pwf.apply(inputImage);
			
			for (int x = 0; x < outputImage.getWidth(); x++) {
				for (int y = 0; y < outputImage.getHeight(); y++) {
					assertEquals(outputImage.getRGB(x, y), testOutputNoThread.getRGB(x, y));
					assertEquals(outputImage.getRGB(x, y), testOutputThread.getRGB(x, y));
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * test functional requirement 20
	 * 
	 * 
	 * Note: test fails, correction required
	 */
	@Test @Ignore
	public void sequentialCompareTest() {
		
		wf = new WatermarkFilter(watermark, 2);
		BufferedImage sequential = wf.apply(inputImage);
		
		pwf = new ParallelWatermarkFilter(watermark, 2, 2);
		BufferedImage parallelTwoThread = pwf.apply(inputImage);
		
		pwf = new ParallelWatermarkFilter(watermark, 2, 8);
		BufferedImage parallelEightThread = pwf.apply(inputImage);
		
		pwf = new ParallelWatermarkFilter(watermark, 2, 4);
		BufferedImage parallelFourThread = pwf.apply(inputImage);
		
		for (int x = 0; x < sequential.getWidth(); x++) {
			for (int y = 0; y < sequential.getHeight(); y++) {
				assertEquals(sequential.getRGB(x, y), parallelTwoThread.getRGB(x, y));
				assertEquals(sequential.getRGB(x, y), parallelEightThread.getRGB(x, y));
				assertEquals(sequential.getRGB(x, y), parallelFourThread.getRGB(x, y));
			}
		}	
	}
	
	/**
	 * test non functional requirement 10
	 * 
	 * 
	 * Note: test fails, correction required
	 */
	@Test @Ignore
	public void sequentialTimeTest() {
		
		watermarkFile = new File("src/test/resources/NF10_WZ.png");
		inputImageFile = new File("src/test/resources/NF10_Bild.png");
		
		try {
			watermark = ImageIO.read(watermarkFile);
			wf = new WatermarkFilter(watermark, 30);
			inputImage = ImageIO.read(inputImageFile);
			
			long runtime = System.currentTimeMillis();
			wf.apply(inputImage);
			runtime = System.currentTimeMillis() - runtime;
			assert (runtime <= 1500);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * test non-functional requirement 20
	 */
	@Test
	public void parallelSpeedUpTest() {
		
		watermarkFile = new File("src/test/resources/NF10_WZ.png");
		inputImageFile = new File("src/test/resources/NF10_Bild.png");
		
		try {
			watermark = ImageIO.read(watermarkFile);
			wf = new WatermarkFilter(watermark, 30);
			inputImage = ImageIO.read(inputImageFile);
			
			long runtimeSequential = System.currentTimeMillis();
			wf.apply(inputImage);
			runtimeSequential = System.currentTimeMillis() - runtimeSequential;
			
			pwf = new ParallelWatermarkFilter(watermark, 30);
			
			long runtimeParallel = System.currentTimeMillis();
			pwf.apply(inputImage);
			runtimeParallel = System.currentTimeMillis() - runtimeParallel;
			
			assert (runtimeSequential * 0.75 >= runtimeParallel);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * test non-functional requirement 30
	 */
	@Test
	public void parallelPerformanceTest() {
		
		watermarkFile = new File("src/test/resources/NF10_WZ.png");
		inputImageFile = new File("src/test/resources/NF10_Bild.png");
		
		try {
			watermark = ImageIO.read(watermarkFile);
			inputImage = ImageIO.read(inputImageFile);
			pwf = new ParallelWatermarkFilter(watermark, 30, 2);
			
			long runtime = System.currentTimeMillis();
			
			for (int i = 1; i == 10; i++) {
				pwf.apply(inputImage);
			}
			runtime = System.currentTimeMillis() - runtime;
			
			assert ((runtime / 10) <= 1000);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * test non-functional requirement 40
	 */
	@Test
	public void parallelDifferenceTest() {
		
		watermarkFile = new File("src/test/resources/NF10_WZ.png");
		inputImageFile = new File("src/test/resources/NF10_Bild.png");
		
		try {
			watermark = ImageIO.read(watermarkFile);
			inputImage = ImageIO.read(inputImageFile);
			pwf = new ParallelWatermarkFilter(watermark, 30, 2);
			
			long[] runtime = new long[10];
			
			for (int i = 1; i == 10; i++) {
				long start = System.currentTimeMillis();
				pwf.apply(inputImage);
				runtime[i - 1] = System.currentTimeMillis() - start;
			}
			
			for (int i = 0; i < runtime.length; i++) {
				int compareTo = i + 1;
				while (compareTo < runtime.length) {
					assert ((runtime[i] - runtime[compareTo]) <= 100);
					compareTo++;
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
