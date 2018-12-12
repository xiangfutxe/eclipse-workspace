package com.ssm.pojo;

import java.sql.Timestamp;

public class User implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String userId;  //用户编号
	private String userName;  //用户名
	private String nickName; // 用户昵称
	private String sex;       //性别
	private Integer age;      //年龄
	private String documentType; //证件类型
	private String numId;     //证件号码
	private String password;  //用户密码
	private String password2;  //二级密码
	private String email;     //电子邮箱
	private String qq;       //qq号码
	private String mobile;//固定电话
	private String openId;//微信
	private Double emoney; //报单币
	private Double cash;  //代金券
	private Integer cashNum;  //特买权个数
	private Double integral;  //积分
	private Double rmoney;  //奖金
	private Double preCash;  //代金券
	private Integer preCashNum;  //特买权个数
	private Double preIntegral;  //积分
	private Double preRmoney;  //支持券
	private Integer refereeId;
	private String refereeUserId;
	private String refereeNode;
	private String refereeAll;
	private Double totalIncome;  //总收入
	private  Integer payTag; 
	private Integer rankJoinOld;
	private Integer rankJoin; //管理级别
	private Integer refereeNum; //推荐人数
	private Integer refereeNum1; //推荐人数
	private Integer refereeNum2; //推荐人数
	private Integer refereeNum3; //推荐人数
	private Integer refereeNum4; //推荐人数
	private Integer refereeNum5; //推荐人数
	private Integer refereeNum6; //推荐人数
	private Integer refereeNum7; //推荐人数
	private Integer refereeVirtualNum; //推荐人数
	private Integer agentTag;
	private Integer state; //当前状态
	private String accountId; //
	private String accountName; //
	private String bankName;//
	private String province;
	private String city;
	private String area;
	private String address;
	private String bankAdr;
	private String headImgUrl;
	private Long version;
	private Timestamp entryTime;
	private Timestamp startTime;
	private Timestamp endTime;
	private Timestamp preTime;
	private Integer viewNum;
	private Integer tag;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Double getEmoney() {
		return emoney;
	}
	public void setEmoney(Double emoney) {
		this.emoney = emoney;
	}
	public Double getCash() {
		return cash;
	}
	public void setCash(Double cash) {
		this.cash = cash;
	}
	public Integer getCashNum() {
		return cashNum;
	}
	public void setCashNum(Integer cashNum) {
		this.cashNum = cashNum;
	}
	public Double getIntegral() {
		return integral;
	}
	public void setIntegral(Double integral) {
		this.integral = integral;
	}
	public Double getRmoney() {
		return rmoney;
	}
	public void setRmoney(Double rmoney) {
		this.rmoney = rmoney;
	}
	public Integer getRefereeId() {
		return refereeId;
	}
	public void setRefereeId(Integer refereeId) {
		this.refereeId = refereeId;
	}
	public String getRefereeUserId() {
		return refereeUserId;
	}
	public void setRefereeUserId(String refereeUserId) {
		this.refereeUserId = refereeUserId;
	}
	public String getRefereeNode() {
		return refereeNode;
	}
	public void setRefereeNode(String refereeNode) {
		this.refereeNode = refereeNode;
	}
	public Double getTotalIncome() {
		return totalIncome;
	}
	public void setTotalIncome(Double totalIncome) {
		this.totalIncome = totalIncome;
	}
	public Integer getPayTag() {
		return payTag;
	}
	public void setPayTag(Integer payTag) {
		this.payTag = payTag;
	}
	public Integer getRankJoinOld() {
		return rankJoinOld;
	}
	public void setRankJoinOld(Integer rankJoinOld) {
		this.rankJoinOld = rankJoinOld;
	}
	public Integer getRankJoin() {
		return rankJoin;
	}
	public void setRankJoin(Integer rankJoin) {
		this.rankJoin = rankJoin;
	}
	public Integer getRefereeNum() {
		return refereeNum;
	}
	public void setRefereeNum(Integer refereeNum) {
		this.refereeNum = refereeNum;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
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
	public String getBankAdr() {
		return bankAdr;
	}
	public void setBankAdr(String bankAdr) {
		this.bankAdr = bankAdr;
	}
	public Timestamp getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Double getPreCash() {
		return preCash;
	}
	public void setPreCash(Double preCash) {
		this.preCash = preCash;
	}
	public Integer getPreCashNum() {
		return preCashNum;
	}
	public void setPreCashNum(Integer preCashNum) {
		this.preCashNum = preCashNum;
	}
	public Double getPreIntegral() {
		return preIntegral;
	}
	public void setPreIntegral(Double preIntegral) {
		this.preIntegral = preIntegral;
	}
	public Double getPreRmoney() {
		return preRmoney;
	}
	public void setPreRmoney(Double preRmoney) {
		this.preRmoney = preRmoney;
	}
	public String getHeadImgUrl() {
		return headImgUrl;
	}
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
	public String getRefereeAll() {
		return refereeAll;
	}
	public void setRefereeAll(String refereeAll) {
		this.refereeAll = refereeAll;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public Integer getRefereeNum1() {
		return refereeNum1;
	}
	public void setRefereeNum1(Integer refereeNum1) {
		this.refereeNum1 = refereeNum1;
	}
	public Integer getRefereeNum2() {
		return refereeNum2;
	}
	public void setRefereeNum2(Integer refereeNum2) {
		this.refereeNum2 = refereeNum2;
	}
	public Integer getRefereeNum3() {
		return refereeNum3;
	}
	public void setRefereeNum3(Integer refereeNum3) {
		this.refereeNum3 = refereeNum3;
	}
	public Integer getRefereeNum4() {
		return refereeNum4;
	}
	public void setRefereeNum4(Integer refereeNum4) {
		this.refereeNum4 = refereeNum4;
	}
	public Integer getRefereeNum5() {
		return refereeNum5;
	}
	public void setRefereeNum5(Integer refereeNum5) {
		this.refereeNum5 = refereeNum5;
	}
	public Integer getRefereeNum6() {
		return refereeNum6;
	}
	public void setRefereeNum6(Integer refereeNum6) {
		this.refereeNum6 = refereeNum6;
	}
	public Integer getRefereeNum7() {
		return refereeNum7;
	}
	public void setRefereeNum7(Integer refereeNum7) {
		this.refereeNum7 = refereeNum7;
	}
	public Integer getRefereeVirtualNum() {
		return refereeVirtualNum;
	}
	public void setRefereeVirtualNum(Integer refereeVirtualNum) {
		this.refereeVirtualNum = refereeVirtualNum;
	}
	public Integer getAgentTag() {
		return agentTag;
	}
	public void setAgentTag(Integer agentTag) {
		this.agentTag = agentTag;
	}
	public Timestamp getPreTime() {
		return preTime;
	}
	public void setPreTime(Timestamp preTime) {
		this.preTime = preTime;
	}
	public Integer getViewNum() {
		return viewNum;
	}
	public void setViewNum(Integer viewNum) {
		this.viewNum = viewNum;
	}
	public Integer getTag() {
		return tag;
	}
	public void setTag(Integer tag) {
		this.tag = tag;
	}
	
}