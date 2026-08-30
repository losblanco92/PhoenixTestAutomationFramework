package com.database;

import java.sql.Connection;
import java.sql.SQLException;

import com.api.utils.ConfigManager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataBaseManager {

	private static final String DB_URL = "jdbc:mysql://64.227.160.186 :3306/SR_DEV";
	private static final String DB_USER_NAME = "srdev_ro_automation";
	private static final String DB_PASSWORD = "Srdev@123";
	private static final int MAXIMUM_POOL_SIZE = Integer.parseInt(ConfigManager.getProperty("MAXIMUM_POOL_SIZE"));
	private static final int MINIMUM_IDLE_CONNECTIONS = Integer
			.parseInt(ConfigManager.getProperty("MINIMUM_IDLE_CONNECTIONS"));
	private static final int CONNECTION_TIMEOUT_IN_SEC = Integer
			.parseInt(ConfigManager.getProperty("CONNECTION_TIMEOUT_IN_SEC"));
	private static final int IDLE_TIMEOUT_IN_SEC = Integer.parseInt(ConfigManager.getProperty("IDLE_TIMEOUT_IN_SEC"));

	private static final int MAX_LIFETIME_IN_MINS = Integer.parseInt(ConfigManager.getProperty("MAX_LIFETIME_IN_MINS"));

	private static final String POOL_NAME = ConfigManager.getProperty("POOL_NAME");

	private static HikariConfig hikariConfig;
	private volatile static HikariDataSource hikariDataSource;

	private DataBaseManager() {

	}

	private static void initializePool() {

		if (hikariDataSource == null) { // double-checked locking pattern.

			synchronized (DataBaseManager.class) { // thread safety

				if (hikariDataSource == null) {

					hikariConfig = new HikariConfig();
					hikariConfig.setJdbcUrl(DB_URL);
					hikariConfig.setUsername(DB_USER_NAME);
					hikariConfig.setPassword(DB_PASSWORD);
					hikariConfig.setMaximumPoolSize(MAXIMUM_POOL_SIZE);
					hikariConfig.setMinimumIdle(MINIMUM_IDLE_CONNECTIONS);
					hikariConfig.setConnectionTimeout(CONNECTION_TIMEOUT_IN_SEC * 1000);
					hikariConfig.setIdleTimeout(IDLE_TIMEOUT_IN_SEC * 1000);
					hikariConfig.setMaxLifetime(MAX_LIFETIME_IN_MINS * 60 * 1000);
					hikariConfig.setPoolName(POOL_NAME);

					hikariDataSource = new HikariDataSource(hikariConfig);
				}

			}

		}

	}

	public static Connection getConnection() throws SQLException {
		Connection connection = null;
		if (hikariDataSource == null) {
			initializePool();
}
      else if (hikariDataSource.isClosed()) {
    	  throw new SQLException("HIKARI DATASOURCE IS CLOSED");
		}

	connection = hikariDataSource.getConnection();
     return connection;

	}

}
