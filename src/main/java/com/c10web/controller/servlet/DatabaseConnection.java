package com.c10web.controller.servlet;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private final static String databaseName = "smartphoneinventorydb";
	private final static String username = "root";
	private final static String password = "";
	private final static String jdbcURL = "jdbc:mysql://localhost:3306/" + databaseName;

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		Class.forName("com.mysql.cj.jdbc.Driver"); 
		conn = DriverManager.getConnection(jdbcURL, username, password);
		return conn;
	}

	public static void main(String[] args) {
		try {
			Connection conn = DatabaseConnection.getConnection(); 
			if (conn != null) {
				System.out.println("The connection has been established");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
