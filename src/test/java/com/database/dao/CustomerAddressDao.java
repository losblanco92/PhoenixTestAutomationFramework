package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.database.DataBaseManager;
import com.database.model.CustomerAddressDBModel;

public class CustomerAddressDao {

	private final static String CUSTOMER_ADDRESS_QUERY = """
			SELECT * FROM tr_customer_address where id =?
			""";
	
	private CustomerAddressDao () {
		
		
	}

	public static CustomerAddressDBModel getCustomerAddressInfo(int customerAddressId) {
		CustomerAddressDBModel customerAddressDBModel = null;
		
		try {
		Connection conn = DataBaseManager.getConnection();

		PreparedStatement preparedStatement = conn.prepareStatement(CUSTOMER_ADDRESS_QUERY);

		preparedStatement.setInt(1, customerAddressId);

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {

			customerAddressDBModel = new CustomerAddressDBModel(resultSet.getInt("id"),  resultSet.getString("flat_number"),
					resultSet.getString("apartment_name"), resultSet.getString("street_name"),
					resultSet.getString("landmark"), resultSet.getString("area"), resultSet.getString("pincode"),
					resultSet.getString("country"), resultSet.getString("state"));

		}
		
		}
		
		catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		return customerAddressDBModel;

	}

}
