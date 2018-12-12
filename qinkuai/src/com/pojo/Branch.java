package com.pojo;

import java.sql.Timestamp;

public class Branch {
	
	private int id;
	private String code;
	private String name;
	private String linkman;
	private String psw;//登陆密码
	private String psw2;//支付密码
	private String tel;
	private String phone;
	private double credit; //授信额度
	private double creditUsed; //已使用额度
	private double creditInit;//初始化额度
	private double standardMin; //购物最低标准
	private double standardUsed; //当日购物总额
	private double emoney;//现金账户
	private String province;
	private String city;
	private String area;
	private String address;
	private String imageUrl;
	private Timestamp entryTime;
	private Timestamp endTime;
	private int state;
	private int tag;
	private int ascNum;
	private int isShop;
	private double num;
	private double price;
	private int type;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}
	public double getCreditUsed() {
		return creditUsed;
	}
	public void setCreditUsed(double creditUsed) {
		this.creditUsed = creditUsed;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPsw() {
		return psw;
	}
	public void setPsw(String psw) {
		this.psw = psw;
	}
	public String getPsw2() {
		return psw2;
	}
	public void setPsw2(String psw2) {
		this.psw2 = psw2;
	}
	public Timestamp getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public int getTag() {
		return tag;
	}
	public void setTag(int tag) {
		this.tag = tag;
	}
	public int getAscNum() {
		return ascNum;
	}
	public void setAscNum(int ascNum) {
		this.ascNum = ascNum;
	}
	public int getIsShop() {
		return isShop;
	}
	public void setIsShop(int isShop) {
		this.isShop = isShop;
	}
	public double getNum() {
		return num;
	}
	public void setNum(double num) {
		this.num = num;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getStandardMin() {
		return standardMin;
	}
	public void setStandardMin(double standardMin) {
		this.standardMin = standardMin;
	}
	public double getStandardUsed() {
		return standardUsed;
	}
	public void setStandardUsed(double standardUsed) {
		this.standardUsed = standardUsed;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public double getEmoney() {
		return emoney;
	}
	public void setEmoney(double emoney) {
		this.emoney = emoney;
	}
	public double getCreditInit() {
		return creditInit;
	}
	public void setCreditInit(double creditInit) {
		this.creditInit = creditInit;
	}
}
