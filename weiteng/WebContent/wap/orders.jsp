<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ssm.pojo.WeixinUserInfo"%>
<%@ page import="com.ssm.pojo.Order"%>
<%@ page import="com.ssm.pojo.User"%>
<%@page import="com.ssm.utils.*" %>
<%@page import="java.util.*" %>
<%@page import="com.ssm.utils.Pager" %>
<%
int atag=3;
WeixinUserInfo user  = (WeixinUserInfo) request.getSession().getAttribute(Constants.USER_SESSION);
String state=StringUtil.notNull((String)request.getAttribute("state"));
User userinfo  = (User) request.getSession().getAttribute(Constants.USERINFO_SESSION);
if(null==user||userinfo==null){
	out.println("<script>");
	out.println("window.location.href='login.jsp';");
	out.println("</script>");
}else if(StringUtil.notNull(userinfo.getNickName()).equals("")){
	out.println("<script>");
	out.println("window.location.href='UserServlet?method=user_edit';");
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
<title>订单列表</title>
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
			<div class="tit">
			<%if(state.equals("")){ %>订单列表
			<%}else if(state.equals("1")){ %>待确认<%}else if(state.equals("2")){ %>待发货
			<%}else if(state.equals("3")){ %>出库中<%}else if(state.equals("4")){ %>已发货
			<%} %>
			</div>
		</div>
	</div>
</header>

<div class="container" >
	 <div class="row rowcar">
		 <%
 Pager pager = (Pager)request.getAttribute("pager");
						if(pager!=null){
							Collection coll = pager.getResultList();
							if(coll!=null){
									Iterator it1 = coll.iterator();
									while(it1.hasNext()){
									Order p = (Order)it1.next();
							%>
<a href="OrderServlet?method=detail&id=<%=p.getOrderId()%>">
				  <ul class="list-group">
		            <li class="list-group-item hproduct clearfix">
					 <p> <span  class="pull-left font-14"><%=p.getOrderId() %></span>
		            <span  class="pull-right font-14"><%=StringUtil.parseToString(p.getOrderTime(), DateUtil.yyyyMMddHHmmss)%>
		            </span>
		            </p>
			         <br>
					         <br>
					       <p>
					        <span  class="pull-left font-14">
					        
					        <%if(p.getOrderType()==1){%>商城购物
					        <%}else if(p.getOrderType()==2){ %>特卖购物
					        <%}else if(p.getOrderType()==3){ %>积分购物
					        <%} %>
					        /<%if(p.getState()==1){%>待支付
					        <%}else if(p.getState()==2){ %>已收款
					         <%}else if(p.getState()==0){ %>已退单
					        <%}else{ %>-<%} %></span>
					       </p>
		            </li>
		            <li class="list-group-item clearfix">
		                <div class="pull-left mt5">
		                	<span class="gary">合计金额：¥<%=p.getPrice() %></span>
		                </div>
						<div class="btn-group btn-group-sm control-num">
		                    <a  href="OrderServlet?method=order_detail&id=<%=p.getOrderId() %>" class="btn1 btn-orders">
		                   <span class="glyphicon gary"> 详情</span></a>
		                   <%if(p.getState()==1) {%>
		                     <a onclick='javascript:if(!confirm("确认进行删除该订单？")) return false;' href="OrderServlet?method=order_back&id=<%=p.getId() %>&orderId=<%=p.getOrderId() %>" class="btn1 btn-orders"><span class="glyphicon gary">退单</span></a>
		                 <% }%>
		                </div>
					</li>
					</ul>
					<%}}}%>
		</div>
		
		 <div  class="pull-center mt5">
		
		 <%if(pager!=null){ %>
		 <span>
		 第<span style="font-color:red;"><%=pager.getPageNo()%></span>页&nbsp; 共<span style="font-color:red;"><%=pager.getPageCount()%></span>页&nbsp; 共<span style="font-color:red;"><%=pager.getRowCount()%></span>条记录&nbsp;
			 </span>
<%if(pager.getPageNo()==1) {%> 首页&nbsp;|&nbsp;上一页&nbsp;|&nbsp;<%}else{%>
					<a
						href="OrderServlet?method=orders&state=<%=StringUtil.notNull(state)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=1">首页</a>&nbsp;|&nbsp;<a
						href="OrderServlet?method=orders&state=<%=StringUtil.notNull(state)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()-1%>">上一页</a>&nbsp;|&nbsp;<%} %>
                 <%if(pager.getPageNo()!=pager.getPageCount()) {%>
					<a href="OrderServlet?method=orders&state=<%=StringUtil.notNull(state)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()+1%>">
                    下一页</a>&nbsp;|&nbsp;<a
						href="OrderServlet?method=orders&state=<%=StringUtil.notNull(state)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageCount()%>">尾页</a>
				<%}else{ %>下一页&nbsp;|&nbsp;尾页<%} %>
<%} %>
		</div>
		</div>
<div class="clear"></div>

<%@include file ="footer.jsp" %>
</body>
</html>
<%}%>