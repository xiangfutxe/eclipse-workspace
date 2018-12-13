<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.weixin.pojo.SNSUserInfo"%>
<!DOCTYPE html>
<html>
<head>

<title>OAuth2.0网页授权</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,user-scalable=0">
<style type="text/css">
*{margin:0;padding:0}

table{border:1px dashed #B9B9DD;font-size:12pt}
td{border:1px dashed #B9B9DD;word-break:break-all;word-wrap:break-word;}
</style>
</head>
 
<body>
<%
SNSUserInfo sns_user = (SNSUserInfo)request.getAttribute("snsUserInfo");
String message = (String) request.getAttribute("message");
out.println(message);
if(null!=sns_user){
 %>
 <table width="100%" cellspacing="0" cellpadding="0">
 <tr>
 <td width="20%">属性</td><td width="80%">值</td>
 </tr>
 <tr>
 <td>OpenId</td> <td><%=sns_user.getOpenId() %></td>
 </tr>
  <tr>
 <td>昵称</td> <td><%=sns_user.getNickName() %></td>
 </tr>
  <tr>
 <td>性别</td> <td><%=sns_user.getSex()%></td>
 </tr>
  <tr>
 <td>国家</td> <td><%=sns_user.getCountry() %></td>
 </tr>
  <tr>
 <td>省份</td> <td><%=sns_user.getProvince()%></td>
 </tr>
  <tr>
 <td>城市</td> <td><%=sns_user.getCity() %></td>
 </tr>
  <tr>
 <td>头像</td> <td><%=sns_user.getHeadImgUrl() %></td>
 </tr>
  <tr>
 <td>特权</td> <td><%=sns_user.getPrivilegeList() %></td>
 </tr>
 </table>
 <%}else out.println("未获取到用户信息");
  %>
</body>
 
</html>