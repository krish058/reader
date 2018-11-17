package com.zetagile.init;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.zetagile.news.util.ProjectConfig;



@WebListener
public class Initializer implements ServletContextListener {
	

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {

			ProjectConfig config = new ProjectConfig();
			
			//this is the main path to store the images (/root/images)
			String products_folder_path = config.getDataRepoPath();
			//this is the server folder name(ecart)
			String server_folder = config.getServerName();
			//this is the final path (/root/images/ecart)
			String images_folder_path =  products_folder_path + File.separator + server_folder;
			
			
			
			
			//checking whether the path /root/images is created or not
			if (!new File(products_folder_path).exists()) {
				new File(products_folder_path).mkdir();
			}
			
			//checking for the existence of the server folder
			if (!new File(images_folder_path).exists()) {
				new File(images_folder_path).mkdir();
			}			
			
			
			
			/**
			 * http://stackoverflow.com/questions/17656046/autowired-in-servletcontextlistener
			 * 
			 * http://stackoverflow.com/questions/4746041/spring-injecting-a-dependency-into-a-servletcontextlistener
			 */
			SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
//			UserServices userServices = new UserServicesImpl();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

//		System.gc();
//		try {
//			((HikariDataSource) dataSource).close();
//			AbandonedConnectionCleanupThread.shutdown();
//		} catch (InterruptedException e) {
//			 logger.warning("SEVERE problem cleaning up: " + e.getMessage());
//			e.printStackTrace();
//		}
	}

}