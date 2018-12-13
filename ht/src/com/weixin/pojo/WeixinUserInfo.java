package com.weixin.pojo;


public class WeixinUserInfo {
	
	private int id;
	
	private String userId;
	
	private int weixinId;
	
	private String openId;
	
	private int subscribe; //关注状态（1是关注，0是未关注），未关注时获取不到其余信息
	
	private String subscribeTime; //关注时间，为时间戳。如果用户曾多次关注，则取最后一次关注的时间
	
	private String nickName;
	
	private int sex;
	
	private String country;
	
	private String province;
	
	private String city;
	
	private String language;
	
	private String headImgUrl;
	
	private int qrId;//永久二维码ID；
	
	private int state;
	
	private String branchId;
	
	private String branchName;
	
	private String qrImg;
	
	private int rankManage;
	
	private double emoney;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
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

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public int getQrId() {
		return qrId;
	}

	public void setQrId(int qrId) {
		this.qrId = qrId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getWeixinId() {
		return weixinId;
	}

	public void setWeixinId(int weixinId) {
		this.weixinId = weixinId;
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

	public String getQrImg() {
		return qrImg;
	}

	public void setQrImg(String qrImg) {
		this.qrImg = qrImg;
	}

	public int getRankManage() {
		return rankManage;
	}

	public void setRankManage(int rankManage) {
		this.rankManage = rankManage;
	}

	public double getEmoney() {
		return emoney;
	}

	public void setEmoney(double emoney) {
		this.emoney = emoney;
	}
	
	
}
