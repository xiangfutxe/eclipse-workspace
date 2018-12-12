<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.ProductType" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.pojo.Admin" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
String token = (String)request.getSession().getAttribute("token");
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
    
    <title>选择分类</title>
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
       <script type="text/javascript" src="js/custom.js"></script>
     
  </head>
  
  <body>
  <div class="container clearfix">
   <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a>分类管理<span class="crumb-step">&gt;</span>
           <span><a href="admin/ProductServlet?method=list">产品列表</a></span>
            <span class="crumb-step">&gt;</span><span>选择分类</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form action="admin/ProductServlet?method=admin_product_add_2" method="post" id="myform" name="myform">
                 
                    <table class="insert-tab" width="97%">
                        <tbody>
                            <tr>
                                <th width="120px"><i class="require-red">*</i>产品类型：</th>
                                <td>
                                <select name="typeName" id="typeName" class="common-text required">
                                <%Collection coll =  (Collection)request.getAttribute("coll_pt");
							if(coll!=null){
									Iterator it = coll.iterator();
									int t= 0;
									while(it.hasNext()){
									ProductType pt = (ProductType)it.next();
									 %>
                                <option value="<%=pt.getName()%>"><%=pt.getName()%></option>
                               <%}} %>
                                </select> &nbsp;<span id="nameTag"></span>
                                </td>
                            </tr>
                            <tr>
                                <th></th>
                                <td>
                                    <input id="sbtn" class="btn btn-primary" value="提交" type="button">
                                  
                                </td>
                            </tr>
                        </tbody></table>
                </form>
            </div>
        </div>

    </div>
    <!--/main-->
    </div>
  </body>
  
 <script language="JavaScript">
 
$(function() {
	$("#typeName").blur(function() {
		if($("#typeName").val()=="")
		$("#nameTag").text("分类名称不能为空！").css({"color":"red","font-size":"12px"});
	});
	
	$("#sbtn").click(function(){
			if (!confirm("确认选择该产品分类？")) {
								return false;
							} else {
		var errorstr = "";
		if($("#typeName").val()==""){
			$("#nameTag").text("分类名称不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}else if($("#nameTag").text()!=""){
			errorstr ="1";
		}
		if(errorstr!=""){
			return false;
		}else{
       		 $('#myform').submit();
       		 return true;
		
		}
}
	});
});
</script>
</html>
<%
}else{
			out.println("<script>");
			out.println("alert('你没有权限进行该操作！')");
			out.println("</script>");
		}
}
%>
