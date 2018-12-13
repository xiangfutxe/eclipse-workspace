package com.ssm.pojo;

import java.sql.Timestamp;

/**
 * JmkInventoryApply entity. @author MyEclipse Persistence Tools
 */
public class InventoryReturnApply implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String applyId;
	private String orderId;
	private String reviewer;
	private String inventory;
	private Timestamp orderTime;
	private Timestamp reviewTime;
	private Integer state;
	private Timestamp entryTime;
	private Integer type;
	private Integer payType;
	private String userId;  
	private String userName;  
	private Integer orderType; 
	private Double price;
	private Double pv;
	// Constructors
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getApplyId() {
		return applyId;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getReviewer() {
		return reviewer;
	}
	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}
	public String getInventory() {
		return inventory;
	}
	public void setInventory(String inventory) {
		this.inventory = inventory;
	}
	public Timestamp getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}
	public Timestamp getReviewTime() {
		return reviewTime;
	}
	public void setReviewTime(Timestamp reviewTime) {
		this.reviewTime = reviewTime;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Timestamp getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
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
	public Double getPv() {
		return pv;
	}
	public void setPv(Double pv) {
		this.pv = pv;
	}
	
}