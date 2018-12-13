<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.CenterDetail" %>
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
				String state = (String)request.getAttribute("state");
String userId = (String)request.getAttribute("userId");
String startTime = (String)request.getAttribute("startTime");
String endTime = (String)request.getAttribute("endTime");
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[3][0].equals("1")||admin.getState().equals("1")){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>服务中心</title>
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
		if (!confirm("确认要删除？")) {
			window.event.returnValue = false;
		}
	}
	
	function passowrdcfm() {
		if (!confirm("确认重置登录密码？")) {
		alert( $subs.filter(":checked").length);
			window.event.returnValue = false;
		}
	}
	
	function passowrd2cfm() {
		if (!confirm("确认重置二级密码？")) {
			window.event.returnValue = false;
		}
	}
	
	$(function() {

		$("#batchDel").click(function() {

			if (!confirm("确认要删除？")) {
				window.event.returnValue = false;
			} else {
				$('#delform').attr("action", "admin/apply_deleteAll.action");
				$('#delform').submit();
			}

		});
		
		$("#yes").click(function() {

			if (!confirm("确认通过审核该服务中心？")) {
				window.event.returnValue = false;
			} else {
				window.event.returnValue = true;
			}

		});
		
		$("#no").click(function() {

			if (!confirm("确认拒绝该服务中心的申请？")) {
				window.event.returnValue = false;
			} else {
				window.event.returnValue = true;
			}

		});
		
		$("#passwordinit").click(function() {

			if (!confirm("确认要重置密码？")) {
				window.event.returnValue = false;
			} else {
			var i = 0;
				$("input[name='ids']").each(function(){
				if(this.checked==true) i++;
				}); 
				if(i==0){
				alert("未选择需要重置密码的用户！");
				
					window.event.returnValue = false;
				}else{
				$('#myform').attr("action", "admin/center_password_init.action");
				$('#myform').submit();
					window.event.returnValue = true;
				}
				
			}

		});
		
		$("#password2init").click(function() {

			if (!confirm("确认要重置二级密码？")) {
				window.event.returnValue = false;
			} else {
			var i = 0;
				$("input[name='ids']").each(function(){
				if(this.checked==true) i++;
				}); 
				if(i==0){
				alert("未选择需要重置密码的用户！");
				
					window.event.returnValue = false;
				}else{
				$('#myform').attr("action", "admin/password2_init.action");
				$('#myform').submit();
					window.event.returnValue = true;
				}
				
			}

		});

		$("#search")
				.click(
						function() {
							var str = "";
							
							if (str == "") {
								$('#searchForm').submit();
							} else {
								
								return false;
							}
						});
						
		$("#ckAll").click(function() {
				if(this.checked){ 
					$("input[name='ids']").each(function(){this.checked=true;}); 
				}else{ 
					$("input[name='ids']").each(function(){this.checked=false;}); 
				} 
			});
	
			$("input[name='ids']").click(function() {
		    var $ids = $("input[name='ids']");
		    $("#ckAll").prop("checked" , $ids.length == $subs.filter(":checked").length ? true :false);
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
						class="crumb-step">&gt;</span><span class="crumb-name">会员管理</span>
						<span
						class="crumb-step">&gt;</span><span class="crumb-name">服务中心</span>
				</div>
			</div>
			<div class="search-wrap">
				<div class="search-content">
					<form action="admin/CenterServlet.do?method=admin_detail_list" id="searchForm"
						method="post">
						<table class="search-tab">
							<tr>
								<th width="80">当前状态:</th>
								<td><select class="common-text" id="state"
									name="state">
										<option value="">--请选择状态--</option>
										<option value="1" <%if(state.equals("1")){ %>selected<%} %>>审核中</option>
										<option value="2" <%if(state.equals("2")){ %>selected<%} %>>审核通过</option>
											<option value="0" <%if(state.equals("0")){ %>selected<%} %>>已关闭</option>
								</select></td>
								<th width="80">用户编号:</th>
								<td><input class="common-text"  name="userId" id="userId" type="text"  value='<%=StringUtil.notNull(userId)%>' readonly/></td>
									
								<td><input class="btn btn-primary btn2" id="search"
									name="search" value="查询" type="button"><span
									id="timeTag" style="color:red"></span>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
				
			<div class="result-wrap">
			<form  id="myform" method="post">
			 <div class="result-title">
                    <div class="result-list">
                    <!--  <button id="passwordinit" class="btn"><i class="icon-font">&#xe035;</i>初始化密码</button>（备注：初始化密码均为：12345678。） -->   
                     
                    </div>
                </div>
                 <div class="result-title">
                    <div class="result-list">
                        <a href="admin/CenterServlet.do?method=list"><i class="icon-font">&#xe037;</i>返回服务中心</a>
                    </div>
                </div>
				<div class="result-content">
					<table class="result-tab" width="97%">
						<tr>
						<th class="tc" width="5%"><input class="allChoose" id="ckAll" name="ckAll" type="checkbox"></th>
							<th>中心编号</th><th>中心类型</th><th>中心名称</th><th>用户信息</th><th>经营区域</th><th>经营地址</th><th>当前状态</th><th>操作</th>
						</tr>
						 <%
								 Pager pager = (Pager)request.getAttribute("pager");
						if(pager!=null){
							Collection coll = pager.getResultList();
							System.out.println(coll.size());
							if(coll!=null){
									Iterator it = coll.iterator();
									while(it.hasNext()){
									CenterDetail center = (CenterDetail)it.next();
							%>
 <tr>
  <td class="tc"><input name="ids" type="checkbox" value="<%=center.getId()%>"></td>
 
  <td><%=StringUtil.notNull(center.getCenterId()) %></td>
  <td><%if(center.getType()==1){%>
								社区店（宅地）<%}else if(center.getType()==2){%>社区点（落地）<%}else if(center.getType()==3){%>
								旗舰店（落地）<%}%></td>
 <td><%=StringUtil.notNull(center.getCenterName()) %></td>
 <td><%=StringUtil.notNull(center.getUserName()) %>(<%=StringUtil.notNull(center.getUserId()) %>)</td>
<td><%=StringUtil.notNull(center.getProvince()) %><%=StringUtil.notNull(center.getCity()) %><%=StringUtil.notNull(center.getArea()) %></td>
 
 <td><%=StringUtil.notNull(center.getAddress()) %></td>
 
  <td><%if(center.getState()==1){ %>审批中<%}else if(center.getState()==2){ %>审批通过<%}else{ %>已关闭<%} %>
                                </td>
 <td><%if(center.getState()==1){ %><a id="yes" href="admin/CenterServlet.do?method=shop_save_yes&id=<%=center.getId() %>">审核通过</a> &nbsp;|&nbsp;<a id="no"  href="admin/CenterServlet.do?method=shop_save_no&id=<%=center.getId() %>">拒绝申请</a><%} %></td>
  
 </tr>
	<%}}
						}else{ %><tr>
							<td colspan="9">暂无记录！</td>
						 </tr>
						 <%} %>
					</table>


				</div>
				</form>
			  <%if(pager!=null){ %>
				 <div class="list-page"> 
                    <div class="list-page-left">
                   <form name="pageSizeForm"  action="admin/CenterServlet.do?method=list" method="post">
							<input type="hidden" name="pageNoStr" value="1"/>
							<input type="hidden" name="state" value="<%=StringUtil.notNull(state)%>"/>
							<input type="hidden" name="userId" value="<%=StringUtil.notNull(userId)%>"/>
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
			<form name="pageNoForm"  action="admin/CenterServlet.do?method=list" method="post">
			<input type="hidden" name="state" value="<%=StringUtil.notNull(state)%>"/>
							<input type="hidden" name="userId" value="<%=StringUtil.notNull(userId)%>"/>
						<input type="hidden" name="pageSizeStr" value="<%=pager.getPageSize()%>"/>
					第<font color="red"><%=pager.getPageNo()%>
					</font>页&nbsp; 共<font color="red"><%=pager.getPageCount()%></font>页&nbsp; 共<font color="red"><%=pager.getRowCount()%></font>条记录&nbsp; &nbsp; &nbsp; 
					<%if(pager.getPageNo()==1) {%> 首页  | 上一页  |<%}else{%>
					<a
						href="admin/CenterServlet.do?method=list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=1
						&userId=<%=StringUtil.notNull(userId)%>&state=<%=StringUtil.notNull(state)%>">首页</a> | <a
						href="admin/CenterServlet.do?method=list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()-1%>">上一页</a>  | <%} %>
                 <%if(pager.getPageNo()!=pager.getPageCount()) {%>
					<a
						href="admin/CenterServlet.do?method=list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()+1%>
						&userId=<%=StringUtil.notNull(userId)%>&state=<%=StringUtil.notNull(state)%>">
                    下一页</a> | <a
						href="admin/CenterServlet.do?method=list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageCount()%>
						&userId=<%=StringUtil.notNull(userId)%>&state=<%=StringUtil.notNull(state)%>">尾页</a>
				<%}else{ %>下一页 | 尾页<%} %>&nbsp;&nbsp; &nbsp; 跳转到第<select
						name="pageNoStr" onchange="javascript:pageNoForm.submit();">
						<%for(int i=0;i<pager.getPageCount();i++){ %>
							<option value="<%=i+1 %>" <%if(pager.getPageNo()==(i+1)){%>selected<%} %>>
								<%=i+1 %>
							</option>
							<%} %>
					</select>页&nbsp;
			</form>
				</div>
                </div>
                <%} %>
			</div>
		</div>
		<!--/main-->
	</div>
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
