package com.pojo;


public class OrderDetailCenter implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String orderId;
	private String productId;
	private String productName;
	private String specification;
	private double productPrice;
	private double productPv;
	private String productType;
	private int type;
	private double price;
	private double pv;
	private double price1;
	private double pv1;
	private Integer num;
	public Integer getId() {
		return id;
	}
	public String getOrderId() {
		return orderId;
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
	public Integer getNum() {
		return num;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
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
	public double getPrice1() {
		return price1;
	}
	public void setPrice1(double price1) {
		this.price1 = price1;
	}
	public double getPv1() {
		return pv1;
	}
	public void setPv1(double pv1) {
		this.pv1 = pv1;
	}

	

}
