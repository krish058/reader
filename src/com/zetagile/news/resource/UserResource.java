package com.zetagile.news.resource;

import org.springframework.http.ResponseEntity;


import com.zetagile.news.resource.util.Response;
import com.zetagile.news.resource.util.WebResponse;

public interface UserResource {

	
	public ResponseEntity<Response> signUp(String platform, String appVersion, String user_details, boolean webapp);

	ResponseEntity<WebResponse> weblogin(String platform, String appVersion, String login_details, boolean webapp);

	ResponseEntity<WebResponse> webapplogout(String sessionid, boolean webapp);

}
