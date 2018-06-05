package org.iMage.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * main window for the GUI
 * 
 * @author rusheeliyer
 *
 */
public class MainWindow extends JFrame {
	
	JFrame frame;
	
	private int watermarksPerRow = 1;
	private Font textFont = new Font("Custom1", Font.PLAIN, 18);
	private BufferedImage input;
	private BufferedImage watermarkPic;
	private BufferedImage outputPic;
	
	private JButton original;
	private JButton watermark; 
	private JButton output;
	private JButton initButton;
	private JFormattedTextField watermarkRow;
	private JSlider threshold;
	private JCheckBox grayscale;
	private JButton runButton;
	private JButton saveButton;
	
	/**
	 * get watermarks per row
	 * 
	 * @return watermarksPerRow
	 */
	public int getWatermarksPerRow() {
		return Integer.parseInt(watermarkRow.getText());
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
	public JButton getOutput() {
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
	 * get init button
	 * @return initButton
	 */
	public JButton getInitButton() {
		return this.initButton;
	}
	
	/**
	 * get the grayscale checkbox
	 * @return grayscale checkbox
	 */
	public JCheckBox getGrayscale() {
		return this.grayscale;
	}
	
	/**
	 * get the run button
	 * @return runButton
	 */
	public JButton getRunButton() {
		return this.runButton;
	}
	
	/**
	 * get the save button
	 * @return saveButton
	 */
	public JButton getSaveButton() {
		return this.saveButton;
	}
	
	/**
	 * operation on the main window frame
	 */
	MainWindow() {
		
		setTitle("iDeal");
		setSize(700, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
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
			output = new JButton(new ImageIcon(outputPic));
			output.setSize(200, 150);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		showPicturePanel();
		showConfig();
		finalButtons();
		
	}
	
	/**
	 * display the picture previews on the main window
	 */
	public void showPicturePanel() {
		
		JPanel picturePanel = new JPanel();
		picturePanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		WindowListener listen = new WindowListener(this);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		JLabel originalLabel = new JLabel("Original");
		originalLabel.setFont(textFont);
		picturePanel.add(originalLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		JLabel watermarkLabel = new JLabel("Watermark");
		watermarkLabel.setFont(textFont);
		picturePanel.add(watermarkLabel, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		JLabel watermarkedLabel = new JLabel("Watermarked");
		watermarkedLabel.setFont(textFont);
		picturePanel.add(watermarkedLabel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridheight = 2;
		picturePanel.add(original, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridheight = 1;
		picturePanel.add(watermark, gbc);
		
		initButton = new JButton("Init");
		initButton.addActionListener(listen);
		gbc.gridx = 1;
		gbc.gridy = 2;
		picturePanel.add(initButton, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridheight = 2;
		picturePanel.add(output, gbc);
		
		original.addActionListener(listen);
		watermark.addActionListener(listen);
		add(picturePanel);
		
	}
	
	/**
	 * display options to adjust the output images
	 */
	public void showConfig() {
		
		JPanel adjustPanel = new JPanel();
		adjustPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1;
		gbc.weighty = 1;
		
		JLabel textLabel = new JLabel("WM per Row");
		textLabel.setFont(textFont);
		gbc.gridx = 0;
		gbc.gridy = 0;
		adjustPanel.add(textLabel, gbc);
		
		NumberFormat format = NumberFormat.getIntegerInstance();
		format.setGroupingUsed(false);
		watermarkRow = new JFormattedTextField(format);
		watermarkRow.setText("0");
		watermarkRow.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				watermarkRow.setForeground(Color.BLACK);
			}

			@Override
			public void focusLost(FocusEvent e) {
				try {
					Integer.parseInt(watermarkRow.getText());
				} catch (NumberFormatException exc) {
					watermarkRow.setFocusLostBehavior(JFormattedTextField.PERSIST);
					watermarkRow.setForeground(Color.RED);
				}
			}
			
		});
		watermarkRow.setEditable(true);
		watermarkRow.setColumns(5);
		gbc.gridx = 1;
		gbc.gridy = 0;
		adjustPanel.add(watermarkRow, gbc);
		
		JLabel thresholdLabel = new JLabel("Threshold (0)");
		thresholdLabel.setFont(textFont);
		gbc.gridx = 0;
		gbc.gridy = 1;
		adjustPanel.add(thresholdLabel, gbc);
		
		threshold = new JSlider(0, 255);
		threshold.setMajorTickSpacing(255);
		threshold.setPaintTicks(true);
		threshold.setPaintLabels(true);
		threshold.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				
				if (!threshold.getValueIsAdjusting()) {
					thresholdLabel.setText("Threshold (" + threshold.getValue() + ")");
				}
				
			}
			
		});
		gbc.gridx = 1;
		gbc.gridy = 1;
		adjustPanel.add(threshold, gbc);
		
		JLabel grayscaleLabel = new JLabel("Grayscale");
		grayscaleLabel.setFont(textFont);
		gbc.gridx = 0;
		gbc.gridy = 2;
		adjustPanel.add(grayscaleLabel, gbc);
		
		grayscale = new JCheckBox();
		gbc.gridx = 1;
		gbc.gridy = 2;
		adjustPanel.add(grayscale, gbc);
		
		getContentPane().add(adjustPanel);
		
	}
	
	/**
	 * show the final panel with the run and save buttons
	 */
	public void finalButtons() {
		
		JPanel buttonPanel = new JPanel();
		WindowListener listen = new WindowListener(this);
		
		runButton = new JButton("Run");
		runButton.addActionListener(listen);
		buttonPanel.add(runButton);
		
		saveButton = new JButton("Save");
		buttonPanel.add(saveButton);
		
		add(buttonPanel);
		
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
