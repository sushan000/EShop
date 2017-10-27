package com.mvc.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mvc.domain.LoginBean;
import com.mvc.util.DBConnection;


public class LoginDAO {
	private DBConnection dbConnection = new DBConnection();
	public java.sql.PreparedStatement stmt;

	public String authenticate(LoginBean login) {
		LoginBean loginDetail = new LoginBean();
		loginDetail = getUserCredential();
		if((login.getUsername().equals(loginDetail.getUsername()))&& (login.getPassword().equals(loginDetail.getPassword()))){
			return "SUCCESS";
		}
		
		return "FAILURE";
	}

	public LoginBean getUserCredential() {
		try {
			String selectSQL = "SELECT * FROM user";
			dbConnection.getConnection();

			stmt=dbConnection.connection.prepareStatement(selectSQL);
			ResultSet rs=stmt.executeQuery();
			LoginBean testBean = new LoginBean();
			
			while(rs.next()) {
				testBean.setUsername(rs.getString("username"));
				testBean.setPassword(rs.getString("password"));
				System.out.println(rs.getString("username"));
				System.out.println(rs.getString("password"));
			}
			return testBean;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
