package com.web.discuss2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.origin.base.DAOBase; 



public class DiscussMgrDao2 {

	

	public  void delete( String id ){
			DAOBase dao = new DAOBase(); 
			String sql = " delete from  discuss2 where id=" + id  ; 
			dao.executeSql(sql);   	  
	}
	

	public  List tongji( ){
		
		List<Discuss2> list = new ArrayList() ;
    	DAOBase dao = new DAOBase();
		try { 
			String sql = " select  sum(discuss.answer) as tiwen  , sum(type.name) as   biaoxian , count(discuss2.username) as qiandao ,  " +
					"	discuss2.username as username  	from  discuss2 ,  discuss ,  type        " +
					"  where discuss2.username = discuss.username   and discuss2.username = type.state   group by discuss2.username " ; 
			
	    	dao.querySql(sql);
    		ResultSet rs = dao.getRes(); 
			while(rs.next()) {
				Discuss2 object = new Discuss2();  
				
				object.content = String.valueOf( rs.getInt( "tiwen" ) ) ;
				object.answer = String.valueOf( rs.getInt( "biaoxian" ) ) ;
				object.uid = String.valueOf( rs.getInt( "qiandao" ) ) ;
				object.username = rs.getString("username") ;
				
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
	
	
	
	public  void save( Discuss2 object ){

    	DAOBase dao = new DAOBase(); 
    	String sql = null; 
    	if( object.getId() == 0) {
    		sql =   "insert into discuss2 (  news_id , content , tbl_date ,  type ,  uid , username  , answer )" +
					"  values(  " + object.getNews_id()    + "  , '" + object.getContent()   +   "' , '" + 
					object.getTbl_date() +  "' , " +  object.getType() +   " , '" + 
					object.uid +  "' , '" +  object.username +   "' , '" + object.getAnswer()  + "' )";   
    	} 

		dao.executeSql(sql);   	 
	}
	
	
	public  void update( String id , String answer ){

    	DAOBase dao = new DAOBase(); 
    	String sql = null; 
    	sql = " update discuss2  set  answer = '" + answer + "'  where id = " + id  ; 
    	 
		dao.executeSql(sql);   	 
	}
	

	public  Discuss2 getObjectById( String id ){
		
    	DAOBase dao = new DAOBase();
		Discuss2 object =  null ; 
		try { 
			String sql = "select * from  discuss2  where id=" + id ;  
			 
	    	dao.querySql(sql);
    		ResultSet rs = dao.getRes(); 
			if(rs.next()) {

				object = new Discuss2(); 
				object.setId(  rs.getInt("id")  ); 
				object.setNews_id(    rs.getInt("news_id")   );   
				object.setContent(  rs.getString("content") );
				object.setTbl_date(  rs.getString("tbl_date") );
				object.setType(   rs.getInt("type")    );
				object.setAnswer( rs.getString("answer") ); 

				object.uid = rs.getString("uid") ;
				object.username = rs.getString("username") ;
				
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
	



	//
	public  List getAll( String id  , String type  ){
		
		List<Discuss2> list = new ArrayList() ;
    	DAOBase dao = new DAOBase();
		try { 
//			String sql = " select * from discuss2  order by id desc "  ;   
			
			String sql=" select * from   discuss2  where 1=1 " ;
			
			if( type != null && !type.equals("") ){
				sql += "  and news_id = '" + type + "' ";
			}
			
			sql += " order by id desc  ";  
			
			
	    	dao.querySql(sql);
    		ResultSet rs = dao.getRes(); 
			while(rs.next()) {
				Discuss2 object = new Discuss2(); 
				object.setId(  rs.getInt("id")  ); 
				object.setNews_id(    rs.getInt("news_id")   );   
				object.setContent(  rs.getString("content") );
				object.setTbl_date(  rs.getString("tbl_date") );
				object.setType(   rs.getInt("type")    );
				object.setAnswer( rs.getString("answer") ); 
				
				object.uid = rs.getString("uid") ;
				object.username = rs.getString("username") ;
				
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
