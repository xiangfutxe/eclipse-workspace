package com.ssm.pojo;


public class ProductDetail implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String pid;
	private String productId;
	private String productName;
	private String specification;
	private Double productPrice;
	private Double productPv;
	private Double price;
	private Integer num;
	public Integer getId() {
		return id;
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
	public Double getProductPrice() {
		return productPrice;
	}
	public Double getProductPv() {
		return productPv;
	}
	public Double getPrice() {
		return price;
	}


	public String getPid() {
		return pid;
	}


	public void setPid(String pid) {
		this.pid = pid;
	}


	public Integer getNum() {
		return num;
	}


	public void setNum(Integer num) {
		this.num = num;
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


	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}


	public void setProductPv(Double productPv) {
		this.productPv = productPv;
	}


	public void setPrice(Double price) {
		this.price = price;
	}
	
}
