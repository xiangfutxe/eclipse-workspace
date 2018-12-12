package com.pojo;

import java.sql.Timestamp;


public class WSettle implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer uId;
	private String userId;
	private String userName;
	private double totalPerformance;
	private double newPerformance; 
	private double totalPrice;
	private double newPrice; 
	private int totalNum;
	private int newNum; 
	private double apv;
	private double acpv;
	private double atpv;
	private double sspv;
	private double bpv;
	private double bcpv;
	private double btpv;
	private double bspv;
	private double cpv;
	private double ccpv;
	private double ctpv;
	private double cspv;
	private double rpv;
	private double rtpv;
	private int maxRank;
	private int maxRankNew;
	private int rankNum_6;
	private int rank;
	private int tag;
	private double groupPerformance;
	private double groupPerformance_1;
	private double groupPerformance_2;
	private double groupPerformance_3;
	private int weekTag; 
	private Timestamp startTime; 
	private Timestamp endTime; 
	private Timestamp entryTime; 
	private int rankJoin;
	private String state;
	private double pmoneyIncome;
	private double monthIncome;
	private  double TPv;
	private  double TNPv;
	private  int isQualify; //是否合格经销商
	private double manageIncome;
	private double doubleConsume;
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
	public double getTotalPerformance() {
		return totalPerformance;
	}
	public double getNewPerformance() {
		return newPerformance;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public double getNewPrice() {
		return newPrice;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public int getNewNum() {
		return newNum;
	}
	public double getApv() {
		return apv;
	}
	public double getAcpv() {
		return acpv;
	}
	public double getAtpv() {
		return atpv;
	}
	public double getBpv() {
		return bpv;
	}
	public double getBcpv() {
		return bcpv;
	}
	public double getBtpv() {
		return btpv;
	}
	public double getCpv() {
		return cpv;
	}
	public double getCcpv() {
		return ccpv;
	}
	public double getCtpv() {
		return ctpv;
	}
	public double getRpv() {
		return rpv;
	}
	public double getRtpv() {
		return rtpv;
	}
	public int getMaxRank() {
		return maxRank;
	}
	public int getMaxRankNew() {
		return maxRankNew;
	}
	public int getRank() {
		return rank;
	}
	public double getGroupPerformance() {
		return groupPerformance;
	}
	public int getWeekTag() {
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
	public String getState() {
		return state;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setTotalPerformance(double totalPerformance) {
		this.totalPerformance = totalPerformance;
	}
	public void setNewPerformance(double newPerformance) {
		this.newPerformance = newPerformance;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public void setNewPrice(double newPrice) {
		this.newPrice = newPrice;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public void setNewNum(int newNum) {
		this.newNum = newNum;
	}
	public void setApv(double apv) {
		this.apv = apv;
	}
	public void setAcpv(double acpv) {
		this.acpv = acpv;
	}
	public void setAtpv(double atpv) {
		this.atpv = atpv;
	}
	public void setBpv(double bpv) {
		this.bpv = bpv;
	}
	public void setBcpv(double bcpv) {
		this.bcpv = bcpv;
	}
	public void setBtpv(double btpv) {
		this.btpv = btpv;
	}
	public void setCpv(double cpv) {
		this.cpv = cpv;
	}
	public void setCcpv(double ccpv) {
		this.ccpv = ccpv;
	}
	public void setCtpv(double ctpv) {
		this.ctpv = ctpv;
	}
	public void setRpv(double rpv) {
		this.rpv = rpv;
	}
	public void setRtpv(double rtpv) {
		this.rtpv = rtpv;
	}
	public void setMaxRank(int maxRank) {
		this.maxRank = maxRank;
	}
	public void setMaxRankNew(int maxRankNew) {
		this.maxRankNew = maxRankNew;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public void setGroupPerformance(double groupPerformance) {
		this.groupPerformance = groupPerformance;
	}
	public void setWeekTag(int weekTag) {
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
	public void setState(String state) {
		this.state = state;
	}
	public double getSspv() {
		return sspv;
	}
	public double getBspv() {
		return bspv;
	}
	public double getCspv() {
		return cspv;
	}
	public void setSspv(double sspv) {
		this.sspv = sspv;
	}
	public void setBspv(double bspv) {
		this.bspv = bspv;
	}
	public void setCspv(double cspv) {
		this.cspv = cspv;
	}
	public String getUserId() {
		return userId.toUpperCase();
	}
	public String getUserName() {
		return userName;
	}
	public void setUserId(String userId) {
		this.userId = userId.toUpperCase();
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getTag() {
		return tag;
	}
	public void setTag(int tag) {
		this.tag = tag;
	}
	public int getRankJoin(){
		return rankJoin;
	}
	public void setRankJoin(int rankJoin) {
		this.rankJoin = rankJoin;
	}
	public int getRankNum_6() {
		return rankNum_6;
	}
	public void setRankNum_6(int rankNum_6) {
		this.rankNum_6 = rankNum_6;
	}
	public double getGroupPerformance_1() {
		return groupPerformance_1;
	}
	public void setGroupPerformance_1(double groupPerformance_1) {
		this.groupPerformance_1 = groupPerformance_1;
	}
	public double getGroupPerformance_2() {
		return groupPerformance_2;
	}
	public void setGroupPerformance_2(double groupPerformance_2) {
		this.groupPerformance_2 = groupPerformance_2;
	}
	public double getGroupPerformance_3() {
		return groupPerformance_3;
	}
	public void setGroupPerformance_3(double groupPerformance_3) {
		this.groupPerformance_3 = groupPerformance_3;
	}
	public double getPmoneyIncome() {
		return pmoneyIncome;
	}
	public void setPmoneyIncome(double pmoneyIncome) {
		this.pmoneyIncome = pmoneyIncome;
	}
	public double getMonthIncome() {
		return monthIncome;
	}
	public void setMonthIncome(double monthIncome) {
		this.monthIncome = monthIncome;
	}
	public double getTPv() {
		return TPv;
	}
	public void setTPv(double tPv) {
		TPv = tPv;
	}
	public double getTNPv() {
		return TNPv;
	}
	public void setTNPv(double tNPv) {
		this.TNPv = tNPv;
	}
	public int getIsQualify() {
		return isQualify;
	}
	public void setIsQualify(int isQualify) {
		this.isQualify = isQualify;
	}
	public double getManageIncome() {
		return manageIncome;
	}
	public void setManageIncome(double manageIncome) {
		this.manageIncome = manageIncome;
	}
	public double getDoubleConsume() {
		return doubleConsume;
	}
	public void setDoubleConsume(double doubleConsume) {
		this.doubleConsume = doubleConsume;
	}
}