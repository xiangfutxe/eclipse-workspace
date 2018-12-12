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
		if(rankstr1[9][2].equals("1")||admin.getState().equals("1")){
		System.out.println("rank:"+admin1.getRank());
		 String[][] rankstr = StringUtil.getRankStr(admin1.getRank());
		
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
											<input
										name="rankstr" type="checkbox" value="003" 
										<%if(rankstr[0][3].equals("1")) {%>checked<%} %> />短信模板
										</td>
								</tr>
								<tr>
									<th>分店管理：</th>
									<td><input name="rankstr" type="checkbox" value="100"
										<%if(rankstr[1][0].equals("1")) {%>checked<%} %> />分店列表&nbsp;<input
										name="rankstr" type="checkbox" value="101"
										<%if(rankstr[1][1].equals("1")) {%>checked<%} %> />分店新增
										&nbsp;<input
										name="rankstr" type="checkbox" value="102"
										<%if(rankstr[1][2].equals("1")) {%>checked<%} %> />分店修改&nbsp;<input
										name="rankstr" type="checkbox" value="103"
										<%if(rankstr[1][3].equals("1")) {%>checked<%} %> />分店删除&nbsp;<input
										name="rankstr" type="checkbox" value="104"
										<%if(rankstr[1][4].equals("1")) {%>checked<%} %> />分店授信
										&nbsp;<input name="rankstr" type="checkbox" value="105"
										<%if(rankstr[1][5].equals("1")) {%>checked<%} %> />分店导出
										&nbsp;<input name="rankstr" type="checkbox" value="106"
										<%if(rankstr[1][6].equals("1")) {%>checked<%} %> />账务清单&nbsp;
										&nbsp;<input name="rankstr" type="checkbox" value="107"
										<%if(rankstr[1][7].equals("1")) {%>checked<%} %> />密码重置&nbsp;
										&nbsp;<input name="rankstr" type="checkbox" value="108"
										<%if(rankstr[1][8].equals("1")) {%>checked<%} %> />账号锁定&nbsp;
										
									</td>
								</tr>
								
								<tr>
									<th>产品管理：</th>
									<td>
									<input name="rankstr" type="checkbox" value="200"
										<%if(rankstr[2][0].equals("1")) {%>checked<%} %> />产品列表&nbsp;
											<input name="rankstr" type="checkbox" value="201"
										<%if(rankstr[2][1].equals("1")) {%>checked<%} %> />产品添加&nbsp;
											<input name="rankstr" type="checkbox" value="202"
										<%if(rankstr[2][2].equals("1")) {%>checked<%} %> />产品修改&nbsp;
										<input name="rankstr" type="checkbox" value="203"
										<%if(rankstr[2][3].equals("1")) {%>checked<%} %> />产品上下架&nbsp;
											<input name="rankstr" type="checkbox" value="204"
										<%if(rankstr[2][4].equals("1")) {%>checked<%} %> />产品删除&nbsp;
										<input name="rankstr" type="checkbox" value="205"
										<%if(rankstr[2][5].equals("1")) {%>checked<%} %> />产品显示&nbsp;
										<input name="rankstr" type="checkbox" value="206"
										<%if(rankstr[2][6].equals("1")) {%>checked<%} %> />分类列表&nbsp;
										<input name="rankstr" type="checkbox" value="207"
										<%if(rankstr[2][7].equals("1")) {%>checked<%} %> />分类添加&nbsp;
										<input name="rankstr" type="checkbox" value="208"
										<%if(rankstr[2][8].equals("1")) {%>checked<%} %> />分类删除
									&nbsp;<input name="rankstr" type="checkbox" value="209"
										<%if(rankstr[2][9].equals("1")) {%>checked<%} %> />单位列表
										&nbsp;<input name="rankstr" type="checkbox" value="210"
										<%if(rankstr[2][10].equals("1")) {%>checked<%} %> />单位添加
										&nbsp;<input name="rankstr" type="checkbox" value="211"
										<%if(rankstr[2][11].equals("1")) {%>checked<%} %> />单位删除
										&nbsp;<input name="rankstr" type="checkbox" value="212"
										<%if(rankstr[2][12].equals("1")) {%>checked<%} %> />属性列表
										&nbsp;<input name="rankstr" type="checkbox" value="213"
										<%if(rankstr[2][13].equals("1")) {%>checked<%} %> />属性添加
										&nbsp;<input name="rankstr" type="checkbox" value="214"
										<%if(rankstr[2][14].equals("1")) {%>checked<%} %> />属性删除
									</td>
								</tr>
								
							<tr><th>仓库管理：</th>
							<td>&nbsp;<input name="rankstr" type="checkbox" value="300"
									<%if(rankstr[3][0].equals("1")) {%>checked<%} %>/>新增仓库
									&nbsp;<input name="rankstr" type="checkbox" value="301"
									<%if(rankstr[3][1].equals("1")) {%>checked<%} %> />仓库列表
									&nbsp;<input name="rankstr" type="checkbox" value="302"
										<%if(rankstr[3][2].equals("1")) {%>checked<%} %> />仓库删除
										<input name="rankstr" type="checkbox" value="303"
										<%if(rankstr[3][3].equals("1")) {%>checked<%} %> />入库查询
										&nbsp;<input name="rankstr" type="checkbox" value="304"
										<%if(rankstr[3][4].equals("1")) {%>checked<%} %> />入库申请
										
										&nbsp;<input name="rankstr" type="checkbox" value="306"
										<%if(rankstr[3][6].equals("1")) {%>checked<%} %> />入库质检验收
										&nbsp;<input name="rankstr" type="checkbox" value="307"
										<%if(rankstr[3][7].equals("1")) {%>checked<%} %> />入库仓管确认
											&nbsp;<input name="rankstr" type="checkbox" value="308"
										<%if(rankstr[3][8].equals("1")) {%>checked<%} %> />入库财务审核
										&nbsp;<input name="rankstr" type="checkbox" value="309"
										<%if(rankstr[3][9].equals("1")) {%>checked<%} %> />出库查询
										&nbsp;<input name="rankstr" type="checkbox" value="310"
										<%if(rankstr[3][10].equals("1")) {%>checked<%} %> />出库申请
										&nbsp;<input name="rankstr" type="checkbox" value="311"
										<%if(rankstr[3][11].equals("1")) {%>checked<%} %> />出库质检确认
										&nbsp;<input name="rankstr" type="checkbox" value="312"
										<%if(rankstr[3][12].equals("1")) {%>checked<%} %> />出库仓管确认
											&nbsp;<input name="rankstr" type="checkbox" value="313"
										<%if(rankstr[3][13].equals("1")) {%>checked<%} %> />出库财务审核
										&nbsp;<input name="rankstr" type="checkbox" value="314"
										<%if(rankstr[3][14].equals("1")) {%>checked<%} %> />库存盘点
										&nbsp;<input name="rankstr" type="checkbox" value="315"
										<%if(rankstr[3][15].equals("1")) {%>checked<%} %> />盘点明细
										&nbsp;<input name="rankstr" type="checkbox" value="316"
										<%if(rankstr[3][16].equals("1")) {%>checked<%} %> />盘盈入库
										&nbsp;<input name="rankstr" type="checkbox" value="317"
										<%if(rankstr[3][17].equals("1")) {%>checked<%} %> />盘盈审核
										&nbsp;<input name="rankstr" type="checkbox" value="318"
										<%if(rankstr[3][18].equals("1")) {%>checked<%} %> />盘亏出库
										&nbsp;<input name="rankstr" type="checkbox" value="319"
										<%if(rankstr[3][19].equals("1")) {%>checked<%} %> />盘亏审核
										&nbsp;<input name="rankstr" type="checkbox" value="320"
										<%if(rankstr[3][20].equals("1")) {%>checked<%} %> />入库明细
										&nbsp;<input name="rankstr" type="checkbox" value="321"
										<%if(rankstr[3][21].equals("1")) {%>checked<%} %> />出库明细
									</td>
							</tr>
								<tr>
									<th>采购管理：</th>
									<td><input name="rankstr" type="checkbox" value="400"
										<%if(rankstr[4][0].equals("1")) {%>checked<%} %> />采购列表&nbsp;<input
										name="rankstr" type="checkbox" value="401"
										<%if(rankstr[4][1].equals("1")) {%>checked<%} %> />采购申请&nbsp;<input
										name="rankstr" type="checkbox" value="402"
										<%if(rankstr[4][2].equals("1")) {%>checked<%} %> />采购审批
										&nbsp;<input
										name="rankstr" type="checkbox" value="403"
										<%if(rankstr[4][3].equals("1")) {%>checked<%} %> />历史采购
										&nbsp;<input
										name="rankstr" type="checkbox" value="404"
										<%if(rankstr[4][4].equals("1")) {%>checked<%} %> />单品汇总
										</td>
								</tr>
								
								<tr>
									<th>订单管理：</th>
									<td><input name="rankstr" type="checkbox" value="500"
										<%if(rankstr[5][0].equals("1")) {%>checked<%} %> />订单列表&nbsp;<input
										name="rankstr" type="checkbox" value="501"
										<%if(rankstr[5][1].equals("1")) {%>checked<%} %> />订单确认&nbsp;<input
										name="rankstr" type="checkbox" value="502"
										<%if(rankstr[5][2].equals("1")) {%>checked<%} %> />订单配货&nbsp;<input
										name="rankstr" type="checkbox" value="503"
										<%if(rankstr[5][3].equals("1")) {%>checked<%} %> />出库审核&nbsp;
										&nbsp;<input
										name="rankstr" type="checkbox" value="504"
										<%if(rankstr[5][4].equals("1")) {%>checked<%} %> />订单支付&nbsp;
										&nbsp;<input
										name="rankstr" type="checkbox" value="505"
										<%if(rankstr[5][5].equals("1")) {%>checked<%} %> />配送汇总&nbsp;
										&nbsp;<input
										name="rankstr" type="checkbox" value="506"
										<%if(rankstr[5][6].equals("1")) {%>checked<%} %> />分店汇总&nbsp;
										&nbsp;<input
										name="rankstr" type="checkbox" value="507"
										<%if(rankstr[5][7].equals("1")) {%>checked<%} %> />新店配货&nbsp;
										&nbsp;<input
										name="rankstr" type="checkbox" value="508"
										<%if(rankstr[5][8].equals("1")) {%>checked<%} %> />新增配货&nbsp;
										</td>
								</tr>
								<tr>
									<th>财务管理：</th>
									<td><input name="rankstr" type="checkbox" value="600"
										<%if(rankstr[6][0].equals("1")) {%>checked<%} %> />现金充值&nbsp;
										<input name="rankstr" type="checkbox" value="601"
										<%if(rankstr[6][1].equals("1")) {%>checked<%} %> />充值清单&nbsp;
										<input name="rankstr" type="checkbox" value="602"
										<%if(rankstr[6][2].equals("2")) {%>checked<%} %> />充值撤回&nbsp;
										<input name="rankstr" type="checkbox" value="611"
										<%if(rankstr[6][11].equals("1")) {%>checked<%} %> />账户增扣&nbsp;
										<input name="rankstr" type="checkbox" value="612"
										<%if(rankstr[6][12].equals("1")) {%>checked<%} %> />增扣列表&nbsp;
										<input name="rankstr" type="checkbox" value="614"
										<%if(rankstr[6][14].equals("1")) {%>checked<%} %> />资金明细&nbsp;
										</td>
								</tr>
								<tr>
									<th>报表管理：</th>
									<td><input name="rankstr" type="checkbox" value="700"
										<%if(rankstr[7][0].equals("1")) {%>checked<%} %> />订单统计&nbsp;<input
										name="rankstr" type="checkbox" value="701"
										<%if(rankstr[7][1].equals("1")) {%>checked<%} %> />入库统计&nbsp;<input
										name="rankstr" type="checkbox" value="702"
										<%if(rankstr[7][2].equals("1")) {%>checked<%} %> />出库统计&nbsp;
										&nbsp;<input
										name="rankstr" type="checkbox" value="703"
										<%if(rankstr[7][3].equals("1")) {%>checked<%} %> />库存检测&nbsp;
										&nbsp;<input
										name="rankstr" type="checkbox" value="704"
										<%if(rankstr[7][4].equals("1")) {%>checked<%} %> />单品总统计&nbsp;
										&nbsp;<input
										name="rankstr" type="checkbox" value="705"
										<%if(rankstr[7][5].equals("1")) {%>checked<%} %> />单品店统计&nbsp;
										&nbsp;<input
										name="rankstr" type="checkbox" value="706"
										<%if(rankstr[7][6].equals("1")) {%>checked<%} %> />店销汇总&nbsp;
										&nbsp;<input
										name="rankstr" type="checkbox" value="707"
										<%if(rankstr[7][7].equals("1")) {%>checked<%} %> />配送总统计&nbsp;
										</td>
								</tr>
								<tr>
									<th>供应商管理：</th>
									<td><input name="rankstr" type="checkbox" value="800"
										<%if(rankstr[8][0].equals("1")) {%>checked<%} %> />供应商列表&nbsp;<input
										name="rankstr" type="checkbox" value="801"
										<%if(rankstr[8][1].equals("1")) {%>checked<%} %> />供应商新增&nbsp;<input
										name="rankstr" type="checkbox" value="802"
										<%if(rankstr[8][2].equals("1")) {%>checked<%} %> />供应商修改&nbsp;<input
										name="rankstr" type="checkbox" value="803"
										<%if(rankstr[8][3].equals("1")) {%>checked<%} %> />供应商删除&nbsp;
										&nbsp;<input
										name="rankstr" type="checkbox" value="804"
										<%if(rankstr[8][4].equals("1")) {%>checked<%} %> />供应商导出&nbsp;
										
										</td>
								</tr>
								<tr>
									<th>系统管理：</th>
									<td><input name="rankstr" type="checkbox" value="900"
										<%if(rankstr[9][0].equals("1")) {%>checked<%} %> />管理员列表&nbsp;
										<input name="rankstr" type="checkbox" value="901"
										<%if(rankstr[9][1].equals("1")) {%>checked<%} %> />新增管理员&nbsp;
										<input name="rankstr" type="checkbox" value="902"
										<%if(rankstr[9][2].equals("1")) {%>checked<%} %> />权限修改&nbsp;
										<input name="rankstr" type="checkbox" value="903"
										<%if(rankstr[9][3].equals("1")) {%>checked<%} %> />管理员删除&nbsp;
										<input name="rankstr" type="checkbox" value="904"
										<%if(rankstr[9][4].equals("1")) {%>checked<%} %> />管理员密码&nbsp;
										<input
										name="rankstr" type="checkbox" value="905"
										<%if(rankstr[9][5].equals("1")) {%>checked<%} %> />网站设置
										&nbsp;<input
										name="rankstr" type="checkbox" value="906"
										<%if(rankstr[9][6].equals("1")) {%>checked<%} %> />基础设置
										&nbsp;<input
										name="rankstr" type="checkbox" value="907"
										<%if(rankstr[9][7].equals("1")) {%>checked<%} %> />系统日志
										&nbsp;<input
										name="rankstr" type="checkbox" value="908"
										<%if(rankstr[9][8].equals("1")) {%>checked<%} %> />数据备份</td>
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