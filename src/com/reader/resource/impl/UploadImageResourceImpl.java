package com.reader.resource.impl;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.poi.POITextExtractor;
import org.apache.poi.extractor.ExtractorFactory;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.reader.dto.User;
import com.reader.resource.ImageUploadResources;
import com.reader.resource.util.WebResponse;
import com.reader.services.ImageUploadServices;
import com.reader.services.UserServices;
import com.reader.services.util.ImageService;

@RestController
@RequestMapping("/image")
public class UploadImageResourceImpl implements ImageUploadResources {
	
	
	@Autowired
	ImageUploadServices uploadServices;
		

	private Logger logger = Logger.getLogger(UploadImageResourceImpl.class.getName());

	@RequestMapping(path = "/uploadimage/{userid}", method = RequestMethod.POST )
	@CrossOrigin(origins = "*")
	@Override
	public ResponseEntity<WebResponse> uploadimage(

			// @RequestHeader("sessionid") String sessionid,
			@RequestHeader(value = "webapp", defaultValue = "false") boolean webapp,			
			@RequestBody MultipartFile sourceFile,@PathVariable(value = "userid") String user_id,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		logger.info("POST /imagersrc/uploadimage/");
		 	 	 	
		WebResponse response = new WebResponse();
		
			try {
				String parsedText;
				PDFParser parser = null;
			    PDDocument pdDoc = null;
			    COSDocument cosDoc = null;
			    PDFTextStripper pdfStripper;
			    
				ImageService service = new ImageService(request);
				//logger.info("service.saveImage" + folder);
				logger.info(sourceFile.getInputStream());
				logger.info(sourceFile.getOriginalFilename());
				String url = service.saveImage(sourceFile.getInputStream(), sourceFile.getOriginalFilename());
				//response.setMessage(url);
				logger.info(" Final URL is=" + url);
				POITextExtractor extractor;
				String SourceFileString = null;
				if (sourceFile.getOriginalFilename().endsWith(".pdf")) {
					try {
						parser = new PDFParser(sourceFile.getInputStream());
						parser.parse();
						cosDoc = parser.getDocument();
						pdfStripper = new PDFTextStripper();
						pdDoc = new PDDocument(cosDoc);
						parsedText = pdfStripper.getText(pdDoc);
						SourceFileString = parsedText;
						System.out.println(SourceFileString.replaceAll("[^A-Za-z0-9. :]", " "));
					} catch (Exception e) {
						e.printStackTrace();
						try {
							if (cosDoc != null)
								cosDoc.close();
							if (pdDoc != null)
								pdDoc.close();
						} catch (Exception e1) {
							e.printStackTrace();
						}
					}

				} else if(sourceFile.getOriginalFilename().endsWith(".docx")) {
					try {
						XWPFDocument doc = new XWPFDocument(sourceFile.getInputStream());
						extractor = new XWPFWordExtractor(doc);
						SourceFileString = extractor.getText();
						System.out.println(SourceFileString.replaceAll("[^A-Za-z0-9. :]", " "));
					} catch (Exception e) {
						e.printStackTrace();
					}

				} else if (sourceFile.getOriginalFilename().endsWith(".doc")) {
					try{
					POIFSFileSystem fileSystem = new POIFSFileSystem(sourceFile.getInputStream());
					extractor = ExtractorFactory.createExtractor(fileSystem);
					SourceFileString = extractor.getText();
					System.out.println(SourceFileString.replaceAll("[^A-Za-z0-9, :]", " "));
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
				if (SourceFileString != null) {
						if(uploadServices.documentParse(SourceFileString,user_id)){
						response.setMessage("uploaded successfully");
						response.setStatus(true);

					}
				}else {
					response.setMessage("There is Empty in Documnet");
					return ResponseEntity.ok().body(response);
				}
			} catch (IOException e) {
				logger.error("caught exception " + e.getMessage() + " in uploadimage()");
				response.setMessage(e.getMessage());
				response.setStatus(false);
			}

		

		return ResponseEntity.ok().body(response);
	}

	@RequestMapping(path = "/gettenderdetailsbyid/userid", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<WebResponse> getAllDetails(@PathVariable(value = "userid") String user_id) {
		WebResponse response = new WebResponse();

		try {
			if(user_id!=null&&!user_id.isEmpty()) {
				
	
						response.setMessage(uploadServices.gettendercode(user_id));
						response.setStatus(true);
			
			
			} else {
				response.setStatus(false);
				response.setMessage("no userID");
			}
			
		}catch(Exception e) {
			logger.error("caught Exception in GET GET /userrsrc/userdetMob/{mobile}: ", e);
			logger.error("Exception occured: ", e);
			response.setStatus(false);
			response.setMessage(e.getMessage());
			
		}	
		return ResponseEntity.ok().body(response);		
	}		

	}
	
	
	
	
	
	
	
	
	
	
	
	
	

