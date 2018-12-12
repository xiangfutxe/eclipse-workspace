package com.pojo;

import java.sql.Timestamp;

public class Product {
	
	private int id;
	private String productType;
	private String productName;
	private String specification;
	private double price;
	private String productId;
	private String dType;
	private String branchType;
	private double pv;
	private String imageUrl;
	private double num;
	private double totalNum;
	private int state;
	private String type;
	private double limitNum;
	private String features;
	private Timestamp endTime;
	private Timestamp entryTime;
	private String team;
	private String unit;
	private double totalPrice;
	private double purchase_price_1;
	private double purchase_price_2;
	private double purchase_pv_1;
	private double purchase_pv_2;
	private double purchase_pv;
	private double integral;
	private  double fee;
	private double tag;
	private double oldNum;
	private String attribute;
	private String inventoryOne;
	private String inventoryThree;
	private String inventoryTwo;
	private double preNum;//日均量
	private double prePrice;//日均销售额
	private double numIn1;
	private double numIn2;
	private double numIn3;
	private double numIn4;
	private double numIn5;
	private double totalNumIn;
	private double numOut1;
	private double numOut2;
	private double numOut3;
	private double numOut4;
	private double numOut5;
	private double numOut6;
	private double numOut7;
	private double numOut8;
	private double numOut9;
	private double totalNumOut;
	private int isHide;
	private String supplier;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getProductType() {
		return productType;
	}
	public void setProductType(String string) {
		this.productType = string;
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
	public double getPv() {
		return pv;
	}
	public void setPv(double pv) {
		this.pv = pv;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public double getNum() {
		return num;
	}
	public void setNum(double num) {
		this.num = num;
	}
	public String getFeatures() {
		return features;
	}
	public void setFeatures(String features) {
		this.features = features;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(double totalNum) {
		this.totalNum = totalNum;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public double getPurchase_price_1() {
		return purchase_price_1;
	}
	public void setPurchase_price_1(double purchase_price_1) {
		this.purchase_price_1 = purchase_price_1;
	}
	public double getPurchase_price_2() {
		return purchase_price_2;
	}
	public void setPurchase_price_2(double purchase_price_2) {
		this.purchase_price_2 = purchase_price_2;
	}
	public double getPurchase_pv() {
		return purchase_pv;
	}
	public void setPurchase_pv(double purchase_pv) {
		this.purchase_pv = purchase_pv;
	}
	public double getPurchase_pv_1() {
		return purchase_pv_1;
	}
	public void setPurchase_pv_1(double purchase_pv_1) {
		this.purchase_pv_1 = purchase_pv_1;
	}
	public double getPurchase_pv_2() {
		return purchase_pv_2;
	}
	public void setPurchase_pv_2(double purchase_pv_2) {
		this.purchase_pv_2 = purchase_pv_2;
	}
	public String getdType() {
		return dType;
	}
	public void setdType(String dType) {
		this.dType = dType;
	}
	public double getIntegral() {
		return integral;
	}
	public void setIntegral(double integral) {
		this.integral = integral;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public double getLimitNum() {
		return limitNum;
	}
	public void setLimitNum(double limitNum) {
		this.limitNum = limitNum;
	}
	public double getTag() {
		return tag;
	}
	public void setTag(double tag) {
		this.tag = tag;
	}
	public double getOldNum() {
		return oldNum;
	}
	public void setOldNum(double oldNum) {
		this.oldNum = oldNum;
	}
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public String getInventoryOne() {
		return inventoryOne;
	}
	public void setInventoryOne(String inventoryOne) {
		this.inventoryOne = inventoryOne;
	}
	public String getInventoryThree() {
		return inventoryThree;
	}
	public void setInventoryThree(String inventoryThree) {
		this.inventoryThree = inventoryThree;
	}
	public String getInventoryTwo() {
		return inventoryTwo;
	}
	public void setInventoryTwo(String inventoryTwo) {
		this.inventoryTwo = inventoryTwo;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public double getPreNum() {
		return preNum;
	}
	public void setPreNum(double preNum) {
		this.preNum = preNum;
	}
	public double getPrePrice() {
		return prePrice;
	}
	public void setPrePrice(double prePrice) {
		this.prePrice = prePrice;
	}
	public double getNumIn1() {
		return numIn1;
	}
	public void setNumIn1(double numIn1) {
		this.numIn1 = numIn1;
	}
	public double getNumIn2() {
		return numIn2;
	}
	public void setNumIn2(double numIn2) {
		this.numIn2 = numIn2;
	}
	public double getNumIn3() {
		return numIn3;
	}
	public void setNumIn3(double numIn3) {
		this.numIn3 = numIn3;
	}
	public double getNumIn4() {
		return numIn4;
	}
	public void setNumIn4(double numIn4) {
		this.numIn4 = numIn4;
	}
	public double getNumIn5() {
		return numIn5;
	}
	public void setNumIn5(double numIn5) {
		this.numIn5 = numIn5;
	}
	public double getNumOut1() {
		return numOut1;
	}
	public void setNumOut1(double numOut1) {
		this.numOut1 = numOut1;
	}
	public double getNumOut2() {
		return numOut2;
	}
	public void setNumOut2(double numOut2) {
		this.numOut2 = numOut2;
	}
	public double getNumOut3() {
		return numOut3;
	}
	public void setNumOut3(double numOut3) {
		this.numOut3 = numOut3;
	}
	public double getNumOut4() {
		return numOut4;
	}
	public void setNumOut4(double numOut4) {
		this.numOut4 = numOut4;
	}
	public double getNumOut5() {
		return numOut5;
	}
	public void setNumOut5(double numOut5) {
		this.numOut5 = numOut5;
	}
	public double getTotalNumIn() {
		return totalNumIn;
	}
	public void setTotalNumIn(double totalNumIn) {
		this.totalNumIn = totalNumIn;
	}
	public double getTotalNumOut() {
		return totalNumOut;
	}
	public void setTotalNumOut(double totalNumOut) {
		this.totalNumOut = totalNumOut;
	}
	public int getIsHide() {
		return isHide;
	}
	public void setIsHide(int isHide) {
		this.isHide = isHide;
	}
	public double getNumOut6() {
		return numOut6;
	}
	public void setNumOut6(double numOut6) {
		this.numOut6 = numOut6;
	}
	public double getNumOut7() {
		return numOut7;
	}
	public void setNumOut7(double numOut7) {
		this.numOut7 = numOut7;
	}
	public double getNumOut8() {
		return numOut8;
	}
	public void setNumOut8(double numOut8) {
		this.numOut8 = numOut8;
	}
	public double getNumOut9() {
		return numOut9;
	}
	public void setNumOut9(double numOut9) {
		this.numOut9 = numOut9;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getBranchType() {
		return branchType;
	}
	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}
	
}
