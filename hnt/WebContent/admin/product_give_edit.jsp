<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.ProductType" %>
<%@page import="com.ssm.pojo.Product" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>
<%@page import="com.ssm.utils.Pager" %>
<%@page import="com.ssm.pojo.Admin" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
String token = (String)request.getSession().getAttribute("token");
Product product = (Product)request.getAttribute("product");
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
    
    <title>上传图片</title>
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
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="admin/ProductServlet.do?method=list">产品列表</a><span class="crumb-step">&gt;</span><span>绑定赠品</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form action="admin/ProductServlet?method=give_save" method="post" id="myform" name="myform">
                 <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
                  <input type="hidden" name="id" id="id" value="<%=product.getId()%>"/> 
                    <table class="insert-tab" width="97%">
                        <tbody><tr>
                            <th width="150px"><i class="require-red">*</i>产品编号：</th>
                            <td><%=StringUtil.notNull(product.getProductId()) %></td>
                        </tr>
                       <tr>  <th><i class="require-red">*</i>产品名称：</th>
                       <td><%=StringUtil.notNull(product.getProductName()) %>
                       </td></tr>
                        <tr><th><i class="require-red">*</i>原赠品编号：</th>
                                <td>
                                  <%=StringUtil.notNull(product.getGiveProductId()) %></td>
                            </tr>
                            <tr><th><i class="require-red">*</i>新赠品编号：</th>
                                <td>
                                 <input type="text" name="productId" id="productId" class="common-text required" />
                                 <span id="productIdTag"></span> <span id="productIdTag1"></span>
                                 </td>
                            </tr>
                            <tr>
                                <th></th>
                                <td>
                                    <input id="sbtn" class="btn btn-primary mr10" value="提交" type="button">
                                    <input class="btn" onClick="javascript:history.go(-1)" value="返回" type="button">
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

$("#productId").blur(function() {
		$.ajax({
				type : "post",
				url : "admin/ProductServlet?method=productAjax",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
				data : {//设置数据源
					productId : $("#productId").val()
				//这里不要加","  不然会报错，而且根本不会提示错误地方

				},

				dataType : "json",//设置需要返回的数据类型
				success : function(data) {
					var d = data;//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
					//得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
					
					if(d.name==""){
					  $("#productIdTag").text("产品信息获取失败，请认真核对编号。").css({"color":"red","font-size":"12px"});
					   $("#productIdTag1").text("");
					  }else{
					   $("#productIdTag1").text(""+d.name+"").css({"color":"green","font-size":"12px"});
					   $("#productIdTag").text("");
					  }
				},
				error : function() {
					alert("系统异常，请稍后重试！");
				}//这里不要加","
				
			});
	});

	$("#sbtn").click(function(){
		if (!confirm("确认保存图片信息？")) {
								return false;
							} else {
		var errorstr = "";
		if($("#productId").val()==""){
			 $("#productIdTag").text("赠品编号不能为空，请认真核对编号。").css({"color":"red","font-size":"12px"});
			$("#productIdTag1").text("");
			errorstr="1";
		}else if($("#productIdTag").text()!=""){
			errorstr="1";
		}
		
		if(errorstr!=""){
			return false;
		}else{
			
       		 $('#myform').submit();
       		 return ture;
		
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
