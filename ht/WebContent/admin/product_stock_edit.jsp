<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.ProductStock" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.utils.Pager" %>
<%@page import="com.pojo.Admin" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
String code = (String)request.getAttribute("code");
if(admin==null) {
out.println("<script>");
out.println("parent.window.location.href='login.jsp';");
out.println("</script>");
}else{
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[3][0].equals("1")||admin.getState().equals("1")){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>分类管理</title>
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
            return false;
        }
    }
    
    
	$(function() {
	
	$("#sbtn").click(function() {
		if (!confirm("确认提交保存修改？")) {
	   return false;
        }else{
		   $('#sform').attr("action", "admin/ProductServlet?method=admin_product_stock_update");
       		 $('#sform').submit();
       		  return true;
       	 }
	});
	
	$("#batchDel").click(function(){
	
		if (!confirm("确认要删除？")) {
            window.event.returnValue = false;
        }else{
         $('#delform').attr("action", "admin/ProductTypeServlet?method=delAll");
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
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a>
            <span class="crumb-step">&gt;</span><span class="crumb-name">产品管理</span>
            <span class="crumb-step">&gt;</span><span class="crumb-name">库存修改</span>
            </div>
        </div>
       
        <div class="result-wrap">
           <form name="sform" id="sform"  method="post">
           <input type="hidden" id="code" name="code" value="<%=code%>"/>
                <div class="result-title">
                    <div class="result-list">
                        <a href="admin/ProductServlet?method=admin_product_stock&code=<%=code%>"><i class="icon-font">&#xe026;</i>返回库存列表</a>

                    </div>
                </div>
             
                <div class="result-content">
                    <table class="result-tab" width="97%">
                        <tr>
                         <th>序号</th>
                            <th>产品编号</th>
                            <th>产品名称</th>
                             <th>产品属性</th>
                             <th>当前库存</th>
                             <th>累计库存</th>
                            <th>修改时间</th>
                        </tr>
                        <%
							Collection coll = (Collection)request.getAttribute("coll");
							if(coll!=null){
									Iterator it = coll.iterator();
									int t= 0;
									while(it.hasNext()){
									ProductStock pt = (ProductStock)it.next();
									t++;
							%>
					  	<tr>
						 	  <td> <%=t%></td>
						 <td> <%=StringUtil.notNull(pt.getProductId())%></td>
						  <td> <%=StringUtil.notNull(pt.getProductName())%></td>
						   <td> <%=StringUtil.notNull(pt.getAttribute())%></td>
						     <td> <input type="text" size="5" name="num<%=pt.getId()%>" value="<%=pt.getNum()%>"  class="common-text required"
						     onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'0');}else{this.value=this.value.replace(/\D/g,'');}"
 onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'0');}else{this.value=this.value.replace(/\D/g,'');}"/></td>
						       <td> <%=pt.getTolNum()%></td>
						 <td> <%=StringUtil.parseToString(pt.getEndTime(), DateUtil.yyyyMMddHHmmss)%></td>
						
						 </tr>
			<%}%>
			 <tr>
						 <th colspan="7"><input  class="btn btn-primary" type="button" id="sbtn" value="保存修改"/></th>
						  </tr>
				<% 	}else{ %>
						 <tr>
						 <td colspan="7">暂无产品库存信息。</td>
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
