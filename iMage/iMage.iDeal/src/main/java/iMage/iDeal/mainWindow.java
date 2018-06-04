package iMage.iDeal;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
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

public class mainWindow extends JFrame {
	
	JFrame frame;
	public JButton original;
	public JButton watermark;
	public JButton output;
	
	mainWindow(){
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
		
	}
	
	public void showPicturePanel() {
		
		JPanel picturePanel = new JPanel();
		picturePanel.add(original);
		picturePanel.add(watermark);
		picturePanel.add(output);
		picturePanel.setVisible(true);
		getContentPane().add(picturePanel);
		
	}
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new mainWindow();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

}
