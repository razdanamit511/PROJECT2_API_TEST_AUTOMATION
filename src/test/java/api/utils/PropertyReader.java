package api.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertyReader {

	Properties prop;

	public PropertyReader() throws Exception {
		// TODO Auto-generated constructor stub

		FileInputStream fin = new FileInputStream("src\\test\\resources\\routes.properties");
		prop = new Properties();
		prop.load(fin);

	}

	public String getPostUrl() {
		return prop.getProperty("post_url");
	}

	public String getGetUrl() {
		return prop.getProperty("get_url");
	}
	
	public String getPutUrl() {
		return prop.getProperty("put_url");
	}
	
	public String getDeleteUrl() {
		return prop.getProperty("delete_url");
	}
}
