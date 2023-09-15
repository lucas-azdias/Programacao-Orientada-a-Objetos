package com.system;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Person implements Serializable {
	
	private static final long serialVersionUID = 1085146587819733775L;
	
	private String name;
	private String idCode;
	private Calendar birthday;
	
	public Person(String name, String idCode, int year, int month, int day) {
		this.name = name;
		this.idCode = idCode;
		
		birthday = new GregorianCalendar(year, month - 1, day, 0, 0, 0);
	}
	
	public String toString() {
		return name + " (" + Utils.dateToString(birthday, true, false) + ")";
	}
	
	public boolean verifyIdentity(String idCode) {
		if (this.idCode.contentEquals(idCode)) {
			return true;
		} else {
			return false;
		}
	}
	
	public String dataInString(String separator) {
		return name + separator + idCode + separator + Utils.dateToString(birthday, separator, false, true);
	}

}
