package com.ssm.pojo;

import java.sql.Timestamp;

import com.ssm.utils.StringUtil;

public class Order implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer uId;
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
	private Integer orderType; 
	private Double price; 
	private Double pv;      
	private Timestamp reviewerTime; 
	private Integer state;
	private Integer centerId;
	private String userByCenterId;
	private Double priceCy;
	private Double pvCy;
	private Integer totalNum;
	private Integer deliveryNum;
	private Integer dTag;
	private Integer invId;
	private Timestamp startTime;
	private Timestamp endTime;
	private Double discount;
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
		return StringUtil.notNull(userId).toUpperCase();
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
	public Integer getOrderType() {
		return orderType;
	}
	public Double getPrice() {
		return price;
	}
	public Double getPv() {
		return pv;
	}
	public Timestamp getReviewerTime() {
		return reviewerTime;
	}
	public Integer getState() {
		return state;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public void setUserId(String userId) {
		this.userId = StringUtil.notNull(userId).toUpperCase();
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
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public void setPv(Double pv) {
		this.pv = pv;
	}
	public void setReviewerTime(Timestamp reviewerTime) {
		this.reviewerTime = reviewerTime;
	}
	public void setState(Integer state) {
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
	public Integer getuId() {
		return uId;
	}
	public void setuId(Integer uId) {
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
	public Integer getCenterId() {
		return centerId;
	}
	public void setCenterId(Integer centerId) {
		this.centerId = centerId;
	}
	public String getUserByCenterId() {
		return StringUtil.notNull(userByCenterId).toUpperCase();
	}
	public void setUserByCenterId(String userByCenterId) {
		this.userByCenterId = StringUtil.notNull(userByCenterId).toUpperCase();
	}
	public Double getPriceCy() {
		return priceCy;
	}
	public void setPriceCy(Double priceCy) {
		this.priceCy = priceCy;
	}
	public Double getPvCy() {
		return pvCy;
	}
	public void setPvCy(Double pvCy) {
		this.pvCy = pvCy;
	}
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public Integer getDeliveryNum() {
		return deliveryNum;
	}
	public void setDeliveryNum(Integer deliveryNum) {
		this.deliveryNum = deliveryNum;
	}
	public Integer getdTag() {
		return dTag;
	}
	public void setdTag(Integer dTag) {
		this.dTag = dTag;
	}
	public int getInvId() {
		return invId;
	}
	public void setInvId(Integer invId) {
		this.invId = invId;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}   
	
}