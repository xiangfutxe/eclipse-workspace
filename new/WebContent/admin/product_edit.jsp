<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.Product" %>
<%@page import="com.pojo.ProductType" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.pojo.Admin" %>
<%@page import="com.pojo.Team" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
String token = (String)request.getSession().getAttribute("token");
Product product = (Product)request.getAttribute("product");
if(admin==null) {
out.println("<script>");
out.println("parent.window.location.href='login.jsp';");
out.println("</script>");
}else{
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[4][2].equals("1")||admin.getState().equals("1")){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>产品修改</title>
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
        <script charset="utf-8" src="kindeditor/kindeditor-all.js"></script>
<script>
KindEditor.ready(function(K) {
	window.editor=K.create('#editor_id', {
		  uploadJson : 'kindeditor/jsp/upload_json.jsp',
                fileManagerJson : 'kindeditor/jsp/file_manager_json.jsp',
                allowFileManager : true
	});
});
        
</script>
  </head>
  
  <body>
  <div class="container clearfix">
   <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a>
            <span class="crumb-step">&gt;</span>产品管理<span class="crumb-step">&gt;</span><span>产品修改</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form action="admin/ProductServlet.do?method=update" method="post" id="myform" name="myform">
               <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
                <input type="hidden" name="id" id="id" value="<%=product.getId()%>"/> 
                    <table class="insert-tab" width="99%">
                        <tbody>
                        <tr>  <th><i class="require-red">*</i>产品类型：</th><td>
                      
                         <input name="type" type="radio" value="1" <%if(product.getType()==1){ %>checked<%} %>/>单品 &nbsp;<input name="type" type="radio" value="2"  <%if(product.getType()==2){ %>checked<%} %>/>套装<span id="typeTag1"></span>
                       
                       <span id="typeTag1"></span>
                       </td></tr>
                            <tr>
                                <th><i class="require-red">*</i>产品代码：</th>
                                <td>
                                    <input class="common-text required" id="productId" name="productId" size="50" value="<%=product.getProductId() %>" type="text" readonly>&nbsp;<span id="proudctIdTag"></span>
                                </td>
                            </tr>
                            <tr>
                                <th><i class="require-red">*</i>产品名称：</th>
                                <td><input class="common-text" name="productName" id="productName" size="50" type="text" value="<%=product.getProductName() %>">&nbsp;<span id="nameTag"></span></td>
                            </tr>
                              <tr>
                                <th>产品规格：</th>
                                <td><input class="common-text" name="specification" id="specification" size="50" type="text" value="<%=product.getSpecification()%>"></td>
                            </tr>
                            <tr>
                                <th><i class="require-red">*</i>零售价：</th>
                                <td><input class="common-text" name="price" id="price" size="50" type="text" value="<%=product.getPrice()%>">&nbsp;<span id="priceTag"></span></td>
                            </tr>
                             <tr>
                                <th><i class="require-red">*</i>代理价：</th>
                                <td><input class="common-text" name="price1" id="price1" size="50" type="text" value="<%=product.getPrice1()%>">&nbsp;<span id="price1Tag"></span></td>
                            </tr>
                             <tr>
                                <th><i class="require-red">*</i>推荐奖励：</th>
                                <td><input class="common-text" name="award" id="award" size="50" type="text" value="<%=product.getAward()%>">&nbsp;<span id="awardTag"></span></td>
                            </tr>
                           
                              <tr>
                                <th><i class="require-red">*</i>物流费：</th>
                                <td><input class="common-text" name="fee" id="fee" size="50" type="text" value="<%=product.getFee()%>">&nbsp;<span id="feeTag"></span></td>
                            </tr>
                            
							 <tr>
                                <th><i class="require-red">*</i>产品介绍：</th>
                                <td><textarea id="editor_id" name="features" style="width:700px;height:300px;"><%=StringUtil.notNull(product.getFeatures())%></textarea>&nbsp;<span id="featuresTag"></span></td>
                            </tr>
                         
                            <tr>
                                <th></th>
                                <td>
                                    <input class="btn btn-primary mr10" value="确定修改" type="submit">
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
 var reg = /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/;
$(function() {
	$("#productId").change(function() {
		$.ajax({
				type : "post",
				url : "admin/ProductServlet.do?method=isExit",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
				data : {//设置数据源
					productId : $("input[name=productId]").val()
				//这里不要加","  不然会报错，而且根本不会提示错误地方

				},

				dataType : "json",//设置需要返回的数据类型
				success : function(data) {
					var d = eval("(" + data + ")");//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
					//得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
					
					if(d.tag=="0") $("#proudctIdTag").text("产品编号不能为空！").css({"color":"red","font-size":"12px"});
					else if(d.tag=="1")  $("#proudctIdTag").text("产品编号已存在！").css({"color":"red","font-size":"12px"});
					else if(d.tag=="2")  $("#proudctIdTag").text("此产品编号可用！").css({"color":"green","font-size":"12px"});
					else $("#proudctIdTag").text("");
					if(getByteLen($("#productId").val())>50) $("#proudctIdTag").text("产品编号字数不能大于25！").css({"color":"red","font-size":"12px"});
				},
				error : function() {
					alert("系统异常，请稍后重试！");
				}//这里不要加","
				
			});
	});
	
	$("#productName").blur(function() {
		if($("#productName").val()=="")$("#nameTag").text("产品名称不能为空！").css({"color":"red","font-size":"12px"});
		else if(getByteLen($("#productName").val())>200) $("#nameTag").text("产品名称字数不能大于100！").css({"color":"red","font-size":"12px"});
		else $("#nameTag").text("");
	});
	$("#specification").blur(function() {
		if(getByteLen($("#specification").val())>50) $("#specificationTag").text("产品规格字数不能大于25！").css({"color":"red","font-size":"12px"});
		else $("#specificationTag").text("");
	});
	
		$("#features").blur(function() {
		if(getByteLen($("#features").val())>250) $("#featuresTag").text("产品介绍字数不能大于125个字！").css({"color":"red","font-size":"12px"});
		else $("#featuresTag").text("");
	});
	$("#price").blur(function() {
		if($("#price").val()=="")$("#priceTag").text("产品单价不能为空！").css({"color":"red","font-size":"12px"});
		else if(!reg.test($(this).val())) $("#priceTag").text("数值格式有误，产品单价应为实数！").css({"color":"red","font-size":"12px"});
		else $("#priceTag").text("");
	});
	$("#price1").blur(function() {
		if($("#price1").val()=="")$("#price1Tag").text("代理价不能为空！").css({"color":"red","font-size":"12px"});
		else if(!reg.test($(this).val())) $("#price1Tag").text("数值格式有误，值应为实数！").css({"color":"red","font-size":"12px"});
		else $("#price1Tag").text("");
	});
	
	
	$("#award").blur(function() {
		if($("#award").val()=="")$("#awardTag").text("总代进货价不能为空！").css({"color":"red","font-size":"12px"});
		else if(!reg.test($(this).val())) $("#awardTag").text("数值格式有误，值应为实数！").css({"color":"red","font-size":"12px"});
		else $("#awardTag").text("");
	});
	
	$("#fee").blur(function() {
		if($("#fee").val()=="")$("#feeTag").text("物流费不能为空！").css({"color":"red","font-size":"12px"});
		else if(!reg.test($(this).val())) $("#feeTag").text("数值格式有误，物流费应为实数！").css({"color":"red","font-size":"12px"});
		else $("#feeTag").text("");
	});
	
	
	$("#sbtn").click(function(){
		 if (!confirm("确认提交修改产品！")) {
				return false;
			}else{
		editor.sync();
		var errorstr = "";
		
		if($("#productName").val()==""){
			$("#nameTag").text("产品名称不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#nameTag").text()!=""){
			errorstr ="1";
		}
		
		if($("#productId").val()==""){
			$("#proudctIdTag").text("产品编号不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#proudctIdTag").text()!=""){
			if($("#proudctIdTag").text()!="此产品编号可用！") errorstr ="1";
		}
		
		if($("#price").val()==""){
			$("#priceTag").text("单价不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#priceTag").text()!=""){
			errorstr ="1";
		}
		if($("#price").val()==""){
			$("#priceTag").text("零售价不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#priceTag").text()!=""){
			errorstr ="1";
		}
		
		if($("#features").val()==""){
			$("#featuresTag").text("产品介绍不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#featuresTag").text()!=""){
			errorstr ="1";
		}
		$("#fee").blur(function() {
		if($("#fee").val()=="")$("#feeTag").text("物流费不能为空！").css({"color":"red","font-size":"12px"});
		else if(!reg.test($(this).val())) $("#feeTag").text("数值格式有误，物流费应为实数！").css({"color":"red","font-size":"12px"});
		else $("#feeTag").text("");
	});
		
if($("#price1").val()==""){
			$("#price1Tag").text("代理价不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#price1Tag").text()!=""){
			errorstr ="1";
		}
		
		if($("#award").val()==""){
			$("#awardTag").text("推荐奖励不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#awardTag").text()!=""){
			errorstr ="1";
		}
	
		if(errorstr!=""){
			return false;
		}else{
       		 $('#myform').submit();
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