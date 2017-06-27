package com.bb.model;

import java.io.Serializable;


/**
 * 收藏
 * @author Administrator
 *
 */
public class Temp implements Serializable  {

	private int 	id ; 
	private String 	food_name ,  seat , price, order_date ;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFood_name() {
		return food_name;
	}
	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	} 
	 
	 
	
	
}
