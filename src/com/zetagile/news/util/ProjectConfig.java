package com.zetagile.news.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class ProjectConfig {

	Properties props;

	public ProjectConfig() {
		props = new Properties();
		try {
			props.load(this.getClass().getClassLoader().getResourceAsStream("project.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getServerName() {
		String SERVER = props.getProperty("SERVER_NAME");

		if(SERVER != null)
			SERVER = SERVER.trim();

		return SERVER;
	}
	
	public String getWebAppServerName(){
		String SERVER = props.getProperty("WEB_APP_SERVER_NAME");

		if(SERVER != null)
			SERVER = SERVER.trim();

		return SERVER;
	}
	public String getHostName() {
		String HOST = props.getProperty("HOST_NAME");


		if(HOST != null)
			HOST = HOST.trim();

		return HOST;
	}

	public String getPortNumber() {
		String PORT = props.getProperty("SECURED_PORT_NUMBER");

		if(PORT != null)
			PORT = PORT.trim();

		return PORT;
	}

	public String getUnsecuredPortNumber() {
		String UNSECURED_PORT_NO = props.getProperty("UNSECURED_PORT_NO");

		if(UNSECURED_PORT_NO != null)
			UNSECURED_PORT_NO = UNSECURED_PORT_NO.trim();

		return UNSECURED_PORT_NO;
	}

	public String getImagesContext() {

		String IMAGES_CONTEXT_ROOT = props.getProperty("IMAGE_CONTEXT");
		if (IMAGES_CONTEXT_ROOT != null)
			IMAGES_CONTEXT_ROOT = IMAGES_CONTEXT_ROOT.trim();

		return IMAGES_CONTEXT_ROOT;
	}

	public boolean getSecureProtocolContext(){
		String SECURE_PROTOCOL_CONTEXT = props.getProperty("SECURE_PROTOCOL");
		if(SECURE_PROTOCOL_CONTEXT != null && SECURE_PROTOCOL_CONTEXT.equals("YES"))
			return true;
		return false;
	}

	public String[] getDeliveryLocations() {
		//We will use the deployment level properties for initial loading.
		//Second time we will not 
		String DELIVERY_LOCATIONS = props.getProperty("DELIVERY_LOCATIONS");
		String[] locations = null;
		if(DELIVERY_LOCATIONS != null) {
			locations = DELIVERY_LOCATIONS.split(",");
		}
		return locations;
	}

	public String getXmppdomain() {

		String XMPP_DOMAIN = props.getProperty("XMPP_HOST");

		if (XMPP_DOMAIN != null)
			XMPP_DOMAIN = XMPP_DOMAIN.trim();
		else
			return null;

		if (XMPP_DOMAIN.isEmpty())
			return null;

		return XMPP_DOMAIN;

	}

	public int getXmppport() {

		String XMPP_PORT = props.getProperty("XMPP_PORT");

		if (XMPP_PORT != null)
			XMPP_PORT = XMPP_PORT.trim();
		else
			return 0;

		if (XMPP_PORT.isEmpty())
			return 0;

		return Integer.parseInt(XMPP_PORT);

	}

	public String getXmppservice() {

		String XMPP_SERVICE = props.getProperty("XMPP_SERVICE");

		if (XMPP_SERVICE != null)
			XMPP_SERVICE = XMPP_SERVICE.trim();
		else
			return null;

		if (XMPP_SERVICE.isEmpty())
			return null;

		return XMPP_SERVICE;

	}

	public String getAndroidVersion() {

		String ANDROID_VERSION = props.getProperty("ANDROID_VERSION");

		if (ANDROID_VERSION != null)
			ANDROID_VERSION = ANDROID_VERSION.trim();
		else
			return null;

		if (ANDROID_VERSION.isEmpty())
			return null;

		return ANDROID_VERSION;

	}

	public String getIOSVersion() {

		String IOS_VERSION = props.getProperty("IOS_VERSION");

		if (IOS_VERSION != null)
			IOS_VERSION = IOS_VERSION.trim();
		else
			return null;

		if (IOS_VERSION.isEmpty())
			return null;

		return IOS_VERSION;

	}

	public String getAndroidStoreVersion() {

		String STORE_MONITOR_VERSION = props.getProperty("STORE_MONITOR_VERSION");

		if (STORE_MONITOR_VERSION != null)
			STORE_MONITOR_VERSION = STORE_MONITOR_VERSION.trim();
		else
			return null;

		if (STORE_MONITOR_VERSION.isEmpty())
			return null;

		return STORE_MONITOR_VERSION;
	}

	public String getVersionNumber(){

		String VERSION_NUMBER = props.getProperty("VERSION_NO");
		if(VERSION_NUMBER != null && !VERSION_NUMBER.isEmpty())
			return VERSION_NUMBER;
		else
			return null;
	}

	public String getForcedMessage() {
		String FORCED_MESSAGE = props.getProperty("FORCED_UPDATE_MESSAGE");
		if(FORCED_MESSAGE != null && !FORCED_MESSAGE.isEmpty())
			return FORCED_MESSAGE;
		else
			return "Please update to latest version";
	}

	public String getRecommendedMessage() {
		String RECOMMENDED_UPDATE_MESSAGE = props.getProperty("RECOMMENDED_UPDATE_MESSAGE");
		if(RECOMMENDED_UPDATE_MESSAGE != null && !RECOMMENDED_UPDATE_MESSAGE.isEmpty())
			return RECOMMENDED_UPDATE_MESSAGE;
		else
			return "Latest version is available for update";
	}

	private String getProperty( String propertyName) {
		String propertyValue = props.getProperty(propertyName);
		if (propertyValue != null && (! propertyValue.isEmpty() ) ) {
			return propertyValue;
		}
		return null;
	}

	private int getIntProperty ( String propertyName ) {
		String propertyValue = this.getProperty(propertyName);
		return Integer.parseInt(propertyValue);
	}

	/**
	 * Default value of Number of people which the vendor can accommodate at a time.
	 * Until the number of people across appointments is less than this value, new dine-in requests are auto accepted.
	 * If properties.config doesn't have valid value , value 10 is returned.
	 * @return default value across stores specified in properties.config file, 10 if value is is not specified correctly.
	 */
	public int getAutoAcceptReservationLimit() {
		try {
			return this.getIntProperty("AUTOACCEPT_RESERVATION_LIMIT");
		} catch ( NumberFormatException ne ) {
			return 10;
		}
	}

	/**
	 * Default Average time duration per dine-in request in <b>minutes</b> i.e. the time duration for which the users will stay once they come in.
	 * If properties.config doesn't have valid value , value 60 is returned.
	 * @return default value across stores specified in properties.config file, 60 if value is is not specified correctly.
	 */
	public int getReservationDuration() {
		try {
			return this.getIntProperty("RESERVATION_DURATION_PER_CUSTOMER");
		} catch ( NumberFormatException ne ) {
			return 60;
		}
	}

	/**
	 * Time Duration for successive book slots <b>minutes</b> e.g. if the value is 15 , the successive intervals will be 12:00, 12:15, 12:30, 13:00 ...
	 * If properties.config doesn't have valid value , value 60 is returned.
	 * @return default value across stores specified in properties.config file, 60 if value is is not specified correctly.
	 */
	public int getReservationSlotInterval() {
		try {
			return this.getIntProperty("RESERVATION_SLOT_INTERVAL");
		} catch ( NumberFormatException ne ) {
			return 60;
		}
	}


	private boolean getBooleanProperty ( String propertyName ) throws Exception {
		String propertyValue = this.getProperty(propertyName);
		if ( propertyValue == null ) {
			throw new Exception( propertyName +" was not found in the Project Configurations") ;
		} else {
			if ( propertyValue.equalsIgnoreCase("yes") || propertyValue.equalsIgnoreCase("true") ) {
				return true;
			} else {
				return false;
			}
		}
	}

	public String getNotificationBroadcastGroup() {
		String NOTIFICATION_BROADCAST_GROUP = props.getProperty("NOTIFICATION_BROADCAST_GROUP");
		if(NOTIFICATION_BROADCAST_GROUP != null && !NOTIFICATION_BROADCAST_GROUP.isEmpty())
			return NOTIFICATION_BROADCAST_GROUP;
		else
			return "No broadcast group specified for the customers of this restaurant";
        }

	public String getAllVendorsBroadcastGroup() {
		String VENDOR_BROADCAST_GROUP = props.getProperty("VENDOR_BROADCAST_GROUP");
		if(VENDOR_BROADCAST_GROUP != null && !VENDOR_BROADCAST_GROUP.isEmpty())
			return VENDOR_BROADCAST_GROUP;
		else
			return "No broadcast group is available for the vendors";
        }

	public String getSENDSMSProperty() {
		String SEND_SMS = props.getProperty("SEND_SMS");
		if(SEND_SMS != null && !SEND_SMS .isEmpty())
			return SEND_SMS;
		else
			return "YES";
	}

	public String getVendorDisplayName(){
		String VENDOR_DISPLAY_NAME = props.getProperty("VENDOR_DISPLAY_NAME");
		if(VENDOR_DISPLAY_NAME != null && !VENDOR_DISPLAY_NAME.isEmpty())
			return VENDOR_DISPLAY_NAME;
		else
			return "";
	}

	public String getApprUrl() {
		String VENDOR_DISPLAY_NAME = props.getProperty("APPR_URL");
		if(VENDOR_DISPLAY_NAME != null && !VENDOR_DISPLAY_NAME.isEmpty())
			return VENDOR_DISPLAY_NAME;
		else
			return "";
	}
	
	public int getOtpExpiryTime(){
		String otp_expiry=props.getProperty("OTP_EXPIRY_TIME");
		if(otp_expiry != null && !otp_expiry.isEmpty())
			return Integer.parseInt(otp_expiry);
		else
			return 5;  //we are setting the default expiry time for otp is 5 mins
	}
	
	public int getOtpMaxResend(){
		String otp_count=props.getProperty("OTP_MAX_RESEND_COUNT");
		if(otp_count != null && !otp_count.isEmpty())
			return Integer.parseInt(otp_count);
		else
			return 5;
	}
	
	public int getOtpMaxRetry(){
		String otp_retry=props.getProperty("OTP_MAX_RETRY_ATTEMPTS");
		if(otp_retry != null && !otp_retry.isEmpty())
			return Integer.parseInt(otp_retry);
		else
			return 3;
	}
	
	public String getOtpTemplate(){
		String otpTemplate=props.getProperty("CUSTOMER_OTP_TEMPLATE_NAME");
		if(otpTemplate != null && !otpTemplate.isEmpty())
			return otpTemplate;
		else
			return "";
	}
	
	public String getOtpSenderId(){
		String otpSenderId=props.getProperty("OTP_TEMPLATE_SENDER_ID");
		if(otpSenderId != null && !otpSenderId.isEmpty())
			return otpSenderId;
		else
			return "";
	}
	
	public String getDataRepoPath(){
		String dataRepoPath=props.getProperty("DATA_REPOSITORY_PATH");
		if(dataRepoPath != null && !dataRepoPath.isEmpty())
			return dataRepoPath.trim();
		else
			return "";
	}
	
	public String getSecureImagePath(){
		String secureImgPath=props.getProperty("SECURE_IMAGE_FOLDER_PATH");
		if(secureImgPath != null && !secureImgPath.isEmpty())
			return secureImgPath.trim();
		else
			return "";
	}
	
	public String getUnsecureImagePath(){
		String unSecureImgPath=props.getProperty("UNSECURE_IMAGE_FOLDER_PATH");
		if(unSecureImgPath != null && !unSecureImgPath.isEmpty())
			return unSecureImgPath.trim();
		else
			return "";
	}

	public String getCurrenyCode() {
		String currencyCode = props.getProperty("CURRENCY_CODE");
		if(currencyCode != null && !currencyCode.trim().isEmpty())
			return currencyCode;
		return null;
	}
	
	public String getPaymentLinkCurrenySymbol() {
		String currencyCode = props.getProperty("PAYMENT_LINK_CUURENCY_SYMBOL");
		if(currencyCode != null && !currencyCode.trim().isEmpty())
			return currencyCode;
		return null;
	}
	
	/**
	 * Whether to send OTP while registration and password reset scenarios
	 * 
	 * @return OTPFeatureToggles
	 */
	public OTPFeatureToggles getOTPToggle(){
		String enable_otp = props.getProperty("ENABLE_OTP");
		if(enable_otp != null && enable_otp.equals("YES"))
			return OTPFeatureToggles.YES;
		else if(enable_otp != null && enable_otp.equals("ACCEPT_ANY_OTP"))
			return OTPFeatureToggles.ACCEPT_ANY_OTP;
		else
			return OTPFeatureToggles.WITHOUT_OTP;
	}
	public String[] getEmail() {
		String email1 = props.getProperty("EMAIL_ID");
		if((email1 != null && !email1.trim().isEmpty())){
			return email1.split(",");
		}
		return null;
	}
	
	/**
	 * Returns different login type scenarios
	 * 
	 * @return OTPFeatureToggles
	 */
	public java.util.List<LoginType> getLoginTypes(){
		String login_type = props.getProperty("LOGIN_TYPE");
		
		String[] login_types = login_type.split(",");
		
		java.util.List<LoginType> login_list = new ArrayList<>();
		for (String type : login_types) {

			if (type != null && type.equals("WITHOUT_PASSWORD_VERIFY_OTP_AT_LOGIN") 
					&& !login_list.contains(LoginType.WITHOUT_DETAILS_VERIFY_OTP_AT_LOGIN))
				login_list.add(LoginType.WITHOUT_PASSWORD_VERIFY_OTP_AT_LOGIN);
			else if (type != null && type.equals("WITHOUT_PASSWORD_VERIFY_OTP_AT_PAYMENT")
					&& !login_list.contains(LoginType.WITHOUT_PASSWORD_VERIFY_OTP_AT_PAYMENT))
				login_list.add(LoginType.WITHOUT_PASSWORD_VERIFY_OTP_AT_PAYMENT);
			else if (type != null && type.equals("WITHOUT_DETAILS_VERIFY_OTP_AT_LOGIN")
					&& !login_list.contains(LoginType.WITHOUT_DETAILS_VERIFY_OTP_AT_LOGIN))
				login_list.add(LoginType.WITHOUT_DETAILS_VERIFY_OTP_AT_LOGIN);
			else if (type != null && type.equals("WITHOUT_DETAILS_VERIFY_OTP_AT_PAYMENT")
					&& !login_list.contains(LoginType.WITHOUT_DETAILS_VERIFY_OTP_AT_PAYMENT))
				login_list.add(LoginType.WITHOUT_DETAILS_VERIFY_OTP_AT_PAYMENT);
			else if (!login_list.contains(LoginType.WITH_PASSWORD))
				login_list.add(LoginType.WITH_PASSWORD);
		}
		
		return login_list;
	}	
	
	public static enum OTPFeatureToggles {
		// Send OTP
		YES, 
		// Accept any OTP coming from client
		ACCEPT_ANY_OTP, 
		// Without OTP verification client can go through subsequent steps.
		WITHOUT_OTP
	}

	/**
	 * Different possible types of logins that can be supported by the system.
	 */
	public static enum LoginType {
		
		// Verify mobile number and register with password and use the password from next login
		WITH_PASSWORD,
		// Verify mobile number with OTP and get other details after verification at registration
		// Login via OTP only, no details would be asked if coming for the second time after logout
		WITHOUT_PASSWORD_VERIFY_OTP_AT_LOGIN,
		// Register a mobile number without verification at registration and ask full name and email at registration
		// Login next time just by entering mobile number. The given mobile number will be verified at Payment.
		WITHOUT_PASSWORD_VERIFY_OTP_AT_PAYMENT, 
		// Register a mobile number by verifying via OTP.
		// Same for login and register
		WITHOUT_DETAILS_VERIFY_OTP_AT_LOGIN, 
		// Register a mobile number and verify the mobile number at payment
		WITHOUT_DETAILS_VERIFY_OTP_AT_PAYMENT,
		// Register using social login types
		SOCIAL_LOGIN
	}


	public String getDuration() {
		String duration = props.getProperty("DURATION");
		if((duration != null && !duration.trim().isEmpty())){
			return duration.trim();
		}
		return null;
	}
	public Map getTriggerTime() {
		Object[] arr = new Object[6];
		String triggerTime = props.getProperty("TRIGGER_TIME");
		if((triggerTime != null && !triggerTime.trim().isEmpty())){
			Map<String, Object> map = new HashMap<String, Object>();
			Map<String, Object> map2 = new HashMap<String, Object>();
			String[] str = { "sec", "min", "hour", "date", "month", "day" };
			String[] a = triggerTime.split(" ");
			int j = 0;
			for (String i : a) {
				String[] index = i.split("-");
				if (index.length > 0) {
					map.put(str[j++], index);
				} else {
					map.put(str[j++], i);
				}
			}
			Set keys = map.keySet();
			String[] date = new String[2];
			String[] hour = new String[2];
			String[] day = new String[2];
			for (Object i : keys) {
				String[] arr2 = (String[]) map.get(i);
				if (arr.length > 1) {
					String key1= i + "From";
					map2.put(i + "From", arr2[0]);
					System.out.println(i + "From" + " --> " + arr[0]);
					System.out.println(i + "To" + " --> " + arr[1]);
				} else {
					System.out.println(i + " --> " + arr[0]);
				}
			}
		}
		return null;
	}
	public int getDecimalPlaces(){
		String value = props.getProperty("DECIMAL_PLACES");
		if(value != null){
			return Integer.parseInt(value);
		}
		return 0;
	}
	
	public int getRoundingMode(){
		String value = props.getProperty("ROUNDING_STRATEGY");
		if(value != null && value.equals("FLOOR"))
			return BigDecimal.ROUND_FLOOR;
		else if (value != null && value.equals("CIELING"))
			return BigDecimal.ROUND_CEILING;
		else if (value != null && value.equals("HALF_EVEN"))
			return BigDecimal.ROUND_HALF_EVEN;
		else
			return -1;
		
	}
	
	public String getApiKey(){
		String API_KEY = props.getProperty("API_KEY");
		if(API_KEY != null && !API_KEY.isEmpty())
			return API_KEY;
		else
			return "";
	}
	
		public boolean isAutoDeliveryEnabled(){
		String deliveryMode = props.getProperty("DELIVERY_MODE");
		if(deliveryMode != null && !deliveryMode.isEmpty() && deliveryMode.equals("AUTO"))
			return true;
		return false;
	}
	
}
