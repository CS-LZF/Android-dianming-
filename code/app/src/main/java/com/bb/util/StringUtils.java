package com.bb.util;

import java.util.ArrayList;

public class StringUtils {
	public static ArrayList<String> spliteByToken(String src, String token){
		String[] array = src.split(token);		
		ArrayList<String> ret = new ArrayList<String>();
		for(String temp : array){
			if(temp != ""){
				ret.add(temp);
			}
		}
		return ret;
	}
	public static String combineByToken(ArrayList<String> list, String token){
		StringBuffer buf = new StringBuffer();
		for(String temp : list){
			buf.append(token).append(temp);
		}
		if(buf.length() != 0){
			buf.deleteCharAt(0);//will refactory soon here
		}
		return buf.toString();
	}
}
