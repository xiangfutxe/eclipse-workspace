<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.Admin" %>
<%@page import="com.utils.StringUtil" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	double[] sum = (double[]) request.getAttribute("sum"); 
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
		  String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[6][10].equals("1")||admin.getState().equals("1")){
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>财务统计</title>
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

</head>

<body>
	<div class="container clearfix">
		<div class="main-wrap">
			<div class="crumb-wrap">
				<div class="crumb-list">
					<i class="icon-font"></i><a href="admin/index.jsp">首页</a><span
						class="crumb-step">&gt;</span>财务管理<span class="crumb-step">&gt;</span><span>余额统计查询</span>
				</div>
			</div>
			<div class="result-wrap">
			
					<div class="result-title">
						<div class="result-list">
							<i class="require-red">*</i><b>余额统计查询</b>
						</div>
					</div>
					<div class="result-content">
						<table class="result-tab" width="400px">
							<tbody>
							<tr>
									<td width="200px">项目</td><td align="center">数值</td>
									
									</tr>
								<tr>
									<td>报单券余额：</td>
									<td>
									<%=StringUtil.decimalFormat(sum[0]) %></td>
								</tr>
								
							
									<tr>
									<td>奖金券余额：</td>
									<td><%=StringUtil.decimalFormat(sum[3]) %></td>
									</tr>
								
									<tr>
									<td>促销券余额：</td>
									<td><%=StringUtil.decimalFormat(sum[4]) %></td>
									</tr>
									<tr>
									<td>合计：</td>
									<td><%=StringUtil.decimalFormat(sum[7]) %></td>
									</tr>
							</tbody>
						</table>
					</div>
					
					<form method="post" id="myform" name="myform">
					<div class="result-title">
						<div class="result-list">
						<br>
							<p><input type="button" class="btn" value="打印报表" onclick="window.print();" /> </p>
						</div>
					</div>
				</form>
			</div>

		</div>
		<!--/main-->
	</div>
</body>

<script type="text/javascript">
  
	/* 页面加载完成，绑定事件 */
	$(document).ready(function() {
		$("#btn1").bind("click", function() {
			$.ajax({
				type : "post",
				url : "user/rankAjax.action",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
				data : {//设置数据源
				rankJoin : $("input[name=rankJoin]").val()
				//这里不要加","  不然会报错，而且根本不会提示错误地方

				},

				dataType : "json",//设置需要返回的数据类型
				success : function(data) {
					var d = eval("(" + data + ")");//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
					//得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
					var $totalprice = $("input[name=totalprice]");
				
					alert($totalprice.val());
					if ($totalprice.value*d.level1 < d.level){
					 $("#msg").text("购买金额未达到加盟标准，请重新购买！").css({"color":"red","font-size":"9px"});
					 return false;
					 }
					else {
						  $('#myform').attr("action", "user/user_shop_save.action");
       						$('#myform').submit();
					}
				},
				error : function() {
					alert("系统异常，请稍后重试！");
				}//这里不要加","
			});

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