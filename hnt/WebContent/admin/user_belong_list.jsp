<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.Admin" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>
<%@page import="com.ssm.utils.Pager" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String userId = (String)request.getAttribute("userId");
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
		String[] strarray =admin.getRank().split(",");
	    			String[][] rankstr = new String[10][10];
	    			for(int i=0;i<10;i++){
	    				for(int j=0;j<10;j++){
	    					if(strarray.length>i){
	    						if(strarray[i].length()==10)
	    						rankstr[i][j] =  strarray[i].substring(j, j+1);
	    						else rankstr[i][j]="0";
	    					}
	    					else rankstr[i][j]="0";
	    				}
	    			}
		if(rankstr[2][1].equals("1")||admin.getState().equals("1")){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>安置网络</title>
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
		
		$("#passwordinit").click(function() {

			if (!confirm("确认要重置一级密码？")) {
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
				$('#myform').attr("action", "admin/password1_init.action");
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
						class="crumb-step">&gt;</span><span class="crumb-name">网络管理</span>
						<span
						class="crumb-step">&gt;</span><span class="crumb-name">服务关系</span>
				</div>
			</div>
			<div class="search-wrap">
				<div class="search-content">
					<form action="admin/UserServlet.do?method=user_belong_list" id="searchForm"
						method="post">
						<table class="search-tab">
							<tr>
									<th width="100px">&nbsp;会员编号：</th><td><input class="common-text"  name="userId" id="userId" type="text" size="15" value='<s:property value="#request.userId"/>'>&nbsp;<input class="btn btn-primary btn2" id="search"
									name="search" value="查询" type="submit"><span
									id="timeTag" style="color:red"></span>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
				
			<div class="result-wrap">
			<form  id="myform" method="post">
				<div class="result-content">
					<table class="result-tab" width="100%">
						<tr>
						<th class="tc" width="5%"><input class="allChoose" id="ckAll" name="ckAll" type="checkbox"></th>
							<th>用户编号</th><th>用户名</th><th>联系电话</th><th>安置人</th><th>安置区域</th><th>推荐人</th><th>报单人</th><th>报单币</th><th>购物币</th><th>复消币</th><th>奖金币</th><th>注册时间</th><th>奖金状态</th><th>状态</th>
						</tr>
						<s:if test="#request.ulist.size()!=0">
						<s:iterator value="#request.ulist"> 
 <tr>
  <td class="tc"><input name="ids" type="checkbox" value="<s:property value="id" />"></td>
 <td><a href='admin/user_list_detail.action?id=<s:property value="id" />'><s:property value="userId" /></a></td>
 <td><s:property value="userName" /></td>
 <td><s:property value="tel" /></td>
 <td><s:property value="userByBelongId.userName" />(<s:property value="userByBelongId.userId" />)</td>
<td><s:if test="nodeTag==1">A区</s:if><s:elseif test="nodeTag==2">B区</s:elseif><s:else>暂无指定区域</s:else></td>
<td><s:property value="userByRefereeId.userName" />(<s:property value="userByRefereeId.userId" />)</td>
<td><s:property value="userByDeclarationId.userName" />(<s:property value="userByDeclarationId.userId" />)</td>
<td><s:text name="format.number">
    							<s:param value="emoney"/> </s:text></td>
								<td><s:text name="format.number">
    							<s:param value="smoney"/> </s:text></td>
								<td><s:text name="format.number">
    							<s:param value="dmoney"/> </s:text></td>
								<td><s:text name="format.number">
    							<s:param value="rmoney"/> </s:text></td>
<td><s:date name="entryTime" format="yyyy-MM-dd HH:mm:ss"/></td>
<td><s:if test="payTag==1">正常</s:if><s:else>停发</s:else></td>
 <td><s:if test='state=="1"'>在线</s:if><s:else>下线</s:else></td>
 </tr>
	 </s:iterator>
	 </s:if>
								<s:else><td colspan="15">暂无记录！</td></s:else>
					</table>


				</div>
				</form>
				<s:if test="#request.ulist.size()!=0">
				<div class="list-page">
					<div class="list-page-left">
						<form name="pageSizeForm" action="admin/user_belong_list.action"
							method="post" enctype="multipart/form-data">
							
							<s:hidden name="userId" value="%{#request.userId}"></s:hidden>
							
							<s:hidden name="page" value="%{#request.pager.pageNo}"></s:hidden>
							当前每页显示<select name="pageSize" id="pageSize"
								onchange="javascript:pageSizeForm.submit();">

								<option value="1"
									<s:if test="#request.pager.pageSize==1"> selected</s:if>>1</option>
								<option value="10"
									<s:if test="#request.pager.pageSize==10"> selected</s:if>>10</option>
								<option value="20"
									<s:if test="#request.pager.pageSize==20"> selected</s:if>>20</option>
								<option value="40"
									<s:if test="#request.pager.pageSize==40"> selected</s:if>>40</option>
								<option value="60"
									<s:if test="#request.pager.pageSize==60"> selected</s:if>>60</option>
								<option value="80"
									<s:if test="#request.pager.pageSize==80"> selected</s:if>>80</option>
								<option value="100"
									<s:if test="#request.pager.pageSize==100"> selected</s:if>>100</option>
							</select>条记录
						</form>

					</div>
					<div class="list-page-right">
						<form name="pageNoForm" action="admin/user_belong_list.action"
							method="post" enctype="multipart/form-data">
					
						<s:hidden name="userId" value="%{#request.userId}"></s:hidden>
							<s:hidden name="pageSize" value="%{#request.pager.pageSize}"></s:hidden>
							第<font color="red"><s:property
									value="#request.pager.pageNo" /> </font>页&nbsp; 共<font color="red"><s:property
									value="#request.pager.pageCount" /> </font>页&nbsp; 共<font color="red"><s:property
									value="#request.pager.rowCount" /> </font>条记录&nbsp; &nbsp; &nbsp;
							<s:if test="#request.pager.pageNo == 1">首页  | 上一页  |</s:if>
							<s:else>
								<a
									href="<s:url action="user_belong_list.action">
                   <s:param name="userId"  value="#request.userId"/>  
					   
                      <s:param name="pageSize" value="#request.pager.pageSize"/> 
                 </s:url>">首页</a> | <a
									href="<s:url action="user_belong_list.action">
					<s:param name="userId"  value="#request.userId"/>  
                   
                      <s:param name="pageSize" value="#request.pager.pageSize"/> 
                      <s:param name="page" value="#request.pager.pageNo - 1"/> 
                 </s:url>">上一页</a>  | </s:else>
							<s:if test="#request.pager.pageNo != #request.pager.pageCount">
								<a
									href="<s:url action="user_belong_list.action">
                <s:param name="userId"  value="#request.userId"/>  
				
                      <s:param name="pageSize" value="#request.pager.pageSize"/> 
                      <s:param name="page" value="#request.pager.pageNo + 1"/> 
                 </s:url>">下一页</a> | <a
									href="<s:url action="user_belong_list.action">
                 <s:param name="userId"  value="#request.userId"/> 
				 
                      <s:param name="pageSize" value="#request.pager.pageSize"/> 
                      <s:param name="page" value="#request.pager.pageCount"/> 
                 </s:url>">尾页</a>
							</s:if>
							<s:else> 下一页 | 尾页</s:else>
							&nbsp;&nbsp; &nbsp; 跳转到第<select name="page"
								onchange="javascript:pageNoForm.submit();">
								<s:iterator value="new int[#request.pager.pageCount]" status="i">
									<option value="<s:property value="#i.index+1"/>"
										<s:if test="#request.pager.pageNo==#i.index+1"> selected</s:if>>
										<s:property value="#i.index+1" />
									</option>
								</s:iterator>
							</select>页&nbsp;
						</form>
					</div>
				</div>
</s:if>
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