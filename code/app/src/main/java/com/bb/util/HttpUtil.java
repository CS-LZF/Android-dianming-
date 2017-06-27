package com.bb.util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class HttpUtil {

	

	/**
	 * 判断是否存在网络问题
	 * @param activity
	 * @return
	 */
	public static boolean isConnectInternet(Activity activity) {
        ConnectivityManager conManager=(ConnectivityManager)activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conManager.getActiveNetworkInfo();
        if(networkInfo != null){  
        	return networkInfo.isAvailable();
        }
        return false;
	 }
	
	
	
	
}
