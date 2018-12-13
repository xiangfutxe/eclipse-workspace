<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.Product" %>
<%@page import="com.ssm.pojo.Inventory" %>
<%@page import="com.ssm.pojo.ProductType" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>
<%@page import="com.ssm.pojo.Admin" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
String token = (String)request.getSession().getAttribute("token");
if(admin==null) {
out.println("<script>");
out.println("window.location.href='login.jsp';");
out.println("</script>");
}else{
	  String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[4][2].equals("1")||admin.getState().equals("1")){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>新增出库</title>
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
            return false;
        }
    }
    
	$(function() {
	
	$("#abtn").click(function(){
		if (!confirm("确认提交申请？")) {
            return false;
        }else{
        	var reg = /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/;
        	var reg1 = /^[1-9]+[0-9]*]*$/;
        	var error = "";
        	var n = 0;
        	var p = 0;
        	
        	if($("#inventoryId").val()=="") error=error+"未选择仓库。<br>";
        	if($("#type").val()=="") error=error+"请选择出库类型，";
        	
 			$("input[name=nums]").each(function(i){ 
 				if($(this).val()!="") 
 					if($(this).val()!=0)	
 					if(!reg1.test($(this).val())) n=n+1;
 			});
 			if(n!=0)
 			error=error+"出库数量数必须为整数；";
 			
 			$("input[name=prices]").each(function(i){ 
 				if($(this).val()!="") {
 				 if(!reg.test($(this).val())) p=p+1;
 				}
 			});
 			
 			if(p!=0)
 			error=error+"进货价数值格式不正确，";
 			if(error==""){
         		$('#saveform').attr("action", "admin/InventoryServlet?method=apply_out_save");
       		 	$('#saveform').submit();
       		 	return true;
       		 }else{
       		 	$("#error").text(error+"请重新确认！");
       		 	 return false;
       		 }
        }
	
	});
	
});
	</script>
  </head>
  
  <body>
  <div class="container clearfix">
   <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">出库管理</span><span class="crumb-step">&gt;</span><span class="crumb-name">新增出库</span></div>
        </div>
       
        <div class="result-wrap">
           <form action="admin/InventoryServlet?method=apply_out_save" method="post" id="saveform" name="saveform">
                 <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
             <div class="result-title">
                    <div class="result-list">
                   <table class="search-tab">
                        <tr>
                            <th width="10%">选择仓库:</th>
                            <td>
                                  <%
 Collection coll_1 = (Collection)request.getAttribute("coll_1");
						
							if(coll_1!=null){
							%>
                                 <select class="common-text" id="inventoryId" name="inventoryId">
                                   <%
									Iterator it = coll_1.iterator();
									while(it.hasNext()){
									Inventory iny = (Inventory)it.next();
									%>
                                    <option value='<%=StringUtil.notNull(iny.getInventoryName())%>'><%=StringUtil.notNull(iny.getInventoryName())%></option>
                                  	<%} %>
                                </select>
                               <%}else{ %>未定义仓库，请先在仓库管理中设置好仓库。<input type="hidden" name="inventoryId"/><%} %>&nbsp;<span id="nameTag"></span>
                            </td>
                            <th>出库类型：</th>
                            <td><select name="type" id="type" class="common-text" >
                            <option value="">--请选择出库类型--</option>
                            <option value="1">--加工出库--</option>
                             <option value="2">--损耗出库--</option>
                              <option value="3">--外销出库--</option>
                             <option value="4">--体验出库--</option>
                              <option value="5">--赠送出库--</option>
                               <option value="6">--研发出库--</option>
                               <option value="7">--退货出库--</option>
                               <option value="8">--盘亏出库--</option>
                            </select></td>
                        </tr>
                    </table>
                    </div>
                </div>
        
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th>图片</th>
                            <th>ID</th>
                            <th>名称</th>
                            <th>规格</th>
                            <th>类型</th>
                            <th>价格</th>
                            <th>出库数量</th>
                        </tr>
                        <%
 Collection coll = (Collection)request.getAttribute("coll");
						
							if(coll!=null){
									Iterator it1 = coll.iterator();
									while(it1.hasNext()){
									Product p = (Product)it1.next();
							%>
					  	<tr>
					  	  <td class="tc"><img width="80px" height="80px" src='<%=StringUtil.notNull(p.getImageUrl())%>'/></td>
						 <td> <%=StringUtil.notNull(p.getProductId())%> 
						 						   <input type="hidden" name="pids" value="<%=StringUtil.notNull(p.getProductId())%>"> 
						 </td>
						  <td> <%=StringUtil.notNull(p.getProductName())%> 
						  <input type="hidden" name="names" value="<%=StringUtil.notNull(p.getProductName())%>"> </td>
						   <td> <%=StringUtil.notNull(p.getSpecification())%>
						   						    <input type="hidden" name="specifications" value="<%=StringUtil.notNull(p.getSpecification())%>"> </td>
						  
					 <td> <%=StringUtil.notNull(p.getProductType())%>
					   <input type="hidden" name="productTypes" value="<%=StringUtil.notNull(p.getProductType())%>">
					  <input type="hidden" name="types" value="<%=p.getType()%>" /></td>
					<td>
						  <input type="text" size="10" name="prices" value="0" class="common-text"> </td>
						  <td ><input type="text" size="10" name="nums" class="common-text"> 
						  <input type="hidden"  name="ids" value='<%=p.getId()%>'> 
						 </tr>
						<%}
						}else{ %><tr>
						 <td colspan="7">暂无产品，请先在产品管理中添加产品。</td></tr>
						 <%} %>
                    </table>
                     </div>
                     <div class="result-list">
                     
                     <table class="search-tab">
                      
                        <tr>
                            <th>
                      <button class="btn btn-info" id="abtn"><i class="icon-font">&#xe026;</i>提交申请</button> 
                     <button class="btn btn-success" id="ebtn" onclick="javascript:history.go(-1)"><i class="icon-font">&#xe02b;</i>返回上一页</button></th><th></th>
                     </tr>
                     </table>
                   
                     </div>
                       <div class="result-list">
                     <table class="search-tab">
                     <tr>
                     <th colspan="2"><span id="error" style="color:red"></span></th>
                     </tr>
                     </table>
                     </div>
                     
                    </form>
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