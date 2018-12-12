package com.pojo;

import java.sql.Timestamp;

/**
 * JmkInventoryDetail entity. @author MyEclipse Persistence Tools
 */

public class InventoryDetail implements java.io.Serializable {

	// Fields
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String applyId;
	private int pid;
	private String productId;
	private String productName;
	private String productType;
	private String specification;
	private int type;
	private double price;
	private double totalPrice;
	private double num;
	private String unit;
	private String remark;
	private String docNum;
	private int supplierId;
	private String supplierCode;
	private String supplierName;
	private Timestamp entryTime;
	private double num1;
	private double num2;
	private double num3;
	private double num4;
	public Integer getId() {
		return id;
	}
	public String getApplyId() {
		return applyId;
	}
	public String getProductId() {
		return productId;
	}
	public String getProductName() {
		return productName;
	}
	public double getPrice() {
		return price;
	}
	public double getNum() {
		return num;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setNum(double num) {
		this.num = num;
	}
	public String getProductType() {
		return productType;
	}
	public int getType() {
		return type;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public Timestamp getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
	}
	public String getDocNum() {
		return docNum;
	}
	public void setDocNum(String docNum) {
		this.docNum = docNum;
	}
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public double getNum1() {
		return num1;
	}
	public void setNum1(double num1) {
		this.num1 = num1;
	}
	public double getNum2() {
		return num2;
	}
	public void setNum2(double num2) {
		this.num2 = num2;
	}
	public double getNum3() {
		return num3;
	}
	public void setNum3(double num3) {
		this.num3 = num3;
	}
	public double getNum4() {
		return num4;
	}
	public void setNum4(double num4) {
		this.num4 = num4;
	}

}