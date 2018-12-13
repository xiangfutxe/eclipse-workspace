package com.pojo;

import java.sql.Timestamp;

public class Product {
	
	private int id;
	private String productName;
	private String specification;
	private String productId;
	private String imageUrl;
	private int	 num;
	private int totalNum;
	private String state;
	private int type;
	private String features;
	private double price;
	private double price1;
	private double price2;
	private double price3;
	private  double fee;
	private double award;
	private double productType;
	private Timestamp endTime;
	private Timestamp entryTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getFeatures() {
		return features;
	}
	public void setFeatures(String features) {
		this.features = features;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getPrice1() {
		return price1;
	}
	public void setPrice1(double price1) {
		this.price1 = price1;
	}
	public double getPrice2() {
		return price2;
	}
	public void setPrice2(double price2) {
		this.price2 = price2;
	}
	public double getPrice3() {
		return price3;
	}
	public void setPrice3(double price3) {
		this.price3 = price3;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public Timestamp getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
	}
	public double getAward() {
		return award;
	}
	public void setAward(double award) {
		this.award = award;
	}
	public double getProductType() {
		return productType;
	}
	public void setProductType(double productType) {
		this.productType = productType;
	}

}
