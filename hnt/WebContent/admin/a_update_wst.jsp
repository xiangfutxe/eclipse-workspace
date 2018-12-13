<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.Admin" %>
<%@page import="com.ssm.dao.UserDao" %>
<%@page import="com.ssm.dao.AdminDao" %>
<%@page import="com.ssm.pojo.Address" %>
<%@page import="com.ssm.pojo.User" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>
<%@page import="com.ssm.mapper.DSettleMapper" %>
<%@page import="com.ssm.mapper.WRewardMapper" %>
<%@page import="com.ssm.mapper.UserMapper" %>
<%@page import="com.ssm.mapper.JoinInfoMapper" %>
<%@page import="com.ssm.mapper.OrderMapper" %>
<%@page import="com.ssm.mapper.SettleDayMapper" %>

<%@page import="org.apache.ibatis.session.SqlSession" %>
<%@page import="com.ssm.utils.SqlSessionFactoryUtils" %>

<%@page import="com.ssm.utils.Pager" %>
<%@page import="java.sql.Timestamp" %>
<%
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();

DSettleMapper wstMapper = sqlSession.getMapper(DSettleMapper.class);
	WRewardMapper wrdMapper = sqlSession.getMapper(WRewardMapper.class);
	UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
	OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
	  SettleDayMapper stMapper = sqlSession.getMapper(SettleDayMapper.class);
	   JoinInfoMapper jfMapper = sqlSession.getMapper(JoinInfoMapper.class);  
			try{	
				User user = userMapper.selectByUserId("CG00613858");
				if(user!=null){
				String[] str = user.getNode().split(",");
				sqlSession.commit();
				}finally {
			sqlSession.close();
    	}

%>
