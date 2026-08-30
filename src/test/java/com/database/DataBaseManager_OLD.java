package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseManager_OLD {

	private static final String DB_URL = "jdbc:mysql://64.227.160.186 :3306/SR_DEV";
	private static final String DB_USER_NAME = "srdev_ro_automation";
	private static final String DB_PASSWORD = "Srdev@123";
	private volatile static Connection connection;

	private DataBaseManager_OLD() {

	}

	public static void createConenction() throws SQLException {

		if (connection == null) {       // double-checked locking pattern.

			synchronized (DataBaseManager_OLD.class) { //thread safety

				if (connection == null) {
					connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD);
				}

			}

		}

		
	}

}
