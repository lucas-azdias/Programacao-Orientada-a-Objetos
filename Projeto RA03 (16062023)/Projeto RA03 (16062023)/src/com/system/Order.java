package com.system;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Order implements Serializable {
	
	private static final long serialVersionUID = 8333609720370757678L;
	
	private int amount;
	private double price;
	private Calendar date;
	
	public Order(int amount, double price, int year, int month, int day) {
		this.amount = amount;
		this.price = price;
		
		date = new GregorianCalendar(year, month - 1, day, 0, 0, 0);
	}
	
	public String toString() {
		return amount + " × " + price + " (" + Utils.dateToString(date, true, false) + ")";
	}
	
	public int getAmount() {
		return amount;
	}
	
	public double getPrice() {
		return price;
	}
	
	public Calendar getDate() {
		return date;
	}
	
	public ReturnData getReturnData(Calendar startDate) {
		return new ReturnData(amount * price, Utils.daysBetweenDates(startDate, date));
	}

}
