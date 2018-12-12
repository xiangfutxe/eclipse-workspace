package com.pojo;

import java.sql.Timestamp;

public class Order implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
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
	private String userByCenterId; 
	private int orderType; 
	private double price; 
	private double pv;  
	private double price1; 
	private double pv1; 
	private Timestamp reviewerTime; 
	private int state;
	private int tag;
	private String summary;
	private String remark;
	private int centerId;
	private double integral;
	private String comments;
	private int inventoryId;
	private int  deliveryTag;
	private Timestamp diveryTime;
	private int diveryTag;
	private Timestamp diveryConfirmTime;
	private double fee;
	private int modifyTag;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getId() {
		return id;
	}
	public String getOrderId() {
		return orderId;
	}
	public String getUserId() {
		return userId.toUpperCase();
	}
	public String getUserName() {
		return userName;
	}
	public String getAdminByReviewerId() {
		return adminByReviewerId;
	}
	public String getAdminByDeliveryId() {
		return adminByDeliveryId;
	}
	public String getUserByDeclarationId() {
		return userByDeclarationId;
	}
	public String getAddress() {
		return address;
	}
	public String getInventory() {
		return inventory;
	}
	public Timestamp getDeliveryTime() {
		return deliveryTime;
	}
	public String getExpress() {
		return express;
	}
	public String getExpressNum() {
		return expressNum;
	}
	public Timestamp getOrderTime() {
		return orderTime;
	}
	public int getOrderType() {
		return orderType;
	}
	public double getPrice() {
		return price;
	}
	public double getPv() {
		return pv;
	}
	public Timestamp getReviewerTime() {
		return reviewerTime;
	}
	public int getState() {
		return state;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public void setUserId(String userId) {
		this.userId = userId.toUpperCase();
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setAdminByReviewerId(String adminByReviewerId) {
		this.adminByReviewerId = adminByReviewerId;
	}
	public void setAdminByDeliveryId(String adminByDeliveryId) {
		this.adminByDeliveryId = adminByDeliveryId;
	}
	public void setUserByDeclarationId(String userByDeclarationId) {
		this.userByDeclarationId = userByDeclarationId;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setInventory(String inventory) {
		this.inventory = inventory;
	}
	public void setDeliveryTime(Timestamp deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	public void setExpress(String express) {
		this.express = express;
	}
	public void setExpressNum(String expressNum) {
		this.expressNum = expressNum;
	}
	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}
	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setPv(double pv) {
		this.pv = pv;
	}
	public void setReviewerTime(Timestamp reviewerTime) {
		this.reviewerTime = reviewerTime;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getReceiver() {
		return receiver;
	}
	public String getPhone() {
		return phone;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public Timestamp getConfirmTime() {
		return confirmTime;
	}
	public void setConfirmTime(Timestamp confirmTime) {
		this.confirmTime = confirmTime;
	}
	public String getAdminByConfirmId() {
		return adminByConfirmId;
	}
	public void setAdminByConfirmId(String adminByConfirmId) {
		this.adminByConfirmId = adminByConfirmId;
	}
	public String getUserByCenterId() {
		return userByCenterId;
	}
	public void setUserByCenterId(String userByCenterId) {
		this.userByCenterId = userByCenterId;
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
	public int getCenterId() {
		return centerId;
	}
	public void setCenterId(int centerId) {
		this.centerId = centerId;
	}
	public double getIntegral() {
		return integral;
	}
	public void setIntegral(double integral) {
		this.integral = integral;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}   
	public int getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}
	public int getDeliveryTag() {
		return deliveryTag;
	}
	public void setDeliveryTag(int deliveryTag) {
		this.deliveryTag = deliveryTag;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	public Timestamp getDiveryTime() {
		return diveryTime;
	}
	public void setDiveryTime(Timestamp diveryTime) {
		this.diveryTime = diveryTime;
	}
	public int getDiveryTag() {
		return diveryTag;
	}
	public void setDiveryTag(int diveryTag) {
		this.diveryTag = diveryTag;
	}
	public Timestamp getDiveryConfirmTime() {
		return diveryConfirmTime;
	}
	public void setDiveryConfirmTime(Timestamp diveryConfirmTime) {
		this.diveryConfirmTime = diveryConfirmTime;
	}
	public int getModifyTag() {
		return modifyTag;
	}
	public void setModifyTag(int modifyTag) {
		this.modifyTag = modifyTag;
	}

}