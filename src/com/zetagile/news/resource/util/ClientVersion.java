package com.zetagile.news.resource.util;

import com.zetagile.news.resource.util.SessionSingleton;
import com.zetagile.news.resource.util.WebResponse;
import com.zetagile.news.util.ProjectConfig;

public class ClientVersion {

	private final int UPTODATE = 0;
	private final int UPDATE_RECOMMENDED = 1;
	private final int UPDATE_FORCED = 2;
	private final String RECOMMENDED_UPDATE_MESSAGE;
	private final String FORCED_UPDATE_MESSAGE;
	
	private final String IOS_STRING = "ios";
	private final String ANDROID_STRING = "android";
	private final String VERSION_SEPARATOR = "\\.";
	private final String DELIMITER = "-";
	
	ProjectConfig projectConfig;
	
	public ClientVersion() {
		projectConfig = new ProjectConfig();
		RECOMMENDED_UPDATE_MESSAGE = projectConfig.getRecommendedMessage();
		FORCED_UPDATE_MESSAGE = projectConfig.getForcedMessage();
	}
	
	public Response checkVersionCode(String typeOfOs, String versionName, boolean storeapp) {
		
		if(typeOfOs != null && typeOfOs.toLowerCase().startsWith(IOS_STRING)) {
			// If the requested device is IOS
			String iosVersion = projectConfig.getIOSVersion();
			String[] iosVersions = iosVersion.split(DELIMITER);

			int code = compareVersion(versionName, iosVersions[0], iosVersions[1]);
			
			if(	code == UPDATE_RECOMMENDED) {
				return recommendedResponse();
			} else if (code == UPDATE_FORCED) {
				return forcedResponse();
			} else if (code == UPTODATE) {
				return uptodateResponse(); 
			} else
				return null;
			
		} else if(typeOfOs != null && typeOfOs.toLowerCase().startsWith(ANDROID_STRING) && !storeapp) {
			// If the requested device is Android
			
			String androidVersion = projectConfig.getAndroidVersion();
			String[] androidVersions = androidVersion.split(DELIMITER);
			
			int code = compareVersion(versionName, androidVersions[0], androidVersions[1]);
			
			if(code == UPDATE_RECOMMENDED) {
				return recommendedResponse();
			} else if (code == UPDATE_FORCED) {
				return forcedResponse();
			} else if (code == UPTODATE) {
				return uptodateResponse(); 
			} else {
				return null;
			}
			
		} else if (typeOfOs != null && typeOfOs.toLowerCase().startsWith(ANDROID_STRING) && storeapp){
			String androidVersion = projectConfig.getAndroidStoreVersion();
			String[] androidVersions = androidVersion.split(DELIMITER);
			
			int code = compareVersion(versionName, androidVersions[0], androidVersions[1]);
			
			if(code == UPDATE_RECOMMENDED) {
				return recommendedResponse();
			} else if (code == UPDATE_FORCED) {
				return forcedResponse();
			} else if (code == UPTODATE) {
				return uptodateResponse(); 
			} else {
				return null;
			}
		}else {
			// If the request is from other sources
			return null;
		}
	}

	private Response uptodateResponse() {
		Response response = new Response();
		response.setfUpdate(false);
		response.setrUpdate(false);
		return response;
	}

	private Response forcedResponse() {
		Response response = new Response();
		response.setfUpdate(true);
		response.setrUpdate(true);
		response.setuMessage(FORCED_UPDATE_MESSAGE);
		return response;
	}

	private Response recommendedResponse() {
		Response response = new Response();
		response.setfUpdate(false);
		response.setrUpdate(true);
		response.setuMessage(RECOMMENDED_UPDATE_MESSAGE);
		return response;
	}

	private int compareVersion(String clientVersion, String minVersion, String currentVersion) {
		
		if(clientVersion == null 
				|| minVersion == null
				|| minVersion.isEmpty()
				|| clientVersion.isEmpty()
				|| currentVersion == null
				|| currentVersion.isEmpty()) {
			return -1;
		}
		
		try {
			String[] clientVersions = clientVersion.split(VERSION_SEPARATOR);
			String[] minVersions = minVersion.split(VERSION_SEPARATOR);
			String[] currentVersions = currentVersion.split(VERSION_SEPARATOR);
			
			if(clientVersions!= null 
					&& clientVersions.length > 0 
					&& minVersions != null 
					&& minVersions.length > 0 
					&& currentVersions != null 
					&& currentVersions.length > 0 ){
			
				int current_client = compare(currentVersions, clientVersions);
				int client_min = compare(clientVersions, minVersions);
				
				if(current_client <= 0)
					return UPTODATE;
				else if(current_client > 0 && client_min >= 0)
					return UPDATE_RECOMMENDED;
				else if(client_min < 0)
					return UPDATE_FORCED;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}

	/**
	 * Compares two version names and returns integer value
	 * 
	 * Returns positive integer if version1 > version2
	 * Returns zero if version1 = version2
	 * Returns negative integer if version1 < version2
	 * 
	 * @param version1 - Version 1 to be compared
	 * @param version2 - Version 2 to be compared
	 * @return Integer
	 */
	public int compare(String version1, String version2) {
		if (version1 != null && version2!= null) {
			String[] allVersions1 = version1.split(VERSION_SEPARATOR);
			String[] allVersions2 = version2.split(VERSION_SEPARATOR);
			
			return compare(allVersions1, allVersions2);
		}
		
		return 0;
	}
	

	/**
	 * Compares two array of major, minor versions and returns integer value
	 * 
	 * Returns positive integer if version1 > version2
	 * Returns zero if version1 = version2
	 * Returns negative integer if version1 < version2
	 * 
	 * @param version1 - Version 1 to be compared
	 * @param version2 - Version 2 to be compared
	 * @return Integer
	 */
	private int compare(String[] versions1, String[] versions2) {
		
		int numOfVersions = versions2.length < versions1.length?versions2.length:versions1.length;
		
		for(int i = 0; i < numOfVersions; i++) {

			if(Integer.parseInt(versions1[i]) == Integer.parseInt(versions2[i])) 
				continue;
			else if (Integer.parseInt(versions1[i]) < Integer.parseInt(versions2[i]))
				return -1;
			else 
				return 1;
		}
		
		return 0;
	}

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
