package org.iMage.plugins;

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
		
	}

	@Override
	public void run() {
		System.ou
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

}
