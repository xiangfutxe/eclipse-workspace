<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.User" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.utils.Pager" %>
<%@page import="com.pojo.Admin" %>
<%@page import="com.pojo.Center" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
String token = (String)request.getSession().getAttribute("token");
Center center = (Center) request.getAttribute("center");
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[1][5].equals("1")||admin.getState().equals("1")){
		if(center!=null){

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>服务店降级</title>
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
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span>会员管理<span class="crumb-step">&gt;</span><span>服务中心</span><span class="crumb-step">&gt;</span><span>服务店降级</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                   <form action="admin/CenterServlet.do?method=admin_down_save" method="post" id="myform" name="myform">
                     <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/>
                      <input type="hidden" name="id" id="id" value="<%=center.getId()%>"/>
                    <table class="insert-tab" width="100%">
                        <tbody>
                            <tr>
                                <th width="150px"><i class="require-red">*</i>会员ID：</th>
                                <td><%=center.getUserId()%>(<%=center.getUserName()%>)
                                </td>
                            </tr>
                             <tr>
                                <th width="150px"><i class="require-red">*</i>升级店类型：</th>
                                <td>
                               资格店</td>
                            </tr>
                               <tr>
                                <th width="150px"><i class="require-red">*</i>服务店编号：</th>
                                <td><%=StringUtil.notNull(center.getCenterId()) %></td>
                            </tr>
                            <tr>
                                <th width="150px"><i class="require-red">*</i>服务店名称：</th>
                                <td><input
							id="centerName" name="centerName" value='<%=StringUtil.notNull(center.getCenterName()) %>' type="text">&nbsp;<span
							id="nameTag"></span></td>
                            </tr>
                            <tr>
									<th><i class="require-red">*</i>经营区域：</th>
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
                                <th width="150px"><i class="require-red">*</i>经营地址：</th>
                                <td><input type="text" value='<%=StringUtil.notNull(center.getAddress()) %>'
							name="address" id="address">&nbsp;<span id="addressTag"></span></td>
                            </tr>
                             <tr>
                                <th width="150px"><i class="require-red">*</i>联系电话：</th>
                                <td><input type="text" name="phone"
							id="phone" value="<%=StringUtil.notNull(center.getPhone()) %>">&nbsp;<span id="phoneTag"></span></td>
                            </tr>
                             <tr>
                                <tr>
                                <th></th>
                                <td>
                                    <input id="sbtn" class="btn btn-primary" value="提交" type="button"/>
                                    &nbsp;
                                    <input id="bbtn" class="btn btn-info" value="返回" type="button" onclick="javascript:history.go(-1);"/>
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
var reg = /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/; //电话号码与手机号码同时验证
	
 $("#city_2").citySelect({
		
		prov:"<%=StringUtil.notNull(center.getProvince())%>", 
    	city:"<%=StringUtil.notNull(center.getCity())%>",
		dist:"<%=StringUtil.notNull(center.getArea())%>",
		nodata:"none"
	}); 
$("#centerName").blur(function(){
			 if($(this).val()=="")  $("#nameTag").text("服务店名称不能为空！").css({"color":"red","font-size":"12px"}); 
   		 else if(getByteLen($("#centerName").val())>50) $("#nameTag").text("服务店名称字数不能大于25位数！").css({"color":"red","font-size":"12px"});
	else $("#nameTag").text("");
		});
		
	
	
    	$("#phone").blur(function(){
   	
				if(!(reg.test($(this).val())))
					$("#phoneTag").text("联系电话格式有误！").css({"color":"red","font-size":"12px"}); 
					else $("#phoneTag").text("");
	
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
   
	
	$("#sbtn").click(function() {
	  if (!confirm("确认提交将实体店降为资格店？")) {
				window.event.returnValue = false;
			}else{
	var errorstr ="";
			if($("#centerName").val()==""){
					$("#nameTag").text("服务店名称不能为空！").css({"color":"red","font-size":"12px"}); 
					errorstr =errorstr+"1";
				
			}else if($("#nameTag").text()!=""){
				errorstr =errorstr+"1";
			}
			if($("#address").val()==""){
					$("#addressTag").text("联系地址不能为空！").css({"color":"red","font-size":"12px"}); 
					
					errorstr =errorstr+"1";
				
			}else if($("#addressTag").text()!=""){
				errorstr =errorstr+"1";
			}
			
			if($("#address").val()==""){
					$("#addressTag").text("联系地址不能为空！").css({"color":"red","font-size":"12px"}); 
					
					errorstr =errorstr+"1";
				
			}else if($("#addressTag").text()!=""){
				errorstr =errorstr+"1";
			}
			
			if(!(reg.test($("#phone").val()))){
					$("#phoneTag").text("联系电话格式有误！").css({"color":"red","font-size":"12px"}); 
					errorstr =errorstr+"1";
				
			}else if($("#phoneTag").text()!=""){
				errorstr =errorstr+"1";
			}
			
				if($("#province").val()=="") {
   		   $("#provinceTag").text("经营区域选择不完整！").css({"color":"red","font-size":"12px"});
   			errorstr =errorstr+"1";
   		 }
   		else if($("#provinceTag").val()!="") errorstr =errorstr+"1";
   		
   		if($("#city").val()=="") {
   		   $("#provinceTag").text("经营区域选择不完整！").css({"color":"red","font-size":"12px"});
   			errorstr =errorstr+"1";
   		 }
   		else if($("#provinceTag").val()!="") errorstr =errorstr+"1";
		
   		if($("#address").val()!="") {
   		  if(getByteLen($("#address").val())>100){
   		   $("#addressTag").text("联系地址字数不能大于50位数！").css({"color":"red","font-size":"12px"});
   			errorstr =errorstr+"1";
   				}
   		 }
   		else if($("#addressTag").val()!="") errorstr =errorstr+"1";
		
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
			out.println("alert('需要修改的服务店信息获取失败！')");
			out.println("</script>");
		}
}else{
			out.println("<script>");
			out.println("alert('你没有权限进行该操作！')");
			out.println("</script>");
		}
}
%>
