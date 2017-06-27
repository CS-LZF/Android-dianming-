package com.bb.model;

import java.io.Serializable;


/** 
 * @author Administrator
 *
 */
public class ResouceTb implements Serializable  {

	public String rid ;
	public String resource_name	;
	public String resource_describe	 = null;
	public String resource_pic_path	;
	public String resource_date;
	
	
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public String getResource_name() {
		return resource_name;
	}
	public void setResource_name(String resource_name) {
		this.resource_name = resource_name;
	}
	public String getResource_describe() {
		return resource_describe;
	}
	public void setResource_describe(String resource_describe) {
		this.resource_describe = resource_describe;
	}
	public String getResource_pic_path() {
		return resource_pic_path;
	}
	public void setResource_pic_path(String resource_pic_path) {
		this.resource_pic_path = resource_pic_path;
	}
	public String getResource_date() {
		return resource_date;
	}
	public void setResource_date(String resource_date) {
		this.resource_date = resource_date;
	}
	
 
	
}
