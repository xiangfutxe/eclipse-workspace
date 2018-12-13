<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.Product" %>
<%@page import="com.ssm.pojo.ProductType" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>
<%@page import="com.ssm.utils.Pager" %>
<%@page import="com.ssm.pojo.Admin" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
String type = (String)request.getAttribute("type");
String isHide = (String)request.getAttribute("isHide");

if(admin==null) {
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
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a>
            <span class="crumb-step">&gt;</span><span class="crumb-name">产品管理</span>
            <span class="crumb-step">&gt;</span><span class="crumb-name">产品列表</span></div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
                <form action="admin/ProductServlet?method=list" method="post">
                    <table class="search-tab">
                        <tr>
                            <th width="80">分类:</th>
                              <% 
									String typeName = (String)request.getAttribute("typeName");
									String productName = (String)request.getAttribute("productName");
								 
								 %>
                            <td>
                                <select class="common-text" id="typeName" name="typeName">
                                    <option value="">全部</option>
                                     <%
								 
							List<ProductType> coll_pt =  (List<ProductType>)request.getAttribute("coll_pt");
							if(coll_pt!=null){
							for(int i=0;i<coll_pt.size();i++){
									ProductType pt = coll_pt.get(i);
							%>
                                    <option value='<%=pt.getTypeName()%>' <%if(pt.getTypeName().equals(typeName)){%>selected<%}%>><%=pt.getTypeName()%></option>
                                  <%} }%>
                                </select>
                            </td>
                             <th>&nbsp;类型:</th> <td> <select class="common-text" id="type" name="type">
                                    <option value="">全部</option>
                                     <option value='1' <%if(StringUtil.notNull(type).equals("1")){%>selected<%}%>>单品</option>
                                      <option value='2' <%if(StringUtil.notNull(type).equals("2")){%>selected<%}%>>套餐</option>
                                    </select>
                                    </td>
                                    <th>&nbsp;状态:</th> <td> <select class="common-text" id="isHide" name="isHide">
                                    <option value="">全部</option>
                                     <option value='1' <%if(StringUtil.notNull(isHide).equals("1")){%>selected<%}%>>显示</option>
                                      <option value='0' <%if(StringUtil.notNull(isHide).equals("0")){%>selected<%}%>>隐藏</option>
                                    </select>
                                    </td>
                            <th>产品名称:</th>
                            <td><input class="common-text"  name="productName" id="productName" type="text" value='<%=StringUtil.notNull(productName)%>'></td>
                            <td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div class="result-wrap">
           <form name="delform" id="delform"  method="post" action="admin/ProductServlet?method=delAll">
                <div class="result-title">
                    <div class="result-list">
                        <a href="admin/ProductServlet?method=addProduct"><i class="icon-font">&#xe026;</i>新增产品</a>
                        <a id="batchDel"><i class="icon-font">&#xe019;</i>批量删除</a>
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
                            <th>分类</th>
                            <th>类型</th>
                            <th>单价</th>
                            <th>PV值</th>
                            <th>创业价</th>
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
							System.out.println(coll.size());
									Iterator it1 = coll.iterator();
									
									while(it1.hasNext()){
									Product p = (Product)it1.next();
									System.out.println(p.getProductId());
							%>
					  	<tr>
					  	  <td class="tc"><input name="ids" type="checkbox" value="<%=p.getId()%>"></td>
							 <td ><img width="80px" height="80px" src='upload/<%=StringUtil.notNull(p.getImageUrl())%>'/>
						 </td>
						 <td> <%=StringUtil.notNull(p.getProductId())%> </td>
						  <td> <%=StringUtil.notNull(p.getProductName())%> </td>
						   <td> <%=StringUtil.notNull(p.getSpecification())%></td>
					 <td> <%=StringUtil.notNull(p.getProductType())%></td>
					  <td> <%if(p.getType()==1) {%>单品<%}else if(p.getType()==2){ %>套餐<%} %></td>
						   <td>
						  <%=p.getPrice()%></td>
						  
						 <td > <%=p.getPv()%>
						  <td>
						  <%=p.getPriceCy()%></td>
						  
						  <td > <%=p.getNum()%>
						   <td > <%=p.getTotalNum()%>
						    <td> <%if(p.getIsHide()==1) {%>显示<%}else if(p.getIsHide()==0){ %>隐藏<%} %></td>
					
						  <td>

                                 <a class="link-update" href="admin/ProductServlet?method=admin_detail&id=<%=p.getId()%>">详情</a>
                                 |<a class="link-update" href="admin/ProductServlet?method=editProduct&id=<%=p.getId()%>">修改</a>
                                 |<%if(p.getIsHide()==1) {%><a class="link-update" href="admin/ProductServlet?method=is_hide&id=<%=p.getId()%>" onclick="javascript:if(confirm('确定隐藏该产品?')==false)return false;">隐藏</a>
                                 <%}else if(p.getIsHide()==0){ %><a class="link-update" href="admin/ProductServlet?method=is_hide_no&id=<%=p.getId()%>" onclick="javascript:if(confirm('确定显示该产品?')==false)return false;">显示</a><%} %>
                            | <a class="link-update" href="admin/ProductServlet?method=img_edit&id=<%=p.getId()%>">上传图片</a>
                            | <a class="link-update" href="admin/ProductServlet?method=give_edit&id=<%=p.getId()%>">绑定赠品</a>
                            |  <a class="link-del"
										href="admin/ProductServlet?method=del&id=<%=p.getId()%>" onclick="javascript:if(confirm('确定删除该产品产品信息?')==false)return false;">删除</a>
                            </td>
						 </tr>
							<%}}
						}else{ %>
						 <tr>
						 <td colspan="9">暂无产品信息，请点击<a href="admin/ProductServlet?method=add">添加产品。</a></td>
						  </tr>
						 <%} %>
                    </table>
                   
                   
                     </div>
                     </form>
                     <%if(pager!=null){ %>
				 <div class="list-page"> 
                    <div class="list-page-left">
                   <form name="pageSizeForm"  action="admin/ProductServlet?method=list" method="post">
						<input type="hidden" name="productName" value="<%=StringUtil.notNull(productName)%>"/>
<input type="hidden" name="typeName" value="<%=StringUtil.notNull(typeName)%>"/>
<input type="hidden" name="type" value="<%=StringUtil.notNull(type)%>"/>
<input type="hidden" name="isHide" value="<%=StringUtil.notNull(isHide)%>"/>

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
			<form name="pageNoForm"  action="admin/ProductServlet?method=list" method="post">
					<input type="hidden" name="productName" value="<%=StringUtil.notNull(productName)%>"/>
					<input type="hidden" name="typeName" value="<%=StringUtil.notNull(typeName)%>"/>
					<input type="hidden" name="type" value="<%=StringUtil.notNull(type)%>"/>
						<input type="hidden" name="isHide" value="<%=StringUtil.notNull(isHide)%>"/>
						
						<input type="hidden" name="pageSizeStr" value="<%=pager.getPageSize()%>"/>
					第<font color="red"><%=pager.getPageNo()%>
					</font>页&nbsp; 共<font color="red"><%=pager.getPageCount()%></font>页&nbsp; 共<font color="red"><%=pager.getRowCount()%></font>条记录&nbsp; &nbsp; &nbsp; 
					<%if(pager.getPageNo()==1) {%> 首页  | 上一页  |<%}else{%>
					<a
						href="admin/ProductServlet?method=list&isHide=<%=StringUtil.notNull(isHide)%>&productName=<%=StringUtil.notNull(productName)%>&typeName=<%=StringUtil.notNull(typeName)%>&type=<%=StringUtil.notNull(type)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=1">首页</a> | <a
						href="admin/ProductServlet?method=list&isHide=<%=StringUtil.notNull(isHide)%>&productName=<%=StringUtil.notNull(productName)%>&typeName=<%=StringUtil.notNull(typeName)%>&type=<%=StringUtil.notNull(type)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()-1%>">上一页</a>  | <%} %>
                 <%if(pager.getPageNo()!=pager.getPageCount()) {%>
					<a
						href="admin/ProductServlet?method=list&isHide=<%=StringUtil.notNull(isHide)%>&productName=<%=StringUtil.notNull(productName)%>&typeName=<%=StringUtil.notNull(typeName)%>&type=<%=StringUtil.notNull(type)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()+1%>">
                    下一页</a> | <a
						href="admin/ProductServlet?method=list&isHide=<%=StringUtil.notNull(isHide)%>&productName=<%=StringUtil.notNull(productName)%>&typeName=<%=StringUtil.notNull(typeName)%>&type=<%=StringUtil.notNull(type)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageCount()%>">尾页</a>
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
