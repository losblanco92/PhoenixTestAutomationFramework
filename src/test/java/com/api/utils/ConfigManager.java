package com.api.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

	private static Properties properties = new Properties();
	private static String path;
	private static String env;

	private ConfigManager() {

	}

	static {

		env = System.getProperty("env", "qa");

		switch (env.toLowerCase().trim()) {
		case "qa" -> path = "config/config.qa.properties";

		case "uat" -> path = "config/config.uat.properties";

		case "dev" -> path = "config/config.dev.properties";

		default -> path = "config/config.qa.properties";

		}

		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);

		if (input == null) {
			throw new RuntimeException("File not found in the path " + path);

		}

		try {
			properties.load(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String getProperty(String key) {

		return properties.getProperty(key);

	}
}
