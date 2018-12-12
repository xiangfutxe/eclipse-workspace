<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.Product" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.utils.Pager" %>
<%@page import="com.pojo.Admin" %>
<%@page import="com.pojo.Branch" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String startTime = (String)request.getAttribute("startTime");
	String endTime = (String)request.getAttribute("endTime");
	String code = (String)request.getAttribute("code");
Branch branch = (Branch)request.getAttribute("admin_branch");
Double totalNum = (Double) request.getAttribute("total_num");
Double totalPrice =(Double) request.getAttribute("total_price");
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
	String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[5][6].equals("1")||admin.getState().equals("1")){
		
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>分店汇总</title>
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
<script src="js/calendar.js"></script>
<script language="JavaScript">
	function delcfm() {
		if (!confirm("确认要退订？")) {
			window.event.returnValue = false;
		}
	}

	$(function() {
		$('#search')
				.click(
						function() {
										
												var startTime = $("#startTime")
													.val();
												var endTime = $("#endTime")
													.val();
													var code = $("#endTime")
													.val();
											var str = "";
											var a = /((^((1[8-9]\d{2})|([2-9]\d{3}))(-)(10|12|0?[13578])(-)(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(11|0?[469])(-)(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(0?2)(-)(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)(-)(0?2)(-)(29)$)|(^([3579][26]00)(-)(0?2)(-)(29)$)|(^([1][89][0][48])(-)(0?2)(-)(29)$)|(^([2-9][0-9][0][48])(-)(0?2)(-)(29)$)|(^([1][89][2468][048])(-)(0?2)(-)(29)$)|(^([2-9][0-9][2468][048])(-)(0?2)(-)(29)$)|(^([1][89][13579][26])(-)(0?2)(-)(29)$)|(^([2-9][0-9][13579][26])(-)(0?2)(-)(29)$))/;
											
											if (code == "") {
												
													str = str + "店铺编号不能为空！\n";
													$("#code").focus();
												
											}else if (startTime == "") {
												
													str = str + "开始时间不能为空！\n";
													$("#startTime").focus();
												
											}else if (startTime.match(a) == null) {
												str = str + "开始时间格式有误！\n";
												$("#startTime").focus();
											}else if (endTime == "") {
												
													str = str + "结束时间不能为空！\n";
													$("#endTime").focus();
												
											}else if (startTime.match(a) == null) {
												str = str + "结束时间格式有误！\n";
												$("#endTime").focus();
											}
											if (str == "") {
												$('#searchForm').submit();
												return true;
											} else {
												alert(str);
												return false;
											}
						});
	});
</script>
</head>

<body>
	<div class="container clearfix">
		<div class="main-wrap">
			<div class="crumb-wrap">
				<div class="crumb-list">
					<i class="icon-font"></i><a href="admin/index.jsp">首页</a><span
						class="crumb-step">&gt;</span>订单管理
						<span class="crumb-step">&gt;</span><span>分店汇总</span>
				</div>
			</div>
			<div class="search-wrap">
				<div class="search-content">
					<form action="admin/OrderServlet.do?method=admin_branch_summary_list" id="searchForm" method="post">
							<table class="search-tab">
							<tr>
							
						<tr>
							<td>&nbsp;店铺编号：
								<input class="common-text" name="code" id="code" size="15"
									type="text" value='<%=StringUtil.notNull(code) %>'>&nbsp;起止日期：
								<input class="common-text" name="startTime" id="startTime" size="15"
									type="text" value='<%=StringUtil.notNull(startTime) %>' onfocus="new Calendar().show(this);">
									-<input class="common-text" name="endTime" id="endTime" size="15"
									type="text" value='<%=StringUtil.notNull(endTime) %>' onfocus="new Calendar().show(this);">
								<input class="btn btn-primary btn2" id="search"
									name="search" value="查询" type="button">&nbsp;<span
									id="timeTag" style="color:red"></span>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<div class="result-wrap">
			
				 <!--startprint-->
					<div id="printContainer">
					<div class="result-content">

						<table  width="97%" 
						style="border:0px; font-size:12px; border-collapse:separate; border-spacing:0px 10px;">
							<tbody>
							<tr>
							<td  align="center">
							<h2>广州秦筷餐饮服务有限公司分店汇总表</h2>
							<br>
							</td>
							</tr>
							
								<tr>
									<td  align="center">起止日期：<%=StringUtil.notNull(startTime)%>至<%=StringUtil.notNull(endTime)%>
									&nbsp;|&nbsp;分店信息：<%if(branch!=null){%><%=branch.getName() %>(<%=branch.getCode() %>)<%} %></td>
								</tr>
							</tbody>
						</table>
					</div>
					
				
					<div class="result-content">

						<table class="result-tab" width="100%" style="font-size:9px;">		
						<thead>				
								<tr>
							
									<td >编码</td>
									<td>产品名称</td>
									<td>规格</td>
									<td>单位</td>
									<td>单价</td>
									<td>总数</td>
									<td>总金额</td>
									<td>备注</td>
									</tr>
									</thead>
									<tbody>
									
									 <%
 Pager pager = (Pager)request.getAttribute("pager");
						if(pager!=null){
							Collection coll = pager.getResultList();
							if(coll!=null){
									Iterator it1 = coll.iterator();
									while(it1.hasNext()){
									Product b = (Product)it1.next();
							%>
							<tr>
							<td><%=b.getProductId()%></td>
							<td><%=b.getProductName()%></td>
							<td><%=b.getSpecification()%></td>
							<td><%=b.getUnit()%></td>
							<td><%=StringUtil.decimalFormat(b.getPrice()) %></td>
							<td><%=b.getTotalNum()%></td>
							<td><%=StringUtil.decimalFormat(b.getTotalPrice()) %></td>
							<td>-</td>
							</tr><%}} %>
									
										<tr>
							<td>合计</td>
							<td>-</td>
							<td>-</td>
							<td>-</td>
							<td>-</td>
							<td><%=StringUtil.decimalFormat0(totalNum)%></td>
							<td><%=StringUtil.decimalFormat(totalPrice) %></td>
							<td>-</td>
									</tr>	
									<%} %>
		
   
							</tbody>	</table>
					</div>
					<div class="result-content">
						<table  style="border:0px; font-size:12px; border-collapse:separate; border-spacing:0px 10px;" width="97%" >
							<tbody>
							<tr>
									<td>制表人：</td>
									<td>配货人：</td>
									<td>送货人：</td>
									<td>接货人：</td>
  							</tr>
							</tbody>
						</table>
					</div>
					</div>	 
					<!--endprint-->
					   
				 <div class="list-page"> 
                   
			<%if(pager!=null){ %>
			 <div class="list-page-left">
			<form name="pageNoForm"  action="admin/OrderServlet.do?method=admin_branch_summary_list" method="post">
					<input type="hidden" name="startTime" value="<%=StringUtil.notNull(startTime)%>"/>
										<input type="hidden" name="endTime" value="<%=StringUtil.notNull(endTime)%>"/>
															<input type="hidden" name="code" value="<%=StringUtil.notNull(code)%>"/>
					
						<input type="hidden" name="pageSizeStr" value="<%=pager.getPageSize()%>"/>
					第<font color="red"><%=pager.getPageNo()%>
					</font>页&nbsp; 共<font color="red"><%=pager.getPageCount()%></font>页&nbsp; 共<font color="red"><%=pager.getRowCount()%></font>条记录&nbsp;
					<%if(pager.getPageNo()==1) {%> 首页|上一页|<%}else{%>
					<a
						href="admin/OrderServlet.do?method=admin_branch_summary_list&code=<%=StringUtil.notNull(code)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=1">首页</a>|<a
						href="admin/OrderServlet.do?method=admin_branch_summary_list&code=<%=StringUtil.notNull(code)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()-1%>">上一页</a>|<%} %>
                 <%if(pager.getPageNo()!=pager.getPageCount()) {%>
					<a
						href="admin/OrderServlet.do?method=admin_branch_summary_list&code=<%=StringUtil.notNull(code)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()+1%>">
                    下一页</a>|<a
						href="admin/OrderServlet.do?method=admin_branch_summary_list&code=<%=StringUtil.notNull(code)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageCount()%>">尾页</a>
				<%}else{ %>下一页|尾页<%} %>
				 &nbsp;<input type="button" class="btn" value="返回上一页"  onclick="javascript:history.go(-1);" /> 
			</form>
				</div>
                </div>
                <%}%>
			</div>
			
			 
			  
		</div>
		<!--/main-->
	</div>
</body>
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="js/jquery.jqprint-0.3.js"></script>
<script language="Javascript">
function preview()
{
    bdhtml=window.document.body.innerHTML;
    sprnstr="<!--startprint-->";
    eprnstr="<!--endprint-->";
    prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+17);
    prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));
    window.document.body.innerHTML=prnhtml;
    window.print();
     window.document.body.innerHTML=bdhtml;
}
</script>
<script type="text/javascript">
	/* 页面加载完成，绑定事件 */
	$(document).ready(function() {
		$("#sbtn1").bind("click", function() {
			if (!confirm("确认订单公司发货？")) {
			return false;
			}else{
			 	$('#myform').attr("action", "admin/OrderServlet.do?method=admin_order_confirm_yes");
	       		$('#myform').submit();
				return true;
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