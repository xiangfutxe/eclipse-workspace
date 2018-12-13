package com.pojo;

import java.sql.Timestamp;

public class AccountDetail {
	private Integer id;
	private String userId;
	private String userName;
	private double amount;
	private double balance;
	private String payType;
	private String tradeType;
	private String summary;
	private Timestamp time;
	public Integer getId() {
		return id;
	}
	public String getUserId() {
		return userId.toUpperCase();
	}
	public String getUserName() {
		return userName;
	}
	public double getAmount() {
		return amount;
	}
	public double getBalance() {
		return balance;
	}
	public String getPayType() {
		return payType;
	}
	public String getTradeType() {
		return tradeType;
	}
	public String getSummary() {
		return summary;
	}
	public Timestamp getTime() {
		return time;
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
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
}
