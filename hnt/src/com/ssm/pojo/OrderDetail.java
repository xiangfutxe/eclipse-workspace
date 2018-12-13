package com.ssm.pojo;


public class OrderDetail implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String orderId;
	private Integer pid;
	private String productId;
	private String productName;
	private String specification;
	private Double productPrice;
	private Double productPv;
	private String productType;
	private Integer type;
	private Double price;
	private Double pv;
	private Integer num;
	private Integer giveNum;
	private Double priceCy;
	private Double pvCy;
	private Double totalPriceCy;
	private Double totalPvCy;
	private Integer deliveryNum;
	private Integer state;
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
	public Integer getType() {
		return type;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
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
	public Double getTotalPriceCy() {
		return totalPriceCy;
	}
	public void setTotalPriceCy(Double totalPriceCy) {
		this.totalPriceCy = totalPriceCy;
	}
	public Double getTotalPvCy() {
		return totalPvCy;
	}
	public void setTotalPvCy(Double totalPvCy) {
		this.totalPvCy = totalPvCy;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getDeliveryNum() {
		return deliveryNum;
	}
	public void setDeliveryNum(Integer deliveryNum) {
		this.deliveryNum = deliveryNum;
	}
	public Integer getGiveNum() {
		return giveNum;
	}
	public void setGiveNum(Integer giveNum) {
		this.giveNum = giveNum;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	

	

}
