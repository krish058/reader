package com.pdf.reader.services;

import com.fasterxml.jackson.databind.node.ObjectNode;


public interface MessageServices {	
		
		public ObjectNode getFullNews();
		boolean deleteTM(String message_Id);
		boolean updateNews(String messageId, String messageTitle, String messageDesc, String messageUrl);
		boolean saveTM(String messageTitle, String messageDesc, String messageUrl, String userid);
	
	

}
