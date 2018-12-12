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
	if(ranks[5][0].equals("1")||admin.getState()-1==0){
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>威腾后台-订单查询</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/tip.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script src="js/calendar.js"></script>
<script type="text/javascript">
$(document).ready(function(){
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});

 $(".add_click").click(function(){
  $(".add_tip").fadeIn(200);
  });
  
  $(".add_tiptop a").click(function(){
  $(".add_tip").fadeOut(200);
});

  $(".add_sure").click(function(){
  $(".add_tip").fadeOut(100);
  $("#myform").attr("action","OrderServlet?method=admin_orders_add");
  $("#myform").submit();
});

  $(".add_cancel").click(function(){
  $(".add_tip").fadeOut(100);
});

$(".del_click").click(function(){
  $(".del_tip").fadeIn(200);
  });
  
  $(".del_tiptop a").click(function(){
  $(".del_tip").fadeOut(200);
});

  $(".del_sure").click(function(){
  $(".del_tip").fadeOut(100);
  $("#myform").attr("action","OrderServlet?method=admin_orders_del");
  $("#myform").submit();
});

  $(".del_cancel").click(function(){
  $(".del_tip").fadeOut(100);
});

  $("#ckAll").click(function() {
		if(this.checked){ 
			$("input[name='ids']").each(function(){this.checked=true;}); 
		}else{ 
			$("input[name='ids']").each(function(){this.checked=false;}); 
		} 
	});

	$("input[name='ids']").click(function() {
  var $ids = $("input[name='ids']");
  $("#ckAll").prop("checked" , $ids.length == $subs.filter(":checked").length ? true :false);
});
	
	$('#search_sub')
	.click(
			function() {
								
									var startTime = $("#startTime")
										.val();
								var endTime = $("#endTime").val();
								var str = "";
								var a = /((^((1[8-9]\d{2})|([2-9]\d{3}))(-)(10|12|0?[13578])(-)(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(11|0?[469])(-)(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(0?2)(-)(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)(-)(0?2)(-)(29)$)|(^([3579][26]00)(-)(0?2)(-)(29)$)|(^([1][89][0][48])(-)(0?2)(-)(29)$)|(^([2-9][0-9][0][48])(-)(0?2)(-)(29)$)|(^([1][89][2468][048])(-)(0?2)(-)(29)$)|(^([2-9][0-9][2468][048])(-)(0?2)(-)(29)$)|(^([1][89][13579][26])(-)(0?2)(-)(29)$)|(^([2-9][0-9][13579][26])(-)(0?2)(-)(29)$))/;
								
								if (startTime == "") {
									if(endTime!=""){
										str = str + "开始时间不能为空！\n";
										$("#startTime").focus();
									}
								}else if (endTime == "") {
									if(startTime!=""){
										str = str + "结束时间不能为空！\n";
										$("#endTime").focus();
									}
								} else if (startTime.match(a) == null) {
									str = str + "开始时间格式有误！\n";
									$("#startTime").focus();
								} else if (endTime.match(a) == null) {
									str = str + "结束时间格式有误！\n";
									$("#endTime").focus();
								}else if(startTime>endTime)
									{
									str = str + "开始时间不能超过结束时间！\n";
									$("#endTime").focus();
								}
								
								if (str == "") {
								$('#searchForm').attr("action","OrderServlet?method=admin_orders");
									$('#searchForm').submit();
								} else {
									alert(str);
									return false;
								}
			});
});
</script>


</head>


<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="index.jsp">首页</a></li>
    <li><a href="#">订单管理</a></li>
    <li><a href="#">订单查询</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    		<div id="serch" class="tabson">
    	<form  id ="searchForm" action="OrderServlet?method=admin_orders" method="post">
    <ul class="seachform">
    <li><label>订单编号</label><input name="orderId" type="text" class="scinput" value="${orderId}" /></li>
         <li><label>会员信息</label><input name="userId" type="text" class="scinput" value="${userId}" /></li>
     
     <li><label>状态:</label>
     <select id="state" name="state" class="dfinput1">
    <option value="">--所有状态--</option>
    <option value="0" <c:if test="${state=='0' }">selected</c:if>>--已退单--</option>
	<option value="1" <c:if test="${state=='1' }">selected</c:if>>--待收款--</option>
	<option value="2" <c:if test="${state=='2' }">selected</c:if>>--已付款--</option>
   </select>
     </li>
    <li><label>购物类型:</label>
     <select id="orderType" name="orderType" class="dfinput1">
    <option value="">--所有购物类型--</option>
	<option value="1"  <c:if test="${orderType=='1' }">selected</c:if>>--商城购物--</option>
    <option value="2" <c:if test="${orderType=='2' }">selected</c:if>>--特卖购物--</option>
   <option value="3" <c:if test="${orderType=='3' }">selected</c:if>>--积分购物--</option>
   </select>
                                </li>
                                
                                <li><label>支付方式:</label>
     <select id="payType" name="payType" class="dfinput1">
    <option value="">--所有支付类型--</option>
	<option value="1"  <c:if test="${payType=='1' }">selected</c:if>>--转账汇款--</option>
    <option value="2" <c:if test="${payType=='2' }">selected</c:if>>--向上级付款--</option>
   <option value="3" <c:if test="${payType=='3' }">selected</c:if>>--在线支付--</option>
   </select>
                                </li>
                                </ul>
          <ul class="seachform">                      
                                 <li><label>起止时间</label><input name="startTime" id="startTime" type="text" class="dfinput1" value="${startTime}" onfocus="new Calendar().show(this);"/>
     -<input name="endTime"  id="endTime" type="text" class="dfinput1" value="${endTime}" onfocus="new Calendar().show(this);"/></li>
    
     <li><input name="sub" id="search_sub" type="submit" class="scbtn" value="查询"/></li>
    
    </ul>
    </form>
    </div>
        <form id="myform" method="post">
   
    
    
    <table class="tablelist">
    	<thead>
    	<tr>
        <th><input  id="ckAll"  name="" type="checkbox" value=""/></th>
        <th>序号<i class="sort"><img src="images/px.gif" /></i></th>
    	<th>订单编号</th>
							<th>会员信息</th>
							<th>订单类型</th>
							<th>订单金额</th>
							<th>特买权个数</th>
							<th>代金券抵扣</th>
							<th>积分抵扣</th>
							<th>零售价</th>
							<th>支付方式</th>
							<th>订单日期</th>
							<th>状态</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
       
        <c:if test="${pager!=null}">
        <c:forEach items="${pager.resultList}" var="result" varStatus="status">
        <tr>
        <td><input name="ids" type="checkbox" value="${result.id}" /></td>
        <td>${status.index+1}</td>
        <td>${result.orderId}</td>
         <td>${result.userId}（${result.userName}）</td>
          <td><c:if test="${result.orderType==1}">商城购物</c:if>
          <c:if test="${result.orderType==2}">特卖购物</c:if>
          <c:if test="${result.orderType==3}">积分购物</c:if>
         </td>
         <td>${result.price}</td>
           <td>${result.cashNum}</td>
            <td>${result.cash}</td>
             <td>${result.integral}</td>
             <td>${result.retailPrice}</td>
               <td><c:if test="${result.payType==1}">转账汇款</c:if>
          <c:if test="${result.payType==2}">向上级汇款</c:if>
          <c:if test="${result.payType==3}">在线支付</c:if>
         </td>
        <td><fmt:formatDate value="${result.orderTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        <td><c:if test="${result.state==0}">已删除</c:if>
          <c:if test="${result.state==1}">待收款</c:if>
          <c:if test="${result.state==2}">已收款</c:if>
         </td>
        <td><a href="OrderServlet?method=admin_order_detail&id=${result.orderId}" class="tablelink">详情</a> </td>
        </tr>
        </c:forEach>
        </c:if>
      
      
        </tbody>
    </table>
     </form>
    <c:if test="${pager!=null}">
    <div class="pagin">
    
    	<div class="message">
    	<form  name="pageSizeForm"  action="OrderServlet?method=admin_orders" method="post">
    	<input type="hidden" name="pageNoStr" value="1"/>
    	<input type="hidden" name="state" value="${state}"/>  
    	<input type="hidden" name="orderType" value="${orderType}"/>
    	<input type="hidden" name="payType" value="${payType}"/>
    	<input type="hidden" name="orderId" value="${orderId}"/>
    	<input type="hidden" name="userId" value="${userId}"/>
    	<input type="hidden" name="startTime" value="${startTime}"/>  	
    	<input type="hidden" name="endTime" value="${endTime}"/>  	
    	第&nbsp;<i class="blue">${pager.pageNo}&nbsp;</i>页&nbsp;共&nbsp;<i class="blue">${pager.pageCount}&nbsp;</i>页&nbsp;共<i class="blue">${pager.rowCount}</i>条记录，当前每页显示
    	<select  name="pageSizeStr" id="pageSizeStr" onchange="javascript:pageSizeForm.submit();">
    	<option value="10" <c:if test="${pager.pageSize==10}">selected</c:if>>10</option>
    	<option value="20"  <c:if test="${pager.pageSize==20}">selected</c:if>>20</option>
    	<option value="40"  <c:if test="${pager.pageSize==40}">selected</c:if>>40</option>
    	<option value="80"  <c:if test="${pager.pageSize==80}">selected</c:if>>80</option>
    	<option value="100"  <c:if test="${pager.pageSize==100}">selected</c:if>>100</option>
    	</select>条记录
    	</form>
    	</div>
       
        <ul class="paginList">
       <c:if test="${pager.pageNo>1}">
        <li class="paginItem"><a href="OrderServlet?method=admin_orders&orderId=${orderId}&userId=${userId}&state=${state}&orderType=${orderType}&payType=${payType}&startTime=${startTime}&endTime=${endTime}&pageSizeStr=${pager.pageSize}&pageNoStr=${pager.pageNo-1}"><span class="pagepre"></span></a></li>
        </c:if>
         <c:if test="${pager.pageNo<=1}">
        <li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>
        </c:if>
      <c:if test="${pager.pageCount<=5}">
         <c:forEach var="p" begin="1" end="${pager.pageCount}" step="1">
         <c:if test="${p==pager.pageNo}"><li class="paginItem current" ><a href="javascript:;">${p}</a></li></c:if>
         <c:if test="${p!=pager.pageNo}"><li class="paginItem" ><a href="OrderServlet?method=admin_orders&orderId=${orderId}&userId=${userId}&state=${state}&orderType=${orderType}&payType=${payType}&startTime=${startTime}&endTime=${endTime}&pageSizeStr=${pager.pageSize}&pageNoStr=${p}">${p}</a></li></c:if>
         
         </c:forEach>
         </c:if>
            <c:if test="${pager.pageCount>5}">
                 <!-- 首页 -->
                  <c:if test="${1==pager.pageNo}"><li class="paginItem current" ><a href="javascript:;">1</a></li></c:if>
         <c:if test="${1!=pager.pageNo}"><li class="paginItem" ><a href="OrderServlet?method=admin_orders&orderId=${orderId}&userId=${userId}&state=${state}&orderType=${orderType}&payType=${payType}&startTime=${startTime}&endTime=${endTime}&pageSizeStr=${pager.pageSize}&pageNoStr=1">1</a></li></c:if>
         
          <!--中间部分 -->
            <c:if test="${pager.pageNo>3}">
	         <li class="paginItem more"><a href="javascript:;">...</a></li>
	         </c:if>
	         <c:if test="${pager.pageNo==1||pager.pageNo==2}">
	        <c:forEach var="p" begin="2" end="3" step="1">
         		 <c:if test="${p==pager.pageNo}"><li class="paginItem current" ><a href="javascript:;">${p}</a></li></c:if>
        	 <c:if test="${p!=pager.pageNo}"><li class="paginItem" ><a href="OrderServlet?method=admin_orders&orderId=${orderId}&userId=${userId}&state=${state}&orderType=${orderType}&payType=${payType}&startTime=${startTime}&endTime=${endTime}&pageSizeStr=${pager.pageSize}&pageNoStr=${p}">${p}</a></li></c:if>
       		 </c:forEach>
       		 </c:if>
       		    <c:if test="${pager.pageNo>2&&pager.pageCount-pager.pageNo>1}">
	       		 <c:forEach var="p" begin="${pager.pageNo-1}" end="${pager.pageNo+1}" step="1">
        		  <c:if test="${p==pager.pageNo}"><li class="paginItem current" ><a href="javascript:;">${p}</a></li></c:if>
        		 <c:if test="${p!=pager.pageNo}"><li class="paginItem" ><a href="OrderServlet?method=admin_orders&orderId=${orderId}&userId=${userId}&state=${state}&orderType=${orderType}&payType=${payType}&startTime=${startTime}&endTime=${endTime}&pageSizeStr=${pager.pageSize}&pageNoStr=${p}">${p}</a></li></c:if>
       		 </c:forEach>
       		 </c:if>
       		 
       		   <c:if test="${pager.pageNo==pager.pageCount||pager.pageNo==pager.pageCount-1}">
	        <c:forEach var="p" begin="${pager.pageCount-2}" end="${pager.pageCount-1}" step="1">
	          <c:if test="${p==pager.pageNo}"><li class="paginItem current" ><a href="javascript:;">${p}</a></li></c:if>
        		 <c:if test="${p!=pager.pageNo}"><li class="paginItem" ><a href="OrderServlet?method=admin_orders&orderId=${orderId}&userId=${userId}&state=${state}&orderType=${orderType}&payType=${payType}&startTime=${startTime}&endTime=${endTime}&pageSizeStr=${pager.pageSize}&pageNoStr=${p}">${p}</a></li></c:if>
       		 </c:forEach>
       		 </c:if>
       		 
       		   <c:if test="${pager.pageCount-pager.pageNo>=3}">
	         <li class="paginItem more"><a href="javascript:;">...</a></li>
	         </c:if>
       	 <!-- 尾页 -->
           <c:if test="${pager.pageCount==pager.pageNo}"><li class="paginItem current" ><a href="javascript:;">${pager.pageCount}</a></li></c:if>
         <c:if test="${pager.pageCount!=pager.pageNo}"><li class="paginItem" ><a href="OrderServlet?method=admin_orders&orderId=${orderId}&userId=${userId}&state=${state}&orderType=${orderType}&payType=${payType}&startTime=${startTime}&endTime=${endTime}&pageSizeStr=${pager.pageSize}&pageNoStr=${pager.pageCount}">${pager.pageCount}</a></li></c:if>
       </c:if>
     
         <c:if test="${pager.pageCount-pager.pageNo==0}">
        <li class="paginItem"><a href="javascript:;"><span class="pagenxt"></span></a></li>
        </c:if>
           <c:if test="${pager.pageCount-pager.pageNo>0}">
        <li class="paginItem"><a href="OrderServlet?method=admin_orders&orderId=${orderId}&userId=${userId}&state=${state}&orderType=${orderType}&payType=${payType}&startTime=${startTime}&endTime=${endTime}&pageSizeStr=${pager.pageSize}&pageNoStr=${pager.pageNo+1}"><span class="pagenxt"></span></a></li>
        </c:if>
        </ul>
    </div>
    </c:if>
    
    <div class="tip">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
      <div class="tipinfo">
        <span><img src="images/ticon_edit.png" /></span>
        <div class="tipright">
        <p>是否确认对信息的修改 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        <div class="tipbtn">
        <input name="" type="button"  class="sure" value="确定" />&nbsp;
        <input name="" type="button"  class="cancel" value="取消" />
        </div>
    </div>
    
     <div class="add_tip">
    	<div class="add_tiptop"><span>提示信息</span><a></a></div>
      <div class="add_tipinfo">
        <span><img src="images/ticon_add.png" /></span>
        <div class="add_tipright">
        <p>是否确认进入新增页面？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        <div class="add_tipbtn">
        <input name="" type="button"  class="add_sure" value="确定" />&nbsp;
        <input name="" type="button"  class="add_cancel" value="取消" />
        </div>
    </div>
    
     <div class="del_tip">
    	<div class="del_tiptop"><span>提示信息</span><a></a></div>
      <div class="del_tipinfo">
        <span><img src="images/ticon_del.png" /></span>
        <div class="del_tipright">
        <p>是否确认要删除所选信息？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        <div class="del_tipbtn">
        <input name="" type="button"  class="del_sure" value="确定" />&nbsp;
        <input name="" type="button"  class="del_cancel" value="取消" />
        </div>
    </div>
    
    
    </div>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
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