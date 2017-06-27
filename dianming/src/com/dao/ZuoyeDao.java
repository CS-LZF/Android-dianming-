package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.origin.base.DAOBase;   
import com.model.Zuoye;


public class ZuoyeDao {

	public  void delete( String id ){
			DAOBase dao = new DAOBase(); 
			String sql = " delete from  zuoye   where  id=" + id  ; 
			dao.executeSql(sql);   	  
	}
	
	public  int  save( Zuoye object ){

    	DAOBase dao = new DAOBase(); 
		String sql =  "insert into   zuoye ( " + 
		         			" mingcheng, " +  
		         			" miaoshu, " +  
		         			" shijian, " +  
		         			" kecheng ) values(' " + 
		         			object.mingcheng + "','" + 
		         			object.miaoshu + "','" + 
		         			object.shijian + "','" + 
		         			object.kecheng + "') ";
		return dao.executeSql(sql);   	 
	}
	
	public  List getAll( String id , String type ){
		
		List<Zuoye> list = new ArrayList() ;
    	DAOBase dao = new DAOBase();
		try { 
			String sql=" select * from   zuoye  where 1=1 " ;
			
			if( type != null && !type.equals("") ){
				sql += "  and kecheng = '" + type + "' ";
			}
			sql += " order by id desc  ";  
			
	    	dao.querySql(sql);
    		ResultSet rs = dao.getRes(); 
			while(rs.next()) {
				Zuoye object = new Zuoye();
				object.id =   String.valueOf( rs.getInt(1))  ;
         		object.mingcheng =   rs.getString("mingcheng"); 
         		object.miaoshu =   rs.getString("miaoshu"); 
         		object.shijian =   rs.getString("shijian"); 
         		object.kecheng =   rs.getString("kecheng"); 
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
	
	
	public  void update( String id , Zuoye o ){

    	DAOBase dao = new DAOBase(); 
    	String sql = null; 
    	sql = " update   zuoye  set  " + 
		         			"mingcheng =  '" +  o.mingcheng + "' , '" + 
		         			"miaoshu =  '" +  o.miaoshu + "' , '" + 
		         			"shijian =  '" +  o.shijian + "' , '" + 
		         			"kecheng =  '" +  o.kecheng + "' where id = " + id  ; 
		dao.executeSql(sql);   	 
	}

	
	public  Zuoye  getObjectById( String id ){
		
    	DAOBase dao = new DAOBase();
		Zuoye  object =  null ; 
		try { 
			String sql = "select * from  zuoye  where id=" + id ;  
			 
	    	dao.querySql(sql);
    		ResultSet rs = dao.getRes(); 
			if(rs.next()) {

				object = new Zuoye(); 
				object.id =   String.valueOf( rs.getInt(1))  ;
         		object.mingcheng =   rs.getString("mingcheng");
         		object.miaoshu =   rs.getString("miaoshu");
         		object.shijian =   rs.getString("shijian");
         		object.kecheng =   rs.getString("kecheng");
				return object ;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(dao != null)
				dao.release();			
		}
		return null;
	}
	
		
		
		
}
