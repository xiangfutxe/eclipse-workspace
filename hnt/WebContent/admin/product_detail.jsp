<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.Product" %>
<%@page import="com.ssm.pojo.ProductDetail" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>
<%@page import="com.ssm.utils.Pager" %>
<%@page import="com.ssm.pojo.Admin" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
Product product = (Product)request.getAttribute("product");
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
    
    <title>产品详情</title>
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
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="admin/ProductServlet.do?method=list">产品管理</a><span class="crumb-step">&gt;</span><span>产品详情</span>
			  <span class="crumb-step">&gt;</span><span><a href="javascript:history.go(-1);">返回上一页</a></span></div>
        </div>
        <div class="result-wrap">
        	<div class="result-title">
                <h1>产品基本信息</h1>
            </div>
            <div class="result-content">
            <ul class="sys-info-list">
             <li>
                        <label class="res-lab">产品图片：</label><span class="res-info"><img width="100" height="100" src="<%=product.getImageUrl()%>"/></span>
                    </li>
                    <li>
                        <label class="res-lab">产品分类：</label><span class="res-info"><%=product.getProductType()%></span>
                    </li>
                    <li>
                        <label class="res-lab">产品ID：</label><span class="res-info"><%=product.getProductId()%></span>
                    </li>
                    <li>
                        <label class="res-lab">产品名称：</label><span class="res-info"><%=product.getProductName()%></span>
                    </li>
                    <li>
                        <label class="res-lab">产品规格：</label><span class="res-info"><%=product.getSpecification()%></span>
                    </li>
                      <li>
                        <label class="res-lab">赠品类型：</label><span class="res-info">
						<%if(product.getGiveType()==0){ %>不属于赠品
						<%}else if(product.getGiveType()==1){  %>一类赠品
						<%}else if(product.getGiveType()==2){  %>二类赠品
						<%}else if(product.getGiveType()==3){  %>三类赠品
						<%} %>
						</span>
                    </li>
                    <li>
                        <label class="res-lab">产品单价：</label><span class="res-info"><%=product.getPrice()%>元</span>
                    </li>
                     <li>
                        <label class="res-lab">产品PV：</label><span class="res-info"><%=product.getPv()%>元</span>
                    </li>
                    
                     <li>
                        <label class="res-lab">赠送搭配：</label><span class="res-info"><%=StringUtil.notNull(product.getGiveProductName())%>(<%=StringUtil.notNull(product.getGiveProductId())%>)</span>
                    </li>
                     <li>
                        <label class="res-lab">买赠活动：</label><span class="res-info"><%if(product.getGiveNum()>0){%>买<%=product.getGiveNum() %>赠1<%}else{ %>无<%} %></span>
                    </li>
					  <li>
                        <label class="res-lab">产品介绍：</label><span class="res-info"><%=product.getFeatures()%></span>
                    </li>
            </ul>
            </div>
        </div>
        <%if(product.getType()==2) {%>
         <div class="result-wrap">
		<div class="result-title">
                <h1>产品清单</h1>
            </div>
         
                <div class="result-content">
                    <table class="result-tab" width="99%">
                        <tr>
                            <th>ID</th>
                            <th>名称</th>
                            <th>规格</th>
                            <th>分类</th>
                            <th>单价</th>
                            <th>PV值</th>
                            <th>数量</th>
                             <th>金额小计</th>
                            <th>PV小计</th>
                        </tr>
                        <%
							List<ProductDetail> coll =(List<ProductDetail>)request.getAttribute("coll");;
							if(coll!=null){
									for(int i=0;i<coll.size();i++){
									ProductDetail p = coll.get(i);
							%>
					  	<tr>
						 <td> <%=StringUtil.notNull(p.getProductId())%> </td>
						  <td> <%=StringUtil.notNull(p.getProductName())%> </td>
						   <td> <%=StringUtil.notNull(p.getSpecification())%></td>
					 <td> <%=StringUtil.notNull(p.getProductType())%></td>
						   <td>
						  <%=p.getProductPrice()%></td>
						 <td > <%=p.getProductPv()%></td>
						 <td > <%=p.getNum()%>
						 </td>
						 <td > <%=p.getPrice()%></td>
						  <td > <%=p.getPv()%>
						 </td>
						  
						 </tr>
							<%}
						}else{ %>
						 <tr>
						 <td colspan="9">暂无产品信息。</td>
						  </tr>
						 <%} %>
                    </table>
                     </div>
                     </div>
                     <%} %>
    </div>
    <!--/main-->
    </div>
  </body>
  
 <script language="JavaScript">
$(function() {
	$("#productId").change(function() {
		$.ajax({
				type : "post",
				url : "admin/checkPid.action",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
				data : {//设置数据源
					productId : $("input[name=productId]").val()
				//这里不要加","  不然会报错，而且根本不会提示错误地方

				},

				dataType : "json",//设置需要返回的数据类型
				success : function(data) {
					var d = eval("(" + data + ")");//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
					//得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
					
					if(d.tag=="0") $("#proudctIdTag").text("产品编号不能为空！").css({"color":"red","font-size":"9px"});
					else if(d.tag=="1")  $("#proudctIdTag").text("产品编号已存在！").css({"color":"red","font-size":"9px"});
					else if(d.tag=="2")  $("#proudctIdTag").text("此产品编号可用！").css({"color":"green","font-size":"9px"});
					else $("#proudctIdTag").text("");
				},
				error : function() {
					alert("系统异常，请稍后重试！");
				}//这里不要加","
				
			});
	});
	$("#name").blur(function() {
		if($("#name").val()=="")$("#nameTag").text("产品名称不能为空！").css({"color":"red","font-size":"9px"});
	});
	$("#price").blur(function() {
		if($("#price").val()=="")$("#priceTag").text("产品单价不能为空！").css({"color":"red","font-size":"9px"});
	});
	$("#pv").blur(function() {
		if($("#pv").val()=="")$("#pvTag").text("产品PV不能为空！").css({"color":"red","font-size":"9px"});
	});
	
	$("#pbtn").click(function() {
		window.location.href="admin/product.action";
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