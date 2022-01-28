package com.example.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	private DbConnection() {};
	private static Connection con;
	
	private static String url = "jdbc:mariadb://database-1.crm2rxoxsody.us-east-1.rds.amazonaws.com:3306/bankdetails";
	private static String username = "bankdetails";
	private static String password = "mypassword";
	
	public static Connection getMyConnection() {
		con = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}
