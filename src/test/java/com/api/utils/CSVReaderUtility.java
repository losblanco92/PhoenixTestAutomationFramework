package com.api.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import com.dataproviders.api.bean.UserBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVReaderUtility {
	
	
	public static <T> Iterator<T> loadCSV (String pathOfCSVFile, Class<T> bean) {
		
		
		
		InputStream stream= Thread.currentThread().getContextClassLoader().getResourceAsStream(pathOfCSVFile);
		InputStreamReader reader = new InputStreamReader(stream);
		CSVReader csvReader = new CSVReader(reader);
		
		CsvToBean<T> csvToBean = new CsvToBeanBuilder(csvReader)
				                            .withType(bean)
				                            .withIgnoreEmptyLine(true)
                                             .build();	
		
		List<T> list = csvToBean.parse();
		 return list.iterator();
	}

}
