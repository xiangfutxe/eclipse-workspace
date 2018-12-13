package com.pojo;

import java.sql.Timestamp;

public class ProductStock {
	
	private int id;
	
	private String productId; //产品代码
	
	private String productName; //产品名称
	
	private int num;//当前库存
	
	private int tolNum;//累计已发库存
	
	private String attribute;  //产品描述
	
	private Timestamp entryTime;
	
	private Timestamp endTime;
	
	private int state;//产品状态，0为删除，1为正常，2为下架

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getTolNum() {
		return tolNum;
	}

	public void setTolNum(int tolNum) {
		this.tolNum = tolNum;
	}

}


