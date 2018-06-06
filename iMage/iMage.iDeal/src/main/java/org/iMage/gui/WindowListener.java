package org.iMage.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.iMage.shutterpile.impl.filters.WatermarkFilter;
import org.iMage.shutterpile.impl.supplier.ImageWatermarkSupplier;

/**
 * Image Action Listener
 * @author rusheeliyer
 *
 */
public class WindowListener implements ActionListener {

	MainWindow window;
	
	/**
	 * Constructor
	 * @param window main window
	 */
	public WindowListener(MainWindow window) {
		this.window = window;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == window.getOriginal() || e.getSource() == window.getWatermark()) {
			setNewImage((JButton) e.getSource());
		} else if (e.getSource() == window.getInitButton()) {
			changeWatermark();
		} else if (e.getSource() == window.getRunButton()) {
			runWatermarking();
		} else if (e.getSource() == window.getOutput()) {
			openWatermarked();
		} else if (e.getSource() == window.getSaveButton()) {
			save();
		} else if (e.getSource() == window.getGrayscale()) {
			waitForInit();
		}
		
	}

	/**
	 * Choose new image as input or watermark 
	 * @param button the button whose image is to be replaced
	 */
	public void setNewImage(JButton button) {
		
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Files", "png");
		fc.addChoosableFileFilter(filter);
		int returnVal = fc.showOpenDialog(button);
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				
				if (fc.getSelectedFile().getAbsolutePath().endsWith("png")) {
					button.setIcon(new ImageIcon(ImageIO.read(fc.getSelectedFile()).getScaledInstance(
							button.getWidth(), button.getHeight(), Image.SCALE_SMOOTH)));
					
					if (button == window.getWatermark()) {
						window.setWatermarkPic(ImageIO.read(fc.getSelectedFile()));
					} else {
						window.setInput(ImageIO.read(fc.getSelectedFile()));
						window.setInputFileName(fc.getSelectedFile().getName());
					}
				} else {
					JOptionPane.showMessageDialog(window, "Please choose a valid PNG file", 
							"File Error", JOptionPane.ERROR_MESSAGE);
				}
								
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	/**
	 * get the new watermark from the selected image
	 */
	public void changeWatermark() {
		
		try {
			window.getWatermarksPerRow();
			window.getRunButton().setEnabled(true);
			window.getSaveButton().setEnabled(true);
			ImageWatermarkSupplier supplier;
			if (window.getGrayscale().isSelected()) {
				supplier = new ImageWatermarkSupplier(window.getWatermarkPic(), true, window.getThresholdValue());
			} else {
				supplier = new ImageWatermarkSupplier(window.getWatermarkPic(), false, window.getThresholdValue());
			}
			window.setWatermarkPic(supplier.getWatermark());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(window, "Invalid watermarks per row input",
					"Input Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	/**
	 * output the picture with watermarks
	 */
	public void runWatermarking() {
		
		int watermarksPerRow = window.getWatermarksPerRow();
		int watermarkWidth = window.getInput().getWidth() / watermarksPerRow;
		if (watermarkWidth < 1 || watermarksPerRow == 0) {
			JOptionPane.showMessageDialog(window, "Watermarks not visible anymore. "
					+ "Please change amount of watermarks per row", "Watermarking Error", JOptionPane.ERROR_MESSAGE);
		} else {
			WatermarkFilter filter = new WatermarkFilter(window.getWatermarkPic(), watermarksPerRow);
			window.setOutput(filter.apply(window.getInput()));
		}
	}
	
	/**
	 * open the dialog with the watermarked picture in full size
	 */
	public void openWatermarked() {
		JDialog display = new JDialog();
		display.setResizable(false);
		display.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		BufferedImage outputPic = window.getOutputPic();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		if (outputPic.getWidth() >= screenSize.getWidth() || outputPic.getHeight() >= screenSize.getHeight()) {
			display.setSize(700, 500);
		} else {
			display.setSize(outputPic.getWidth(), outputPic.getWidth());
		}
		
		String grayscale = window.getGrayscale().isSelected() ? ", grayscale)" : ")";
		display.setTitle(window.getInputFileName() + "(threshold " + window.getThresholdValue() + ", WM pr "
		+ window.getWatermarksPerRow() + grayscale);
		JScrollPane scrollPic = new JScrollPane();
		JLabel picContainer = new JLabel(new ImageIcon((Image) outputPic));
		scrollPic.setViewportView(picContainer);
		scrollPic.setVisible(true);
		display.add(scrollPic, BorderLayout.CENTER);
		display.setVisible(true);
	}
	
	/**
	 * save the watermarked image
	 */
	public void save() {
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = fc.showSaveDialog(window);
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			if (fc.getSelectedFile().getAbsolutePath().endsWith("png")) {
				try {
					ImageIO.write(window.getOutputPic(), "png", fc.getSelectedFile());
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(window, "Please save as PNG file", 
						"File Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	
	/**
	 * disable run and save button if threshold and/or grayscale is changed
	 */
	public void waitForInit() {
		window.getRunButton().setEnabled(false);
		window.getSaveButton().setEnabled(false);
	}
	
}
