<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.Inventory" %>
<%@page import="com.pojo.InventoryDetail" %>
<%@page import="com.pojo.InventoryApply" %>
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
	InventoryApply ap = (InventoryApply) request.getAttribute("admin_apply");
	InventoryDetail total_ap = (InventoryDetail) request.getAttribute("total_ap");
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
		if(rankstr[3][7].equals("1")||admin.getState().equals("1")){
		
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>入库详情</title>
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
					<i class="icon-font"></i><a href="admin/index.action">首页</a><span
						class="crumb-step">&gt;</span><span>仓库管理</span>
						<span
						class="crumb-step">&gt;</span><span>入库仓管确认</span><span
						class="crumb-step">&gt;</span><span class="crumb-name">入库单</span>
				</div>
			</div>
			
			<div class="result-wrap">
				
				<div class="result-content">
					<table width="100%" style="border:0px;font-size:12px; border-collapse:separate; border-spacing:0px 10px;">
						<tr>
							<td  colspan="2" align="center">
							<h4><%if(ap.getType()==1){ %>采购<%}else if(ap.getType()==2){ %>加工
							<%}else if(ap.getType()==3){ %>退货<%}else if(ap.getType()==4){ %>盘盈<%} %>入库单</h4>
							</td>
						</tr>
						<tr>
							<td>
							申请单号：<%=ap.getApplyId() %>
							</td>
							<td>
							票据单号：<%=ap.getDocNum() %>
							</td>
						</tr>
						<tr>
							<td>
							供应商：<%=ap.getSupplierName() %>
							</td>
							<td>
							日期：<%=StringUtil.parseToString(ap.getEntryTime(), DateUtil.yyyyMMddHHmmss) %>
							</td>
						</tr>
						</table>
						<table class="insert-tab" width="100%">
						
							<tr>
								<td>序号
								</td>
								<td>编号
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
								<td>操作
								</td>
							</tr>
					 <%
							Collection coll = (Collection)request.getAttribute("coll");
							int t=0;
							if(coll!=null){
									Iterator it1 = coll.iterator();
									while(it1.hasNext()){
									InventoryDetail p = (InventoryDetail)it1.next();
									t++;
							%>
							<tr>
							<td><%=t%>
								 <td> <%=StringUtil.notNull(p.getProductId())%> </td>
						  <td> <%=StringUtil.notNull(p.getProductName())%> </td>
					 <td> <%=StringUtil.notNull(p.getSpecification())%></td>
					  <td> <%=StringUtil.notNull(p.getUnit())%></td>
					   <td> <%=StringUtil.decimalFormat(p.getNum())%></td>
						<td> <%=StringUtil.decimalFormat(p.getPrice())%></td>
						<td> <%=StringUtil.decimalFormat(p.getTotalPrice())%></td>
						<td><%=StringUtil.notNull(p.getRemark()) %></td>
						<td><%if(ap.getType()==1){%><a href="admin/InventoryServlet.do?method=apply_in_cfm_edit&id=<%=p.getId()%>">修改</a><%} %>
							</tr>
					<%}
					if(coll.size()<6){
					for(int i=t;i<6;i++){%>
					<tr>
					<td><%=i+1 %>
								 <td>&nbsp; </td>
						  <td>&nbsp;</td>
					 <td>&nbsp;</td>
					  <td>&nbsp;</td>
					   <td>&nbsp;</td>
						 <td>&nbsp;</td>
					 <td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						</tr>
					<% }
					}
					}else{ 
						for(int i=0;i<6;i++){%>
					<tr>
					<td><%=i+1 %>
								 <td>&nbsp; </td>
						  <td>&nbsp;</td>
					 <td>&nbsp;</td>
					  <td>&nbsp;</td>
					   <td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						</tr>
					<%}} %>
					<tr>
					<td>合计
								 <td>&nbsp; </td>
						  <td>&nbsp;</td>
					 <td>&nbsp;</td>
					  <td>&nbsp;</td>
					    <td><%=StringUtil.decimalFormat(total_ap.getNum()) %></td>
						<td><%if(ap.getType()==1){%><%=StringUtil.decimalFormat(total_ap.getPrice()) %><%} %></td>
						<td><%if(ap.getType()==1){%><%=StringUtil.decimalFormat(total_ap.getTotalPrice()) %><%} %></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						</tr>
						<tr>
					 <td>备注</td>
						
								 <td colspan="9"><%=StringUtil.notNull(ap.getRemark()) %></td>
						
						
						</tr>
					</table>
				</div>
				<p>&nbsp;</p>
				 <div class="result-list">
				 	<form  id="cfmform" method="post">
				 <input type="hidden" name="id" id="id" value="<%=ap.getApplyId()%>"/>
                     <table class="search-tab">
                        <tr>
                            <td>
                            <%if(ap.getState()==3){ %>
                            <button class="btn btn-info" id="ybtn"><i class="icon-font">&#xe025;</i>确认通过</button>&nbsp;
                             <button class="btn btn-info" id="nbtn"><i class="icon-font">&#xe024;</i>确认不通过</button>&nbsp;
                             
                  <%}else if(ap.getState()==6){ %>
                     <button class="btn btn-info" id="re_btn"><i class="icon-font">&#xe025;</i>重新确认</button>&nbsp;
                  
                  <%} %>
                   <button class="btn" id="b_btn" ><i class="icon-font">&#xe046;</i>返回确认列表</button>&nbsp;
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
	
	$("#ybtn").click(function(){
		if (!confirm("确认通过？")) {
								return false;
							} else {
				$('#cfmform').attr("action","admin/InventoryServlet.do?method=apply_in_cfm_yes");
							
       		 $('#cfmform').submit();
       		 return true;
}
	});
	
	$("#re_btn").click(function(){
		if (!confirm("确认重新提交？")) {
								return false;
							} else {
				$('#cfmform').attr("action","admin/InventoryServlet.do?method=apply_in_cfm_yes");
							
       		 $('#cfmform').submit();
       		 return true;
}
	});
	
	$("#nbtn").click(function(){
		if (!confirm("确认不通过，不通过入库单将退回上一步流程？")) {
								return false;
							} else {
											$('#cfmform').attr("action","admin/InventoryServlet.do?method=apply_in_cfm_no");
							
       		 $('#cfmform').submit();
       		 return true;
}
	});
	
	$("#b_btn").click(function(){
		
				$('#cfmform').attr("action","admin/InventoryServlet.do?method=apply_in_cfm");
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