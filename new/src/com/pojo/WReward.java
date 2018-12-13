package com.pojo;

import java.sql.Timestamp;


public class WReward implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private int uid;
	private String userId;
	private String userName;
	private double amount;
	private int userById;
	private String userByUserId;
	private String userByUserName;
	private int type; 
	private Timestamp startTime;
	private Timestamp endTime;
	private Timestamp entryTime; 
	private double amount1;
	private double amount2;
	private double amount3;
	private double amount4;
	private double totalAmount;
	private double tax;
	private int state;
	private String remark;
	private int tag;
	private String accountName;
	private String accountId;
	private String bankName;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
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
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getUserById() {
		return userById;
	}
	public void setUserById(int userById) {
		this.userById = userById;
	}
	public String getUserByUserId() {
		return userByUserId;
	}
	public void setUserByUserId(String userByUserId) {
		this.userByUserId = userByUserId;
	}
	public String getUserByUserName() {
		return userByUserName;
	}
	public void setUserByUserName(String userByUserName) {
		this.userByUserName = userByUserName;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public Timestamp getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public double getAmount1() {
		return amount1;
	}
	public void setAmount1(double amount1) {
		this.amount1 = amount1;
	}
	public double getAmount2() {
		return amount2;
	}
	public void setAmount2(double amount2) {
		this.amount2 = amount2;
	}
	public int getTag() {
		return tag;
	}
	public void setTag(int tag) {
		this.tag = tag;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public double getAmount3() {
		return amount3;
	}
	public void setAmount3(double amount3) {
		this.amount3 = amount3;
	}
	public double getAmount4() {
		return amount4;
	}
	public void setAmount4(double amount4) {
		this.amount4 = amount4;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
}