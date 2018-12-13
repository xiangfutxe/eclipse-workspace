<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.pojo.Admin" %>
<%@page import="com.pojo.Promotion" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
String token = (String)request.getSession().getAttribute("token");
Promotion param = (Promotion) request.getAttribute("promotion");
if(admin==null) {
out.println("<script>");
out.println("parent.window.location.href='login.jsp';");
out.println("</script>");
}else{
	String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[9][1].equals("1")||admin.getState().equals("1")){
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>网站设置</title>
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
     <script type="text/javascript" src="js/lhgcalendar.min.js"></script>
      <script type="text/javascript">
	 $(function(){
    $('#startTime').calendar({ format:'yyyy-MM-dd HH:mm:ss' });

$('#endTime').calendar({ format:'yyyy-MM-dd HH:mm:ss' });
});
</script>
  </head>
  
  <body>
  <div class="container clearfix">
   <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span>基础设置<span class="crumb-step">&gt;</span><span>网站设置</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form method="post" id="myform" name="myform">
                <input type="hidden" id="id" name="id" value='<%=param.getId() %>' />
                	<input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/>
                    <table class="insert-tab" width="100%">
                        <tbody>
                         <tr>
                                <td colspan="2">前台设置</td>
                                
                            </tr>
                            <tr>
                                <th width="120px"><i class="require-red">*</i>前台开关：</th>
                                <td>
                                <select class="common-text required" id="amount_1" name="amount_1">
                                <option value="1" <%if(param.getAmount_1()==1){%>selected<%} %> >开通</option>
                                <option  value="0" <%if(param.getAmount_1()==0){%>selected<%} %>>关闭</option>
                                </select>
                                   &nbsp;<span id="amount_1Tag"></span>
                                </td>
                            </tr>
                              <tr>
                                <th width="120px"><i class="require-red">*</i>前台留言：</th>
                                <td>
                                   <textarea rows="5" cols="30" id="comments" name="comments"><%=StringUtil.notNull(param.getComments()) %></textarea>
                                </td>
                            </tr>
                             <tr>
                                <td colspan="2">促销政策：</td>
                              
                            </tr>
                                <tr>
                                <th  width="120px">起点节点：
                                </th>
                                <td><input  class="common-text required"  type="text" name="userId" id="userId" value="<%=param.getUserId()%>"/><span id="nameTag"></span></td>
                                </tr>
                                 <tr>
                                <th  width="120px">起止时间：
                                </th>
                                <td><input  class="common-text required"  type="text" name="startTime" id="startTime" value="<%=StringUtil.parseToString(param.getStartTime(), DateUtil.yyyyMMddHHmmss)%>"/>
                                -<input  class="common-text required"  type="text" name="endTime" id="endTime" value="<%=StringUtil.parseToString(param.getEndTime(), DateUtil.yyyyMMddHHmmss)%>"/>
                                <span id="startTimeTag"></span><span id="endTimeTag"></span></td>
                                </tr>
                             <tr>  <th  width="120px">促销内容：
                                </th>
                                <td>
<input type="radio" id="amount_2" name="amount_2" value="0" <%if(param.getAmount_2()==0) {%>checked<%}%>/>暂无&nbsp;<input type="radio" id="amount_2" name="amount_2" value="1" <%if(param.getAmount_2()==1) {%>checked<%}%>/>银级升金级&nbsp;<input type="radio" id="amount_2" name="amount_2" value="2" <%if(param.getAmount_2()==2) {%>checked<%}%>/>银级升白金级
&nbsp;<input type="radio" id="amount_2" name="amount_2" value="3" <%if(param.getAmount_2()==3) {%>checked<%}%>/>银级升钻级
<br/>
<input type="radio" id="amount_3" name="amount_3" value="0" <%if(param.getAmount_3()==0) {%>checked<%}%>>暂无&nbsp;
<input type="radio" id="amount_3" name="amount_3" value="1" <%if(param.getAmount_3()==1) {%>checked<%}%>>金级升白金级&nbsp;
<input type="radio" id="amount_3" name="amount_3" value="2" <%if(param.getAmount_3()==2) {%>checked<%}%>>金级升钻级
<br/>
<input type="radio" id="amount_4" name="amount_4" value="0" <%if(param.getAmount_4()==0) {%>checked<%}%>>暂无
<input type="radio" id="amount_4" name="amount_4" value="1" <%if(param.getAmount_4()==1) {%>checked<%}%>>白金级升钻级<br/>
								</td>
                               
                            </tr>
                             
                            <tr>
                                <th></th>
                                <td>
                                    <input id="sbtn" class="btn btn-primary" value="提交修改" type="button">
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

	var reg = /^[0-9]\d*$/;
	
	
	$("#amount_1").blur(function() {
		if($(this).val()==""){
		$("#amount_1Tag").text("参数值不能为空！").css({"color":"red","font-size":"12px"}); 
	}else if(!reg.test($(this).val())) $("#amount_1Tag").text("参数值格式有误！").css({"color":"red","font-size":"12px"}); 
	else $("#amount_1Tag").text("");
	});	
	
	$("#userId").blur(function() {
		if($(this).val()==""){
		$("#nameTag").text("节点值不能为空！").css({"color":"red","font-size":"12px"}); 
	}else $("#nameTag").text("");
	});	
	
	
	
  	 
  var a = /^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)\s+([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$/;
			$("#startTime").blur(function() {
	if($(this).val()==""){
		$("#startTimeTag").text("开始时间不能为空！").css({"color":"red","font-size":"12px"}); 
	}else if(!a.test($(this).val())) $("#startTimeTag").text("开始时间格式有误！").css({"color":"red","font-size":"12px"}); 
	else $("#startTimeTag").text("");
	});		
	
	$("#endTime").blur(function() {
	if($(this).val()==""){
		$("#endTimeTag").text("结束时间不能为空！").css({"color":"red","font-size":"12px"}); 
	}else if(!a.test($(this).val())) $("#endTimeTag").text("结束时间格式有误！").css({"color":"red","font-size":"12px"}); 
	else $("#endTimeTag").text("");
	});										
											
	$("#sbtn").click(function(){
		var errorstr = "";
		if($("#userId").val()==""){
			$("#nameTag").text("节点不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr=1;
		}else if($("#nameTag").text()!=""){
			errorstr =1;
		}
		
		if($("#amount_1").val()==""){
			$("#amount_1Tag").text("参数值不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}else if($("#amount_1Tag").text()!=""){
			errorstr ="1";
		}
		
		if($("#startTime").val()==""){
			$("#startTimeTag").text("开始时间不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}else if($("#startTimeTag").text()!=""){
			errorstr ="1";
		}
		
		if($("#endTime").val()==""){
			$("#startTimeTag").text("结束时间不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}else if($("#endTimeTag").text()!=""){
			errorstr ="1";
		}
		
		if(errorstr!=""){
			return false;
		}else{
			$('#myform').attr("action", "admin/PromotionServlet.do?method=update");
	       	$('#myform').submit();
       		 return true;
		
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