package com.pojo;

import java.sql.Timestamp;

public class Order implements java.io.Serializable {

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
	private String comments;
	private int inventoryId;
	private double fee;
	private int uid1;
	private String userId1;
	private int tag1;
	private double price1; 
	private int uid2;
	private String userId2;
	private int tag2;
	private double price2; 
	private int uid3;
	private String userId3;
	private int tag3;
	private double price3; 
	private int rankJoin;
	private int num;
	private double discount;
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
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	public int getUid1() {
		return uid1;
	}
	public void setUid1(int uid1) {
		this.uid1 = uid1;
	}
	public String getUserId1() {
		return userId1;
	}
	public void setUserId1(String userId1) {
		this.userId1 = userId1;
	}
	public int getTag1() {
		return tag1;
	}
	public void setTag1(int tag1) {
		this.tag1 = tag1;
	}
	public double getPrice1() {
		return price1;
	}
	public void setPrice1(double price1) {
		this.price1 = price1;
	}
	public int getUid2() {
		return uid2;
	}
	public void setUid2(int uid2) {
		this.uid2 = uid2;
	}
	public String getUserId2() {
		return userId2;
	}
	public void setUserId2(String userId2) {
		this.userId2 = userId2;
	}
	public int getTag2() {
		return tag2;
	}
	public void setTag2(int tag2) {
		this.tag2 = tag2;
	}
	public double getPrice2() {
		return price2;
	}
	public void setPrice2(double price2) {
		this.price2 = price2;
	}
	public int getUid3() {
		return uid3;
	}
	public void setUid3(int uid3) {
		this.uid3 = uid3;
	}
	public String getUserId3() {
		return userId3;
	}
	public void setUserId3(String userId3) {
		this.userId3 = userId3;
	}
	public int getTag3() {
		return tag3;
	}
	public void setTag3(int tag3) {
		this.tag3 = tag3;
	}
	public double getPrice3() {
		return price3;
	}
	public void setPrice3(double price3) {
		this.price3 = price3;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getRankJoin() {
		return rankJoin;
	}
	public void setRankJoin(int rankJoin) {
		this.rankJoin = rankJoin;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
}