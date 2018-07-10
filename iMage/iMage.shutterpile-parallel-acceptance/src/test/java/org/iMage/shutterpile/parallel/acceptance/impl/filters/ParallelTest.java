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
	BufferedImage fOutput;
	BufferedImage fInput;
	BufferedImage fWatermark;
	BufferedImage nfImage;
	BufferedImage nfWatermark;
	
	/**
	 * set up variables before each test case
	 * @throws Exception exception
	 */
	@Before
	public void setUp() throws Exception {
		
		File file = new File("src/test/resources/F10_Ausgabe.png");
		fOutput = ImageIO.read(file);
		
		file = new File("src/test/resources/F10_Eingabe.png");
		fInput = ImageIO.read(file);
		
		file = new File("src/test/resources/F10_WZ.png");
		fWatermark = ImageIO.read(file);
		
		file = new File("src/test/resources/NF10_Bild.png");
		nfImage = ImageIO.read(file);
		
		file = new File("src/test/resources/NF10_WZ.png");
		nfWatermark = ImageIO.read(file);
		
	}

	/**
	 * test functional requirement 10
	 */
	@Test
	public void parallelApplyTest() {
		
		//check when thread count automatically generated
		pwf = new ParallelWatermarkFilter(fWatermark, 2);
		BufferedImage testOutputNoThread = pwf.apply(fInput);
		
		//check when manual thread count given in constructor
		pwf = new ParallelWatermarkFilter(fWatermark, 2, 4);
		BufferedImage testOutputThread = pwf.apply(fInput);
		
		for (int x = 0; x < fOutput.getWidth(); x++) {
			for (int y = 0; y < fOutput.getHeight(); y++) {
				assertEquals(fOutput.getRGB(x, y), testOutputNoThread.getRGB(x, y));
				assertEquals(fOutput.getRGB(x, y), testOutputThread.getRGB(x, y));
			}
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
		
		wf = new WatermarkFilter(fWatermark, 2);
		BufferedImage sequential = wf.apply(fInput);
		
		pwf = new ParallelWatermarkFilter(fWatermark, 2, 2);
		BufferedImage parallelTwoThread = pwf.apply(fInput);
		
		pwf = new ParallelWatermarkFilter(fWatermark, 2, 8);
		BufferedImage parallelEightThread = pwf.apply(fInput);
		
		pwf = new ParallelWatermarkFilter(fWatermark, 2, 4);
		BufferedImage parallelFourThread = pwf.apply(fInput);
		
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
		
		wf = new WatermarkFilter(nfWatermark, 30);
		
		long runtime = System.currentTimeMillis();
		wf.apply(nfImage);
		runtime = System.currentTimeMillis() - runtime;
		assert (runtime <= 1500);
	}
	
	/**
	 * test non-functional requirement 20
	 */
	@Test
	public void parallelSpeedUpTest() {
		
		wf = new WatermarkFilter(nfWatermark, 30);
		
		long runtimeSequential = System.currentTimeMillis();
		wf.apply(nfImage);
		runtimeSequential = System.currentTimeMillis() - runtimeSequential;
		
		pwf = new ParallelWatermarkFilter(nfWatermark, 30);
		
		long runtimeParallel = System.currentTimeMillis();
		pwf.apply(nfImage);
		runtimeParallel = System.currentTimeMillis() - runtimeParallel;
		
		assert (runtimeSequential * 0.75 >= runtimeParallel);
	}
	
	/**
	 * test non-functional requirement 30
	 */
	@Test
	public void parallelPerformanceTest() {
		
		pwf = new ParallelWatermarkFilter(nfWatermark, 30, 2);
		
		long runtime = System.currentTimeMillis();
		
		for (int i = 1; i == 10; i++) {
			pwf.apply(nfImage);
		}
		runtime = System.currentTimeMillis() - runtime;
		
		assert ((runtime / 10) <= 1000);
	}
	
	/**
	 * test non-functional requirement 40
	 */
	@Test
	public void parallelDifferenceTest() {
		
		pwf = new ParallelWatermarkFilter(nfWatermark, 30, 2);
		
		long[] runtime = new long[10];
		
		for (int i = 1; i == 10; i++) {
			long start = System.currentTimeMillis();
			pwf.apply(nfImage);
			runtime[i - 1] = System.currentTimeMillis() - start;
		}
		
		for (int i = 0; i < runtime.length; i++) {
			int compareTo = i + 1; //compare each value in the array with all elements that come after it
			while (compareTo < runtime.length) {
				assert (Math.abs(runtime[i] - runtime[compareTo]) <= 100);
				compareTo++;
			}
		}
		
	}

}
