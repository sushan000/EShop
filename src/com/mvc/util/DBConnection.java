package com.mvc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;

public class DBConnection {
	public static Connection connection;
	public String dbURL,user,pass;
	
	public boolean getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			dbURL = "jdbc:mysql://localhost:3306/shop";
			user ="admin";
			pass= "root";
			connection = DriverManager.getConnection(dbURL, user, pass);
			return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
}
