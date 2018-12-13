<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>
<%@page import="com.ssm.utils.Pager" %>
<%@page import="com.ssm.pojo.Admin" %>
<%@page import="com.ssm.pojo.Promotion" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
String nameKey = StringUtil.notNull((String) request.getAttribute("nameKey"));
if(admin==null) {
out.println("<script>");
out.println("parent.window.location.href='login.jsp';");
out.println("</script>");
}else{
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[9][20].equals("1")||admin.getState().equals("1")){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>促销政策</title>
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
    <script language="JavaScript">
     function delcfm() {
        if (!confirm("确认要删除？")) {
            window.event.returnValue = false;
        }
    }
    
    
	$(function() {
	
	$("#batchDel").click(function(){
	
		if (!confirm("确认要删除？")) {
            window.event.returnValue = false;
        }else{
         $('#delform').attr("action", "admin/basic_data_deleteAll.action");
       		 $('#delform').submit();
        }
	
	});
	
	$("#ckAll").click(function() {
		if(this.checked){ 
			$("input[name='ids']").each(function(){this.checked=true;}); 
		}else{ 
			$("input[name='ids']").each(function(){this.checked=false;}); 
		} 
	});
	
	$("input[name='ids']").click(function() {
    var $ids = $("input[name='ids']");
    $("#ckAll").prop("checked" , $ids.length == $subs.filter(":checked").length ? true :false);
  });
  
   $("#pageSize").bind("propertychange",function() {
    });
});
	</script>
  </head>
  
  <body>
  <div class="container clearfix">
   <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">系统设置
            <span class="crumb-step">&gt;</span><span class="crumb-name">促销政策</span></div>
        </div>
       
        <div class="result-wrap">
           <form name="delform" id="delform"  method="post">
                <div class="result-title">
                    <div class="result-list">
                        <a href="admin/PromotionServlet?method=addPro"><i class="icon-font">&#xe026;</i>新增政策</a>
                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th>参数名称</th>
                            <th>参数1</th>
							 <th>参数2</th>
                            <th>参数3</th>
                             <th>参数4</th>
							 <th>参数5</th>
							 <th>摘要</th>
							 <th>开始时间</th>
							 <th>结算时间</th>
							 <th>状态</th>
                            <th>操作</th>
                        </tr>
                     	<%
								 List<Promotion> coll = ( List<Promotion>)request.getAttribute("coll");
						
							if(coll!=null){
									Iterator it = coll.iterator();
									while(it.hasNext()){
									Promotion pro = (Promotion)it.next();
							%>
					  	<tr>
					  	   <td> <%=StringUtil.notNull(pro.getName()) %></td>
						 <td> <%=StringUtil.decimalFormat(pro.getNum1()) %></td>
						 <td> <%=StringUtil.decimalFormat(pro.getNum2()) %></td>
						 <td> <%=StringUtil.decimalFormat(pro.getNum3()) %></td>
						 <td> <%=StringUtil.decimalFormat(pro.getNum4()) %></td>
						 <td> <%=StringUtil.decimalFormat(pro.getNum5()) %></td>
						  <td> <%=StringUtil.notNull(pro.getSummary()) %></td>
						  <td> <%=StringUtil.parseToString(pro.getStartTime(), DateUtil.yyyyMMddHHmmss) %></td>
						  <td> <%=StringUtil.parseToString(pro.getEndTime(), DateUtil.yyyyMMddHHmmss) %></td>
						  <td> <%if(pro.getState()==1){ %>进行中<%}else{ %>已停用<%} %> </td>
						  <td>
                                <a class="link-update" href="admin/PromotionServlet?method=editPro&id=<%=pro.getId()%>">修改</a>&nbsp;|&nbsp;
                                <a class="link-update" href="admin/PromotionServlet?method=removePro&id=<%=pro.getId()%>" onClick="delcfm()">删除</a>
                            </td>
						 </tr>
						<%}
						}else{ %>
						 <tr>
						 <td colspan="11">添加参数，请点击<a href="admin/Promotionervlet?method=addPro">添加参数。</a></td>
						  </tr>
						 <%} %>
						</table>


					</div>
				</form>
			</div>
		</div>
		<!--/main-->
	</div>
</body>
</html>
<%
}else{
			out.println("<script>");
			out.println("alert('你没有权限进行该操作！')");
			out.println("</script>");
		}
}
%>
