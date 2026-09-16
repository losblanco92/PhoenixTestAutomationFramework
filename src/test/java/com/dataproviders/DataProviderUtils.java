package com.dataproviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	private static final Logger LOGGER = LogManager.getLogger(DataProviderUtils.class);

	@DataProvider(name = "LoginAPIDataProvider", parallel = true)
	public static Iterator<UserBean> loginAPIDataProvider() {

		LOGGER.info("Loading Data from CSV file testData/LoginCreds.csv");
		return CSVReaderUtility.loadCSV("testData/LoginCreds.csv", UserBean.class);

	}

	@DataProvider(name = "CreateJobAPIDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> createJobAPIDataProvider() {
		LOGGER.info("Loading Data from CSV file testData/CreateJobData.csv");

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
		LOGGER.info("Generating fake CreateJob Data with faker count {}", fakerCount);
		return FakerDataGenerator.generateFakeCreateJobData(fakerCountInt);

	}

	@DataProvider(name = "LoginAPIJsonDataProvider", parallel = true)
	public Iterator<UserBean> loginApiJsonDataProvider() {

		return JsonReaderUtility.loadJson("testData/loginAPITest.json", UserBean[].class);

	}

	@DataProvider(name = "CreateJobAPIJsonDataProvider", parallel = true)
	
	
	public Iterator<CreateJobPayload> createJobApiJsonDataProvider() {
		LOGGER.info("Loading Data from JSON file testData/CreateJobAPIData.json");

		return JsonReaderUtility.loadJson("testData/CreateJobAPIData.json", CreateJobPayload[].class);

	}

	@DataProvider(name = "LoginAPIDataProviderUsingExcel", parallel = true)
	public static Iterator<UserBean> loginAPIDataProviderUsingExcel() {
		LOGGER.info("Loading Data from Excel file testData/PhoenixTestData.xlsx");

		return ExcelReaderUtility.loadExcelTestData("testData/PhoenixTestData.xlsx", "LoginTestData", UserBean.class);

	}

	@DataProvider(name = "CreateJobAPIExcelDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> createJobAPIExcelDataProvider() {
		LOGGER.info("Loading Data from Excel file testData/PhoenixTestData.xlsx");

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
		LOGGER.info("Loading Data from DataBase for CreateJob payload");

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
