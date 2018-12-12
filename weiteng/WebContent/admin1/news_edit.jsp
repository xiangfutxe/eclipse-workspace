<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.Admin" %>
<%@page import="com.pojo.News" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
String token = (String)request.getSession().getAttribute("token");
News news = (News) request.getAttribute("news");
if(admin==null) {
out.println("<script>");
out.println("parent.window.location.href='login.jsp';");
out.println("</script>");
}else{
	String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[0][0].equals("1")||admin.getState().equals("1")){
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改新闻</title>
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
	     <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.min.js"> </script>
  <script type="text/javascript" charset="utf-8" src="ueditor/lang/zh-cn/zh-cn.js"></script>
      <script charset="utf-8" src="kindeditor/kindeditor-all.js"></script>
<script>
KindEditor.ready(function(K) {
	window.editor=K.create('#editor_id', {
		  uploadJson : 'kindeditor/jsp/upload_json.jsp',
                fileManagerJson : 'kindeditor/jsp/file_manager_json.jsp',
                allowFileManager : true
	});
});
        
</script>
  </head>
  
  <body>
  <div class="container clearfix">
   <div class="main-wrap">
        <div class="crumb-wrap">
             <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span>新闻管理<span class="crumb-step">&gt;</span><span>修改新闻</span>
			</div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form action="admin/NewsServlet.do?method=admin_news_update" method="post" id="myform" name="myform">
                
                    <table class="insert-tab" width="100%">
                        <tbody>
						 <tr>
                                <th><i class="require-red">*</i>新闻标题：	
                                <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/>
                                 <input type="hidden" id="id" name="id" value='<%=news.getId()%>'/>
                                </th>
                                <td><input class="common-text" name="title" id="title" type="text" size="50" value='<%=StringUtil.notNull(news.getTitle())%>'>&nbsp;<span id="titleTag"></span></td>
                            </tr>
						<tr>
               		<th width="120px"><i class="require-red">*</i>新闻分类：</th>
               		<td>
                        <select class="common-text" id="sort" name="sort">
                           <option value='1' <%if(news.getSort()==1){ %>selected<%} %>>最新资讯</option>
                           <option value='2' <%if(news.getSort()==2){ %>selected<%} %>>分会动态</option>
                            <option value='3' <%if(news.getSort()==3){ %>selected<%} %>>总部竞赛</option>
                            <option value='4' <%if(news.getSort()==4){ %>selected<%} %>>分部竞赛</option>
                            <option value='5' <%if(news.getSort()==5){ %>selected<%} %>>环球之旅</option>
                            <option value='6' <%if(news.getSort()==6){ %>selected<%} %>>公益足迹</option>
                            <option value='7' <%if(news.getSort()==7){ %>selected<%} %>>善行有我</option>
                            <option value='8' <%if(news.getSort()==8){ %>selected<%} %>>国学传播</option>
                           </select>
                               &nbsp;<span id="sortTag"></span>
                            </td>
               	</tr>
               	<tr>
               		<th width="120px"><i class="require-red">*</i>来源：</th>
               		<td><input class="common-text" name="source" id="source" type="text" size="50" 
               		value='<%=StringUtil.notNull(news.getSource())%>'>&nbsp;<span id="sourceTag"></span>
               		</td>
               	</tr>
                            <tr>
                                <th><i class="require-red">*</i>新闻正文：</th>
                                 <td><textarea id="editor_id" name="contents" style="width:700px;height:300px;"><%=StringUtil.notNull(news.getContents())%></textarea><span id="contentsTag"></span>
								 
								 </td>
                            </tr>
                            
                            <tr>
                                <th></th>
                                <td>
                                    <input id="btn" class="btn btn-primary mr10" value="提交修改" type="button">
                                     <input id="btn1" class="btn"  value="返回上一页" type="button" onclick="history.go(-1);">
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
	

	$("#title").blur(function() {
		if($(this).val()=="")$("#titleTag").text("新闻标题不能为空！").css({"color":"red","font-size":"12px"});
		else if(getByteLen($(this).val())>200) $("#titleTag").text("新闻标题字数不能大于100！").css({"color":"red","font-size":"12px"});
		else $("#titleTag").text("");
	});
	
	$("#contents").blur(function() {
		if($(this).val()=="")$("#contentsTag").text("新闻正文不能为空！").css({"color":"red","font-size":"12px"});
	
		else $("#contentsTag").text("");
	});
	
	$("#source").blur(function() {
		if($(this).val()=="")$("#sourceTag").text("发布来源不能为空！").css({"color":"red","font-size":"12px"});
	
		else $("#sourceTag").text("");
	});
	
	$("#sort").blur(function() {
		if($(this).val()=="")$("#sortTag").text("新闻分类不能为空！").css({"color":"red","font-size":"12px"});
		else $("#sortTag").text("");
	});
	
	
	
	$("#btn").click(function(){
		
		 if (!confirm("确认提交修改新闻！")) {
				window.event.returnValue = false;
			}
			else{
			editor.sync();
				var errorstr = "";
				if($("#title").val()==""){
					$("#titleTag").text("新闻标题不能为空！").css({"color":"red","font-size":"12px"}); 
					errorstr ="1";
				}else if($("#titleTag").text()!=""){
					errorstr ="1";
				}
				
				if($("#sort").val()==""){
					$("#sortTag").text("未定义新闻分类，请在添加分类中添加分类！").css({"color":"red","font-size":"12px"});
					errorstr ="1";
				}else if($("#sortTag").text()!=""){
					errorstr ="1";
				}
				if($("#source").val()==""){
					$("#sourceTag").text("发布来源不能为空！").css({"color":"red","font-size":"12px"}); 
					errorstr ="1";
				}else if($("#contentsTag").text()!=""){
					errorstr ="1";
				}
				
				if($("#contents").val()==""){
					$("#contentsTag").text("正文内容不能为空！").css({"color":"red","font-size":"12px"}); 
					errorstr ="1";
				}else if($("#contentsTag").text()!=""){
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