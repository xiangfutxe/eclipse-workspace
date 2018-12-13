package com.pojo;
import java.sql.Timestamp;

public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	
	private String userId;  //用户编号
	private String userName;  //用户名
	private String sex;       //性别
	private Integer age;      //年龄
	private Timestamp birth;
	private String documentType; //证件类型
	private String tel;       //电话号码
	private String numId;     //证件号码
	private String password;  //用户密码
	private String password2;  //二级密码
	private String email;     //电子邮箱
	private String qq;       //qq号码
	private String phone;//固定电话
	private String weixin;//微信
	private double emoney; //电子币
	private double rmoney;  //奖金
	private double integral;
	private int rankJoin;
	private String province;
	private String city;
	private String address;
	private String area;
	private Timestamp entryTime;
	private Timestamp endTime;
	private String branchId;
	private String branchName;
	private int state;
	private String imageUrl;
	private int weixinId;
	private String openId;
	private int subscribe; //关注状态（1是关注，0是未关注），未关注时获取不到其余信息
	private String subscribeTime; //关注时间，为时间戳。如果用户曾多次关注，则取最后一次关注的时间
	private String nickName;
	private String country;
	private String language;
	private String headImgUrl;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getWeixin() {
		return weixin;
	}
	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}
	public double getEmoney() {
		return emoney;
	}
	public void setEmoney(double emoney) {
		this.emoney = emoney;
	}
	public double getRmoney() {
		return rmoney;
	}
	public void setRmoney(double rmoney) {
		this.rmoney = rmoney;
	}
	public double getIntegral() {
		return integral;
	}
	public void setIntegral(double integral) {
		this.integral = integral;
	}
	public int getRankJoin() {
		return rankJoin;
	}
	public void setRankJoin(int rankJoin) {
		this.rankJoin = rankJoin;
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
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Timestamp getBirth() {
		return birth;
	}
	public void setBirth(Timestamp birth) {
		this.birth = birth;
	}
	public int getWeixinId() {
		return weixinId;
	}
	public void setWeixinId(int weixinId) {
		this.weixinId = weixinId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public int getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(int subscribe) {
		this.subscribe = subscribe;
	}
	public String getSubscribeTime() {
		return subscribeTime;
	}
	public void setSubscribeTime(String subscribeTime) {
		this.subscribeTime = subscribeTime;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getHeadImgUrl() {
		return headImgUrl;
	}
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
	
}