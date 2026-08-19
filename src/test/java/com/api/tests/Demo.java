package com.api.tests;

import java.util.Iterator;

import com.api.utils.CSVReaderUtility;
import com.dataproviders.api.bean.CreateJobBean;

public class Demo {

	public static void main(String[] args) {
		Iterator<CreateJobBean> bean= CSVReaderUtility.loadCSV("testData/CreateJobData.csv", CreateJobBean.class);

		while(bean.hasNext())
		{
			System.out.println(bean.next());
		}
		
		
		
		
	}

}
