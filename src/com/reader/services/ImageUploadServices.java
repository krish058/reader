package com.reader.services;

import com.fasterxml.jackson.databind.node.ObjectNode;

public interface ImageUploadServices {

	boolean documentParse(String doc, String userId);

	ObjectNode gettendercode(String userID);
}
