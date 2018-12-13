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


 <%@ include file="header.jsp" %>  

<div class="top-deco"></div>

<div class="content">
	<div class="header">
    	<a href="#" class="homepage-logo">
        	<img src="../images/misc/index-logo.png" alt="img">
        </a>
        <p class="go-title-ch">
        	会员列表</p><p class="go-title-en">USER LIST
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
       <table  class="table-detail">
            <tr>
                <th>用户编号<br>用户名</th>
                <th>归属人<br>归属区域</th>
                <th>注册时间<br>当前状态</th>
				<th>操作</th>
            </tr>
              <%
								 Pager pager = (Pager)request.getAttribute("pager");
						if(pager!=null){
							Collection coll = pager.getResultList();
							if(coll!=null){
									Iterator it = coll.iterator();
									int t =0;
									while(it.hasNext()){
									User user1 = (User)it.next();
									t++;
							%>
            <tr>
                <td><a href='UserServlet.do?method=user_list_detail&id=<%=user1.getId()%>'><%=StringUtil.notNull(user1.getUserId())%></a>
                <br><%=StringUtil.notNull(user1.getUserName())%></td>
                <td><%=StringUtil.notNull(user1.getUserByBelongId())%><br><%if(user1.getNodeTag()==1){%>A区<%}else if(user1.getNodeTag()==2) {%>B区<%}else if(user1.getNodeTag()==3){%>C区<% }else{%>未指定区域<%}%></td>
				<td><%=StringUtil.parseToString(user1.getEntryTime(), DateUtil.yyyyMMddHHmmss)%><br><%if(user.getState()==1){%>在线<%}else {%>下线<%}%></td>
				<td><a href='UserServlet.do?method=user_list_detail&id=<%=user1.getUserId()%>'>详情</a></td>
			</tr>
          	<%}}
						}else{ %><tr><td colspan="4">暂无记录！</td></tr><%} %>
        </table>
        </div>
   </div>      
     <% if(pager!=null){ %>
     <div class="one-half-responsive">
        <div class="container">
        <%if(pager.getPageNo()!=1){ %>
      <a href="UserServlet.do?method=user_list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=1" class="button-icon icon-home button-orange">首页</a>
        
       <a href="UserServlet.do?method=user_list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()-1%>" class="button-icon icon-left button-blue">上一页</a>
           <%} if(pager.getPageNo()!=pager.getPageCount()){ %>
   		 <a href="UserServlet.do?method=user_list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()+1%>" class="button-icon icon-right button-green">下一页</a>
       <a href="UserServlet.do?method=user_list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageCount()%>" class="button-icon icon-end button-magenta">尾页</a><%} %>
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
 <%} %>