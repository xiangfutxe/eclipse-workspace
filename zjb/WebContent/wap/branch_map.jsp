<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.pojo.Branch"%>
<%@ page import="java.util.*"%>
<%@page import="com.utils.StringUtil" %>

<% 
List<Branch> plist = (List<Branch>) request.getAttribute("branch_list");
Branch branch = (Branch) request.getAttribute("branch");
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
<title>选择一家门店</title>
  <link rel="stylesheet" href="https://cache.amap.com/lbs/static/main1119.css"/>
    <style type="text/css">
      #container{
        width: 100%;
        height: 450px;
       	margin-top: 50px
      } 
        #panel {
            position: absolute;
            background-color: white;
            max-height: 90%;
            overflow-y: auto;
            top: 50px;
            right: 10px;
            width: 280px;
            
        }
        
         #tbox {
            margin-top: 455px;
            width: 100%;
        }
    </style>
    <script type="text/javascript" src="https://webapi.amap.com/maps?v=1.4.8&key=08400e7504a01c074d748a531c8d19c9"></script>
    <script type="text/javascript" src="https://cache.amap.com/lbs/static/addToolbar.js"></script>
</head>
<body>
<div class="fanhui_cou">
	<div class="fanhui_1"></div>
	<div class="fanhui_ding">顶部</div>
</div> <header class="header header_1">
    <div class="fix_nav">
        <div class="nav_inner">
            <a class="nav-left back-icon" href="javascript:history.back();">返回</a>
            <div class="tit"><%=branch.getName() %>
            <input type="hidden" id="adr" name="adr" value="<%=branch.getProvince()+branch.getCity()+branch.getAddress()%>"/>
             </div>
        </div>
    </div>
</header>
<div class="container">
	<div id="container"></div>
	<div id="panel"></div>
	<div id="tbox">
<div class = "row">
<div class = "tb_box">		
	 <ul>
		            <li class="b-list">
			                <div class="b-info">
		          <span  class="b-title">店铺名称：</span>
		          <span class="b-right"><%=StringUtil.notNull(branch.getName())%>
		            </span>
		           </div>
		           </li>
		              
		         <li class="b-list">
		           <div class="b-info">
		          <span  class="b-title">负责人：</span>
		          <span class="b-right"><%=StringUtil.notNull(branch.getLinkman())%>
		            </span>
		           </div>
		           </li>
		             <li class="b-list">
		           <div class="b-info">
		          <span  class="b-title">联系电话：</span>
		          <span class="b-right"><%=StringUtil.notNull(branch.getTel())%>
		            </span>
		           </div>
		           </li>
					 <li class="b-list">
		           <div class="b-info">
		          <span  class="b-title">所在省市县：</span>
		          <span class="b-right"><%=StringUtil.notNull(branch.getProvince())%><%=StringUtil.notNull(branch.getCity())%><%=StringUtil.notNull(branch.getArea())%>
		            </span>
		           </div>
		           </li>
		            <li class="b-list">
		           <div class="b-info">
		          <span  class="b-title">详细地址：</span>
		          <span class="b-right"><%=StringUtil.notNull(branch.getAddress())%>
		            </span>
		           </div>
		           </li>
		           
					</ul>

	</div>
					
	</div>
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
</body>
<script type="text/javascript">
    var map = new AMap.Map("container", {
        resizeEnable: true,
    });
    AMap.service(["AMap.PlaceSearch"], function() {
        var placeSearch = new AMap.PlaceSearch({ //构造地点查询类
            pageSize: 1,
            pageIndex: 1,
          //city: "020", //城市
            map: map,
            panel: "panel"
        });
        //关键字查询
        var keyword = document.getElementById('adr').value;
        placeSearch.search(keyword+" 秦筷");
    });
</script>
</html>

