<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.User" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.pojo.Branch" %>
<%@page import="com.pojo.Admin" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
String token = (String)request.getSession().getAttribute("token");
Branch branch = (Branch) request.getAttribute("admin_branch");
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else if(branch==null){
		out.println("<script>");
		out.println("店铺信息获取失败！");
		out.println("</script>");
	}else{
	 String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[1][4].equals("1")||admin.getState().equals("1")){

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>分店授信</title>
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
       <script type="text/javascript" src="js/jquery.cityselect.js"></script>
     
  </head>
  
  <body>
  <div class="container clearfix">
   <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span>分店管理<span class="crumb-step">&gt;</span><span>分店新增</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                 <form action="admin/BranchServlet.do?method=admin_credit_save" method="post" id="myform" name="myform">
                     <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/>
                      <input type="hidden" name="code" id="code" value="<%=branch.getId()%>"/>
                    <table class="insert-tab" width="100%">
                        <tbody>
                            <tr>
                                <th width="150px">店铺编号：</th>
                                <td><%=StringUtil.notNull(branch.getCode()) %>
                                </td>
                            </tr>
                             <tr>
                                <th width="150px">店铺名称：</th>
                               <td><%=StringUtil.notNull(branch.getName()) %></td>
                            </tr>
                            <tr>
                                <th width="150px"><i class="require-red">*</i>原授信额度：</th>
                                <td><%=StringUtil.decimalFormat(branch.getCredit()) %></td>
                            </tr>
                           
                             <tr>
                                <th width="150px"><i class="require-red">*</i>新授信额度：</th>
                                <td><input type="text" name="credit"
							id="credit" value="0">&nbsp;<span id="creditTag"></span></td>
                            </tr>
                             <tr>
                                <tr>
                                <th></th>
                                <td>
                                    <input id="sbtn" class="btn btn-primary " value="提交修改" type="button">
                                     <input id="back_btn" class="btn btn-info " value="返回上一页"  onclick="javascript:history.go(-1);"type="button">
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

	var reg1 = /^[0-9]*$/; //验证非负整数
		
		
		$("#credit").blur(function(){
			 if($(this).val()=="")  $("#creditTag").text("授信额度不能为空！").css({"color":"red","font-size":"12px"}); 
	   		else if(!(reg1.test($(this).val())))
	   		$("#creditTag").text("授信额度格式有误！").css({"color":"red","font-size":"12px"}); 
	   		else $("#creditTag").text("");
		});
		
	
	
	$("#sbtn").click(function() {
	  if (!confirm("确认提交申请？")) {
				window.event.returnValue = false;
			}else{
	var errorstr ="";
		
			if($("#credit").val()==""){
					$("#creditTag").text("授信额度不能为空！").css({"color":"red","font-size":"12px"}); 
					errorstr =errorstr+"1";
			}else if($("#creditTag").text()!=""){
				errorstr =errorstr+"1";
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
