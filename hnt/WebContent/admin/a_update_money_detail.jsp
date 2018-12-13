<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.Admin" %>
<%@page import="com.ssm.dao.UserDao" %>
<%@page import="com.ssm.dao.MoneyDao" %>
<%@page import="com.ssm.pojo.User" %>
<%@page import="com.ssm.pojo.AccountDetail" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>

<%@page import="com.ssm.utils.Pager" %>
<%@page import="java.sql.Timestamp" %>
<%

Pager  pager = new Pager();
pager.setPageNo(1);
pager.setPageSize(2000);
AccountDetail ad  = new AccountDetail();
UserDao userDao = new UserDao();
User user1 = new User();
List<User> ulist = userDao.findUserByList(user1);
String error="";
for(int i=0;i<ulist.size();i++){
int uid = ulist.get(i).getId();
	
	String sql1 = "update emoneyDetail set uid ='"+uid+"' where userId='"+ulist.get(i).getUserId()+"'";
	String sql2 = "update dmoneyDetail set uid ='"+uid+"' where userId='"+ulist.get(i).getUserId()+"'";
	String sql3 = "update rmoneyDetail set uid ='"+uid+"' where userId='"+ulist.get(i).getUserId()+"'";
	MoneyDao moneyDao1 = new MoneyDao();
	if(moneyDao1.updateForSql(sql1)==0){
		error+=ulist.get(i).getUserId()+"报单券更新失败;<br>";
	}
	MoneyDao moneyDao2 = new MoneyDao();
	if(moneyDao2.updateForSql(sql2)==0){
		error+=ulist.get(i).getUserId()+"复消券更新失败;<br>";
	}
	MoneyDao moneyDao3 = new MoneyDao();
	if(moneyDao3.updateForSql(sql3)==0){
		error+=ulist.get(i).getUserId()+"奖金券更新失败;<br>";
	}
	
}
if(error.equals(""))
	out.println("编号更新成功。");
else out.println(error);

%>
