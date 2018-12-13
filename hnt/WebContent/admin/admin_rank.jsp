<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.Admin" %>
<%@page import="com.ssm.utils.StringUtil" %>
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
		if(rankstr1[9][4].equals("1")||admin.getState().equals("1")){
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
						href="admin/admin.jsp">管理员管理</a><span class="crumb-step">&gt;</span><span>权限管理</span>
				</div>
			</div>
			<div class="result-wrap">
				<div class="result-content">
					<form action="admin/AdminServlet?method=updateRank" method="post" id="myform" name="myform">
				<input type="hidden" name="token" value='${sessionScope.token }'/>
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
										&nbsp;
										<input
										name="rankstr" type="checkbox" value="003" 
										<%if(rankstr[0][3].equals("1")) {%>checked<%} %> />留言回复
										&nbsp;
										<input
										name="rankstr" type="checkbox" value="004" 
										<%if(rankstr[0][4].equals("1")) {%>checked<%} %> />留言删除
										
										</td>
								</tr>
								<tr>
									<th>会员管理：</th>
									<td><input name="rankstr" type="checkbox" value="100"
										<%if(rankstr[1][0].equals("1")) {%>checked<%} %> />空单注册&nbsp;<input
										name="rankstr" type="checkbox" value="101"
										<%if(rankstr[1][1].equals("1")) {%>checked<%} %> />会员列表
										&nbsp;<input
										name="rankstr" type="checkbox" value="102"
										<%if(rankstr[1][2].equals("1")) {%>checked<%} %> />会员修改&nbsp;<input
										name="rankstr" type="checkbox" value="103"
										<%if(rankstr[1][3].equals("1")) {%>checked<%} %> />会员权限
										&nbsp;<input
										name="rankstr" type="checkbox" value="104"
										<%if(rankstr[1][4].equals("1")) {%>checked<%} %> />会员导出
										&nbsp;<input
										name="rankstr" type="checkbox" value="105"
										<%if(rankstr[1][5].equals("1")) {%>checked<%} %> />隐藏会员
										&nbsp;<input
										name="rankstr" type="checkbox" value="106"
										<%if(rankstr[1][6].equals("1")) {%>checked<%} %> />会员账户
										&nbsp;<input
										name="rankstr" type="checkbox" value="107"
										<%if(rankstr[1][7].equals("1")) {%>checked<%} %> />等级变更
										&nbsp;<input
										name="rankstr" type="checkbox" value="108"
										<%if(rankstr[1][8].equals("1")) {%>checked<%} %> />服务商变更
										&nbsp;<input
										name="rankstr" type="checkbox" value="109"
										<%if(rankstr[1][9].equals("1")) {%>checked<%} %> />销售商变更
										&nbsp;<input
										name="rankstr" type="checkbox" value="110"
										<%if(rankstr[1][10].equals("1")) {%>checked<%} %> />升级管理
										&nbsp;<input
										name="rankstr" type="checkbox" value="111"
										<%if(rankstr[1][11].equals("1")) {%>checked<%} %> />升级导出
										&nbsp;<input
										name="rankstr" type="checkbox" value="112"
										<%if(rankstr[1][12].equals("1")) {%>checked<%} %> />实单升级
										&nbsp;<input
										name="rankstr" type="checkbox" value="113"
										<%if(rankstr[1][13].equals("1")) {%>checked<%} %> />空单列表
										&nbsp;<input
										name="rankstr" type="checkbox" value="114"
										<%if(rankstr[1][14].equals("1")) {%>checked<%} %> />新增空单
										&nbsp;<input
										name="rankstr" type="checkbox" value="115"
										<%if(rankstr[1][15].equals("1")) {%>checked<%} %> />服务店列表
										&nbsp;<input
										name="rankstr" type="checkbox" value="116"
										<%if(rankstr[1][16].equals("1")) {%>checked<%} %> />服务店增改
										
										&nbsp;<input
										name="rankstr" type="checkbox" value="117"
										<%if(rankstr[1][17].equals("1")) {%>checked<%} %> />服务店隐藏	
										&nbsp;<input
										name="rankstr" type="checkbox" value="118"
										<%if(rankstr[1][18].equals("1")) {%>checked<%} %> />服务店导出
										&nbsp;<input
										name="rankstr" type="checkbox" value="119"
										<%if(rankstr[1][19].equals("1")) {%>checked<%} %> />级别调整
										&nbsp;<input
										name="rankstr" type="checkbox" value="120"
										<%if(rankstr[1][20].equals("1")) {%>checked<%} %> />调整列表
										&nbsp;<input
										name="rankstr" type="checkbox" value="121"
										<%if(rankstr[1][21].equals("1")) {%>checked<%} %> />调整导出
										&nbsp;<input
										name="rankstr" type="checkbox" value="122"
										<%if(rankstr[1][22].equals("1")) {%>checked<%} %> />管理级别调整
										&nbsp;<input
										name="rankstr" type="checkbox" value="123"
										<%if(rankstr[1][23].equals("1")) {%>checked<%} %> />管理级别调整列表
										&nbsp;<input
										name="rankstr" type="checkbox" value="124"
										<%if(rankstr[1][24].equals("1")) {%>checked<%} %> />管理级别调整导出
										&nbsp;<input
										name="rankstr" type="checkbox" value="125"
										<%if(rankstr[1][25].equals("1")) {%>checked<%} %> />高级查询
										&nbsp;<input
										name="rankstr" type="checkbox" value="126"
										<%if(rankstr[1][26].equals("1")) {%>checked<%} %> />会员账户导出
										&nbsp;
									</td>
								</tr>
								<tr>
									<th>网络管理：</th>
									<td><input name="rankstr" type="checkbox" value="200"
										<%if(rankstr[2][0].equals("1")) {%>checked<%} %> />服务关系(树形)&nbsp;<input
										name="rankstr" type="checkbox" value="201"
										<%if(rankstr[2][1].equals("1")) {%>checked<%} %> />销售关系(树形)&nbsp;&nbsp;
										<input name="rankstr" type="checkbox" value="202"
										<%if(rankstr[2][2].equals("1")) {%>checked<%} %> />服务关系(反向)&nbsp;<input
										name="rankstr" type="checkbox" value="203"
										<%if(rankstr[2][3].equals("1")) {%>checked<%} %> />销售关系(反向)&nbsp;<input
										name="rankstr" type="checkbox" value="204"
										<%if(rankstr[2][4].equals("1")) {%>checked<%} %> />远端会员
										&nbsp;<input
										name="rankstr" type="checkbox" value="205"
										<%if(rankstr[2][5].equals("1")) {%>checked<%} %> />团队会员奖衔
										
										</td>
								</tr>
								<tr>
									<th>产品管理：</th>
									<td><input name="rankstr" type="checkbox" value="300"
										<%if(rankstr[3][0].equals("1")) {%>checked<%} %> />产品列表&nbsp;<input
										name="rankstr" type="checkbox" value="301"
										<%if(rankstr[3][1].equals("1")) {%>checked<%} %> />分类管理&nbsp;
										<input name="rankstr" type="checkbox" value="302"
										<%if(rankstr[3][2].equals("1")) {%>checked<%} %> />产品新增&nbsp;
										<input name="rankstr" type="checkbox" value="303"
										<%if(rankstr[3][3].equals("1")) {%>checked<%} %> />产品修改&nbsp;
										<input name="rankstr" type="checkbox" value="304"
										<%if(rankstr[3][4].equals("1")) {%>checked<%} %> />产品导出&nbsp;
										</td>
								</tr>
								<tr>
									<th>仓库管理：</th>
									<td><input name="rankstr" type="checkbox" value="400"
									<%if(rankstr[4][0].equals("1")) {%>checked<%} %>/>新增仓库&nbsp;<input
										name="rankstr" type="checkbox" value="401"
										<%if(rankstr[4][1].equals("1")) {%>checked<%} %> />仓库列表&nbsp;<input
										name="rankstr" type="checkbox" value="402"
										<%if(rankstr[4][2].equals("1")) {%>checked<%} %> />入库管理
										&nbsp;<input name="rankstr" type="checkbox" value="403"
										<%if(rankstr[4][3].equals("1")) {%>checked<%} %> />入库审核
										&nbsp;<input
										name="rankstr" type="checkbox" value="405"
										<%if(rankstr[4][5].equals("1")) {%>checked<%} %> />出库管理
										&nbsp;<input name="rankstr" type="checkbox" value="406"
										<%if(rankstr[4][6].equals("1")) {%>checked<%} %> />出库审核
										&nbsp;<input name="rankstr" type="checkbox" value="404"
										<%if(rankstr[4][4].equals("1")) {%>checked<%} %> />库存汇总
										&nbsp;<input name="rankstr" type="checkbox" value="408"
										<%if(rankstr[4][8].equals("1")) {%>checked<%} %> />调库管理
										&nbsp;<input name="rankstr" type="checkbox" value="409"
										<%if(rankstr[4][9].equals("1")) {%>checked<%} %> />调库审核
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
										<%if(rankstr[5][3].equals("1")) {%>checked<%} %> />发货审核
										&nbsp;<input
										name="rankstr" type="checkbox" value="504"
										<%if(rankstr[5][4].equals("1")) {%>checked<%} %> />订单列表(客服)
										&nbsp;<input
										name="rankstr" type="checkbox" value="505"
										<%if(rankstr[5][5].equals("1")) {%>checked<%} %> />订单汇总
										&nbsp;<input
										name="rankstr" type="checkbox" value="506"
										<%if(rankstr[5][6].equals("1")) {%>checked<%} %> />订单导出
										&nbsp;<input
										name="rankstr" type="checkbox" value="507"
										<%if(rankstr[5][7].equals("1")) {%>checked<%} %> />明细导出
										</td>
								</tr>
								<tr>
									<th>财务管理：</th>
									<td><input name="rankstr" type="checkbox" value="600"
										<%if(rankstr[6][0].equals("1")) {%>checked<%} %> />现金充值&nbsp;<input
										name="rankstr" type="checkbox" value="601"
										<%if(rankstr[6][1].equals("1")) {%>checked<%} %> />充值审核&nbsp;<input
										name="rankstr" type="checkbox" value="602"
										<%if(rankstr[6][2].equals("1")) {%>checked<%} %> />提现审核&nbsp;<input
										name="rankstr" type="checkbox" value="603"
										<%if(rankstr[6][3].equals("1")) {%>checked<%} %> />账号管理
										&nbsp;<input
										name="rankstr" type="checkbox" value="604"
									<%if(rankstr[6][4].equals("1")) {%>checked<%} %> />余额汇总
										&nbsp;<input
										name="rankstr" type="checkbox" value="605"
										<%if(rankstr[6][5].equals("1")) {%>checked<%} %> />账户补扣
										&nbsp;<input
										name="rankstr" type="checkbox" value="606"
										<%if(rankstr[6][6].equals("1")) {%>checked<%} %> />补扣明细&nbsp;<input
										name="rankstr" type="checkbox" value="607"
										<%if(rankstr[6][7].equals("1")) {%>checked<%} %> />资金明细&nbsp;<input
										name="rankstr" type="checkbox" value="608"
										<%if(rankstr[6][8].equals("1")) {%>checked<%} %> />电子券管理&nbsp;<input
										name="rankstr" type="checkbox" value="609"
										<%if(rankstr[6][9].equals("1")) {%>checked<%} %> />余额查询</td>
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
										<%if(rankstr[7][3].equals("1")) {%>checked<%} %> />空点奖金&nbsp;<input
										name="rankstr" type="checkbox" value="704"
										<%if(rankstr[7][4].equals("1")) {%>checked<%} %> />发放工资
										&nbsp;<input
										name="rankstr" type="checkbox" value="706"
										<%if(rankstr[7][6].equals("1")) {%>checked<%} %> />公司奖金
										&nbsp;<input
										name="rankstr" type="checkbox" value="707"
										<%if(rankstr[7][7].equals("1")) {%>checked<%} %> />团队奖金查询
										&nbsp;<input
										name="rankstr" type="checkbox" value="708"
										<%if(rankstr[7][8].equals("1")) {%>checked<%}%> />公司明细
										&nbsp;<input
										name="rankstr" type="checkbox" value="709"
										<%if(rankstr[7][9].equals("1")) {%>checked<%}%> />冻点明细
										&nbsp;<input
										name="rankstr" type="checkbox" value="710"
										<%if(rankstr[7][10].equals("1")) {%>checked<%}%> />至尊明细
										&nbsp;<input
										name="rankstr" type="checkbox" value="711"
										<%if(rankstr[7][11].equals("1")) {%>checked<%}%> />至尊导入
										&nbsp;
										&nbsp;<input
										name="rankstr" type="checkbox" value="712"
										<%if(rankstr[7][12].equals("1")) {%>checked<%}%> />奖金导出
										&nbsp;
										<input
										name="rankstr" type="checkbox" value="720"
										<%if(rankstr[7][20].equals("1")) {%>checked<%} %> />奖金结算(日)&nbsp;<input
										name="rankstr" type="checkbox" value="721"
										<%if(rankstr[7][21].equals("1")) {%>checked<%} %> />奖金明细(日)&nbsp;<input
										name="rankstr" type="checkbox" value="722"
										<%if(rankstr[7][22].equals("1")) {%>checked<%} %> />奖金汇总(日)&nbsp;<input
										name="rankstr" type="checkbox" value="723"
										<%if(rankstr[7][23].equals("1")) {%>checked<%} %> />空点奖金(日)&nbsp;<input
										name="rankstr" type="checkbox" value="724"
										<%if(rankstr[7][24].equals("1")) {%>checked<%} %> />发放工资(日)
										&nbsp;<input
										name="rankstr" type="checkbox" value="726"
										<%if(rankstr[7][26].equals("1")) {%>checked<%} %> />公司奖金(日)
										&nbsp;<input
										name="rankstr" type="checkbox" value="727"
										<%if(rankstr[7][27].equals("1")) {%>checked<%} %> />团队奖金查询(日)
										&nbsp;<input
										name="rankstr" type="checkbox" value="728"
										<%if(rankstr[7][28].equals("1")) {%>checked<%}%> />公司明细(日)
										&nbsp;<input
										name="rankstr" type="checkbox" value="729"
										<%if(rankstr[7][29].equals("1")) {%>checked<%}%> />冻点明细(日)
										&nbsp;
										</td>
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
										name="rankstr" type="checkbox" value="804"
										<%if(rankstr[8][4].equals("1")) {%>checked<%} %> />已结业绩汇总&nbsp;
										<input
										name="rankstr" type="checkbox" value="805"
										<%if(rankstr[8][5].equals("1")) {%>checked<%} %> />分段业绩查询&nbsp;
										<input
										name="rankstr" type="checkbox" value="806"
										<%if(rankstr[8][6].equals("1")) {%>checked<%} %> />服务业绩查询&nbsp;
										<input
										name="rankstr" type="checkbox" value="807"
										<%if(rankstr[8][7].equals("1")) {%>checked<%} %> />服务业绩汇总&nbsp;
										<input
										name="rankstr" type="checkbox" value="808"
										<%if(rankstr[8][8].equals("1")) {%>checked<%} %> />区域业绩汇总&nbsp;
										<input
										name="rankstr" type="checkbox" value="809"
										<%if(rankstr[8][9].equals("1")) {%>checked<%} %> />区域业绩查询&nbsp;
										<input
										name="rankstr" type="checkbox" value="810"
										<%if(rankstr[8][10].equals("1")) {%>checked<%} %> />伞下信息导出&nbsp;
										<input
										name="rankstr" type="checkbox" value="811"
										<%if(rankstr[8][11].equals("1")) {%>checked<%} %> />直推业绩查询&nbsp;
										<input
										name="rankstr" type="checkbox" value="812"
										<%if(rankstr[8][12].equals("1")) {%>checked<%} %> />伞下推荐汇总&nbsp;
										<input
										name="rankstr" type="checkbox" value="812"
										<%if(rankstr[8][13].equals("1")) {%>checked<%} %> />伞下会员汇总&nbsp;
										<input
										name="rankstr" type="checkbox" value="814"
										<%if(rankstr[8][14].equals("1")) {%>checked<%} %> />财务汇总导出&nbsp;
										
										<input
										name="rankstr" type="checkbox" value="820"
										<%if(rankstr[8][20].equals("1")) {%>checked<%} %> />奖金明细汇总导出&nbsp;
										<input
										name="rankstr" type="checkbox" value="821"
										<%if(rankstr[8][21].equals("1")) {%>checked<%} %> />电子币汇总导出&nbsp;
										&nbsp;
										<input
										name="rankstr" type="checkbox" value="822"
										<%if(rankstr[8][22].equals("1")) {%>checked<%} %> />已结业绩查询(日)&nbsp;
										<input
										name="rankstr" type="checkbox" value="823"
										<%if(rankstr[8][23].equals("1")) {%>checked<%} %> />奖金明细汇总(日)
										</td>
									
								</tr>
								<tr>
									<th>员工管理：</th>
									<td><input name="rankstr" type="checkbox" value="900"
										<%if(rankstr[9][0].equals("1")) {%>checked<%} %> />员工列表&nbsp;<input
										name="rankstr" type="checkbox" value="901"
										<%if(rankstr[9][1].equals("1")) {%>checked<%} %> />新增员工
										&nbsp;<input
										name="rankstr" type="checkbox" value="902"
										<%if(rankstr[9][2].equals("1")) {%>checked<%} %> />员工详情
										&nbsp;<input
										name="rankstr" type="checkbox" value="903"
										<%if(rankstr[9][3].equals("1")) {%>checked<%} %> />员工权限
										&nbsp;<input
										name="rankstr" type="checkbox" value="904"
										<%if(rankstr[9][4].equals("1")) {%>checked<%} %> />员工密码
										&nbsp;<input
										name="rankstr" type="checkbox" value="905"
										<%if(rankstr[9][5].equals("1")) {%>checked<%} %> />员工删除
										&nbsp;<input
										name="rankstr" type="checkbox" value="906"
										<%if(rankstr[9][6].equals("1")) {%>checked<%} %> />部门列表
										&nbsp;<input
										name="rankstr" type="checkbox" value="907"
										<%if(rankstr[9][7].equals("1")) {%>checked<%} %> />新增部门
										&nbsp;<input
										name="rankstr" type="checkbox" value="908"
										<%if(rankstr[9][8].equals("1")) {%>checked<%} %> />部门修改
										&nbsp;<input
										name="rankstr" type="checkbox" value="909"
										<%if(rankstr[9][9].equals("1")) {%>checked<%} %> />部门删除
										&nbsp;<input
										name="rankstr" type="checkbox" value="910"
										<%if(rankstr[9][10].equals("1")) {%>checked<%} %> />职务列表
										&nbsp;<input
										name="rankstr" type="checkbox" value="911"
										<%if(rankstr[9][11].equals("1")) {%>checked<%} %> />新增职务
										&nbsp;<input
										name="rankstr" type="checkbox" value="912"
										<%if(rankstr[9][12].equals("1")) {%>checked<%} %> />职务修改
										&nbsp;<input
										name="rankstr" type="checkbox" value="913"
										<%if(rankstr[9][13].equals("1")) {%>checked<%} %> />职务删除
										</td>
								</tr>
								<tr>
									<th>系统管理：</th>
									<td><input name="rankstr" type="checkbox" value="915"
										<%if(rankstr[9][15].equals("1")) {%>checked<%} %> />管理员管理&nbsp;<input
										name="rankstr" type="checkbox" value="916"
										<%if(rankstr[9][16].equals("1")) {%>checked<%} %> />网站设置
										&nbsp;<input
										name="rankstr" type="checkbox" value="917"
										<%if(rankstr[9][17].equals("1")) {%>checked<%} %> />基础设置
										&nbsp;<input
										name="rankstr" type="checkbox" value="918"
										<%if(rankstr[9][18].equals("1")) {%>checked<%} %> />数据备份
										&nbsp;<input
										name="rankstr" type="checkbox" value="919"
										<%if(rankstr[9][19].equals("1")) {%>checked<%} %> />日志管理
										&nbsp;<input
										name="rankstr" type="checkbox" value="920"
										<%if(rankstr[9][20].equals("1")) {%>checked<%} %> />拓展政策
										&nbsp;<input
										name="rankstr" type="checkbox" value="921"
										<%if(rankstr[9][21].equals("1")) {%>checked<%} %> />新增政策
										&nbsp;<input
										name="rankstr" type="checkbox" value="922"
										<%if(rankstr[9][22].equals("1")) {%>checked<%} %> />特殊权限
										&nbsp;<input
										name="rankstr" type="checkbox" value="923"
										<%if(rankstr[9][23].equals("1")) {%>checked<%} %> />会员日志
										&nbsp;<input
										name="rankstr" type="checkbox" value="924"
										<%if(rankstr[9][24].equals("1")) {%>checked<%} %> />会员日志删除
										&nbsp;<input
										name="rankstr" type="checkbox" value="925"
										<%if(rankstr[9][25].equals("1")) {%>checked<%} %> />会员日志导出</td>
								</tr>
								<tr>
									<th></th>
									<td><input id="sbtn" class="btn btn-primary mr6"
										value="保存" type="button"/> <input class="btn mr6"
										onClick='javascript:window.location.href="admin/AdminServlet?method=list";' value="返回" type="button"/>
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