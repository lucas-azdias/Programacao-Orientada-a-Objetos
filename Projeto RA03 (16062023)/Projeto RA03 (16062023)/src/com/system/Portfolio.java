package com.system;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.SortedSet;
import java.util.TreeSet;

public class Portfolio implements Serializable {
	
	private static final long serialVersionUID = 2140517850794994741L;
	
	private Person owner;
	private ArrayList<PortfolioItem> portfolioItems;
	private Calendar startDate;
	
	public Portfolio(String name, String idCode, int year, int month, int day) {
		this.owner = new Person(name, idCode, year, month, day);
		
		portfolioItems = new ArrayList<>();
		startDate = Calendar.getInstance();
	}
	
	public String ownerToString() {
		return owner.toString();
	}
	
	public String ownerDataInString(String separator) {
		return owner.dataInString(separator);
	}
	
	public boolean verifyIdentity(String idCode) {
		return owner.verifyIdentity(idCode);
	}
	
	public boolean insertPortfolioItem(Asset asset) {
		if (!hasPortfolioItem(asset)) {
			portfolioItems.add(new PortfolioItem(asset));
			return true;
		} else {
			return false;
		}
	}
	
	public boolean insertOrder(String code, int amount, double price, int year, int month, int day) {
		PortfolioItem portfolioItem = getPortfolioItem(code);
		
		if (portfolioItem != null) {
			portfolioItem.insertOrder(amount, price, year, month, day);
			
			Calendar newDate = new GregorianCalendar(year, month - 1, day, 0, 0, 0);
			if (startDate.compareTo(newDate) > 0) { // if newDate before startDate
				startDate = newDate;
			}
			
			return true;
		} else {
			return false;
		}
	}
	
	public String[] assetsToString() {
		String[] assets = new String[portfolioItems.size()];
		for (int i = 0; i < portfolioItems.size(); i++) {
			assets[i] = portfolioItems.get(i).toString();
		}
		return assets;
	}
	
	public String[] ordersToString() {
		String[] orders = new String[portfolioItems.size()];
		for (int i = 0; i < portfolioItems.size(); i++) {
			orders[i] = portfolioItems.get(i).toString();
			String[] itemOrders = portfolioItems.get(i).ordersToString();
			for (int j = 0; j < itemOrders.length; j++) {
				orders[i] += "\n\t" + itemOrders[j];
			}
					
		}
		return orders;
	}
	
	public double getReturn() {
		ReturnData[][] allCompiledReturnData = new ReturnData[portfolioItems.size()][];
		SortedSet<Long> dates = new TreeSet<Long>();
		
		for (int i = 0; i < portfolioItems.size(); i++) {
			allCompiledReturnData[i] = portfolioItems.get(i).getCompiledReturnData(startDate);
			
			for (int j = 0; j < allCompiledReturnData[i].length; j++) {
				dates.add(allCompiledReturnData[i][j].getRangeDate());
			}
		}

		double[] lastValues = new double[portfolioItems.size()];
		Arrays.fill(lastValues, 1);
		
		double returnYield = 1;
		
		double lastAllSums = 0, allSums = 0, valuePortfolio = 0;
		for (Long date : dates) {
			allSums = 0;
			valuePortfolio = 0;
			for (int j = 0; j < allCompiledReturnData.length; j++) {
				double value = 0;
				ReturnData temp;
				for (int k = 0; k < allCompiledReturnData[j].length; k++) {
					temp = allCompiledReturnData[j][k];
					if (temp.getRangeDate() > date) {
						break;
					}
					value = temp.getTotalValue();
				}
				allSums += value;
				if (lastValues[j] != 0) {
					valuePortfolio += value;
				}
				lastValues[j] = value;
			}
			if (lastAllSums == 0) {
				lastAllSums = allSums;
			}
			returnYield *= valuePortfolio / lastAllSums;
			// System.out.println(lastAllSums + " " + valuePortfolio + " " + returnYield);
			lastAllSums = allSums;
		}
		
		return returnYield - 1F;
	}
	
	public double getReturnAnualized() {
		long daysFromStart = Utils.daysBetweenDates(startDate, Calendar.getInstance());
		double returnYield = Math.pow(getReturn() + 1F, (365F / daysFromStart)) - 1F;
		if (!Double.isNaN(returnYield)) {
			return returnYield;
		} else {
			return 0;
		}
	}
	
	private boolean hasPortfolioItem(Asset asset) {
		for (PortfolioItem portfolioItem : portfolioItems) {
			if (portfolioItem.comparesAsset(asset)) {
				return true;
			}
		}
		return false;
	}
	
	private PortfolioItem getPortfolioItem(String code) {
		for (PortfolioItem portfolioItem : portfolioItems) {
			if (portfolioItem.verifyCode(code)) {
				return portfolioItem;
			}
		}
		return null;
	}

}
