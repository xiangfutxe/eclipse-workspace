package com.pojo;

import java.sql.Timestamp;


public class OrderDetail implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int oid;
	private String orderId;
	private String productId;
	private String productName;
	private String specification;
	private double productPrice;
	private double productPv;
	private double productFee;
	private String productType;
	private int type;
	private double price;
	private double pv;
	private double fee;
	private double num;
	private double integral;
	private String imageUrl;
	private String unit;
	private Timestamp entryTime;
	private int pid;
	
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getId() {
		return id;
	}
	public String getOrderId() {
		return orderId;
	}
	public String getProductId() {
		return productId;
	}
	public String getProductName() {
		return productName;
	}
	public String getSpecification() {
		return specification;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public double getProductPv() {
		return productPv;
	}
	public double getPrice() {
		return price;
	}
	public double getPv() {
		return pv;
	}
	public double getNum() {
		return num;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public void setProductPv(double productPv) {
		this.productPv = productPv;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setPv(double pv) {
		this.pv = pv;
	}
	public void setNum(double num) {
		this.num = num;
	}
	public String getProductType() {
		return productType;
	}
	public int getType() {
		return type;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public void setType(int type) {
		this.type = type;
	}
	public double getIntegral() {
		return integral;
	}
	public void setIntegral(double integral) {
		this.integral = integral;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	public double getProductFee() {
		return productFee;
	}
	public void setProductFee(double productFee) {
		this.productFee = productFee;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public Timestamp getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
	}
	

	

}
