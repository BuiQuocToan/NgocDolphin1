package com.vti.utils.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DatabaseProperties {
	public static final String RESOURCE = "Resources/";
	
	private Properties properties;
	
	public DatabaseProperties() throws FileNotFoundException, IOException {
		properties = new Properties();
		properties.load(new FileInputStream(RESOURCE + "database.properties"));
	}
	
	public String getProperties(String key) {
		return properties.getProperty(key);
	}
}
