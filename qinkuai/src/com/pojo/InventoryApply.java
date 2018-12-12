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
	private String operatorId;
	private String checkId;
	private String confirmId;
	private String reviewId;
	private String inventory;
	private int inventoryId;
	private String applyId;
	private Timestamp checkTime;
	private Timestamp confirmTime;
	private Timestamp reviewTime;
	private String approveId;
	private Timestamp approveTime;
	private int state;
	private Timestamp time;
	private Timestamp EntryTime;
	private int type;
	private int payType;
	private String docNum;
	private int maxNum;
	private int supplierId;
	private String supplierCode;
	private String supplierName;
	private String remark;

	// Constructors
	public Integer getId() {
		return id;
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
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public String getCheckId() {
		return checkId;
	}
	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}
	public String getConfirmId() {
		return confirmId;
	}
	public void setConfirmId(String confirmId) {
		this.confirmId = confirmId;
	}
	public String getReviewId() {
		return reviewId;
	}
	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}
	public Timestamp getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Timestamp checkTime) {
		this.checkTime = checkTime;
	}
	public Timestamp getConfirmTime() {
		return confirmTime;
	}
	public void setConfirmTime(Timestamp confirmTime) {
		this.confirmTime = confirmTime;
	}
	public Timestamp getEntryTime() {
		return EntryTime;
	}
	public void setEntryTime(Timestamp entryTime) {
		EntryTime = entryTime;
	}
	public String getDocNum() {
		return docNum;
	}
	public void setDocNum(String docNum) {
		this.docNum = docNum;
	}
	public int getMaxNum() {
		return maxNum;
	}
	public void setMaxNum(int maxNum) {
		this.maxNum = maxNum;
	}
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public String getApproveId() {
		return approveId;
	}

	public void setApproveId(String approveId) {
		this.approveId = approveId;
	}

	public Timestamp getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(Timestamp approveTime) {
		this.approveTime = approveTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}