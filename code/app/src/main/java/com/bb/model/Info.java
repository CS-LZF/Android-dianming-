package com.bb.model;

import java.io.Serializable;


/**
 *  音乐的pojo
 * @author Administrator
 *
 */
public class Info implements Serializable  {

	public int info_id = -1;
	public String info_name;
	public String info_pic = null;
	public String info_description;
	public String info_price;
	
	public float info_discount_price;

	public int info_flag;
	
	
	public int getInfo_id() {
		return info_id;
	}
	public void setInfo_id(int info_id) {
		this.info_id = info_id;
	}
	public String getInfo_name() {
		return info_name;
	}
	public void setInfo_name(String info_name) {
		this.info_name = info_name;
	}
	public String getInfo_pic() {
		return info_pic;
	}
	public void setInfo_pic(String info_pic) {
		this.info_pic = info_pic;
	}
	public String getInfo_description() {
		return info_description;
	}
	public void setInfo_description(String info_description) {
		this.info_description = info_description;
	}
	public String getInfo_price() {
		return info_price;
	}
	public void setInfo_price(String info_price) {
		this.info_price = info_price;
	}
	public float getInfo_discount_price() {
		return info_discount_price;
	}
	public void setInfo_discount_price(float info_discount_price) {
		this.info_discount_price = info_discount_price;
	}
	public int getInfo_flag() {
		return info_flag;
	}
	public void setInfo_flag(int info_flag) {
		this.info_flag = info_flag;
	}


	
	
	
	
	
}
