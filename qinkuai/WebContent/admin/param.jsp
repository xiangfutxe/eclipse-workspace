<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.utils.Pager" %>
<%@page import="com.pojo.Admin" %>
<%@page import="com.pojo.Param" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
String nameKey = StringUtil.notNull((String) request.getAttribute("nameKey"));
if(admin==null) {
out.println("<script>");
out.println("parent.window.location.href='login.jsp';");
out.println("</script>");
}else{
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[9][2].equals("1")||admin.getState().equals("1")){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>基础设置</title>
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
	
		if (!confirm("确认要删除？")) {
            window.event.returnValue = false;
        }else{
         $('#delform').attr("action", "admin/basic_data_deleteAll.action");
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
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">基础设置</span></div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
                <form action="admin/ParamServlet.do?method=list" method="post">
                    <table class="search-tab">
                        <tr>
                            <th width="120">参数名称:</th>
                            <td><input class="common-text"  name="nameKey" id="nameKey" type="text" value='<%=nameKey%>'></td>
                            <td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div class="result-wrap">
           <form name="delform" id="delform"  method="post">
                <div class="result-title">
                    <div class="result-list">
                        <a href="admin/ParamServlet.do?method=add"><i class="icon-font">&#xe026;</i>新增参数</a>
                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th>参数名称</th>
                            <th>参数1</th>
							 <th>参数2</th>
                            <th>参数3</th>
                             <th>参数4</th>
							 <th>参数5</th>
							 <th>参数6</th>
							 <th>参数7</th>
							 <th>参数8</th>
							 <th>参数9</th>
							 <th>参数10</th>
                            <th>单位</th>
                            <th>操作</th>
                        </tr>
                     	<%
								 Pager pager = (Pager)request.getAttribute("pager");
						if(pager!=null){
							Collection coll = pager.getResultList();
							if(coll!=null){
									Iterator it = coll.iterator();
									while(it.hasNext()){
									Param ad = (Param)it.next();
							%>
					  	<tr>
					  	   <td> <%=StringUtil.notNull(ad.getParamName()) %></td>
						 <td> <%=StringUtil.decimalFormat(ad.getAmount_1()) %></td>
						 <td> <%=StringUtil.decimalFormat(ad.getAmount_2()) %></td>
						 <td> <%=StringUtil.decimalFormat(ad.getAmount_3()) %></td>
						 <td> <%=StringUtil.decimalFormat(ad.getAmount_4()) %></td>
						 <td> <%=StringUtil.decimalFormat(ad.getAmount_5()) %></td>
						 <td> <%=StringUtil.decimalFormat(ad.getAmount_6()) %></td>
						 <td> <%=StringUtil.decimalFormat(ad.getAmount_7()) %></td>
						 <td> <%=StringUtil.decimalFormat(ad.getAmount_8()) %></td>
						 <td> <%=StringUtil.decimalFormat(ad.getAmount_9()) %></td>
						 <td> <%=StringUtil.decimalFormat(ad.getAmount_10()) %></td>
						  <td> <%=StringUtil.notNull(ad.getUnit()) %></td>
						  <td>
                                <a class="link-update" href="admin/ParamServlet.do?method=edit&id=<%=ad.getId()%>">修改</a>&nbsp;|&nbsp;
                                <a class="link-update" href="admin/ParamServlet.do?method=del&id=<%=ad.getId()%>" onClick="delcfm()">删除</a>
                            </td>
						 </tr>
						<%}}
						}else{ %>
						 <tr>
						 <td colspan="13">添加参数，请点击<a href="admin/ParamServlet.do?method=add">添加参数。</a></td>
						  </tr>
						 <%} %>
						</table>


					</div>
				</form>
			<%if(pager!=null){ %>
				 <div class="list-page"> 
                    <div class="list-page-left">
                   <form name="pageSizeForm"  action="admin/ParamServlet.do?method=list" method="post">
						<input type="hidden" name="nameKey" value="<%=StringUtil.notNull(nameKey)%>"/>
							<input type="hidden" name="pageNoStr" value="1"/>
					当前每页显示<select name="pageSizeStr" id="pageSizeStr" onchange="javascript:pageSizeForm.submit();">
						<option value="1"
							<%if(pager.getPageSize()==1) {%>selected<%}%>>1</option>
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
			<form name="pageNoForm"  action="admin/ParamServlet.do?method=list" method="post">
					<input type="hidden" name="nameKey" value="<%=StringUtil.notNull(nameKey)%>"/>
						<input type="hidden" name="pageSizeStr" value="<%=pager.getPageSize()%>"/>
					第<font color="red"><%=pager.getPageNo()%>
					</font>页&nbsp; 共<font color="red"><%=pager.getPageCount()%></font>页&nbsp; 共<font color="red"><%=pager.getRowCount()%></font>条记录&nbsp; &nbsp; &nbsp; 
					<%if(pager.getPageNo()==1) {%> 首页  | 上一页  |<%}else{%>
					<a
						href="admin/ParamServlet.do?method=list&nameKey=<%=StringUtil.notNull(nameKey)%>&pageSize=<%=pager.getPageSize()%>&pageNoStr=1">首页</a> | <a
						href="admin/ParamServlet.do?method=list&nameKey=<%=StringUtil.notNull(nameKey)%>&pageSize=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()-1%>">上一页</a>  | <%} %>
                 <%if(pager.getPageNo()!=pager.getPageCount()) {%>
					<a
						href="admin/ParamServlet.do?method=list&nameKey=<%=StringUtil.notNull(nameKey)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()+1%>">
                    下一页</a> | <a
						href="admin/ParamServlet.do?method=list&nameKey=<%=StringUtil.notNull(nameKey)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageCount()%>">尾页</a>
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
