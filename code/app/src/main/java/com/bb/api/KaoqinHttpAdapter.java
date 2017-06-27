package com.bb.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.bb.model.Discuss2;
import com.bb.model.Zuoye;
import com.bb.util.Constants;



public class  KaoqinHttpAdapter {

	
	public static ArrayList<Discuss2> getAllKaoqinList(long lastId, int pageNo, String flag) throws Exception{
		String url = Constants.WEB_APP_URL + "discuss2.do?method=list&type=json";
		if( flag != null && !flag.equals("") ){
			try {
				flag  = URLEncoder.encode(flag, "utf-8") ;
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			url += "&f=" + flag;
		}
		
		System.out.println( " getUpdatesList url ::::::::::" + url );
		String jsonString = BaseAuthenicationHttpClient.doRequest(url, "", "");

		System.out.println( " getUpdatesList jsonString ::::::::::" + jsonString );
		
		
		JSONArray jsonArray = new JSONArray(jsonString);		
		ArrayList<Discuss2> ret = new ArrayList<Discuss2>();
		for( int i = 0; i != jsonArray.length(); i++){
			JSONObject json = jsonArray.getJSONObject(i);

			Discuss2 object = new Discuss2(); 
     		object.content  = json.getString("content");
     		object.tbl_date  = json.getString("tbl_date"); 
			ret.add(object);
		}
		return ret;
	}
	
 
	
//	public static ArrayList<Zuoye> getFollowedByType(long lastId, int pageNo,  String news_type ) throws Exception  {
//	String url = Constants.WEB_APP_URL + "foodList.do?type=json";
//	if( news_type != null ){
//		try {
//			news_type  = URLEncoder.encode(news_type, "utf-8") ;
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		url += "&news_type=" +  news_type  ;
//	}
//	return getUpdatesList(url,lastId,pageNo);
//}
	
	
}



