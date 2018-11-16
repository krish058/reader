package com.pdf.reader.resource.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pdf.reader.services.util.ImageService;
import com.pdf.reader.resource.ImageUploadResources;
import com.pdf.reader.resource.util.ClientVersion;

import com.pdf.reader.resource.util.WebResponse;

@RestController
@RequestMapping("/image")
public class UploadImageResourceImpl implements ImageUploadResources {

	private Logger logger = Logger.getLogger(UploadImageResourceImpl.class.getName());

	@RequestMapping(path = "/uploadimage/", method = RequestMethod.POST )
	@CrossOrigin(origins = "*")
	@Override
	public ResponseEntity<WebResponse> uploadimage(

			// @RequestHeader("sessionid") String sessionid,
			@RequestHeader(value = "webapp", defaultValue = "false") boolean webapp,			
			@RequestBody MultipartFile sourceFile,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		logger.info("POST /imagersrc/uploadimage/");
		 	 	 	
		WebResponse response = new WebResponse();
		WebResponse Wresponse = new WebResponse();
		ClientVersion version = new ClientVersion();
		WebResponse wResponse = version.isAdmin(webapp);
		if (wResponse == null) {
			try {
				ImageService service = new ImageService(request);
				//logger.info("service.saveImage" + folder);
				String url = service.saveImage(sourceFile.getInputStream(), sourceFile.getOriginalFilename());
				response.setMessage(url);
				response.setStatus(true);
				
				logger.info(" Final URL is=" + url);
			} catch (IOException e) {
				logger.error("caught exception " + e.getMessage() + " in uploadimage()");
				response.setMessage(e.getMessage());
				response.setStatus(false);
			}

		} else {
			response.setMessage("Bad request! Invalid parameters!!!");
			response.setStatus(false);
			logger.info("Bad request! Invalid parameters!!!");
		}

		return ResponseEntity.ok().body(response);
	}

}
