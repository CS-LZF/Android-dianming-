package edu.self.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * 数据库连接管理
 * @author mzba
 *
 */
public class PoolProperties {

	private Properties properties;
	private String driver;
	private String url;
	private String user;
	private String password;
	private int maxConn;
	private int minConn;
	
	public PoolProperties(){
		properties = new Properties();
		String path = getClass().getProtectionDomain().getCodeSource().getLocation().toString();
		path = path.substring(6, path.length());
		if (path.indexOf("WEB-INF") > 0) {
		      path = path.substring(0, path.indexOf("/WEB-INF") + 9) ;
		}else{
		      path = "d:";
		}
		try {
			this.properties.load(new FileReader(path + "db.pro"));
		}catch (FileNotFoundException e) {
		    e.printStackTrace();
		}catch (IOException e) {
		    e.printStackTrace();
		}
		this.setDriver(properties.getProperty("driver"));
		this.setUrl(properties.getProperty("url"));
		this.setUser(properties.getProperty("user"));
		this.setPassword(properties.getProperty("password"));
		this.setMaxConn(Integer.parseInt(properties.getProperty("maxConn")));
		this.setMinConn(Integer.parseInt(properties.getProperty("minConn")));
	}
	
	
	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getMaxConn() {
		return maxConn;
	}

	public void setMaxConn(int maxConn) {
		this.maxConn = maxConn;
	}

	public int getMinConn() {
		return minConn;
	}

	public void setMinConn(int minConn) {
		this.minConn = minConn;
	}
	
	
}
