package com.wd.listener;

import java.util.Enumeration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.wd.config.AppConfig;

public class AppListener implements ServletContextListener {

	public AppListener() {
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			AppConfig config = AppConfig.getInstance();
			Enumeration<String> parameters = arg0.getServletContext().getInitParameterNames();
			while (parameters.hasMoreElements()) {
				String name = (String) parameters.nextElement();
				config.setParameter(name, arg0.getServletContext().getInitParameter(name));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println();
		}
	}

}
