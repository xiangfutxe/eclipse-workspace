package com.pojo;


public class ProductDetail implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String pId;
	private String productId;
	private String productName;
	private String specification;
	private String productType;
	private double productPrice;
	private double productPv;
	private double price;
	private double pv;
	private double integral;
	private String imageUrl;
	private double num;
	public Integer getId() {
		return id;
	}
	
	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getProductId() {
		return productId;
	}
	public String getProductName() {
		return productName;
	}
	public String getSpecification() {
		return specification;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public double getProductPv() {
		return productPv;
	}
	public double getPrice() {
		return price;
	}
	public double getPv() {
		return pv;
	}
	public double getNum() {
		return num;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public void setProductPv(double productPv) {
		this.productPv = productPv;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setPv(double pv) {
		this.pv = pv;
	}
	public void setNum(double num) {
		this.num = num;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public double getIntegral() {
		return integral;
	}

	public void setIntegral(double integral) {
		this.integral = integral;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
