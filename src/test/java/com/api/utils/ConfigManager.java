package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.qameta.allure.Step;

public class ConfigManager {
	private static final Logger LOGGER = LogManager.getLogger(ConfigManager.class);
	private static Properties properties = new Properties();
	private static String path;
	public static String env;

	private ConfigManager() {

	}

	static {
		LOGGER.info("Reading environment value passed from terminal");

		if (System.getProperty("env") == null) {

			LOGGER.warn("No environment value passed. Picking up QA environment to run the test");
		}

		env = System.getProperty("env", "qa");

		LOGGER.info("Running the test in the {} environment ", env);

		switch (env.toLowerCase().trim()) {
		case "qa" -> path = "config/config.qa.properties";

		case "uat" -> path = "config/config.uat.properties";

		case "dev" -> path = "config/config.dev.properties";

		default -> path = "config/config.qa.properties";

		}
		
		LOGGER.info("Using the properties file from the {} path", path);


		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);

		if (input == null) {
			LOGGER.error("Cannot find file in {} path", path);
			throw new RuntimeException("File not found in the path " + path);

		}

		try {
			properties.load(input);
		} catch (IOException e) {
			LOGGER.error("Cannot find file in {} path", path, e);
			e.printStackTrace();
		}

	}

	@Step("Getting Property Value from config file")

	public static String getProperty(String key) {

		return properties.getProperty(key);

	}
}
