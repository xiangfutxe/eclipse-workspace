<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.User" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.utils.Pager" %>
<%@page import="com.pojo.Address" %>
<%@ include file="check.jsp" %>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User user = (User)request.getSession().getAttribute("sys_user");
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

<title>地址管理</title>

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
<script type="text/javascript">
	function delcfm() {
		if (!confirm("确认要进行删除操作，该操作执行后将不可逆转？")) {
			return false;
		}
	}
</script>
</head>
<body>

<div class="top-deco"></div>

<div class="content">
	<div class="header">
    	<a href="#" class="homepage-logo">
        	<img src="../images/misc/index-logo.png" alt="img">
        </a>
        <p class="go-title-ch">
        	地址管理</p><p class="go-title-en">ADDRESS
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
     <div class="container">
            <a href="AddressServlet.do?method=add" class="button  button-blue">添加地址</a>
        </div>
       <table  class="table-detail">
            <tr>
                <th>收件人/收货地址</th>
                <th>联系电话/录入时间</th>
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
									Address ca = (Address)it.next();
									t++;
							%>
            <tr>
                <td><%=StringUtil.notNull(ca.getReceiver()) %><br><%=StringUtil.notNull(ca.getAddress()) %></td>
                <td><%=StringUtil.notNull(ca.getPhone()) %><br><%=StringUtil.parseToString(ca.getEntryTime(), DateUtil.yyyyMMddHHmmss)%></td>
                 <td> <a href="AddressServlet.do?method=edit&id=<%=ca.getId()%>">修改&nbsp;|&nbsp;</a> <a href="AddressServlet.do?method=del&id=<%=ca.getId()%>" onclick="delcfm();">删除 </a></td>
             </tr>
							<%}}
						}else{ %><tr>
							<td colspan="3">暂无记录！</td>
						 </tr>
						 <%} %>
						 </table>
        </div>
   </div>      
   <% if(pager!=null){ %>
     <div class="one-half-responsive">
        <div class="container">
         <%if(pager.getPageNo()!=1){ %>
      <a href="AddressServlet.do?method=list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=1" class="button-icon icon-home button-orange">首页</a>
        
       <a href="AddressServlet.do?method=list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()-1%>" class="button-icon icon-left button-blue">上一页</a>
        <%} if(pager.getPageNo()!=pager.getPageCount()){ %>
   		 <a href="AddressServlet.do?method=list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()+1%>" class="button-icon icon-right button-green">下一页</a>
       <a href="AddressServlet.do?method=list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageCount()%>" class="button-icon icon-end button-magenta">尾页</a> <%} %>
      </div>
      </div>
      <%} %>
       <div class="decoration"></div>
        <div class="container no-bottom">
    <p>温馨提示：如上述明细没有包含当日最新发生明细，请您稍后重查。</p>
      </div>
     
</div>
 <div class="decoration"></div>
 <%@ include file="footer.jsp" %>  <div style="height:28px;"></div>
<div class="bottom-deco"></div>


</body>
</html>
 <%} %>