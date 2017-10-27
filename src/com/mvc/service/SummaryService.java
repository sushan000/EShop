package com.mvc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mvc.domain.ItemBean;
import com.mvc.domain.SummaryOfAllItem;

public class SummaryService {
	private SummaryOfAllItem cartSummary;// item and its total
	private ItemService itemService;
	private int GrandTotal=0;
	//private Map<Integer,Integer> cart= new HashMap<>();
	public SummaryService() {
		cartSummary = new SummaryOfAllItem();
		itemService = new ItemService();
	}
	
	public SummaryOfAllItem getSummary(Map<Integer, Integer> itemIdQuantity){
		List<ItemBean> itemList = new ArrayList<>();
		for(Map.Entry<Integer, Integer> entry: itemIdQuantity.entrySet()) {
			
			ItemBean item= new ItemBean();
			item = itemService.getItemById(entry.getKey());
			item.setId(entry.getKey());
			item.setQuantity(entry.getValue());
			//System.out.println(item.getPrice());
			itemList.add(item);
			GrandTotal += item.getPrice()*(entry.getValue());
		}
		cartSummary.setItem(itemList);
		cartSummary.setGrandTotal(GrandTotal);
		return cartSummary;
	}
	public Map<Integer,Integer> getUpdatedSummary(Map<Integer,Integer> itemIdQuantity,Integer id , Integer quantity) {
		Map<Integer,Integer> cart= new HashMap<>();
		for (Map.Entry<Integer, Integer> entry: itemIdQuantity.entrySet()) {
			
			if (entry.getKey().equals(id)) {
				cart.put(entry.getKey(), entry.getValue()+quantity.intValue());
			}else {
				cart.put(entry.getKey(),entry.getValue());
			}
			
		}
		
		return cart;
	}
	
}
