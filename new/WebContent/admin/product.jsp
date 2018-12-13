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
		if(rankstr[4][0].equals("1")||admin.getState().equals("1")){
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
                <form action="admin/ProductServlet.do?method=list" method="post">
                    <table class="search-tab">
                        <tr>
                         
                              <% 
									String productName = (String)request.getAttribute("productName");
								 %>
                           
                             <th width="70">选择类型:</th> <td> <select class="common-text" id="type" name="type">
                                    <option value="">全部</option>
                                     <option value='1' <%if(StringUtil.notNull(type).equals("1")){%>selected<%}%>>单品</option>
                                      <option value='2' <%if(StringUtil.notNull(type).equals("2")){%>selected<%}%>>套装</option>
                                    </select>
                                    </td>
                            <th width="70">产品名称:</th>
                            <td><input class="common-text"  name="productName" id="productName" type="text" value='<%=StringUtil.notNull(productName)%>'></td>
                            <td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div class="result-wrap">
           <form name="delform" id="delform"  method="post" action="admin/ProductServlet.do?method=delAll">
                <div class="result-title">
                    <div class="result-list">
                    <%if(rankstr[4][1].equals("1")||admin.getState().equals("1")){ %>
                        <a href="admin/ProductServlet.do?method=add"><i class="icon-font">&#xe026;</i>新增产品</a>
                        <%}if(rankstr[4][4].equals("1")||admin.getState().equals("1")){ %>
                        <a id="batchDel"><i class="icon-font">&#xe019;</i>批量删除</a>
                        <%} %>
                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th class="tc" width="20px"><input class="allChoose" id="ckAll" name="ckAll" type="checkbox"></th>
                              <th>图片</th>
                            <th>ID</th>
                            <th>名称</th>
                            <th>规格</th>
                            <th>类型</th>
                            <th>零售价</th>
                            <th>代理价</th>
                             <th>推荐奖励</th>
                             
                             <th>物流费</th>
                             <th>当前库存</th>
                             <th>入库总数</th>
                         
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
					  	  <td class="tc"><input name="ids" type="checkbox" value="<%=p.getId()%>"></td>
							 <td  width="90px"><img width="80px" height="80px" src='upload/<%=StringUtil.notNull(p.getImageUrl())%>'/>
						 <td> <%=StringUtil.notNull(p.getProductId())%> </td>
						  <td> <%=StringUtil.notNull(p.getProductName())%> </td>
						   <td> <%=StringUtil.notNull(p.getSpecification())%></td>
					  <td> <%if(p.getType()==1) {%>单品<%}else if(p.getType()==2){ %>套装<%} %></td>
						   <td>
						  <%=p.getPrice()%></td>
						  
						 <td > <%=p.getPrice1()%>
						  <td > <%=p.getAward()%></td>
						    <td > <%=p.getFee()%></td>
						  <td > <%=p.getNum()%>
						   <td > <%=p.getTotalNum()%>
					
						 </td>
						  <td>

                                 <a class="link-update" href="admin/ProductServlet.do?method=detail&id=<%=p.getId()%>">详情</a>&nbsp;
                                 <%if(rankstr[4][2].equals("1")||admin.getState().equals("1")){ %> 
                                 |&nbsp;<a class="link-update" href="admin/ProductServlet.do?method=edit&id=<%=p.getId()%>">修改</a>&nbsp;
                                <%}if(rankstr[4][1].equals("1")||admin.getState().equals("1")){ %>  
                                |&nbsp; <a class="link-update" href="admin/ProductServlet.do?method=img_edit&id=<%=p.getId()%>">上传图片</a>&nbsp;
                             <%}if(rankstr[4][4].equals("1")||admin.getState().equals("1")){ %>|&nbsp;  <a
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
                   <form name="pageSizeForm"  action="admin/ProductServlet.do?method=list" method="post">
						<input type="hidden" name="productName" value="<%=StringUtil.notNull(productName)%>"/>
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
			<form name="pageNoForm"  action="admin/ProductServlet.do?method=list" method="post">
					<input type="hidden" name="productName" value="<%=StringUtil.notNull(productName)%>"/>
						<input type="hidden" name="pageSizeStr" value="<%=pager.getPageSize()%>"/>
					第<font color="red"><%=pager.getPageNo()%>
					</font>页&nbsp; 共<font color="red"><%=pager.getPageCount()%></font>页&nbsp; 共<font color="red"><%=pager.getRowCount()%></font>条记录&nbsp; &nbsp; &nbsp; 
					<%if(pager.getPageNo()==1) {%> 首页  | 上一页  |<%}else{%>
					<a
						href="admin/ProductServlet.do?method=list&productName=<%=StringUtil.notNull(productName)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=1">首页</a> | <a
						href="admin/ProductServlet.do?method=list&productName=<%=StringUtil.notNull(productName)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()-1%>">上一页</a>  | <%} %>
                 <%if(pager.getPageNo()!=pager.getPageCount()) {%>
					<a
						href="admin/ProductServlet.do?method=list&productName=<%=StringUtil.notNull(productName)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()+1%>">
                    下一页</a> | <a
						href="admin/ProductServlet.do?method=list&productName=<%=StringUtil.notNull(productName)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageCount()%>">尾页</a>
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
