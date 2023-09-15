package com.system;

import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class Bond extends Asset {
	
	private static final long serialVersionUID = -5438131655293857469L;
	
	private double price;
	private double maturityPrice;
	private Calendar maturityDate;
	
	public Bond(String name, String code, double price, double maturityPrice, int year, int month, int day) {
		super(name, code);
		this.price = price;
		this.maturityPrice = maturityPrice;
		
		maturityDate = new GregorianCalendar(year, month - 1, day, 0, 0, 0);
	}
	
	public Bond(double yield, String name, String code, double maturityPrice, int year, int month, int day) {
		super(name, code);
		this.maturityPrice = maturityPrice;
		
		maturityDate = new GregorianCalendar(year, month - 1, day, 0, 0, 0);
		price = getPriceByYield(yield, maturityDate);
	}

	@Override
	public double getCurrentPrice() {
		return price;
	}

	@Override
	public String toString() {
		return super.toString() + " - " + Utils.round(getYield() * 100, 2) + "%";
	}

	@Override
	public String dataInString(String separator) {
		return super.dataInString(separator) + separator + maturityPrice
				+ separator + maturityDate.get(Calendar.YEAR)
				+ separator + (maturityDate.get(Calendar.MONTH) + 1)
				+ separator + maturityDate.get(Calendar.DATE);
	}
	
	public double getYield() {
		long daysToMaturity = Utils.daysBetweenDates(Calendar.getInstance(), maturityDate);
		// Obs.: 365 should be 252 (all working days in the year)
		return Math.pow((maturityPrice / price), (365F / daysToMaturity)) - 1;
	}
	
	private double getPriceByYield(double yield, Calendar maturityDate) {
		long daysToMaturity = Utils.daysBetweenDates(Calendar.getInstance(), maturityDate);
		return 1000F / Math.pow(1F + yield, (daysToMaturity / 365F));
	}

}
