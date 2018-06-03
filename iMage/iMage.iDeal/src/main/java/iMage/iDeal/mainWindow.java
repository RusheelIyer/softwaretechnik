package iMage.iDeal;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class mainWindow extends JFrame {
	
	JFrame frame;
	
	mainWindow(){
		setSize(700, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setResizable(false);
		setVisible(true);
		
		
		
	}
	
	public void showPictures() {
		
		JPanel picturePanel = new JPanel();
		picturePanel.setLayout(new GridLayout(0, 3, 5, 0));
		
	}
	
	public static void main(String[] args) {
		new mainWindow();
	}

}
