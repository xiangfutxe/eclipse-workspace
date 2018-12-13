package com.pojo;

import java.sql.Timestamp;


public class Settle implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private double newPrice;

	private double totalPrice;
	private double newAward;
	private double totalAward; 
	private int weekTag; 
	private Timestamp startTime; 
	private Timestamp endTime; 
	private Timestamp entryTime; 

	private int state;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getNewPrice() {
		return newPrice;
	}

	public void setNewPrice(double newPrice) {
		this.newPrice = newPrice;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public double getNewAward() {
		return newAward;
	}

	public void setNewAward(double newAward) {
		this.newAward = newAward;
	}

	public double getTotalAward() {
		return totalAward;
	}

	public void setTotalAward(double totalAward) {
		this.totalAward = totalAward;
	}

	public int getWeekTag() {
		return weekTag;
	}

	public void setWeekTag(int weekTag) {
		this.weekTag = weekTag;
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Timestamp getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
	}
	
}
	