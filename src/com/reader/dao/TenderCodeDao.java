package com.reader.dao;

import com.reader.dto.TenderCodes;

public interface TenderCodeDao {

	public TenderCodes getDataAccordingByTenderId(String tenderCode);
}
