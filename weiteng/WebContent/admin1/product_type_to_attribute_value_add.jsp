<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.ProductAttributeClassify" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.utils.Pager" %>
<%@page import="com.pojo.Admin" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
  String  typeAttributeId = (String)request.getSession().getAttribute("typeAttributeId");
  
    String  token = (String)request.getSession().getAttribute("token");
if(admin==null) {
out.println("<script>");
out.println("parent.window.location.href='login.jsp';");
out.println("</script>");
}else{
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[3][1].equals("1")||admin.getState().equals("1")){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>属性值列表</title>
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
	
	$("#reback").click(function(){
	
         $('#delform').attr("action", "admin/ProductTypeServlet?method=type_to_attribute_value_list");
       	 $('#delform').submit();
       
	
	});
	
	$("#sub").click(function(){
	
		if (!confirm("确认要提交保存？")) {
            return false;
        }else{
         $('#delform').attr("action", "admin/ProductTypeServlet?method=type_to_attribute_value_save");
       		 $('#delform').submit();
       		  return true;
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
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a>
            <span class="crumb-step">&gt;</span><span class="crumb-name">分类管理</span>
            <span class="crumb-step">&gt;</span><span class="crumb-name">属性选择</span>
             <span class="crumb-step">&gt;</span><span class="crumb-name">添加属性值</span>
            </div>
        </div>
      
       
        <div class="result-wrap">
           <form name="delform" id="delform"  method="post">
           <input id="id" name="id" type="hidden" value="<%=typeAttributeId %>"/>
           <input id="token" name="token" type="hidden" value="<%=token %>"/>
                <div class="result-content">
                    <table class="result-tab" width="97%">
                    <tr>
                     <th>序号</th>
                            <th>属性名称</th>
                            </tr>
                        <%
                        
							Collection coll = (Collection)request.getAttribute("coll");
							if(coll!=null){
									Iterator it = coll.iterator();
									int t= 0;
									while(it.hasNext()){
									ProductAttributeClassify pt = (ProductAttributeClassify)it.next();
									t++;
							%>
					   	<tr>
					  	<td class="tc"><input name="ids" type="checkbox" value="<%=pt.getId()%>"></td>
						 	
						 <td> <%=pt.getValue()%></td>
						
						 </tr>
			<%}
						}else{ %>
						 <tr>
						 <td colspan="2">暂无属性值信息，请点击<a href="admin/ProductTypeServlet?method=attribute_add">添加产品属性。</a></td>
						  
						 <%} %>
						 </tr>
                    </table>
                  <br>
                  <input class="btn btn-primary" id="sub" value="提交保存" type="button">&nbsp;
                   <input class="btn btn-info" id="reback" value="属性列表" type="button">
                  
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
