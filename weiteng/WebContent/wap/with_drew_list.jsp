<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ssm.pojo.WeixinUserInfo"%>
<%@ page import="com.ssm.pojo.Order"%>

<%@page import="com.ssm.utils.*" %>
<%@page import="java.util.*" %>
<%@page import="com.ssm.utils.Pager" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
int atag=4;
WeixinUserInfo user  = (WeixinUserInfo) request.getSession().getAttribute(Constants.USER_SESSION);
String state=StringUtil.notNull((String)request.getAttribute("state"));
if(user==null){
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
<title>提现申请</title>
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
			<a class="nav-left back-icon" href="UserServlet?method=user_home">返回</a>
			<div class="tit">提现记录
			</div>
			<a class=" nav-right user-icon" href="UserServlet?method=user_home">个人中心</a>
						
		</div>
	</div>
</header>

		   <table class="tablelist">
    	<thead>
    	<tr>
        <th>序号<i class="sort"></i></th>
    	<th>提现金额</th>
							<th>手续费</th>
							<th>到账金额</th>
							<th>申请时间/状态</th>
        </tr>
        </thead>
        <tbody>
       
        <c:if test="${pager!=null}">
        <c:forEach items="${pager.resultList}" var="result" varStatus="status">
        <tr>
        <td>${status.index+1}</td>
        <td>${result.amount}</td>
         <td>${result.fee}</td>
           <td>${result.actualAmount}</td>
          <td> <fmt:formatDate value="${result.applyTime}" pattern="yyyy-MM-dd"/>
          <br>
          <c:if test="${result.state==0}">已删除</c:if>
          <c:if test="${result.state==1}">待审批</c:if>
          <c:if test="${result.state==2}">已通过</c:if>
         </td>
        </tr>
        </c:forEach>
        </c:if>
      
      
        </tbody>
    </table>
    <c:if test="${pager!=null}">
    <div class="pagin">
    
    	<div class="message">
    	<form  name="pageSizeForm"  action="WithDrewServlet?method=list" method="post">
    
    	第&nbsp;<i class="blue">${pager.pageNo}&nbsp;</i>页&nbsp;共&nbsp;<i class="blue">${pager.pageCount}&nbsp;</i>页&nbsp;共<i class="blue">${pager.rowCount}</i>条记录&nbsp;
    	
       <c:if test="${pager.pageCount>1}">
        <a class="fontSize-14" href="WithDrewServlet?method=list&pageSizeStr=${pager.pageSize}&pageNoStr=1">首页</a>&nbsp;|&nbsp;
	        <c:if test="${pager.pageNo>1}">
	      <a class="fontSize-14" href="WithDrewServlet?method=list&pageSizeStr=${pager.pageSize}&pageNoStr=${pager.pageNo-1}">上一页</a>&nbsp;|&nbsp;
		        <c:if test="${pager.pageNo!=pager.pageCount}">
		    <a href="javascript:;">下一页</a>&nbsp;|&nbsp;
		        </c:if>
	        </c:if>
	  <a class="fontSize-14" href="WithDrewServlet?method=list&pageSizeStr=${pager.pageSize}&pageNoStr=${pager.pageCount}">尾页</a>
	        
        </c:if>
            	</form>
        </div>
    </div>
    </c:if>
<div class="clear"></div>
<div class="clear"></div>
<div class="clear"></div>
<footer class="footer">
  <div class="foot-con1">
	<div class="foot-con_3">
		<a href="WithDrewServlet?method=add">
			<span class="text">新增提现</span>
		</a>
	</div>
  </div>
</footer>
</body>
</html>
<%}%>