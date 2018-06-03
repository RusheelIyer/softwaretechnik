package iMage.iDeal;

import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.iMage.shutterpile.*;

public class mainWindow extends JFrame {
	
	JFrame frame;
	
	mainWindow(){
		setSize(700, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setResizable(false);
		setVisible(true);
		
		showPictures();
		
	}
	
	public void showPictures() {
		
		JPanel picturePanel = new JPanel();
		picturePanel.setLayout(new GridLayout(0, 3, 5, 0));
		
		try {
			
			BufferedImage original = ImageIO.read(new File("src/main/resources/Input.png"));
			BufferedImage watermark = ImageIO.read(new File("src/main/resources/Watermark.png"));
			BufferedImage output = ImageIO.read(new File("src/main/resources/Output.png"));
			
			JLabel originalPic = new JLabel(new ImageIcon(original));
			JLabel watermarkPic = new JLabel(new ImageIcon(watermark));
			JLabel outputPic = new JLabel(new ImageIcon(output));
			picturePanel.add(originalPic);
			picturePanel.add(watermarkPic);
			picturePanel.add(outputPic);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new mainWindow();
	}

}
