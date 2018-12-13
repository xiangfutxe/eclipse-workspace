<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.Order" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.utils.Pager" %>
<%@page import="com.pojo.Admin" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
String token = (String)request.getSession().getAttribute("token");
Order orders = (Order)request.getAttribute("orders");
if(admin==null) {
out.println("<script>");
out.println("parent.window.location.href='login.jsp';");
out.println("</script>");
}else{
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[5][1].equals("1")||rankstr[5][2].equals("1")||admin.getState().equals("1")){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>收货地址修改</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	  <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
      <script type="text/javascript" src="js/jquery.js"></script>
     
  </head>
  
  <body>
  <div class="container clearfix">
   <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="admin/ProductServlet.do?method=list">订单管理</a><span class="crumb-step">&gt;</span><span>修改地址</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form action="admin/OrderServlet.do?method=admin_order_adr_update" method="post" id="myform" name="myform">
               <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
                <input type="hidden" name="id" id="id" value="<%=orders.getOrderId()%>"/> 
                    <table class="insert-tab" width="99%">
                        <tbody><tr>
                            <th width="120px"><i class="require-red">*</i>收件人：</th>
                                <td>
                                    <input class="common-text required" id="receiver" name="receiver" size="50" value="<%=orders.getReceiver()%>" type="text">&nbsp;<span id="reveiverTag"></span>
                                </td>
                            </tr>
                            <tr>
                                <th><i class="require-red">*</i>收货地址：</th>
                                <td><input class="common-text" name="address" id="address" size="50" type="text" value="<%=orders.getAddress() %>">&nbsp;<span id="addressTag"></span></td>
                            </tr>
                              <tr>
                                <th><i class="require-red">*</i>联系电话：</th>
                                <td><input class="common-text" name="phone" id="phone" size="50" type="text" value="<%=orders.getPhone()%>">&nbsp;<span id="phoneTag"></span></td>
                            </tr>
                            <tr>
                                <th></th>
                                <td>
                                    <input class="btn btn-primary mr10" value="确定修改" type="submit">
                                    <input class="btn" onClick="history.go(-1)" value="返回" type="button">
                                </td>
                            </tr>
                        </tbody></table>
                </form>
            </div>
        </div>

    </div>
    <!--/main-->
    </div>
  </body>
   <script type="text/javascript" src="js/custom.js"></script>
 <script language="JavaScript">
 var reg = /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/;
$(function() {
	
	$("#reveiver").blur(function() {
		if($("#reveiver").val()=="")$("#reveiverTag").text("收件人不能为空！").css({"color":"red","font-size":"12px"});
		else $("#reveiverTag").text("");
	});
	$("#address").blur(function() {
		if($("#address").val()=="")$("#addressTag").text("收货地址不能为空！").css({"color":"red","font-size":"12px"});
		else if(getByteLen($("#address").val())>100) $("#addressTag").text("产品名称字数不能大于50！").css({"color":"red","font-size":"12px"});
		else $("#addressTag").text("");
	});
	$("#phone").blur(function() {
		if($("#phone").val()=="")$("#phoneTag").text("联系电话不能为空！").css({"color":"red","font-size":"12px"});
		else if(getByteLen($("#phone").val())>15) $("#addressTag").text("联系电话格式有误！").css({"color":"red","font-size":"12px"});
		else $("#phoneTag").text("");
	});
	
	$("#sbtn").click(function(){
		
		var errorstr = "";
		if($("#reveiver").val()==""){
			$("#reveiverTag").text("收件人不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#reveiverTag").text()!=""){
			errorstr ="1";
		}
		
		if($("#address").val()==""){
			$("#addressTag").text("收货地址不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#addressTag").text()!=""){
			errorstr ="1";
		}
		
		if($("#phone").val()==""){
			$("#phoneTag").text("联系电话不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#phoneTag").text()!=""){
			errorstr ="1";
		}
		if(errorstr!=""){
			return false;
		}else{
			
       		 $('#myform').submit();
       		 return ture;
		
		}
	});
	
});
</script>
</html>
<%
}else{
			out.println("<script>");
			out.println("alert('你没有权限进行该操作！')");
			out.println("</script>");
		}
}
%>