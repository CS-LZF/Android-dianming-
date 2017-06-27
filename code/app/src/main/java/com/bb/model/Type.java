package com.bb.model;

import java.io.Serializable;

public class Type  implements Serializable { 

	
	private int  id ;
	private String name  ;
	private String content ;  
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	 
	
	
	
	
	  
}
