package com.pojo;

import java.sql.Timestamp;

public class ChargeApply implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String userId;
	private String userName;
	private String admin;
	private String accountId;
	private String accountName;
	private String bankName;
	private String applyId;
	private double amount;
	private Timestamp applyTime;
	private String remark;
	private int type;
	private Timestamp reviewTime;
	private int state;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getId() {
		return id;
	}
	public String getUserId() {
		return userId.toUpperCase();
	}
	public String getUserName() {
		return userName;
	}
	public String getAdmin() {
		return admin;
	}
	public String getAccountId() {
		return accountId;
	}
	public String getAccountName() {
		return accountName;
	}
	public String getBankName() {
		return bankName;
	}
	public String getApplyId() {
		return applyId;
	}
	public double getAmount() {
		return amount;
	}
	public Timestamp getApplyTime() {
		return applyTime;
	}
	public String getRemark() {
		return remark;
	}
	public int getType() {
		return type;
	}
	public Timestamp getReviewTime() {
		return reviewTime;
	}
	public int getState() {
		return state;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setUserId(String userId) {
		this.userId = userId.toUpperCase();
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public void setApplyTime(Timestamp applyTime) {
		this.applyTime = applyTime;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void setReviewTime(Timestamp reviewTime) {
		this.reviewTime = reviewTime;
	}
	public void setState(int state) {
		this.state = state;
	}


}