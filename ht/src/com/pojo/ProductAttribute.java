package com.pojo;

import java.sql.Timestamp;

/*
 * 产品属性
 */
public class ProductAttribute {
	private int id;
	
	private String name; //属性名称
	
	private int isClassify; //是否定义可选属性值
	
	private int state; //状态（0为不可使用，1为可使用)
	
private Timestamp entryTime;
	
	private Timestamp endTime;

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

	public int getIsClassify() {
		return isClassify;
	}

	public void setIsClassify(int isClassify) {
		this.isClassify = isClassify;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Timestamp getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	
	
}
