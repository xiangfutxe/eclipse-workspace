<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ssm.pojo.WeixinUserInfo"%>
<%@page import="com.ssm.pojo.Product" %>
<%@page import="com.ssm.pojo.Order" %>
<%@page import="com.ssm.pojo.ProductSort" %>
<%@page import="java.util.*" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.ArithUtil" %>
<%@ page import="com.ssm.utils.Constants"%>
<%@page import="com.ssm.service.ICustomService" %>
<%@page import="com.ssm.service.CustomService" %>

<%
WeixinUserInfo user  = (WeixinUserInfo) request.getSession().getAttribute(Constants.USER_SESSION);
String idstr = (String)request.getAttribute("idstr");
List<Product> plist = (List<Product>) request.getSession().getAttribute("shop_cart_list");
ICustomService cs = new CustomService();
if(user==null){
out.println("<script>");
out.println("window.location.href='login.jsp';");
out.println("</script>");
}else{
	double total_price=0;

 %>
<!DOCTYPE html>
<html>
<head>
<script charset="utf-8" src="js/jquery.min.js?v=01291"></script>
<script charset="utf-8" src="js/global.js?v=01291"></script>
<script charset="utf-8" src="js/bootstrap.min.js?v=01291"></script>
<script charset="utf-8" src="js/template.js?v=01291"></script>
<script src="js/amazeui.min.js" type="text/javascript"></script>
<link href="css/amazeui.min.css" type="text/css" rel="stylesheet" />
<link href="css/style_p.css" type="text/css" rel="stylesheet" />
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
<title>零售购物</title>

<style>
body{
background:#FFFFFF;
}
</style>
</head>
<body>
<div class="fanhui_cou">
	<div class="fanhui_1"></div>
	<div class="fanhui_ding">顶部</div>
</div>
<header class="header">
	<div class="fix_nav">
		<div class="nav_inner">
			<a class="nav-left back-icon" href="javascript:history.back();"></a>
			<div class="tit">商品列表</div>
			<a class=" nav-right sort-icon" href="OrderServlet?method=orders"">订单</a>
		</div>
	</div>
</header>

 <form action="OrderServlet?method=shop_retail_list"  method="post" id="myform" name="myform">
  <input  type='hidden' name="tag" id="tag" value="1"/>
 
 <div class="content-list" id="outer">
	    	<div class="list-left" id="tab">
	    	<li <%if(StringUtil.notNull(idstr).equals("0")){%> class="current"<%} %>>
	    	<a href="OrderServlet?method=shop_retail_list&tag=1">全部</a></li>
	    	<%
	    	Collection coll_pt = (Collection) request.getAttribute("coll_pt");
	    		if(coll_pt!=null){
									Iterator it1 = coll_pt.iterator();
									while(it1.hasNext()){
									ProductSort p = (ProductSort)it1.next();
									
	    	 %>
	    		<li <%if(idstr.equals(String.valueOf(p.getId()))){%> class="current"<%} %>>
	    		<a href="OrderServlet?method=shop_retail_list&id=<%=p.getId()%>&tag=1"><%=p.getName() %></a>
	    		</li>
	    	<%}} %>
	    	</div>
	    	<div class="list-right" id="content">
	    		<ul class="list-pro">
	    		<%
	    	Collection coll = (Collection) request.getAttribute("coll");
	    		if(coll!=null){
									Iterator it1 = coll.iterator();
									while(it1.hasNext()){
									Product p = (Product)it1.next();
									boolean b=false;
									if(plist!=null){
										for(int i=0;i<plist.size();i++){
											if(plist.get(i).getId()-p.getId()==0){
												p.setNum(plist.get(i).getNum());
												b= true;
												break;
											}
										}
									}
									if(!b)p.setNum(0);
									total_price =  ArithUtil.add(total_price,ArithUtil.mul(p.getNum(),p.getPrice()));	
									
	    	 %>
			    	<li>
			    	<!--  	<img src="../upload/<%=p.getImageUrl() %>" style="width:80px" class="list-pic" />-->
			    		<div class="shop-list-mid">
			    		
		                	<div><a href="ProductServlet?method=product_detail&id=<%=p.getProductId()%>"><%=p.getProductName() %></a></div>
		                	<div><a href="ProductServlet?method=product_detail&id=<%=p.getProductId()%>">规格:<%=p.getSpecification()%>；&nbsp;库存:<%=StringUtil.decimalFormat(p.getTotalNum())%>；</a></div>
		                	
		                	<div class="am-gallery-desc">￥<%=StringUtil.decimalFormat(p.getPrice())%></div>
		                	
		                </div>
		                <div class="list-cart">
					                <div class="btn-group btn-group-sm control-num">
		                    <a onclick="disDe(<%=p.getId()%>);" href="javascript:void(0);" class="btn btn-default"><span class="glyphicon glyphicon-minus gary"></span></a>
		                    <input type="tel" id="num_<%=p.getId()%>" class="btn gary2 Amount" readonly="readonly" value="<%=p.getNum() %>" maxlength="4">
		                    <a onclick="increate(<%=p.getId()%>);" href="javascript:void(0);" class="btn btn-default"><span class="glyphicon glyphicon-plus gary"></span></a>
			                </div>
		                </div> 
			    	</li>
			    	<%}} %>
	            </ul>
	    	</div>
	    </div>
	    <script>
	$(function(){
		$("#total_price").text(<%=total_price%>);
		});
	</script>
<div class="clear"></div>
<footer class="footer">
  <div class="foot-btn">
   <div class="foot-left">
   <p> &nbsp;&nbsp;合计：<em class="red f22">¥<span  id="total_price"></span></em></p>
   </div>
  <div class="foot-right">
  
   <a href="OrderServlet?method=shop_retail_cart"  id="submitShopCart" class=" btn btn-danger">去结算</a>
           &nbsp;
           <a href="UserServlet?method=index"   class=" btn btn-warning">首页</a>  &nbsp;
   </div>
   </div>
</footer>
</form>
</body>
<script>
//购物数量加减
function increate(index) {
		$.ajax({
						type : "post",
						url : "OrderServlet?method=increate_ajax",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
						data : "tag="+index,
						dataType : "json",//设置需要返回的数据类型
						success : function(data) {
							var d = data;//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
							if(d.total_price!=null){
								$("#total_price").text(d.total_price);
								$("#num_"+index).attr("value",d.num);
							}
						},
						error : function() {
						alert("系统异常，请稍后重试！");
						}//这里不要加","
					});
	}
	
	function disDe(index) {

		$.ajax({
						type : "post",
						url : "OrderServlet?method=sub_ajax",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
						data : "tag="+ index,
						dataType : "json",//设置需要返回的数据类型
						success : function(data) {
							var d = data;//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
							if(d.total_price!=null){
								$("#total_price").text(d.total_price);
								$("#num_"+index).attr("value",d.num);
							}
						},
						error : function() {
						alert("系统异常，请稍后重试！");
						}//这里不要加","
					});
	}

</script>
</html>
<%}%>