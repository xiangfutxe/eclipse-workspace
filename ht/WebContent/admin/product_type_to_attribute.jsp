<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.ProductTypeToAttribute" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.utils.Pager" %>
<%@page import="com.pojo.Admin" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
  String  typeId = (String)request.getSession().getAttribute("typeId");
if(admin==null) {
out.println("<script>");
out.println("parent.window.location.href='login.jsp';");
out.println("</script>");
}else{
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[3][2].equals("1")||admin.getState().equals("1")){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>分类属性</title>
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
		
         $('#delform').attr("action", "admin/ProductTypeServlet?method=type_list");
       		 $('#delform').submit();
	
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
            <span class="crumb-step">&gt;</span><span class="crumb-name">分类属性</span>
            </div>
        </div>
      
       
        <div class="result-wrap">
           <form name="delform" id="delform"  method="post">
                <div class="result-title">
                    <div class="result-list">
                        <a href="admin/ProductTypeServlet?method=type_to_attribute_add"><i class="icon-font">&#xe026;</i>新增属性</a>
                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="97%">
                        <tr>
                         <th>序号</th>
                            <th>属性名称</th>
                            <th>录入时间</th>
                            <th>最后修改时间</th>
                            <th>操作</th>
                        </tr>
                        <%
							
							Collection coll =(Collection)request.getAttribute("coll");
							if(coll!=null){
									Iterator it = coll.iterator();
									int t= 0;
									while(it.hasNext()){
									ProductTypeToAttribute pt = (ProductTypeToAttribute)it.next();
									t++;
							%>
					  	<tr>
						 	  <td> <%=t%></td>
						 <td> <%=pt.getAttributeName()%></td>
						  <td> <%=StringUtil.parseToString(pt.getEntryTime(), DateUtil.yyyyMMddHHmmss)%></td>
						 <td> <%=StringUtil.parseToString(pt.getEndTime(), DateUtil.yyyyMMddHHmmss)%></td>
						  <td>   
						  <a
										class="link-del"
										href="admin/ProductTypeServlet?method=type_to_attribute_value_list&id=<%=pt.getAttributeId()%>">属性值管理</a>
										&nbsp;|&nbsp;
						  <a
										class="link-del"
										href="admin/ProductTypeServlet?method=type_to_attribute_del&id=<%=pt.getId()%>" onclick="if(confirm('确定删除该产品属性信息?')==false)return false;">删除</a>
										
                            </td>
						 </tr>
			<%}
						}else{ %>
						 <tr>
						 <td colspan="5">暂无属性值信息，请点击<a href="admin/ProductTypeServlet?method=type_to attribute_add">添加分类属性。</a></td>
						  </tr>
						 <%} %>
                    </table>
                    <br>
                     <input class="btn btn-info" id="reback" value="分类列表" type="button">
                   
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
