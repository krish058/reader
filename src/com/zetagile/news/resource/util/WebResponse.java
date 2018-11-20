package com.zetagile.news.resource.util;

import java.io.Serializable;

public class WebResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Object message;
	private String sessionid;
	private String storeid;
	private boolean status;
	private boolean defaultPassword;
	private boolean reservationEnabled;
	private boolean storeEnabled;
	
	public String getSessionid() {
		return sessionid;
	}
	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}
	public String getStoreid() {
		return storeid;
	}
	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}
	public Object getMessage() {
		return message;
	}
	public void setMessage(Object message) {
		this.message = message;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public boolean isDefaultPassword() {
		return defaultPassword;
	}
	public void setDefaultPassword(boolean defaultPassword) {
		this.defaultPassword = defaultPassword;
	}
	public boolean isReservationEnabled() {
		return reservationEnabled;
	}
	public void setReservationEnabled(boolean reservationEnabled) {
		this.reservationEnabled = reservationEnabled;
	}
	public boolean isStoreEnabled() {
		return storeEnabled;
	}
	public void setStoreEnabled(boolean storeEnabled) {
		this.storeEnabled = storeEnabled;
	}
	
}
