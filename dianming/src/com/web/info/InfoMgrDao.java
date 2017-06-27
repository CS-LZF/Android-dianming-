package com.web.info;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.origin.base.DAOBase;  
import com.web.util.Constant;



public class InfoMgrDao {

	

	public  void delete( String id ){
			DAOBase dao = new DAOBase(); 
			String sql = " delete from  " + Constant.DB_PREFIX + "info_info   where  info_id=" + id  ; 
			dao.executeSql(sql);   	  
	}
	
	
	public  void save( Info object ){

    	DAOBase dao = new DAOBase(); 
		String sql =  "insert into " + Constant.DB_PREFIX + "info_info ( info_name  , info_pic , info_description , info_price , info_discount_price ,info_flag ) values( '" + 
								object.info_name  + "' , '" + object.info_pic  + "' , '" + object.info_description + "' , '" + 
								object.info_price  + "' , "  + object.info_discount_price +  " , " + object.info_flag  + ")";   
		dao.executeSql(sql);   	 
	}

	public  List getAllByHot(){
		
		List<Info> list = new ArrayList() ;
    	DAOBase dao = new DAOBase();
		try { 
			String sql=" select * from " + Constant.DB_PREFIX + "info_info  order by info_count desc  ";  
	    	dao.querySql(sql);
    		ResultSet rs = dao.getRes(); 
			while(rs.next()) {
				Info object = new Info();
				object.info_id =   String.valueOf( rs.getInt(1))  ;
				object.info_name =  rs.getString(2)  ;
				object.info_pic =  rs.getString(3) ; 
				object.info_description =  rs.getString(4) ; 
				object.info_price =    rs.getString(5) ;
				object.info_discount_price =   String.valueOf( rs.getFloat(6) ) ;
				object.info_flag =   String.valueOf( rs.getInt(7) ) ;
				object.info_count =  rs.getString("info_count") ; 
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
	//android获取课程
public  List getAll1(){
		
		List<Info> list = new ArrayList() ;
    	DAOBase dao = new DAOBase();
		try { 
			String sql=" select * from info_info  order by info_id desc  ";  
	    	dao.querySql(sql);
    		ResultSet rs = dao.getRes(); 
			while(rs.next()) {
				Info object = new Info();
				object.info_id =   String.valueOf( rs.getInt(1))  ;
				object.info_name =  rs.getString(2)  ;
				object.info_pic =  rs.getString(3) ; 
				object.info_description =  rs.getString(4) ; 
				object.info_price =    rs.getString(5) ;
				object.info_discount_price =   String.valueOf( rs.getFloat(6) ) ;
				object.info_flag =   String.valueOf( rs.getInt(7) ) ;
				object.info_count =  rs.getString("info_count") ; 
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
	//web服务器获取课程列表
	public  List getAll(Object pic){
		
		List<Info> list = new ArrayList() ;
    	DAOBase dao = new DAOBase();
		try { 
			String sql=" select info_info.* from admin,info_info where admin.id=info_info.info_pic and info_info.info_pic="+pic+" order by info_id desc  ";  
	    	dao.querySql(sql);
    		ResultSet rs = dao.getRes(); 
			while(rs.next()) {
				Info object = new Info();
				object.info_id =   String.valueOf( rs.getInt(1))  ;
				object.info_name =  rs.getString(2)  ;
				object.info_pic =  rs.getString(3) ; 
				object.info_description =  rs.getString(4) ; 
				object.info_price =    rs.getString(5) ;
				object.info_discount_price =   String.valueOf( rs.getFloat(6) ) ;
				object.info_flag =   String.valueOf( rs.getInt(7) ) ;
				object.info_count =  rs.getString("info_count") ; 
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
	
	public  List getFoodsByType( String type ){
		
		List<Info> list = new ArrayList() ;
    	DAOBase dao = new DAOBase();
		try { 
			String sql=" select * from " + Constant.DB_PREFIX + "info_info  where info_price = '" + type + "'     order by info_id desc  "  ;  
	    	dao.querySql(sql);
    		ResultSet rs = dao.getRes(); 
			while(rs.next()) {
				Info object = new Info();
				object.info_id =   String.valueOf( rs.getInt(1))  ;
				object.info_name =  rs.getString(2)  ;
				object.info_pic =  rs.getString(3) ; 
				object.info_description =  rs.getString(4) ; 
				object.info_price =    rs.getString(5) ;
				object.info_discount_price =   String.valueOf( rs.getFloat(6) ) ;
				object.info_flag =   String.valueOf( rs.getInt(7) ) ;
				object.info_count =  rs.getString("info_count") ; 
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


	public  List getFoodsByFlag( String flag ){
		
		List<Info> list = new ArrayList() ;
    	DAOBase dao = new DAOBase();
		try { 
			String sql=" select * from " + Constant.DB_PREFIX + "info_info  where info_flag=  " + flag + "     order by info_id desc  "  ;  
	    	dao.querySql(sql);
    		ResultSet rs = dao.getRes(); 
			while(rs.next()) {
				Info object = new Info();
				
//				info_name  , info_pic , info_description , info_price , info_discount_price ,info_flag 
				
				object.info_id =   String.valueOf( rs.getInt(1))  ;
				object.info_name =  rs.getString(2)  ;
				object.info_pic =  rs.getString(3) ; 
				object.info_description =  rs.getString(4) ; 
				object.info_price =    rs.getString(5) ;
				object.info_discount_price =   String.valueOf( rs.getFloat(6) ) ;
				object.info_flag =   String.valueOf( rs.getInt(7) ) ;
				object.info_count =  rs.getString("info_count") ; 
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
	
}
