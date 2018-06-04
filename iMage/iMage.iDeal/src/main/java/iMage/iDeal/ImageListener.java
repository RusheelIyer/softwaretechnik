package iMage.iDeal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageListener implements ActionListener {

	mainWindow window;
	
	public ImageListener(mainWindow window) {
		this.window = window;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == window.original || e.getSource() == window.watermark) {
			
			setNewImage((JButton) e.getSource());
			
		}
		
	}

	public void setNewImage(JButton button) {
		
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(button);
		fc.addChoosableFileFilter(new FileNameExtensionFilter("Image Files", ImageIO.getReaderFileSuffixes()));
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				
				button.setIcon(new ImageIcon(ImageIO.read(fc.getSelectedFile())));
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
}
