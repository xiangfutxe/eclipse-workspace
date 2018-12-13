package com.ssm.pojo;

import java.sql.Timestamp;

import com.ssm.utils.StringUtil;

public class ChuangYe implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private int uid;
	private String userId;
	private String userName;
	private int uById;
	private String userById;
	private String userByName;
	private double amount;
	private double amount1;
	private double amount2;
	private double amount3;
	private Timestamp entryTime;
	private int state;
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
	public int getuById() {
		return uById;
	}
	public void setuById(int uById) {
		this.uById = uById;
	}
	public String getUserById() {
		return userById;
	}
	public void setUserById(String userById) {
		this.userById = userById;
	}
	public String getUserByName() {
		return userByName;
	}
	public void setUserByName(String userByName) {
		this.userByName = userByName;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
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
	public double getAmount3() {
		return amount3;
	}
	public void setAmount3(double amount3) {
		this.amount3 = amount3;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}