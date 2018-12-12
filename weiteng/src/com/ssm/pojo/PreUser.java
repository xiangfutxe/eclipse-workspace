package com.ssm.pojo;


import java.sql.Timestamp;

import com.ssm.utils.StringUtil;
public class PreUser implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String userId;  //用户编号
	private String userName;  //用户名
	private String userByBelongId;  //归属人
	private Integer nodeTag; //归属区域
	private String userByDeclarationId; //报单人
	private String userByRefereeId; //推荐人
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
	private Integer totalNum;
	private Integer totalNumReal;
	private String node;  //安置节点信息
	private String nodeABC;//安置节点区
	private String refereeNode;//推荐节点信息
	private String declarationNode;//报单节点信息
	private String refereeAll;//所有推荐人信息
	private String userByAId;  //归属人
	private String userByBId;  //归属人
	private String userByCId;  //归属人
	private Timestamp entryTime;
	private Integer isEmpty; //是否空单
	private Integer centerType;
	private String realRefereeUserId;
	private Integer realRefereeId;
	private Integer refereeId;
	private Integer belongId;
	private Integer declarationId;
	private Double totalPerformance;
	private Double acpv;
	private Double atpv;
	private Double bcpv;
	private Double btpv;
	private Double rtpv;
	private Double joinPvTal;
	private Double joinPvNew;
	private Timestamp userTime;
	private Timestamp startTime;
	private Timestamp endTime;
	private Double rpv1;
	private Double rtpv1;
	private Integer rank;
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
	public String getUserByBelongId() {
		return userByBelongId;
	}
	public void setUserByBelongId(String userByBelongId) {
		this.userByBelongId = userByBelongId;
	}
	public Integer getNodeTag() {
		return nodeTag;
	}
	public void setNodeTag(Integer nodeTag) {
		this.nodeTag = nodeTag;
	}
	public String getUserByDeclarationId() {
		return userByDeclarationId;
	}
	public void setUserByDeclarationId(String userByDeclarationId) {
		this.userByDeclarationId = userByDeclarationId;
	}
	public String getUserByRefereeId() {
		return userByRefereeId;
	}
	public void setUserByRefereeId(String userByRefereeId) {
		this.userByRefereeId = userByRefereeId;
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
	public Integer getIsDeclaration() {
		return isDeclaration;
	}
	public void setIsDeclaration(Integer isDeclaration) {
		this.isDeclaration = isDeclaration;
	}
	public Integer getCenterId() {
		return centerId;
	}
	public void setCenterId(Integer centerId) {
		this.centerId = centerId;
	}
	public Integer getIdByBelongCenter() {
		return idByBelongCenter;
	}
	public void setIdByBelongCenter(Integer idByBelongCenter) {
		this.idByBelongCenter = idByBelongCenter;
	}
	public String getUserIdByBelongCenter() {
		return userIdByBelongCenter;
	}
	public void setUserIdByBelongCenter(String userIdByBelongCenter) {
		this.userIdByBelongCenter = userIdByBelongCenter;
	}
	public Integer getRankManage() {
		return rankManage;
	}
	public void setRankManage(Integer rankManage) {
		this.rankManage = rankManage;
	}
	public Integer getRankManageTag() {
		return rankManageTag;
	}
	public void setRankManageTag(Integer rankManageTag) {
		this.rankManageTag = rankManageTag;
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
	public Integer getRankJoin() {
		return rankJoin;
	}
	public void setRankJoin(Integer rankJoin) {
		this.rankJoin = rankJoin;
	}
	public Integer getRankJoinTag() {
		return rankJoinTag;
	}
	public void setRankJoinTag(Integer rankJoinTag) {
		this.rankJoinTag = rankJoinTag;
	}
	public Integer getRankJoinOld() {
		return rankJoinOld;
	}
	public void setRankJoinOld(Integer rankJoinOld) {
		this.rankJoinOld = rankJoinOld;
	}
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public Integer getTotalNumReal() {
		return totalNumReal;
	}
	public void setTotalNumReal(Integer totalNumReal) {
		this.totalNumReal = totalNumReal;
	}
	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}
	public String getNodeABC() {
		return nodeABC;
	}
	public void setNodeABC(String nodeABC) {
		this.nodeABC = nodeABC;
	}
	public String getRefereeNode() {
		return refereeNode;
	}
	public void setRefereeNode(String refereeNode) {
		this.refereeNode = refereeNode;
	}
	public String getDeclarationNode() {
		return declarationNode;
	}
	public void setDeclarationNode(String declarationNode) {
		this.declarationNode = declarationNode;
	}
	public String getRefereeAll() {
		return refereeAll;
	}
	public void setRefereeAll(String refereeAll) {
		this.refereeAll = refereeAll;
	}
	public String getUserByAId() {
		return userByAId;
	}
	public void setUserByAId(String userByAId) {
		this.userByAId = userByAId;
	}
	public String getUserByBId() {
		return userByBId;
	}
	public void setUserByBId(String userByBId) {
		this.userByBId = userByBId;
	}
	public String getUserByCId() {
		return userByCId;
	}
	public void setUserByCId(String userByCId) {
		this.userByCId = userByCId;
	}
	public Timestamp getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
	}
	public Integer getIsEmpty() {
		return isEmpty;
	}
	public void setIsEmpty(Integer isEmpty) {
		this.isEmpty = isEmpty;
	}
	public Integer getCenterType() {
		return centerType;
	}
	public void setCenterType(Integer centerType) {
		this.centerType = centerType;
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
	public Double getJoinPvTal() {
		return joinPvTal;
	}
	public void setJoinPvTal(Double joinPvTal) {
		this.joinPvTal = joinPvTal;
	}
	public Double getJoinPvNew() {
		return joinPvNew;
	}
	public void setJoinPvNew(Double joinPvNew) {
		this.joinPvNew = joinPvNew;
	}
	public Timestamp getUserTime() {
		return userTime;
	}
	public void setUserTime(Timestamp userTime) {
		this.userTime = userTime;
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
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}