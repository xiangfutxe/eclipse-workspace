<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.VideoInfo" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.utils.Pager" %>
<%@page import="com.pojo.Admin" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
String token = (String)request.getSession().getAttribute("token");
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
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="admin/ProductServlet.do?method=list">产品列表</a><span class="crumb-step">&gt;</span><span>上传图片</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form action="admin/VideoServlet.do?method=img_save" method="post" id="myform" name="myform" enctype="multipart/form-data">
                 <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
                  <input type="hidden" name="id" id="id" value="<%=video.getId()%>"/> 
                    <table class="insert-tab" width="97%">
                        <tbody><tr>
                            <th width="150px"><i class="require-red">*</i>视频标题：</th>
                            <td><%=StringUtil.notNull(video.getTitle()) %></td>
                        </tr>
                       <tr>  <th><i class="require-red">*</i>视频地址：</th>
                       <td><%=StringUtil.notNull(video.getVideoUrl()) %>
                       </td></tr>
                        <tr>  <th><i class="require-red">*</i>视频截图：</th>
                       <td><img src="upload/<%=StringUtil.notNull(video.getImageUrl()) %>" alt="暂无" width='300' height="300"/>
                       </td></tr>
                        <tr><th><i class="require-red">*</i>视频截图：</th>
                                <td>
                                  <input name="image" id="image" type="file"></td>
                            </tr>
                            <tr>
                                <th></th>
                                <td>
                                    <input id="sbtn" class="btn btn-primary " value="提交" type="button">
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

	$("#sbtn").click(function(){
		if (!confirm("确认保存图片信息？")) {
								return false;
		} else {
		var errorstr = "";
		
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
