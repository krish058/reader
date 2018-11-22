package com.reader.dao;

import com.reader.dto.ImageUpload;
import com.reader.dto.TenderCodes;
import com.reader.dto.User;

public interface ImageUploadDao {

	public boolean insertintoDb(ImageUpload doc);
	
	public void getDataByAccountId(String accountId);

	TenderCodes getvaluebytendercode(String tendercode);

	ImageUpload gettenderbyid(String userId);
	
}
