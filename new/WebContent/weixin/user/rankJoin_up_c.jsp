<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.utils.Pager" %>
<%@page import="com.pojo.User" %>
<%@page import="com.pojo.OrderDetail" %>
<%@page import="com.pojo.Order" %>
<%@ include file="check.jsp" %>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User user = (User)request.getSession().getAttribute("sys_user");
User user1 = (User)request.getSession().getAttribute("user1");
String token = (String)request.getSession().getAttribute("token");
	if (user == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}
if(user1!=null){

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

<title>会员升级</title>

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
 <script type="text/javascript" src="../scripts/jquery.cityselect.js"></script>
</head>
<body>


<div class="top-deco"></div>

<div class="content">
	<div class="header">
    	<a href="#" class="homepage-logo">
        	<img src="../images/misc/index-logo.png" alt="img">
        </a>
        <p class="go-title-ch">
        	会员升级</p><p class="go-title-en">MEMBER RANK UP
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
              
                <form action="UserServlet.do?method=rankJoin_up_c" method="post"  id="myform">
                 <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
                    <fieldset>
                                 
                        
                         <table  class="table-detail">
           <tr>
                <td colspan="2"><h4>会员信息</h4></td>
            </tr>
             <tr>
                <td style="width:30%">会员编号:</td>
                <td><%=StringUtil.notNull(user1.getUserId()) %></td></tr>
            <tr>
             <tr>
                <td style="width:30%">会员名称:</td>
                <td><%=StringUtil.notNull(user1.getUserName()) %></td></tr>
            <tr>
                <td style="width:30%">原等级:</td>
                <td><%if(user1.getRankJoinOld()==1){ %>银级代理商<%}else if(user1.getRankJoinOld()==2){ %>
			金级代理商<%}else if(user1.getRankJoinOld()==3){ %>钻级代理商<%} %>
			</td></tr>
              <tr>
                <td style="width:30%">升级等级:</td>
                <td> <%if(user1.getRankJoin()==1){ %>银级代理商<%}else if(user1.getRankJoin()==2){ %>
			金级代理商<%}else if(user1.getRankJoin()==3){ %>钻级代理商<%} %>
			</td> </tr>
              
		</table> 
		 <div class="decoration"></div>
		 <h4 class="title"> <span> 产品清单 &nbsp;&nbsp;</span> </h4>
		  <table  class="table-detail">
		  <tr>
		  <th>产品编码<br>产品名称</th>
									<th>产品数量<br>类型</th>
									<th>金额小计</th>
									</tr>
											<%
								Order orders = (Order)request.getSession().getAttribute("orders");
							List<OrderDetail> coll =  (List<OrderDetail>)request.getSession().getAttribute("olist");
							if(coll!=null){
									for(int i=0;i<coll.size();i++){
									coll.get(i);
							%>
									<tr>
										<td><%if(coll.get(i).getType()==2){ %><a href="ProductServlet.do?method=product_detail&id=<%=coll.get(i).getProductId() %>" target="_blank"><%=coll.get(i).getProductId() %></a>
										<%}else{ %><%=coll.get(i).getProductId() %><%} %><br><%=coll.get(i).getProductName() %>
										</td>
										<td><%=coll.get(i).getNum()%><br>
										<%if(coll.get(i).getType()==2){ %>套装
										<%}else{ %><%=coll.get(i).getProductId() %><%} %>单品</td>
										<td><%=coll.get(i).getPrice() %>
										
										</td>
									</tr>
								<%}
						}else{ %><tr><td colspan="3">暂无产品，请联系商家添加产品！</td></tr> <%} %>
								<tr class="even">
									
							<td ></td><td>合计</td>
									<td><%=orders.getPrice() %><br><%=orders.getPv() %></td>
								</tr>
		  </table>
                     <div class="container">
			<p>
             <a id="btn1"  class="button button-blue">提交升级</a>
              <a href="javascript:histroy.go(-1);" class="button button-blue">返回上一页</a></p>
        </div>
                    </fieldset>
                </form>       
            </div>
        </div>
    </div>
     
</div>
 <div class="decoration"></div>
 <%@ include file="footer.jsp" %>  
<div style="height:28px;"></div>
<div class="bottom-deco"></div>


</body>
<script type="text/javascript">
	$(function() {
  	  $("#btn1").click(function(){
  	  if (!confirm("确认提交保存升级信息？")) {
				return false;
			}else{
		       		 $('#myform').submit();
		       		 return true;
		 }   
	   });
   
   
        });
        
   </script>
</html>
<%
}
%>