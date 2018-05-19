package org.iMage.plugins;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ServiceLoader;

/**
 * Knows all available plug-ins and is responsible for using the service loader API to detect them.
 *
 * @author Dominic Wolff
 *
 */
public final class PluginManager {

  /**
   * No constructor for utility class.
   */
  private PluginManager() {
    throw new IllegalAccessError();
  }

  /**
   * Return an {@link Iterable} Object with all available {@link JmjrstPlugin}s alphabetically sorted 
   * according to their class names.
   *
   * @return an {@link Iterable} Object containing all available plug-ins alphabetically sorted 
   *          according to their class names.
   */
  public static Iterable<JmjrstPlugin> getPlugins() {
	  
	  ArrayList<JmjrstPlugin> plugins = new ArrayList<JmjrstPlugin>();
	  ServiceLoader<JmjrstPlugin> serviceLoader = ServiceLoader.load(JmjrstPlugin.class);
	  
	  for (JmjrstPlugin plugin : serviceLoader) {
		  plugins.add(plugin);
	  }
	  
	  Collections.sort(plugins);
	  return plugins;
	  
  }

}
