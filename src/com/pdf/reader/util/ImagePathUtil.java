package com.pdf.reader.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

//import com.zetagile.cart.resource.impl.ImageUploadResourcesImpl;


public class ImagePathUtil {
	public static String getImagePath(String imageurl){
		
		final Logger logger = Logger.getLogger(ImagePathUtil.class.getName());

		Pattern pattern;
		Matcher matcher;
		pattern = Pattern.compile("(?:http)([s]?)(:)(//)([a-zA-Z0-9.]+)(:?)(([0-9]+)?)(/)(([a-zA-Z]+)?)(/)(([a-zA-Z0-9]+)?)");
		//pattern = Pattern.compile("(?:http)(:)(//)([a-zA-Z0-9]+)(:)([0-9]+)");
		matcher = pattern.matcher(imageurl);
		String image_url = matcher.replaceAll("");
		logger.info("Final url path to store in db is " + image_url + matcher);
		return image_url;
	}
}
