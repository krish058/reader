package com.pdf.reader.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("deprecation")
@ResponseStatus(value=HttpStatus.DESTINATION_LOCKED, reason="Accepted but not correct")

public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	String message;
    public CustomException(String message){
        this.message = message;
    }
    public String getMessage(){
    	return message;
    }

}