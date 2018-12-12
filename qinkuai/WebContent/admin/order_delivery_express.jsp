<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.Order" %>
<%@page import="com.pojo.Inventory" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.utils.Pager" %>
<%@page import="com.pojo.Admin" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		Order orders = (Order)request.getAttribute("orders");
if(admin==null) {
out.println("<script>");
out.println("parent.window.location.href='login.jsp';");
out.println("</script>");
}
else{
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[5][2].equals("1")||admin.getState().equals("1")){
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>录入快递信息</title>
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
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="OrderServlet.do?method=order_delivery">发货管理</a><span class="crumb-step">&gt;</span><span>录入快递信息</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form method="post" id="myform" name="myform" >
               <input type="hidden" name="id" id="id" value='<%=orders.getOrderId()%>'/>
                    <table class="insert-tab" width="97%">
                        <tbody>
                            <tr>
                                <th width="150px"><i class="require-red">*</i>快递公司：</th>
                                <td>
                                    <input class="common-text required" id="express" name="express"  value="" type="text">&nbsp;<span id="expressTag"></span>
                                </td>
                            </tr>
                             <tr>
                                <th><i class="require-red">*</i>快递单号：</th>
                                <td>
                                    <input class="common-text required" id="expressNum" name="expressNum"  value="" type="text">&nbsp;<span id="expressNumTag"></span>
                                </td>
                            </tr>
                             <tr>
                                <th><i class="require-red">*</i>快递费：</th>
                                <td>
                                    <input class="common-text required" id="expressFee" name="expressFee"  value="" type="text">&nbsp;<span id="expressFeeTag"></span>
                                </td>
                            </tr>
                            <tr>
                            <th><i class="require-red">*</i>选择仓库:</th>
								<td><select class="common-text" id="inventoryName"
									name="inventoryName">
											 <%
							List<String> coll_1 = (List<String>)request.getAttribute("coll");
							if(coll_1.size()>0){
									for(int i=0;i<coll_1.size();i++){
							%>
											<option value="<%=StringUtil.notNull(coll_1.get(i))%>">
												<%=StringUtil.notNull(coll_1.get(i))%>
											</option>
										<%} }%>
								</select><span id='inventoryTag'></span>
                            </tr>
                            	 <tr>
                                <th><i class="require-red">*</i>备注信息：</th>
                                <td>	 <textarea  style="width: 250px; height: 100px" id="remark" name="remark"></textarea>
							</td>
                            </tr>
                           
                            <tr>
                                <th></th>
                                <td>
                                    <input id="sbtn" class="btn btn-primary mr10" value="提交" type="button">
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
$(function() {

	var reg = /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/;
	
	$("#express").blur(function() {
		if($(this).val()==""){
			$("#expressTag").text("快递公司名不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}else if(getByteLen($(this).val())>50) $("#expressTag").text("快递公司名字数不能大于25！").css({"color":"red","font-size":"12px"});
		else $("#expressTag").text("");
	});	
	
	$("#expressNum").blur(function() {
		if($(this).val()==""){
		$("#bankTag").text("快递单号不能为空！").css({"color":"red","font-size":"12px"}); 
	}else if(getByteLen($(this).val())>50) $("#expressNumTag").text("快递单号字数不能大于25！").css({"color":"red","font-size":"12px"});
	else $("#bankTag").text("");
	});	
	
	$("#expressFee").blur(function() {
		if($(this).val()==""){
		$("#expressFeeTag").text("快递费不能为空！").css({"color":"red","font-size":"12px"}); 
	}else if(!reg.test($(this).val())) $("#expressFeeTag").text("数值格式有误，物流费应为实数！").css({"color":"red","font-size":"12px"});
	else $("#expressFeeTag").text("");
	});	
	
	$("#inventoryName").blur(function() {
		if($(this).val()==""){
		$("#inventoryTag").text("发货仓库不能为空！").css({"color":"red","font-size":"12px"}); 
	}else $("#inventoryTag").text("");
	});	
	
	$("#sbtn").click(function(){
	if (!confirm("确认要出库，请核对好快递信息？")) {
			window.event.returnValue = false;
		}else{
		var errorstr = "";
		if($("#express").val()==""){
			$("#expressTag").text("快递公司名不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}else if($("#expressTag").text()!=""){
			errorstr ="1";
		}
		
		if($("#inventoryName").val()==""){
			$("#inventoryTag").text("发货仓库不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}else if($("#inventoryTag").text()!=""){
			errorstr ="1";
		}
		
		if($("#expressNum").val()==""){
			$("#expressNumTag").text("快递单号不能为空！").css({"color":"red","font-size":"12px"}); 
			
			errorstr ="1";
		}else if($("#expressNumTag").text()!=""){
		
			errorstr ="1";
		}
		
		if($("#expressFee").val()==""){
			$("#expressFeeTag").text("快递费不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}else if(!reg.test($("#expressFee").val())) $("#expressFeeTag").text("数值格式有误，快递费应为实数！").css({"color":"red","font-size":"12px"});
		else if($("#expressFeeTag").text()!=""){
		
			errorstr ="1";
		}
		
		if($("#bankName").val()==""){
			$("#bankTag").text("开户行不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}else if($("#bankTag").text()!=""){
			errorstr ="1";
		}
		
		if(errorstr!=""){
			return false;
		}else{
			$('#myform').attr("action", "admin/OrderServlet.do?method=admin_order_delivery_yes");
	       	$('#myform').submit();
       		 return true;
		
		}
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