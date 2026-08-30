package com.database;

import java.sql.Connection;
import java.sql.SQLException;

public class DemoRuner {
	
	public synchronized static void main(String[] args) throws SQLException {
		
		Connection connection=DataBaseManager.getConnection();
		System.out.println(connection);
	}

}
