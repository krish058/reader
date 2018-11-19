package com.zetagile.news.dao.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import com.zetagile.news.dao.AbstractDao;
import com.zetagile.news.dao.ImageUploadDao;
import com.zetagile.news.dto.ImageUpload;
import com.zetagile.news.util.Utils;

public class ImageUploadDaoImpl extends AbstractDao implements ImageUploadDao{
	
	private Logger logger = Logger.getLogger(ImageUploadDaoImpl.class);
	
	public Boolean insertintoDb(ImageUpload doc){
		logger.info("inserting into db");
		logger.info("TenderCode is (" + Utils.getNotNullString(doc.getTenderCode()) + ")");
		//TODO Connect DB
		//insert data to Db
		try {
			if (doc.getUserId() == null){
				logger.info("No AccountId Updated"+doc.getUserId());
				doc.setUserId(UserDaoImpl.getUserIdgen());
				getSession().saveOrUpdate(doc);
			}
			else if(doc.getUserId() != null){
				getSession().saveOrUpdate(doc);
			}
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void getDataByAccountId(String accountId){
		logger.info("getDataByAccountId(" + Utils.getNotNullString(accountId) + ")");
		//TODO Connect DB
		//Retrieve data from accountID
	}
	
	private static long previousIDTime = 0;
	private static long separator = 0;
	
	private synchronized static String getUserIdgen() {

		long currentTime = new Date().getTime();

		if (currentTime == previousIDTime)
			separator++;
		else
			separator = 0;

		previousIDTime = currentTime;

		DateFormat dFormat = new SimpleDateFormat("yyyyMMwwddhhmmssSSS");

		return "USER" + dFormat.format(new Date(currentTime)) + "" + separator;
	}

}
