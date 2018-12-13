package com.ssm.pojo;

import java.sql.Timestamp;

import com.ssm.utils.StringUtil;


public class DSettle implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer uId;
	private String userId;
	private String userName;
	private Double totalPerformance;
	private Double newPerformance; 
	private Double totalPrice;
	private Double newPrice; 
	private Double totalPerformance1;
	private Double newPerformance1; 
	private Double totalPrice1;
	private Double newPrice1; 
	private Integer totalNum;
	private Integer newNum; 
	private Double apv;
	private Double acpv;
	private Double atpv;
	private Double atprice;
	private Double aprice;
	private Double btprice;
	private Double bprice;
	private Double sspv;
	private Double bpv;
	private Double bcpv;
	private Double btpv;
	private Double bspv;

	private Double rpv;
	private Double rtpv;
	private Double rprice;
	private Double rtprice;
	private Double rprice1;
	private Double rtprice1;
	private Double rpv1;
	private Double rtpv1;
	private Integer maxRank;
	private Integer maxRankNew;
	private Integer rank;
	private Integer tag;
	private Double groupPerformance;
	private Integer weekTag; 
	private Timestamp startTime; 
	private Timestamp endTime; 
	private Timestamp entryTime; 
	private Integer state;
	private Integer isTouch;
	private Integer isFirm;
	private Integer rankJoin;
	private Integer rankJoinOld;
	private Integer rankJoinTag;
	private Integer isEmpty;
	private Integer isBonus;//分红周数
	private Integer payTag;
	private Integer totalNumReal;//实单总数
	private Integer newNumReal; //新增实单人数
	private Integer maxTag;
	private Double joinPvTal;
	private Double joinPvNew;
	private Double joinPriceTal;
	private Double joinPriceNew;
	private String leftNodeId;
	private String rightNodeId;
	private String leftNodePv;
	private String rightNodePv;
	private String leftNodePrice;
	private String rightNodePrice;
	private String nodeTag;
	private int ctag;
	private Timestamp dayTime;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getId() {
		return id;
	}
	
	public Integer getuId() {
		return uId;
	}
	public void setuId(Integer uId) {
		this.uId = uId;
	}
	public Double getTotalPerformance() {
		return totalPerformance;
	}
	public Double getNewPerformance() {
		return newPerformance;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public Double getNewPrice() {
		return newPrice;
	}
	public Integer getTotalNum() {
		return totalNum;
	}
	public Integer getNewNum() {
		return newNum;
	}
	public Double getApv() {
		return apv;
	}
	public Double getAcpv() {
		return acpv;
	}
	public Double getAtpv() {
		return atpv;
	}
	public Double getBpv() {
		return bpv;
	}
	public Double getBcpv() {
		return bcpv;
	}
	public Double getBtpv() {
		return btpv;
	}
	
	public Double getRpv() {
		return rpv;
	}
	public Double getRtpv() {
		return rtpv;
	}
	public Integer getMaxRank() {
		return maxRank;
	}
	public Integer getMaxRankNew() {
		return maxRankNew;
	}
	public Integer getRank() {
		return rank;
	}
	public Double getGroupPerformance() {
		return groupPerformance;
	}
	public Integer getWeekTag() {
		return weekTag;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public Timestamp getEntryTime() {
		return entryTime;
	}
	public Integer getState() {
		return state;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setTotalPerformance(Double totalPerformance) {
		this.totalPerformance = totalPerformance;
	}
	public void setNewPerformance(Double newPerformance) {
		this.newPerformance = newPerformance;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public void setNewPrice(Double newPrice) {
		this.newPrice = newPrice;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public void setNewNum(Integer newNum) {
		this.newNum = newNum;
	}
	public void setApv(Double apv) {
		this.apv = apv;
	}
	public void setAcpv(Double acpv) {
		this.acpv = acpv;
	}
	public void setAtpv(Double atpv) {
		this.atpv = atpv;
	}
	public void setBpv(Double bpv) {
		this.bpv = bpv;
	}
	public void setBcpv(Double bcpv) {
		this.bcpv = bcpv;
	}
	public void setBtpv(Double btpv) {
		this.btpv = btpv;
	}
	
	public void setRpv(Double rpv) {
		this.rpv = rpv;
	}
	public void setRtpv(Double rtpv) {
		this.rtpv = rtpv;
	}
	public void setMaxRank(Integer maxRank) {
		this.maxRank = maxRank;
	}
	public void setMaxRankNew(Integer maxRankNew) {
		this.maxRankNew = maxRankNew;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public void setGroupPerformance(Double groupPerformance) {
		this.groupPerformance = groupPerformance;
	}
	public void setWeekTag(Integer weekTag) {
		this.weekTag = weekTag;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Double getSspv() {
		return sspv;
	}
	public Double getBspv() {
		return bspv;
	}
	
	public void setSspv(Double sspv) {
		this.sspv = sspv;
	}
	public void setBspv(Double bspv) {
		this.bspv = bspv;
	}
	
	public String getUserId() {
		return StringUtil.notNull(userId).toUpperCase();
	}
	public String getUserName() {
		return userName;
	}
	public void setUserId(String userId) {
		this.userId = StringUtil.notNull(userId).toUpperCase();
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getTag() {
		return tag;
	}
	public void setTag(Integer tag) {
		this.tag = tag;
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
	public Integer getIsEmpty() {
		return isEmpty;
	}
	public void setIsEmpty(Integer isEmpty) {
		this.isEmpty = isEmpty;
	}
	public Integer getIsBonus() {
		return isBonus;
	}
	public void setIsBonus(Integer isBonus) {
		this.isBonus = isBonus;
	}
	public Integer getTotalNumReal() {
		return totalNumReal;
	}
	public void setTotalNumReal(Integer totalNumReal) {
		this.totalNumReal = totalNumReal;
	}
	public Integer getNewNumReal() {
		return newNumReal;
	}
	public void setNewNumReal(Integer newNumReal) {
		this.newNumReal = newNumReal;
	}
	public Integer getMaxTag() {
		return maxTag;
	}
	public void setMaxTag(Integer maxTag) {
		this.maxTag = maxTag;
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
	public Double getJoinPriceTal() {
		return joinPriceTal;
	}
	public void setJoinPriceTal(Double joinPriceTal) {
		this.joinPriceTal = joinPriceTal;
	}
	public Double getJoinPriceNew() {
		return joinPriceNew;
	}
	public void setJoinPriceNew(Double joinPriceNew) {
		this.joinPriceNew = joinPriceNew;
	}
	public Double getTotalPerformance1() {
		return totalPerformance1;
	}
	public void setTotalPerformance1(Double totalPerformance1) {
		this.totalPerformance1 = totalPerformance1;
	}
	public Double getNewPerformance1() {
		return newPerformance1;
	}
	public void setNewPerformance1(Double newPerformance1) {
		this.newPerformance1 = newPerformance1;
	}
	public Double getTotalPrice1() {
		return totalPrice1;
	}
	public void setTotalPrice1(Double totalPrice1) {
		this.totalPrice1 = totalPrice1;
	}
	public Double getNewPrice1() {
		return newPrice1;
	}
	public void setNewPrice1(Double newPrice1) {
		this.newPrice1 = newPrice1;
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
	public Integer getPayTag() {
		return payTag;
	}
	public void setPayTag(Integer payTag) {
		this.payTag = payTag;
	}
	public Timestamp getDayTime() {
		return dayTime;
	}
	public void setDayTime(Timestamp dayTime) {
		this.dayTime = dayTime;
	}
	public Integer getRankJoinOld() {
		return rankJoinOld;
	}
	public void setRankJoinOld(Integer rankJoinOld) {
		this.rankJoinOld = rankJoinOld;
	}
	public String getLeftNodeId() {
		return leftNodeId;
	}
	public void setLeftNodeId(String leftNodeId) {
		this.leftNodeId = leftNodeId;
	}
	public String getRightNodeId() {
		return rightNodeId;
	}
	public void setRightNodeId(String rightNodeId) {
		this.rightNodeId = rightNodeId;
	}
	public String getLeftNodePv() {
		return leftNodePv;
	}
	public void setLeftNodePv(String leftNodePv) {
		this.leftNodePv = leftNodePv;
	}
	public String getRightNodePv() {
		return rightNodePv;
	}
	public void setRightNodePv(String rightNodePv) {
		this.rightNodePv = rightNodePv;
	}
	public String getLeftNodePrice() {
		return leftNodePrice;
	}
	public void setLeftNodePrice(String leftNodePrice) {
		this.leftNodePrice = leftNodePrice;
	}
	public String getRightNodePrice() {
		return rightNodePrice;
	}
	public void setRightNodePrice(String rightNodePrice) {
		this.rightNodePrice = rightNodePrice;
	}
	public String getNodeTag() {
		return nodeTag;
	}
	public void setNodeTag(String nodeTag) {
		this.nodeTag = nodeTag;
	}
	public int getCtag() {
		return ctag;
	}
	public void setCtag(int ctag) {
		this.ctag = ctag;
	}
	
}