package com.mvc.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mvc.domain.ItemBean;
import com.mvc.util.DBConnection;

public class ItemService {
	private DBConnection dbConnection = new DBConnection();
	public PreparedStatement stmt;
	public ResultSet rs;

	public List<ItemBean> getItemDetails() {
		try {
			List<ItemBean> itemList = new ArrayList<>();
			dbConnection.getConnection();
			String selectQuery = "SELECT * FROM item";
			stmt = dbConnection.connection.prepareStatement(selectQuery);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ItemBean item = new ItemBean();
				item.setId(rs.getInt("id"));
				item.setItemName(rs.getString("name"));
				item.setPrice(rs.getInt("price"));
				itemList.add(item);
			}
			return itemList;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public ItemBean getItemById(int id) {
		dbConnection.getConnection();
		String selectQuery = "SELECT * FROM item WHERE id =" + id;
		try {
			stmt = dbConnection.connection.prepareStatement(selectQuery);
			ResultSet rs = stmt.executeQuery();
			ItemBean item = new ItemBean();
			while (rs.next()) {
				item.setId(rs.getInt("id"));
				item.setItemName(rs.getString("name"));
				item.setPrice(rs.getInt("price"));
			}
			return item;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

}
