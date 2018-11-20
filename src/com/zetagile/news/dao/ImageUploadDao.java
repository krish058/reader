package com.zetagile.news.dao;

import com.zetagile.news.dto.ImageUpload;

public interface ImageUploadDao {

	public boolean insertintoDb(ImageUpload doc);
	
	public void getDataByAccountId(String accountId);
	
}
