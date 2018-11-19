package com.zetagile.news.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mysql.fabric.xmlrpc.base.Array;
import com.zetagile.news.dao.ImageUploadDao;
import com.zetagile.news.dto.ImageUpload;
import com.zetagile.news.services.ImageUploadServices;


@Service
@Transactional

public class ImageUploadServicesImpl implements ImageUploadServices {
	
	@Autowired
	ImageUploadDao imageUploadDao;

	@Override
	public Boolean documentParse(String doc) {
		try{
			ImageUpload imageUpload = new ImageUpload();
			Map<String, String> imageDocs = new HashMap<String, String>();
			String[] splited = doc.split(" ");
			for(int i = 0; i < splited.length; i++){
				if(splited[i].contains(":")){
					String s = null;
					int x = i;
					for(int y = 1; y < splited.length-x; y++){
						if(splited[y+x].contains(".")){
							s = s+splited[y+x];
							break;
						}
					}
					imageDocs.put(splited[i], s);
				}
				imageUpload.setName(imageDocs.get("Name"));
				imageUpload.setAddress((imageDocs.get("Address")));
				if(imageDocs.get("TenderCode") == null || StringUtils.isNumeric(new StringBuilder(Integer.parseInt(imageDocs.get("TenderCode"))))){
					imageDocs.put("TenderCode", "123");
				}
				imageUpload.setTenderCode(imageDocs.get("TenderCode"));
				imageUploadDao.insertintoDb(imageUpload);
			}
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	
	
}
