<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.Admin" %>
<%@page import="com.ssm.pojo.Message" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
String token = (String)request.getSession().getAttribute("token");
Message msg = (Message) request.getAttribute("msg");
if(admin==null) {
out.println("<script>");
out.println("parent.window.location.href='login.jsp';");
out.println("</script>");
}else{
	String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[0][2].equals("1")||admin.getState().equals("1")){
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>留言簿</title>
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
     
  </head>
  
  <body>
  <div class="container clearfix">
   <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="admin/bank_account.action">信息管理</a><span class="crumb-step">&gt;</span><span>留言回复</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form action="admin/MessageServlet?method=admin_reply_save"  method="post" id="saveform" name="saveform">
                 <input type="hidden" name="id" value="<%=msg.getId()%>">
                  <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/>
                    <table class="insert-tab" width="100%">
                        <tbody>
                            <tr>
                                <th width="120px"><i class="require-red">*</i>留言时间：</th>
                                <td>
                                    <%=StringUtil.parseToString(msg.getEntryTime(),DateUtil.yyyyMMddHHmmss)%>
                                </td>
                            </tr>
                             <tr>
                                <th><i class="require-red">*</i>留言用户：</th>
                                <td>
                                    <%=StringUtil.notNull(msg.getUserName()) %> (<%=StringUtil.notNull(msg.getUserId()) %>)
                                    </td>
                            </tr>
                             <tr>
                                <th><i class="require-red">*</i>留言信息：</th>
                                <td>
                                   <%=StringUtil.notNull(msg.getUserMsg())%>
                                    </td>
                            </tr>
                             <tr>
                                <th><i class="require-red">*</i>回复内容：</th>
                                <td>
                                    <textarea id="adminMsg" name="adminMsg" style="width:60%;height:100px;overflow:scroll;resize:none;"></textarea> &nbsp;<span id="msgTag"></span>
                                </td>
                            </tr>
                            <tr>
                                <th></th>
                                <td>
                                    <input id="sbtn" class="btn btn-primary mr10" value="回复" type="button">
                                    <input class="btn" onClick="history.go(-1)" value="返回" type="button">
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
	
   $("#adminMsg").blur(function(){
   	 if($("#adminMsg").val()==""){
				$("#msgTag").text("发表内容不能为空！").css({"color":"red","font-size":"12px"});
	}
	else if(getByteLen($("#adminMsg").val())>200) $("#msg").text("发表内容字数不能大于100！").css({"color":"red","font-size":"12px"});
	else $("#msgTag").text("");
   });
   
	$("#sbtn").click(function() {
	if (!confirm("确认要发布新的留言？")) {
								return false;
		} else {
	var errorstr ="";
			if($("#adminMsg").val()==""){
					$("#msgTag").text("发表内容不能为空！").css({"color":"red","font-size":"12px"}); 
					errorstr =errorstr+"1";
				
			}	else if(getByteLen($("#adminMsg").val())>200) {
				$("#msgTag").text("发表内容字数不能大于100！").css({"color":"red","font-size":"12px"});
				errorstr =errorstr+"1";
			}
			else if($("#msgTag").text()!=""){
				errorstr =errorstr+"1";
			}
		
		if(errorstr!=""){
			return false;
		}else{
       		 $('#saveform').submit();
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