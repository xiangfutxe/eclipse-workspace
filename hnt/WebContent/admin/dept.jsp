<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>
<%@page import="com.ssm.pojo.Admin" %>
<%@page import="com.ssm.pojo.Dept" %>
<%@page import="com.ssm.utils.Pager" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	String name = (String) request.getAttribute("name");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>管理员列表</title>
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
<link rel="stylesheet" type="text/css" href="css/pager.css" />

<script type="text/javascript" src="js/jquery.js"></script>

<script type="text/javascript">
		$(function(){
	 	   /** 获取上一次选中的部门数据 */
	 	   var boxs  = $("input[type='checkbox'][id^='box_']");
	 	   
	 	  /** 给全选按钮绑定点击事件  */
	    	$("#checkAll").click(function(){
	    		// this是checkAll  this.checked是true
	    		// 所有数据行的选中状态与全选的状态一致
	    		boxs.attr("checked",this.checked);
	    	});
	    	
	 	  /** 给数据行绑定鼠标覆盖以及鼠标移开事件  */
	    	$("tr[id^='data_']").hover(function(){
	    		$(this).css("backgroundColor","#eeccff");
	    	},function(){
	    		$(this).css("backgroundColor","#ffffff");
	    	});
	    	
	    	
	 	   /** 删除员工绑定点击事件 */
	 	   $("#delete").click(function(){
	 		   /** 获取到用户选中的复选框  */
	 		   var checkedBoxs = boxs.filter(":checked");
	 		   if(checkedBoxs.length < 1){
	 			  alert("请选择一个需要删除的部门！");
	 		   }else{
	 			   /** 得到用户选中的所有的需要删除的ids */
	 			   var ids = checkedBoxs.map(function(){
	 				   return this.value;
	 			   });
	 			   
	 				   if(confirm("确认要删除吗?","删除部门")){
	 					   // alert("删除："+ids.get());
	 					   // 发送请求
	 					   window.location.href = "admin/DeptServlet?method=removeDept&ids=" + ids.get();
	 				   }
	 		   }
	 	   });
	    })
	</script>
</head>

<body>
	<div class="container clearfix">
		<div class="main-wrap">

			<div class="crumb-wrap">
				<div class="crumb-list">
					<i class="icon-font"></i><a href="admin/index.jsp">首页</a>
					<span
						class="crumb-step">&gt;</span><span class="crumb-name">员工管理</span>
					<span
						class="crumb-step">&gt;</span><span class="crumb-name">部门列表</span>
				</div>
			</div>
			<div class="search-wrap">
				<div class="search-content">
					<form action="admin/DeptServlet?method=list" method="post">
						<table class="search-tab">
							<tr>
								<th width="100">名称:</th>
								<td><input class="common-text" name="name"
									type="text" value='<%=StringUtil.notNull(name)%>'>
								</td>
								<td><input class="btn btn-primary btn2" name="sub"
									value="查询" type="submit">
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<div class="result-wrap">
				<form name="delform" id="delform" action="admin/DeptServlet?method=delAll"  method="post">
					<div class="result-title">
						<div class="result-list">
							<a href="admin/DeptServlet?method=addDept"><i class="icon-font">&#xe026;</i>新增部门</a>
							<a id="delete"><i class="icon-font">&#xe019;</i>批量删除</a>
						</div>
					</div>
					<div class="result-content">
					
						<table class="result-tab" width="100%">
							 <tr class="main_trbg_tit" align="center">
			  <td><input type="checkbox" name="checkAll" id="checkAll"></td>
			  <td>部门名称</td>
			  <td>详细信息</td>
			  <td align="center">操作</td>
			</tr>
			<%
								 Pager pager = (Pager)request.getAttribute("pager");
						if(pager!=null){
							Collection coll = pager.getResultList();
							System.out.println(coll.size());
							if(coll!=null){
									Iterator it = coll.iterator();
									int t=0;
									while(it.hasNext()){
									t++;
									Dept dept = (Dept)it.next();
							%>
				<tr id="data_<%=t%>" align="center" class="main_trbg" onMouseOver="move(this);" onMouseOut="out(this);">
					<td><input type="checkbox" id="box_<%=t%>" value="<%=dept.getId()%>"></td>
					 <td><%=dept.getName()%></td>
					  <td><%=dept.getRemark()%></td>
					 <td align="center" width="40px;"><a href="admin/DeptServlet?method=editDept&id=<%=dept.getId()%>">修改</a>
					  </td>
				</tr>
				<%}}} %>
						</table>
					</div>
				</form>
			
		
			<%if(pager!=null){ %>
				 <div class="list-page"> 
                    <div class="list-page-left">
                   <form name="pageSizeForm"  action="admin/DeptServlet?method=list" method="post">
						<input type="hidden" name="name" value="<%=StringUtil.notNull(name)%>"/>
							<input type="hidden" name="pageNoStr" value="1"/>
					当前每页显示<select name="pageSizeStr" id="pageSizeStr" onchange="javascript:pageSizeForm.submit();">
						<option value="5"
							<%if(pager.getPageSize()==5) {%>selected<%}%>>5</option>
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
			<form name="pageNoForm"  action="admin/DeptServlet?method=list" method="post">
					<input type="hidden" name="name" value="<%=StringUtil.notNull(name)%>"/>
						<input type="hidden" name="pageSizeStr" value="<%=pager.getPageSize()%>"/>
					第<font color="red"><%=pager.getPageNo()%>
					</font>页&nbsp; 共<font color="red"><%=pager.getPageCount()%></font>页&nbsp; 共<font color="red"><%=pager.getRowCount()%></font>条记录&nbsp; &nbsp; &nbsp; 
					<%if(pager.getPageNo()==1) {%> 首页  | 上一页  |<%}else{%>
					<a
						href="admin/DeptServlet?method=list&name=<%=StringUtil.notNull(name)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=1">首页</a> | <a
						href="admin/DeptServlet?method=list&name=<%=StringUtil.notNull(name)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()-1%>">上一页</a>  | <%} %>
                 <%if(pager.getPageNo()!=pager.getPageCount()) {%>
					<a
						href="admin/DeptServlet?method=list&name=<%=StringUtil.notNull(name)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()+1%>">
                    下一页</a> | <a
						href="admin/DeptServlet?method=list&name=<%=StringUtil.notNull(name)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageCount()%>">尾页</a>
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

