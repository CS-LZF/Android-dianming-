package com.bb.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import com.bb.api.BaseAuthenicationHttpClient;

import com.bb.model.Qingjia;
import com.bb.util.Constants;



public class  QingjiaHttpAdapter {

	
	public static ArrayList<Qingjia> getAllQingjiaList(long lastId, int pageNo, String flag) throws Exception{
		String url = Constants.WEB_APP_URL + "qingjia.do?method=list&type=json";
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

		JSONArray jsonArray = new JSONArray(jsonString);		
		ArrayList<Qingjia> ret = new ArrayList<Qingjia>();
		for( int i = 0; i != jsonArray.length(); i++){
			JSONObject json = jsonArray.getJSONObject(i);

			Qingjia object = new Qingjia();
			object.id =  json.getString("id");

     		object.biaoti = json.getString("biaoti");
     		object.miaoshu = json.getString("miaoshu");
     		object.shijian = json.getString("shijian");
     		object.zhuangtai = json.getString("zhuangtai");
     		object.xusheng = json.getString("xusheng");
     		object.kecheng = json.getString("kecheng");
          
           
			ret.add(object);
		}
		return ret;
	}
	
 
	
//	public static ArrayList<Qingjia> getFollowedByType(long lastId, int pageNo,  String news_type ) throws Exception  {
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



