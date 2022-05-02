package com.vti.utils.properties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtils {
	private static Connection connection;
	private DatabaseProperties databaseProperties;

	public JdbcUtils() throws FileNotFoundException, IOException {
		databaseProperties = new DatabaseProperties();
	}

	public void isConnect() throws SQLException, ClassNotFoundException {
		String url = databaseProperties.getProperties("url");
		String username = databaseProperties.getProperties("username");
		String password = databaseProperties.getProperties("password");

		Class.forName(databaseProperties.getProperties("driver"));

		connection = DriverManager.getConnection(url, username, password);
		System.out.println("Connect success!");
	}

	public Connection Connect() throws ClassNotFoundException, SQLException {
		if (connection == null|| connection.isClosed()) {
			String url = databaseProperties.getProperties("url");
			String username = databaseProperties.getProperties("username");
			String password = databaseProperties.getProperties("password");

			Class.forName(databaseProperties.getProperties("driver"));

			connection = DriverManager.getConnection(url, username, password);

		}
		return connection;
	}

	public void disconnect() throws SQLException {
		if (connection != null && connection.isClosed()) {
			connection.close();
//			connection = null;
		}
	}
}
