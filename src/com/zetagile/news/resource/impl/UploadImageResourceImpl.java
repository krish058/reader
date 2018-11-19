package com.zetagile.news.resource.impl;


import java.io.IOException;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zetagile.news.services.ImageUploadServices;
import com.zetagile.news.services.impl.ImageUploadServicesImpl;
import com.zetagile.news.services.util.ImageService;
import com.zetagile.news.resource.ImageUploadResources;
import com.zetagile.news.resource.util.ClientVersion;
import com.zetagile.news.resource.util.WebResponse;

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
				response.setMessage(url);
				response.setStatus(true);
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
					ImageUploadServicesImpl imageUploadServices = new ImageUploadServicesImpl();
					imageUploadServices.documentParse(SourceFileString);
				}else {
					response.setMessage("There is Empty in Documnet");
					return ResponseEntity.ok().body(response);
				}
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
