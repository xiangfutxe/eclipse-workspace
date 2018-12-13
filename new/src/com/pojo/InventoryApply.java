package com.pojo;

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
	private int inventoryId;
	private String applyId;
	private Timestamp reviewTime;
	private int state;
	private Timestamp time;
	private int type;
	// Constructors
	public Integer getId() {
		return id;
	}
	public String getAdminByOperatorId() {
		return adminByOperatorId;
	}
	public String getAdminByReviewerId() {
		return adminByReviewerId;
	}
	public String getInventory() {
		return inventory;
	}
	public String getApplyId() {
		return applyId;
	}
	public Timestamp getReviewTime() {
		return reviewTime;
	}
	
	public Timestamp getTime() {
		return time;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public void setAdminByOperatorId(String adminByOperatorId) {
		this.adminByOperatorId = adminByOperatorId;
	}
	public void setAdminByReviewerId(String adminByReviewerId) {
		this.adminByReviewerId = adminByReviewerId;
	}
	public void setInventory(String inventory) {
		this.inventory = inventory;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	public void setReviewTime(Timestamp reviewTime) {
		this.reviewTime = reviewTime;
	}
	
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public int getState() {
		return state;
	}
	public int getType() {
		return type;
	}
	public void setState(int state) {
		this.state = state;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}
	
}