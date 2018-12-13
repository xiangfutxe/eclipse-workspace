<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.User" %>
<%@page import="com.ssm.utils.StringUtil"%>
<%@ include file="check.jsp" %>  

<%
User user = (User)request.getSession().getAttribute("sys_user");
String message = (String) request.getAttribute("message");
	if (user == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>最新动态</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="Assets/css/reset.css"/>
<script type="text/javascript" src="Assets/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="Assets/js/js_z.js"></script>
<script type="text/javascript" src="Assets/js/main.js"></script>
<link rel="stylesheet" type="text/css" href="css/thems.css">
<link rel="stylesheet" type="text/css" href="css/table.css">
<link rel="stylesheet" type="text/css" href="css/form.css">
<script type="text/javascript" src="js/other.js"></script>
<script src="js/jquery.slider.js"></script>
<link rel="stylesheet" href="fonts/fontCss.css">
	<link rel="stylesheet" href="css/jquery.slider.css">
	      <script type="text/javascript" src="js/jquery.cityselect.js"></script>
	<script>
	window.onload = function(){
		$('.testSlider').slider({
			originType:'tuoyuan'
		});
		$('.testSlider1').slider({
			width:300,
			height:300,
			showSlider: false,
			showOrigin: false,
			originPosition:'center'
		});

		$('.testSlider3').slider({
			width:1094,
			height:300,
			originPosition:'center',
			source: [
							{
								src: 'img/slider1.jpg',									// 幻灯片图片地址		
								hasHref: true,												// 是否有链接
								href: '#',			// 链接地址					
								hrefTarget: '_self'											// 链接打开方式  _self, _blank, _top等
 							},
						]
		});
	};
</script>
  </head>
  
 <body>
 <!--头部-->
 <jsp:include page="header.jsp" />
<div class="main_bg">
    <!--幻灯片-->
    <div class="banner1">
            <span class="testSlider3" style="display: inline-block;"></span>
    </div>
  
	<!--幻灯片END-->
	<div class="main_bg">
	<div class="second">   
        <div class="scd_m clearfix">
        	<div class="scd_ml">
            	<ul class="sidenav">
                  <li><a href="OrderServlet?method=orders">订单列表</a></li> 
				<li><a href="OrderServlet?method=order_divery">服务订单</a></li> 
				<li><a  href="OrderServlet?method=order_emoney_add">零售购物</a></li>
				<li class="now"><a href="OrderServlet?method=order_dmoney_add">复消购物</a></li>
                </ul>
                                <div style="height:300px;"></div>
                
                </div>
		 <div class="scd_mr">
            	<div class="ht_table">
            	<h2>当前位置：订单中心-&gt;复消购物-&gt;<a>信息提示</a></h2>
            	</div>
            		<div class="content content-full-width">
                	<div class="notice">
         <h2>信息提示</h2><span class="required_notification"></span>
   </div>
   
							<div class="p-li">
								 <%=StringUtil.notNull(message)%>
							</div>
							<div class="p-li">
							<span class="left"> <a href="OrderServlet?method=order_dmoney_add"> 复消购物</a>&nbsp;或&nbsp;<a href="OrderServlet?method=orders"> 订单列表</a></span>
							</div>
  </div>
					</div>
         </div> 
               </div>
            </div>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>
<%}%>