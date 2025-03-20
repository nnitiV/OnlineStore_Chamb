package com.itbulls.cunha.utils.db;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

public class DBUtils {
	private static final String JDBC_MYSQL_HOST = "jdbc:mysql://localhost:3306/";
	private static final String DB_NAME = "onlineshop";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";

	private static BasicDataSource ds = new BasicDataSource();

	static {
		ds.setUrl(JDBC_MYSQL_HOST + DB_NAME);
		ds.setUsername(USERNAME);
		ds.setPassword(PASSWORD);
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver"); // Updated driver class

		// Connection pool settings
		ds.setMinIdle(3); // Minimum number of idle connections
		ds.setMaxIdle(20); // Maximum number of idle connections
		ds.setMaxTotal(50); // Maximum number of active connections
		ds.setMaxWaitMillis(5000); // Maximum wait time for a connection

		// Connection validation
		ds.setValidationQuery("SELECT 1"); // Simple query to validate connections
		ds.setTestOnBorrow(true); // Validate connections before borrowing
		ds.setTestWhileIdle(true); // Validate idle connections

		// Connection leak detection
		ds.setRemoveAbandonedTimeout(60); // Abandoned connections are removed after 60 seconds
		ds.setLogAbandoned(true); // Log abandoned connections
	}

	private DBUtils() {
		// Private constructor to prevent instantiation
	}

	public static Connection getConnection() {
		try {
			Connection connection = ds.getConnection();
			return connection;
		} catch (SQLException e) {
			throw new RuntimeException("Failed to acquire database connection.", e);
		}
	}

}
