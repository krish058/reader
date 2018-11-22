package com.reader.services.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.reader.util.ProjectConfig;

public class ImageService {

	private Logger logger = Logger.getLogger(ImageService.class.getName());

	String ROOT_DIRECTORY;
	String SERVER_NAME;

	ProjectConfig config = new ProjectConfig();

	public ImageService(HttpServletRequest request) {
		try {
			ROOT_DIRECTORY = config.getDataRepoPath();
			SERVER_NAME = "reader";
			logger.info("ROOT_DIRECTORY	 =" + ROOT_DIRECTORY + " SERVER_NAME=" + SERVER_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getImageRootDirectory() {
		return ROOT_DIRECTORY;
	}

	public String saveImage(InputStream in, String fileName) {
		// logger.info("Input Parameters, folder=" + folder);
		int n;
		String image_url;

		int filecount = new File(ROOT_DIRECTORY + File.separator + SERVER_NAME + File.separator).listFiles().length;
		String filePath = ROOT_DIRECTORY + File.separator + SERVER_NAME + File.separator + filecount
				+ getExtension(fileName);
		logger.info("Final Path=" + filePath);

		try {
			OutputStream out = new FileOutputStream(new File(filePath));
			while ((n = in.read()) != -1)
				out.write(n);
			out.close();
			logger.info("Image is stored successfully");
		} catch (IOException e) {
			logger.error("Unable to store the image" + e);
			e.printStackTrace();
		}
		return filePath;
	}

	private String getExtension(String file_name) {
		String extension = "";

		int i = file_name.lastIndexOf('.');
		if (i > 0) {
			extension = file_name.substring(i);
		}

		return extension;
	}

	// documation

}
