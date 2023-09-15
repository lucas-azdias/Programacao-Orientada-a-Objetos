package com.system;

public class Stock extends Asset {
	
	private static final long serialVersionUID = 4204100887199361489L;
	
	private double price;
	
	public Stock(String name, String code, double price) {
		super(name, code);
		this.price = price;
	}

	@Override
	public double getCurrentPrice() {
		return price;
	}

	@Override
	public String typeToString() {
		return "[Stock]";
	}

	@Override
	public String type() {
		return "stock";
	}

	@Override
	public String toString() {
		return super.toString() + " - " + "R$" + Double.toString(price).replace('.', ',');
	}

}
