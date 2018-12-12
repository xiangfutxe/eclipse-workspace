package com.ssm.pojo;

import java.sql.Timestamp;

import com.ssm.utils.StringUtil;

public class Order implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer uid;
	private String orderId; 
	private String userId;  
	private String userName;  
	private String reviewerId; 
	private Timestamp orderTime;
	private Integer orderType; 
	private Double price; 
	private Double cash; 
	private Integer cashNum;    
	private Double integral;    
	private Double retailPrice;   
	private Integer refereeId;  
	private Double giveCash; 
	private Integer giveCashNum;    
	private Double giveIntegral;    
	private Timestamp reviewTime; 
	private Integer rank;
	private Integer state;
	private String imageUrl;
	private Integer payType;
	private String province;
	private String city;
	private String area;
	private String address;
	private Timestamp startTime;
	private Timestamp endTime;
	private String msg;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
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
	public String getReviewerId() {
		return reviewerId;
	}
	public void setReviewerId(String reviewerId) {
		this.reviewerId = reviewerId;
	}
	public Timestamp getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getCash() {
		return cash;
	}
	public void setCash(Double cash) {
		this.cash = cash;
	}
	public Integer getCashNum() {
		return cashNum;
	}
	public void setCashNum(Integer cashNum) {
		this.cashNum = cashNum;
	}
	public Double getIntegral() {
		return integral;
	}
	public void setIntegral(Double integral) {
		this.integral = integral;
	}
	public Double getRetailPrice() {
		return retailPrice;
	}
	public void setRetailPrice(Double retailPrice) {
		this.retailPrice = retailPrice;
	}
	public Timestamp getReviewTime() {
		return reviewTime;
	}
	public void setReviewTime(Timestamp reviewTime) {
		this.reviewTime = reviewTime;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
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
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Integer getRefereeId() {
		return refereeId;
	}
	public void setRefereeId(Integer refereeId) {
		this.refereeId = refereeId;
	}
	public Double getGiveCash() {
		return giveCash;
	}
	public void setGiveCash(Double giveCash) {
		this.giveCash = giveCash;
	}
	public Integer getGiveCashNum() {
		return giveCashNum;
	}
	public void setGiveCashNum(Integer giveCashNum) {
		this.giveCashNum = giveCashNum;
	}
	public Double getGiveIntegral() {
		return giveIntegral;
	}
	public void setGiveIntegral(Double giveIntegral) {
		this.giveIntegral = giveIntegral;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}