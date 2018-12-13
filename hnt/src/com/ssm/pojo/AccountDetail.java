package com.ssm.pojo;

import java.sql.Timestamp;

import com.ssm.utils.StringUtil;

public class AccountDetail {
	private Integer id;
	private Integer uid;
	private String userId;
	private String userName;
	private Double amount;
	private Double balance;
	private Double emoneyBalance;
	private Double dmoneyBalance;
	private Double rmoneyBalance;
	private Double emoneyAdd;
	private Double emoneySub;
	private Double dmoneyAdd;
	private Double dmoneySub;
	private Double rmoneyAdd;
	private Double rmoneySub;
	private Integer payType;
	private String tradeType;
	private String summary;
	private Timestamp entryTime;
	private Timestamp startTime;
	private Timestamp endTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUserId() {
		return StringUtil.notNull(userId).toUpperCase();
	}
	public void setUserId(String userId) {
		this.userId = StringUtil.notNull(userId).toUpperCase();
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public Timestamp getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
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
	public Double getEmoneyBalance() {
		return emoneyBalance;
	}
	public void setEmoneyBalance(Double emoneyBalance) {
		this.emoneyBalance = emoneyBalance;
	}
	public Double getDmoneyBalance() {
		return dmoneyBalance;
	}
	public void setDmoneyBalance(Double dmoneyBalance) {
		this.dmoneyBalance = dmoneyBalance;
	}
	public Double getRmoneyBalance() {
		return rmoneyBalance;
	}
	public void setRmoneyBalance(Double rmoneyBalance) {
		this.rmoneyBalance = rmoneyBalance;
	}
	public Double getEmoneyAdd() {
		return emoneyAdd;
	}
	public void setEmoneyAdd(Double emoneyAdd) {
		this.emoneyAdd = emoneyAdd;
	}
	public Double getEmoneySub() {
		return emoneySub;
	}
	public void setEmoneySub(Double emoneySub) {
		this.emoneySub = emoneySub;
	}
	public Double getDmoneyAdd() {
		return dmoneyAdd;
	}
	public void setDmoneyAdd(Double dmoneyAdd) {
		this.dmoneyAdd = dmoneyAdd;
	}
	public Double getDmoneySub() {
		return dmoneySub;
	}
	public void setDmoneySub(Double dmoneySub) {
		this.dmoneySub = dmoneySub;
	}
	public Double getRmoneyAdd() {
		return rmoneyAdd;
	}
	public void setRmoneyAdd(Double rmoneyAdd) {
		this.rmoneyAdd = rmoneyAdd;
	}
	public Double getRmoneySub() {
		return rmoneySub;
	}
	public void setRmoneySub(Double rmoneySub) {
		this.rmoneySub = rmoneySub;
	}
	
	
}
