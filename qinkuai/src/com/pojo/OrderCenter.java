package com.pojo;

import java.sql.Timestamp;

public class OrderCenter implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private int cId;
	private String orderId; 
	private String centerName;  
	private String centerId;
	private String adminByReviewId; 
	private String adminByDeliveryId;
	private String adminByConfirmId;
	private Timestamp confirmTime; 
	private String receiver;
	private String phone;
	private String address;
	private Timestamp deliveryTime; 
	private String express;
	private String expressNum;
	private Timestamp orderTime;
	private int orderType; 
	private double price; 
	private double pv;  
	private double price1; 
	private double pv1; 
	private int diveryTag;
	private int tag;
	private Timestamp reviewTime; 
	private String adminByReviewerId; 
	private String summary;
	private String remark;
	private int state;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	public String getCenterId() {
		return centerId;
	}
	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}
	public String getAdminByReviewId() {
		return adminByReviewId;
	}
	public void setAdminByReviewId(String adminByReviewId) {
		this.adminByReviewId = adminByReviewId;
	}
	public String getAdminByDeliveryId() {
		return adminByDeliveryId;
	}
	public void setAdminByDeliveryId(String adminByDeliveryId) {
		this.adminByDeliveryId = adminByDeliveryId;
	}
	public String getAdminByConfirmId() {
		return adminByConfirmId;
	}
	public void setAdminByConfirmId(String adminByConfirmId) {
		this.adminByConfirmId = adminByConfirmId;
	}
	public Timestamp getConfirmTime() {
		return confirmTime;
	}
	public void setConfirmTime(Timestamp confirmTime) {
		this.confirmTime = confirmTime;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Timestamp getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(Timestamp deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	public String getExpress() {
		return express;
	}
	public void setExpress(String express) {
		this.express = express;
	}
	public String getExpressNum() {
		return expressNum;
	}
	public void setExpressNum(String expressNum) {
		this.expressNum = expressNum;
	}
	public Timestamp getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}
	public int getOrderType() {
		return orderType;
	}
	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getPv() {
		return pv;
	}
	public void setPv(double pv) {
		this.pv = pv;
	}
	public double getPrice1() {
		return price1;
	}
	public void setPrice1(double price1) {
		this.price1 = price1;
	}
	public double getPv1() {
		return pv1;
	}
	public void setPv1(double pv1) {
		this.pv1 = pv1;
	}
	public int getDiveryTag() {
		return diveryTag;
	}
	public void setDiveryTag(int diveryTag) {
		this.diveryTag = diveryTag;
	}
	public int getTag() {
		return tag;
	}
	public void setTag(int tag) {
		this.tag = tag;
	}
	public Timestamp getReviewTime() {
		return reviewTime;
	}
	public void setReviewTime(Timestamp reviewTime) {
		this.reviewTime = reviewTime;
	}
	public String getAdminByReviewerId() {
		return adminByReviewerId;
	}
	public void setAdminByReviewerId(String adminByReviewerId) {
		this.adminByReviewerId = adminByReviewerId;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}