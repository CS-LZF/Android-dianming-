package com.web.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.origin.base.DAOBase;




public class UserDao {


	public  boolean login( String tableName , String name , String password){

    	DAOBase dao = new DAOBase();
		try { 
			String sql = "select * from " + tableName  + " where name='" + name + "' and password='" + password +"'"; 
	    	dao.querySql(sql);
    		ResultSet rs = dao.getRes(); 
			while(rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(dao != null)
				dao.release();			
		}
		return false;
	}
	

	public  boolean userLogin( String tableName , String name , String password , String flag ){

    	DAOBase dao = new DAOBase();
		try { 
			String sql = "select 1 from " + tableName  + " where user_userid='" + name + "' and user_password='" + password +"' and  flag ='" + flag + "' "; 
	    	dao.querySql(sql);
    		ResultSet rs = dao.getRes(); 
			while(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(dao != null)
				dao.release();			
		}
		return false;
	}
	
	public User getUserTbByName(String tableName , String name , String password ){

		DAOBase dao = new DAOBase();
		User object = null ;
		
		try { 
			String sql = " select uid as id , user_username as screen_name , 2 as type  from "  + tableName + " where user_userid='" + name + "' and user_password='" + password + "'"; 
	    	dao.querySql(sql);
    		ResultSet rs = dao.getRes(); 
			while(rs.next()) {
				object = new User() ;
				object.setId( String.valueOf( rs.getInt(1))  );
				object.setScreen_name(  rs.getString(2)  );			
				object.type =    rs.getString(3)  ;	
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(dao != null)
				dao.release();			
		}
		return object;
	}
	
	public User getUserByName(String tableName , String name , String password ){

		DAOBase dao = new DAOBase();
		User object = null ;
		
		try { 
			String sql = " select id , screen_name , type  from "  + tableName + " where name='" + name + "' and password='" + password + "'"; 
	    	dao.querySql(sql);
    		ResultSet rs = dao.getRes(); 
			while(rs.next()) {
				object = new User() ;
				object.setId( String.valueOf( rs.getInt(1))  );
				object.setScreen_name(  rs.getString(2)  );			
				object.type =    rs.getString(3)  ;	
				break;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(dao != null)
				dao.release();			
		}
		return object;
	}

	public  void delete( String id ){
			DAOBase dao = new DAOBase(); 
			String sql = " delete from  admin where id=" + id  ; 
			dao.executeSql(sql);   	  
	}
	
	
	public  void save( User object ){

    	DAOBase dao = new DAOBase(); 
		String sql = "insert into admin ( name  , password , screen_name , type   ) values( '" + 
						object.getName() + "' , '" + object.getPassword()   + " ' , '"  + object.getScreen_name() 
					 + " ' , '"  + object.type + "' ) "  ;   
		dao.executeSql(sql);   	 
	}

	
	public  void update( String table ,User object ){

    	DAOBase dao = new DAOBase(); 
		String sql = " update " +  table + " set name = '" + object.getName()  + "', type = '" + object.type + "' , password='" + object.getPassword()+ 
					"', screen_name ='" +  object.getScreen_name() + "' where id="  + object.getId() ;   
		dao.executeSql(sql);   	 
	}
	
	
	public  User getById( String tableName , String id ){		
    	DAOBase dao = new DAOBase();
		User object = null ;		
		try { 
			String sql = "select * from  " + tableName +  "  where id = " + id ;  
	    	dao.querySql(sql);
    		ResultSet rs = dao.getRes(); 
			while(rs.next()) {
				object = new User() ; 
				object.setId( String.valueOf( rs.getInt(1))  );
				object.setName( rs.getString(2) ) ;
				object.setPassword( rs.getString(3) );
				object.setScreen_name(  rs.getString(4)  );
				object.type =    rs.getString(5)  ;
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(dao != null)
				dao.release();			
		}
		return object;
	}

	
	public  List getAll( int pageIndex  , int pageSize  ){
		
		List<User> list = new ArrayList() ;
    	DAOBase dao = new DAOBase();
		try { 
			String sql="select * from admin  limit " + pageIndex  + ", " + pageSize ;   
	    	dao.querySql(sql);
    		ResultSet rs = dao.getRes(); 
			while(rs.next()) {
				User object = new User();
				object.setId( String.valueOf( rs.getInt(1))  );
				object.setName( rs.getString(2) ) ;
				object.setPassword( rs.getString(3) );
				object.setScreen_name(  rs.getString(4)  );
				object.type =    rs.getString(5)  ;
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
		
		String sql = " select count(id) ids from admin " ;
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
	
	
}
