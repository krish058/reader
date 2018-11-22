package com.reader.dao.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.reader.dao.AbstractDao;
import com.reader.dao.UserDao;
import com.reader.dto.User;
import com.reader.util.Utils;

@Repository
public class UserDaoImpl extends AbstractDao implements UserDao {

	private Logger logger = Logger.getLogger(UserDaoImpl.class.getName());

	@Override
	public boolean isUserEmailava(String email_id) {
		Criteria cr = getSession().createCriteria(User.class);
		cr.add(Restrictions.eq("emailId", email_id));
		if (cr.list().size() > 0)
			return true;
		return false;

	}

	@Override
	public boolean isUserMobileNoava(String mobno) {
		Criteria cr = getSession().createCriteria(User.class);
		cr.add(Restrictions.eq("mobileNo", mobno));
		if (cr.list().size() > 0)
			return true;
		return false;

	}

	@Override
	public boolean newUserregister(User nu) {
		try {
			if (nu.getUserId() == null)
				nu.setUserId(getUserIdgen());
			getSession().saveOrUpdate(nu);
			logger.info("saved");
			return true;
		}

		catch (HibernateException e) {
			e.printStackTrace();
			logger.error("error in savenews" + e);
			return false;
		}
	}

	private static long previousIDTime = 0;
	private static long separator = 0;

	public synchronized static String getUserIdgen() {

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
	public User getUser(String user_id) {
		logger.info("getUser(" + Utils.getNotNullString(user_id) + ")");
		User user = null;
		try {
			if (user_id != null)
				user = (User) getSession().get(User.class, user_id);
			if (user != null)
				return user;
			else {
				if (logger.isDebugEnabled())
					logger.debug("User Doesn't exist with Id: " + user_id);
				return null;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			logger.error("caught HibernateException " + e + "with user Id: " + Utils.getNotNullString(user_id));
			return null;
		}
	}

	
	@Override
	public User getidbyemail(String email) {

		logger.info("getUserOffers(" + Utils.getNotNullString(email) + ")");

		Criteria cr = getSession().createCriteria(User.class);
		cr.add(Restrictions.eq("emailId", email));

		if (cr.list() != null && cr.list().size() > 0){
			
			return (User) cr.list().get(0);
		}
		else
			return null;
	}
	@Override
	public User getkeybyid(String userId){
		logger.info("getUserOffers(" + Utils.getNotNullString(userId) + ")");
		
		Criteria cr = getSession().createCriteria(User.class);
		cr.add(Restrictions.eq("userId", userId));		
		if (cr.list() != null && cr.list().size() > 0)
			return (User) cr.list().get(4);
		
		
		return null;
		
	}
	
	@Override
	public boolean updatelogintime(User user) {
		user.setTimeStamp(new Date());
		getSession().saveOrUpdate(user);
		return true;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getallUserdetails() {
		try {
			Criteria cr = getSession().createCriteria(User.class);

			if (cr.list() != null && cr.list().size() > 0)
				return cr.list();
			else
				return null;
		} catch (HibernateException e) {
			e.printStackTrace();

			logger.error("Caught HibernateException in getallUserdetails() " + e);
			return null;
		} finally {

		}

	}
	

}
