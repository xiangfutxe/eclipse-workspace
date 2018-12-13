<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.ProductTypeToAttributeValue" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.utils.Pager" %>
<%@page import="com.pojo.Admin" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
  String  attributeId = (String)request.getSession().getAttribute("attributeId");
if(admin==null) {
out.println("<script>");
out.println("parent.window.location.href='login.jsp';");
out.println("</script>");
}else{
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[3][2].equals("1")||admin.getState().equals("1")){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>属性值列表</title>
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
         $('#delform').attr("action", "admin/ProductTypeServlet?method=delAll");
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
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a>
            <span class="crumb-step">&gt;</span><span class="crumb-name">分类管理</span>
            <span class="crumb-step">&gt;</span><span class="crumb-name">属性管理</span>
            <span class="crumb-step">&gt;</span><span class="crumb-name">属性值</span></div>
        </div>
      
       
        <div class="result-wrap">
           <form name="delform" id="delform"  method="post">
                <div class="result-title">
                    <div class="result-list">
                        <a href="admin/ProductTypeServlet?method=type_to_attribute_value_add"><i class="icon-font">&#xe026;</i>新增属性值</a>
                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="97%">
                        <tr>
                         <th>序号</th>
                            <th>属性值</th>
                            <th>录入时间</th>
                            <th>最后修改时间</th>
                            <th>操作</th>
                        </tr>
                        <%
                        
                      
								 Pager pager = (Pager)request.getAttribute("pager");
						if(pager!=null){
							Collection coll = pager.getResultList();
							if(coll!=null){
									Iterator it = coll.iterator();
									int t= 0;
									while(it.hasNext()){
									ProductTypeToAttributeValue pt = (ProductTypeToAttributeValue)it.next();
									t++;
							%>
					  	<tr>
						 	  <td> <%=t%></td>
						 <td> <%=StringUtil.notNull(pt.getValue())%></td>
						  <td> <%=StringUtil.parseToString(pt.getEntryTime(), DateUtil.yyyyMMddHHmmss)%></td>
						 <td> <%=StringUtil.parseToString(pt.getEndTime(), DateUtil.yyyyMMddHHmmss)%></td>
						  <td>   <a
										class="link-del"
										href="admin/ProductTypeServlet?method=attribute_value_del&id=<%=pt.getId()%>" onclick="if(confirm('确定删除该产品属性值信息?')==false)return false;">删除</a>
										
                            </td>
						 </tr>
			<%}}
						}else{ %>
						 <tr>
						 <td colspan="5">暂无属性值信息。</td>
						  </tr>
						 <%} %>
                    </table>
                   
                   
                     </div>
                     </form>
                     <%if(pager!=null){ %>
				 <div class="list-page"> 
                    <div class="list-page-left">
                   <form name="pageSizeForm"  action="admin/ProductTypeServlet?method=attribute_value_list" method="post">
						<input type="hidden" name="attributeId" value="<%=StringUtil.notNull(attributeId)%>"/>
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
			<form name="pageNoForm"  action="admin/ProductTypeServlet?method=attribute_value_list" method="post">
					<input type="hidden" name="attributeId" value="<%=StringUtil.notNull(attributeId)%>"/>
						<input type="hidden" name="pageSizeStr" value="<%=pager.getPageSize()%>"/>
					第<font color="red"><%=pager.getPageNo()%>
					</font>页&nbsp; 共<font color="red"><%=pager.getPageCount()%></font>页&nbsp; 共<font color="red"><%=pager.getRowCount()%></font>条记录&nbsp; &nbsp; &nbsp; 
					<%if(pager.getPageNo()==1) {%> 首页  | 上一页  |<%}else{%>
					<a
						href="admin/ProductTypeServlet?method=attribute_value_list&attributeId=<%=StringUtil.notNull(attributeId)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=1">首页</a> | <a
						href="admin/ProductTypeServlet?method=attribute_value_list&attributeId=<%=StringUtil.notNull(attributeId)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()-1%>">上一页</a>  | <%} %>
                 <%if(pager.getPageNo()!=pager.getPageCount()) {%>
					<a
						href="admin/ProductTypeServlet?method=attribute_value_list&attributeId=<%=StringUtil.notNull(attributeId)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()+1%>">
                    下一页</a> | <a
						href="admin/ProductTypeServlet?method=attribute_value_list&id=<%=StringUtil.notNull(attributeId)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageCount()%>">尾页</a>
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
