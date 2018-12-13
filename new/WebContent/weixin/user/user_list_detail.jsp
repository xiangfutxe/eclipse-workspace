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
User new_user = (User)request.getAttribute("new_user");
	String psw2 = (String)request.getSession().getAttribute("psw2");
	if (user == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{


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

<title>会员管理</title>

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
<script type="text/javascript" src="../scripts/contact.js"></script>
<script type="text/javascript" src="../scripts/custom.js"></script>
<script type="text/javascript" src="../scripts/framework.js"></script>
<script type="text/javascript" src="../scripts/framework.launcher.js"></script>
</head>
<body>

<div id="preloader">
	<div id="status">
    	<p class="center-text">
			欢迎进入直销管理系统！！！
        </p>
    </div>
</div>

<div class="top-deco"></div>

<div class="content">
	<div class="header">
    	<a href="#" class="homepage-logo">
        	<img src="../images/misc/index-logo.png" alt="img">
        </a>
        <p class="go-title-ch">
        	会员详情</p><p class="go-title-en">USER DETAIL
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
    <div class="one-half-responsive last-column">
        <div class="container no-bottom">
          <table  class="table-detail">
           <tr>
                <td colspan="2"><h4>会员关系</h4></td>
            </tr>
            <tr>
                <td style="width:30%">会员等级:</td>
                <td><%if(new_user.getRankJoin()==1){ %>银卡会员<%}else if(new_user.getRankJoin()==2){ %>
			金卡会员<%}else if(new_user.getRankJoin()==3){ %>白金卡会员<%}else if(new_user.getRankJoin()==4){ %>钻卡会员<%}else if(new_user.getRankJoin()==5){ %>VIP<%} %></td>
            </tr>
              <tr>
                <td style="width:30%">会员等级:</td>
                <td><%if(new_user.getRankJoin()==1){ %>银卡会员<%}else if(new_user.getRankJoin()==2){ %>
			金卡会员<%}else if(new_user.getRankJoin()==3){ %>白金卡会员<%}else if(new_user.getRankJoin()==4){ %>钻卡会员<%}else if(new_user.getRankJoin()==5){ %>VIP<%} %></td>
            </tr>
              <tr>
                <td>服务商 :</td>
                <td><%=StringUtil.notNull(new_user.getUserByRefereeId())%> </td>
            </tr>
              <tr>
                <td>销售商</td>
                <td><%=StringUtil.notNull(new_user.getUserByBelongId())%></td>
            </tr>
              <tr>
                <td colspan="2"><h4>基本资料</h4></td>
            </tr>
            <tr>
                <td>会员编号：
                <td><%=StringUtil.notNull(new_user.getUserId())%></td></tr>
              <tr>
                <td>真实姓名：
                <td><%=StringUtil.notNull(new_user.getUserName())%></tr>
              <tr>
                <td>年龄 :</td>
                <td><%=new_user.getAge()%> </td>
            </tr>
              <tr>
                <td>性别：</td>
                <td><%=StringUtil.notNull(new_user.getSex())%></td>
            </tr>
            <tr>
                <td>证件类型：</td>
                <td><%=StringUtil.notNull(new_user.getDocumentType())%></td>
            </tr>
            <tr>
                <td>证件号码：</td>
                <td><%=StringUtil.notNull(new_user.getNumId())%></td>
            </tr>
            <tr>
                <td>联系电话：</td>
                <td><%=StringUtil.notNull(new_user.getTel())%></td>
            </tr>
            <tr>
                <td>固定电话：</td>
                <td><%=StringUtil.notNull(new_user.getPhone())%></td>
            </tr>
            <tr>
                <td>电子邮箱：</td>
                <td><%=StringUtil.notNull(new_user.getEmail())%></td>
            </tr>
            <tr>
                <td>QQ号码：</td>
                <td><%=StringUtil.notNull(new_user.getQq())%></td>
            </tr>
            <tr>
                <td>微信：</td>
                <td><%=StringUtil.notNull(new_user.getWeixin())%></td>
            </tr>
             <tr>
                <td>经营地区：</td>
                <td><%=StringUtil.notNull(new_user.getProvince())%><%=StringUtil.notNull(new_user.getCity())%><%=StringUtil.notNull(new_user.getArea())%></td>
            </tr>
             <tr>
                <td>联系地址：</td>
                <td><%=StringUtil.notNull(new_user.getAddress())%></td>
            </tr>
             <tr>
                <td colspan="2"><h4>银行账号</h4></td>
            </tr>
            <tr>
                <td style="width:30%">账户姓名：</td>
                <td><%=StringUtil.notNull(new_user.getAccountName())%></td>
            </tr>
              <tr>
                <td style="width:30%">银行卡号：</td>
                <td><%=StringUtil.notNull(new_user.getAccountId())%></td>
            </tr>
              <tr>
                <td>开户银行：</td>
                <td><%=StringUtil.notNull(new_user.getBankName())%></td>
            </tr>
            
             <tr>
                <td>开户网点：</td>
                <td><%=StringUtil.notNull(new_user.getBankAdr())%></td>
            </tr>
		</table>
        </div>
   </div>      
   </div>
</div>
 <div class="decoration"></div>
 <%@ include file="footer.jsp" %>  
<div style="height:28px;"></div>
<div class="bottom-deco"></div>


</body>
</html>
<%}%>