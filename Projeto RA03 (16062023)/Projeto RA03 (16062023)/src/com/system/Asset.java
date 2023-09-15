package com.system;

import java.io.Serializable;

public abstract class Asset implements Typable, Serializable {
	
	private static final long serialVersionUID = 7883369158822293408L;
	
	private String name;
	private String code;
	
	public Asset(String name, String code) {
		this.name = name;
		this.code = code.toUpperCase();
	}
	
	public abstract double getCurrentPrice();
	
	public String toString() {
		return typeToString() + " " +  name + " (" + code + ")";
	}
	
	public boolean verifyCode(String code) {
		if (this.code.contentEquals(code.toUpperCase())) {
			return true;
		} else {
			return false;
		}
	}
	
	public String dataInString(String separator) {
		return type() + separator + name + separator + code + separator + getCurrentPrice();
	}
	
}
