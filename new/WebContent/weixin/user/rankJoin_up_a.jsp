<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.User" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.utils.Pager" %>
<%@ include file="check.jsp" %>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User user = (User)request.getSession().getAttribute("sys_user");
String token = (String)request.getSession().getAttribute("token");
		String psw2 = (String)request.getSession().getAttribute("psw2");
	if (user == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
User user1 = (User)request.getAttribute("user1");
%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<link rel="apple-touch-icon-precomposed" sizes="114x114" href="../images/splash/splash-icon.png">
<link rel="apple-touch-startup-image" href="../images/splash/splash-screen.png" 			media="screen and (max-device-width: 320px)" />  
<link rel="apple-touch-startup-image" href="../images/splash/splash-screen_402x.png" 		media="(max-device-width: 480px) and (-webkit-min-device-pixel-ratio: 2)" /> 
<link rel="apple-touch-startup-image" sizes="640x1096" href="../images/splash/splash-screen_403x.png" />
<link rel="apple-touch-startup-image" sizes="1024x748" href="../images/splash/splash-screen-ipad-landscape" media="screen and (min-device-width : 481px) and (max-device-width : 1024px) and (orientation : landscape)" />
<link rel="apple-touch-startup-image" sizes="768x1004" href="../images/splash/splash-screen-ipad-portrait.png" media="screen and (min-device-width : 481px) and (max-device-width : 1024px) and (orientation : portrait)" />
<link rel="apple-touch-startup-image" sizes="1536x2008" href="../images/splash/splash-screen-ipad-portrait-retina.png"   media="(device-width: 768px)	and (orientation: portrait)	and (-webkit-device-pixel-ratio: 2)"/>
<link rel="apple-touch-startup-image" sizes="1496x2048" href="../images/splash/splash-screen-ipad-landscape-retina.png"   media="(device-width: 768px)	and (orientation: landscape)	and (-webkit-device-pixel-ratio: 2)"/>

<title>经销商升级</title>

<link href="../styles/style.css"     		rel="stylesheet" type="text/css">
<link href="../styles/framework.css" 		rel="stylesheet" type="text/css">
<link href="../styles/owl.carousel.css" 	 rel="stylesheet" type="text/css">
<link href="../styles/owl.theme.css" 		rel="stylesheet" type="text/css">
<link href="../styles/swipebox.css"		 rel="stylesheet" type="text/css">
<link href="../styles/colorbox.css"		 rel="stylesheet" type="text/css">


<script type="text/javascript" src="../scripts/jquery.js"></script>
<script type="text/javascript" src="../scripts/jqueryui.js"></script>
<script type="text/javascript" src="../scripts/owl.carousel.min.js"></script>
<script type="text/javascript" src="../scripts/jquery.swipebox.js"></script>
<script type="text/javascript" src="../scripts/colorbox.js"></script>
<script type="text/javascript" src="../scripts/snap.js"></script>
<script type="text/javascript" src="../scripts/rankJoin.js"></script>
<script type="text/javascript" src="../scripts/custom.js"></script>
<script type="text/javascript" src="../scripts/framework.js"></script>
</head>
<body>


<div class="top-deco"></div>

<div class="content">
	<div class="header">
    	<a href="#" class="homepage-logo">
        	<img src="../images/misc/index-logo.png" alt="img">
        </a>
        <p class="go-title-ch">
        	经销商升级</p><p class="go-title-en">JOIN UP
        </p>
         <a href="javascript:history.go(-1);" class="go-logo">返回</a>
        <a href="main.jsp" class="go-home">首页</a>
        <a href="#" class="go-menu">菜单</a>
        <a href="#" class="go-back">关闭</a>
    </div>
    <div class="decoration"></div>
    <div class="navigation">
    	<div class="corner-deco"></div>
    	<div class="navigation-wrapper">
           <%@ include file="menu.jsp" %>  
        </div>
    </div>
</div>
<div class="content">
    <div class="container">
    <div class="container no-bottom">
            <div class="contact-form no-bottom"> 
                <form action="UserServlet.do?method=rankJoin_up_a"  id="myform" name="myform" method="post" class="contactForm" >
             	<input type="hidden" name="userId" id="userId" value="<%=user1.getUserId()%>"> 	
              <table  class="table-detail">
                     <tr>
                <td >经销商编号：</td>
                            <td> <%=StringUtil.notNull(user1.getUserId()) %></td>
                       </tr>
                        <tr>
                <td >经销商名称：</td>
                            <td> <%=StringUtil.notNull(user1.getUserName()) %></td>
                       </tr>
                         <tr>
                              <tr>
                <td >当前等级：</td>
                            <td> 
                               <%if(user1.getRankJoin()==1){ %>银级代理
                                  <%}else if(user1.getRankJoin()==2){ %>金级代理
                                  <%}else if(user1.getRankJoin()==3){ %>钻级代理
								  <%} %>
								      <input type="hidden" name="rankJoinOld" value="<%=user1.getRankJoin() %>"/>
                        <tr>
               			 <td >升级等级：</td>
                            <td> 
                                <select id="rankJoin" name="rankJoin" class="contactField">
										 <%if(user1.getRankJoin()==0){ %>
										  <option value="1">银级代理</option>
										    <option value="2">金级代理</option>
										  <option value="3">钻级代理</option>
										 
										    <%}else if(user1.getRankJoin()==1){ %>
										    <option value="2">金级代理</option>
										  <option value="3">钻级代理</option>
										  <%}else if(user1.getRankJoin()==2){ %>
										  <option value="3">钻级代理</option>
										  <%}else if(user1.getRankJoin()==3){ %>
										  <option value="">-你当前为最高等级-</option>
										  <%} %>
										  </select>
                              </td></tr>
                              </table>
                              </form>
                       <div> <span id="rankJoinmsg"></span>
                        </div> 
                         <div>
                          <input id="sbtn"  value="提交" type="button" class="buttonWrap button button-green contactSubmitButton">
                        </div>
                        
            </div>
        </div>
    </div>
    
  
    <div class="decoration"></div>
</div>
 <%@ include file="footer.jsp" %>  
<div style="height:28px;"></div>
<div class="bottom-deco"></div>


</body>
<script type="text/javascript">
$(function() {
	$("#sbtn").click(function(){
	 if (!confirm("确认提交升级加盟级别？")) {
				return false;
			}else{
		var errorstr = "";
		if($("#rankJoin").val()==""){
			$("#rankJoinmsg").text("未选择需要加盟的等级！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}else if($("#rankJoin").val()==$("#rankJoinTag").val()){
			$("#rankJoinmsg").text("最新等级与原等级相同，请核对后再试！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		
		if(errorstr!=""){
			return false;
		}else{
       		 $('#myform').submit();
       		 return true;
		
		}
		}
	});
});
</script>
</html>
<%}%>