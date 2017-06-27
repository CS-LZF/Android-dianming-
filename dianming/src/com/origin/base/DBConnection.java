package com.origin.base;

/*
 * 创建数据库连接池
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 


public class DBConnection {

/*
 * ------------------------------------------------------------------private mothed
 */	

	private Connection conn = null;
 
	 
	private static final String url = "jdbc:mysql://localhost:3306/b_150310_kaoqin?useUnicode=true&characterEncoding=utf-8"; 
	private static final String user = "root";
	private static final String password = "root";

	
	public Connection getInstance() throws SQLException {

		try {	
			if(conn == null){
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conn = DriverManager.getConnection(url,user,password); 
  			}

		}  catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;	
	}
/*
 * 
 */
    public void close(){
    	try {
			if(conn != null && !conn.isClosed())
    		conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
