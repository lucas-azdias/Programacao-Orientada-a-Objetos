package com.system;

public class TreasuryBond extends Bond {

	private static final long serialVersionUID = -6725471990331549068L;

	public TreasuryBond(String name, String code, double price, double maturityPrice, int year, int month, int day) {
		super(name, code, price, maturityPrice, year, month, day);
	}

	public TreasuryBond(double yield, String name, String code, double maturityPrice, int year, int month, int day) {
		super(yield, name, code, maturityPrice, year, month, day);
	}

	@Override
	public String typeToString() {
		return "[Treasury Bond]";
	}

	@Override
	public String type() {
		return "tbond";
	}

}
