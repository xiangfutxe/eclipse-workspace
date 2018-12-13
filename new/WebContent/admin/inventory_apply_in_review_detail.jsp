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
	InventoryApply ap = (InventoryApply) request.getAttribute("inventory");
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[3][5].equals("1")||admin.getState().equals("1")){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>入库审核</title>
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
 <script type="text/javascript">
	
	$(function() {
  	 
  	  $("#rybtn").click(function(){
  	  if (!confirm("确认要审核通过？")) {
				return false;
			}else{
						$("#myform").attr("action",'admin/InventoryServlet.do?method=apply_in_review_yes');
					$("#myform").submit();
		       		 return true;
		 }   
	   });
   
    $("#rnbtn").click(function(){
  	  if (!confirm("确认要审核不通过？")) {
				return false;
			}else{
						$("#myform").attr("action",'admin/InventoryServlet.do?method=apply_in_review_no');
					$("#myform").submit();
		       		 return true;
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
					<i class="icon-font"></i><a href="admin/index.jsp">首页</a>
					<span class="crumb-step">&gt;</span><span>库存管理</span>
					<span class="crumb-step">&gt;</span><span>入库审核</span><span
						class="crumb-step">&gt;</span><span class="crumb-name">详情</span>
				</div>
			</div>
			
			<div class="result-wrap">
				<div class="result-title">
					<div class="result-list">
						入库订单详情
					</div>
				</div>
				<div class="result-content">
					<table class="result-tab" width="100%">
						<tr>
							<th colspan="8">订单信息</th>
						</tr>
							<tr>
								<td>申请编号
								</td>
								<td><%=StringUtil.notNull(ap.getApplyId()) %>
								</td>
								<td>仓库类型</td>
								<td><%=StringUtil.notNull(ap.getInventory()) %></td>
							
								<td>订单类型
								</td>
								<td>入库
								</td>
								<td>当前状态</td>
								<td><%if(ap.getState()==1){%>待审核<%}else if(ap.getState()==2) {%>审核通过<%}else if(ap.getState()==3) {%>审核不通过<%}else{ %>已删除<%} %></td>
								
								
							</tr>
							<tr>
								<td>申请人
								</td>
								<td><%=StringUtil.notNull(ap.getAdminByOperatorId()) %>
								</td>
								<td>申请时间</td>
								<td><%=StringUtil.parseToString(ap.getTime(), DateUtil.yyyyMMddHHmmss) %>
								</td>
								<td>申请人
								</td>
								<td><%=StringUtil.notNull(ap.getAdminByReviewerId()) %>
								</td>
								<td>申请时间</td>
								<td><%=StringUtil.parseToString(ap.getReviewTime(), DateUtil.yyyyMMddHHmmss) %>
								</td>
							</tr>
					</table>


				</div>
				<div class="result-title">
					<div class="result-list">
						&nbsp;
					</div>
				</div>
				<div class="result-content">
					<table class="result-tab" width="100%">
						<tr>
							<th>产品编号</th>
							<th>产品名称</th>
							<th>产品类型</th>
							<th>采购价格</th>
							<th>采购数量</th>
						</tr>
						 <%
							Collection coll = (Collection)request.getAttribute("coll");
							if(coll!=null){
									Iterator it1 = coll.iterator();
									while(it1.hasNext()){
									InventoryDetail p = (InventoryDetail)it1.next();
							%>
							<tr>
								 <td> <%=StringUtil.notNull(p.getProductId())%> </td>
						  <td> <%=StringUtil.notNull(p.getProductName())%> </td>
					 		 <td> <%if(p.getType()==1){%>单品<%}else if(p.getType()==2){%>套装<%} %></td>
						<td> <%=StringUtil.decimalFormat(p.getPrice())%></td>
							<td> <%=p.getNum()%></td>
							</tr>
					<%}} %>
					</table>

				</div>
				<form id="myform" name="myform" method="post">
				 <input type="hidden" id="id" name="id" value='<%=ap.getApplyId()%>'/>
				 <div class="result-list">
                     <table class="search-tab">
                        <tr>
                            <th>
                           <%if(ap.getState()==1){ %>
							<button class="btn btn-success" id="rybtn"><i class="icon-font">&#xe025;</i>审核通过</button>
                             <button class="btn btn-danger" id="rnbtn"><i class="icon-font">&#xe024;</i>审核不通过</button>
                            <%} %>
                    <button class="btn btn-grey" id="ebtn" onclick="javascript:history.go(-1)"><i class="icon-font">&#xe02b;</i>返回上一页</button></th>
                     </tr>
                     </table>
                     </div>
                     
					 </form>
                     </div>

			</div>
		</div>
		<!--/main-->
</body>
</html>

<%
}else{
			out.println("<script>");
			out.println("alert('你没有权限进行该操作！')");
			out.println("</script>");
		}
}
%>