package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.poiji.bind.Poiji;

public class ExcelReaderUtility {
	
	
	private ExcelReaderUtility () {
		
		
	}

	public static <T>Iterator<T> loadExcelTestData(String filePath, String sheetName, Class<T> bean)   {

		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(filePath);

		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XSSFSheet mySheet = workbook.getSheet(sheetName);  

		  List<T> list = Poiji.fromExcel(mySheet, bean);	
		 return list.iterator();
		  
	}
}
