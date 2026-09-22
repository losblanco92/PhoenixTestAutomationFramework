package com.api.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import io.qameta.allure.Step;

public class CSVReaderUtility {
	
	private static final Logger LOGGER = LogManager.getLogger(CSVReaderUtility.class);
	
	
	private CSVReaderUtility () {
		
		
	}

	@Step("Loading Test Data from the csv file")

	public static <T> Iterator<T> loadCSV (String pathOfCSVFile, Class<T> bean) {
		
		LOGGER.info("Loding the csv file from the path {}", pathOfCSVFile);
		
		InputStream stream= Thread.currentThread().getContextClassLoader().getResourceAsStream(pathOfCSVFile);
		InputStreamReader reader = new InputStreamReader(stream);
		CSVReader csvReader = new CSVReader(reader);
		
		LOGGER.info("Converting the CSV to bean class", bean);
		CsvToBean<T> csvToBean = new CsvToBeanBuilder(csvReader)
				                            .withType(bean)
				                            .withIgnoreEmptyLine(true)
                                             .build();	
		
		List<T> list = csvToBean.parse();
		 return list.iterator();
	}

}
