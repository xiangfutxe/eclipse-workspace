<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.ssm.pojo.Admin"%>
<%@ page import="com.ssm.pojo.User"%>
<%@ page import="com.ssm.pojo.AccountDetail"%>

<%@ page import="com.ssm.dao.UserDao"%>
<%@ page import="com.ssm.mapper.UserMapper"%>
<%@ page import="com.ssm.mapper.MoneyMapper"%>
<%@ page import="com.ssm.utils.Constants"%>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>
<%@page import="com.ssm.utils.ArithUtil" %>

<%@page import="com.ssm.service.*" %>
<%@page import="org.apache.ibatis.session.SqlSession" %>
<%@page import="com.ssm.utils.SqlSessionFactoryUtils" %>
<%@page import="java.util.Date" %>
<%@page import="java.sql.Timestamp" %>
<%@page import="java.util.Map" %>
<%@page import="java.util.HashMap" %>
<%@page import="java.util.List" %>





<%

SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
ICustomService cs = new CustomService();
UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
MoneyMapper moneyMapper = sqlSession.getMapper(MoneyMapper.class);
Timestamp date = new Timestamp(new Date().getTime());
String error="";

try{
Map<String,Object> params = new HashMap<>();
List<User> ulist = userMapper.selectUserByListForUpdate(params);
int t=0;
int maxId = userMapper.maxId(params);
User[] user = new User[maxId+1];
for(int i=0;i<ulist.size();i++) {
	 int id = ulist.get(i).getId();
	 if(user[id]==null) user[id]=new User();
	 user[id].setId(ulist.get(i).getId());
	 user[id].setUserId(ulist.get(i).getUserId());
	 user[id].setUserName(ulist.get(i).getUserName());
	 user[id].setCashNum(0);
	 user[id].setIntegral(ulist.get(i).getIntegral());
	 user[id].setRefereeAll(StringUtil.notNull(ulist.get(i).getRefereeAll()));
	 user[id].setRefereeNode(StringUtil.notNull(ulist.get(i).getRefereeNode()));
	 user[id].setRefereeUserId(StringUtil.notNull(ulist.get(i).getRefereeUserId()));
	 user[id].setRefereeId(ulist.get(i).getRefereeId());
	 user[id].setVersion(ulist.get(i).getVersion());
	 user[id].setEntryTime(ulist.get(i).getEntryTime());
}
String tableName = Constants.CASHNUMDETAIL_TABLE;
List<AccountDetail> alist= moneyMapper.selectByList("select * from "+tableName+" order by id asc");

for(int i=0;i<alist.size();i++) {
	int id= alist.get(i).getUid();
	if(user[id]!=null)
		out.println("balance:"+alist.get(i).getBalance());
	user[id].setCashNum(alist.get(i).getBalance().intValue());
}
for(int i=0;i<ulist.size();i++) {
	 int id = ulist.get(i).getId();
	if(user[id]!=null){
		out.println("ulist:"+user[id].getCashNum());
		userMapper.updateForSql("update users set cash_num='"+user[id].getCashNum()+"' where id='"+user[id].getId()+"'");
	}
}
out.println("alist:size:"+alist.size());
sqlSession.commit();
}catch(Exception e){
	e.printStackTrace();
	sqlSession.rollback();
}finally{
	
	sqlSession.close();
}

out.println("error:"+error);
 %>