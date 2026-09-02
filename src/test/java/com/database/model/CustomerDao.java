package com.database.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.api.request.model.Customer;
import com.database.DataBaseManager;

public class CustomerDao {

	private final static String CUSTOMER_DETAILS_QUERY = """
			SELECT * FROM tr_customer where id=409333 """;

	public static CustomerDBModel getCustomerInfo() throws SQLException {

		CustomerDBModel customerDBModel = null;

		Connection conn = DataBaseManager.getConnection();

		Statement statement = conn.createStatement();

		ResultSet resultSet = statement.executeQuery(CUSTOMER_DETAILS_QUERY);

		while (resultSet.next()) {

			customerDBModel = new CustomerDBModel(resultSet.getString("first_name"), resultSet.getString("last_name"),
					resultSet.getString("mobile_number"), resultSet.getString("mobile_number_alt"),
					resultSet.getString("email_id"), resultSet.getString("email_id_alt"));

		}

		return customerDBModel;

	}

}
