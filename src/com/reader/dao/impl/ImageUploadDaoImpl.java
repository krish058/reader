package com.reader.dao.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.reader.dao.AbstractDao;
import com.reader.dao.ImageUploadDao;
import com.reader.dto.ImageUpload;
import com.reader.dto.TenderCodes;
import com.reader.dto.User;
import com.reader.util.Utils;

public class ImageUploadDaoImpl extends AbstractDao implements ImageUploadDao{
	
	private Logger logger = Logger.getLogger(ImageUploadDaoImpl.class);
	
	@Override
	public boolean insertintoDb(ImageUpload doc){
		logger.info("inserting into db");
	//	logger.info("TenderCode is (" + Utils.getNotNullString(doc.getTenderCode()) + ")");
		//TODO Connect DB
		//insert data to Db
				
			//logger.info("Saving data in doc_details "+doc);
			getSession().saveOrUpdate(doc);	
			return true;
		
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
	
	
	@Override
	public ImageUpload gettenderbyid(String userId) {
		Criteria cr = getSession().createCriteria(ImageUpload.class);
		
		
			cr.add(Restrictions.eq("UserAccountId",userId));
		
		if(cr.list().size() > 0)
			return (ImageUpload) cr.list().get(0);

		return null;
	}
	
	@Override
	public TenderCodes getvaluebytendercode(String tendercode) {
		Criteria cr = getSession().createCriteria(TenderCodes.class);
		
		
			cr.add(Restrictions.eq("UserAccountId",tendercode));
		
		if(cr.list().size() > 0)
			return (TenderCodes) cr.list().get(0);

		return null;
	}

	
	
	

}
