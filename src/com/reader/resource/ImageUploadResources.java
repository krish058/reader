package com.reader.resource;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.reader.resource.util.WebResponse;

public interface ImageUploadResources {

ResponseEntity<WebResponse> uploadimage(boolean webapp, MultipartFile sourceFile, String user_id,
		HttpServletRequest request);

ResponseEntity<WebResponse> getAllDetails(String user_id);
}
