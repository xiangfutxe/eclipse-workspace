<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.Admin" %>
<%@page import="com.utils.StringUtil" %>
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
		if(rankstr[2][0].equals("1")||admin.getState().equals("1")){
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>新增视频</title>
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
             <div class="crumb-list"><i class="icon-font"></i><a href="user/index.jsp">首页</a>
             <span class="crumb-step">&gt;</span><span>视频管理</span>
             <span class="crumb-step">&gt;</span><span>新增视频</span>
             </div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
               <form action="admin/VideoServlet.do?method=admin_save" method="post" id="myform"  enctype="multipart/form-data">
               <table class="insert-tab" width="100%">
               	<tbody>
               	<tr>
               		<th width="120px"><i class="require-red">*</i>视频标题：</th>
               		<td><input class="common-text" name="title" id="title" type="text" size="50">&nbsp;<span id="titleTag"></span>
               	 	<input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/>
               		</td>
               	</tr>
               		<tr>
               		<th width="120px"><i class="require-red">*</i>视频文件1：</th>
               		<td><input name="videoUrl" id="videoUrl" type="file">&nbsp;<span id="videoUrlTag"></span>
               		</td>
               	</tr>
               	<tr>
               		<th width="120px"><i class="require-red">*</i>视频文件2：</th>
               		<td><input name="videoUrl2" id="videoUrl2" type="file">&nbsp;<span id="videoUrl2Tag"></span>
               		</td>
               	</tr>
               	<tr>
               		<th width="120px"><i class="require-red">*</i>视频文件3：</th>
               		<td><input name="videoUrl3" id="videoUrl3" type="file">&nbsp;<span id="videoUrl3Tag"></span>
               		</td>
               	</tr>
               		<tr>
               		<th width="120px"><i class="require-red">*</i>视频截图：</th>
               		<td><input name="imageUrl" id="imageUrl" type="file" >&nbsp;<span id="imageUrlTag"></span>
               		</td>
               	</tr>
               		<tr>
               		<th width="120px"><i class="require-red">*</i>视频来源：</th>
               		<td><input class="common-text" name="source" id="source" type="text" size="50">&nbsp;<span id="sourceTag"></span>
               		</td>
               	</tr>
               	 <tr>
                    <th><i class="require-red">*</i>视频简介：</th>
                      <td><input class="common-text" name="introduction" id="introduction" type="text" size="50">&nbsp;<span id="introductionTag"></span>
						</td>
                            </tr>
                             <tr>
                                <th>&nbsp;</th>
                                <td>
                                    <input id="btn" class="btn btn-primarymr10" value="提交" type="button">
                                    &nbsp; <input id="btn1" class="btn"  value="重置" type="button">
                                </td>
                            </tr>
                             <tr>
                                <td colspan='2'>备注：视频文件建议尺寸560*420，视频截图建议尺寸248*186。</td>
                               
                            </tr>
               	</tbody>
               </table>
               </form>
               </div>
               </div>
               
    </div>
    </div>
  </body>
  
 <script language="JavaScript">
$(function() {
	$("#title").blur(function() {
		if($(this).val()=="")$("#titleTag").text("视频标题不能为空！").css({"color":"red","font-size":"12px"});
		else if(getByteLen($(this).val())>200) $("#titleTag").text("视频标题字数不能大于100！").css({"color":"red","font-size":"12px"});
		else $("#titleTag").text("");
	});
	
	$("#introduction").blur(function() {
		if($(this).val()=="")$("#introductionTag").text("视频简介不能为空！").css({"color":"red","font-size":"12px"});
	else if(getByteLen($(this).val())>200) $("#introductionTag").text("视频简介字数不能大于100！").css({"color":"red","font-size":"12px"});
		else $("#introductionTag").text("");
	});
	
	$("#source").blur(function() {
		if($(this).val()=="")$("#sourceTag").text("发布来源不能为空！").css({"color":"red","font-size":"12px"});
	
		else $("#sourceTag").text("");
	});
	$("#videoUrl").blur(function() {
		if($(this).val()=="")$("#videoUrlTag").text("视频地址不能为空！").css({"color":"red","font-size":"12px"});
	else if(getByteLen($(this).val())>200) $("#videoUrlTag").text("视频地址字数不能大于100！").css({"color":"red","font-size":"12px"});
		else $("#videoUrlTag").text("");
	});
	
	
	$("#btn").click(function(){
		 if (!confirm("确认提交保存视频！")) {
				return false;
			}
			else{
				var errorstr = "";
				if($("#title").val()==""){
					$("#titleTag").text("视频标题不能为空！").css({"color":"red","font-size":"12px"}); 
					errorstr ="1";
				}else if($("#titleTag").text()!=""){
					errorstr ="1";
				}
				
				if($("#source").val()==""){
					$("#sourceTag").text("视频来源不能为空！").css({"color":"red","font-size":"12px"}); 
					errorstr ="1";
				}else if($("#sourceTag").text()!=""){
					errorstr ="1";
				}
				
				if($("#introduction").val()==""){
					$("#introductionTag").text("视频简介不能为空！").css({"color":"red","font-size":"12px"}); 
					errorstr ="1";
				}else if($("#introductionTag").text()!=""){
					errorstr ="1";
				}
				
				if($("#videoUrl").val()==""){
					$("#videoUrlTag").text("视频地址不能为空！").css({"color":"red","font-size":"12px"}); 
					errorstr ="1";
				}else if($("#videoUrlTag").text()!=""){
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