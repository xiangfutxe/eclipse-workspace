package com.pojo;

import java.sql.Timestamp;

public class BankAccount implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Integer id;
	private int uid;
	private String userId;
	private String userName;
	private String accountId;
	private String accountName;
	private String bankName;
	private String bankAdr;
	private Timestamp endTime;
	private Timestamp entryTime;
	private int state;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getId() {
		return id;
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
	public Timestamp getEndTime() {
		return endTime;
	}
	public Timestamp getEntryTime() {
		return entryTime;
	}
	public int getState() {
		return state;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
	}
	public void setState(int state) {
		this.state = state;
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
	public String getBankAdr() {
		return bankAdr;
	}
	public void setBankAdr(String bankAdr) {
		this.bankAdr = bankAdr;
	}

}