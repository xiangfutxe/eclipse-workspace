<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ssm.pojo.WeixinUserInfo"%>
<%@ page import="com.ssm.pojo.Address"%>
<%@ page import="com.ssm.pojo.User"%>

<%@ page import="java.util.*"%>
<%@ page import="com.ssm.utils.Constants"%>
<%@ page import="com.ssm.utils.Pager"%>


<%
WeixinUserInfo user  = (WeixinUserInfo) request.getSession().getAttribute(Constants.USER_SESSION);
User userinfo  = (User) request.getSession().getAttribute(Constants.USERINFO_SESSION);
if(null==user){
	out.println("<script>");
	out.println("window.location.href='login.jsp';");
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
<link rel="stylesheet" href="css/order3.css?v=01291"><meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<meta content="telephone=no" name="format-detection">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1;user-scalable=no;">
<title>地址管理</title>
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
			<a class="nav-left back-icon" href="javascript:history.back();"></a>
			
			<div class="tit">地址管理</div>
		</div>
	</div>
</header>

<div class="container ">
    <div class="row rowcar">
    <%
 Pager pager = (Pager)request.getAttribute("pager");
						if(pager!=null){
							Collection coll = pager.getResultList();
							if(coll!=null){
									Iterator it1 = coll.iterator();
									while(it1.hasNext()){
									Address adr = (Address)it1.next();
							%>
        <ul class="list-group">
		            <li class="list-group-item hproduct clearfix">
		           <a href="AddressServlet?method=select&tag=${sessionScope.tag}&id=<%=adr.getId()%>">
		            <p> <span  class="pull-left font-16"><%=adr.getReciver()%></span>
		            <span  class="pull-right font-16"><%=adr.getPhone()%>
		            </span>
		            </p>
		           
			         <br>
			         <p>
			                 <span class="pull-left"><%=adr.getProvince()%> <%=adr.getCity()%> <%=adr.getArea()%> <%=adr.getAddress()%></span></p>
		           </a>
		            </li>
		            
		            <li class="list-group-item clearfix">
		                <div class="pull-left mt5">
		                	<span class="gary"><input type="radio" name="adr_tag" value="<%=adr.getId()%>" <%if(adr.getTag()==1){ %>checked="checked"<%} %>/>&nbsp;设为默认地址</span>
		                </div>
						<div class="btn-group btn-group-sm control-num">
		                    <a onclick="javascript:if(!confirm('确定删除该信息？'))return true;" href="AddressServlet?method=del&id=<%=adr.getId()%>&tag=${sessionScope.tag}" class="btn1 btn-default"><span class="glyphicon gary">删除</span></a>
		                </div>
					</li>
					</ul>
					<%}}}%>
		       </div>

		 <div  class="pull-center mt5">
		
		 <%if(pager!=null){
			 if(pager.getRowCount()>pager.getPageSize()){
			 %>
		 
		 <span>
		 第<span style="font-color:red;"><%=pager.getPageNo()%></span>页&nbsp; 共<span style="font-color:red;"><%=pager.getPageCount()%></span>页&nbsp; 共<span style="font-color:red;"><%=pager.getRowCount()%></span>条记录&nbsp;
			 </span>
<%if(pager.getPageNo()==1) {%> 首页&nbsp;|&nbsp;上一页&nbsp;|&nbsp;<%}else{%>
					<a
						href="AddressServlet?method=list&tag=${sessionScope.tag }&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=1">首页</a>&nbsp;|&nbsp;<a
						href="AddressServlet?method=list&tag=${sessionScope.tag }&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()-1%>">上一页</a>&nbsp;|&nbsp;<%} %>
                 <%if(pager.getPageNo()!=pager.getPageCount()) {%>
					<a href="AddressServlet?method=list&tag=${sessionScope.tag }&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()+1%>">
                    下一页</a>&nbsp;|&nbsp;<a
						href="AddressServlet?method=list&tag=${sessionScope.tag }&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageCount()%>">尾页</a>
				<%}else{ %>下一页&nbsp;|&nbsp;尾页<%} %>
<%}} %>
		</div>
		</div>
<div class="clear"></div>

<footer class="footer">
  <div class="foot-con1">
	<div class="foot-con_3">
		<a href="AddressServlet?method=add">
			<span class="text">新增收货地址</span>
		</a>
	</div>
  </div>
</footer></body>
<script type="text/javascript">
$(function(){
	$('input[name="adr_tag"]').click(function(){	
		$.ajax({
					type : "post",
					url : "AddressServlet?method=updateTag",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
					data : "id="+$(this).val(),
					dataType : "json",//设置需要返回的数据类型
					success : function(data) {
						var d = data;//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
						alert("成功");
					},
					error : function() {
						alert("系统异常，请稍后重试！");
					}//这里不要加","
				});
});
</script>
</html>
<%}%>