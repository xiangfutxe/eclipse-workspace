<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.ProductType" %>
<%@page import="com.pojo.Product" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.utils.Pager" %>
<%@page import="com.pojo.Admin" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
String token = (String)request.getSession().getAttribute("token");
String[] attribute = (String[])request.getAttribute("attribute");
String typeName = (String)request.getAttribute("typeName");
Integer[] attributeId = (Integer[])request.getAttribute("attributeId");
String[][] attributeValue = (String[][])request.getAttribute("attributeValue");
Integer[][] attributeValueId = (Integer[][])request.getAttribute("attributeValueId");
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
    
    <title>新增产品</title>
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
       <script type="text/javascript" src="js/custom.js"></script>
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
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="admin/ProductServlet.do?method=list">产品列表</a><span class="crumb-step">&gt;</span><span>新增产品</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form action="admin/ProductServlet?method=admin_product_save" method="post" id="myform" name="myform">
                 <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
                    <table class="insert-tab" width="97%">
                        <tbody><tr>
                            <th width="150px"><i class="require-red">*</i>产品分类：</th>
                            <td>
                                   <input name="productType" type="hidden" value="<%=typeName%>"/>
                                   <%=typeName%>
                            </td>
                        </tr>
                       <tr>  <th><i class="require-red">*</i>产品类型：</th><td>
                       <select class="common-text" id="isGroup" name="isGroup">
                        <option value='1'>单品</option>
                       </select><span id="typeTag1"></span>
                       </td></tr>
                        <tr>  <th><i class="require-red">*</i>所属专区：</th><td>
                                    <input name="belongArea" type="checkbox" value="报单专区"/>
                                   报单专区
                                    <input name="belongArea" type="checkbox" value="积分专区"/>
                                  	积分专区
                    <span id="dTypeTag"></span>
                       </td></tr>
                            <tr>
                                <th><i class="require-red">*</i>产品代码：</th>
                                <td>
                                    <input class="common-text required" id="code" name="code"  value="" type="text">&nbsp;<span id="codeTag"></span><span id="codeTag1"></span>
                                </td>
                            </tr>
                            <tr>
                                <th><i class="require-red">*</i>产品名称：</th>
                                <td><input class="common-text" name="productName" id="productName" type="text">&nbsp;<span id="nameTag"></span></td>
                            </tr>
                              <tr>
                                <th><i class="require-red">*</i>产品规格：</th>
                                <td><input class="common-text" name="spec" id="spec" type="text">&nbsp;<span id="specTag"></span></td>
                            </tr>
                            
                            <%
                            if(attribute!=null){
                             for(int i=0;i<attribute.length;i++){ %>
                              <tr>
                                <th><%=StringUtil.notNull(attribute[i])%>：
                                  <input class="common-text" name="attribute<%=attributeId[i] %>" id="attribute<%=attributeId[i] %>"  value="<%=StringUtil.notNull(attribute[i]) %>" type="hidden">
                                </th>
                                <td>
                                <%if(attributeValueId[i][attributeValueId[i].length-1]==0) {%>
                                <input class="common-text" name="feature<%=attributeId[i] %>"  type="text">
                                <%}else {%>
                                	<% for(int j=0;j<attributeValue[i].length;j++){ 
                                	if(!StringUtil.notNull(attributeValue[i][j]).equals("")){
                                	%>
                                	  <input class="common-text" name="feature<%=attributeId[i] %>" value="<%=StringUtil.notNull(attributeValue[i][j])%>" type="checkbox">&nbsp;<%=StringUtil.notNull(attributeValue[i][j]) %>&nbsp;
                                	<%}
                                	} %>
                                <%} %>
                               </td>
                            </tr>
                            <%}
                            } %>
                            <tr>
                                <th><i class="require-red">*</i>产品单价：</th>
                                <td><input class="common-text" name="price" id="price" type="text">&nbsp;<span id="priceTag"></span></td>
                            </tr>
                             
                              <tr>
                                <th><i class="require-red">*</i>产品PV值：</th>
                                <td><input class="common-text" name="pv" id="pv" type="text">&nbsp;<span id="pvTag"></span></td>
                            </tr>
                            <tr>
                                <th><i class="require-red">*</i>所需积分：</th>
                                <td>
                                <input class="common-text" name="integral" id="integral" type="text" value='0'>&nbsp;<span id="integralTag"></span>
                            </tr>
                              
                           <tr>
                                <th><i class="require-red">*</i>产品介绍：</th>
                                 <td><textarea id="editor_id" name="description" style="width:700px;height:300px;">
</textarea><span id="featuresTag"></span>
								 </td>
                            </tr>
                            <tr>
                                <th></th>
                                <td>
                                    <input id="sbtn" class="btn btn-primary mr10" value="提交" type="button">
                                    <input class="btn" onClick="history.go(-1)" value="返回" type="button">
                                </td>
                            </tr>
                             <tr>
                               
                                <td colspan="2">
                                   <i class="require-red"> 备注：</i>
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
  
 <script language="JavaScript">

$(function() {
	var reg = /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/;
$("#code").blur(function() {
		$.ajax({
				type : "post",
				url : "admin/ProductServlet?method=isExit",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
				data : {//设置数据源
					productId : $("#code").val()
				//这里不要加","  不然会报错，而且根本不会提示错误地方

				},

				dataType : "json",//设置需要返回的数据类型
				success : function(data) {
					var d = data;//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
					//得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
					
					if(d.tag=="1") {
					 $("#codeTag1").text("产品编号已存在！").css({"color":"red","font-size":"12px"});
					 $("#codeTag").text("");
					 }
					else if(d.tag=="2") {
					 	$("#codeTag").text("此产品编号可用！").css({"color":"green","font-size":"12px"});
					 	 $("#codeTag1").text("");
					 } else{
					 $("#codeTag").text("");
					  $("#codeTag1").text("");
					 }
					if(getByteLen($("#code").val())>20){
					 $("#codeTag1").text("产品编号字数不能大于20！").css({"color":"red","font-size":"12px"});
					  $("#codeTag").text("");
					 }
				
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
	
	$("#price").blur(function() {
		if($("#price").val()=="")$("#priceTag").text("产品单价不能为空！").css({"color":"red","font-size":"12px"});
		else if(!reg.test($(this).val())) $("#priceTag").text("数值格式有误，产品单价应为实数！").css({"color":"red","font-size":"12px"});
		else $("#priceTag").text("");
	});
	$("#pv").blur(function() {
		if($("#pv").val()=="")$("#pvTag").text("产品PV不能为空！").css({"color":"red","font-size":"12px"});
		else if(!reg.test($(this).val())) $("#pvTag").text("数值格式有误，产品PV应为实数！").css({"color":"red","font-size":"12px"});
		else $("#pvTag").text("");
	});

	$("#integral").blur(function() {
		if($("#integral").val()=="")$("#integralTag").text("所需积分不能为空！").css({"color":"red","font-size":"12px"});
		else if(!reg.test($(this).val())) $("#integralTag").text("数值格式有误，值应为实数！").css({"color":"red","font-size":"12px"});
		else $("#integralTag").text("");
	});
	
	$("#sbtn").click(function(){
		if (!confirm("确认保存产品信息？")) {
			return false;
		} else {
			editor.sync();
		var errorstr = "";
			
		if($("#productName").val()==""){
			$("#nameTag").text("产品名称不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#nameTag").text()!=""){
			errorstr ="1";
		}
		
		if($("#code").val()==""){
			$("#codeTag1").text("产品编号不能为空！").css({"color":"red","font-size":"12px"}); 
			$("#codeTag").text("");
			errorstr ="1";
		}else if($("#codeTag1").text()!=""){
			errorstr ="1";
		}
		
		if($("#price").val()==""){
			$("#priceTag").text("单价不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#priceTag").text()!=""){
			errorstr ="1";
		}
			
		if($("#price").val()==""){
			$("#priceTag").text("单价不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#priceTag").text()!=""){
			errorstr ="1";
		}
		if($("#pv").val()==""){
			$("#pvTag").text("pv值不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#pvTag").text()!=""){
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
