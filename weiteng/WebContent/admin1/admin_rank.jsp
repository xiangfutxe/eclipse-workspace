<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.Admin" %>
<%@page import="com.utils.StringUtil" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	Admin admin1 = (Admin) request.getAttribute("admin1");
	String message = (String)request.getAttribute("message");
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
	if(!StringUtil.notNull(message).equals("")){
		out.println("<script>");
		out.println("alert('"+message+"')");
		out.println("</script>");
	}
	 String[][] rankstr1 = StringUtil.getRankStr(admin.getRank());
		if(rankstr1[9][0].equals("1")||admin.getState().equals("1")){
		 String[][] rankstr = StringUtil.getRankStr(admin1.getRank());
		 System.out.println(rankstr[0][0]);
		
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>权限管理</title>
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
						class="crumb-step">&gt;</span><a class="crumb-name"
						href="admin/admin.action">管理员管理</a><span class="crumb-step">&gt;</span><span>权限管理</span>
				</div>
			</div>
			<div class="result-wrap">
				<div class="result-content">
					<form action="admin/AdminServlet.do?method=updateRank" method="post" id="myform" name="myform">
					<input type="hidden" name="id" value='<%=admin1.getId()%>'/>
						<table class="insert-tab" width="100%">
							<tbody>
								<tr>
									<th width="150px">信息管理：</th>
									<td><input name="rankstr" type="checkbox" value="000" 
								<%if(rankstr[0][0].equals("1")) {%>checked<%}%>/>添加新闻&nbsp;
										<input
										name="rankstr" type="checkbox" value="001" 
										<%if(rankstr[0][1].equals("1")) {%>checked<%} %> />新闻列表
										&nbsp;
										<input
										name="rankstr" type="checkbox" value="002" 
										<%if(rankstr[0][2].equals("1")) {%>checked<%} %> />留言簿
										
										</td>
								</tr>
								<tr>
									<th>会员管理：</th>
									<td><input name="rankstr" type="checkbox" value="100"
										<%if(rankstr[1][0].equals("1")) {%>checked<%} %> />会员列表&nbsp;<input
										name="rankstr" type="checkbox" value="101"
										<%if(rankstr[1][1].equals("1")) {%>checked<%} %> />会员详情&nbsp;<input
										name="rankstr" type="checkbox" value="102"
										<%if(rankstr[1][2].equals("1")) {%>checked<%} %> />会员导出&nbsp;<input
										name="rankstr" type="checkbox" value="103"
										<%if(rankstr[1][3].equals("1")) {%>checked<%} %> />会员修改
										&nbsp;<input
										name="rankstr" type="checkbox" value="104"
										<%if(rankstr[1][4].equals("1")) {%>checked<%} %> />协会列表
										&nbsp;<input
										name="rankstr" type="checkbox" value="105"
										<%if(rankstr[1][5].equals("1")) {%>checked<%} %> />协会详情
										
										&nbsp;<input
										name="rankstr" type="checkbox" value="106"
										<%if(rankstr[1][6].equals("1")) {%>checked<%} %> />协会新增
										&nbsp;<input
										name="rankstr" type="checkbox" value="107"
										<%if(rankstr[1][7].equals("1")) {%>checked<%} %> />协会修改
										&nbsp;<input
										name="rankstr" type="checkbox" value="108"
										<%if(rankstr[1][8].equals("1")) {%>checked<%} %> />协会导出
											&nbsp;<input
										name="rankstr" type="checkbox" value="109"
										<%if(rankstr[1][9].equals("1")) {%>checked<%} %> />职位列表
										&nbsp;<input
										name="rankstr" type="checkbox" value="110"
										<%if(rankstr[1][10].equals("1")) {%>checked<%} %> />职位新增
										&nbsp;<input
										name="rankstr" type="checkbox" value="111"
										<%if(rankstr[1][11].equals("1")) {%>checked<%} %> />职位修改
										
									</td>
								</tr>
								<tr>
									<th>协会管理：</th>
									<td><input name="rankstr" type="checkbox" value="20"
										<%if(rankstr[2][0].equals("1")) {%>checked<%} %> />推荐关系&nbsp;<input
										name="rankstr" type="checkbox" value="21"
										<%if(rankstr[2][1].equals("1")) {%>checked<%} %> />安置关系&nbsp;<input
										name="rankstr" type="checkbox" value="22"
										<%if(rankstr[2][2].equals("1")) {%>checked<%} %> />安置结构图&nbsp;<input
										name="rankstr" type="checkbox" value="23"
										<%if(rankstr[2][3].equals("1")) {%>checked<%} %> />推荐结构图
										</td>
								</tr>
								<tr>
									<th>视频管理：</th>
									<td><input name="rankstr" type="checkbox" value="300"
										<%if(rankstr[3][0].equals("1")) {%>checked<%} %> />上传视频&nbsp;<input
										name="rankstr" type="checkbox" value="301"
										<%if(rankstr[3][1].equals("1")) {%>checked<%} %> />视频列表</td>
								</tr>
								<tr>
									<th>库存管理：</th>
									<td><input name="rankstr" type="checkbox" value="400"
									<%if(rankstr[4][0].equals("1")) {%>checked<%} %>/>产品新增
									&nbsp;<input
										name="rankstr" type="checkbox" value="401"
										<%if(rankstr[4][1].equals("1")) {%>checked<%} %> />产品列表&nbsp;<input
										name="rankstr" type="checkbox" value="402"
										<%if(rankstr[4][2].equals("1")) {%>checked<%} %> />产品修改
										&nbsp;<input name="rankstr" type="checkbox" value="403"
										<%if(rankstr[4][3].equals("1")) {%>checked<%} %> />产品导出
										&nbsp;<input name="rankstr" type="checkbox" value="404"
										<%if(rankstr[4][4].equals("1")) {%>checked<%} %> />新增分类
										&nbsp;<input name="rankstr" type="checkbox" value="405"
										<%if(rankstr[4][5].equals("1")) {%>checked<%} %> />分类列表
										&nbsp;<input name="rankstr" type="checkbox" value="406"
										<%if(rankstr[4][6].equals("1")) {%>checked<%} %> />分类修改
										&nbsp;<input name="rankstr" type="checkbox" value="407"
										<%if(rankstr[4][7].equals("1")) {%>checked<%} %> />新增属性
										&nbsp;<input name="rankstr" type="checkbox" value="408"
										<%if(rankstr[4][8].equals("1")) {%>checked<%} %> />属性列表
										&nbsp;<input name="rankstr" type="checkbox" value="409"
										<%if(rankstr[4][9].equals("1")) {%>checked<%} %> />属性修改
										</td>
								</tr>
								<tr>
									<th>订单管理：</th>
									<td><input name="rankstr" type="checkbox" value="500"
										<%if(rankstr[5][0].equals("1")) {%>checked<%} %> />订单列表&nbsp;<input
										name="rankstr" type="checkbox" value="501"
										<%if(rankstr[5][1].equals("1")) {%>checked<%} %> />订单确认&nbsp;<input
										name="rankstr" type="checkbox" value="502"
										<%if(rankstr[5][2].equals("1")) {%>checked<%} %> />发货管理&nbsp;<input
										name="rankstr" type="checkbox" value="503"
										<%if(rankstr[5][3].equals("1")) {%>checked<%} %> />发货审核</td>
								</tr>
								<tr>
									<th>财务管理：</th>
									<td><input name="rankstr" type="checkbox" value="600"
										<%if(rankstr[6][0].equals("1")) {%>checked<%} %> />现金充值&nbsp;<input
										name="rankstr" type="checkbox" value="601"
										<%if(rankstr[6][1].equals("1")) {%>checked<%} %> />充值审核&nbsp;
										<input name="rankstr" type="checkbox" value="602"
										<%if(rankstr[6][2].equals("1")) {%>checked<%} %> />充值列表
										&nbsp;<input name="rankstr" type="checkbox" value="603"
										<%if(rankstr[6][3].equals("1")) {%>checked<%} %> />提现审核
										&nbsp;<input name="rankstr" type="checkbox" value="604"
										<%if(rankstr[6][4].equals("1")) {%>checked<%} %> />提现列表
										&nbsp;<input
										name="rankstr" type="checkbox" value="605"
										<%if(rankstr[6][5].equals("1")) {%>checked<%} %> />开户行管理
										&nbsp;<input
										name="rankstr" type="checkbox" value="606"
									<%if(rankstr[6][6].equals("1")) {%>checked<%} %> />余额汇总
										&nbsp;<input
										name="rankstr" type="checkbox" value="607"
										<%if(rankstr[6][7].equals("1")) {%>checked<%} %> />账户补扣
										&nbsp;<input
										name="rankstr" type="checkbox" value="608"
										<%if(rankstr[6][8].equals("1")) {%>checked<%} %> />补扣明细&nbsp;<input
										name="rankstr" type="checkbox" value="609"
										<%if(rankstr[6][9].equals("1")) {%>checked<%} %> />资金明细</td>
								</tr>
								<tr>
									<th>奖金管理：</th>
									<td><input name="rankstr" type="checkbox" value="700"
										<%if(rankstr[7][0].equals("1")) {%>checked<%} %> />奖金结算&nbsp;<input
										name="rankstr" type="checkbox" value="701"
										<%if(rankstr[7][1].equals("1")) {%>checked<%} %> />奖金明细&nbsp;<input
										name="rankstr" type="checkbox" value="702"
										<%if(rankstr[7][2].equals("1")) {%>checked<%} %> />奖金汇总&nbsp;<input
										name="rankstr" type="checkbox" value="703"
										<%if(rankstr[7][3].equals("1")) {%>checked<%} %> />积分明细&nbsp;<input
										name="rankstr" type="checkbox" value="704"
										<%if(rankstr[7][4].equals("1")) {%>checked<%} %> />发放工资</td>
								</tr>
								<tr>
									<th>报表统计：</th>
									<td><input name="rankstr" type="checkbox" value="800"
										<%if(rankstr[8][0].equals("1")) {%>checked<%} %> />销售业绩查询&nbsp;<input
										name="rankstr" type="checkbox" value="801"
										<%if(rankstr[8][1].equals("1")) {%>checked<%} %> />待结业绩查询
										&nbsp;<input
										name="rankstr" type="checkbox" value="802"
										<%if(rankstr[8][2].equals("1")) {%>checked<%} %> />已结业绩查询&nbsp;
										<input
										name="rankstr" type="checkbox" value="803"
										<%if(rankstr[8][3].equals("1")) {%>checked<%} %> />促销升级汇总
										</td>
									
								</tr>
								<tr>
									<th>系统管理：</th>
									<td><input name="rankstr" type="checkbox" value="900"
										<%if(rankstr[9][0].equals("1")) {%>checked<%} %> />管理员管理&nbsp;<input
										name="rankstr" type="checkbox" value="901"
										<%if(rankstr[9][1].equals("1")) {%>checked<%} %> />网站设置
										&nbsp;<input
										name="rankstr" type="checkbox" value="902"
										<%if(rankstr[9][2].equals("1")) {%>checked<%} %> />基础设置
										&nbsp;<input
										name="rankstr" type="checkbox" value="903"
										<%if(rankstr[9][3].equals("1")) {%>checked<%} %> />数据备份
										&nbsp;<input
										name="rankstr" type="checkbox" value="905"
										<%if(rankstr[9][5].equals("1")) {%>checked<%} %> />日志管理</td>
								</tr>
								<tr>
									<th></th>
									<td><input id="sbtn" class="btn btn-primary mr6"
										value="保存" type="button"/> <input class="btn mr6"
										onClick='javascript:window.location.href="admin/AdminServlet.do?method=list";' value="返回" type="button"/>
										<input class="btn mr6" id="ckAll" value="全选" type="button"/>&nbsp;<input class="btn mr6" id="ckAllNot" value="全不选" type="button"/></td>
								</tr>
							</tbody>
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
		
		$("#ckAll").click(function() {
				
					$("input[name='rankstr']").each(function(){this.checked=true;}); 
				
		  });
		  $("#ckAllNot").click(function() {
					$("input[name='rankstr']").each(function(){this.checked=false;}); 
		  });

		$("#sbtn").click(function() {
			if (!confirm("确认要提交修改权限？")) {
            window.event.returnValue = false;
        }else{
				$('#myform').submit();
				 window.event.returnValue = true;

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