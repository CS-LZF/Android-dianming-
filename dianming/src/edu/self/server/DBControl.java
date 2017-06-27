package edu.self.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.self.connection.DBConnectionManager;

/**
 * 数据库操作类
 * @author mzba
 *
 */
public class DBControl {

	private static DBConnectionManager manager = DBConnectionManager.getInstance();
	
	/**
	 * 判断是否已存在指定的用户
	 * @param userId
	 * @return
	 */
	public static boolean isExistsUser(String userId) {
		boolean result = false;
		Statement stmt = null;
		Connection conn = manager.getConnection();
		try {
			stmt = conn.createStatement();
			String sql = "select * from user_tb where user_userid='"+userId+"'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(stmt != null){
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			manager.freeConnection(conn);
		}
		return result;
	}
	
	/**
	 * 增加一个用户
	 * @param userObject
	 * @return
	 */
	public static boolean insertUser(JSONObject userObject) {
		boolean result = false;
		Statement stmt = null;
		Connection conn = manager.getConnection();
		try {
			stmt = conn.createStatement();
			String sql = "insert into user_tb (user_userid,user_username,user_password,user_phone,user_address," +
					"user_grade ) " +
					"values ('"+userObject.getString("userId")+"','"+userObject.getString("userName")+"'," +
					"'"+userObject.getString("password")+"','"+userObject.getString("phone")+"'," +
					"'"+userObject.getString("address")+"'" +
					", 0 )";
			System.out.println(sql);
			int rs = stmt.executeUpdate(sql);
			if (rs > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			try {
				if(stmt != null){
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			manager.freeConnection(conn);
		}
		return result;
	}
	
	/**
	 * 判断用户名密码是否正确
	 * @param userId
	 * @param password
	 * @return
	 */
	public static boolean login(String userId, String password) {
		boolean result = false;
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = manager.getConnection();
		String sql = "select * from user_tb where user_userid='"+userId+"' and user_password='"+password+"'";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				if(stmt != null){
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			manager.freeConnection(conn);
		}
		return result;
	}
	
	/**
	 * 插入评论
	 * @param resObject
	 * @return
	 */
	public static boolean insertComent( String rid , String price , String  description , String  uid  ) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String ly_time = sdf.format(new java.util.Date()); 

		
		boolean result = false;
		Statement stmt = null;
		Connection conn = manager.getConnection();
		try {
			stmt = conn.createStatement();
			String sql = "insert into comment_tb ( rid ,price , description ,comment_date , uid) " +
					"values ( "+ rid +" , '" + price + "','" + description  + 
					 "' , '"  + ly_time + "'," +  uid + ")" ;
			int rs = stmt.executeUpdate(sql);
			if (rs > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(stmt != null){
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			manager.freeConnection(conn);
		}
		return result;
	}
	
	/**
	 * 插入商品
	 * @param resObject
	 * @return
	 */
	public static boolean insertResource(JSONObject resObject, String resource_pic_path ) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String ly_time = sdf.format(new java.util.Date()); 

		
		boolean result = false;
		Statement stmt = null;
		Connection conn = manager.getConnection();
		try {
			stmt = conn.createStatement();
			String sql = "insert into resource_tb (resource_name,resource_describe,resource_status, resource_type, resource_price, resource_pic_path, resource_buy_date, resource_date , uid) " +
					"values ('"+resObject.getString("resourceName")+"','"+resObject.getString("resourceDescribe")+"','" +
					"审核中' , '"+resObject.getString("resource_type")+"' , '" + resObject.getString("resource_price")+"','" +
					resource_pic_path + "' , '" + resObject.getString("resourceDate")+"' , '" +  ly_time + "'," + 
//							"'"+resObject.getString("resourceStatus")+"',"
					resObject.getInt("uid")+")" ;
			int rs = stmt.executeUpdate(sql);
			if (rs > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			try {
				if(stmt != null){
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			manager.freeConnection(conn);
		}
		return result;
	}
	
	/**
	 * 根据userId获取用户相关信息 
	 * @param userId
	 * @return
	 */
	public static JSONObject getUserByUId(int uid) {
		JSONObject userObject = new JSONObject();
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = manager.getConnection();
		String sql = "select * from user_tb where uid="+uid+"";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				userObject.put("uid", rs.getInt("uid"));
				userObject.put("userId", rs.getString("user_userid"));
				userObject.put("userName", rs.getString("user_username"));
				userObject.put("password", rs.getString("user_password"));
				userObject.put("phone", rs.getString("user_phone"));
				userObject.put("address", rs.getString("user_address"));
				userObject.put("lat", rs.getDouble("user_lat"));
				userObject.put("long", rs.getDouble("user_long"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				if(stmt != null){
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			manager.freeConnection(conn);
		}
		return userObject;
	}
	
	public static JSONObject getUserByUserId(String userId) {
		JSONObject userObject = new JSONObject();
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = manager.getConnection();
		String sql = "select * from user_tb where user_userid='"+userId+"'";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				userObject.put("uid", rs.getInt("uid"));
				userObject.put("userId", rs.getString("user_userid"));
				userObject.put("userName", rs.getString("user_username"));
				userObject.put("password", rs.getString("user_password"));
				userObject.put("phone", rs.getString("user_phone"));
				userObject.put("address", rs.getString("user_address"));
				userObject.put("lat", rs.getDouble("user_lat"));
				userObject.put("long", rs.getDouble("user_long"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				if(stmt != null){
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			manager.freeConnection(conn);
		}
		return userObject;
	}
	
	/**
	 * 获取所有的用户信息
	 * @return
	 */
	public static JSONArray getAllUser() {
		JSONArray userArrays = new JSONArray();
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = manager.getConnection();
		String sql = "select * from user_tb";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				JSONObject userObject = new JSONObject();
				userObject.put("uid", rs.getInt("uid"));
				userObject.put("userId", rs.getString("user_userId"));
				userObject.put("userName", rs.getString("user_username"));
				userObject.put("password", rs.getString("user_password"));
				userObject.put("phone", rs.getString("user_phone"));
				userObject.put("address", rs.getString("user_address"));
				userObject.put("grade", rs.getInt( "user_grade")); 
				userArrays.put(userObject);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				if(stmt != null){
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			manager.freeConnection(conn);
		}
		return userArrays;
	}
	
	/**
	 * 根据用户uid获取商品信息
	 * @param uid
	 * @return
	 */
	public static JSONArray getResourceByUid(int uid) {
		JSONArray resourceArrays = new JSONArray();
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = manager.getConnection();
		String sql = "select user_tb.* , resource_tb.*  from resource_tb,user_tb " +
				"where user_tb.uid=resource_tb.uid and resource_tb.uid="+uid+"";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				JSONObject object = new JSONObject();
				object.put("rid", rs.getInt("rid"));
				object.put("resourceName", rs.getString("resource_name"));
				object.put("resourceDescribe", rs.getString("resource_describe")); 

				object.put("resource_status", rs.getString("resource_status")); 
				
				object.put("resource_type", rs.getString("resource_type")); 
				object.put("resource_price", rs.getString("resource_price"));
				
				object.put("resource_pic_path", rs.getString("resource_pic_path"));  
				object.put("resource_date", rs.getString("resource_date")); 
				 
				
				object.put("uid", rs.getInt("uid"));
				object.put("userId", rs.getString("user_userId"));
				object.put("userName", rs.getString("user_username")); 
				object.put("phone", rs.getString("user_phone"));
				object.put("address", rs.getString("user_address"));
				resourceArrays.put(object);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				if(stmt != null){
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			manager.freeConnection(conn);
		}
		return resourceArrays;
	}
	
	/**
	 * 修改商品
	 * @param object
	 * @return
	 */
	public static boolean updateResource(JSONObject object) {
		boolean result = false;
		Statement stmt = null;
		Connection conn = manager.getConnection();
		try {
			stmt = conn.createStatement();
			String sql = "update resource_tb set resource_name='"
					+ object.getString("resourceName")
					+ "',resource_describe='"
					+ object.getString("resourceDescribe")
					+ "',resource_status='"
					+ object.getString("status") + "' where rid="
					+ object.getInt("rid") + "";
			int rs = stmt.executeUpdate(sql);
			if (rs > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			try {
				if(stmt != null){
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			manager.freeConnection(conn);
		}
		return result;
	}
	
	/**
	 * 修改用户
	 * @param userObject
	 * @return
	 */
	public static boolean updateUser(JSONObject userObject) {
		boolean result = false;
		Statement stmt = null;
		Connection conn = manager.getConnection();
		try {
			stmt = conn.createStatement();
			String sql = "update user_tb set user_username='"
					+ userObject.getString("userName") + "',user_password = '"
					+ userObject.getString("password") + "',user_phone='"
					+ userObject.getString("phone") + "',user_address='"
					+ userObject.getString("address") + "'  where uid = "
					+ userObject.getInt("uid") + "";
			int rs = stmt.executeUpdate(sql);
			if (rs > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			try {
				if(stmt != null){
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			manager.freeConnection(conn);
		}
		return result;
	}
	
	

	 
	
	/**
	 * 获取 
	 * @return
	 */
	public static JSONArray getAllOrder( ) {
		JSONArray jsonArrays = new JSONArray();
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = manager.getConnection();
		String sql = "select *  from  orders  order by id desc ";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){

				JSONObject object = new JSONObject();  

				object.put("id",  String.valueOf( rs.getInt("id")) ) ;
				object.put("jiudian_id",  String.valueOf( rs.getInt("jiudian_id")) ) ;
				object.put(  "seat" ,    rs.getString("seat") ) ;
				object.put(  "description" ,   rs.getString("description")  ) ;
				object.put(  "order_date" ,    rs.getString("order_date")  ) ;
				object.put(  "beizhu" , rs.getString("beizhu")   ) ;
				object.put(  "price" ,  rs.getString("price")  ) ;
				object.put(  "state" ,  rs.getString("state")  ) ;
				object.put(  "ruzhu" ,   rs.getString("ruzhu")  ) ;
				object.put(  "likai" ,   rs.getString("likai")  ) ;
				
				jsonArrays.put(object);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				if(stmt != null){
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			manager.freeConnection(conn);
		}
		return jsonArrays;
	}
	
	/**
	 * 获取所有的商品
	 * @return
	 */
	public static JSONArray getAllResources( String name , String resource_type , int order ) {
		JSONArray resourceArrays = new JSONArray();
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = manager.getConnection();
		String sql = " select user_tb.*,resource_tb.*  from resource_tb,user_tb where user_tb.uid=resource_tb.uid ";
		
		if( name != null && !name.equals("")){
			sql += " and resource_name like '%" + name + "%' " ;
		}
		if( resource_type != null && !resource_type.equals("")){
			sql += " and resource_type like '%" + resource_type + "%' " ;
		}
		
		switch(order){
			case 0:  ; sql += " and resource_status = '审核通过' " ; break;
			case 1: sql = " select  user_resouce.* , count_comment from  ( select  user_tb.user_userId, user_tb.user_phone, " +
						  " user_tb.user_address , user_tb.user_username ,resource_tb.* " +   
						  " from resource_tb,user_tb  where user_tb.uid=resource_tb.uid ) as user_resouce  " + 
						  " left join ( select count(*) as count_comment , rid from comment_tb group by rid  ) as comment_tb_t " +  
						  " on comment_tb_t.rid = user_resouce.rid order by  count_comment desc ;" ; break;
						  
//						  select  user_resouce.* , count_comment
//						  from  ( select  user_tb.user_userId, user_tb.user_phone, user_tb.user_address , user_tb.user_username ,resource_tb.*  
//						   from resource_tb,user_tb  
//						   where user_tb.uid=resource_tb.uid ) as user_resouce 
//						  left join ( select count(*) as count_comment , rid from comment_tb group by rid  ) as comment_tb_t 
//						  on comment_tb_t.rid = user_resouce.rid 
//						  order by  count_comment desc ;

			case 2: sql  += "  order by resource_date desc " ; break;
		}
	 
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){

				JSONObject object = new JSONObject();

				object.put("rid", rs.getInt("rid"));
				object.put("resourceName", rs.getString("resource_name"));
				object.put("resourceDescribe", rs.getString("resource_describe")); 
				 
				object.put("resource_type", rs.getString("resource_type")); 
				object.put("resource_price", rs.getString("resource_price")); 
				object.put("resource_pic_path", rs.getString("resource_pic_path"));  
				object.put("resource_buy_date", rs.getString("resource_buy_date")); 
				object.put("resource_date", rs.getString("resource_date")); 
				 
				object.put("uid", rs.getInt("uid"));
				object.put("userId", rs.getString("user_userId"));
				object.put("userName", rs.getString("user_username")); 
				object.put("phone", rs.getString("user_phone"));
				object.put("address", rs.getString("user_address"));
				resourceArrays.put(object);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				if(stmt != null){
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			manager.freeConnection(conn);
		}
		return resourceArrays;
	}
	
	/**
	 * 删除 
	 * @param rid
	 * @return
	 */
	public static boolean deleteOrder(String id) {
		boolean result = false;
		Statement stmt = null;
		Connection conn = manager.getConnection();
		try {
			stmt = conn.createStatement();
			String sql = "delete from orders where id = "+id ;
			int rs = stmt.executeUpdate(sql);
			if (rs > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(stmt != null){
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			manager.freeConnection(conn);
		}
		return result;
	}
	
	/**
	 * 删除 
	 * @param rid
	 * @return
	 */
	public static boolean deleteUsers(String id) {
		boolean result = false;
		Statement stmt = null;
		Connection conn = manager.getConnection();
		try {
			stmt = conn.createStatement();
			String sql = "delete from user_tb where uid	 = "+id ;
			int rs = stmt.executeUpdate(sql);
			if (rs > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(stmt != null){
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			manager.freeConnection(conn);
		}
		return result;
	}
	
	
	/**
	 * 删除商品
	 * @param rid
	 * @return
	 */
	public static boolean deleteResource(int rid) {
		boolean result = false;
		Statement stmt = null;
		Connection conn = manager.getConnection();
		try {
			stmt = conn.createStatement();
			String sql = "delete from resource_tb where rid="+rid+"";
			int rs = stmt.executeUpdate(sql);
			if (rs > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(stmt != null){
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			manager.freeConnection(conn);
		}
		return result;
	}
	
	
}
