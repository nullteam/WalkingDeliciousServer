package com.wd.config;

import java.util.HashMap;

public class AppConfig  {

	private static AppConfig config=null;
	private HashMap<String, String> values = new HashMap<String, String>();
	
	public static AppConfig getInstance() {
		if(config==null) config = new AppConfig();
		return config;
	}
	public AppConfig() {
	}
	
	public HashMap<String, String> setParameter(String name,String value){
		values.put(name,value);
		return values;
	}
	
	public String getParameter(String name) {
		return values.get(name);
	}
	
}
