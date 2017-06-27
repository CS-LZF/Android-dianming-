package com.web.order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.origin.base.DAOBase; 
import com.web.info.Info;
import com.web.util.Constant;
import com.web.util.Tool;



public class OrderDao {

	public  void delete( String id ){
			DAOBase dao = new DAOBase(); 
			String sql = " delete from  orders  where  id=" + id  ; 
			dao.executeSql(sql);   	  
	}
	
	public  void save( Order object ){

    	DAOBase dao = new DAOBase(); 
		String sql =  "insert into  orders  (  info_id   , seat , description , order_date , beizhu  , price ) values( " + 
								object.info_id    + " , '" + object.getSeat()  + "' , '" + object.getDesc()  + "' , '" + 
								 Tool.getNowTime() + "' , '" + object.beizhu   + "' , '" + object.price  +   "')";   
		dao.executeSql(sql);   	 
	}

	public  void updateOrder( String id  , String state ){
 
    	DAOBase dao = new DAOBase();  
		String sql =  " update orders  set  state = '" + state + "'  where  id=" + id ;   
		dao.executeSql(sql);   	 
	}
	
	public  void updateAlert( String id  , String state ){
		 
    	DAOBase dao = new DAOBase();  
		String sql =  " update orders  set  go_alert = '" + state + "'  where  id=" + id ;   
		dao.executeSql(sql);   	 
	}
	
	
	public  List getAllByAlert(String id ){
		List<Order> list = new ArrayList() ;
    	DAOBase dao = new DAOBase();
		try { 
			String sql=" select * from orders  where go_alert= '1' and seat = '" + id + "' and state='未结账'  order by id desc ";  
	    	dao.querySql(sql);
    		ResultSet rs = dao.getRes(); 
			while(rs.next()) {
				Order object = new Order();
				object.setId( String.valueOf( rs.getInt(1))) ; 
				object.info_id =   String.valueOf( rs.getInt(2))  ;
				object.setSeat(  rs.getString(3) ) ;
				object.setDesc(  rs.getString(4) ); 
				object.setOrder_date(  rs.getString(5) ); 

				object.beizhu = rs.getString(6) ; 
				object.price = rs.getString(7) ; 
				object.state =  rs.getString("state") ; 
				object.go_alert =  rs.getString("go_alert") ; 
				
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


	public  List getAll(){
		
		List<Order> list = new ArrayList() ;
    	DAOBase dao = new DAOBase();
		try { 
			String sql=" select * from orders order by id desc ";  
	    	dao.querySql(sql);
    		ResultSet rs = dao.getRes(); 
			while(rs.next()) {
				Order object = new Order();
				object.setId( String.valueOf( rs.getInt(1))) ; 
				object.info_id =   String.valueOf( rs.getInt(2))  ;
				object.setSeat(  rs.getString(3) ) ;
				object.setDesc(  rs.getString(4) ); 
				object.setOrder_date(  rs.getString(5) ); 

				object.beizhu = rs.getString(6) ; 
				object.price = rs.getString(7) ; 
				object.state =  rs.getString("state") ; 
				object.go_alert =  rs.getString("go_alert") ; 
				
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
	 
	public  String tongji( String  startDate , String endDate  ){
		
		String sum_price = null ;
    	DAOBase dao = new DAOBase();
		try {  
			String sql=" select sum(price) as sum_price  from orders where order_date > '" + startDate + "' and  order_date < '" + endDate + "'";  
	    	dao.querySql(sql);
    		ResultSet rs = dao.getRes(); 
			while(rs.next()) {
				sum_price  = rs.getString("sum_price") ;  
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(dao != null)
				dao.release();			
		}
		return sum_price;
	}

	
	public  Info getFoodById( String info_id ){

		Info object = null ;
    	DAOBase dao = new DAOBase();
		try { 
			String sql=" select * from " + Constant.DB_PREFIX + "food_info  where info_id=  " + info_id	 + "     order by info_id desc  "  ;  
	    	dao.querySql(sql);
    		ResultSet rs = dao.getRes(); 
			while(rs.next()) {
				object = new Info();
//				object.setinfo_id(  String.valueOf( rs.getInt(1))  );
//				object.setFood_name( rs.getString(2) ) ;
//				object.setFood_pic( rs.getString(3) ); 
//				object.setFood_description( rs.getString(4) ); 
//				object.setFood_price(   rs.getString(5)   );
//				object.setFood_discount_price(  String.valueOf( rs.getFloat(6) ) );
//				object.setFood_flag(  String.valueOf( rs.getInt(7) ) );
//
//				object.food_count =  rs.getString("food_count") ; 
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(dao != null)
				dao.release();			
		}
		return object ;
	}

	
	public  void updateFoodCount( String info_id , long food_count){
 
    	DAOBase dao = new DAOBase(); 
		String sql =  " update food_info  set  food_count = '" + food_count + "'  where  info_id=" + info_id ;   
		dao.executeSql(sql);   	 
	}

	
}
