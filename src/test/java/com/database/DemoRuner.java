package com.database;

import java.sql.SQLException;

public class DemoRuner {
	
	public synchronized static void main(String[] args) throws SQLException {
		
		DataBaseManager.createConenction();
		DataBaseManager.createConenction();
		DataBaseManager.createConenction();
		DataBaseManager.createConenction();
	}

}
