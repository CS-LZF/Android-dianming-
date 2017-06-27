package com.web.user_tb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.origin.base.DAOBase;




public class UserTbDao {
 
 

	public  void delete( String id ){
			DAOBase dao = new DAOBase(); 
			String sql = " delete from  user_tb where uid=" + id  ; 
			dao.executeSql(sql);   	  
	}
	
	
	public  List getAll( int pageIndex  , int pageSize  ){
		
		List<UserTb> list = new ArrayList() ;
    	DAOBase dao = new DAOBase();
		try { 
			String sql="select * from user_tb  limit " + pageIndex  + ", " + pageSize ;   
	    	dao.querySql(sql);
    		ResultSet rs = dao.getRes(); 
			while(rs.next()) {
				UserTb object = new UserTb();
				object.uid =  String.valueOf( rs.getInt(1)) ;
				
				object.user_userid =   rs.getString(2)  ;
				object.user_username =   rs.getString(3)  ;
				object.user_password =   rs.getString(4)  ;
				object.user_phone =   rs.getString(5)  ;
				object.user_address =   rs.getString(6)  ;
				
				list.add(object);  
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(dao != null)
				dao.release();			
		}
		return list;
	}
	
	public  List getAll(   ){
		
		List<UserTb> list = new ArrayList() ;
    	DAOBase dao = new DAOBase();
		try { 
			String sql="select * from user_tb  " ;   
	    	dao.querySql(sql);
    		ResultSet rs = dao.getRes(); 
			while(rs.next()) {
				UserTb object = new UserTb();
				object.uid =  String.valueOf( rs.getInt(1)) ;
				
				object.user_userid =   rs.getString(2)  ;
				object.user_username =   rs.getString(3)  ;
				object.user_password =   rs.getString(4)  ;
				object.user_phone =   rs.getString(5)  ;
				object.user_address =   rs.getString(6)  ;
				
				list.add(object);  
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(dao != null)
				dao.release();			
		}
		return list;
	}
	
	public int getTotalCount( ){
		
		String sql = " select count(uid) ids from user_tb " ;
	 	DAOBase dao = new DAOBase();
		int rowCount = 0; 
		try { 
	    	dao.querySql(sql);
    		ResultSet rs = dao.getRes(); 
    		if(rs.next()) { 
    		    rowCount = rs.getInt("ids"); 
    		}
    		 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(dao != null)
				dao.release();			
		}
		return rowCount ;
	}
	
	
	public  boolean login(String userId, String password) {
		
		boolean result = false;
		String sql = "select * from user_tb where user_userid='"+userId+"' and user_password='"+password+"'";
	 	DAOBase dao = new DAOBase();
		try { 
	    	dao.querySql(sql);
    		ResultSet rs = dao.getRes(); 
    		if(rs.next()) { 
    			result = true;
    		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(dao != null)
				dao.release();			
		}
		return result;
	}

	
	/**
	 * 判断是否已存在指定的用户
	 * @param userId
	 * @return
	 */
	public static boolean isExistsUser(String userId) {
		
		boolean result = false;
		String sql = "select * from user_tb where user_userid='"+userId+"'";
	 	DAOBase dao = new DAOBase();
		try { 
	    	dao.querySql(sql);
    		ResultSet rs = dao.getRes(); 
    		if(rs.next()) { 
    			result = true;
    		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(dao != null)
				dao.release();			
		}
		return result;
	}
	
	
	public static boolean insertUser(JSONObject userObject) {
		boolean result = false;
		DAOBase dao = new DAOBase(); 
		String sql;
		try {
			sql = "insert into user_tb (user_userid,user_username,user_password,user_phone,user_address," +
					"user_grade ) " +
					"values ('"+userObject.getString("userId")+"','"+userObject.getString("userName")+"'," +
					"'"+userObject.getString("password")+"','"+userObject.getString("phone")+"'," +
					"'"+userObject.getString("address")+"'" +
					", 0 )";
			int rs = dao.executeSql(sql);   	  
			if (rs > 0) {
				result = true;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
}
