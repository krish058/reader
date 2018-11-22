package com.reader.resource.util;

import com.reader.resource.util.SessionSingleton;
import com.reader.resource.util.WebResponse;


public class ClientVersion {

	public WebResponse createSession(boolean webapp) {
		WebResponse response = new WebResponse();
		if(webapp){
			SessionSingleton session = SessionSingleton.getInstance();
			response.setSessionid(session.getSessionid());
			return response;
		}
		return null;
	}
	public WebResponse isAdmin(boolean webapp) {
		WebResponse response = new WebResponse();
		if(webapp){
			SessionSingleton session = SessionSingleton.getInstance();
			response.setSessionid(session.getSessionid());
			return response;
		}
		return null;
	}

}
