<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.Inventory" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.utils.Pager" %>
<%@page import="com.pojo.Admin" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
if(admin==null) {
out.println("<script>");
out.println("parent.window.location.href='login.jsp';");
out.println("</script>");
}
else{
		 String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[3][1].equals("1")||admin.getState().equals("1")){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>仓库管理</title>
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
    <script language="JavaScript">
     function delcfm() {
        if (!confirm("确认要删除？")) {
            window.event.returnValue = false;
        }
    }
    
    
	$(function() {
	
	$("#batchDel").click(function(){
	
		if (!confirm("确认要批量删除？")) {
            window.event.returnValue = false;
        }else{
         $('#delform').attr("action", "admin/inventory_deleteAll.action");
       		 $('#delform').submit();
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
  
   $("#pageSize").bind("propertychange",function() {
    });
});
	</script>
  </head>
  
  <body>
  <div class="container clearfix">
   <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">仓库管理</span><span class="crumb-step">&gt;</span><span class="crumb-name">区域仓库</span></div>
        </div>
        <div class="search-wrap">
        <div class="search-content">
					<form action="admin/InventoryServlet.do?method=list" id="searchForm"
						method="post" enctype="multipart/form-data">
						<table class="search-tab">
							<tr>
								<th width="70">仓库名称：</th>
								<% 
									String inventoryName = (String)request.getAttribute("inventoryName");
								 %>
								<td><input class="common-text" name="inventoryName" id="inventoryName"
									type="text" value='<%=StringUtil.notNull(inventoryName)%>'>
								
								<td><input class="btn btn-primary btn2" id="search"
									name="search" value="查询" type="button">
								</td>
							</tr>
						</table>
					</form>
				</div>
           </div>
        <div class="result-wrap">
           <form name="delform" id="delform"  method="post" enctype="multipart/form-data">
                <div class="result-title">
                    <div class="result-list">
                        <a href="admin/InventoryServlet.do?method=add"><i class="icon-font">&#xe026;</i>添加仓库</a>
                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                      
                            <th>仓库名称</th>
							 <th>仓库负责人</th>
							  <th>仓库所在地</th>
							   <th>联系电话</th>
                            <th>创建时间</th>
                            <th>操作</th>
                        </tr>
                         <%
								 Pager pager = (Pager)request.getAttribute("pager");
						if(pager!=null){
							Collection coll = pager.getResultList();
							if(coll!=null){
									Iterator it = coll.iterator();
									while(it.hasNext()){
									Inventory iny = (Inventory)it.next();
							%>
					  	<tr>
					  	 
						 <td> <%=StringUtil.notNull(iny.getInventoryName())%></td>
						  <td> <%=StringUtil.notNull(iny.getLinkman())%></td>
						   <td> <%=StringUtil.notNull(iny.getAddress())%></td>
						    <td> <%=StringUtil.notNull(iny.getPhone())%></td>
						  <td> <%=StringUtil.parseToString(iny.getEntryTime(), DateUtil.yyyyMMddHHmmss)%></td>
						
						  <td>
                        <%if(rankstr[4][9].equals("1")||admin.getState().equals("1")){  %>  
                        <a class="link-del"
										href="admin/InventoryServlet.do?method=del&id=<%=iny.getId()%>" onclick="if(confirm('确定删除该仓库信息?')==false)return false;">删除</a><%} %>
                            </td>
						 </tr>
					<%}}
						}else{ %>
						 <tr>
						 <td colspan="6">暂无仓库信息，请点击<a href="admin/InventoryServlet.do?method=add">添加仓库。</a></td>
						  </tr>
						 <%} %>
                    </table>
                   
                   
                     </div>
                     </form>
                     <%if(pager!=null){ %>
				 <div class="list-page"> 
                    <div class="list-page-left">
                   <form name="pageSizeForm"  action="admin/InventoryServlet.do?method=list" method="post">
						<input type="hidden" name="inventoryName" value="<%=StringUtil.notNull(inventoryName)%>"/>
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
			<form name="pageNoForm"  action="admin/InventoryServlet.do?method=list" method="post">
					<input type="hidden" name="inventoryName" value="<%=StringUtil.notNull(inventoryName)%>"/>
						<input type="hidden" name="pageSizeStr" value="<%=pager.getPageSize()%>"/>
					第<font color="red"><%=pager.getPageNo()%>
					</font>页&nbsp; 共<font color="red"><%=pager.getPageCount()%></font>页&nbsp; 共<font color="red"><%=pager.getRowCount()%></font>条记录&nbsp; &nbsp; &nbsp; 
					<%if(pager.getPageNo()==1) {%> 首页  | 上一页  |<%}else{%>
					<a
						href="admin/InventoryServlet.do?method=list&inventoryName=<%=StringUtil.notNull(inventoryName)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=1">首页</a> | <a
						href="admin/InventoryServlet.do?method=list&inventoryName=<%=StringUtil.notNull(inventoryName)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()-1%>">上一页</a>  | <%} %>
                 <%if(pager.getPageNo()!=pager.getPageCount()) {%>
					<a
						href="admin/InventoryServlet.do?method=list&inventoryName=<%=StringUtil.notNull(inventoryName)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()+1%>">
                    下一页</a> | <a
						href="admin/InventoryServlet.do?method=list&inventoryName=<%=StringUtil.notNull(inventoryName)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageCount()%>">尾页</a>
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