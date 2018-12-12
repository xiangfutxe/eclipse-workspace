<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.Branch" %>
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
	String code = (String)request.getAttribute("branch_code");
String startTime = (String)request.getAttribute("startTime");
String endTime = (String)request.getAttribute("endTime");
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[1][0].equals("1")||admin.getState().equals("1")){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>分店列表</title>
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
		if (!confirm("确认要进行退单操作？")) {
			return false;
		}
	}
	
	function passowrdcfm() {
		if (!confirm("确认重置登录密码？")) {
		alert( $subs.filter(":checked").length);
			return false;
		}
	}
	
	function passowrd2cfm() {
		if (!confirm("确认重置二级密码？")) {
			return false;
		}
	}
	

	$(function() {

		$("#delAll").click(function() {

			if (!confirm("确认要删除所选店铺？")) {
				return false;
			} else {
				$('#myform').attr("action", "admin/BranchServlet.do?method=admin_delAll");
				$('#myform').submit();
			}

		});
		
		 $("#exportExcel").click(function(){
	  	  if (!confirm("确认提交导出Excel？")) {
				return false;
			}
			else{
	       		$("#searchForm").attr("action", "admin/BranchServlet.do?method=exportExcel");
				$("#searchForm").submit();
	       		 return true;
			}
		 });
		
		$("#passwordinit").click(function() {

			if (!confirm("确认要重置登陆密码？")) {
				return false;
			} else {
			var i = 0;
				$("input[name='ids']").each(function(){
				if(this.checked==true) i++;
				}); 
				if(i==0){
				alert("未选择需要重置密码的用户！");
				
					return false;
				}else{
				$('#myform').attr("action", "admin/BranchServlet.do?method=admin_psw1_init");
				$('#myform').submit();
					return true;
				}
				
			}

		});
		
		$("#password2init").click(function() {

			if (!confirm("确认要重置支付密码？")) {
				return false;
			} else {
			var i = 0;
				$("input[name='ids']").each(function(){
				if(this.checked==true) i++;
				}); 
				if(i==0){
				alert("未选择需要重置密码的用户！");
				
					return false;
				}else{
				$('#myform').attr("action", "admin/BranchServlet.do?method=admin_psw2_init");
				$('#myform').submit();
					return true;
				}
			}
		});
		
		$("#lock").click(function() {

			if (!confirm("确认要锁定该用户？")) {
				return false;
			} else {
			var i = 0;
				$("input[name='ids']").each(function(){
				if(this.checked==true) i++;
				}); 
				if(i==0){
				alert("未选择需要锁定的用户！");
				
					return false;
				}else{
				$('#myform').attr("action", "admin/BranchServlet.do?method=admin_lock");
				$('#myform').submit();
					return true;
				}
			}
		});
		
		$("#unlock").click(function() {
			if (!confirm("确认要解除锁定用户？")) {
				return false;
			} else {
			var i = 0;
				$("input[name='ids']").each(function(){
				if(this.checked==true) i++;
				}); 
				if(i==0){
				alert("未选择需要解除锁定的用户！");
				
					return false;
				}else{
				$('#myform').attr("action", "admin/BranchServlet.do?method=admin_unlock");
				$('#myform').submit();
					return true;
				}
			}
		});
		
		$("#search")
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
						class="crumb-step">&gt;</span><span class="crumb-name">分店管理</span>
						<span
						class="crumb-step">&gt;</span><span class="crumb-name">分店列表</span>
				</div>
			</div>
			<div class="search-wrap">
				<div class="search-content">
					<form action="admin/BranchServlet.do?method=admin_list" id="searchForm"
						method="post">
						<table class="search-tab">
							<tr>
								<td> &nbsp;分店编号：<input class="common-text" name="code" id="code" size="15"
									type="text" value='<%=StringUtil.notNull(code) %>'>
									&nbsp;注册日期：
								<input class="common-text" name="startTime" id="startTime" size="15"
									type="text" value='<%=StringUtil.notNull(startTime) %>' onfocus="new Calendar().show(this);">
								-
								<input class="common-text" name="endTime" id="endTime" size="15"
									type="text" value='<%=StringUtil.notNull(endTime) %>' onfocus="new Calendar().show(this);">
								&nbsp;<input class="btn btn-primary btn2" id="search"
									name="search" value="查询" type="button">&nbsp;
									<%if(rankstr[1][5].equals("1")||admin.getState().equals("1")){ %>
									<input class="btn btn-info" id="exportExcel"
									name="exportExcel" value="导出Excel" type="button">
									<%} %><span
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
                    <%if(rankstr[1][7].equals("1")||admin.getState().equals("1")){ %>
                        <button id="passwordinit" class="btn"><i class="icon-font">&#xe046;</i>登陆密码重置</button>
                         <button id="password2init" class="btn"><i class="icon-font">&#xe046;</i>支付密码重置</button>
                       <%}if(rankstr[1][8].equals("1")||admin.getState().equals("1")){ %>
                        <button id="lock" class="btn"><i class="icon-font">&#xe016;</i>锁定</button>
                         <button id="unlock" class="btn"><i class="icon-font">&#xe015;</i>解锁</button>
                          <%}if(rankstr[1][3].equals("1")||admin.getState().equals("1")){ %>
                        <button id="delAll" class="btn"><i class="icon-font">&#xe019;</i>批量删除</button>
                     <%}%>
                    </div>
                </div>
				<div class="result-content">
					<table class="result-tab" width="100%" style="font-size:9px;">
						<tr>
						<th class="tc" width="20px"><input class="allChoose" id="ckAll" name="ckAll" type="checkbox"></th>
							<th width="28px">序号</th>
							<th>编号</th>
							<th>店名</th>
							<th width="55px">店商类型</th>
							<th>联系人</th>
							<th width="55px">授信额度</th>
							<th width="55px">剩余额度</th>
							<th width="55px">预付款</th>
							<th width="90px">手机号码</th>
							<th>地址</th>
							<th  width="28px">状态</th><th>操作</th>
						</tr>
						  <%
								 Pager pager = (Pager)request.getAttribute("pager");
						if(pager!=null){
							Collection coll = pager.getResultList();
							if(coll!=null){
									Iterator it = coll.iterator();
									while(it.hasNext()){
									Branch bh = (Branch)it.next();
							%>
 <tr>
  <td class="tc"><input name="ids" type="checkbox" value="<%=bh.getId()%>"></td>
  <td><%=bh.getAscNum() %>
 <td><%=StringUtil.notNull(bh.getCode())%></td>
 <td><%=StringUtil.notNull(bh.getName())%></td>
 <td><%if(bh.getType()==1){%>加盟店<%}else if(bh.getType()==2){%>直营店<%}else if(bh.getType()==3){%>外购商<%} %></td>
 <td><%=StringUtil.notNull(bh.getLinkman())%></td>
<td><%=StringUtil.decimalFormat(bh.getCredit())%></td>
<td><%=StringUtil.decimalFormat(bh.getCredit()-bh.getCreditUsed())%></td>
<td><%=StringUtil.decimalFormat(bh.getEmoney())%></td>
<td><%=StringUtil.notNull(bh.getTel())%></td>
<td><%=StringUtil.notNull(bh.getProvince())%><%=StringUtil.notNull(bh.getCity())%><%=StringUtil.notNull(bh.getArea())%><%=StringUtil.notNull(bh.getAddress())%></td>
<td><%if(bh.getState()==1){%>正常<%}else if(bh.getState()==2){%>锁定<%}else{%>-<%} %></td>

 <td>
 <%if(rankstr[1][2].equals("1")||admin.getState().equals("1")){ %>
 <a href='admin/BranchServlet.do?method=admin_edit&code=<%=bh.getCode()%>'>修改</a>|
 <a href='admin/BranchServlet.do?method=admin_up&code=<%=bh.getCode()%>'>上移</a>|
 <a href='admin/BranchServlet.do?method=admin_down&code=<%=bh.getCode()%>'>下移</a>|
 <%}if(rankstr[1][4].equals("1")||admin.getState().equals("1")){ %><a href='admin/BranchServlet.do?method=admin_credit_add&code=<%=bh.getId()%>'>授信</a>
 <%} %></td>
  
 </tr>
	 	<%}}
						}else{ %><tr><td colspan="11">暂无记录！</td></tr><%} %>
					</table>


				</div>
				</form>
				 <%if(pager!=null){ %>
				 <div class="list-page"> 
                    <div class="list-page-left">
                   <form name="pageSizeForm"  action="admin/BranchServlet.do?method=admin_list" method="post">
							<input type="hidden" name="pageNoStr" value="1"/>
							<input type="hidden" name="code" value="<%=StringUtil.notNull(code)%>"/>
							<input type="hidden" name="startTime" value="<%=StringUtil.notNull(startTime)%>"/>
							<input type="hidden" name="endTime" value="<%=StringUtil.notNull(endTime)%>"/>
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
			<form name="pageNoForm"  action="admin/BranchServlet.do?method=admin_list" method="post">
						<input type="hidden" name="pageSizeStr" value="<%=pager.getPageSize()%>"/>
						<input type="hidden" name="code" value="<%=StringUtil.notNull(code)%>"/>
							
							<input type="hidden" name="startTime" value="<%=StringUtil.notNull(startTime)%>"/>
							<input type="hidden" name="endTime" value="<%=StringUtil.notNull(endTime)%>"/>
					第<font color="red"><%=pager.getPageNo()%>
					</font>页&nbsp; 共<font color="red"><%=pager.getPageCount()%></font>页&nbsp; 共<font color="red"><%=pager.getRowCount()%></font>条记录&nbsp; &nbsp; &nbsp; 
					<%if(pager.getPageNo()==1) {%> 首页  | 上一页  |<%}else{%>
					<a
						href="admin/BranchServlet.do?method=admin_list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=1
						&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&code=<%=StringUtil.notNull(code)%>
						
						">首页</a> | <a
						href="admin/BranchServlet.do?method=admin_list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()-1%>
						&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&code=<%=StringUtil.notNull(code)%>
						
						">上一页</a>  | <%} %>
                 <%if(pager.getPageNo()!=pager.getPageCount()) {%>
					<a
						href="admin/BranchServlet.do?method=admin_list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()+1%>
						&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&code=<%=StringUtil.notNull(code)%>
						
						">
                    下一页</a> | <a
						href="admin/BranchServlet.do?method=admin_list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageCount()%>
						&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&code=<%=StringUtil.notNull(code)%>
						">尾页</a>
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
