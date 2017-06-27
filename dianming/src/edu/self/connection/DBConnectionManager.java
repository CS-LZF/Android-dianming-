package edu.self.connection;

import java.sql.Connection;
import java.util.Enumeration;
import java.util.Hashtable;

import edu.self.util.PoolProperties;


/**
 * 连接池的管理类，负责读取配置连接池的文件，并创建连接池
 * 从连接池中获取，释放连接
 * mzba
 */
public class DBConnectionManager {

	/**
	 * 唯一数据库连接池管理实例类
	 * 使用单例模式创建
	 */
	private static DBConnectionManager instance;
	
	/**
	 * 连接池的集合
	 */
	private Hashtable<String,DBConnectionPool> pools = new Hashtable<String,DBConnectionPool>();
	private static String poolName = "MYSQL_MZBA";      //连接池名字
	
	public static synchronized DBConnectionManager getInstance(){
		if(instance == null){
			instance = new DBConnectionManager();
		}
		return instance;
	}
	
	/**
	 * 只允许内部实例化管理类
	 */
	private DBConnectionManager(){
		this.init();
	}
	
	/***
	 * 加载驱动程序
	 */
	private void init(){
		PoolProperties poolProperties = new PoolProperties();
		DBConnectionPool pool = new DBConnectionPool(poolProperties.getDriver(), poolProperties.getUrl(), 
				poolProperties.getUser(), poolProperties.getPassword(), poolProperties.getMaxConn());
		pools.put(poolName, pool);
	}
	
	/**
	 * 根据连接池的名字得到一个连接
	 */
	public Connection getConnection(){
		DBConnectionPool pool = null;
		Connection conn = null;
		pool = pools.get(poolName);
		try{
			conn = pool.getConnection();
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
	/**
	 * 释放一个连接
	 */
	public synchronized void freeConnection(Connection conn){
		DBConnectionPool pool = pools.get(poolName);
		if(pool != null){
			pool.freeConnection(conn);
		}
	}
	/**
	 * 释放所有连接
	 */
	public synchronized void release(){
		Enumeration<DBConnectionPool> allpools = pools.elements();
		while(allpools.hasMoreElements()){
			DBConnectionPool pool = allpools.nextElement();
			if(pool != null){
				pool.release();
			}
		}
		pools.clear();
	}

}
