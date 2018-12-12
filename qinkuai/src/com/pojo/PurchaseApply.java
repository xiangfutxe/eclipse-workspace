package com.pojo;

import java.sql.Timestamp;

/**
 * JmkInventoryApply entity. @author MyEclipse Persistence Tools
 */
public class PurchaseApply implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String operatorId;
	private String reviewId;
	private String applyId;
	private int state;
	private int supplierId;
	private String supplierCode;
	private String supplierName;
	private String remark;
	private Timestamp operatorTime;
	private Timestamp ReviewTime;
	private Timestamp EntryTime;
	private Timestamp EndTime;
	private String docNum;
	private double price;
	private double num;
	private double totalNum;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public String getReviewId() {
		return reviewId;
	}
	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}
	public String getApplyId() {
		return applyId;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Timestamp getOperatorTime() {
		return operatorTime;
	}
	public void setOperatorTime(Timestamp operatorTime) {
		this.operatorTime = operatorTime;
	}
	public Timestamp getReviewTime() {
		return ReviewTime;
	}
	public void setReviewTime(Timestamp reviewTime) {
		ReviewTime = reviewTime;
	}
	public Timestamp getEntryTime() {
		return EntryTime;
	}
	public void setEntryTime(Timestamp entryTime) {
		EntryTime = entryTime;
	}
	public Timestamp getEndTime() {
		return EndTime;
	}
	public void setEndTime(Timestamp endTime) {
		EndTime = endTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public double getNum() {
		return num;
	}
	public void setNum(double num) {
		this.num = num;
	}
	public double getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(double totalNum) {
		this.totalNum = totalNum;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDocNum() {
		return docNum;
	}
	public void setDocNum(String docNum) {
		this.docNum = docNum;
	}
	
}