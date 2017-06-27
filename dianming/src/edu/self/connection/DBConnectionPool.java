package edu.self.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 * 此内部类定义了一个连接池，它能够根据要求创建连接，直到预定的最大连接数为止。
 * 在返回连接给客户程序之前，它能够验证连接的有效性。
 */
public class DBConnectionPool {

	/*
	 * 连接池
	 */
	private List<Connection> freeConnections = new ArrayList<Connection>();
	private Connection conn = null;
	private int connect = 0;     //使用的连接数
	private int maxConn;      //最大连接数
	private String driver;    //数据库驱动
	private String url;     //数据库连接地址
	private String user;     //用户名
	private String password;      //用户名
	
	public DBConnectionPool(String driver,String URL,String user,String password,int maxConn){
		this.driver = driver;
		this.url = URL;
		this.user = user;
		this.password = password;
		this.maxConn = maxConn;
		poolInfo();
	}
	
	/*
	 * 显示准备创建连接池的信息
	 */
	private void poolInfo(){
		Connection conn = this.newConnection();
		freeConnections.add(conn);
		for(int i = 0;i < this.maxConn - 1;i++){
			Connection freeConn = conn;
			freeConnections.add(freeConn);
		}
	}
	
	/*
	 * 用完，释放一个连接
	 */
	public synchronized void freeConnection(Connection conn){
		this.freeConnections.add(conn);
		this.connect--;
	}
	
	/*
	 * 从连接池中获取一个可用连接，当无法从连接池中获取可用连接时，新创建一个连接
	 */
	public synchronized Connection getConnection(){
		if(this.freeConnections.size() > 0){
			/*
			 * 当在连接池中取出一个连接后，删除此连接
			 */
			conn = this.freeConnections.get(0);
			this.freeConnections.remove(0);
		}
		/*
		 * 当取出的连接为null时，递归调用自己，直到获得一个可用连接为止
		 */
		if(conn == null){
			conn = getConnection();
		}else{
			conn = newConnection();
		}
		if(this.maxConn == 0 || this.maxConn < this.connect){
			/*
			 * 等待超过最大连接时
			 */
			conn = null;
		}
		if(conn != null){
			this.connect++;
		}
		return conn;
	}
	
	/*
	 * 释放全部连接
	 */
	public synchronized void release(){
		Iterator<Connection> allConns = this.freeConnections.iterator();
		while(allConns.hasNext()){
			Connection conn = (Connection)allConns.next();
			try{
				if(null != conn){
					conn.close();
				}
				conn = null;
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		this.freeConnections.clear();
	}
	
	/*
	 * 创建一个数据库连接对象
	 */
	private Connection newConnection(){
		try{
			Class.forName(driver);
		}catch(ClassNotFoundException e2){
			e2.printStackTrace();
		}
		try{
			conn = DriverManager.getConnection(url,user,password);
		}catch(SQLException e3){
			e3.printStackTrace();
			System.exit(0);
		}
		return conn;
	}
}
