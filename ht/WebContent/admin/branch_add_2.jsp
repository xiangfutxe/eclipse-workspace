<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.Admin" %>
<%@page import="com.weixin.pojo.WeixinUserInfo" %>
<%@page import="com.utils.StringUtil" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
String token = (String)request.getSession().getAttribute("token");
WeixinUserInfo weixin_user = (WeixinUserInfo)request.getAttribute("weixin_user");
if(admin==null) {
out.println("<script>");
out.println("parent.window.location.href='login.jsp';");
out.println("</script>");
}else if(weixin_user==null) {
out.println("会员信息获取失败，请重试");
}else{
	   String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[1][6].equals("1")||admin.getState().equals("1")){
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>新增分会</title>
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
      <script type="text/javascript" src="js/jquery.cityselect.js"></script>
  </head>
  
  <body>
  <div class="container clearfix">
   <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a>
            <span class="crumb-step">&gt;</span>会员管理
            <span class="crumb-step">&gt;</span><span><a class="crumb-name" href="admin/BranchServlet.do?method=admin_branch_list">新增协会</a></span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form action="admin/BranchServlet.do?method=admin_branch_save" method="post" id="myform" name="myform">
                <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
                    <table class="insert-tab" width="97%">
                        <tbody>
                          <tr>
                                <th width="120px"><i class="require-red">*</i>会员信息：</th>
                                <td>
                                <%=weixin_user.getUserId() %>(<%=weixin_user.getNickName() %>)
                                    <input  id="userId" name="userId"  value="<%=weixin_user.getUserId() %>" type="hidden">
                                </td>
                            </tr>
                            <tr>
                                <th width="120px"><i class="require-red">*</i>分会编号：</th>
                                <td>
                                    <input class="common-text required" id="branchId" name="branchId"  value="" type="text">&nbsp;<span id="idTag"></span>
                                </td>
                            </tr>
                             <tr>
                                <th width="120px"><i class="require-red">*</i>分会名称：</th>
                                <td>
                                    <input class="common-text required" id="branchName" name="branchName"  value="" type="text">&nbsp;<span id="nameTag"></span>
                                </td>
                            </tr>
                             <tr>
                                <th><i class="require-red">*</i>协会密码：</th>
                                <td>
                                    <input class="common-text required" id="password" name="password" value="" type="password">&nbsp;<span id="passwordTag"></span>
                                </td>
                            </tr>
                             <tr>
                                <th><i class="require-red">*</i>确认密码：</th>
                                <td>
                                    <input class="common-text required" id="repassword" name="repassword" value="" type="password">&nbsp;<span id="repasswordTag"></span>
                                </td>
                            </tr>
                             <tr>
                                <th><i class="require-red">*</i>联系电话：</th>
                                <td>
                                    <input class="common-text required" id="phone" name="phone" value="" type="text">&nbsp;<span id="phoneTag"></span>
                                </td>
                            </tr>
                             <tr>
                                <th><i class="require-red">*</i>所在区域：</th>
                                <td>
              <div id="city_2">
											<select class="prov common-text " id="province"
												name="province"></select> <select class="city common-text "
												id="city" name="city" disabled="disabled">
											</select> <select class="dist common-text " id="area" name="area"
												disabled="disabled"></select> &nbsp;<span id="provinceTag"></span>
										</div>
										</td>
                            </tr>
                            <tr>
									<th>详细地址：</th>
									<td><input class="common-text required" id="address"
										name="address" value="" type="text">&nbsp;<span
										id="addressTag"></span></td>

								</tr>
                            <tr>
                                <th></th>
                                <td>
                                    <input id="sbtn" class="btn btn-primary mr10" value="保存" type="button">
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
   <script type="text/javascript" src="js/custom.js"></script>
 <script language="JavaScript">
$(function() {

$("#city_2").citySelect({
			nodata : "none",
			required : false
		});

	$("#branchId").blur(function() {
		$.ajax({
				type : "post",
				url : "admin/BranchServlet.do?method=isExit",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
				data : {//设置数据源
					branchId : $("#branchId").val()
				//这里不要加","  不然会报错，而且根本不会提示错误地方
				},

				dataType : "json",//设置需要返回的数据类型
				success : function(data) {
					var d = data.tag;
					if(d==0) $("#idTag").text("编号不能为空！").css({"color":"red","font-size":"12px"});
					else if(d==1)  $("#idTag").text("编号已存在！").css({"color":"red","font-size":"12px"});
					else if(d==2)  $("#idTag").text("编号可用！").css({"color":"green","font-size":"12px"});
					else if(d==3)  $("#idTag").text("数据库连接失败或管理员登录异常！").css({"color":"green","font-size":"12px"});
					else $("#idTag").text("");
					if(getByteLen($("#branchId").val())>50) $("#idTag").text("编号字数不能大于25！").css({"color":"red","font-size":"12px"});
					if(getByteLen($("#branchId").val())<3) $("#idTag").text("编号字数不能小于3！").css({"color":"red","font-size":"12px"});
				},
				error : function() {
					$("#idTag").text("系统异常，请稍后重试！").css({"color":"red","font-size":"12px"});
				}//这里不要加","
				
			});
	});
	
	$("#password").blur(function() {
		if($("#password").val()=="")$("#passwordTag").text("用户密码不能为空！").css({"color":"red","font-size":"12px"});
		else if(getByteLen($(this).val())>50) $("#passwordTag").text("用户密码字数不能大于25！").css({"color":"red","font-size":"12px"});
		else $("#passwordTag").text("");
	});
	$("#repassword").blur(function() {
		if($("#repassword").val()!=$("#password").val())$("#repasswordTag").text("两次输入的密码不一致，请重试。").css({"color":"red","font-size":"12px"});
		else if(getByteLen($(this).val())>50) $("#repasswordTag").text("用户密码字数不能大于25！").css({"color":"red","font-size":"12px"});
		else $("#repasswordTag").text("");
	});
	$("#province").blur(function(){
   		 if($("#province").val()=="")$("#provinceTag").text("经营区域选择不完整！").css({"color":"red","font-size":"12px"});
   			else $("#provinceTag").text("");
   			});
   			$("#city").blur(function(){
   		 if($("#city").val()=="")$("#provinceTag").text("经营区域选择不完整！").css({"color":"red","font-size":"12px"});
   			else $("#provinceTag").text("");
   			});
		
		$("#address").blur(function(){
   		 if($("#address").val()!=""){
   		  if(getByteLen($("#address").val())>100) $("#addressTag").text("联系地址字数不能大于50位数！").css({"color":"red","font-size":"12px"});
   		else $("#addressTag").text("");
   		}else $("#addressTag").text("");
   			});
   			
	$("#sbtn").click(function(){
	if (!confirm("确认提交注册管理员信息？")) {
								return false;
							} else {
		var errorstr = "";
		if($("#branchId").text()!=""){
		$("#idTag").text("分会编号不能为空！").css({"color":"red","font-size":"12px"});
			errorstr ="1";
		}else if($("#idTag").val()!=""){
			if($("#idTag").val()!="编号可用！")
			errorstr ="1";
		}
		if($("#branchName").text()!=""){
		$("#nameTag").text("分会名称不能为空！").css({"color":"red","font-size":"12px"});
			errorstr ="1";
		}else if($("#nameTag").val()!=""){
			errorstr ="1";
		}
		if($("#password").val()==""){
			$("#passwordTag").text("用户密码不能为空！").css({"color":"red","font-size":"12px"});
			errorstr ="1";
		}else if($("#passwordTag").text()!=""){
			errorstr ="1";
		}
	
		if($("#repassword").val()!=$("#password").val()){
			$("#repasswordTag").text("两次输入的密码不一致，请重试。").css({"color":"red","font-size":"12px"});
			errorstr ="1";
		}else if($("#repasswordTag").text()!=""){
			errorstr ="1";
		}
		
			if($("#province").val()=="") {
   		   $("#provinceTag").text("经营区域选择不完整！").css({"color":"red","font-size":"12px"});
   			errorstr = errorstr++;
   		 }
   		else if($("#provinceTag").val()!="") errorstr++;
   		
   		if($("#city").val()=="") {
   		   $("#provinceTag").text("经营区域选择不完整！").css({"color":"red","font-size":"12px"});
   			errorstr = errorstr++;
   		 }
   		else if($("#provinceTag").val()!="") errorstr++;
		
   		if($("#address").val()!="") {
   		  if(getByteLen($("#address").val())>100){
   		   $("#addressTag").text("联系地址字数不能大于50位数！").css({"color":"red","font-size":"12px"});
   			errorstr = errorstr++;
   				}
   		 }
   		else if($("#addressTag").val()!="") errorstr++;
	
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
