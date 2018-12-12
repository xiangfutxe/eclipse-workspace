<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.Product" %>
<%@page import="com.pojo.Inventory" %>
<%@page import="com.pojo.Supplier" %>
<%@page import="com.pojo.ProductType" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.pojo.Admin" %>
<%@page import="com.utils.Pager" %>
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
		if(rankstr[3][4].equals("1")||admin.getState().equals("1")){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>新增入库</title>
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
	$("#supplier").blur(function() {
		if($(this).val()==""){
			$("#supplierTag").text("供应商不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else $("#supplierTag").text("");
	});	
	$("#abtn").click(function(){
		if (!confirm("确认提交申请？")) {
            return false;
        }else{
        	var error=0;
        	if($("#supplier").val()==""){
	        	$("#supplierTag").text("供应商不能为空").css({"color":"red","font-size":"12px"});
	        	error++;
        	}else if($("#supplierTag").text()!=""){
        	 error++;
        	}
 			if(error==""){
         		$('#saveform').attr("action", "admin/InventoryServlet.do?method=apply_in_save");
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
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a>
            <span class="crumb-step">&gt;</span><span class="crumb-name">仓库管理</span>
            <span class="crumb-step">&gt;</span><span class="crumb-name">入库申请</span>
            <span class="crumb-step">&gt;</span><span class="crumb-name">新增入库</span></div>
        </div>
       
        <div class="result-wrap">
           <form action="admin/InventoryServlet.do?method=apply_in_save" method="post" id="saveform" name="saveform">
                 <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
             <table class="insert-tab" width="100%">
                        <tbody>
                            <tr>
                                <th width="150px"><i class="require-red">*</i>供应商：</th>
                                <td>
<select id="supplier" name="supplier">
  <%
							Collection coll =  (Collection)request.getAttribute("coll");
							if(coll!=null){
									Iterator it = coll.iterator();
									while(it.hasNext()){
									Supplier su = (Supplier)it.next();
							%>
<option value="<%=su.getId()%>"><%=su.getName() %></option>
<%}}%>
</select>      <span id="supplierTag"></span>                          </td>
                            </tr>
                             <tr>
                                <th>入库类型：</th>
                                <td>
									<select id="type" name="type"> 
									<option value='1'>采购入库</option>
									<option value='2'>加工入库</option>
									<option value='3'>退货入库</option>
									</select>
									                                </td>
                            </tr>
                             <tr>
                                <th>单据编号：</th>
                                <td>
                                    <input class="common-text required" id="docNum" name="docNum" value="" type="text">&nbsp;<span id="addressTag"></span>
                                </td>
                            </tr>
                            
                            <tr>
                                <th></th>
                                <td>
                                    <input id="abtn" class="btn btn-primary mr10" value="生成申请单" type="submit">
                                    <input class="btn " onClick="history.go(-1)" value="返回" type="button">
                                </td>
                            </tr>
                        </tbody></table>
                   
                     
                    </form>
                     </div>
                       <div class="result-list">
                     <table class="search-tab">
                     <tr>
                     <th colspan="2"><span id="error" style="color:red"></span></th>
                     </tr>
                     </table>
                     </div>
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