package com.zetagile.news.resource;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.zetagile.news.resource.util.WebResponse;

public interface ImageUploadResources {

ResponseEntity<WebResponse> uploadimage(boolean webapp, MultipartFile sourceFile, String user_id,
		HttpServletRequest request);
}
