package com.pojo;

import java.sql.Timestamp;

public class ProductTypeToAttributeValue {
	
	private int id;
	
	private int attributeId; //对应的属性值
	
	private String attributeName;
	
	private int valueId;
	
	private String value;
	
	
	private int state;
	
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getValueId() {
		return valueId;
	}

	public void setValueId(int valueId) {
		this.valueId = valueId;
	}

}
