<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.Product" %>
<%@page import="com.pojo.ProductType" %>
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
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[2][0].equals("1")||admin.getState().equals("1")){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>产品列表</title>
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
            return  false;
        }else{
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
	
	$("#exportExcel").click(function(){
	  	  if (!confirm("确认提交导出Excel？")) {
				return false;
			}
			else{
	       		$("#searchForm").attr("action", "admin/ProductServlet.do?method=exportExcel");
				$("#searchForm").submit();
	       		 return true;
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
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">产品管理</span></div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
                <form action="admin/ProductServlet.do?method=admin_list"  id="searchForm" method="post">
                    <table class="search-tab">
                        <tr>
                            <th width="80">选择分类:</th>
                              <% 
									String typeName = (String)request.getAttribute("typeName");
									String productName = (String)request.getAttribute("productName");
								 %>
                            <td>
                                <select class="common-text" id="typeName" name="typeName">
                                    <option value="">全部</option>
                                     <%
								 
							Collection coll_pt =  (Collection)request.getAttribute("coll_pt");
							if(coll_pt!=null){
									Iterator it = coll_pt.iterator();
									while(it.hasNext()){
									ProductType pt = (ProductType)it.next();
							%>
                                    <option value='<%=pt.getTypeName()%>' <%if(pt.getTypeName().equals(typeName)){%>selected<%}%>><%=pt.getTypeName()%></option>
                                  <%} }%>
                                </select>
                            </td>
                             
                            <th width="70">产品编号或名称:</th>
                            <td><input class="common-text"  name="productName" id="productName" type="text" value='<%=StringUtil.notNull(productName)%>'></td>
                            <td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit">
                            &nbsp;<input class="btn btn-info" id="exportExcel"
									name="exportExcel" value="导出Excel" type="button"></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div class="result-wrap">
           <form name="delform" id="delform"  method="post" action="admin/ProductServlet.do?method=delAll">
              
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                             <th>图片</th>
                            <th>ID</th>
                            <th>品名</th>
                            <th>规格</th>
                            <th>类别</th>
                            <th>单位</th>
                            <th>所在仓库</th>
                             <th>销售对象</th>
                            <th>单价(元)</th>
                             <th>当前库存</th>
                             <th>入库总数</th>
                              <th>状态</th>
                            <th>操作</th>
                        </tr>
                        <%
 Pager pager = (Pager)request.getAttribute("pager");
						if(pager!=null){
							Collection coll = pager.getResultList();
							if(coll!=null){
									Iterator it1 = coll.iterator();
									while(it1.hasNext()){
									Product p = (Product)it1.next();
							%>
					  	<tr>
						 						 <td ><img width="80px" height="80px" src='upload/<%=StringUtil.notNull(p.getImageUrl())%>'/>
						 
						 <td> <%=StringUtil.notNull(p.getProductId())%> </td>
						  <td> <%=StringUtil.notNull(p.getProductName())%> </td>
						   <td> <%=StringUtil.notNull(p.getSpecification())%></td>
					 <td> <%=StringUtil.notNull(p.getProductType())%></td>
					  <td> <%=StringUtil.notNull(p.getUnit())%></td>
					  <td><%=StringUtil.notNull(p.getInventoryTwo())%><%=StringUtil.notNull(p.getInventoryThree())%></td>
					  <td>
					  <%
					  		String[] str = StringUtil.notNull(p.getBranchType()).split(",");
					  		for(int i=0;i<str.length;i++){
					   %>
						  <%if(str[i].equals("1")){%>加盟店<br> 
						  <%}else if(str[i].equals("2")){ %>直营店<br>
						  <%}else if(str[i].equals("3")){ %>外购商<br><%}} %></td>
						
						   
						   <td>
						  <%=StringUtil.decimalFormat(p.getPrice())%></td>
						  <td > <%=StringUtil.decimalFormat(p.getNum())%>
						   <td > <%=StringUtil.decimalFormat(p.getTotalNum())%>
						 </td>
						  <td>
						  <%if(p.getState()==1){%>热销中<%}else if(p.getState()==2){ %>下架<%}else{ %>-<%} %></td>
						  <td>

                                 <a class="link-update" href="admin/ProductServlet.do?method=detail&id=<%=p.getId()%>">详情</a>&nbsp;
                                 <%if(rankstr[2][2].equals("1")||admin.getState().equals("1")){ %> 
                                 |&nbsp;<a class="link-update" href="admin/ProductServlet.do?method=edit&id=<%=p.getId()%>">修改</a>&nbsp;
                                <%if(p.getState()==1){ %> |&nbsp;<a class="link-update" href="admin/ProductServlet.do?method=admin_down&id=<%=p.getId()%>" onclick="if(confirm('确定对该产品进行下架?')==false)return false;">下架</a>&nbsp;
                                  <%}else if(p.getState()==2){ %> |&nbsp;<a class="link-update" href="admin/ProductServlet.do?method=admin_up&id=<%=p.getId()%>" onclick="if(confirm('确定对该产品进行上架?')==false)return false;">上架</a>&nbsp;
                                  <%} %>
                                
                                <%}if(rankstr[2][1].equals("1")||admin.getState().equals("1")){ %>  
                                |&nbsp; <a class="link-update" href="admin/ProductServlet.do?method=img_edit&id=<%=p.getId()%>">上传图片</a>&nbsp;
                             <%}if(rankstr[2][4].equals("1")||admin.getState().equals("1")){ %>|&nbsp;  <a
										class="link-del"
										href="admin/ProductServlet.do?method=del&id=<%=p.getId()%>" onclick="if(confirm('确定删除该产品产品信息?')==false)return false;">删除</a>
                           <%} %>
                            </td>
						 </tr>
							<%}}
						}else{ %>
						 <tr>
						 <td colspan="12">暂无产品信息，请点击<a href="admin/ProductServlet.do?method=add">添加产品。</a></td>
						  </tr>
						 <%} %>
                    </table>
                   
                   
                     </div>
                     </form>
                     <%if(pager!=null){ %>
				 <div class="list-page"> 
                    <div class="list-page-left">
                   <form name="pageSizeForm"  action="admin/ProductServlet.do?method=admin_list" method="post">
						<input type="hidden" name="productName" value="<%=StringUtil.notNull(productName)%>"/>
<input type="hidden" name="typeName" value="<%=StringUtil.notNull(typeName)%>"/>
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
			<form name="pageNoForm"  action="admin/ProductServlet.do?method=admin_list" method="post">
					<input type="hidden" name="productName" value="<%=StringUtil.notNull(productName)%>"/>
					<input type="hidden" name="typeName" value="<%=StringUtil.notNull(typeName)%>"/>
						<input type="hidden" name="pageSizeStr" value="<%=pager.getPageSize()%>"/>
					第<font color="red"><%=pager.getPageNo()%>
					</font>页&nbsp; 共<font color="red"><%=pager.getPageCount()%></font>页&nbsp; 共<font color="red"><%=pager.getRowCount()%></font>条记录&nbsp;
					<%if(pager.getPageNo()==1) {%> 首页|上一页|<%}else{%>
					<a
						href="admin/ProductServlet.do?method=admin_list&productName=<%=StringUtil.notNull(productName)%>&typeName=<%=StringUtil.notNull(typeName)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=1">首页</a>|<a
						href="admin/ProductServlet.do?method=admin_list&productName=<%=StringUtil.notNull(productName)%>&typeName=<%=StringUtil.notNull(typeName)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()-1%>">上一页</a>|<%} %>
                 <%if(pager.getPageNo()!=pager.getPageCount()) {%>
					<a
						href="admin/ProductServlet.do?method=admin_list&productName=<%=StringUtil.notNull(productName)%>&typeName=<%=StringUtil.notNull(typeName)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()+1%>">
                    下一页</a>|<a
						href="admin/ProductServlet.do?method=admin_list&productName=<%=StringUtil.notNull(productName)%>&typeName=<%=StringUtil.notNull(typeName)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageCount()%>">尾页</a>
				<%}else{ %>下一页|尾页<%} %>&nbsp;跳转到第
				<select
						name="pageNoStr" onchange="javascript:pageNoForm.submit();">
						<%for(int i=0;i<pager.getPageCount();i++){ %>
							<option value="<%=i+1 %>" <%if(pager.getPageNo()==(i+1)){%>selected<%} %>>
								第<%=i+1 %>页
							</option>
							<%} %>
					</select>&nbsp;
			</form>
				</div>
                </div>
                <%}%>
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
