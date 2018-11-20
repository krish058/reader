package com.zetagile.news.dao;

import com.zetagile.news.dto.TenderCodes;

public interface TenderCodeDao {

	public TenderCodes getDataAccordingByTenderId(String tenderCode);
}
