package com.ssm.pojo;

import java.sql.Timestamp;

public class Inventory {
	
	private Integer id;
	private String inventoryName;
	private String address;
	private String phone;
	private String linkman;
	private String state;
	private Timestamp endTime;
	private Timestamp entryTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getInventoryName() {
		return inventoryName;
	}
	public void setInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
	
	

}
