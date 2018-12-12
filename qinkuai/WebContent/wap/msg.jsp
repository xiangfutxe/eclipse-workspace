<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.pojo.Branch"%>
<%@page import="com.pojo.Message" %>
<%@page import="com.utils.*" %>
<%@page import="java.util.*" %>
<%@page import="com.utils.Pager" %>
<%
Branch branch = (Branch)request.getSession().getAttribute("sys_branch");
String tag=(String)request.getAttribute("tag");
String state=(String)request.getAttribute("state");
if(branch==null){
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
<link rel="stylesheet" href="css/order3.css?v=01291">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<meta content="telephone=no" name="format-detection">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1;user-scalable=no;">
<title>留言信箱</title>
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
			<a class="nav-right add-icon" href="MessageServlet.do?method=add">留言</a>
			<div class="tit">
		留言信箱
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
									Message p = (Message)it1.next();
							%>
				  <ul class="list-group">
		            <li class="list-group-item hproduct clearfix">
					<p>
					       <span class="pull-left">留言内容：<%=p.getUserMsg()%></span>
					       </p>
					         <br>
					       <p>
					       <span class="pull-left">留言时间：<%=StringUtil.parseToString(p.getEntryTime(), DateUtil.yyyyMMddHHmmss)%></span>
					   <a  href="MessageServlet.do?method=del&id=<%=p.getId() %>" 
					   class="pull-right btn1 btn-default" onclick="javascript:if(!confirm('确认删除该留言？')) return false;">
					      
					       <span class="pull-right glyphicon gary"> 删除</span>
					       </a>
					       </p>
					         <br>
					       
		            </li>
		            <li class="list-group-item clearfix">
		                <div class="pull-left mt5">
		                	<span class="gary">回复：
		                <%if(StringUtil.notNull(p.getAdminMsg()).equals("")){ %>
		   	暂无回复。<%}else{ %><%=StringUtil.notNull(p.getAdminMsg()) %> <%} %>            
		                	</span>
		                	<br>
		                 <span class="gary">时间：<%=StringUtil.parseToString(p.getReplyTime(), DateUtil.yyyyMMddHHmmss)%></span>
		                	 
		                	 <br>
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
						href="MessageServlet.do?method=list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=1">首页</a>&nbsp;|&nbsp;<a
						href="MessageServlet.do?method=list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()-1%>">上一页</a>&nbsp;|&nbsp;<%} %>
                 <%if(pager.getPageNo()!=pager.getPageCount()) {%>
					<a href="MessageServlet.do?method=list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()+1%>">
                    下一页</a>&nbsp;|&nbsp;<a
						href="MessageServlet.do?method=list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageCount()%>">尾页</a>
				<%}else{ %>下一页&nbsp;|&nbsp;尾页<%} %>
<%} %>
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
		<a href="OrderServlet?method=orders">
			<i class="navIcon sort"></i>
			<span class="text">订单</span>
		</a>
		<a href="userhome.jsp"  class="active">
			<i class="navIcon member"></i>
			<span class="text">我的</span>
		</a>
	</div>
  </div>
</footer></body>
</html>
<%}%>