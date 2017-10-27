package com.mvc.domain;

import java.util.List;

public class SummaryOfAllItem {

	private List<ItemBean> item;
	private int grandTotal;
	public List<ItemBean> getItem() {
		return item;
	}
	public void setItem(List<ItemBean> item) {
		this.item = item;
	}
	public int getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(int grandTotal) {
		this.grandTotal = grandTotal;
	}

	

}
