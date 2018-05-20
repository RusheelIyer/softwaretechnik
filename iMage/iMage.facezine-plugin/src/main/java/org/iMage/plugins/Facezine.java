package org.iMage.plugins;

import java.io.File;

import javax.swing.JOptionPane;

import org.jis.Main;

/**
 * Facezine Plugin
 * @author rusheeliyer
 *
 */
public class Facezine extends JmjrstPlugin {

	private Main mainClass;
	
	@Override
	public String getName() {
		return "Facezine";
	}

	@Override
	public void init(Main main) {
		mainClass = main;
		System.err.println("iMage: Sammelt Ihre Daten seit 2016! Folgende Ordner werden (meist) durchsucht: "
		+ System.getProperty("user.home"));
		search(System.getProperty("user.home") + "/Bilder");
		search(System.getProperty("user.home") + "/Picture");
		search(System.getProperty("user.home") + "/Desktop");
		search(System.getProperty("user.home") + "/pics");
		
	}

	@Override
	public void run() {
		System.out.println("Folgende Bilddateien wurden gefunden:");
	}

	@Override
	public boolean isConfigurable() {
		return true;
	}

	@Override
	public void configure() {
		JOptionPane.showMessageDialog(mainClass, System.getProperty("user.home"), 
				"Gefundene Bildordner", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void search(String dir) {
		
		File directory = new File(dir);
		
		if (directory.exists()) {
			for (File current : directory.listFiles()) {
				if (current.isDirectory()) {
					search(current.getAbsolutePath());
				} else {
					String fileName = current.getName();
					int index = fileName.lastIndexOf('.');
					if (index >= 0) {
						if (fileName.substring(index + 1).equalsIgnoreCase("png")
								|| fileName.substring(index + 1).equalsIgnoreCase("jpg")) {
							System.out.println(current.getAbsolutePath());
						}
					}
				}
			}
		}
		
	}

}
