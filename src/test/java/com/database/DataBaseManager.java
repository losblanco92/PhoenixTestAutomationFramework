package com.database;

import java.sql.Connection;
import java.sql.SQLException;

import com.api.utils.ConfigManager;
import com.api.utils.EnvUtility;
import com.api.utils.VaultDBConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataBaseManager {
	private static boolean isVaultUp = true;
	private static final String DB_URL = loadSecret("DB_URL");
	private static final String DB_USER_NAME = loadSecret("DB_USER_NAME");
	private static final String DB_PASSWORD = loadSecret("DB_PASSWORD");

	public static String loadSecret(String key) {

		String value = null;

		if (isVaultUp) {
			value = VaultDBConfig.getSecret(key);

			if (value == null) {

				System.err.println("VAULT IS DOWN");
				isVaultUp = false;

			}

			else {
				System.out.println("READING VALUE FROM VAULT");

				return value;
			}
		}

		System.out.println("READING VALUE from ENV FILE");
		value = EnvUtility.getValue(key);
		return value;

	}

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
		} else if (hikariDataSource.isClosed()) {
			throw new SQLException("HIKARI DATASOURCE IS CLOSED");
		}

		connection = hikariDataSource.getConnection();
		return connection;

	}

}
