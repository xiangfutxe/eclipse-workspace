<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.PurchaseApply" %>
<%@page import="com.pojo.PurchaseDetail" %>
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
	PurchaseApply ap = (PurchaseApply) request.getAttribute("admin_apply");
	PurchaseDetail total_ap = (PurchaseDetail) request.getAttribute("total_ap");
	String message =(String) request.getAttribute("message");
		if(!StringUtil.notNull(message).equals("")){
		out.println("<script>");
		out.println("alert('"+message+"');");
		out.println("</script>");
		}
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[4][1].equals("1")||admin.getState().equals("1")){
		
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>采购详情</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/main.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/layer.js"></script>
<script src="js/calendar.js"></script>
<script language="JavaScript">
	function delcfm() {
		if (!confirm("确认要删除？")) {
			return false;
		}
	}

	function editForm(surl) {
			layer.open({
		        type: 2,
		        title: '修改清单',
		        maxmin: true,
		        shadeClose: true, //点击遮罩关闭层
		        area : ['800px' , '460px'],
		        page:surl 
    		});
		
	}
</script>
</head>

<body>
	<div class="container clearfix">
		<div class="main-wrap">
			<div class="crumb-wrap">
				<div class="crumb-list">
					<i class="icon-font"></i><a href="admin/index.jsp">首页</a><span
						class="crumb-step">&gt;</span><span>采购管理</span>
						<span class="crumb-step">&gt;</span><span class="crumb-name">采购列表</span>
						<span class="crumb-step">&gt;</span><span class="crumb-name">采购详情</span>
				</div>
			</div>
			
			<div class="result-wrap">
				
				<div class="result-content">
					<table width="100%" style="border:0px;font-size:12px; border-collapse:separate; border-spacing:0px 10px;">
						<tr>
							<td  colspan="2" align="center">
							<h4>采购申请单</h4>
							</td>
						</tr>
						<tr>
							<td>
							申请单号：<%=ap.getApplyId() %>
							</td>
							<td>
							&nbsp;
							</td>
						</tr>
						<tr>
							<td>
							供应商：<%=ap.getSupplierName() %>
							</td>
							<td>
							日期：<%=ap.getSupplierName() %>
							</td>
						</tr>
						</table>
						<table class="insert-tab" width="100%">
						
							<tr>
								<td>序号
								</td>
								<td>产品编号
								</td>
								<td>品名
								<td>规格
								</td>
								<td>单位
								</td>
								<td>数量
								<td>单价
								</td>
								<td>小计
								</td>
								<td>备注
								</td>
							</tr>
					 <%
							Collection coll = (Collection)request.getAttribute("coll");
							int t=0;
							if(coll!=null){
									Iterator it1 = coll.iterator();
									while(it1.hasNext()){
									PurchaseDetail p = (PurchaseDetail)it1.next();
									t++;
							%>
							<tr>
							<td><%=t%>
								 <td> <%=StringUtil.notNull(p.getProductId())%> </td>
						  <td> <%=StringUtil.notNull(p.getProductName())%> </td>
					 <td> <%=StringUtil.notNull(p.getSpecification())%></td>
					  <td> <%=StringUtil.notNull(p.getUnit())%></td>
					   <td> <%=p.getNum()%></td>
						<td> <%=StringUtil.decimalFormat(p.getPrice())%></td>
						<td> <%=StringUtil.decimalFormat(p.getTotalPrice())%></td>
						<td><%=StringUtil.notNull(p.getRemark()) %></td>
						
							</tr>
					<%}%>
					<tr>
					<td colspan="2">合计 </td>
						  <td>&nbsp;</td>
					 <td>&nbsp;</td>
					  <td>&nbsp;</td>
					   <td><%=total_ap.getNum() %></td>
						 <td>&nbsp;</td>
					 <td><%=StringUtil.decimalFormat(total_ap.getTotalPrice())%></td>
						<td>&nbsp;</td>
						
						</tr>
					<% } %>
					<tr>
					 <td>备注</td>
					 <td colspan="8"><%=StringUtil.notNull(ap.getRemark()) %></td>
						
						
						</tr>
					</table>
				</div>
				<p>&nbsp;</p>
				 <div class="result-list">
				 	<form id="cfmform" method="post">
				 <input type="hidden" name="id" id="id" value="<%=ap.getApplyId()%>"/>
                     <table class="search-tab">
                        <tr>
                            <td>
                          
                   <button class="btn" id="b_btn"><i class="icon-font">&#xe046;</i>返回采购列表</button>&nbsp;
                    
                   </td>
                     </tr>
                     </table>
                     </form>
                     </div>
			</div>
			                 
		</div>
		<!--/main-->
		
	</div>
</body>
<script language="JavaScript">

$(function() {
	var reg = /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/;
	var reg1 =  /^(0|[1-9]\d*)$/;//验证非负整数
	$("#productId").blur(function() {
		$.ajax({
				type : "post",
				url : "admin/ProductServlet.do?method=isExit",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
				data : {//设置数据源
					productId : $("#productId").val()
				//这里不要加","  不然会报错，而且根本不会提示错误地方

				},

				dataType : "json",//设置需要返回的数据类型
				success : function(data) {
					var d = data;//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
					//得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
					
					if(d.tag=="1"){
						 $("#productName").text(d.productName).css({"color":"green","font-size":"12px"});
						$("#productIdTag").text("");
					}else{
					 $("#productIdTag").text("产品信息获取失败！").css({"color":"green","font-size":"12px"});
					 $("#productName").text("");
					}
					
				},
				error : function() {
					$("#productIdTag").text("系统异常，请稍后重试！").css({"color":"red","font-size":"12px"});
					$("#productIdTag").text("");
				}//这里不要加","
				
			});
	});
	$("#price").blur(function() {
		if($("#price").val()=="")$("#priceTag").text("产品单价不能为空！").css({"color":"red","font-size":"12px"});
		else if(!reg.test($(this).val())) $("#priceTag").text("数值格式有误，产品单价应为实数！").css({"color":"red","font-size":"12px"});
		else $("#priceTag").text("");
	});
	$("#num").blur(function() {
		if($("#num").val()=="")$("#numTag").text("产品数量不能为空！").css({"color":"red","font-size":"12px"});
		else if(!reg1.test($(this).val())) $("#numTag").text("数值格式有误，产品数量应为整数！").css({"color":"red","font-size":"12px"});
		else $("#numTag").text("");
	});
	
	$("#cbtn").click(function(){
		if (!confirm("确认保存产品信息？")) {
								return false;
							} else {
				
		var errorstr = "";
		if($("#productId").val()==""){
			$("#productIdTag").text("产品编号不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}else if($("#productIdTag").text()!=""){
			errorstr ="1";
		}
		
		if($("#price").val()==""){
			$("#priceTag").text("单价不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#priceTag").text()!=""){
			errorstr ="1";
		}
		
		if($("#num").val()==""){
			$("#numTag").text("数量不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#numTag").text()!=""){
			errorstr ="1";
		}
	if(errorstr!=""){
			return false;
		}else{
       		 $('#addform').submit();
       		 return true;
		
		}
}
	});
	
	$("#ybtn").click(function(){
		if (!confirm("确认审批通过采购申请？")) {
								return false;
							} else {
			$('#cfmform').attr("action","admin/PurchaseApplyServlet.do?method=admin_review_yes");
							
       		 $('#cfmform').submit();
       		 return true;
}
	});
	
	$("#nbtn").click(function(){
		if (!confirm("确认审批不同通过采购申请？")) {
								return false;
							} else {
			$('#cfmform').attr("action","admin/PurchaseApplyServlet.do?method=admin_review_no_add");
							
       		 $('#cfmform').submit();
       		 return true;
}
	});
	
	$("#b_btn").click(function(){
		
				$('#cfmform').attr("action","admin/PurchaseApplyServlet.do?method=admin_apply_list");
       		 $('#cfmform').submit();
       		 return true;
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