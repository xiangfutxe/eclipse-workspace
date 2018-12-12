<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.pojo.Branch"%>
<%@ page import="java.util.*"%>
<% 
List<Branch> plist = (List<Branch>) request.getAttribute("branch_list");

%>
<!DOCTYPE html>
<html>
<head>
<script charset="utf-8" src="js/jquery.min.js?v=01291"></script>
<script charset="utf-8" src="js/global.js?v=01291"></script>
<script charset="utf-8" src="js/bootstrap.min.js?v=01291"></script>
<script charset="utf-8" src="js/template.js?v=01291"></script>

<link rel="stylesheet" href="css/bootstrap.css?v=01291">
<link rel="stylesheet" href="css/style.css?v=1?v=01291">
<link rel="stylesheet" href="css/member.css?v=01291">
<link rel="stylesheet" href="css/order3.css?v=01291">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<meta content="telephone=no" name="format-detection">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1;user-scalable=no;">
<script charset="utf-8" src="js/shopCart.js?v=01291"></script>
<title>商家信息</title>
</head>
<body>
<div class="fanhui_cou">
	<div class="fanhui_1"></div>
	<div class="fanhui_ding">顶部</div>
</div> <header class="header header_1">
    <div class="fix_nav">
        <div class="nav_inner">
            <a class="nav-left back-icon" href="javascript:history.back();">返回</a>
            <div class="tit">商家信息</div>
        </div>
    </div>
</header>
<div class="container">
    <div class="row rowcar">
     <%if(plist!=null){
    		for(int i=0;i<plist.size();i++){
    		%>
        <ul class="list-group">
		            
		            <li class="list-group-item hproduct clearfix">
			                <div class="p-pic"><img class="img-responsive" src="../upload/<%=plist.get(i).getImageUrl()%>"></div>
			                <div class="p-info">
			                    <p class="p-title"><%=plist.get(i).getName()%></p>
		                    	<p class="p-attr">
			                    <span><%=plist.get(i).getProvince()%><%=plist.get(i).getCity()%><%=plist.get(i).getArea()%><%=plist.get(i).getAddress()%></span></p>
		                    	<p class="p-origin">
			                        <em class="price">¥<%=plist.get(i).getTel()%></em>
			                    </p>
			                </div>
		            </li>
					</ul>
					<%}}%>
					
		       </div>
</div>
<div class="clear"></div>

<footer class="footer">
  <div class="foot-con">
	<div class="foot-con_2">
		<a href="BranchServlet.do?method=index">
			<i class="navIcon home"></i>
			<span class="text">主页</span>
		</a>
		
		<a href="ProductServlet?method=list">
			<i class="navIcon sort"></i>
			<span class="text">购物</span>		
		</a>
		<a href="OrderServlet?method=orders" >
			<i class="navIcon shop"></i>
			<span class="text">订单</span>
		</a>
		<a href="userhome.jsp">
			<i class="navIcon member"></i>
			<span class="text">我的</span>
		</a>
	</div>
  </div>
</footer>
</body></html>

