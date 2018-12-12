package com.pojo;

import java.sql.Timestamp;

public class InventoryProductStock {
	
	private int id;
	private int setId;
	private int pid;
	private String productType;
	private String productName;
	private String specification;
	private double price;
	private String productId;
	private double startNum;
	private double sysNum;
	private double curNum;
	private double inNum;
	private double outNum;
	private int state;
	private Timestamp startTime;
	private Timestamp endTime;
	private Timestamp entryTime;
	private String unit;
	private int weekTag;
	private String inventoryOne;
	private String inventoryTwo;
	private String inventoryThree;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public double getStartNum() {
		return startNum;
	}
	public void setStartNum(double startNum) {
		this.startNum = startNum;
	}
	public double getSysNum() {
		return sysNum;
	}
	public void setSysNum(double sysNum) {
		this.sysNum = sysNum;
	}
	public double getCurNum() {
		return curNum;
	}
	public void setCurNum(double curNum) {
		this.curNum = curNum;
	}
	public double getInNum() {
		return inNum;
	}
	public void setInNum(double inNum) {
		this.inNum = inNum;
	}
	public double getOutNum() {
		return outNum;
	}
	public void setOutNum(double outNum) {
		this.outNum = outNum;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
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
	public Timestamp getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public int getSetId() {
		return setId;
	}
	public void setSetId(int setId) {
		this.setId = setId;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getWeekTag() {
		return weekTag;
	}
	public void setWeekTag(int weekTag) {
		this.weekTag = weekTag;
	}
	public String getInventoryOne() {
		return inventoryOne;
	}
	public void setInventoryOne(String inventoryOne) {
		this.inventoryOne = inventoryOne;
	}
	public String getInventoryTwo() {
		return inventoryTwo;
	}
	public void setInventoryTwo(String inventoryTwo) {
		this.inventoryTwo = inventoryTwo;
	}
	public String getInventoryThree() {
		return inventoryThree;
	}
	public void setInventoryThree(String inventoryThree) {
		this.inventoryThree = inventoryThree;
	}
	
}
