package com.ssm.pojo;


public class ProductDetail implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String pId;
	private String productId;
	private Integer p_id;
	private String productName;
	private String specification;
	private String productType;
	private Double productPrice;
	private Double productPv;
	private Double price;
	private Double pv;
	private Double productPriceCy;
	private Double productPvCy;
	private Double priceCy;
	private Double pvCy;
	private String imageUrl;
	private Integer num;
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
	public Double getProductPrice() {
		return productPrice;
	}
	public Double getProductPv() {
		return productPv;
	}
	public Double getPrice() {
		return price;
	}
	public Double getPv() {
		return pv;
	}
	public Integer getNum() {
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
	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}
	public void setProductPv(Double productPv) {
		this.productPv = productPv;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public void setPv(Double pv) {
		this.pv = pv;
	}
	public void setNum(Integer num) {
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

	public Integer getP_id() {
		return p_id;
	}

	public void setP_id(Integer p_id) {
		this.p_id = p_id;
	}

	public Double getPriceCy() {
		return priceCy;
	}

	public void setPriceCy(Double priceCy) {
		this.priceCy = priceCy;
	}

	public Double getPvCy() {
		return pvCy;
	}

	public void setPvCy(Double pvCy) {
		this.pvCy = pvCy;
	}

	public Double getProductPriceCy() {
		return productPriceCy;
	}

	public void setProductPriceCy(Double productPriceCy) {
		this.productPriceCy = productPriceCy;
	}

	public Double getProductPvCy() {
		return productPvCy;
	}

	public void setProductPvCy(Double productPvCy) {
		this.productPvCy = productPvCy;
	}

}
