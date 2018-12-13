<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.Admin" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.pojo.VideoInfo" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
VideoInfo video = (VideoInfo)request.getAttribute("video_info");
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
    
    <title>视频详情</title>
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
             <span class="crumb-step">&gt;</span><span>视频详情</span>
             </div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
               <form action="admin/VideoServlet.do?method=admin_save" method="post" id="myform">
               <table class="insert-tab" width="100%">
               	<tbody>
               	</tr>
               		<tr>
               		<th width="120px"><i class="require-red">*</i>视频展示：</th>
               		<td> 
               		
  <object width="560" height="420">  
            <param name="movie" value="upload/video/<%=video.getVideoUrl()%>"></param>  
            <param name="flashvars"></param>  
            <param name="allowFullScreen" value="true"></param>  
            <param name="allowscriptaccess" value="always"></param>  
            <embed src="test.swf" type="application/x-shockwave-flash"  
                allowscriptaccess="always" allowfullscreen="true"> </embed>  
        </object>  
        <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,0,0" width="500" height="400" id="FLVPlayer"> 
<param name="movie" value="FLVPlayer_Progressive.swf" /> 
<param name="salign" value="lt" /> 
<param name="quality" value="high" /> 
<param name="scale" value="noscale" /> 
<param name="FlashVars" value="&MM_ComponentVersion=1&skinName=Clear_Skin_1&streamName=FLV文件名&autoPlay=false&autoRewind=false" /> 
<embed src="upload/video/<%=video.getVideoUrl()%>" flashvars="&MM_ComponentVersion=1&skinName=Clear_Skin_1&streamName=FLV文件名&autoPlay=false&autoRewind=false" quality="high" scale="noscale" width="320" height="240" name="FLVPlayer" salign="LT" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" /> 
</object> 
</td>
               	</tr>
               	<tr>
               		<th width="120px"><i class="require-red">*</i>视频标题：</th>
               		<td><%=StringUtil.notNull(video.getTitle()) %>
               	 	
               		</td>
               	</tr>
               		<tr>
               		<th width="120px"><i class="require-red">*</i>视频地址：</th>
               		<td><%=StringUtil.notNull(video.getVideoUrl())%>
               		</td>
               	</tr>
               		<tr>
               		<th width="120px"><i class="require-red">*</i>视频来源：</th>
               		<td><%=StringUtil.notNull(video.getSource())%></td>
               	</tr>
               	<tr>
               		<th width="120px"><i class="require-red">*</i>视频简介：</th>
               		<td><%=StringUtil.notNull(video.getIntroduction())%></td>
               	</tr>
               	
                             <tr>
                                <th>&nbsp;</th>
                                <td>
                                    &nbsp; <input id="btn1" class="btn"  value="返回" type="button" onclick="javascript:history.go(-1);">
                                </td>
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