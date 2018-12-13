package com.pojo;

import java.sql.Timestamp;

public class ProductTypeToAttribute {
	
	private int id;
	
	private int typeId; // 产品分类（唯一，一个产品分类对应一个属性结果集）
	
	private int attributeId; //对应的属性值
	
	private String attributeName;
	
	private int state;
	
	private Timestamp entryTime;
	
	private Timestamp endTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}


	public int getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(int attributeId) {
		this.attributeId = attributeId;
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

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	

}
