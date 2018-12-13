package com.pojo;

import java.sql.Timestamp;

public class Product {
	
	private int id;
	
	private String code; //产品代码
	
	private String name; //产品名称
	
	private String type; //产品分类
	
	private String attribute; //产品属性
	
	private double price; //产品单价
	
	private double pv; //产品提点
	
	private double integral;
	
	private String spec;
	
	private int num;//当前库存
	
	private int totalNum;//累计总库存
	
	private String imageUrl1; //产品图片 
	
	private String imageUrl2; //产品图片 
	
	private String imageUrl3; //产品图片 
	
	private String imageUrl4; //产品图片 
	
	private String imageUrl5; //产品图片 
	
	private String description;  //产品描述
	
	private Timestamp entryTime;
	
	private Timestamp endTime;
	
	private int isGroup;
	
	private String belongArea;
	
	private String productType;
	
	private int state;//产品状态，0为删除，1为正常，2为下架

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public String getImageUrl1() {
		return imageUrl1;
	}

	public void setImageUrl1(String imageUrl1) {
		this.imageUrl1 = imageUrl1;
	}

	public String getImageUrl2() {
		return imageUrl2;
	}

	public void setImageUrl2(String imageUrl2) {
		this.imageUrl2 = imageUrl2;
	}

	public String getImageUrl3() {
		return imageUrl3;
	}

	public void setImageUrl3(String imageUrl3) {
		this.imageUrl3 = imageUrl3;
	}

	public String getImageUrl4() {
		return imageUrl4;
	}

	public void setImageUrl4(String imageUrl4) {
		this.imageUrl4 = imageUrl4;
	}

	public String getImageUrl5() {
		return imageUrl5;
	}

	public void setImageUrl5(String imageUrl5) {
		this.imageUrl5 = imageUrl5;
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

	public double getPv() {
		return pv;
	}

	public void setPv(double pv) {
		this.pv = pv;
	}

	public int getIsGroup() {
		return isGroup;
	}

	public void setIsGroup(int isGroup) {
		this.isGroup = isGroup;
	}

	public double getIntegral() {
		return integral;
	}

	public void setIntegral(double integral) {
		this.integral = integral;
	}

	public String getBelongArea() {
		return belongArea;
	}

	public void setBelongArea(String belongArea) {
		this.belongArea = belongArea;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

}


