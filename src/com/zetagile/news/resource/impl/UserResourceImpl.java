package com.zetagile.news.resource.impl;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zetagile.news.util.Utils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zetagile.news.resource.util.SessionSingleton;
import com.zetagile.news.resource.util.JSONSerializer;

import com.zetagile.news.dto.User;
import com.zetagile.news.resource.UserResource;
import com.zetagile.news.resource.util.ClientVersion;
import com.zetagile.news.resource.util.Response;
import com.zetagile.news.resource.util.WebResponse;
import com.zetagile.news.services.UserServices;


@RestController
@RequestMapping(path = "/User")
public class UserResourceImpl implements UserResource {

	@Autowired
	UserServices userserviceImpl;

	private Logger logger = Logger.getLogger(UserResourceImpl.class.getName());

	@RequestMapping(path = "/userregistration", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "*")
	@Override
	public ResponseEntity<Response> signUp(@RequestHeader(name = "platform", required = false) String platform,
			@RequestHeader(name = "version", required = false) String appVersion, @RequestBody String user_details,
			@RequestHeader(value = "webapp", defaultValue = "false") boolean webapp) {

		logger.info("user/userregistration/" + Utils.getNotNullString(user_details));

		ClientVersion clientVersion = new ClientVersion();
		Response response = clientVersion.checkVersionCode(platform, appVersion, false);
		WebResponse Wresponse = new WebResponse();
		ClientVersion version = new ClientVersion();
		try {
			String username, emailid, role, mobilenumber, password;
			ObjectMapper mapper = new ObjectMapper();
			JsonNode newuser = mapper.readTree(user_details);
			username = newuser.get("username").asText();
			emailid = newuser.get("email").asText();
			role = newuser.get("userrole").asText();
			mobilenumber = newuser.get("mobileno").asText();
			password = newuser.get("password").asText();
			if (userserviceImpl.newUserRegistration(username, emailid, role, mobilenumber, password)) {
				Wresponse.setStatus(true);
				Wresponse.setMessage("user registered saved successfully");
			} else {
				Wresponse.setStatus(false);
				Wresponse.setMessage("Unable to registered!!");
			}

		} catch (IOException e) {
			e.printStackTrace();
			logger.error("caught IOException in POST /user/userregistration " + e);
			Wresponse.setStatus(false);
			Wresponse.setMessage(e.getMessage());

		}

		return ResponseEntity.ok().body(response);

	}

	@RequestMapping(path = "/userlogin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "*")
	@Override
	public ResponseEntity<WebResponse> weblogin(@RequestHeader(name = "platform", required = false) String platform,
			@RequestHeader(name = "version", required = false) String appVersion, @RequestBody String login_details,
			@RequestHeader(value = "webapp", defaultValue = "false") boolean webapp) {

		logger.info("user/userregistration/" + Utils.getNotNullString(login_details));

		WebResponse wresponse = new WebResponse();
		ClientVersion version = new ClientVersion();
		WebResponse wResponse = version.isAdmin(webapp);

		try {
			String emailid, role, password;
			ObjectMapper mapper = new ObjectMapper();
			JsonNode newuser = mapper.readTree(login_details);
			emailid = newuser.get("email").asText();
			role = newuser.get("userrole").asText();
			password = newuser.get("password").asText();
			if (userserviceImpl.validateUser(emailid, password, role)) {
				//TODO
				//	response.setMessage(autoEventsServices.getEvenetsAndOffers());
				wresponse.setMessage(userserviceImpl.getalluserdetails());
				logger.info("user details -------------"+userserviceImpl.getalluserdetails());
				wresponse.setStatus(true);
			} else {
				wresponse.setStatus(false);
				wresponse.setMessage("unvalid user!!");
			}

		} catch (IOException e) {
			e.printStackTrace();
			logger.error("caught IOException in POST userlogin " + e);
			wresponse.setStatus(false);
			wresponse.setMessage(e.getMessage());

		}

		return ResponseEntity.ok().body(wresponse);

	}
	
	@RequestMapping(path = "/logout/", method = RequestMethod.POST)
	@Override
	public ResponseEntity<WebResponse> webapplogout(@RequestHeader("sessionid") String sessionid,
			@RequestHeader(value = "webapp", defaultValue = "true") boolean webapp) {

		logger.info("POST /userrsrc/logout/");

		WebResponse response = new WebResponse();
		ClientVersion version = new ClientVersion();
		WebResponse wResponse = version.isAdmin(webapp);

		if (wResponse != null) {
			if (wResponse.getSessionid().equals(sessionid)) {
				SessionSingleton.getInstance().setMeNull();
				response.setMessage("logged out successfully");
				response.setStatus(true);
			} else {
				response.setMessage("Invalid sessionid");
				response.setStatus(false);
			}
		} else {
			response.setMessage("Bad request! Invalid parameters!!!");
			response.setStatus(false);
		}
		return ResponseEntity.ok().body(response);
	}

}
