package com.database.model;

import java.sql.SQLException;

public class DemoRunner {

	public static void main(String[] args) throws SQLException {
	CustomerProductDBModel customerProductDBModel= CustomerProductDao.getCustomerProductInfo(411451);
		
	
	System.out.println(customerProductDBModel);
		
	}

}
