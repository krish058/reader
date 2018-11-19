package com.zetagile.news.dao;

import com.zetagile.news.dto.ImageUpload;

public interface ImageUploadDao {

	public Boolean insertintoDb(ImageUpload doc);
	
	public void getDataByAccountId(String accountId);
	
}
