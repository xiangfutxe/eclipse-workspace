<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import="com.ssm.pojo.Admin"%>
<%@page import="com.ssm.utils.StringUtil"%>
<%@page import="com.ssm.utils.Constants"%>

<%
Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
if (admin == null) {
	out.println("<script>");
	out.println("parent.window.location.href='login.jsp';");
	out.println("</script>");
}else{
 String[][] ranks = StringUtil.getRankStr(admin.getRank());
	if(ranks[5][2].equals("1")||admin.getState()-1==0){
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>威腾后台-配货出库</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/tip.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
</head>


<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="index.jsp">首页</a></li>
    <li><a href="#">订单管理</a></li>
     <li><a href="#">配货查询</a></li>
    <li><a href="#">配货单详情</a></li>
    </ul>
    </div>
            	
   <form  id ="myform" method="post">
     <input type="hidden" name="token" id="token" value="${sessionScope.token}"/>
         <input type="hidden" name="id" id="id" value="${orders.id}"/>
         <input type="hidden" name="orderId" id="id" value="${orders.orderId}"/>
     
    <div class="rightinfo">
    
    <table class="table-detail">
   
        <tbody>
        <tr>
        <td>订单编号</td>
        <td>${orders.orderId}</td>
        <td>订单类型</td>
		<td><c:if test="${orders.orderType==1}">商城购物</c:if>
          <c:if test="${orders.orderType==2}">特卖购物</c:if>
          <c:if test="${orders.orderType==3}">积分购物</c:if>
          </td>
								<td>订单金额</td><td>${orders.price}
								</td>
								<td>当前状态</td>
								<td><c:if test="${orders.state==0}">已退单</c:if>
	          <c:if test="${orders.state==1}">待确认</c:if>
	          <c:if test="${orders.state==2}">待发货</c:if>
	           <c:if test="${orders.state==3}">已发货</c:if>
	            <c:if test="${orders.state==4}">已签收</c:if></td>
							</tr>
							<tr>
								<td>会员信息
								</td>
								<td>${orders.userId}（${orders.userName}）
								</td>
								<td>申请时间</td>
								<td>
				<fmt:formatDate value="${orders.orderTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
								</td>
								<td>审核人
								</td>
								<td>${orders.reviewerId}
								</td>
								<td>审核时间</td>
								<td>
				<fmt:formatDate value="${orders.reviewTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							</tr>
							<tr>
								<td>收款人
								</td>
								<td>${orders.receiver}
								</td>
								<td>联系电话</td>
								<td>
									${orders.phone}			</td>
								<td>收货地址
								</td>
								<td colspan="3">${orders.province}${orders.city}${orders.area}${orders.address}
								</td>
							</tr>
      
        </tbody>
    </table>
    <table class="tablelist">
   
    	<thead>
    	<tr>
        <th>序号<i class="sort"><img src="images/px.gif" /></i>
        </th>
      
         
    	<th>产品编号</th>
							<th>产品名称</th>
							<th>规格</th>
							<th>单价</th>
							<th>数量</th>
							<th>总额</th>
        </tr>
        </thead>
        <tbody>
       
        <c:if test="${coll!=null}">
        <c:forEach items="${coll}" var="result" varStatus="status">
        <tr>
        <td>${status.index+1}</td>
        <td>${result.productId}
        </td>
         <td>${result.productName}</td>
          <td>${result.specification}
          </td>
          <td>${result.productPrice}</td>
		<td>${result.num}</td>
         <td>${result.price}</td>
        </tr>
        </c:forEach>
        </c:if>
      
      
        </tbody>
    </table>
    <div  class="tabson">
     <ul class="forminfo">
        <li>        
        <input id="back_click"  type="button" class="btn" value="返回上一页" onclick="javascript:history.go(-1);"/></li>
    </ul>
    </div>
    
      <div class="save_tip">
    	<div class="save_tiptop"><span>提示信息</span><a></a></div>
      <div class="save_tipinfo">
        <span><img src="images/ticon_add.png" /></span>
        <div class="save_tipright">
        <p>是否确认该订单已收款？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        <div class="save_tipbtn">
        <input name="" type="button"  class="save_sure" value="确定" />&nbsp;
        <input name="" type="button"  class="save_cancel" value="取消" />
        </div>
    </div>
    
    </div>
    
     </form>
	<script type="text/javascript">
$(document).ready(function(){
	$('.tablelist tbody tr:odd').addClass('odd');
	
	$("#yes_click").click(function(){
		  $(".save_tip").fadeIn(200);
		  });
		  
  $(".save_tiptop a").click(function(){
  $(".save_tip").fadeOut(200);
});

  $(".save_sure").click(function(){
	  $(".save_tip").fadeOut(100);
  
		$('#myform').attr("action", "OrderDeliveryServlet?method=admin_order_review_yes");
		 	$('#myform').submit();
		 
 
});

  $(".save_cancel").click(function(){
  $(".save_tip").fadeOut(100);
});


});
</script>
</body>
</html>
<%
	}else{
		out.println("<script>");
		out.println("window.location.href='error_rank.jsp';");
		out.println("</script>");
	}
}
%>