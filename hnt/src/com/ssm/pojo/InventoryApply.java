package com.ssm.pojo;

import java.sql.Timestamp;

/**
 * JmkInventoryApply entity. @author MyEclipse Persistence Tools
 */
public class InventoryApply implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String adminByOperatorId;
	private String adminByReviewerId;
	private String inventory;
	private String applyId;
	private Timestamp reviewTime;
	private Integer state;
	private Timestamp time;
	private Integer type;
	private Integer payType;
	private Timestamp startTime;
	private Timestamp endTime;

	// Constructors
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAdminByOperatorId() {
		return adminByOperatorId;
	}
	public void setAdminByOperatorId(String adminByOperatorId) {
		this.adminByOperatorId = adminByOperatorId;
	}
	public String getAdminByReviewerId() {
		return adminByReviewerId;
	}
	public void setAdminByReviewerId(String adminByReviewerId) {
		this.adminByReviewerId = adminByReviewerId;
	}
	public String getInventory() {
		return inventory;
	}
	public void setInventory(String inventory) {
		this.inventory = inventory;
	}
	public String getApplyId() {
		return applyId;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
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
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
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
	
}