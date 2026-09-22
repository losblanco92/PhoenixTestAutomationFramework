package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.qameta.allure.Step;

public class JsonReaderUtility {
	private static final Logger LOGGER = LogManager.getLogger(JsonReaderUtility.class);

	public JsonReaderUtility() {

	}
	@Step("Loading Test Data from the json file")

	public static <T> Iterator<T> loadJson(String fileName, Class<T[]> clazz) {

		LOGGER.info("Reason JSON from the file {}", fileName);
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);

		ObjectMapper obj = new ObjectMapper();
		T[] classArray;
		List<T> list = null;
		try {
			
			LOGGER.info("Converting JSON data to bean class {}", clazz);
			classArray = obj.readValue(is, clazz);
			list = Arrays.asList(classArray);
		} catch (IOException e) {
			LOGGER.error("Cannot read JSON from the file {}", fileName, e);
			e.printStackTrace();
		}

		return list.iterator();
	}

}
