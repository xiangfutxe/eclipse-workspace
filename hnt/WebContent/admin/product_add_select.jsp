<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.Product" %>
<%@page import="com.ssm.pojo.ProductType" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>
<%@page import="com.ssm.utils.Pager" %>
<%@page import="com.ssm.pojo.Admin" %>
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
		if(rankstr[3][2].equals("1")||admin.getState().equals("1")){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>产品列表</title>
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
					var $num = $("input[name=numstr]");
					var $price = $("input[name=pricestr]");
					var $pv = $("input[name=pvstr]");
					var totalprice = 0;
					var totalpv = 0;
					var err=0;
					for ( var i = 0; i < $num.size(); i++) {
						var n = 0;
						var p = 0;
						var v = 0;
						if ($num[i].value == ""||$num[i].value == 0){
							n = 0;
						}
						else if(!reg.test($num[i].value)){
							err =1;
							n=0;
						}
						else
							n = $num[i].value;
						if ($price[i].value == "")
							p = 0;
						else
							p = $price[i].value;
						if ($pv[i].value == "")
							v = 0;
						else
							v = $pv[i].value;
						totalprice = totalprice + n * p;
						totalpv = totalpv+n*v;
					}
					if(err ==1)	$("#msg").text("产品数量的格式有误，请重新核对！").css({"color":"red","font-size":"12px"});
					else{
					 	$("#msg").text("");
						$("#totalPrice").text(Math.round(totalprice*100)/100).css({"color":"green","font-size":"12px"});
						$("#totalPv").text(Math.round(totalpv*100)/100).css({"color":"green","font-size":"12px"});
						$("#tprice").val(Math.round(totalprice*100)/100);
						$("#tpv").val(Math.round(totalpv*100)/100);
					}
		});
		
		 $("#btn2").bind("click", function() {
		 var error ="";
					if($("#tprice").val()==""){
						$("#msg").text("请先计算产品合计金额！").css({"color":"red","font-size":"12px"});
					error ="1";
					}else if($("#tprice").val()<=0){
					$("#msg").text("产品合计金额必须大于零！").css({"color":"red","font-size":"12px"});
					error ="1";
					}
					if(error==""){
					 $('#delform').submit();
					 return true;
					}else return false;
		});
		
});
	</script>
  </head>
  
  <body>
  <div class="container clearfix">
   <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">产品管理</span>
            <span class="crumb-step">&gt;</span><span class="crumb-name">添加产品</span>
            <span class="crumb-step">&gt;</span><span class="crumb-name">组合产品选择</span></div>
        </div>
      
        <div class="result-wrap">
           <form name="delform" id="delform"  method="post" action="admin/ProductServlet?method=save_select">
               <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th>ID</th>
                            <th>名称</th>
                            <th>规格</th>
                            <th>分类</th>
                            <th>单价</th>
                            <th>PV</th>
                            <th>数量</th>
                        </tr>
                        <%
 							List<Product> coll = (List<Product>)request.getAttribute("coll_pt");
						
							if(coll!=null){
									for(int i=0;i<coll.size();i++){
									Product p = coll.get(i);
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
						  <td>
						  <%=p.getPv()%><input type="hidden"
											name="pvstr" value='<%=p.getPv()%>'>
						 </td>
						 <td><input class="common-text required" type="text" name='numstr' value="0">
						 <input
											type="hidden" name="pid" value='<%=p.getId()%>'>
										</td>
						 </tr>
							<%}%>
							<tr>
							 <td colspan="4">合计：</td>
							 <td><span id="totalPrice"></span></td>
							 <td><span id="totalPv"></span></td>
							 <td>-</td>
							 </tr>
							<%
						}else{ %>
						 <tr>
						 <td colspan="6">暂无产品信息。</td>
						  </tr>
						 <%} %>
						 <tr><td colspan="7"> <input id="btn1" class="btn btn-primary mr10"
									value="计算组合" type="button"/>&nbsp;<input id="btn2" class="btn btn-primary mr10"
									value="保存组合产品" type="button"/></td></tr>
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
