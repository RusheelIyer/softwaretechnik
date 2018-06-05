package org.iMage.gui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

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
			
			changeWatermark(window.getWatermarkPic());
			
		}
		
	}

	/**
	 * Choose new image as input or watermark 
	 * @param button the button whose image is to be replaced
	 */
	public void setNewImage(JButton button) {
		
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.addChoosableFileFilter(new FileNameExtensionFilter("Image Files", ImageIO.getReaderFileSuffixes()));
		int returnVal = fc.showOpenDialog(button);
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				
				button.setIcon(new ImageIcon(ImageIO.read(fc.getSelectedFile()).getScaledInstance(
						button.getWidth(), button.getHeight(), Image.SCALE_SMOOTH)));
				
				if (button == window.getWatermark()) {
					window.setWatermarkPic(ImageIO.read(fc.getSelectedFile()));
				} else {
					window.setInput(ImageIO.read(fc.getSelectedFile()));
				}
								
			} catch (IOException e1) {
				e1.printStackTrace();
			}  catch (NullPointerException nullPointer) {
				JOptionPane.showMessageDialog(window, "Please choose a valid image file", 
						"Image File Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	
	/**
	 * get the new watermark from the selected image
	 * @param watermarkPic the selected image
	 */
	public void changeWatermark(BufferedImage watermarkPic) {
		
		ImageWatermarkSupplier supplier = new ImageWatermarkSupplier(watermarkPic);
		window.setWatermarkPic(supplier.getWatermark());
		
	}
	
}