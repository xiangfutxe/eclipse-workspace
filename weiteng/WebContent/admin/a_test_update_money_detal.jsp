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
	 user[id].setIntegral(ulist.get(i).getIntegral());
	 user[id].setRefereeAll(StringUtil.notNull(ulist.get(i).getRefereeAll()));
	 user[id].setRefereeNode(StringUtil.notNull(ulist.get(i).getRefereeNode()));
	 user[id].setRefereeUserId(StringUtil.notNull(ulist.get(i).getRefereeUserId()));
	 user[id].setRefereeId(ulist.get(i).getRefereeId());
	 user[id].setVersion(ulist.get(i).getVersion());
	 user[id].setEntryTime(ulist.get(i).getEntryTime());
}
Timestamp endTime = new Timestamp(StringUtil.parseToDate("2018-11-23 02:00:00", DateUtil.yyyyMMddHHmmss).getTime());
String tableName = Constants.INTEGRALDETAIL_TABLE;
for(int i=ulist.size()-1;i>=0;i--) {
	 int id = ulist.get(i).getId();
	if(user[id].getEntryTime().getTime()-endTime.getTime()<0){
		user[id].setIntegral(ArithUtil.sub(user[id].getIntegral(),10));
		AccountDetail ad1 = cs.returnAccountDetail(user[id], 10, user[id].getIntegral(), 2, Constants.MONEY_DETAIL_YYPE_2, "会员关注补赠多发回扣", date);
		
			Map<String,Object> param1 = new HashMap<>();
			param1.put("account",ad1);
			param1.put("tableName", tableName);
			Integer up1 =moneyMapper.save(param1);
			if(up1!=null&&up1>0) {
				String sql="update users set integral=integral-10  where id='"+user[id].getId()+"'";
				Integer up2 = userMapper.updateForSql(sql);
				if(up2!=null&&up2>0) {
					if(user[id].getRefereeId()>0){
						int refereeId = user[id].getRefereeId();
						user[refereeId].setIntegral(ArithUtil.sub(user[refereeId].getIntegral(),10));
						AccountDetail ad2 = cs.returnAccountDetail(user[refereeId], 10, user[refereeId].getIntegral(), 2, Constants.MONEY_DETAIL_YYPE_2, "会员邀请补赠多发回扣", date);
						Map<String,Object> param2 = new HashMap<>();
						param2.put("account",ad2);
						param2.put("tableName", tableName);
						Integer up3 =moneyMapper.save(param2);
						if(up3!=null&&up3>0) {
							String sql1="update users set integral=integral-10  where id='"+user[refereeId].getId()+"'";
							Integer up4 = userMapper.updateForSql(sql1);
							if(up4!=null&&up4>0) {
								
							}else error+=user[refereeId].getUserId()+"邀请赠送失败;";
						}else error+=user[refereeId].getUserId()+"邀请赠送账户明细更新失败;";
					}
				}else error+=user[id].getUserId()+"会员关注赠送失败;";
		}else error+=user[id].getUserId()+"会员关注赠送账户明细更新失败;";
	}

}
sqlSession.commit();
}catch(Exception e){
	sqlSession.rollback();
}finally{
	sqlSession.close();
}
out.println("error:"+error);
 %>