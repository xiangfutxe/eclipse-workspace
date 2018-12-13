package com.pojo;
import java.sql.Timestamp;
public class AgentApply implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String applyId;  //用户编号
	private String userId;  //用户编号
	private String userName;  //用户名
	private String documentType; //证件类型
	private String tel;       //电话号码
	private String numId;     //证件号码
	private String province;
	private String city;
	private String address;
	private String area;
	private String reviewer;
	private int state;
	private Timestamp reviewTime;
	private Timestamp entryTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getApplyId() {
		return applyId;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	public String getUserId() {
		return userId.toUpperCase();
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
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getNumId() {
		return numId;
	}
	public void setNumId(String numId) {
		this.numId = numId;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getReviewer() {
		return reviewer;
	}
	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}
	public Timestamp getReviewTime() {
		return reviewTime;
	}
	public void setReviewTime(Timestamp reviewTime) {
		this.reviewTime = reviewTime;
	}
	public Timestamp getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}

	
}