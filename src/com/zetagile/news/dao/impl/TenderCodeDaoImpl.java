package com.zetagile.news.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import com.zetagile.news.dao.AbstractDao;
import com.zetagile.news.dao.TenderCodeDao;
import com.zetagile.news.dto.TenderCodes;
import com.zetagile.news.util.Utils;

public class TenderCodeDaoImpl extends AbstractDao implements TenderCodeDao{

	private Logger logger = Logger.getLogger(TenderCodeDaoImpl.class);
	@Override
	public TenderCodes getDataAccordingByTenderId(String tenderCode) {
		TenderCodes codes = new TenderCodes();
		try {
			if (tenderCode != null)
				codes = (TenderCodes) getSession().get(TenderCodes.class, tenderCode);
			if (codes != null)
				return codes;
			else {
				if (logger.isDebugEnabled())
					logger.debug("User Doesn't exist with Id: " + tenderCode);
				return null;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			logger.error("caught HibernateException " + e + "with user Id: " + Utils.getNotNullString(tenderCode));
			return null;
		}
	}

}
