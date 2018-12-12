package com.pojo;

import java.sql.Timestamp;


public class Settle implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private double totalPerformance;
	private double newPerformance; 
	private double newPerformance_jc; 
	private double totalPrice;
	private double newPrice; 
	private double totalPmoney;
	private double newPmoney; 
	private int totalNum;
	private int newNum; 
	private int tag; 
	private int weekTag; 
	private Timestamp startTime; 
	private Timestamp endTime; 
	private String state;
	private double pmoney;
	private double totalPerformance1;
	private double newPerformance1; 
	private double totalPrice1;
	private double newPrice1; 
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getId() {
		return id;
	}
	public double getTotalPerformance() {
		return totalPerformance;
	}
	public double getNewPerformance() {
		return newPerformance;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public double getNewPrice() {
		return newPrice;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public int getNewNum() {
		return newNum;
	}
	public int getTag() {
		return tag;
	}
	public int getWeekTag() {
		return weekTag;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public String getState() {
		return state;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setTotalPerformance(double totalPerformance) {
		this.totalPerformance = totalPerformance;
	}
	public void setNewPerformance(double newPerformance) {
		this.newPerformance = newPerformance;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public void setNewPrice(double newPrice) {
		this.newPrice = newPrice;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public void setNewNum(int newNum) {
		this.newNum = newNum;
	}
	public void setTag(int tag) {
		this.tag = tag;
	}
	public void setWeekTag(int weekTag) {
		this.weekTag = weekTag;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public void setState(String state) {
		this.state = state;
	}
	public double getNewPerformance_jc() {
		return newPerformance_jc;
	}
	public void setNewPerformance_jc(double newPerformance_jc) {
		this.newPerformance_jc = newPerformance_jc;
	}
	public double getTotalPmoney() {
		return totalPmoney;
	}
	public void setTotalPmoney(double totalPmoney) {
		this.totalPmoney = totalPmoney;
	}
	public double getNewPmoney() {
		return newPmoney;
	}
	public void setNewPmoney(double newPmoney) {
		this.newPmoney = newPmoney;
	}
	public double getPmoney() {
		return pmoney;
	}
	public void setPmoney(double pmoney) {
		this.pmoney = pmoney;
	}
	public double getTotalPerformance1() {
		return totalPerformance1;
	}
	public void setTotalPerformance1(double totalPerformance1) {
		this.totalPerformance1 = totalPerformance1;
	}
	public double getNewPerformance1() {
		return newPerformance1;
	}
	public void setNewPerformance1(double newPerformance1) {
		this.newPerformance1 = newPerformance1;
	}
	public double getTotalPrice1() {
		return totalPrice1;
	}
	public void setTotalPrice1(double totalPrice1) {
		this.totalPrice1 = totalPrice1;
	}
	public double getNewPrice1() {
		return newPrice1;
	}
	public void setNewPrice1(double newPrice1) {
		this.newPrice1 = newPrice1;
	}


}