<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.User" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.utils.Pager" %>
<%@page import="com.pojo.AccountDetail" %>
<%@ include file="check.jsp" %>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User user = (User)request.getSession().getAttribute("sys_user");

String startTime = (String)request.getAttribute("startTime");
String type = (String)request.getAttribute("type");
double[] sum = (double[])request.getAttribute("sum");
String endTime = (String)request.getAttribute("endTime");
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

<title>欢迎首页</title>

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
</head>
<body>

<div class="top-deco"></div>

<div class="content">
	<div class="header">
    	<a href="#" class="homepage-logo">
        	<img src="../images/misc/index-logo.png" alt="img">
        </a>
        <p class="go-title-ch">
        	账户明细</p><p class="go-title-en">ACCOUNT DETAIL
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
    <h4><%if(type.equals("1")){ %>电子券账户<%}else if(type.equals("2")){ %>奖金券账户<%}else if(type.equals("3")){ %>积分券账户
    <%} %></h4>
       <table  class="table-detail">
            <tr>
                
                <th>交易日期<br>收支方式</th>
                <th>金额<br>交易类型</th>
                <th>账户余额<br>摘要</th>
            </tr>
           <%
								 Pager pager = (Pager)request.getAttribute("pager");
						if(pager!=null){
							Collection coll = pager.getResultList();
							if(coll!=null){
									Iterator it = coll.iterator();
									int t =0;
									while(it.hasNext()){
									AccountDetail ad = (AccountDetail)it.next();
									t++;
							%>
            <tr>
                <td><%=StringUtil.parseToString(ad.getTime(), DateUtil.yyyyMMddHHmmss) %><br><%if(StringUtil.notNull(ad.getPayType()).equals("1")){ %>
											收入
											<%}else if(StringUtil.notNull(ad.getPayType()).equals("2")){%>支出<%}else{%>-<%}%></td>
                <td><%=StringUtil.decimalFormat(ad.getAmount())%><br><%=StringUtil.notNull(ad.getTradeType()) %></td>
                 <td><%=StringUtil.decimalFormat(ad.getBalance())%><br><%=StringUtil.notNull(ad.getSummary()) %></td>
            </tr>
          <%}}%><tr>
								<td colspan='3'>累计总收入：<%=StringUtil.decimalFormat(sum[0])%><br>
								累计总支出：<%=StringUtil.decimalFormat(sum[1])%>
								</td>
							</tr>
						<%}else{ %>
						<tr>	<td colspan="3">暂无交易记录！</td></tr>
						<%} %>
        </table>
        </div>
   </div>      
    <% if(pager!=null){ %>
     <div class="one-half-responsive">
        <div class="container">
         <%if(pager.getPageNo()!=1){ %>
      <a href="AccountServlet.do?method=money_detail&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=1&type=<%=StringUtil.notNull(type)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>" class="button-icon icon-home button-orange">首页</a>
        
       <a href="AccountServlet.do?method=money_detail&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()-1%>&type=<%=StringUtil.notNull(type)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>">上一页</a>
        <%} if(pager.getPageNo()!=pager.getPageCount()){ %>
   		 <a href="AccountServlet.do?method=money_detail&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()+1%>
						&type=<%=StringUtil.notNull(type)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>" class="button-icon icon-right button-green">下一页</a>
       <a href="AccountServlet.do?method=money_detail&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageCount()%>&type=<%=StringUtil.notNull(type)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>" class="button-icon icon-end button-magenta">尾页</a><%} %>
      </div>
      </div>
      <%} %>
       <div class="decoration"></div>
        <div class="container no-bottom">
    <p>温馨提示：如上述明细没有包含当日最新发生明细，请您稍后重查。</p>
      </div>
     
</div>
 <div class="decoration"></div>
 <%@ include file="footer.jsp" %>  
<div style="height:28px;"></div>
<div class="bottom-deco"></div>


</body>
</html>
<%}%>