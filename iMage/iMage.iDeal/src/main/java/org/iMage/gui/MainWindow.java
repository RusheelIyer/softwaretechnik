package org.iMage.gui;

import java.awt.EventQueue;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * main window for the GUI
 * 
 * @author rusheeliyer
 *
 */
public class MainWindow extends JFrame {
	
	JFrame frame;
	/**
	 * button with input preview
	 */
	private JButton original;
	/**
	 * button with watermark preview
	 */
	private JButton watermark; 
	/**
	 * button with watermarked image preview
	 */
	private JButton output;
	
	/**
	 * get original button
	 * @return original
	 */
	public JButton getOriginal() {
		return original;
	}
	
	/**
	 * get watermark button
	 * @return watermark
	 */
	public JButton getWatermark() {
		return watermark;
	}

	/**
	 * get output button
	 * @return output
	 */
	public JButton getOutput() {
		return output;
	}
	
	/**
	 * operation on the main window frame
	 */
	MainWindow() {
		setSize(700, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setResizable(false);
		setVisible(true);
		
		try {
			
			original = new JButton(new ImageIcon(ImageIO.read(new File("src/main/resources/Input.png"))));
			original.setSize(200, 150);
			watermark = new JButton(new ImageIcon(ImageIO.read(new File("src/main/resources/Watermark.png"))));
			watermark.setSize(100, 100);
			output = new JButton(new ImageIcon(ImageIO.read(new File("src/main/resources/Output.png"))));
			output.setSize(200, 150);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		showPicturePanel();
		
		ImageListener listen = new ImageListener(this);
		original.addActionListener(listen);
		watermark.addActionListener(listen);
		
	}
	
	/**
	 * display the picture previews on the main window
	 */
	public void showPicturePanel() {
		
		JPanel picturePanel = new JPanel();
		picturePanel.add(original);
		picturePanel.add(watermark);
		picturePanel.add(output);
		picturePanel.setVisible(true);
		getContentPane().add(picturePanel);
		
	}
	
	/**
	 * main method 
	 * @param args arguments
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainWindow();
			}
		});
		
	}

}
