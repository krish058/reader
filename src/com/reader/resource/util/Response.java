package com.reader.resource.util;

import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Response implements Serializable {
	
	private static final long serialVersionUID = 1005193018113122249L;
	private Object message;
	
	private Object data;
	
	private boolean status;

	private boolean rUpdate;
	private boolean fUpdate;
	private String uMessage;
	
	public Object getMessage() {
		return message;
	}
	public boolean isStatus() {
		return status;
	}
	public void setMessage(Object message) {
		this.message = message;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public boolean isrUpdate() {
		return rUpdate;
	}
	public boolean isfUpdate() {
		return fUpdate;
	}
	public String getuMessage() {
		return uMessage;
	}
	public void setrUpdate(boolean rUpdate) {
		this.rUpdate = rUpdate;
	}
	public void setfUpdate(boolean fUpdate) {
		this.fUpdate = fUpdate;
	}
	public void setuMessage(String uMessage) {
		this.uMessage = uMessage;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	@Override
	public String toString() {
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		} 
	}
	
}
