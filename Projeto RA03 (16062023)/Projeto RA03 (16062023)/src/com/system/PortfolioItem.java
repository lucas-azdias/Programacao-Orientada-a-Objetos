package com.system;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class PortfolioItem implements Serializable {

	private static final long serialVersionUID = -5768673627426901509L;
	
	private Asset asset;
	private Order result;
	private ArrayList<Order> orders;
	
	public PortfolioItem(Asset asset) {
		this.asset = asset;
		
		orders = new ArrayList<Order>();
		
		updateResult(0);
	}
	
	public String toString() {
		return asset.toString();
	}
	
	public String[] ordersToString() {
		String[] orders = new String[this.orders.size()];
		for (int i = 0; i < this.orders.size(); i++) {
			orders[i] = this.orders.get(i).toString();
		}
		return orders;
	}
	
	public Order getResult() {
		updateResult(0);
		return result;
	}
	
	public void insertOrder(int amount, double price, int year, int month, int day) {
		orders.add(new Order(amount, price, year, month, day));
		updateResult(amount);
		sortOrders();
	}
	
	public ReturnData[] getCompiledReturnData(Calendar startDate) {
		ReturnData[] compiledReturnData = new ReturnData[orders.size() + 1];
		for (int i = 0; i < orders.size(); i++) {
			compiledReturnData[i] = orders.get(i).getReturnData(startDate);
		}
		
		updateResult(0);
		
		compiledReturnData[orders.size()] = result.getReturnData(startDate);
		
		return compiledReturnData;
	}
	
	public boolean verifyCode(String code) {
		return asset.verifyCode(code);
	}
	
	public boolean comparesAsset(Asset asset) {
		return this.asset.equals(asset);
	}
	
	private void sortOrders() {
		int sortingIndex = 0;
		for (int i = 0; i < orders.size(); i++) {
			int minDateRdIndex = sortingIndex;
			Order tempRd = orders.get(sortingIndex);
			for (int j = 0; j < orders.size() - sortingIndex - 1; j++) {
				tempRd = orders.get(sortingIndex + j + 1);
				if (orders.get(minDateRdIndex).getDate().compareTo(tempRd.getDate()) > 0) {
					minDateRdIndex = sortingIndex + j + 1;
				}
			}
			tempRd = orders.get(sortingIndex);
			orders.set(sortingIndex, orders.get(minDateRdIndex));
			orders.set(minDateRdIndex, tempRd);
		}
	}
	
	private void updateResult(int amount) {
		if (result != null) {
			amount += result.getAmount();
		}
		
		result = new Order(
			amount,
			asset.getCurrentPrice(),
			Calendar.getInstance().get(Calendar.YEAR),
			Calendar.getInstance().get(Calendar.MONTH) + 1,
			Calendar.getInstance().get(Calendar.DATE)
		);
	}

}
