package com.ssm.pojo;

import java.sql.Timestamp;

import com.ssm.utils.StringUtil;

public class Center implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer uId;
	private String userId;
	private String centerId;
	private String userName;
	private String adminName;
	private String password;
	private String centerName;
	private String province;
	private String city;
	private String address;
	private String area;
	private String phone;
	private Integer type;
	private Timestamp entryTime;
	private Integer state;
	private Integer isEmpty;
	private Double amount;
	private Double amount_1;
	private Integer typeNew;
	private Integer stateNew;
	private  Integer typeForm;
	private String license;
	private String measure;
	private Integer isHide;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getuId() {
		return uId;
	}
	public void setuId(Integer uId) {
		this.uId = uId;
	}
	public String getUserId() {
		return StringUtil.notNull(userId).toUpperCase();
	}
	public void setUserId(String userId) {
		this.userId = StringUtil.notNull(userId).toUpperCase();
	}
	public String getCenterId() {
		return StringUtil.notNull(centerId).toUpperCase();
	}
	public void setCenterId(String centerId) {
		this.centerId = StringUtil.notNull(centerId).toUpperCase();
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
	public Timestamp getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getIsEmpty() {
		return isEmpty;
	}
	public void setIsEmpty(Integer isEmpty) {
		this.isEmpty = isEmpty;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getAmount_1() {
		return amount_1;
	}
	public void setAmount_1(Double amount_1) {
		this.amount_1 = amount_1;
	}
	public Integer getTypeNew() {
		return typeNew;
	}
	public void setTypeNew(Integer typeNew) {
		this.typeNew = typeNew;
	}
	public Integer getStateNew() {
		return stateNew;
	}
	public void setStateNew(Integer stateNew) {
		this.stateNew = stateNew;
	}
	public Integer getTypeForm() {
		return typeForm;
	}
	public void setTypeForm(Integer typeForm) {
		this.typeForm = typeForm;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public String getMeasure() {
		return measure;
	}
	public void setMeasure(String measure) {
		this.measure = measure;
	}
	public Integer getIsHide() {
		return isHide;
	}
	public void setIsHide(Integer isHide) {
		this.isHide = isHide;
	}
	
}