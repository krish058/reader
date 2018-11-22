package com.reader.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.reader.dao.UserDao;
import com.reader.dto.User;
import com.reader.resource.util.JSONSerializer;
import com.reader.services.UserServices;
import com.reader.util.PasswordUtil;

@Service
@Transactional
public class UserServicesImpl implements UserServices {

	@Autowired
	UserDao userdaoImpl;

	private Logger logger = Logger.getLogger(UserServicesImpl.class.getName());

	@Override
	public boolean newUserRegistration(String username, String emailid, String role, String mobilenumber,
			String password) {
		if (username != null && !username.isEmpty() && emailid != null && !emailid.isEmpty() && role != null
				&& !role.isEmpty() && mobilenumber != null && !mobilenumber.isEmpty() && password != null
				&& !password.isEmpty()) {

			if (userdaoImpl.isUserEmailava(emailid) || userdaoImpl.isUserMobileNoava(mobilenumber)) {
				logger.info("user already exists!!!");
			} else
				try {
					String salt = PasswordUtil.getSalt(30);
					User user = new User();
					user.setEmailId(emailid);
					user.setUsername(username);
					user.setMobileNo(mobilenumber);
					user.setEnabled(true);
					user.setUserRole(role);
					user.setPassword(PasswordUtil.generateSecurePassword(password, salt));// secure
																							// password
					user.setKey(salt);// salt key

					return userdaoImpl.newUserregister(user);
				} catch (Exception e) {
					logger.error("error while hashing password");
					e.printStackTrace();
				}

		} else
			throw new RuntimeException("Invalid paramters!!!");
		return false;
	}

	@Override
	public boolean validateUser(String emailid, String password, String role) {

		if (emailid != null && !emailid.isEmpty() && password != null && !password.isEmpty() && role != null
				&& !role.isEmpty()) {

			User user_id = new User();
			String user_saltkey = null;
			String user_securekey = null;
			String userrole = null;
			Date date = new Date();
			boolean result = false;
			
			if (userdaoImpl.isUserEmailava(emailid)) {
				user_id = userdaoImpl.getidbyemail(emailid);
				userrole = user_id.getUserRole().toString().toLowerCase().trim();
				String user_role = role.toLowerCase().toString().trim();
				if (!userrole.equals(user_role)) {
					logger.info("check role");
					return false;
				}
				user_saltkey = user_id.getKey().toString();
				user_securekey = user_id.getPassword().toString();
				// User provided password to validate
				String providedPassword = password;
				String saltkey = user_saltkey;
				String mySecurePassword = user_securekey;
				boolean passwordMatch = PasswordUtil.verifyUserPassword(providedPassword, mySecurePassword, saltkey);
				if (passwordMatch) {

					User user = new User();
					// user.setTimeStamp(new Date());
					// user.updatelogintime(emailid,date);
					user_id.setTimeStamp(date);
					result = userdaoImpl.updatelogintime(user_id);

					logger.info("Provided user password is correct.");
					return passwordMatch;
				} else {
					logger.error("Provided password is incorrect");
				}

			} else
				logger.info("check emailcredentials");
			return false;

		} else
			logger.info("check entered credentials");
		return false;

	}
	
	public ObjectNode getalluserdetails() {
		JSONSerializer alldetails = new JSONSerializer();
//
		List<User> ae = userdaoImpl.getallUserdetails();
		

		if (ae != null) {
			alldetails.put("userdetails", ae);
		}


		try {
			ObjectNode objectNode = (ObjectNode) new ObjectMapper().readTree(alldetails.toString());
			return objectNode;
		} catch (Exception e) {
			logger.error("Object mapper failed in converting into json ");
			throw new RuntimeException("Something went wrong", e);
		}
	
	}


	

}
