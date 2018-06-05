package org.iMage.gui;

import java.awt.Color;
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
import javax.swing.JTextField;

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
	
	private JTextField watermarkRow;
	private JButton original;
	private JButton watermark; 
	private JLabel output;
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
		
		setTitle("iDeal");
		setSize(700, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
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
		//showImageAdjusters();
		
	}
	
	/**
	 * display the picture previews on the main window
	 */
	public void showPicturePanel() {
		
		GridBagConstraints gbc = new GridBagConstraints();
		init = new JButton("Init");
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		JLabel originalLabel = new JLabel("Original");
		add(originalLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		JLabel watermarkLabel = new JLabel("Watermark");
		add(watermarkLabel, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		JLabel watermarkedLabel = new JLabel("Watermarked");
		add(watermarkedLabel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridheight = 2;
		add(original, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridheight = 1;
		add(watermark, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		add(init, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridheight = 2;
		add(output, gbc);
		
		WindowListener listen = new WindowListener(this);
		original.addActionListener(listen);
		watermark.addActionListener(listen);
		
	}
	
	/**
	 * display options to adjust the output images
	 */
	public void showImageAdjusters() {
		
		JPanel adjustPanel = new JPanel();
		watermarkRow = new JTextField("1");
		watermarkRow.setSize(100, JTextField.HEIGHT);
		watermarkRow.setEditable(true);
		
		try {
			watermarksPerRow = Integer.parseInt(watermarkRow.getText());
		} catch (NumberFormatException e) {
			watermarkRow.setForeground(Color.RED);
		}
		
		adjustPanel.add(watermarkRow);
		adjustPanel.setVisible(true);
		getContentPane().add(adjustPanel);
		
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
