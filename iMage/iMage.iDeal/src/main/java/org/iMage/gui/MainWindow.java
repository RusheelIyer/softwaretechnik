package org.iMage.gui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	 * label with watermarked image preview
	 */
	private JLabel output;
	
	/**
	 * init buttons
	 */
	private JButton init;
	
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
			output = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/resources/Output.png"))));
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
		picturePanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		init = new JButton("Init");
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 2;
		picturePanel.add(original, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		picturePanel.add(watermark, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		picturePanel.add(init, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridheight = 2;
		picturePanel.add(output, gbc);
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
