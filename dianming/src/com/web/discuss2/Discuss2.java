package com.web.discuss2;

import java.io.Serializable;


public class Discuss2  implements Serializable { 

	
	public int  id ;
	public int  news_id ;
	public String content ; 
	public String  tbl_date ;

	public String  uid , username  ;
 
	
	/**
	 * 0——评论，1——评论
	 */
	public int  type ;
	public String  answer ;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNews_id() {
		return news_id;
	}
	public void setNews_id(int news_id) {
		this.news_id = news_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTbl_date() {
		return tbl_date;
	}
	public void setTbl_date(String tbl_date) {
		this.tbl_date = tbl_date;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	  
}
