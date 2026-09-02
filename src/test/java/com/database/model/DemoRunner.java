package com.database.model;

import java.sql.SQLException;

import com.api.request.model.Customer;

public class DemoRunner {

	public static void main(String[] args) throws SQLException {
	CustomerDBModel customerDbData	= CustomerDao.getCustomerInfo();
		
	
	System.out.println(customerDbData);
		
	}

}
