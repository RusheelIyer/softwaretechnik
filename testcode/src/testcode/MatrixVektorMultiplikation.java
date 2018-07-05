package testcode;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MatrixVektorMultiplikation implements Runnable{

	private int[][] meinA = null;
	private int[] meinB = null;
	private int[] meinY = null;
	private int meinStart = 0;
	private int meinEnde = 0;
	
	private static CyclicBarrier barriere = null;
	
	private void printVektor(int[] v) {
		
		for (int i = 0; i < v.length; i++) {
			System.out.println(v[i] + "\n");
		}
		
	}
	
	public MatrixVektorMultiplikation() {
		
	}
	
	private MatrixVektorMultiplikation(int[][] a, int[] b, int[] y, int start, int ende) {
		this.meinA = a;
		this.meinB = b;
		this.meinY = y;
		this.meinStart = start;
		this.meinEnde = ende;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = meinStart; i < meinEnde; i++) {
			for (int j = 0; j < meinB.length; j++) {
				meinY[i] += meinA[i][j] * meinB[j];
			}
		}
		
		try {
			barriere.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
	
	public void multiplikation(int[][] a, int[] b, int anzahlFäden) {
		MatrixVektorMultiplikation[] vmmArbeiter = new MatrixVektorMultiplikation[anzahlFäden];
		Thread[] vmmFäden = new Thread[anzahlFäden];
		
		barriere = new CyclicBarrier(anzahlFäden, new Runnable() {
			public void run() {
				System.out.println("Alle Fäden haben die Barriere erreicht!");
				printVektor(meinY);
			}
		});
		
		meinY = new int[a.length];
		int start = 0;
		int ende = 0;
		int anzahlZeilen = (int) Math.ceil((double) a.length / anzahlFäden);
		
		for (int f = 0; f < anzahlFäden; f++) {
			start = f * anzahlZeilen;
			ende = Math.min((f + 1) * anzahlZeilen, a.length);
			
			vmmArbeiter[f] = new MatrixVektorMultiplikation(a, b, meinY, start, ende);
			vmmFäden[f] = new Thread(vmmArbeiter[f]);
			vmmFäden[f].start();
			
		}
		
	}
	
	public static void main(String[] args) {
		
		MatrixVektorMultiplikation mvm = new MatrixVektorMultiplikation();
		int[][] a = new int[][] {
			{1,1,1,1},
			{0,2,2,2},
			{0,0,3,3},
			{0,0,0,4}
		};
		int[] b = new int[] {1,1,1,1};
		int anzahlFäden = 8;
		
		mvm.multiplikation(a, b, anzahlFäden);
		System.out.println("Warte auf Ergebnis...");
	}

}
