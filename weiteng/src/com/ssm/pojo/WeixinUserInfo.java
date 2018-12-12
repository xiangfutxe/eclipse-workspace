package com.ssm.pojo;

import java.sql.Timestamp;

public class WeixinUserInfo {
	
	private Integer id;
	
	private String userId;
	
	private Integer weixinId;
	
	private String openId;
	
	private Integer subscribe; //关注状态（1是关注，0是未关注），未关注时获取不到其余信息
	
	private String subscribeTime; //关注时间，为时间戳。如果用户曾多次关注，则取最后一次关注的时间
	
	private String nickName;
	
	private Integer sex;
	
	private String country;
	
	private String province;
	
	private String city;
	
	private String language;
	
	private String headImgUrl;
	
	private Integer qrId;//永久二维码ID；
	
	private Integer state;
	
	private String branchId;
	
	private String branchName;
	
	private Integer refereeId;
	
	private String qrImg;
	
	private Integer rankManage;
	
	private double emoney;
	
	private Timestamp entryTime;
	
	private Timestamp endTime;

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

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
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

	public Integer getSubscribe() {
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getQrId() {
		return qrId;
	}

	public void setQrId(Integer qrId) {
		this.qrId = qrId;
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

	public int getWeixinId() {
		return weixinId;
	}

	public void setWeixinId(Integer weixinId) {
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

	public Integer getRankManage() {
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

	public void setSubscribe(Integer subscribe) {
		this.subscribe = subscribe;
	}

	public Integer getRefereeId() {
		return refereeId;
	}

	public void setRefereeId(Integer refereeId) {
		this.refereeId = refereeId;
	}
	
	
}
