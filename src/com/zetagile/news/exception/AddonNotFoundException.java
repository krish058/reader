package com.zetagile.news.exception;



public class AddonNotFoundException  extends Exception {
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public String getMessage() {
		return message;
	}
	
	public AddonNotFoundException (String message){  
		 this.message = message;
	}
}
