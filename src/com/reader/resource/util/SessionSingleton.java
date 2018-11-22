package com.reader.resource.util;

import java.math.BigInteger;
import java.security.SecureRandom;

public class SessionSingleton {

	private static String sessionid;
	private static String userid;
	private static long starttime;
	private static long sessionValidPeriod = 6000000;

	private static SessionSingleton sessionSingleton = null;

	private SessionSingleton(){
		SecureRandom secureRandom = new SecureRandom();
		sessionid = new BigInteger(130,secureRandom).toString(32);
	}

	public static SessionSingleton getInstance(){
		if(sessionSingleton == null){
			starttime = System.currentTimeMillis();
			sessionSingleton = new SessionSingleton();
		}
		return sessionSingleton;
	}

	public synchronized String getSessionid() {
		return sessionid;
	}

	public static String getUserid() {
		return userid;
	}

	public static void setUserid(String userid) {
		SessionSingleton.userid = userid;
	}

	public void setMeNull(){
		sessionSingleton = null;
	}

	public boolean validity(long currenttime){

		if((currenttime - starttime)>sessionValidPeriod){
			setMeNull();
			sessionid = null;
			return false;
		}
		else
			return true;
	}

}
