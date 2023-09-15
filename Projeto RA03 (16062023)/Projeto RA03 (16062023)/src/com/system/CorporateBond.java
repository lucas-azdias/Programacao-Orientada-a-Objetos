package com.system;

public class CorporateBond extends Bond {

	private static final long serialVersionUID = 2358371332102565300L;

	public CorporateBond(String name, String code, double price, double maturityPrice, int year, int month, int day) {
		super(name, code, price, maturityPrice, year, month, day);
	}

	public CorporateBond(double yield, String name, String code, double maturityPrice, int year, int month, int day) {
		super(yield, name, code, maturityPrice, year, month, day);
	}

	@Override
	public String typeToString() {
		return "[Corporate Bond]";
	}

	@Override
	public String type() {
		return "cbond";
	}

}
