package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.poiji.bind.Poiji;

import io.qameta.allure.Step;

public class ExcelReaderUtility {
	
	private static final Logger LOGGER = LogManager.getLogger(ExcelReaderUtility.class);

	private ExcelReaderUtility () {
		
		
	}

	@Step("Loading Test Data from the excel file")

	public static <T>Iterator<T> loadExcelTestData(String filePath, String sheetName, Class<T> bean)   {
LOGGER.info("Reading the test data from .xlsx file from {}, and the sheet name is {}", filePath, sheetName);
		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(filePath);

		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(is);
		} catch (IOException e) {
			LOGGER.error("Cannot read the .xslx file {}", filePath);
			e.printStackTrace();
		}
		XSSFSheet mySheet = workbook.getSheet(sheetName);  
		LOGGER.error("Converting XSSFSheet {} to POJO Class of type {}", sheetName, bean);
		  List<T> list = Poiji.fromExcel(mySheet, bean);	
		 return list.iterator();
		  
	}
}
