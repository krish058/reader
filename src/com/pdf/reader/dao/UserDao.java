package com.pdf.reader.dao;



import java.util.List;

import com.pdf.reader.dto.User;

public interface UserDao {

	boolean isUserMobileNoava(String mobno);
	public boolean isUserEmailava(String email_id);
	boolean newUserregister(User nu);
	User getUser(String user_id);
	public User getidbyemail(String email);
	User getkeybyid(String userId);
	boolean updatelogintime(User user);
	List<User> getallUserdetails();
	
	
	

}
