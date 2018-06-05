package org.iMage.gui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
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
	
	private int watermarksPerRow = 30;
	private BufferedImage input;
	private BufferedImage watermarkPic;
	private BufferedImage outputPic;
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
	 * get watermarks per row
	 * 
	 * @return watermarksPerRow
	 */
	public int getWatermarksPerRow() {
		return this.watermarksPerRow;
	}
	
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
	 * get output label
	 * @return output
	 */
	public JLabel getOutput() {
		return output;
	}
	
	/**
	 * get the input picture
	 * @return the input picture
	 */
	public BufferedImage getInput() {
		return this.input;
	}
	
	/**
	 * get the watermark picture
	 * @return the watermark picture
	 */
	public BufferedImage getWatermarkPic() {
		return this.watermarkPic;
	}
	
	/**
	 * get the output picture
	 * @return the output picture
	 */
	public BufferedImage getOutputPic() {
		return this.outputPic;
	}
	
	/**
	 * set new input picture once new one has been chosen by the user
	 * @param input the new image
	 */
	public void setInput(BufferedImage input) {
		this.input = input;
		this.original.setIcon(new ImageIcon(input.getScaledInstance(200, 150, Image.SCALE_SMOOTH)));
	}
	
	/**
	 * set new watermark picture once new one has been chosen by the user
	 * @param watermarkPic the new image
	 */
	public void setWatermarkPic(BufferedImage watermarkPic) {
		this.watermarkPic = watermarkPic;
		this.watermark.setIcon(new ImageIcon(watermarkPic.getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
	}
	
	/**
	 * set new output picture once new watermark/input has been chosen by the user
	 * @param outputPic the new image
	 */
	public void setOutput(BufferedImage outputPic) {
		this.outputPic = outputPic;
		this.output.setIcon(new ImageIcon(outputPic.getScaledInstance(200, 150, Image.SCALE_SMOOTH)));
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
			
			input = ImageIO.read(new File("src/main/resources/Input.png"));
			original = new JButton(new ImageIcon(input));
			original.setSize(200, 150);
			
			watermarkPic = ImageIO.read(new File("src/main/resources/Watermark.png"));
			watermark = new JButton(new ImageIcon(watermarkPic));
			watermark.setSize(100, 100);
			
			outputPic = ImageIO.read(new File("src/main/resources/Output.png"));
			output = new JLabel(new ImageIcon(outputPic));
			output.setSize(200, 150);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		showPicturePanel();
		
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
		
		ImageListener listen = new ImageListener(this);
		original.addActionListener(listen);
		watermark.addActionListener(listen);
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
