package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.database.DataBaseManager;
import com.database.model.CustomerProductDBModel;

public class CustomerProductDao {
	private static final Logger LOGGER = LogManager.getLogger(CustomerProductDao.class);

	private CustomerProductDao () {
		
	}

	private final static String CUSTOMER_PRODUCT_QUERY = """
			SELECT * FROM tr_customer_product where id=?

			""";

	public static CustomerProductDBModel getCustomerProductInfo(int tr_customer_product_id) {
		CustomerProductDBModel customerProductDBModel = null;
		Connection conn;
		try {
			LOGGER.info("GETTING THE CONNECTION FROM DATABASE MANAGER");
			conn = DataBaseManager.getConnection();
			LOGGER.info("EXECUTING SQL QUERY");		
			PreparedStatement preparedStatement = conn.prepareStatement(CUSTOMER_PRODUCT_QUERY);

		preparedStatement.setInt(1, tr_customer_product_id);

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {

			customerProductDBModel = new CustomerProductDBModel(resultSet.getInt("id"),
					resultSet.getInt("tr_customer_id"), resultSet.getString("dop"), resultSet.getString("popurl"),
					resultSet.getString("imei1"), resultSet.getString("imei2"), resultSet.getString("serial_number"),
					resultSet.getInt("mst_model_id"));

		}
		}
		
		catch (SQLException e) {
			LOGGER.error("CANNOT CONVERT RESULTSET TO BEAN", e);

			System.err.println(e.getMessage());
		}

		return customerProductDBModel;

	}

}
