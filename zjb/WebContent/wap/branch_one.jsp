<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.pojo.Branch"%>
<%@ page import="java.util.*"%>
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
    <script type="text/javascript" src="https://webapi.amap.com/maps?v=1.4.8&key=08400e7504a01c074d748a531c8d19c9&plugin=AMap.CitySearch"></script>
    <script type="text/javascript" src="https://cache.amap.com/lbs/static/addToolbar.js"></script>
 <style type="text/css">
      #container{
        width: 100%;
        height: 250px;
       margin: 0px
      } 
       #txt{
        width: 100%;
      	margin-top: 210px
      }
    </style>
</head>
<body  onload="showCityInfo()">
<div class="fanhui_cou">
	<div class="fanhui_1"></div>
	<div class="fanhui_ding">顶部</div>
</div> <header class="header header_1">
    <div class="fix_nav">
        <div class="nav_inner">
            <a class="nav-left back-icon" href="javascript:history.back();">返回</a>
            <div class="tit">选择一家门店</div>
        </div>
    </div>
</header>
<div class="container ">
<div id="container"></div>
<div id="tip"></div>
<div id = "txt"> 
<div class = "row">
<div class = "tb_box">
        <ul>
           <%if(branch!=null){%>
        <li class="b-list">
			                <div class="b-info">
			                    <p><span  class="b-title"><%=branch.getProvince()%>[<%=branch.getCity()%>]</span> 
			                    <span class="b-attr"> 有<%=branch.getTag()%>家门店</span> <span class="b-right"><img src="images/bottom-tag.png" style="width:15px;height:15px"/></span>
			                    </p>
			                </div>
		            </li>
     <%
     if(plist!=null){
    		for(int i=0;i<plist.size();i++){
    		%>
		            <li class="b-list-1">
			                <div  class="b-left">
			                <div class="b-info">
			                    <div class="b-title"><%=plist.get(i).getName()%></div>
			                    <div class="b-attr"> <%=plist.get(i).getAddress()%></div>
			               </div>
			               </div>
			                 <div  class="b-right">
			                 <a href="BranchServlet.do?method=branch_map&code=<%=plist.get(i).getCode()%>">
			                 <img src="images/open.png" style="width:30px;height:30px"/>
			                  <img src="images/right-tag.png" style="width:15px;height:15px"/>
			                  </a>
			                   </div>
		            </li>
					
					<%}
					}}
					else{%>
					  <li class="b-list">
			                <div class="b-info">
			                    <p><span  class="b-title">暂无商家信息！</span> 
			                   <span class="b-right"><img src="images/bottom-tag.png" style="width:15px;height:15px"/></span>
			                    </p>
			                </div>
		            </li>
					<%} %>
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
        zoom: 14,
    });
    //获取用户所在城市信息
    function showCityInfo() {
        //实例化城市查询类
        var citysearch = new AMap.CitySearch();
        //自动获取用户IP，返回当前城市
        citysearch.getLocalCity(function(status, result) {
            if (status === 'complete' && result.info === 'OK') {
                if (result && result.city && result.bounds) {
                    var cityinfo = result.city;
                    var citybounds = result.bounds;
                    document.getElementById('tip').innerHTML = '您当前所在城市：'+result.province+cityinfo;
                    //地图显示当前城市
                    map.setBounds(citybounds);
                }
            } else {
                document.getElementById('tip').innerHTML = result.info;
            }
        });
    }
</script>
</html>

