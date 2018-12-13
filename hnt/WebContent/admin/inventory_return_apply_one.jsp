<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.User" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>
<%@page import="com.ssm.utils.Pager" %>
<%@page import="com.ssm.pojo.Admin" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
		String[] strarray =admin.getRank().split(",");
	    			String[][] rankstr = new String[10][10];
	    			for(int i=0;i<10;i++){
	    				for(int j=0;j<10;j++){
	    					if(strarray.length>i){
	    						if(strarray[i].length()==10)
	    						rankstr[i][j] =  strarray[i].substring(j, j+1);
	    						else rankstr[i][j]="0";
	    					}
	    					else rankstr[i][j]="0";
	    				}
	    			}
		if(rankstr[1][2].equals("1")||admin.getState().equals("1")){

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>等级变更</title>
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
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span>仓库管理<span class="crumb-step">&gt;</span><span>调库管理</span>
            <span class="crumb-step">&gt;</span><span>新增调库</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                 <form action="admin/InventoryReturnApplyServlet.do?method=apply_add_1" method="post" id="myform" name="myform">
                    <table class="insert-tab" width="100%">
                        <tbody>
                            <tr>
                                <th width="100px"><i class="require-red">*</i>订单编号：</th>
                                <td><input type="text" name="orderId" id="orderId"><span id="msg1"></span><span id="msg"></span>
                                </td>
                            </tr>
                             <tr>
                                <tr>
                                <th></th>
                                <td>
                                    <input id="sbtn" class="btn btn-primary btn4 mr10" value="提交" type="button">
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
   <script type="text/javascript" src="js/custom.js"></script>
 <script language="JavaScript">
$(function() {

$("#userId").blur(
				function() {
					$.ajax({
						type : "post",
						url : "admin/InventoryReturnApplyServlet.do?method=orderIdAjax",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
						data : {//设置数据源
							userId : $("input[name=orderId]").val()
						//这里不要加","  不然会报错，而且根本不会提示错误地方
						},

						dataType : "json",//设置需要返回的数据类型

						success : function(data) {
							var d = data;//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
							if (d.msg == "") {
								$("#msg").text("");
								$("#msg1").text("该订单可调库。").css({"color":"green","font-size":"12px"});
							} else{
								$("#msg").text("" + d.msg + "")
										.css({
											"color" : "red",
											"font-size" : "12px"
										});
								$("#msg1").text("");		
							}

						},

						error : function() {

							alert("系统异常，请稍后重试！");

						}//这里不要加","
					});
				});
	$("#sbtn").click(function(){
	 if (!confirm("确认提交操作？")) {
				return false;
			}else{
		var errorstr = "";
		if($("#orderId").val()==""){
			$("#msg").text("订单编号不能为空。").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}else if($("#msg").val()!=""){
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
