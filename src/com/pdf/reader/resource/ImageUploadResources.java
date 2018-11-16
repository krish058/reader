package com.pdf.reader.resource;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.pdf.reader.resource.util.WebResponse;

public interface ImageUploadResources {
public ResponseEntity<WebResponse> uploadimage(boolean webapp, MultipartFile sourceFile, HttpServletRequest request);
}
