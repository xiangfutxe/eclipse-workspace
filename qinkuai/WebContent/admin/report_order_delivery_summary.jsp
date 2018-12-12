<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.Product" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.utils.Pager" %>
<%@page import="com.pojo.Admin" %>
<%@page import="com.pojo.Branch" %>
<%@page import="com.service.ICustomService" %>
<%@page import="com.service.CustomService" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String startTime = (String)request.getAttribute("startTime");
	String endTime = (String)request.getAttribute("endTime");
	Product[] plist = (Product[])request.getAttribute("plist");
	Branch[] blist = (Branch[])request.getAttribute("blist");
	Double[] nlist = (Double[])request.getAttribute("nlist");
	Double[][] bnlist = (Double[][])request.getAttribute("bnlist");
	Pager pager = (Pager)request.getAttribute("pager");
	Integer pnum = (Integer)request.getAttribute("pageNum");
	ICustomService cs = new CustomService();
	int pageNum = 5;
	if(pnum!=null) pageNum= pnum;
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
	String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[7][7].equals("1")||admin.getState().equals("1")){
		double totalNum = 0;
		double totalPrice =0;
		int num =0;
		if(blist!=null){
			for(int i=1;i<blist.length;i++){
			if(blist[i]!=null) num++;
			}
		}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>配货统计表</title>
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
											
											var str = "";
											var a = /((^((1[8-9]\d{2})|([2-9]\d{3}))(-)(10|12|0?[13578])(-)(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(11|0?[469])(-)(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(0?2)(-)(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)(-)(0?2)(-)(29)$)|(^([3579][26]00)(-)(0?2)(-)(29)$)|(^([1][89][0][48])(-)(0?2)(-)(29)$)|(^([2-9][0-9][0][48])(-)(0?2)(-)(29)$)|(^([1][89][2468][048])(-)(0?2)(-)(29)$)|(^([2-9][0-9][2468][048])(-)(0?2)(-)(29)$)|(^([1][89][13579][26])(-)(0?2)(-)(29)$)|(^([2-9][0-9][13579][26])(-)(0?2)(-)(29)$))/;
											
											if (startTime == "") {
												
													str = str + "开始时间不能为空！\n";
													$("#startTime").focus();
												
											}else if (startTime.match(a) == null) {
												str = str + "开始时间格式有误！\n";
												$("#startTime").focus();
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
						class="crumb-step">&gt;</span>报表统计
						<span class="crumb-step">&gt;</span><span>配送总统计</span>
				</div>
			</div>
			<div class="search-wrap">
				<div class="search-content">
					<form action="admin/ReportServlet.do?method=report_order_delivery_summary" id="searchForm" method="post">
							<table class="search-tab">
							<tr>
							
						<tr>
							<td>&nbsp;日期：
								<input class="common-text" name="startTime" id="startTime" size="15"
									type="text" value='<%=StringUtil.notNull(startTime) %>' onfocus="new Calendar().show(this);">
								-<input class="common-text" name="endTime" id="endTime" size="15"
									type="text" value='<%=StringUtil.notNull(endTime) %>' onfocus="new Calendar().show(this);">
									&nbsp;单页显示最大值：<select name = "pageNum" id="pageNum">
								<option value="5" <%if(pageNum==5){ %>selected<%} %>>5</option>
								<option value="6" <%if(pageNum==6){ %>selected<%} %>>6</option>
								
								<option value="7" <%if(pageNum==7){ %>selected<%} %>>7</option>
								<option value="8" <%if(pageNum==8){ %>selected<%} %>>8</option>
								<option value="9" <%if(pageNum==8){ %>selected<%} %>>9</option>
								<option value="10" <%if(pageNum==10){ %>selected<%} %>>10</option>
								<option value="12" <%if(pageNum==12){ %>selected<%} %>>12</option>
								<option value="15" <%if(pageNum==15){ %>selected<%} %>>15</option>
								<option value="18" <%if(pageNum==18){ %>selected<%} %>>18</option>
								<option value="20" <%if(pageNum==20){ %>selected<%} %>>20</option>
								<option value="25" <%if(pageNum==25){ %>selected<%} %>>25</option>
								</select>
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
						style="font_family:'微软雅黑';color:#000 !important;font-weight:normal;border:0px;border-collapse:separate; border-spacing:0px 10px;">
							<tbody>
							<tr style="font-size:20px;">
							<td colspan="3" align="center">
							<strong>配送总统计</strong>
							<br>
							</td>
							</tr>
							<tr style="font-size:16px;">
									<td  align="center">日期：<%=StringUtil.notNull(startTime)%>到<%=StringUtil.notNull(endTime)%>&nbsp;|&nbsp;<%if(pager!=null){ %>第<%=pager.getPageNo() %>页<%} %></td>
								</tr>
							</tbody>
						</table>
					</div>
					
				
					<div class="result-content">

						<table class="result-tab-2" width="100%">		
						<thead>				
								<tr>
								<%
								int  r_num = 1;
								
									int b_num=1;
									 Integer bnum_str = (Integer)request.getAttribute("b_num"); 
									if(bnum_str!=null) {
										if(bnum_str>0) {
										 b_num = bnum_str;
										 r_num=2;} 
									 }%>
									<td rowspan="<%=r_num%>" width="40px">编码</td>
									<td rowspan="<%=r_num%>" width="100px">产品名称</td>
									<td rowspan="<%=r_num%>" width="60px">规格</td>
									<td rowspan="<%=r_num%>" width="30px">单位</td>
									<td rowspan="<%=r_num%>" width="40px">单价</td>
									<td rowspan="<%=r_num%>" width="30px">总数</td>
									<td colspan="<%=b_num%>">经营门店配送产品数量</td>
									<td rowspan="2">备注</td>
									</tr>
									
									<tr>
									 <%
 
						if(pager!=null){
							Collection coll = pager.getResultList();
							if(coll!=null){
									Iterator it1 = coll.iterator();
									while(it1.hasNext()){
									Branch b = (Branch)it1.next();
							%><td  width="15px"><%=b.getName()%></td><%}}} %>
									</tr>
										</thead>
									<tbody>
									
		<%if(plist!=null){
		for(int i=1;i<plist.length;i++){
		if(plist[i]!=null){
		if(nlist[i]>0){
		 %>				
 <tr>
										<td><%=StringUtil.notNull(plist[i].getProductId())%>
										</td>
										<td><%=StringUtil.notNull(plist[i].getProductName())%>
										</td>
										<td><%=StringUtil.notNull(plist[i].getSpecification())%>
										</td>
										<td><%=StringUtil.notNull(plist[i].getUnit())%>
										</td>
										<td><%=StringUtil.decimalFormat(plist[i].getPrice()) %>
										</td>
										<td><%=StringUtil.decimalFormat0(nlist[i]) %>
										</td>
										<%
						if(pager!=null){
							Collection coll = pager.getResultList();
							if(coll!=null){
									Iterator it1 = coll.iterator();
									int j = 0;
									while(it1.hasNext()){
									Branch b = (Branch)it1.next();
									j = b.getId();
									%>
										
										<td><%if(bnlist[j][i]==null){ %>&nbsp;<%}else{ %><%=StringUtil.decimalFormat(bnlist[j][i]) %><%} %>
										</td>
										<%}}}else{ %>
										<td>-
										</td>
										<%} %>
										<td>-
										</td>
									</tr>
									<%}}}} %>
									
		
								
   
							</tbody>	</table>
					</div>
					
					</div>	 
					
					   
				 <div class="list-page"> 
                   
			<%if(pager!=null){ %>
			 <div class="list-page-left">
			<form name="pageNoForm"  action="admin/ReportServlet.do?method=report_order_delivery_summary" method="post">
					<input type="hidden" name="startTime" value="<%=StringUtil.notNull(startTime)%>"/>
<input type="hidden" name="endTime" value="<%=StringUtil.notNull(endTime)%>"/>
					
					<input type="hidden" name="pageNum" value="<%=pageNum%>"/>
						<input type="hidden" name="pageSizeStr" value="<%=pager.getPageSize()%>"/>
					第<font color="red"><%=pager.getPageNo()%>
					</font>页&nbsp; 共<font color="red"><%=pager.getPageCount()%></font>页&nbsp; 共<font color="red"><%=pager.getRowCount()%></font>条记录&nbsp;
					<%if(pager.getPageNo()==1) {%> 首页|上一页|<%}else{%>
					<a
						href="admin/ReportServlet.do?method=report_order_delivery_summary&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&pageNum=<%=pageNum %>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=1">首页</a>|<a
						href="admin/ReportServlet.do?method=report_order_delivery_summary&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&pageNum=<%=pageNum %>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()-1%>">上一页</a>|<%} %>
                 <%if(pager.getPageNo()!=pager.getPageCount()) {%>
					<a
						href="admin/ReportServlet.do?method=report_order_delivery_summary&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&pageNum=<%=pageNum %>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()+1%>">
                    下一页</a>|<a
						href="admin/ReportServlet.do?method=report_order_delivery_summary&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&pageNum=<%=pageNum %>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageCount()%>">尾页</a>
				<%}else{ %>下一页|尾页<%} %>
				 &nbsp;<input type="button" class="btn" value="打印统计表"  onclick="preview()" /> 
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
var b = false;
 b = <%=cs.isShop()%>
 if(!b){
    bdhtml=window.document.body.innerHTML;
    sprnstr="<!--startprint-->";
    eprnstr="<!--endprint-->";
    prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+17);
    prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));
    window.document.body.innerHTML=prnhtml;
    window.print();
     window.document.body.innerHTML=bdhtml;
     }else{
      alert("当前时间不能打印汇总单。");
     }
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