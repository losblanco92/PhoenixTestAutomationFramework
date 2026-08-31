package com.dataproviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.UserCredentials;
import com.api.utils.CSVReaderUtility;
import com.api.utils.CreateJobBeanMapper;
import com.api.utils.ExcelReaderUtility;
import com.api.utils.FakerDataGenerator;
import com.api.utils.JsonReaderUtility;
import com.database.dao.CreateJobPayloadDataDao;
import com.dataproviders.api.bean.CreateJobBean;
import com.dataproviders.api.bean.UserBean;

public class DataProviderUtils {

	@DataProvider(name = "LoginAPIDataProvider", parallel = true)
	public static Iterator<UserBean> loginAPIDataProvider() {

		return CSVReaderUtility.loadCSV("testData/LoginCreds.csv", UserBean.class);

	}

	@DataProvider(name = "CreateJobAPIDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> createJobAPIDataProvider() {

		Iterator<CreateJobBean> createJobBeanIterator = CSVReaderUtility.loadCSV("testData/CreateJobData.csv",
				CreateJobBean.class);

		List<CreateJobPayload> payLoadList = new ArrayList<CreateJobPayload>();

		CreateJobBean tempBean;
		CreateJobPayload tempPayload;

		while (createJobBeanIterator.hasNext()) {
			tempBean = createJobBeanIterator.next();

			tempPayload = CreateJobBeanMapper.mapper(tempBean);
			payLoadList.add(tempPayload);
		}

		return payLoadList.iterator();

	}

	@DataProvider(name = "CreateJobAPIFakerDataProvider")
	public static Iterator<CreateJobPayload> createJobAPIFakerDataProvider() {
		String fakerCount = System.getProperty("fakerCount", "5");
		int fakerCountInt = Integer.parseInt(fakerCount);

		return FakerDataGenerator.generateFakeCreateJobData(fakerCountInt);

	}

	@DataProvider(name = "LoginAPIJsonDataProvider", parallel = true)
	public Iterator<UserCredentials> loginApiJsonDataProvider() {

		return JsonReaderUtility.loadJson("testData/loginAPITest.json", UserCredentials[].class);

	}

	@DataProvider(name = "CreateJobAPIJsonDataProvider", parallel = true)
	public Iterator<CreateJobPayload> createJobApiJsonDataProvider() {

		return JsonReaderUtility.loadJson("testData/CreateJobAPIData.json", CreateJobPayload[].class);

	}

	@DataProvider(name = "LoginAPIDataProviderUsingExcel", parallel = true)
	public static Iterator<UserBean> loginAPIDataProviderUsingExcel() {

		return ExcelReaderUtility.loadExcelTestData("testData/PhoenixTestData.xlsx", "LoginTestData", UserBean.class);

	}

	@DataProvider(name = "CreateJobAPIExcelDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> createJobAPIExcelDataProvider() {

		Iterator<CreateJobBean> iterator = ExcelReaderUtility.loadExcelTestData("testData/PhoenixTestData.xlsx",
				"CreateJobTestData", CreateJobBean.class);
		CreateJobBean tempBean;
		CreateJobPayload tempPayload;
		List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();

		while (iterator.hasNext()) {
			tempBean = iterator.next();
			tempPayload = CreateJobBeanMapper.mapper(tempBean);
			payloadList.add(tempPayload);
		}

		return payloadList.iterator();

	}

	@DataProvider(name = "CreateJobAPIDBDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> createJobAPIDBDataProvider() {

		List<CreateJobBean> beanList = CreateJobPayloadDataDao.getCreateJobPayloadData();

		Iterator<CreateJobBean> iterator = beanList.iterator();

		CreateJobBean tempBean;
		CreateJobPayload tempPayload;

		List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();

		while (iterator.hasNext()) {
			tempBean = iterator.next();

			tempPayload = CreateJobBeanMapper.mapper(tempBean);

			payloadList.add(tempPayload);

		}

		return payloadList.iterator();

	}

}
