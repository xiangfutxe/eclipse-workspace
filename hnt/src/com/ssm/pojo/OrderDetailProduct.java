package com.ssm.pojo;


public class OrderDetailProduct implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String userId;
	private String userName;
	private String receiver;
	private String phone;
	private String address;
	private Integer odId;
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
	private Double priceCy;
	private Double pvCy;
	private Double discount;
	private Double totalPriceCy;
	private Double totalPvCy;
	private Integer deliveryNum;
	private Integer state;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOdId() {
		return odId;
	}
	public void setOdId(Integer odId) {
		this.odId = odId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
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
	public Double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}
	public Double getProductPv() {
		return productPv;
	}
	public void setProductPv(Double productPv) {
		this.productPv = productPv;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getPv() {
		return pv;
	}
	public void setPv(Double pv) {
		this.pv = pv;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
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
	public Integer getDeliveryNum() {
		return deliveryNum;
	}
	public void setDeliveryNum(Integer deliveryNum) {
		this.deliveryNum = deliveryNum;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	
}
