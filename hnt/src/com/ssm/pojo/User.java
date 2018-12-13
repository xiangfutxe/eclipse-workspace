package com.ssm.pojo;


import java.sql.Timestamp;

import com.ssm.utils.StringUtil;
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	
	private String userId;  //用户编号
	private String userByBelongId;  //归属人
	private Integer nodeTag; //归属区域
	private String userByDeclarationId; //报单人
	private String userByRefereeId; //推荐人
	private String userName;  //用户名
	private String sex;       //性别
	private Integer age;      //年龄
	private String documentType; //证件类型
	private String tel;       //电话号码
	private String numId;     //证件号码
	private String password;  //用户密码
	private String password2;  //二级密码
	private String email;     //电子邮箱
	private String qq;       //qq号码
	private String phone;//固定电话
	private String weixin;//微信
	private Double emoney; //报单币
	private Double smoney;  //购物币
	private Double dmoney;  //复消币
	private Double rmoney;  //奖金
	private Double pmoney;  //支持券
	private Double TRmoney;
	private Integer emptyNumTol;
	private Integer isToReal;
	private Double Integeregral;
	private Double totalIncome;  //总收入
	private  Integer payTag; 
	private Integer isDeclaration;   //
	private Integer centerId;   //
	private Integer idByBelongCenter;
	private String userIdByBelongCenter;
	private Integer rankManage; //管理级别
	private Integer rankManageTag;//管理级别是否送的
	private Integer refereeNum; //推荐人数
	private Integer state; //当前状态
	private Integer rankJoin;
	private Integer rankJoinTag;
	private Integer rankJoinOld;
	private Integer rankJoinOriginal;
	private Integer isUp;
	private Double joinPv;
	private Double joinPrice;
	private Integer tag;
	private String accountId; //
	private String accountName; //
	private String bankName;//
	private String province;
	private String city;
	private String address;
	private String area;
	private String node;  //安置节点信息
	private String nodeABC;//安置节点区
	private String refereeNode;//推荐节点信息
	private String declarationNode;//报单节点信息
	private String refereeAll;//所有推荐人信息
	private String userByAId;  //归属人
	private String userByBId;  //归属人
	private String userByCId;  //归属人
	private String bankAdr;
	private Timestamp entryTime;
	private Timestamp validtyTime;
	private Integer isEmpty; //是否空单
	private Double zmoney;
	private Integer centerType;
	private Integer isTouch;//是否参与层碰，默认0；
	private Integer isFirm;//是否公司点位，默认0；
	private Integer viewNum;
	private String realRefereeUserId;
	private Integer realRefereeId;
	private Integer refereeId;
	private Integer belongId;
	private Integer declarationId;
	private Timestamp viewTime;
	private Integer emptyNum;
	private Double totalPerformance;
	private Double totalPrice;
	private Double acpv;
	private Double atpv;
	private Double bcpv;
	private Double btpv;
	private Double rtpv;
	private String centerPsw;
	private String centerName;
	private Integer type;
	private Integer isHide;
	private Integer isOld;
	private Integer isBonus;//分红周数
	private Integer maxTag;
	private Integer isMax;
	private Timestamp userTime;
	private Integer raiseNum;//培育奖享受层数
	private Double chuangyeAward;
	private Double chuangyeAwardPaid;
	private Double chuangyeAwardSet;
	private Timestamp chuangyeValidtyTime;
	private Integer capFactor ;//封顶倍数，默认1；0为不给享受；
	private Integer provId;
	private Timestamp startTime;
	private Timestamp endTime;
	private  String authority; //会员权限 默认：'111111111100000000000000000000';30个零。
	private Double rprice;
	private Double rtprice;
	private Double rprice1;
	private Double rtprice1;
	private Double rpv1;
	private Double rtpv1;
	private Double atprice;
	private Double aprice;
	private Double btprice;
	private Double bprice;
	private Integer rank;
	private Timestamp visitTime;
	private Integer visitNum;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getId() {
		return id;
	}
	public String getUserId() {
		return StringUtil.notNull(userId).toUpperCase();
	}
	
	public String getUserName() {
		return userName;
	}
	public String getSex() {
		return sex;
	}
	public Integer getAge() {
		return age;
	}
	public String getDocumentType() {
		return documentType;
	}
	public String getTel() {
		return tel;
	}
	public String getNumId() {
		return numId;
	}
	public String getPassword() {
		return password;
	}
	public String getPassword2() {
		return password2;
	}
	public String getEmail() {
		return email;
	}
	public String getQq() {
		return qq;
	}
	public Double getEmoney() {
		return emoney;
	}
	public Double getSmoney() {
		return smoney;
	}
	public Double getDmoney() {
		return dmoney;
	}
	public Double getRmoney() {
		return rmoney;
	}
	
	public Integer getRankJoin() {
		return rankJoin;
	}
	public void setRankJoin(Integer rankJoin) {
		this.rankJoin = rankJoin;
	}
	public Double getTotalIncome() {
		return totalIncome;
	}
	public Integer getPayTag() {
		return payTag;
	}
	public Integer getIsDeclaration() {
		return isDeclaration;
	}
	public Integer getCenterId() {
		return centerId;
	}
	
	public Integer getRankManage() {
		return rankManage;
	}
	public Integer getRankManageTag() {
		return rankManageTag;
	}
	public Integer getRefereeNum() {
		return refereeNum;
	}
	public Integer getState() {
		return state;
	}
	public String getAccountId() {
		return accountId;
	}
	public String getAccountName() {
		return accountName;
	}
	public String getBankName() {
		return bankName;
	}
	public String getProvince() {
		return province;
	}
	public String getCity() {
		return city;
	}
	public String getAddress() {
		return address;
	}
	public String getArea() {
		return area;
	}
	public String getNode() {
		return node;
	}
	
	public String getRefereeNode() {
		return refereeNode;
	}
	public String getDeclarationNode() {
		return declarationNode;
	}
	public String getUserByAId() {
		return StringUtil.notNull(userByAId).toUpperCase();
	}
	public String getUserByBId() {
		return  StringUtil.notNull(userByBId).toUpperCase();
	}
	public String getUserByCId() {
		return StringUtil.notNull(userByCId).toUpperCase();
	}
	public Timestamp getEntryTime() {
		return entryTime;
	}
	public Integer getIsEmpty() {
		return isEmpty;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setUserId(String userId) {
		this.userId =  StringUtil.notNull(userId).toUpperCase();
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public void setNumId(String numId) {
		this.numId = numId;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public void setEmoney(Double emoney) {
		this.emoney = emoney;
	}
	public void setSmoney(Double smoney) {
		this.smoney = smoney;
	}
	public void setDmoney(Double dmoney) {
		this.dmoney = dmoney;
	}
	public void setRmoney(Double rmoney) {
		this.rmoney = rmoney;
	}
	public void setTotalIncome(Double totalIncome) {
		this.totalIncome = totalIncome;
	}
	public void setPayTag(Integer payTag) {
		this.payTag = payTag;
	}
	public void setIsDeclaration(Integer isDeclaration) {
		this.isDeclaration = isDeclaration;
	}
	public void setCenterId(Integer centerId) {
		this.centerId = centerId;
	}
	
	public void setRankManage(Integer rankManage) {
		this.rankManage = rankManage;
	}
	public void setRankManageTag(Integer rankManageTag) {
		this.rankManageTag = rankManageTag;
	}
	public void setRefereeNum(Integer refereeNum) {
		this.refereeNum = refereeNum;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public void setNode(String node) {
		this.node = node;
	}
	
	public void setRefereeNode(String refereeNode) {
		this.refereeNode = refereeNode;
	}
	public void setDeclarationNode(String declarationNode) {
		this.declarationNode = declarationNode;
	}
	public void setUserByAId(String userByAId) {
		this.userByAId =  StringUtil.notNull(userByAId).toUpperCase();
	}
	public void setUserByBId(String userByBId) {
		this.userByBId = StringUtil.notNull(userByBId).toUpperCase();
	}
	public void setUserByCId(String userByCId) {
		this.userByCId =  StringUtil.notNull(userByCId).toUpperCase();
	}
	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
	}
	public void setIsEmpty(Integer isEmpty) {
		this.isEmpty = isEmpty;
	}
	public String getUserByBelongId() {
		return  StringUtil.notNull(userByBelongId).toUpperCase();
	}
	public Integer getNodeTag() {
		return nodeTag;
	}
	public String getUserByDeclarationId() {
		return  StringUtil.notNull(userByDeclarationId).toUpperCase();
	}
	public String getUserByRefereeId() {
		 return  StringUtil.notNull(userByRefereeId).toUpperCase();
		
	}
	public void setUserByBelongId(String userByBelongId) {
		this.userByBelongId = StringUtil.notNull(userByBelongId).toUpperCase();
	}
	public void setNodeTag(Integer nodeTag) {
		this.nodeTag = nodeTag;
	}
	public void setUserByDeclarationId(String userByDeclarationId) {
		this.userByDeclarationId =  StringUtil.notNull(userByDeclarationId).toUpperCase();
	}
	public void setUserByRefereeId(String userByRefereeId) {
		this.userByRefereeId = StringUtil.notNull(userByRefereeId).toUpperCase();
	}
	public String getNodeABC() {
		return nodeABC;
	}
	public void setNodeABC(String nodeABC) {
		this.nodeABC = nodeABC;
	}
	public String getRefereeAll() {
		return refereeAll;
	}
	public void setRefereeAll(String refereeAll) {
		this.refereeAll = refereeAll;
	}
	public String getPhone() {
		return phone;
	}
	public String getWeixin() {
		return weixin;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}
	public Double getJoinPv() {
		return joinPv;
	}
	public void setJoinPv(Double joinPv) {
		this.joinPv = joinPv;
	}
	public Integer getTag() {
		return tag;
	}
	public void setTag(Integer tag) {
		this.tag = tag;
	}
	public String getBankAdr() {
		return bankAdr;
	}
	public void setBankAdr(String bankAdr) {
		this.bankAdr = bankAdr;
	}
	public Double getIntegeregral() {
		return Integeregral;
	}
	public void setIntegeregral(Double Integeregral) {
		this.Integeregral = Integeregral;
	}
	public Double getPmoney() {
		return pmoney;
	}
	public Timestamp getValidtyTime() {
		return validtyTime;
	}
	public void setPmoney(Double pmoney) {
		this.pmoney = pmoney;
	}
	public void setValidtyTime(Timestamp validtyTime) {
		this.validtyTime = validtyTime;
	}
	public Integer getRankJoinOld() {
		return rankJoinOld;
	}
	public void setRankJoinOld(Integer rankJoinOld) {
		this.rankJoinOld = rankJoinOld;
	}
	public Double getJoinPrice() {
		return joinPrice;
	}
	public void setJoinPrice(Double joinPrice) {
		this.joinPrice = joinPrice;
	}
	public Integer getIsUp() {
		return isUp;
	}
	public void setIsUp(Integer isUp) {
		this.isUp = isUp;
	}

	public Integer getRankJoinTag() {
		return rankJoinTag;
	}
	public void setRankJoinTag(Integer rankJoinTag) {
		this.rankJoinTag = rankJoinTag;
	}
	public Double getTRmoney() {
		return TRmoney;
	}
	public Integer getIsToReal() {
		return isToReal;
	}
	public void setTRmoney(Double tRmoney) {
		TRmoney = tRmoney;
	}
	public void setIsToReal(Integer isToReal) {
		this.isToReal = isToReal;
	}
	public Double getZmoney() {
		return zmoney;
	}
	public void setZmoney(Double zmoney) {
		this.zmoney = zmoney;
	}
	public Integer getCenterType() {
		return centerType;
	}
	public void setCenterType(Integer centerType) {
		this.centerType = centerType;
	}
	public Integer getIsTouch() {
		return isTouch;
	}
	public void setIsTouch(Integer isTouch) {
		this.isTouch = isTouch;
	}
	public Integer getIsFirm() {
		return isFirm;
	}
	public void setIsFirm(Integer isFirm) {
		this.isFirm = isFirm;
	}
	public Integer getViewNum() {
		return viewNum;
	}
	public void setViewNum(Integer viewNum) {
		this.viewNum = viewNum;
	}
	public Timestamp getViewTime() {
		return viewTime;
	}
	public void setViewTime(Timestamp viewTime) {
		this.viewTime = viewTime;
	}
	public String getRealRefereeUserId() {
		return realRefereeUserId;
	}
	public void setRealRefereeUserId(String realRefereeUserId) {
		this.realRefereeUserId = realRefereeUserId;
	}
	public Integer getRealRefereeId() {
		return realRefereeId;
	}
	public void setRealRefereeId(Integer realRefereeId) {
		this.realRefereeId = realRefereeId;
	}
	public Integer getEmptyNum() {
		return emptyNum;
	}
	public void setEmptyNum(Integer emptyNum) {
		this.emptyNum = emptyNum;
	}
	
	public Integer getRefereeId() {
		return refereeId;
	}
	public void setRefereeId(Integer refereeId) {
		this.refereeId = refereeId;
	}
	public Integer getBelongId() {
		return belongId;
	}
	public void setBelongId(Integer belongId) {
		this.belongId = belongId;
	}
	public Integer getDeclarationId() {
		return declarationId;
	}
	public void setDeclarationId(Integer declarationId) {
		this.declarationId = declarationId;
	}
	public Double getTotalPerformance() {
		return totalPerformance;
	}
	public void setTotalPerformance(Double totalPerformance) {
		this.totalPerformance = totalPerformance;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Double getAcpv() {
		return acpv;
	}
	public void setAcpv(Double acpv) {
		this.acpv = acpv;
	}
	public Double getAtpv() {
		return atpv;
	}
	public void setAtpv(Double atpv) {
		this.atpv = atpv;
	}
	public Double getBcpv() {
		return bcpv;
	}
	public void setBcpv(Double bcpv) {
		this.bcpv = bcpv;
	}
	public Double getBtpv() {
		return btpv;
	}
	public void setBtpv(Double btpv) {
		this.btpv = btpv;
	}
	public Double getRtpv() {
		return rtpv;
	}
	public void setRtpv(Double rtpv) {
		this.rtpv = rtpv;
	}
	public String getCenterPsw() {
		return centerPsw;
	}
	public void setCenterPsw(String centerPsw) {
		this.centerPsw = centerPsw;
	}
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getIsOld() {
		return isOld;
	}
	public void setIsOld(Integer isOld) {
		this.isOld = isOld;
	}
	public Integer getIsBonus() {
		return isBonus;
	}
	public void setIsBonus(Integer isBonus) {
		this.isBonus = isBonus;
	}
	public Integer getRankJoinOriginal() {
		return rankJoinOriginal;
	}
	public void setRankJoinOriginal(Integer rankJoinOriginal) {
		this.rankJoinOriginal = rankJoinOriginal;
	}
	public Integer getMaxTag() {
		return maxTag;
	}
	public void setMaxTag(Integer maxTag) {
		this.maxTag = maxTag;
	}
	public Integer getIsMax() {
		return isMax;
	}
	public void setIsMax(Integer isMax) {
		this.isMax = isMax;
	}
	public Timestamp getUserTime() {
		return userTime;
	}
	public void setUserTime(Timestamp userTime) {
		this.userTime = userTime;
	}
	public Integer getRaiseNum() {
		return raiseNum;
	}
	public void setRaiseNum(Integer raiseNum) {
		this.raiseNum = raiseNum;
	}
	public Double getChuangyeAward() {
		return chuangyeAward;
	}
	public void setChuangyeAward(Double chuangyeAward) {
		this.chuangyeAward = chuangyeAward;
	}
	public Double getChuangyeAwardPaid() {
		return chuangyeAwardPaid;
	}
	public void setChuangyeAwardPaid(Double chuangyeAwardPaid) {
		this.chuangyeAwardPaid = chuangyeAwardPaid;
	}
	public Timestamp getChuangyeValidtyTime() {
		return chuangyeValidtyTime;
	}
	public void setChuangyeValidtyTime(Timestamp chuangyeValidtyTime) {
		this.chuangyeValidtyTime = chuangyeValidtyTime;
	}
	public Double getChuangyeAwardSet() {
		return chuangyeAwardSet;
	}
	public void setChuangyeAwardSet(Double chuangyeAwardSet) {
		this.chuangyeAwardSet = chuangyeAwardSet;
	}
	
	public Integer getCapFactor() {
		return capFactor;
	}
	public void setCapFactor(Integer capFactor) {
		this.capFactor = capFactor;
	}
	public Integer getProvId() {
		return provId;
	}
	public void setProvId(Integer provId) {
		this.provId = provId;
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
	public Integer getEmptyNumTol() {
		return emptyNumTol;
	}
	public void setEmptyNumTol(Integer emptyNumTol) {
		this.emptyNumTol = emptyNumTol;
	}
	public Integer getIdByBelongCenter() {
		return idByBelongCenter;
	}
	public void setIdByBelongCenter(Integer idByBelongCenter) {
		this.idByBelongCenter = idByBelongCenter;
	}
	public String getUserIdByBelongCenter() {
		return StringUtil.notNull(userIdByBelongCenter).toUpperCase();
	}
	public void setUserIdByBelongCenter(String userIdByBelongCenter) {
		this.userIdByBelongCenter =  StringUtil.notNull(userIdByBelongCenter).toUpperCase();
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public Integer getIsHide() {
		return isHide;
	}
	public void setIsHide(Integer isHide) {
		this.isHide = isHide;
	}
	public Double getRprice() {
		return rprice;
	}
	public void setRprice(Double rprice) {
		this.rprice = rprice;
	}
	public Double getRtprice() {
		return rtprice;
	}
	public void setRtprice(Double rtprice) {
		this.rtprice = rtprice;
	}
	public Double getRprice1() {
		return rprice1;
	}
	public void setRprice1(Double rprice1) {
		this.rprice1 = rprice1;
	}
	public Double getRtprice1() {
		return rtprice1;
	}
	public void setRtprice1(Double rtprice1) {
		this.rtprice1 = rtprice1;
	}
	public Double getRpv1() {
		return rpv1;
	}
	public void setRpv1(Double rpv1) {
		this.rpv1 = rpv1;
	}
	public Double getRtpv1() {
		return rtpv1;
	}
	public void setRtpv1(Double rtpv1) {
		this.rtpv1 = rtpv1;
	}
	public Double getAtprice() {
		return atprice;
	}
	public void setAtprice(Double atprice) {
		this.atprice = atprice;
	}
	public Double getAprice() {
		return aprice;
	}
	public void setAprice(Double aprice) {
		this.aprice = aprice;
	}
	public Double getBtprice() {
		return btprice;
	}
	public void setBtprice(Double btprice) {
		this.btprice = btprice;
	}
	public Double getBprice() {
		return bprice;
	}
	public void setBprice(Double bprice) {
		this.bprice = bprice;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public Timestamp getVisitTime() {
		return visitTime;
	}
	public void setVisitTime(Timestamp visitTime) {
		this.visitTime = visitTime;
	}
	public Integer getVisitNum() {
		return visitNum;
	}
	public void setVisitNum(Integer visitNum) {
		this.visitNum = visitNum;
	}
	
}