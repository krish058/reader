package com.reader.services;

import com.fasterxml.jackson.databind.node.ObjectNode;

public interface UserServices {

	boolean newUserRegistration(String username, String emailid, String role, String mobilenumber, String password);

	boolean validateUser(String emailid, String password, String role);

	ObjectNode getalluserdetails();

}
