package db;

import java.sql.*;

public class db {


	private String dbDriver = "com.mysql.jdbc.Driver"; 
	private static final String sConnStr = "jdbc:mysql://localhost:3306/b_150310_kaoqin?useUnicode=true&characterEncoding=utf-8"; 
	private static final String userName = "root";
	private static final String passwd = "root";
	
	
	public Connection connect = null;
	public ResultSet rs = null;

	public db() {
		try {
			Class.forName(dbDriver).newInstance();
			connect = DriverManager.getConnection(sConnStr, userName, passwd);
		} catch (Exception ex) {
			System.out.println("12121");
		}
	}

	public ResultSet executeQuery(String sql) {
		try {
			connect = DriverManager.getConnection(sConnStr, userName, passwd);
			Statement stmt = connect.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return rs;
	}

	public void executeUpdate(String sql) {
		Statement stmt = null;
		rs = null;
		try {
			connect = DriverManager.getConnection(sConnStr, userName, passwd);
			stmt = connect.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			connect.close();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}

}
