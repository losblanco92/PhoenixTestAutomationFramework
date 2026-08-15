package com.demo.csv;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class ReadCSVFile {
	
	public static void main(String[] args) throws IOException, CsvException {
		
		InputStream input= Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/LoginCreds.csv");
		
		
		InputStreamReader isr = new InputStreamReader(input);
		CSVReader csvReader = new CSVReader(isr);
		
		
	  List<String[]> csvList= csvReader.readAll();
	  
	  
	  for(String [] csvArray:csvList)
	  {
		 for(String data: csvArray)
		 {
			 System.out.print(data+" ");
		 }
		 
		 System.out.println();
	  }
	  
	  
	}

}
