<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.OrderDetail" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.utils.Pager" %>
<%@page import="com.pojo.Admin" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
String type = (String)request.getAttribute("type");
if(admin==null) {
out.println("<script>");
out.println("parent.window.location.href='login.jsp';");
out.println("</script>");
}else{
String startTime = (String)request.getAttribute("startTime");
String endTime = (String)request.getAttribute("endTime");
String productId = (String)request.getAttribute("productId");
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[7][4].equals("1")||admin.getState().equals("1")){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>订单统计</title>
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
	<script src="js/calendar.js"></script>
<script language="JavaScript">
	function delcfm() {
		if (!confirm("确认要退订？")) {
			window.event.returnValue = false;
		}
	}

	$(function() {
	$("#exportExcel").click(function(){
	  	  if (!confirm("确认提交导出Excel？")) {
				return false;
			}
			else{
	       		$("#searchForm").attr("action", "admin/ReportServlet.do?method=exportExcel_order_summary");
				$("#searchForm").submit();
	       		 return true;
			}
		 });
		$('#search')
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
  <div class="container clearfix">
   <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a>
            <span class="crumb-step">&gt;</span><span class="crumb-name">报表统计</span>
            <span class="crumb-step">&gt;</span><span class="crumb-name">单品总统计</span></div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
                <form action="admin/ReportServlet.do?method=report_product_detail_summary"  id="searchForm" method="post">
                    <table class="search-tab">
                        <tr>
                          	<th width="100">&nbsp;产品编号：</th><td>
                          	<input type="text" name="productId" id="productId" value="<%=StringUtil.notNull(productId)%>">
                          	起止日期：
								
									<input class="common-text" name="startTime" id="startTime" size="15"
									type="text" value='<%=StringUtil.notNull(startTime) %>' onfocus="new Calendar().show(this);">
								-<input class="common-text" name="endTime" id="endTime" size="15"
									type="text" value='<%=StringUtil.notNull(endTime) %>' onfocus="new Calendar().show(this);">
								&nbsp;<input class="btn btn-primary btn2" id="search"
									name="search" value="查询" type="button">
									&nbsp;<input class="btn btn-info" id="exportExcel"
									name="exportExcel" value="导出Excel" type="button"><span
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
							<strong>产品出入库明细汇总表</strong>
							<br>
							</td>
							</tr>
							<tr style="font-size:16px;">
									<td  align="center">统计日期：<%=StringUtil.notNull(startTime)%>&nbsp;到&nbsp;<%=StringUtil.notNull(endTime)%></td>
								</tr>
							</tbody>
						</table>
					</div>
					
				
					<div class="result-content">

						<table class="result-tab-2" width="100%">		
                        <tr>
                             <th>分类</th>
                            <th>编号</th>
                            <th>货品名称</th>
                            <th>规格</th>
                            <th>单位</th>
                             <th>单价</th>
                             <th>类型</th>
                            <th>数量</th>
                             <th>日期</th>
                        </tr>
                        <%
 Pager pager = (Pager)request.getAttribute("pager");
						if(pager!=null){
							Collection coll = pager.getResultList();
							if(coll!=null){
									Iterator it1 = coll.iterator();
									while(it1.hasNext()){
									OrderDetail p = (OrderDetail)it1.next();
							%>
					  	<tr>
							<td> <%=StringUtil.notNull(p.getProductType())%></td>						 
						 <td> <%=StringUtil.notNull(p.getProductId())%> </td>
						  <td> <%=StringUtil.notNull(p.getProductName())%> </td>
						   <td> <%=StringUtil.notNull(p.getSpecification())%></td>
					 <td> <%=StringUtil.notNull(p.getUnit())%></td>
						   <td>
						  <%=StringUtil.decimalFormat(p.getPrice())%></td>
						   <td > <%if(p.getType()==1){%>消费购物
						   <%}else if(p.getType()==2){ %>新店配货
						   <%}else if(p.getType()==3){ %>加工出库
						   <%}else if(p.getType()==4){ %>盘亏出库
						   <%}else if(p.getType()==5){ %>损耗出库
						   <%}else if(p.getType()==6){ %>外销出库
						   <%}else if(p.getType()==7){ %>体验出库
						   <%}else if(p.getType()==8){ %>赠送出库
						   <%}else if(p.getType()==9){ %>研发出库
						   <%}else if(p.getType()==10){ %>退货出库
						   <%}else if(p.getType()==11){ %>采购入库	
						   <%}else if(p.getType()==12){ %>加工入库
						   <%}else if(p.getType()==13){ %>退货入库
						   <%}else if(p.getType()==14){ %>盘盈入库
						   <%} %></td>
						  <td > <%=StringUtil.decimalFormat(p.getNum())%> </td>
						  
 							<td > <%=StringUtil.parseToString(p.getEntryTime(), DateUtil.yyyyMMddHHmmss)%>
						 </td>
                                
						 </tr>
							<%}
							double[] sum = (double[])request.getAttribute("sum");
							%>
							<tr><td colspan="9">入库总数：<%=StringUtil.decimalFormat(sum[1])%>;&nbsp;出库总数：<%=StringUtil.decimalFormat(sum[0])%>.</td>
							
							</tr>
							<%}
						}else{ %>
						 <tr>
						 <td colspan="9">暂无销售信息。</td>
						  </tr>
						 <%} %>
                    </table>
                     </div>
                     	<!--endprint-->
                     <%if(pager!=null){ %>
				 <div class="list-page"> 
                    <div class="list-page-left">
                   <form name="pageSizeForm"  action="admin/ReportServlet.do?method=report_product_detail_summary" method="post">
							<input type="hidden" name="startTime" value="<%=StringUtil.notNull(startTime)%>"/>
							<input type="hidden" name="endTime" value="<%=StringUtil.notNull(endTime)%>"/>
							<input type="hidden" name="productId" value="<%=StringUtil.notNull(productId)%>"/>
						
							<input type="hidden" name="pageNoStr" value="1"/>
					当前每页显示<select name="pageSizeStr" id="pageSizeStr" onchange="javascript:pageSizeForm.submit();">
					
						<option value="10"
							<%if(pager.getPageSize()==10) {%>selected<%}%>>10</option>
						<option value="20"
							<%if(pager.getPageSize()==20) {%> selected<%}%>>20</option>
						<option value="40"
							<%if(pager.getPageSize()==40) {%> selected<%}%>>40</option>
						<option value="60"
							<%if(pager.getPageSize()==60) {%> selected<%}%>>60</option>
						<option value="80"
							<%if(pager.getPageSize()==80) {%>selected<%}%>>80</option>
						<option value="100"
							<%if(pager.getPageSize()==100) {%> selected<%}%>>100</option>
							<option value="200"
							<%if(pager.getPageSize()==200) {%> selected<%}%>>200</option>
					</select>条记录
					</form>
                    
			</div> <div class="list-page-right">
			<form name="pageNoForm"  action="admin/ReportServlet.do?method=report_product_detail_summary" method="post">
						<input type="hidden" name="startTime" value="<%=StringUtil.notNull(startTime)%>"/>
							<input type="hidden" name="endTime" value="<%=StringUtil.notNull(endTime)%>"/>
						<input type="hidden" name="productId" value="<%=StringUtil.notNull(productId)%>"/>
						
						<input type="hidden" name="pageSizeStr" value="<%=pager.getPageSize()%>"/>
					第<font color="red"><%=pager.getPageNo()%>
					</font>页&nbsp; 共<font color="red"><%=pager.getPageCount()%></font>页&nbsp; 共<font color="red"><%=pager.getRowCount()%></font>条记录&nbsp;
					<%if(pager.getPageNo()==1) {%> 首页|上一页|<%}else{%>
					<a
						href="admin/ReportServlet.do?method=report_product_detail_summary&productId=<%=StringUtil.notNull(productId)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=1">首页</a>|<a
						href="admin/ReportServlet.do?method=report_product_detail_summary&productId=<%=StringUtil.notNull(productId)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()-1%>">上一页</a>|<%} %>
                 <%if(pager.getPageNo()!=pager.getPageCount()) {%>
					<a
						href="admin/ReportServlet.do?method=report_product_detail_summary&productId=<%=StringUtil.notNull(productId)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()+1%>">
                    下一页</a>|<a
						href="admin/ReportServlet.do?method=report_product_detail_summary&productId=<%=StringUtil.notNull(productId)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageCount()%>">尾页</a>
				<%}else{ %>下一页|尾页<%} %>&nbsp;跳转到第
				<select
						name="pageNoStr" onchange="javascript:pageNoForm.submit();">
						<%for(int i=0;i<pager.getPageCount();i++){ %>
							<option value="<%=i+1 %>" <%if(pager.getPageNo()==(i+1)){%>selected<%} %>>
								第<%=i+1 %>页
							</option>
							<%} %>
					</select>&nbsp; &nbsp;<input type="button" class="btn" value="打印统计表"  onclick="preview()" /> 
			</form>
				</div>
                </div>
                <%}%>
			</div>
			</div>
		</div>
		<!--/main-->
	</div>
</body>
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
</html>
<%
}else{
			out.println("<script>");
			out.println("alert('你没有权限进行该操作！')");
			out.println("</script>");
		}
}
%>
