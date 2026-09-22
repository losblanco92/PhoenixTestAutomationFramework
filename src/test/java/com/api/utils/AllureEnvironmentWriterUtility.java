package com.api.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.logging.log4j.Logger;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;

public class AllureEnvironmentWriterUtility {
 private static final Logger LOGGER = LogManager.getLogger(AllureEnvironmentWriterUtility.class);
 
	public static void createEnvironmentPropertiesFile (){
	String folderPath ="target/allure-results";	
	File file = new File(folderPath);
	file.mkdirs();
		
	Properties properties = new Properties();
	
	FileWriter fw = null;
	
	properties.setProperty("Name", "Neer");
	properties.setProperty("Project Name", "Phoenix Test Automation Framework");
	properties.setProperty("Environment", ConfigManager.env);
	properties.setProperty("BASE_URI", ConfigManager.getProperty("BASE_URI"));
	properties.setProperty("Operating System", System.getProperty("os.name"));
	properties.setProperty("OS_Version", System.getProperty("os.version"));
	properties.setProperty("Java_Version", System.getProperty("java.version"));

	try {
		fw = new FileWriter(folderPath+"/environment.properties");
		properties.store(fw, "My Properties File");
		LOGGER.info("Create environment.properties file at {}", folderPath);
	} catch (IOException e) {
		LOGGER.error("Unable to create environment.properties file", e);
		e.printStackTrace();
	}
	
	
	}

}
