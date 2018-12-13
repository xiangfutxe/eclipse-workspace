<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.weixin.pojo.WeixinUserInfo"%>
<%@page import="com.pojo.Product" %>
<%@page import="java.util.*" %>
<%@page import="com.utils.Pager" %>
<%
//WeixinUserInfo sns_user = (WeixinUserInfo)request.getSession().getAttribute("snsUserInfo");
//if(null!=sns_user){
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
<link rel="stylesheet" href="css/order3.css?v=01291"><meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<meta content="telephone=no" name="format-detection">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1;user-scalable=no;">
<title>商品列表</title>
<script type="text/javascript">
$(document).ready(function(){
	var attr = parseInt($(".member_m_pic_1").height());
	var attr3 = parseInt($(".member_m_z_1").height());
	var h = attr - attr3;
	var clientWidth=document.body.clientWidth;
	$(".member_mp_t_img img").css("width",clientWidth*0.3);
	$(".member_mp_t_img img").css("height",clientWidth*0.3);
	
	handleUserPic();
});

function handleUserPic(){
	var th = $(".member_m_pic").outerHeight(true);
	if(th<60){
		setTimeout("handleUserPic",500);
	}else{
		$(".member_m_pic .img-circle").css("height",th*0.9);
		$(".member_m_pic .img-circle").css("width",th*0.9);
	}
	
}
</script>
</head>
<body>
<div class="fanhui_cou">
	<div class="fanhui_1"></div>
	<div class="fanhui_ding">顶部</div>
</div>
<header class="header">
	<div class="fix_nav">
		<div class="nav_inner">
			<a class="nav-left back-icon" href="javascript:history.back();">返回</a>
			<div class="tit">产品列表</div>
		</div>
	</div>
</header>

<div class="container" id="container2">
	<div class="row">
		<ul class="mod-filter clearfix">
			<div class="white-bg_2 bb1">
			
			<li id="default" class="active"><a
				title="默认排序"  href="javascript:void(0);">默认</a></li>
			<li id="buys"  ><a title="点击按销量从高到低排序"
				href="javascript:void(0);" >销量
				<i class='icon_sort'></i>
			</a></li>
			<li id="comments" ><a title="点击按发布时间从高到低排序"
				href="javascript:void(0);" >发布时间
				<i class='icon_sort'></i>
			</a></li>
			<li id="cash"  ><a title="点击按价格从高到低排序"
				href="javascript:void(0);" >价格
				<i class='icon_sort'></i>
			</a></li>
			</div>
		</ul>
		
		<div class="item-list" id="container" rel="2" status="0"><input type="hidden" id="ListTotal" value="1">
		 <%
 Pager pager = (Pager)request.getAttribute("pager");
						if(pager!=null){
							Collection coll = pager.getResultList();
							if(coll!=null){
									Iterator it1 = coll.iterator();
									while(it1.hasNext()){
									Product p = (Product)it1.next();
							%>
<a href="ProductServlet?method=detail&id=<%=p.getCode()%>">
				<div class="hproduct clearfix" style="background:#fff;border-top:0px;">
					<div class="p-pic"><img style="max-height:100px;margin:auto;" class="img-responsive" src="../upload/<%=p.getImageUrl1() %>" alt="暂无图片"></div>
					<div class="p-info">
						<p class="p-title"><%=p.getName() %></p>
						<p class="p-origin"><em class="price"><%=p.getPrice() %></em>
</p>
						</div>
				</div>
			</a>
		<%}}} %>
		</div>
		
		<div id="ajax_loading" style="display:none;width:300px;margin: 10px  auto 15px;text-align:center;">
			 <img src="images/loading.gif">
		</div>
		 <form  action='/m_search/prodlist' method="post" id="list_form">
				<input type="hidden" id="curPageNO" name="curPageNO"  value=""/>
			    <input type="hidden"  id="categoryId" name="categoryId" value="36" />
			    <input type="hidden" id="orders" name="orders"  value=""/>
			    <input type="hidden" id="hasProd" name="hasProd"  value="" />
			    <input type="hidden" id="keyword" name="keyword"  value="" />
			    <input type="hidden" id="prop" name="prop"  value="" />
		</form>
	</div>
</div>
<div class="clear"></div>

<footer class="footer">
  <div class="foot-con">
	<div class="foot-con_2">
		<a href="index.jsp">
			<i class="navIcon home"></i>
			<span class="text">店铺主页</span>
		</a>
		
		<a href="ProductServlet?method=list"   class="active">
			<i class="navIcon sort"></i>
			<span class="text">所有商品</span>		
		</a>
		<a href="shopcart.jsp" >
			<i class="navIcon shop"></i>
			<span class="text">购物车</span>
		</a>
		<a href="userhome.jsp">
			<i class="navIcon member"></i>
			<span class="text">会员主页</span>
		</a>
	</div>
	
  </div>
</footer></body>
</html>
<%//}%>