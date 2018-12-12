<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.Product" %>
<%@page import="com.pojo.ProductType" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.utils.Pager" %>
<%@page import="com.pojo.Admin" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
String type = (String)request.getAttribute("type");
String token = (String)request.getSession().getAttribute("token");
if(admin==null) {
out.println("<script>");
out.println("parent.window.location.href='login.jsp';");
out.println("</script>");
}else{
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[5][8].equals("1")||admin.getState().equals("1")){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>新店购物</title>
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
	var reg = /^[0-9]*]*$/;
	$("#batchDel").click(function(){
	
		if (!confirm("确认要删除？")) {
            return  false;
        }else{
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
    
    $("#btn1").bind("click", function() {
			$('#delform').attr("action","admin/OrderServlet.do?method=admin_order_new_branch_shop");
			$('#delform').submit();
		});
		
		 $("#btn2").bind("click", function() {
					var $num = $("input[name=numstr]");
					var err=0;
					for ( var i = 0; i < $num.size(); i++) {
						if ($num[i].value == ""){
							err =1;
						}
						else if(!reg.test($num[i].value)){
							err =1;
						}
					}
					if(err==0){
						 $('#delform').attr("action","admin/OrderServlet.do?method=admin_order_new_branch_shop_save");
						 $('#delform').submit();
					 return true;
					}else{
					alert("数据有误,请核对");
					 return false;
					 }
		});
		
});
	</script>
  </head>
  
  <body>
  <div class="container clearfix">
   <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a>
            <span class="crumb-step">&gt;</span><span class="crumb-name">订单管理</span>
            <span class="crumb-step">&gt;</span><span class="crumb-name">新店配货</span>
            <span class="crumb-step">&gt;</span><span class="crumb-name">选购产品</span></div>
        </div>
      
        <div class="result-wrap">
           <form name="delform" id="delform"  method="post" >
               <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
                <div class="result-content">
                店铺编号：<input type="text" class="common-text required" id="code" name="code"><span id="msg"></span><span id="msg1"></span>
                   <br>
                   <br>
                    <table class="result-tab" width="100%">
                        <tr>
                            <th>ID</th>
                            <th>名称</th>
                            <th>规格</th>
                            <th>分类</th>
                            <th>单价</th>
                            <th>数量</th>
                        </tr>
                        <%
 Collection coll = (Collection)request.getAttribute("coll");
						
							if(coll!=null){
									Iterator it1 = coll.iterator();
									while(it1.hasNext()){
									Product p = (Product)it1.next();
							%>
					  	<tr>
						 <td> <%=StringUtil.notNull(p.getProductId())%> </td>
						  <td> <%=StringUtil.notNull(p.getProductName())%> </td>
						   <td> <%=StringUtil.notNull(p.getSpecification())%></td>
					 <td> <%=StringUtil.notNull(p.getProductType())%></td>
						   <td>
						  <%=p.getPrice()%><input type="hidden"
											name="pricestr" value='<%=p.getPrice()%>'>
						 </td>
						 <td><input class="common-text required" type="text" name='numstr' value="0">
						 <input
											type="hidden" name="pid" value='<%=p.getId()%>'>
										</td>
						 </tr>
							<%}%>
							<%
						}else{ %>
						 <tr>
						 <td colspan="6">暂无产品信息。</td>
						  </tr>
						 <%}%>
						 <tr><td colspan="6"> <input id="btn1" class="btn btn-primary mr10"
									value="返回列表" type="button"/>&nbsp;<input id="btn2" class="btn btn-primary mr10"
									value="生成订单" type="button"/></td></tr>
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
