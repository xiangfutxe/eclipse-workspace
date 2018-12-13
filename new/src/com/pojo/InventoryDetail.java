package com.pojo;

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
	private String productId;
	private String productName;
	private String productType;
	private String specification;
	private int type;
	private double price;
	private Integer num;
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
	public Integer getNum() {
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
	public void setNum(Integer num) {
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



}