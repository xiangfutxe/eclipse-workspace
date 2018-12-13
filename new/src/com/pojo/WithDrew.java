package com.pojo;

import java.sql.Timestamp;

public class WithDrew implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private int uId;
	private String userId;
	private String userName;
	private String admin;
	private String applyId;
	private double amount;
	private double fee;
	private double actualAmount;
	private String accountId;
	private String accountName;
	private String bankName;
	private String bankAdr;
	private String comments;
	private Timestamp applyTime;
	private Timestamp reviewTime;
	private int state;
	private String centerByCenterId; //服务中心编号
	private String centerByUserId; //服务中心所属会员
	public Integer getId() {
		return id;
	}
	public int getuId() {
		return uId;
	}
	public String getUserId() {
		return userId.toUpperCase();
	}
	public String getUserName() {
		return userName;
	}
	
	public String getApplyId() {
		return applyId;
	}
	public double getAmount() {
		return amount;
	}
	public double getFee() {
		return fee;
	}
	public double getActualAmount() {
		return actualAmount;
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
	public String getBankAdr() {
		return bankAdr;
	}
	public Timestamp getApplyTime() {
		return applyTime;
	}
	public Timestamp getReviewTime() {
		return reviewTime;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public void setUserId(String userId) {
		this.userId = userId.toUpperCase();
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	public void setActualAmount(double actualAmount) {
		this.actualAmount = actualAmount;
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
	public void setBankAdr(String bankAdr) {
		this.bankAdr = bankAdr;
	}
	public void setApplyTime(Timestamp applyTime) {
		this.applyTime = applyTime;
	}
	public void setReviewTime(Timestamp reviewTime) {
		this.reviewTime = reviewTime;
	}
	public String getAdmin() {
		return admin;
	}
	public int getState() {
		return state;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getCenterByCenterId() {
		return centerByCenterId;
	}
	public void setCenterByCenterId(String centerByCenterId) {
		this.centerByCenterId = centerByCenterId;
	}
	public String getCenterByUserId() {
		return centerByUserId;
	}
	public void setCenterByUserId(String centerByUserId) {
		this.centerByUserId = centerByUserId;
	}

	// Constructors


}