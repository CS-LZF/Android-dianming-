package com.model;



public class Zuoye implements java.io.Serializable
{
	public String id;

	public String  mingcheng ; 
	public String  miaoshu ; 
	public String  shijian ; 
	public String  kecheng ; 
 
	public Zuoye()
	{
	}

	public String getId()
	{
		return this.id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getMingcheng()
	{
		return this.mingcheng;
	} 
	public void setMingcheng(String mingcheng)
	{
		this.mingcheng = mingcheng;
	} 
	public String getMiaoshu()
	{
		return this.miaoshu;
	} 
	public void setMiaoshu(String miaoshu)
	{
		this.miaoshu = miaoshu;
	} 
	public String getShijian()
	{
		return this.shijian;
	} 
	public void setShijian(String shijian)
	{
		this.shijian = shijian;
	} 
	public String getKecheng()
	{
		return this.kecheng;
	} 
	public void setKecheng(String kecheng)
	{
		this.kecheng = kecheng;
	} 


}