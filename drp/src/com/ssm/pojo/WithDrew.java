package com.ssm.pojo;

import java.sql.Timestamp;

public class WithDrew implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer uId;
	private String userId;
	private String userName;
	private String admin;
	private String applyId;
	private Double amount;
	private Double fee;
	private Double actualAmount;
	private String accountId;
	private String accountName;
	private String bankName;
	private String bankAdr;
	private Timestamp applyTime;
	private Timestamp reviewTime;
	private Timestamp startTime;
	private Timestamp endTime;
	private Integer state;
	private String comments;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getuId() {
		return uId;
	}
	public void setuId(Integer uId) {
		this.uId = uId;
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
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public String getApplyId() {
		return applyId;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getFee() {
		return fee;
	}
	public void setFee(Double fee) {
		this.fee = fee;
	}
	public Double getActualAmount() {
		return actualAmount;
	}
	public void setActualAmount(Double actualAmount) {
		this.actualAmount = actualAmount;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankAdr() {
		return bankAdr;
	}
	public void setBankAdr(String bankAdr) {
		this.bankAdr = bankAdr;
	}
	public Timestamp getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Timestamp applyTime) {
		this.applyTime = applyTime;
	}
	public Timestamp getReviewTime() {
		return reviewTime;
	}
	public void setReviewTime(Timestamp reviewTime) {
		this.reviewTime = reviewTime;
	}
	
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	

}