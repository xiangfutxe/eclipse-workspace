<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.Admin" %>
<%@page import="com.ssm.dao.UserDao" %>
<%@page import="com.ssm.dao.MoneyDao" %>
<%@page import="com.ssm.pojo.User" %>
<%@page import="com.ssm.pojo.JoinInfo" %>
<%@page import="com.ssm.dao.JoinInfoDao" %>

<%@page import="com.ssm.pojo.AccountDetail" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>

<%@page import="com.ssm.utils.Pager" %>
<%@page import="java.sql.Timestamp" %>
<%

Pager  pager = new Pager();
pager.setPageNo(1);
pager.setPageSize(2000);
UserDao userDao = new UserDao();
User user = new User();
List<User> ulist = userDao.findUserByList(user);
Timestamp date = new Timestamp(StringUtil.parseToDate("2017-11-03 22:30:00", DateUtil.yyyyMMddHHmmss).getTime());
JoinInfoDao jfDao = null;
for(int i=0;i<ulist.size();i++){
	if(ulist.get(i).getRankJoin()<5){
		JoinInfo jf = new JoinInfo();
		jf.setUid(ulist.get(i).getId());
		jf.setUserId(ulist.get(i).getUserId());
		jf.setUserName(ulist.get(i).getUserName());
		jf.setOldRankJoin(ulist.get(i).getRankJoin());
		jf.setNewRankJoin(5);
		jf.setState(2);
		jf.setRid(ulist.get(i).getRefereeId());
		Timestamp entryTime = new Timestamp (date.getTime()+i*2000);
		jf.setEntryTime(entryTime);
		jfDao = new JoinInfoDao();
		jfDao.saveJoinInfo(jf);
		userDao = new UserDao();
		User user1 = new User();
		user1.setId(ulist.get(i).getId());
		user1.setRankJoin(5);
		userDao.updateUsers(user1);
		out.println("yes");
		
	}
}

%>
