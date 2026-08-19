package com.dataproviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.api.request.model.CreateJobPayload;
import com.api.utils.CSVReaderUtility;
import com.api.utils.CreateJobBeanMapper;
import com.dataproviders.api.bean.CreateJobBean;
import com.dataproviders.api.bean.UserBean;

public class DataProviderUtils {

	@DataProvider(name = "LoginAPIDataProvider", parallel = true)
	public static Iterator<UserBean> loginAPIDataProvider() {

		return CSVReaderUtility.loadCSV("testData/LoginCreds.csv", UserBean.class);

	}

	@DataProvider(name = "CreateJobAPIDataProvider")
	public static Iterator<CreateJobPayload> createJobAPIDataProvider() {

		Iterator<CreateJobBean> createJobBeanIterator = CSVReaderUtility.loadCSV("testData/CreateJobData.csv",
				CreateJobBean.class);

		List<CreateJobPayload> payLoadList = new ArrayList<CreateJobPayload>();

		CreateJobBean tempBean;
		CreateJobPayload tempPayload;

		while (createJobBeanIterator.hasNext()) {
			tempBean= createJobBeanIterator.next();

			tempPayload = CreateJobBeanMapper.mapper(tempBean);
			payLoadList.add(tempPayload);
		}
		
		return payLoadList.iterator();

	}
}
