package com.bb.util;

import java.text.DateFormat;
import java.util.Date;
import java.util.TimeZone;


public class Tool {

	
	public static String getNowTime(){
		
		DateFormat dateFormatterChina = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM);//格式化输出
		TimeZone timeZoneChina = TimeZone.getTimeZone("Asia/Shanghai");//获取时区
		dateFormatterChina.setTimeZone(timeZoneChina);//设置系统时区
		Date curDate = new Date();//获取系统时间

		return  dateFormatterChina.format(curDate) ;
	}
	
}
