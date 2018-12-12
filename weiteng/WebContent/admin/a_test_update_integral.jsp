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
List<AccountDetail> alist = moneyMapper.selectByList("select * from "+Constants.INTEGRALDETAIL_TABLE+" order by id asc;");

int t=0;
int maxId = userMapper.maxId(params);
User[] user = new User[maxId+1];
for(int i=0;i<ulist.size();i++) {
	 int id = ulist.get(i).getId();
	 if(user[id]==null) user[id]=new User();
	 user[id].setId(ulist.get(i).getId());
	 user[id].setUserId(ulist.get(i).getUserId());
	 user[id].setUserName(ulist.get(i).getUserName());
	 user[id].setIntegral((double)0);
}
for(int i=0;i<alist.size();i++){
	int id =alist.get(i).getUid();
	user[id].setIntegral(ArithUtil.add(user[id].getIntegral(), alist.get(i).getAmount()));
	
		String sql1 = "update "+Constants.INTEGRALDETAIL_TABLE+" set balance='"+user[id].getIntegral()+"' where id='"+alist.get(i).getId()+"'";
		Integer up1 =moneyMapper.updateForSql(sql1);
		
	
		
}
for(int i=0;i<ulist.size();i++) {
	int id =ulist.get(i).getId();
	String sql1 = "update users set integral='"+user[id].getIntegral()+"' where id='"+ulist.get(i).getId()+"'";
	Integer up1 =moneyMapper.updateForSql(sql1);
}
sqlSession.commit();
}catch(Exception e){
	sqlSession.rollback();
	error  =e.getMessage();
}finally{
	sqlSession.close();
}
out.println("error:"+error);
 %>