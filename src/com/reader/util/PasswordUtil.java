package com.reader.util;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordUtil {
	// The higher the number of iterations the more 
    // expensive computing the hash is for us and
    // also for an attacker.
	
    //private static final int iterations = 20*1000;
	
	
	
	
	 private static final Random RANDOM = new SecureRandom();
	    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	    private static final int ITERATIONS = 10000;
	    private static final int KEY_LENGTH = 256;
	    	//salt value is generated randomly
	     public static String getSalt(int length) {
	        StringBuilder returnValue = new StringBuilder(length);
	        for (int i = 0; i < length; i++) {
	            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
	        }
	        return new String(returnValue);
	    }
	     
	     
	     
	     //pass+salt=encoded
	    public static byte[] hash(char[] password, byte[] salt) {
	        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
	        Arrays.fill(password, Character.MIN_VALUE);
	        try {
	            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
	            return skf.generateSecret(spec).getEncoded();
	        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
	            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
	        } finally {
	            spec.clearPassword();
	        }
	    }
	    //a securePassword is generated
	    public static  String generateSecurePassword(String password, String salt) {
	        String returnValue = null;
	        byte[] securePassword = hash(password.toCharArray(), salt.getBytes());
	 
	        returnValue = Base64.getEncoder().encodeToString(securePassword);
	 
	        return returnValue;
	    }
	    // with pass and salt using 
	    public static  boolean verifyUserPassword(String providedPassword,
	            String securedPassword, String salt)
	    {
	        boolean returnValue = false;
	        
	        // Generate New secure password with the same salt
	        String newSecurePassword = generateSecurePassword(providedPassword, salt);
	        
	        // Check if two passwords are equal
	        returnValue = newSecurePassword.equalsIgnoreCase(securedPassword);
	        
	        return returnValue;
	    }
    

}