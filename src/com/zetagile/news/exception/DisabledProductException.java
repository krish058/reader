package com.zetagile.news.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason="Unable to create order")
public class DisabledProductException extends Exception{
	private static final long serialVersionUID = 1L;
	
	private String message;
	private Object data;
	
	public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}

	public DisabledProductException(String message, Object data){  
		 this.data = data;
		 this.message = message;
	}  
}
