package com.reader.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@Entity

@Table(name = "user_store")

public class User implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="UserAccountId")
	private String userId;
	
	@Column(name="Name")
	private String Username;
	
	@Column(name="Email_Id")
	private String emailId;
	@Column(name="Secure_Password")
	private String password;
	@Column(name="User_Role")
	private String userRole;
	@ Column(name="Mobile_no")	
	private String mobileNo;
	@ Column(name="salt_key")	
	private String key;
	@Column(name="Last_login_at")
	private Date timeStamp;
	public Date getTimeStamp() {
		return timeStamp;
	}
	@PreUpdate
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	@Column(name="enabled")
	boolean enabled = true;
	
	
}
