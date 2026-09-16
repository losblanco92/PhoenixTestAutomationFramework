package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.database.DataBaseManager;
import com.database.model.CustomerDBModel;

public class CustomerDao {
	
	private static final Logger LOGGER = LogManager.getLogger(CustomerDao.class);


	private CustomerDao() {

	}

	private final static String CUSTOMER_DETAILS_QUERY = """
			SELECT * FROM tr_customer where id=? """;

	public static CustomerDBModel getCustomerInfo(int customerId) {

		CustomerDBModel customerDBModel = null;
         Connection conn;
		try {
			LOGGER.info("GETTING THE CONNECTION FROM DATABASE MANAGER");
			conn = DataBaseManager.getConnection();
			LOGGER.info("EXECUTING SQL QUERY");
			PreparedStatement preparedStatement = conn.prepareStatement(CUSTOMER_DETAILS_QUERY);

			preparedStatement.setInt(1, customerId);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				customerDBModel = new CustomerDBModel(resultSet.getInt("id"), resultSet.getString("first_name"),
						resultSet.getString("last_name"), resultSet.getString("mobile_number"),
						resultSet.getString("mobile_number_alt"), resultSet.getString("email_id"),
						resultSet.getString("email_id_alt"),resultSet.getInt("tr_customer_address_id") );

			}
		}

		catch (SQLException e) {
			LOGGER.error("CANNOT CONVERT RESULTSET TO BEAN", e);

			System.err.println(e.getMessage());
		}

		return customerDBModel;

	}

}
