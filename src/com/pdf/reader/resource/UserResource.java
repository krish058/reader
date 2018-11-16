package com.pdf.reader.resource;

import org.springframework.http.ResponseEntity;


import com.pdf.reader.resource.util.Response;
import com.pdf.reader.resource.util.WebResponse;

public interface UserResource {

	
	public ResponseEntity<Response> signUp(String platform, String appVersion, String user_details, boolean webapp);

	ResponseEntity<Response> weblogin(String platform, String appVersion, String login_details, boolean webapp);

	ResponseEntity<WebResponse> webapplogout(String sessionid, boolean webapp);

}
