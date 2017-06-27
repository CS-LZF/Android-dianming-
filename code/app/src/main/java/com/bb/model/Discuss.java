package com.bb.model;


import java.io.Serializable;


public class Discuss  implements Serializable { 

	
	private int  id ;
	private int  news_id ;
	private String content ; 
	private String  tbl_date ;
	
	/**
	 * 0——评论，1——评论
	 */
	private int  type ;
	private String  answer ;
	
	
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
	
	
 
	 
	
	
	  
}
