package com.origin.base;

/*
 * DAO对象父类
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DAOBase {

/*
 *---------------------------------------------------------private method 
 */	
	private Statement stmt;
	private DBConnection conn; 
	private PreparedStatement pstmt;
	private ResultSet res; 


/*
 * --------------------------------------------------------public method
 */	
	public DAOBase(){
		conn = new DBConnection();
	}

	public int executeSql(String sql){
		int i = 0;
		try {
			pstmt = conn.getInstance().prepareStatement(sql);
			i = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}finally{
			if (pstmt!=null){ 
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
			conn.close();			
		}
		return i;
	}
/*
 * 
 */
	public void batchExeSql(String[] batchSql){
		try {
			int i = 0;
			for (; i < batchSql.length; i++){
				pstmt = conn.getInstance().prepareStatement(batchSql[i]);
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getStackTrace();
		}finally{
			if (pstmt!=null){ 
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
			conn.close();			
		}
	}
/*
 * 
 */
	public void querySql(String sql){
		int i = 0;
		try {
			stmt = conn.getInstance().createStatement();
			res = stmt.executeQuery(sql);
			System.out.println( "DAOBase.querySql.res:"+res+ "::::sql::::" + sql );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace( );
		} 
	}
/*
 * 
 */
	public ResultSet getRes(){
		return res;
	}
/*
 * 
 */	
	public void release(){
		try {
			if(res != null)
			    res.close();
			if (stmt!=null)
				stmt.close();
			if (pstmt!=null)
				pstmt.close();						
			conn.close();				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace() ;
		}
	}
}
