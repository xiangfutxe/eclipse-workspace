<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.Admin" %>
<%@page import="com.ssm.dao.UserDao" %>

<%@page import="com.ssm.pojo.User" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>

<%@page import="com.ssm.utils.Pager" %>
<%@page import="java.sql.Timestamp" %>
<%
UserDao userDao = new UserDao();
User sel_user = new User();
Timestamp endTime = new Timestamp(StringUtil.parseToDate("2017-10-01 00:00:10", DateUtil.yyyyMMddHHmmss).getTime());
sel_user.setEndTime(endTime);
Pager  pager = new Pager();
pager.setPageNo(1);
pager.setPageSize(100);
List<User> ulist = (List<User>)userDao.findUserMoneyByPage(sel_user, pager).getResultList();

for(int i=ulist.size();i>0;i--){
	out.println("start "+i+"<br>");
	userDao = new UserDao();
	User update_user = new User();
	update_user.setId(ulist.get(i-1).getId());
	update_user.setEntryTime(endTime);
	if(userDao.updateUsers(update_user)>0)
	out.println("update yes "+i+"<br>");
	endTime  = new Timestamp(endTime.getTime()+20000);
}

for(int i=0;i<ulist.size();i++){
out.println("["+ulist.get(i).getUserId()+"]["+StringUtil.parseToString(ulist.get(i).getEntryTime(), DateUtil.yyyyMMddHHmmss)+"]<br>");
}
%>
