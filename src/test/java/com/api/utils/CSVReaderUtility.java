package com.api.utils;

import java.io.InputStreamReader;

import com.dataproiders.api.bean.UserBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVReaderUtility {
	
	
	public void loadCSV (String pathOfCSVFile) {
		
		
		
		Thread.currentThread().getContextClassLoader().getResourceAsStream(pathOfCSVFile);
		InputStreamReader steam = new InputStreamReader(null);
		CSVReader csvReader = new CSVReader(null);
		
		CsvToBean<UserBean> csvToBean = new CsvToBeanBuilder(csvReader)
				                            .withType(UserBean.class)
				                            .withIgnoreEmptyLine(true)
                                             .build();		
	}

}
