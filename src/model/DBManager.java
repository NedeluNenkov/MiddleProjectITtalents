package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

	private static final String DB_NAME = "/project";
	private static final String DB_IP = "localhost";
	private static final String DB_PORT = "3306";
	private static final String DB_PASS = "NikolayTolev93";
	private static final String DB_USER = "root";
	
	private static Connection connection;
	private static volatile DBManager instance;

	private DBManager() {
		try {
			// load driver
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Sorry, driver not loaded or does not exist! Aborting.");
			return;
		}
		System.out.println("Driver loaded.");

		// create connection
		try {
			connection = DriverManager.getConnection("jdbc:mysql://" + DB_IP + ":" + DB_PORT + DB_NAME, DB_USER,DB_PASS);

		} catch (SQLException e) {
			System.out.println("Sorry, connection failed. Maybe wrong credentials?");
			System.out.println(e.getMessage());
		}
	}

	public static DBManager getInstance() {
		if (instance == null) {
			synchronized (DBManager.class) {
				if (instance == null) {
					instance = new DBManager();
				}
			}
		}
		return instance;
	}

	public Connection getConnection() {
		return connection;
	}
}
