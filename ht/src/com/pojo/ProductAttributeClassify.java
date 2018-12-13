package com.pojo;

import java.sql.Timestamp;

/*
 * 定义可选产品属性值
 */
public class ProductAttributeClassify {
	private int id;
	
	private int attributeId; //属性值 
	
	private String value; //属性值 
	
	private int state;//是否显示
	
private Timestamp entryTime;
	
	private Timestamp endTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public int getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(int attributeId) {
		this.attributeId = attributeId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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
