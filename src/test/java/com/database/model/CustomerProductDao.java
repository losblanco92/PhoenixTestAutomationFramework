package com.database.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.database.DataBaseManager;

public class CustomerProductDao {

	private final static String CUSTOMER_PRODUCT_QUERY = """
			SELECT * FROM tr_customer_product where id=?

			""";

	public static CustomerProductDBModel getCustomerProductInfo(int tr_customer_product_id) {
		CustomerProductDBModel customerProductDBModel = null;
		
		try {
		Connection conn = DataBaseManager.getConnection();
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
			System.err.println(e.getMessage());
		}

		return customerProductDBModel;

	}

}
