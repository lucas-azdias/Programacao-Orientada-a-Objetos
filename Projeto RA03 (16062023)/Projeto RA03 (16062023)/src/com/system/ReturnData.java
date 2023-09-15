package com.system;

import java.io.Serializable;

public class ReturnData implements Serializable {
	
	private static final long serialVersionUID = -4244008446236926726L;
	
	private double totalValue;
	private long rangeDate;
	
	public ReturnData(double totalValue, long rangeDate) {
		this.totalValue = totalValue;
		this.rangeDate = rangeDate;
	}
	
	public double getTotalValue() {
		return totalValue;
	}

	public long getRangeDate() {
		return rangeDate;
	}
}
