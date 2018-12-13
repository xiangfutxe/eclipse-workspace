package com.pojo;
import java.sql.Timestamp;

import com.utils.StringUtil;
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	
	private String userId;  //用户编号
	private String userIdOld;  //用户编号
	private String userByBelongId;  //归属人
	private Integer nodeTag; //归属区域
	private String userByDeclarationId; //报单人
	private String userByRefereeId; //推荐人
	private int rid;
	private int bid;
	private int did;
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
	private double emoney; //报单币
	private double smoney;  //购物币
	private double dmoney;  //复消币
	private double rmoney;  //奖金
	private double pmoney;  //支持券
	private double TRmoney;
	private int isToReal;
	private double integral;
	private double totalIncome;  //总收入
	private  int payTag; 
	private String centerByBelongId;
	private Integer rankManage; //管理级别
	private Integer rankManageTag;//管理级别是否送的
	private Integer refereeNum; //推荐人数
	private int state; //当前状态
	private int rankJoin;
	private int rankJoinTag;
	private int rankJoinOld;
	private int isUp;
	private double joinPv;
	private double joinPrice;
	private int centerId;
	private int centerByNew;
	private int centerByOld;
	private int tag;
	private double pmoneyIncome;
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
	private int rankNum_6;
	private Timestamp entryTime;
	private Timestamp validtyTime;
	private String team;
	private String teamName;
	private int isEmpty; //是否空单
	private  int isQualify; //是否合格经销商
	private double apv;
	private double bpv;
	private int centerType;
	private int addNum;
	private int shopNum;
	private int emptyNum;
	private double monthIncome;
	private double fund;
	private double kmoney;
	private double backFill;
	private int isHide;
	private double manageIncome;
	private int viewNum;
	private Timestamp viewTime;
	private int listTag;
	private int imgTag;
	private double backFillReach;
	private int backFillTag;
	private int isRefereeList;
	private int isBelongList;
	private int isUserList;
	private int isDeclaration;
	private String refereeUserId;
	private int refereeId;
	private int declareId;
	private String declareUserId;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getId() {
		return id;
	}
	public String getUserId() {
		return userId.toUpperCase();
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
	public double getEmoney() {
		return emoney;
	}
	public double getSmoney() {
		return smoney;
	}
	public double getDmoney() {
		return dmoney;
	}
	public double getRmoney() {
		return rmoney;
	}
	
	public int getRankJoin() {
		return rankJoin;
	}
	public void setRankJoin(int rankJoin) {
		this.rankJoin = rankJoin;
	}
	public double getTotalIncome() {
		return totalIncome;
	}
	public int getPayTag() {
		return payTag;
	}
	public int getIsDeclaration() {
		return isDeclaration;
	}
	public int getCenterId() {
		return centerId;
	}
	public String getCenterByBelongId() {
		return centerByBelongId;
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
	public int getState() {
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
		return userByAId.toUpperCase();
	}
	public String getUserByBId() {
		return userByBId.toUpperCase();
	}
	public String getUserByCId() {
		return userByCId.toUpperCase();
	}
	public Timestamp getEntryTime() {
		return entryTime;
	}
	public int getIsEmpty() {
		return isEmpty;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setUserId(String userId) {
		this.userId = StringUtil.notNull(userId).toUpperCase();
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
	public void setEmoney(double emoney) {
		this.emoney = emoney;
	}
	public void setSmoney(double smoney) {
		this.smoney = smoney;
	}
	public void setDmoney(double dmoney) {
		this.dmoney = dmoney;
	}
	public void setRmoney(double rmoney) {
		this.rmoney = rmoney;
	}
	public void setTotalIncome(double totalIncome) {
		this.totalIncome = totalIncome;
	}
	public void setPayTag(int payTag) {
		this.payTag = payTag;
	}
	public void setIsDeclaration(int isDeclaration) {
		this.isDeclaration = isDeclaration;
	}
	public void setCenterId(int centerId) {
		this.centerId = centerId;
	}
	public void setCenterByBelongId(String centerByBelongId) {
		this.centerByBelongId = centerByBelongId;
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
	public void setState(int state) {
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
		this.userByAId = StringUtil.notNull(userByAId).toUpperCase();
	}
	public void setUserByBId(String userByBId) {
		this.userByBId = StringUtil.notNull(userByBId).toUpperCase();
	}
	public void setUserByCId(String userByCId) {
		this.userByCId = StringUtil.notNull(userByCId).toUpperCase();
	}
	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
	}
	public void setIsEmpty(int isEmpty) {
		this.isEmpty = isEmpty;
	}
	public String getUserByBelongId() {
		return StringUtil.notNull(userByBelongId).toUpperCase();
	}
	public Integer getNodeTag() {
		return nodeTag;
	}
	public String getUserByDeclarationId() {
		return StringUtil.notNull(userByDeclarationId).toUpperCase();
	}
	public String getUserByRefereeId() {
		return StringUtil.notNull(userByRefereeId).toUpperCase();
	}
	public void setUserByBelongId(String userByBelongId) {
		this.userByBelongId = StringUtil.notNull(userByBelongId).toUpperCase();
	}
	public void setNodeTag(Integer nodeTag) {
		this.nodeTag = nodeTag;
	}
	public void setUserByDeclarationId(String userByDeclarationId) {
		this.userByDeclarationId = StringUtil.notNull(userByDeclarationId).toUpperCase();
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
	public double getJoinPv() {
		return joinPv;
	}
	public void setJoinPv(double joinPv) {
		this.joinPv = joinPv;
	}
	public int getTag() {
		return tag;
	}
	public void setTag(int tag) {
		this.tag = tag;
	}
	public String getBankAdr() {
		return bankAdr;
	}
	public void setBankAdr(String bankAdr) {
		this.bankAdr = bankAdr;
	}
	public double getIntegral() {
		return integral;
	}
	public void setIntegral(double integral) {
		this.integral = integral;
	}
	public double getPmoney() {
		return pmoney;
	}
	public Timestamp getValidtyTime() {
		return validtyTime;
	}
	public void setPmoney(double pmoney) {
		this.pmoney = pmoney;
	}
	public void setValidtyTime(Timestamp validtyTime) {
		this.validtyTime = validtyTime;
	}
	public int getRankJoinOld() {
		return rankJoinOld;
	}
	public void setRankJoinOld(int rankJoinOld) {
		this.rankJoinOld = rankJoinOld;
	}
	public double getJoinPrice() {
		return joinPrice;
	}
	public void setJoinPrice(double joinPrice) {
		this.joinPrice = joinPrice;
	}
	public int getIsUp() {
		return isUp;
	}
	public void setIsUp(int isUp) {
		this.isUp = isUp;
	}
	public int getRankJoinTag() {
		return rankJoinTag;
	}
	public void setRankJoinTag(int rankJoinTag) {
		this.rankJoinTag = rankJoinTag;
	}
	public double getTRmoney() {
		return TRmoney;
	}
	public int getIsToReal() {
		return isToReal;
	}
	public void setTRmoney(double tRmoney) {
		TRmoney = tRmoney;
	}
	public void setIsToReal(int isToReal) {
		this.isToReal = isToReal;
	}
	public int getCenterByNew() {
		return centerByNew;
	}
	public void setCenterByNew(int centerByNew) {
		this.centerByNew = centerByNew;
	}
	public int getCenterByOld() {
		return centerByOld;
	}
	public void setCenterByOld(int centerByOld) {
		this.centerByOld = centerByOld;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getUserIdOld() {
		return userIdOld;
	}
	public void setUserIdOld(String userIdOld) {
		this.userIdOld = userIdOld;
	}
	public double getApv() {
		return apv;
	}
	public void setApv(double apv) {
		this.apv = apv;
	}
	public double getBpv() {
		return bpv;
	}
	public void setBpv(double bpv) {
		this.bpv = bpv;
	}
	public int getCenterType() {
		return centerType;
	}
	public void setCenterType(int centerType) {
		this.centerType = centerType;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public int getRankNum_6() {
		return rankNum_6;
	}
	public void setRankNum_6(int rankNum_6) {
		this.rankNum_6 = rankNum_6;
	}
	public double getPmoneyIncome() {
		return pmoneyIncome;
	}
	public void setPmoneyIncome(double pmoneyIncome) {
		this.pmoneyIncome = pmoneyIncome;
	}
	public int getAddNum() {
		return addNum;
	}
	public void setAddNum(int addNum) {
		this.addNum = addNum;
	}
	public int getShopNum() {
		return shopNum;
	}
	public void setShopNum(int shopNum) {
		this.shopNum = shopNum;
	}
	public int getEmptyNum() {
		return emptyNum;
	}
	public void setEmptyNum(int emptyNum) {
		this.emptyNum = emptyNum;
	}
	public double getMonthIncome() {
		return monthIncome;
	}
	public void setMonthIncome(double monthIncome) {
		this.monthIncome = monthIncome;
	}
	public double getFund() {
		return fund;
	}
	public void setFund(double fund) {
		this.fund = fund;
	}
	public double getKmoney() {
		return kmoney;
	}
	public void setKmoney(double kmoney) {
		this.kmoney = kmoney;
	}
	public int getIsQualify() {
		return isQualify;
	}
	public void setIsQualify(int isQualify) {
		this.isQualify = isQualify;
	}
	public double getBackFill() {
		return backFill;
	}
	public void setBackFill(double backFill) {
		this.backFill = backFill;
	}
	public int getIsHide() {
		return isHide;
	}
	public void setIsHide(int isHide) {
		this.isHide = isHide;
	}
	public double getManageIncome() {
		return manageIncome;
	}
	public void setManageIncome(double manageIncome) {
		this.manageIncome = manageIncome;
	}
	public int getViewNum() {
		return viewNum;
	}
	public void setViewNum(int viewNum) {
		this.viewNum = viewNum;
	}
	public Timestamp getViewTime() {
		return viewTime;
	}
	public void setViewTime(Timestamp viewTime) {
		this.viewTime = viewTime;
	}
	public int getListTag() {
		return listTag;
	}
	public void setListTag(int listTag) {
		this.listTag = listTag;
	}
	public int getImgTag() {
		return imgTag;
	}
	public void setImgTag(int imgTag) {
		this.imgTag = imgTag;
	}
	public double getBackFillReach() {
		return backFillReach;
	}
	public void setBackFillReach(double backFillReach) {
		this.backFillReach = backFillReach;
	}
	public int getBackFillTag() {
		return backFillTag;
	}
	public void setBackFillTag(int backFillTag) {
		this.backFillTag = backFillTag;
	}
	public int getIsRefereeList() {
		return isRefereeList;
	}
	public void setIsRefereeList(int isRefereeList) {
		this.isRefereeList = isRefereeList;
	}
	public int getIsBelongList() {
		return isBelongList;
	}
	public void setIsBelongList(int isBelongList) {
		this.isBelongList = isBelongList;
	}
	public int getIsUserList() {
		return isUserList;
	}
	public void setIsUserList(int isUserList) {
		this.isUserList = isUserList;
	}
	public String getRefereeUserId() {
		return refereeUserId;
	}
	public void setRefereeUserId(String refereeUserId) {
		this.refereeUserId = refereeUserId;
	}
	public int getRefereeId() {
		return refereeId;
	}
	public void setRefereeId(int refereeId) {
		this.refereeId = refereeId;
	}
	public int getDeclareId() {
		return declareId;
	}
	public void setDeclareId(int declareId) {
		this.declareId = declareId;
	}
	public String getDeclareUserId() {
		return declareUserId;
	}
	public void setDeclareUserId(String declareUserId) {
		this.declareUserId = declareUserId;
	}
	
}