package com.api.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.cdimascio.dotenv.Dotenv;
import io.qameta.allure.Step;

public class EnvUtility {
	private static final Logger LOGGER = LogManager.getLogger(EnvUtility.class);

	private static Dotenv dotenv;

	static {

		LOGGER.info("Loading .env file...");
		dotenv = Dotenv.load();
	}

	private EnvUtility() {

	}

	@Step("Retreiving Secret from the .env File")

	public static String getValue(String varName)

	{
  LOGGER.info("Reading the value of {} from .env", varName);
		return dotenv.get(varName);
	}

}
