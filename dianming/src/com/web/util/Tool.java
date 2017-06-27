package com.web.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tool {

	
	
	public static  String getNowTime(){
		
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
		
		return formatter.format(currentTime) ;
	}
	
	public static  String getFileName(){
		
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat( "yyyyMMddHHmmss");

		return formatter.format(currentTime) ;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println( getNowTime()  ); 
		
	}

}
