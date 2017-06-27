package com.bb.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.bb.model.Discuss;
import com.bb.model.Info;
import com.bb.model.News;
import com.bb.model.ResouceTb;
import com.bb.model.Type; 
import com.bb.util.Constants;


/**
 * 
 * @author 
 *
 */
public class HttpApiAccessor {

	

	/**
	 * 发送评论
	 * @param map
	 * @return
	 */
	public static String sendDiscuss ( HashMap map )  {

		String url =  Constants.WEB_APP_URL + "discuss.do" ;
		
		String ret = null; 
		
		try { 
			ret = BaseAuthenicationHttpClient.doRequest(url, "", "" , map );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ret;
	}

	/**
	 * 发送评论
	 * @param map
	 * @return
	 */
	public static String sendDiscuss2 ( HashMap map )  {

		String url =  Constants.WEB_APP_URL + "discuss2.do" ;
		
		String ret = null; 
		
		try { 
			ret = BaseAuthenicationHttpClient.doRequest(url, "", "" , map );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ret;
	}
	
	

	public static ArrayList getAllDiscussById( int id  , String t )  {

		String url =  Constants.WEB_APP_URL + "discuss.do?method=list&type=json&id=" + id + "&t=" + t ;
		
		ArrayList ret = null;
		String jsonString;
		
		try {
			jsonString = BaseAuthenicationHttpClient.doRequest(url, "", "");
			JSONArray jsonArray = new JSONArray( jsonString  ); 
			 
			ret = new ArrayList<News>();

			if( jsonString.equals("[null]") )
				return ret ; 
		
			for( int i = 0; i != jsonArray.length(); i++){
				JSONObject json = jsonArray.getJSONObject(i);
				Discuss object = jsonToDiscuss(json);
				ret.add(object);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ret;
	}
	

//{"answer":"0","content":"213123","id":1,"news_id":8,"tbl_date":"2012-05-19 11:43:45","type":0}]:::==1======== 1
	
	private static Discuss jsonToDiscuss (JSONObject json) throws JSONException{
		
		Discuss object = new Discuss();
		object.setId(  json.getInt("id")  ); 
		object.setNews_id(    json.getInt("news_id")   );   
		object.setContent(  json.getString("content") );
		object.setTbl_date(  json.getString("tbl_date") );
		object.setType(   json.getInt("type")    );
		object.setAnswer( json.getString("answer") ); 
		
		return object;
	}
	
	
	
	
	/** 
	 *  从服务器获取所有的分类
	 */
	public static ArrayList<Type> getAllNewsType(   )  {
		String url =  Constants.WEB_APP_URL + "type.do?method=list&type=json" ;
		ArrayList<Type> ret = null;
		String jsonString;
		try {
			jsonString = BaseAuthenicationHttpClient.doRequest(url, "", "");
			JSONArray jsonArray = new JSONArray( jsonString  ); 
			ret = new ArrayList<Type>();
			for( int i = 0; i != jsonArray.length(); i++){
				JSONObject json = jsonArray.getJSONObject(i);
				Type object = jsonToType(json);
				ret.add(object);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	private static  Type jsonToType(JSONObject json) throws JSONException{
		Type object = new Type();
		object.setId( json.getInt("id") );
		object.setName(  json.getString("name") );
		object.setContent( json.getString("content") ); 
		return object;
	}
	 
	public static ArrayList<ResouceTb> getZuoyeList(long lastId, int pageNo, String flag) throws Exception{
		String url = Constants.WEB_APP_URL + "infoList3.do?type=json";
		url = appendParams(url, lastId, pageNo);
		
		System.out.println( " getUpdatesList url ::::::::::" + url );
		String jsonString = BaseAuthenicationHttpClient.doRequest(url, "", "");

		JSONArray jsonArray = new JSONArray(jsonString);		
		ArrayList<ResouceTb> ret = new ArrayList<ResouceTb>();
		for( int i = 0; i != jsonArray.length(); i++){
			JSONObject json = jsonArray.getJSONObject(i);
			ResouceTb info = jsonToResouceTb(json);
			ret.add(info);
		}
		return ret;
	}
		
	
	
	public static ArrayList<ResouceTb> getZiyuanList(long lastId, int pageNo, String flag) throws Exception{
		String url = Constants.WEB_APP_URL + "infoList2.do?type=json";
		url = appendParams(url, lastId, pageNo);
		
		System.out.println( " getUpdatesList url ::::::::::" + url );
		String jsonString = BaseAuthenicationHttpClient.doRequest(url, "", "");

		JSONArray jsonArray = new JSONArray(jsonString);		
		ArrayList<ResouceTb> ret = new ArrayList<ResouceTb>();
		for( int i = 0; i != jsonArray.length(); i++){
			JSONObject json = jsonArray.getJSONObject(i);
			ResouceTb info = jsonToResouceTb(json);
			ret.add(info);
		}
		return ret;
	}
		
 
	public static ArrayList<Info> getFollowed(long lastId, int pageNo, String flag) throws Exception{
		String url = Constants.WEB_APP_URL + "foodList.do?type=json";
		if(flag != null && !flag.equals( Constants.FLAG_ALL)){
			url += "&f=" + flag;
		}
		return getUpdatesList(url,lastId,pageNo);
	}

	public static ArrayList<Info> getFollowedBySale(long lastId, int pageNo ) throws Exception{
		String url = Constants.WEB_APP_URL + "foodList.do?method=hot&type=json";
		return getUpdatesList(url,lastId,pageNo);
	}
	
	public static ArrayList<Info> getFollowedByType(long lastId, int pageNo,  String news_type ) throws Exception  {
		String url = Constants.WEB_APP_URL + "foodList.do?type=json";
		if( news_type != null ){
			try {
				news_type  = URLEncoder.encode(news_type, "utf-8") ;
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			url += "&news_type=" +  news_type  ;
		}
		return getUpdatesList(url,lastId,pageNo);
	}

	
	
//	
	private static ArrayList<Info> getUpdatesList(String url,long lastId, int pageNo) throws Exception{
		url = appendParams(url, lastId, pageNo);
		
		System.out.println( " getUpdatesList url ::::::::::" + url );
		String jsonString = BaseAuthenicationHttpClient.doRequest(url, "", "");

		JSONArray jsonArray = new JSONArray(jsonString);		
		ArrayList<Info> ret = new ArrayList<Info>();
		for( int i = 0; i != jsonArray.length(); i++){
			JSONObject json = jsonArray.getJSONObject(i);
			Info food = jsonToInfo(json);
			ret.add(food);
		}
		return ret;
	}
	
//	将json数据解析赋值到info类
	private static Info jsonToInfo(JSONObject json) throws JSONException{
		Info info = new Info();
		info.info_description = json.getString("info_description") ;
		info.info_discount_price = Float.valueOf(json.get("info_discount_price").toString()) ;
		info.info_flag = json.getInt("info_flag") ;
		info.info_id = json.getInt("info_id") ;
		info.info_name = json.getString("info_name") ;
		info.info_pic = json.getString("info_pic") ;
		info.info_price =  json.getString("info_price") ;
		return info;
	}


	private static ResouceTb jsonToResouceTb(JSONObject json) throws JSONException{
		ResouceTb info = new ResouceTb(); 
		info.rid = json.getString("rid") ;
		info.resource_name = json.getString("resource_name") ;
		info.resource_describe = json.getString("resource_describe") ;
		info.resource_pic_path =  json.getString("resource_pic_path") ;
		info.resource_date =  json.getString("resource_date") ;
		return info;
	}
 
	
	private static String appendParams(String url, long lastId, int pageNo) {
		if(lastId != -1){
			url = "?last_id=" + lastId;
		}
		if(pageNo != -1){
			url = "&pageNo=" + pageNo;
		}
		return url;
	}

	
	
}



