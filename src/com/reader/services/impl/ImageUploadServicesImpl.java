package com.reader.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.reader.dao.ImageUploadDao;
import com.reader.dto.ImageUpload;
import com.reader.dto.TenderCodes;
import com.reader.dto.User;
import com.reader.services.ImageUploadServices;


@Service
@Transactional

public class ImageUploadServicesImpl implements ImageUploadServices {
	
	@Autowired
	ImageUploadDao imageUploadDao;

	@Override
	public boolean documentParse(String doc,String userId) {
		try{
			Map<String, String> imageDocs = new HashMap<String, String>();
			String[] splited = doc.split(" ");
			for(int i = 0; i < splited.length; i++){
				if(splited[i].contains(":")){
					String s = "";
					int x = i;
					for(int y = 0; y < splited.length-x; y++){
						if(splited[y+x].contains(".")){
							for(int k = x+1; k <= y+x ;k++){
								s = s+splited[k]+" ";
							}
//							s = s+splited[y+x-1]+" "+splited[y+x];
							break;
						}
					}
					s = s.replaceAll("[^a-zA-Z0-9 ]","");
					imageDocs.put(splited[i].replaceAll("[^a-zA-Z0-9]",""), s.substring(0, s.length()-1));
				}
			}
			ImageUpload imageUpload = new ImageUpload();
			imageUpload.setUserId(userId);
			imageUpload.setName(imageDocs.get("Name"));
			imageUpload.setAddress((imageDocs.get("Address")));
			if(imageDocs.get("TenderCode") == null || StringUtils.isNumeric(new StringBuilder(Integer.parseInt(imageDocs.get("TenderCode"))))){
				imageDocs.put("TenderCode", "123");
			}
			imageUpload.setTenderCode(imageDocs.get("TenderCode"));			
			return imageUploadDao.insertintoDb(imageUpload);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	@Override
	public ObjectNode gettendercode(String userId){
		
TenderCodes codes=new TenderCodes();
ImageUpload imageupload = new ImageUpload();
imageupload =imageUploadDao.gettenderbyid(userId);
String tendercode= imageupload.getTenderCode();
codes=imageUploadDao.getvaluebytendercode(tendercode);
String tenderprice=codes.getPrice();

try {
	ObjectNode objectNode = (ObjectNode) new ObjectMapper().readTree(imageupload.toString());
	objectNode.put("price",tenderprice );
	return objectNode;
} catch (Exception e) {
	throw new RuntimeException("json convertion failed", e);
}


		
		
	}
	
	
	
}
