package com.reader.util;

import java.io.IOException;

import java.util.Properties;


public class ProjectConfig {

	Properties props;

	public ProjectConfig() {
		props = new Properties();
		try {
			props.load(this.getClass().getClassLoader().getResourceAsStream("project.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	
	public String getDataRepoPath(){
		String dataRepoPath=props.getProperty("DATA_REPOSITORY_PATH");
		if(dataRepoPath != null && !dataRepoPath.isEmpty())
			return dataRepoPath.trim();
		else
			return "";
	}
}