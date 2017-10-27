package com.mvc.service;

import java.util.HashMap;
import java.util.Map;

public class Driver {
	public static void main(String[] args) {
		// LoginBean testBean = new LoginBean();
		// LoginDAO loginDao = new LoginDAO();
		// testBean = loginDao.getUserCredential();
		//
		// System.out.println(testBean.getUsername());
		// List<ItemBean> itemlist = new ArrayList<>();
		// ItemBean itemBean = new ItemBean();
		// ItemService itemDAO = new ItemService();
		// itemlist= itemDAO.getItemDetails();
		// for(ItemBean item:itemlist) {
		// System.out.println(item.toString());
		// }

		// ItemService item = new ItemService();
		// System.out.println(item.getItemById(1));
		//
		SummaryService summary = new SummaryService();
		Map<Integer, Integer> map = new HashMap<>();
		Map<Integer, Integer> newMap = new HashMap<>();
		
		map.put(1, 1);
		map.put(2, 2);
		map.put(5, 9);
		newMap=summary.getUpdatedSummary(map, 5, 10);
		map = newMap;
		newMap=summary.getUpdatedSummary(map, 2, 10);
		map = newMap;
		newMap=summary.getUpdatedSummary(map, 5, 10);
		map= newMap;
		for (Map.Entry<Integer, Integer> entry : newMap.entrySet()) {

			System.out.println(entry.getKey()+"\t");
			System.out.println(entry.getValue()+"\n");

		}

	}
}
