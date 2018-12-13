<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.pojo.Admin" %>
<%@page import="com.pojo.TimeParam" %>
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
		if(admin.getState().equals("1")){
TimeParam tp = (TimeParam)request.getAttribute("timeparam");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>时间参数</title>
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
});
</script>
  </head>
  
  <body>
  <div class="container clearfix">
   <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="addmin/index.jsp">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="admin/inventory.action">系统管理</a><span class="crumb-step">&gt;</span><span>时间参数设置</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form  method="post" id="myform" name="myform">
                <input type="hidden" id="id" name="id" value='<%=tp.getId()%>' />
<input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
                    <table class="insert-tab" width="100%">
                        <tbody>
                            <tr>
                                <th width="150px"><i class="require-red">*</i>参数名称：</th>
                                <td>
                                    <input class="common-text required" id="paramName" name="paramName"  value='<%=tp.getParamName()%>' type="text" readonly>&nbsp;<span id="nameTag"></span>
                                </td>
                            </tr>
                             <tr>
                                <th><i class="require-red">*</i>周结算起始时间：</th>
                                <td>
                                    <input class="common-text required" id="startTime" name="startTime" value='<%=StringUtil.parseToString(tp.getStartTime(), DateUtil.yyyyMMddHHmmss)%>' type="text" />&nbsp;<span id="timeTag"></span>
                                </td>
                            </tr>
                             <tr>
                                <th><i class="require-red">*</i>周相隔时间：</th>
								
                                <td>
                                    <input class="common-text required" id="weekNum" name="weekNum" value='<%=tp.getWeekNum()%>'  type="text" />&nbsp;<span id="weekTag"></span>
                                </td>
                            </tr>
                             <tr>
                                <th><i class="require-red">*</i>月所包括周数：</th>
                                <td>
                                    <input class="common-text required" id="monthNum" name="monthNum" value='<%=tp.getMonthNum()%>'  type="text" />&nbsp;<span id="monthTag"></span>
                                </td>
                            </tr>
                             <tr>
                                <td colspan="2"><i class="require-red">*</i>备注说明：周相隔时间为实数，单位为小时，例如可输入0.5，即相隔半小时；月所包括周数必须为整数！</td>
                               
                            </tr>
                            <tr>
                                <th></th>
                                <td>
                                    <input id="sbtn" class="btn btn-primary  mr10" value="保存" type="button">
                                    <input id="addbtn" class="btn mr10" value="生成结算表" type="button">
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
	$('startTime').calendar({ format:'yyyy-MM-dd HH:mm:ss' });
	
	
	$("#sbtn").click(function(){
		  if (!confirm("确认提交保存修改？")) {
				window.event.returnValue = false;
			}else{
		var errorstr = "";
	
		if(errorstr!=""){
			return false;
		}else{
		 $('#myform').attr("action","admin/TimeParamServlet.do?method=update");
       		 $('#myform').submit();
       		 return true;
		
		}
			}
	});
	
	$("#addbtn").click(function(){
		  if (!confirm("确认生成结算表？")) {
				return false;
			}else{
			$('#myform').attr("action","admin/TimeParamServlet.do?method=addSettle");
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
