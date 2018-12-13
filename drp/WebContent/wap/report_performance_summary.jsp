<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.pojo.Branch" %>
<%@page import="com.pojo.Product" %>
<%@page import="java.util.List" %>
<%
//WeixinUserInfo sns_user = (WeixinUserInfo)request.getSession().getAttribute("snsUserInfo");
//if(null!=sns_user){
Branch branch = (Branch)request.getSession().getAttribute("sys_branch");
String startTime = (String)request.getAttribute("startTime");
String endTime = (String)request.getAttribute("endTime");
Integer totalNum = (Integer) request.getAttribute("total_num");
Double totalPrice =(Double) request.getAttribute("total_price");
if(branch==null){
out.println("<script>");
out.println("window.location.href='login.jsp';");
out.println("</script>");
}else{
String message = (String)request.getAttribute("message");
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
<link rel="stylesheet" href="css/style_login.css">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<meta content="telephone=no" name="format-detection">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1;user-scalable=no;">
<script charset="utf-8" src="js/shopCart.js?v=01291"></script>
<script src="../js/calendar.js"></script>
<style>
INPUT 
{ 
border-left: #ccc 1px solid; 
border-top: #ccc 1px solid; 
border-right: #ccc 1px solid; 
border-bottom: #ccc 1px solid; 
margin: 0px 0px 10px 10px; 
padding-left: 10px; 
float: left; 
font-size: 1.5em; 
line-height: 1.5em; 
height: 30px; 
text-align: left; 
} 
</style>

<title>订单汇总</title>
</head>
<body>
<div class="fanhui_cou">
	<div class="fanhui_1"></div>
	<div class="fanhui_ding">顶部</div>
</div> <header class="header header_1">
    <div class="fix_nav">
        <div class="nav_inner">
            <a class="nav-left back-icon" href="javascript:history.back();">返回</a>
            <div class="tit">订单汇总</div>
        </div>
    </div>
</header>
<div class="container" >
	 <div class="row rowcar">
	 	<form id="myform" action="ReportServlet.do?method=report_performance_summary" class="login" method="post">

	   <ul class="list-group">
		            <li class="list-group-item hproduct clearfix">
		         
					时间段:<%=StringUtil.notNull(startTime)%>至<%=StringUtil.notNull(endTime) %>
				
				</li>
				  <li class="list-group-item hproduct clearfix">
				  <div class="col-xs-3 ">产品编号/产品名称</div>
				  <div class="col-xs-3 ">产品数量／产品单价</div>
				 <div class="col-xs-3 ">小计</div>
				  </li>
				  <%List<Product> plist = (List<Product>)request.getAttribute("sys_plist");
				  if(plist!=null){
				  for(int i=0;i<plist.size();i++){
				   %>
				     <li class="list-group-item hproduct clearfix">
				   <div class="col-xs-3 "><%=StringUtil.notNull(plist.get(i).getProductId())%><br>
				  <%=StringUtil.notNull(plist.get(i).getProductName()) %></div>
				   <div class="col-xs-3 "><%=plist.get(i).getTotalNum() %><br>
				  <%=StringUtil.decimalFormat(plist.get(i).getPrice()) %></div>
				   <div class="col-xs-3 "><%=StringUtil.decimalFormat(plist.get(i).getTotalPrice()) %>
				  </div>
				  </li>
				  <%}%>
				   <li class="list-group-item hproduct clearfix">
				   <div class="col-xs-3 ">合计</div>
				   <div class="col-xs-3 "><%=totalNum %></div>
				   <div class="col-xs-3 "><%=totalPrice %></div>
				  </li>
				  <%} %>
				<li class="list-group-item hproduct clearfix">
					<div class="login_psdNav">&nbsp;</div>
					<div class="login_submit">
						 <a href="userhome.jsp"   class=" btn btn-warning">返回会员首页</a>&nbsp;
						  <a href="report_performance_time.jsp"  class=" btn btn-success">重新查询</a>
						  
						</div>
						<div class="login_submit"><span id="msg"></span></div>
						<div class="login_psdNav">&nbsp;</div>
		            </li>
		            </ul>
		            </form>
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
			<i class="navIcon shop"></i>
			<span class="text">购物</span>		
		</a>
		<a href="OrderServlet?method=orders" >
			<i class="navIcon sort"></i>
			<span class="text">订单</span>
		</a>
		<a href="userhome.jsp"  class="active">
			<i class="navIcon member"></i>
			<span class="text">我的</span>
		</a>
	</div>
  </div>
</footer>
</body>
<script type="text/javascript">
$(document).ready(function(){
	$("#slide img").each(function(){
	var img_src=$(this).attr("_src");
	$(this).attr("src",img_src);
	});
	<%if(!StringUtil.notNull(message).equals("")){
	%>
	 $("#msg").text("<%=message%>").css({"color":"red","font-size":"12px"});
	<%}%>
	$("#sub_btn").click(function(){
	var error = 0;
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	var str = "";
	var a = /((^((1[8-9]\d{2})|([2-9]\d{3}))(-)(10|12|0?[13578])(-)(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(11|0?[469])(-)(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(0?2)(-)(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)(-)(0?2)(-)(29)$)|(^([3579][26]00)(-)(0?2)(-)(29)$)|(^([1][89][0][48])(-)(0?2)(-)(29)$)|(^([2-9][0-9][0][48])(-)(0?2)(-)(29)$)|(^([1][89][2468][048])(-)(0?2)(-)(29)$)|(^([2-9][0-9][2468][048])(-)(0?2)(-)(29)$)|(^([1][89][13579][26])(-)(0?2)(-)(29)$)|(^([2-9][0-9][13579][26])(-)(0?2)(-)(29)$))/;
	if (startTime == "") {
			if(endTime!=""){
			str = str + "开始时间不能为空！\n";
			error=1;
			$("#startTime").focus();
			}
	}else if (endTime == "") {
			if(startTime!=""){
			str = str + "结束时间不能为空！\n";
			error=1;
			$("#endTime").focus();
			}
	} else if (startTime.match(a) == null) {
		str = str + "开始时间格式有误！\n";
		error=1;
		$("#startTime").focus();
	} else if (endTime.match(a) == null) {
		str = str + "结束时间格式有误！\n";
		error=1;
		$("#endTime").focus();
	}else if(startTime>endTime){
		str = str + "开始时间不能超过结束时间！\n";
		error=1;
		$("#endTime").focus();
	}
	
	if (error>0) {
	$("#msg").text(str).css({"color":"red","font-size":"12px"});
			return false;
		} else {
			 $('#myform').submit();
       		 return true;
		}
	});
});
</script>
</html>
<%}%>