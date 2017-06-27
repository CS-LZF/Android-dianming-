package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.origin.base.DAOBase;   
import com.model.Qingjia;


public class QingjiaDao {

	public  void delete( String id ){
			DAOBase dao = new DAOBase(); 
			String sql = " delete from  qingjia   where  id=" + id  ; 
			dao.executeSql(sql);   	  
	}
	
	public  int  save( Qingjia object ){

    	DAOBase dao = new DAOBase(); 
		String sql =  "insert into   qingjia ( " + 
		         			" biaoti, " +  
		         			" miaoshu, " +  
		         			" shijian, " +  
		         			" zhuangtai, " +  
		         			" xusheng, " +  
		         			" kecheng ) values(' " + 
		         			object.biaoti + "','" + 
		         			object.miaoshu + "','" + 
		         			object.shijian + "','" + 
		         			object.zhuangtai + "','" + 
		         			object.xusheng + "','" + 
		         			object.kecheng + "') ";
		return dao.executeSql(sql);   	 
	}
	
	public  List getAll( String id , String type ){
		
		List<Qingjia> list = new ArrayList() ;
    	DAOBase dao = new DAOBase();
		try { 
			String sql=" select * from   qingjia  where 1=1 " ;
			
			if( type != null && !type.equals("") ){
				sql += "  and name = '" + type + "' ";
			}
			sql += " order by id desc  ";  
			
	    	dao.querySql(sql);
    		ResultSet rs = dao.getRes(); 
			while(rs.next()) {
				Qingjia object = new Qingjia();
				object.id =   String.valueOf( rs.getInt(1))  ;
         		object.biaoti =   rs.getString("biaoti"); 
         		object.miaoshu =   rs.getString("miaoshu"); 
         		object.shijian =   rs.getString("shijian"); 
         		object.zhuangtai =   rs.getString("zhuangtai"); 
         		object.xusheng =   rs.getString("xusheng"); 
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
	
	
	public  void update( String id , Qingjia o ){

    	DAOBase dao = new DAOBase(); 
    	String sql = null; 
    	sql = " update   qingjia  set  " + 
		         			"biaoti =  '" +  o.biaoti + "' , '" + 
		         			"miaoshu =  '" +  o.miaoshu + "' , '" + 
		         			"shijian =  '" +  o.shijian + "' , '" + 
		         			"zhuangtai =  '" +  o.zhuangtai + "' , '" + 
		         			"xusheng =  '" +  o.xusheng + "' , '" + 
		         			"kecheng =  '" +  o.kecheng + "' where id = " + id  ; 
		dao.executeSql(sql);   	 
	}

	
	public  Qingjia  getObjectById( String id ){
		
    	DAOBase dao = new DAOBase();
		Qingjia  object =  null ; 
		try { 
			String sql = "select * from  qingjia  where id=" + id ;  
			 
	    	dao.querySql(sql);
    		ResultSet rs = dao.getRes(); 
			if(rs.next()) {

				object = new Qingjia(); 
				object.id =   String.valueOf( rs.getInt(1))  ;
         		object.biaoti =   rs.getString("biaoti");
         		object.miaoshu =   rs.getString("miaoshu");
         		object.shijian =   rs.getString("shijian");
         		object.zhuangtai =   rs.getString("zhuangtai");
         		object.xusheng =   rs.getString("xusheng");
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
