package com.ballew.tools.cli.api;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import com.ballew.tools.cli.api.console.Console;

/**
 * The CLIContext is a key/value datastore that gets passed between commands.
 * It should be used to persist state between command execution. Applications can
 * specify their own CLIContext, allowing for known keys to be used through getters/setters,
 * or to store/do anything else that might be needed.
 * @author Sean
 *
 */
public class CLIContext {
	
	/**
	 * The properties held by this context.
	 */
	private Map<String, Object> _properties;
	
	/**
	 * The application in which this context is contained.
	 */
	private CommandLineApplication<? extends CLIContext> _app;
	
	public CLIContext(CommandLineApplication<? extends CLIContext> app) {
		_properties = new HashMap<String, Object>();
		_app = app;
		loadProperties(getEmbeddedPropertiesFile());
		loadProperties(getExternalPropertiesFile());
	}
	
	/**
	 * Get the application in which this context is contained.
	 * @return The application in which this context is contained.
	 */
	public CommandLineApplication<? extends CLIContext> getHostApplication() {
		return _app;
	}
	
	/**
	 * Load properties from a file.
	 * @param propFile
	 */
	private void loadProperties(File propFile) {
		if (propFile == null) {
			return;
		}
		FileInputStream stream = null;
		try {
			stream = new FileInputStream(propFile);
			Properties props = new Properties();
			props.load(stream);
			
			Iterator<Object> keyIt = props.keySet().iterator();
			while (keyIt.hasNext()) {
				String key = keyIt.next().toString();
				_properties.put(key, props.get(key));
			}
		}
		catch (Exception e) {
			Console.warn("Unable to load properties file ["+propFile.getAbsolutePath()+"].");
		}
		finally {
			if (stream != null) {
				try {
					stream.close();
				}
				catch (Exception e) {
					Console.warn("Unable to close properties file ["+propFile.getAbsolutePath()+"].");
				}
			}
		}
	}
	
	/**
	 * Get the embedded property file. If none should be used, specify null.
	 * @return
	 */
	protected File getEmbeddedPropertiesFile() {
		return null;
	}
	
	/**
	 * Get the external property file. If none should be used, specify null.
	 * @return
	 */
	protected File getExternalPropertiesFile() {
		return null;
	}
	
	/**
	 * Add an object to the context.
	 * @param key The key to add.
	 * @param o The object to add.
	 * @return The previous object associated with this key, or null if there was none.
	 */
	public Object put(String key, Object o) {
		return _properties.put(key, o);
	}
	
	public Object getObject(String key) {
		return _properties.get(key);
	}
	
	/**
	 * Get the string value, or null if not found.
	 * @param key The key to search for.
	 * @return The value, or null if not found.
	 */
	public String getString(String key) {
		return getString(key, null);
	}
	
	/**
	 * Get the string value, or the defaultValue if not found.
	 * @param key The key to search for.
	 * @return The value, or defaultValue if not found.
	 */
	public String getString(String key, String defaultValue) {
		Object o = getObject(key);
		if (o == null) {
			return defaultValue;
		}
		if (!(o instanceof String)) {
			throw new IllegalArgumentException("Object ["+o+"] associated with key ["+key+"] is not of type String.");
		}
		return (String)o;
	}
	
	/**
	 * Get the boolean value, or false if not found.
	 * @param key The key to search for.
	 * @return The value, or false if not found.
	 */
	public boolean getBoolean(String key) {
		return getBoolean(key, false);
	}
	
	/**
	 * Get the boolean value, or the defaultValue if not found.
	 * @param key The key to search for.
	 * @return The value, or defaultValue if not found.
	 */
	public boolean getBoolean(String key, boolean defaultValue) {
		Object o = getObject(key);
		if (o == null) {
			return defaultValue;
		}
		boolean b = defaultValue;
		try {
			b = Boolean.parseBoolean(o.toString());
		}
		catch (Exception e) {
			throw new IllegalArgumentException("Object ["+o+"] associated with key ["+key+"] is not of type Boolean.");
		}
		return b;
	}
	
	/**
	 * Get the integer value, or -1 if not found.
	 * @param key The key to search for.
	 * @return The value, or -1 if not found.
	 */
	public int getInteger(String key) {
		return getInteger(key, -1);
	}
	
	/**
	 * Get the integer value, or the defaultValue if not found.
	 * @param key The key to search for.
	 * @return The value, or defaultValue if not found.
	 */
	public int getInteger(String key, int defaultValue) {
		Object o = getObject(key);
		if (o == null) {
			return defaultValue;
		}
		int val = defaultValue;
		try {
			val = Integer.parseInt(o.toString());
		}
		catch (Exception e) {
			throw new IllegalArgumentException("Object ["+o+"] associated with key ["+key+"] is not of type Integer.");
		}
		return val;
	}
	
	/**
	 * Determine whether the given key exists in the context or not.
	 * @param key The key to search for.
	 * @return True if the key is contained within this context, false otherwise.
	 */
	public boolean containsKey(String key) {
		return _properties.containsKey(key);
	}
	
}
