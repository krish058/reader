package com.reader.util;

public class Utils {
	public static String getNotNullString(String s) {
		if (s != null) {
			return s;
		}
		return "null";
	}

	public static String getNotNullObject(Object object) {
		if(object != null){
			return object.toString();
		}
		return null;
	}
	
	public static double truncateDecimal(double value, int precision) {
		//For value = 34.342, method returns 34.34 when precision is 2 
		return Math.floor(value * (Math.pow(10, precision)))/Math.pow(10, precision);
	}
}