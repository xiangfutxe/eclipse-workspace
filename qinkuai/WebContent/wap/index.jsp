<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.pojo.News" %>
<%@page import="java.util.*" %>
<%@page import="com.utils.*" %>
<%
List<News> nlist = (List<News>) request.getAttribute("sys_news");
			if(nlist==null){
			out.println("<script>");
out.println("window.location.href='BranchServlet.do?method=index';");
out.println("</script>");
			}else{
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
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<meta content="telephone=no" name="format-detection">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1;user-scalable=no;">
<title>首页HOME</title>

</head>
<body>
<div class="container">
		    <div class="row">
        <div id="slide">
            <div class="hd">
                <ul><li class="on">1</li><li class="on">2</li><li class="on">3</li></ul>
            </div>
            <div class="bd">
                <div class="tempWrap" style="overflow:hidden; position:relative;">
                	<ul style="width:3840px; position: relative; overflow: hidden; padding: 0px; margin: 0px; transition-duration: 200ms; transform: translateX(-768px);">
	                	<li style="display: table-cell; vertical-align: top; width: 768px;">
	   	                    	<a href="contact_us.jsp" target="_blank">
	   	                    		<img src="img/banner1.jpg" alt="暂无图片" >
	   	                    	</a>
	   	                    </li>
	                	<li style="display: table-cell; vertical-align: top; width: 768px;">
	   	                    	<a href="contact_us.jsp" target="_blank">
	   	                    		<img src="img/banner2.jpg" alt="暂无图片" >
	   	                    	</a>
	   	                    </li>
	                	<li style="display: table-cell; vertical-align: top; width: 768px;">
	   	                    	<a href="contact_us.jsp" target="_blank">
	   	                    		<img src="img/banner3.jpg" alt="暂无图片">
	   	                    	</a>
	   	                    </li>
	                	</ul>
                </div>
            </div>
        </div>
    </div>
		
<script charset="utf-8" src="js/TouchSlide.js"></script>

<script type="text/javascript">
	
	TouchSlide({
		slideCell:"#slide",
		titCell:".hd ul", //å¼å¯èªå¨åé¡µ autoPage:true ï¼æ­¤æ¶è®¾ç½® titCell ä¸ºå¯¼èªåç´ åè£¹å±
		mainCell:".bd ul",
		effect:"left",
		autoPlay:true,//èªå¨æ­æ¾
		autoPage:true, //èªå¨åé¡µ
		switchLoad:"_src" //åæ¢å è½½ï¼çå®å¾çè·¯å¾ä¸º"_src"
	});
	</script>
<div class="row category">
        <a href="about_us.jsp" class="col-xs-4">
            <img  src="img/menu-01.png">
        </a>
        <a href="BranchServlet.do?method=list" class="col-xs-4">
            <img  src="img/menu-02.png">
        </a>
        <a href="ProductServlet?method=product_list" class="col-xs-4">
            <img  src="img/menu-03.png">
        </a>
         <a href="contact_us.jsp" class="col-xs-4">
            <img  src="img/menu-04.png">
        </a>
    </div>
    
<div class="row">
<div class="tb_box">
			<h2 class="tab_tit">
				最新动态</h2>
			
			<div class="tb_type tb_type_even clearfix">
			<ul>
			<% 
			for(int i=0;i<nlist.size();i++){
			%>
			<li class="col-xs-12"  style="margin-bottom:10px;margin-top:10px;">
			<a href="NewsServlet.do?method=detail&id=<%=nlist.get(i).getId()%>">
								[<%=StringUtil.parseToString(nlist.get(i).getEntryTime(), DateUtil.yyyyMMdd) %>]&nbsp;<%=StringUtil.notNull(nlist.get(i).getTitle()) %>
	                		</a>
			</li>
			<%} %>
			</ul>
						
						</div>
		</div>
		<!--äº§åå-->
		<div class="tb_box">
			<div class="tb_type tb_type_even clearfix">
					<a class="tb_floor" href="ProductServlet?method=list">
								<img src="img/2.jpg" style="width:100%;">
							</a>
						
						<a class="th_link tb_last" href="ProductServlet?method=list">
								<img class="tb_pic" src="img/1.jpg" style="width:100%;">
	                		</a>
						</div>
						<p></p>
						<div class="tb_type tb_type_even clearfix">
					<a class="tb_floor" href="ProductServlet?method=list">
								<img src="img/4.jpg" style="width:100%;">
							</a>
						
						<a class="th_link tb_last" href="ProductServlet?method=list">
								<img class="tb_pic" src="img/3.jpg" style="width:100%;">
	                		</a>
						</div>
		</div>
		<div class="tb_box">
			<p class="tab_tit">
				<img src="img/rec1.jpg" style="width:100%;">
				</p>
				<p class="tab_tit">
				<img src="img/rec3.jpg" style="width:100%;">
				</p>
		</div>
		
		</div>
	 
</div>
<!-- 
<div class="foot_index">
	<div class="foot_index_tit">Aim Beauty Limited(HongKong)</div>
	<div class="foot_index_rx">æå¡ç­çº¿ï¼020-87774389</div>
</div>
-->
<footer class="footer">
  <div class="foot-con">
	<div class="foot-con_2">
		<a href="BranchServlet.do?method=index" class="active">
			<i class="navIcon home"></i>
			<span class="text">主页</span>
		</a>
		
		<a href="ProductServlet?method=list">
			<i class="navIcon shop"></i>
			<span class="text">购物</span>		
		</a>
		<a href="OrderServlet?method=orders" >
			<i class="navIcon sort"></i>
			<span class="text">订单</span>
		</a>
		<a href="userhome.jsp">
			<i class="navIcon member"></i>
			<span class="text">我的</span>
		</a>
	</div>
  </div>
</footer>
<script type="text/javascript">
$(document).ready(function(){
	$("#slide img").each(function(){
	var img_src=$(this).attr("_src");
	$(this).attr("src",img_src);
	});
});

function searchproduct(){
	   var keyword = document.getElementById("keyword").value;
	   if(keyword == undefined || keyword==null ||keyword ==""){
	     alert("è¯·è¾å¥æç´¢å³é®å­ï¼");
	     return false;
	   }
	   document.getElementById("searchform").submit();
	}
</script>
</body></html>
<%} %>
