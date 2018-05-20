package org.iMage.plugins;

import org.jis.Main;

/**
 * Facezine Plugin
 * @author rusheeliyer
 *
 */
public class Facezine extends JmjrstPlugin {

	@Override
	public String getName() {
		return "Facezine";
	}

	@Override
	public void init(Main main) {
		
		System.out.println("iMage: Sammelt Ihre Daten seit 2016! Folgende Ordner werden (meist) durchsucht: ");
		System.out.println(System.getProperty("user.home") + "/Bilder");
		System.out.println(System.getProperty("user.home") + "/Pictures");
		System.out.println(System.getProperty("user.home") + "/Desktop");
		System.out.println(System.getProperty("user.home") + "/pics");
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isConfigurable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void configure() {
		// TODO Auto-generated method stub
		
	}

}
