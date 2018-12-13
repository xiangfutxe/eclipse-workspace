package com.pojo;

import java.sql.Timestamp;

public class OrderDelivery implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private int uId;
	private String orderId; 
	private String userId;  
	private String userName;  
	private String adminByConfirmId;
	private String adminByReviewerId; 
	private String adminByDeliveryId;
	private String userByDeclarationId; 
	private String receiver;
	private String phone;
	private String address;
	private String inventory; 
	private Timestamp confirmTime; 
	private Timestamp deliveryTime; 
	private String express;
	private String expressNum;
	private Timestamp orderTime;
	private int orderType; 
	private double price; 
	private double pv;  
	private Timestamp reviewerTime; 
	private int state;
	private int tag;
	private String summary;
	private String remark;
	private int inventoryId;
	private double fee;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAdminByConfirmId() {
		return adminByConfirmId;
	}
	public void setAdminByConfirmId(String adminByConfirmId) {
		this.adminByConfirmId = adminByConfirmId;
	}
	public String getAdminByReviewerId() {
		return adminByReviewerId;
	}
	public void setAdminByReviewerId(String adminByReviewerId) {
		this.adminByReviewerId = adminByReviewerId;
	}
	public String getAdminByDeliveryId() {
		return adminByDeliveryId;
	}
	public void setAdminByDeliveryId(String adminByDeliveryId) {
		this.adminByDeliveryId = adminByDeliveryId;
	}
	public String getUserByDeclarationId() {
		return userByDeclarationId;
	}
	public void setUserByDeclarationId(String userByDeclarationId) {
		this.userByDeclarationId = userByDeclarationId;
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
	public String getInventory() {
		return inventory;
	}
	public void setInventory(String inventory) {
		this.inventory = inventory;
	}
	public Timestamp getConfirmTime() {
		return confirmTime;
	}
	public void setConfirmTime(Timestamp confirmTime) {
		this.confirmTime = confirmTime;
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
	public Timestamp getReviewerTime() {
		return reviewerTime;
	}
	public void setReviewerTime(Timestamp reviewerTime) {
		this.reviewerTime = reviewerTime;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getTag() {
		return tag;
	}
	public void setTag(int tag) {
		this.tag = tag;
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
	public int getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	

}