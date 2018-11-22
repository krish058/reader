package com.reader.resource;

import org.springframework.http.ResponseEntity;

import com.reader.resource.util.Response;
import com.reader.resource.util.WebResponse;

public interface UserResource {

	
	public ResponseEntity<WebResponse> signUp(String platform, String appVersion, String user_details, boolean webapp);

	ResponseEntity<WebResponse> weblogin(String platform, String appVersion, String login_details, boolean webapp);

	ResponseEntity<WebResponse> webapplogout(String sessionid, boolean webapp);

}
