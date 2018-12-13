<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.utils.Pager" %>
<%@page import="com.pojo.User" %>
<%@page import="com.pojo.Product" %>
<%@page import="com.pojo.ProductDetail" %>
<%@ include file="check.jsp" %>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User user = (User)request.getSession().getAttribute("sys_user");
Product product =(Product) request.getAttribute("product");
String message = (String)request.getAttribute("message");
	if (user == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else if(!StringUtil.notNull(message).equals("")){
	out.println("<script>");
		out.println("alert('"+message+"')");
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

<title>套装清单</title>

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
        	套装清单</p><p class="go-title-en">SET LIST
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
                <th>产品编号<br>产品名称</th>
                <th>产品价格<br>产品PV</th>
                 <th>产品规格<br>产品数量</th>
            </tr>
        <%
							Collection coll =(Collection)request.getAttribute("coll");;
							if(coll!=null){
									Iterator it1 = coll.iterator();
									int i=0;
									while(it1.hasNext()){
									ProductDetail p = (ProductDetail)it1.next();
									i++;
							%>
            <tr>
                <td><%=StringUtil.notNull(p.getProductId())%> <br><%=StringUtil.notNull(p.getProductName())%></td>
                <td> <%=p.getPrice()%><br><%=p.getPv()%></td>
                 <td> <%=StringUtil.notNull(p.getSpecification())%><br> <%=p.getNum()%></td>
             </tr>
							<%}
						}else{ %>
						 <tr>
						 <td colspan="3">暂无产品信息。</td>
						  </tr>
						 <%} %>
						 </table>
        </div>
   </div>      
  
       <div class="decoration"></div>
      <div class="container">
            <a href="javascript:window.close();" class="button  button-blue">关闭窗口</a>
        </div>
     
</div>
 <div class="decoration"></div>
 <%@ include file="footer.jsp" %> 
  <div style="height:28px;"></div>
<div class="bottom-deco"></div>


</body>
</html>
 <%} %>