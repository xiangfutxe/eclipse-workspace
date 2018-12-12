<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.ProductType" %>
<%@page import="com.pojo.ProductAttribute" %>
<%@page import="com.pojo.Product" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.utils.Pager" %>
<%@page import="com.pojo.Admin" %>
<%@page import="com.pojo.ProductUnit" %>
<%@page import="com.pojo.Inventory" %>
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
		if(rankstr[2][1].equals("1")||admin.getState().equals("1")){
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
                <form action="admin/ProductServlet.do?method=admin_save" method="post" id="myform" name="myform">
                 <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
                    <table class="insert-tab" width="97%">
                        <tbody>
                         <tr>
                            <th width="150px"><i class="require-red">*</i>销售对象：</th>
                             <td> <input type="checkbox" name="branchType" value='1'>加盟店
                             &nbsp;<input type="checkbox" name="branchType" value='2'>直营店&nbsp;
                             <input type="checkbox" name="branchType" value='3'>外购商&nbsp;<span id="branchTypeTag"></span>
                              
							</td>
							 <tr>
                          <tr>
                            <th width="150px"><i class="require-red">*</i>消费类型：</th>
                             <td> <input type="checkbox" name="type" value='1' checked>消费产品&nbsp;<input type="checkbox" name="type" value='2'>新店产品&nbsp;<span id="typeTag"></span>
                              
							</td>
							 <tr>
                            <th width="150px"><i class="require-red">*</i>产品属性：</th>
                            <td>
 <%	 
							Collection coll_pa =  (Collection)request.getAttribute("coll_pa");
							if(coll_pa!=null){%>
                                <select id= "attribute" name="attribute">
                                    <%
									Iterator it = coll_pa.iterator();
									while(it.hasNext()){
									ProductAttribute pt = (ProductAttribute)it.next();
							%>
							
							<option value='<%=pt.getName()%>'> <%=pt.getName()%></option>
							
							
                              
<%}%>
</select><span id="attributeTag"></span>
<%
}else{%>
                               未定义产品类型，请点击<a href="admin/ProductAttributeServlet.do?method=add">新增属性</a>!<input type="hidden" name="attribute"/>&nbsp;<span id="attributeTag"></span>
                            <%}%></td>
                        </tr>
                        <tr>
                            <th width="150px"><i class="require-red">*</i>产品分类：</th>
                            <td>
 <%	 
							Collection coll_pt =  (Collection)request.getAttribute("coll_pt");
							if(coll_pt!=null){%>
                                <select id= "productType" name="productType">
                                    <%
									Iterator it = coll_pt.iterator();
									while(it.hasNext()){
									ProductType pt = (ProductType)it.next();
							%>
							
							<option value='<%=pt.getTypeName()%>'> <%=pt.getTypeName()%></option>
							
							
                              
<%}%>
</select><span id="typeNameTag"></span>
<%
}else{%>
                               未定义产品类型，请点击<a href="admin/ProductTypeServlet.do?method=add">新增分类</a>!<input type="hidden" name="productType"/>&nbsp;<span id="typeNameTag"></span>
                            <%}%></td>
                        </tr>
                         <tr>
                            <th width="150px"><i class="require-red">*</i>所在仓库：</th>
                            <td>
                            <select id= "inventoryOne" name="inventoryOne">
 								<%	 
							Collection coll_1 =  (Collection)request.getAttribute("coll_1");
							if(coll_1!=null){
									Iterator it = coll_1.iterator();
									while(it.hasNext()){
									Inventory pt = (Inventory)it.next();
							%>
							
							<option value='<%=pt.getInventoryName()%>'> <%=pt.getInventoryName()%></option>
							
							
                              
<%}}%>
</select>
<select id= "inventoryTwo" name="inventoryTwo">
 								<%	 
							Collection coll_2 =  (Collection)request.getAttribute("coll_2");
							if(coll_2!=null){
									Iterator it = coll_2.iterator();
									while(it.hasNext()){
									Inventory pt = (Inventory)it.next();
							%>
							
							<option value='<%=pt.getInventoryName()%>'> <%=pt.getInventoryName()%></option>
							
							
                              
<%}}%>
</select>
<select id= "inventoryThree" name="inventoryThree">
 								<%	 
							Collection coll_3 =  (Collection)request.getAttribute("coll_3");
							if(coll_3!=null){
									Iterator it = coll_3.iterator();
									while(it.hasNext()){
									Inventory pt = (Inventory)it.next();
							%>
							
							<option value='<%=pt.getInventoryName()%>'> <%=pt.getInventoryName()%></option>
							
							
                              
<%}}%>
</select><span id="inventoryNameTag"></span>
</td>
                        </tr>
                        
                            <tr>
                                <th><i class="require-red">*</i>产品代码：</th>
                                <td>
                                    <input class="common-text required" id="productId" name="productId"  value="" type="text">&nbsp;<span id="productIdTag"><span id="productIdTag1"></span>
                                </td>
                            </tr>
                            <tr>
                                <th><i class="require-red">*</i>产品名称：</th>
                                <td><input class="common-text" name="productName" id="productName" type="text">&nbsp;<span id="nameTag"></span></td>
                            </tr>
                              <tr>
                                <th>产品规格：</th>
                                <td><input class="common-text" name="specification" id="specification"  type="text">&nbsp;<span id="specificationTag"></span></td>
                            </tr>
                            <tr>
                            <th width="150px"><i class="require-red">*</i>单位：</th>
                            <td>
 <%	 
							Collection coll_t =  (Collection)request.getAttribute("coll_pn");
							if(coll_t!=null){%>
							<select id= "unit" name="unit">
                                    <%
									Iterator it = coll_t.iterator();
									int i =0;
									while(it.hasNext()){
									ProductUnit pt = (ProductUnit)it.next();
							%>
							
							<option value='<%=pt.getName()%>'> <%=pt.getName()%></option>
							
                                  <%} %>
                               </select>
							<span id="unitTag"></span>
<%}else{%>
                               未定义单位信息，请点击<a href="admin/ProductUnitServlet.do?method=admin_add">新增单位</a>!<input type="hidden" name="unit" id="unit"/>&nbsp;<span id="unitTag"></span>
                            <%}%></td>
                        </tr>
                            <tr>
                                <th><i class="require-red">*</i>产品单价：</th>
                                <td><input class="common-text" name="price" id="price" type="text">&nbsp;<span id="priceTag"></span></td>
                            </tr>
                             <tr>
                                <th><i class="require-red">*</i>安全库存：</th>
                                <td><input class="common-text" name="limitNum" id="attribute" value="0" type="text">&nbsp;<span id="limitNumTag"></span></td>
                            </tr>
                           <tr>
                                <th><i class="require-red">*</i>产品介绍：</th>
                                 <td><textarea id="editor_id" name="features" style="width:700px;height:300px;">
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
	var reg1 =  /^(0|[1-9]\d*)$/;//验证非负整数
$("#productId").blur(function() {
		$.ajax({
				type : "post",
				url : "admin/ProductServlet.do?method=isExit",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
				data : {//设置数据源
					productId : $("#productId").val()
				//这里不要加","  不然会报错，而且根本不会提示错误地方

				},

				dataType : "json",//设置需要返回的数据类型
				success : function(data) {
					var d = data;//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
					//得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
					
					if(d.tag=="1"){
					 $("#productIdTag1").text("产品编号已存在！").css({"color":"red","font-size":"12px"});
					$("#productIdTag").text("");
					
					}else if(d.tag=="2"){ 
					 $("#productIdTag").text("此产品编号可用！").css({"color":"green","font-size":"12px"});
					$("#productIdTag1").text("");
					}else {
						$("#productIdTag1").text("");
						$("#productIdTag").text("");
					}
					if(getByteLen($("#productId").val())>20){
					 $("#proudctIdTag1").text("产品编号字数不能大于20！").css({"color":"red","font-size":"12px"});
					$("#productIdTag").text("");
					}
				},
				error : function() {
					$("#productIdTag1").text("系统异常，请稍后重试！").css({"color":"red","font-size":"12px"});
					$("#productIdTag").text("");
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
	$("#attribute").blur(function() {
		if($("#attribute").val()=="")$("#attributeTag").text("产品属性不能为空！").css({"color":"red","font-size":"12px"});
		else $("#attributeTag").text("");
	});
	
	
	$("#limitNum").blur(function() {
		if($("#limitNum").val()=="")$("#priceTag").text("安全库存不能为空！").css({"color":"red","font-size":"12px"});
		else if(!reg.test($(this).val())) $("#priceTag").text("数值格式有误！").css({"color":"red","font-size":"12px"});
		else $("#limitNumTag").text("");
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
		else if($("#nameTag1").text()!=""){
			errorstr ="1";
		}
		
		if($("#productId").val()==""){
			$("#productIdTag1").text("产品编号不能为空！").css({"color":"red","font-size":"12px"}); 
			$("#productIdTag").text("");
			errorstr ="1";
		}else if($("#productIdTag1").text()!=""){
			errorstr ="1";
		}

		if($("#productType").val()==""){
			$("#typeNameTag").text("产品分类不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}else if($("#typeNameTag").text()!=""){
			errorstr ="1";
		}
		
		if($("#price").val()==""){
			$("#priceTag").text("单价不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#priceTag").text()!=""){
			errorstr ="1";
		}
		
		if($("#unit").val()==""){
			$("#unitTag").text("单位不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#unitTag").text()!=""){
			errorstr ="1";
		}

		if($("#attribute").val()==""){
			$("#attributeTag").text("产品属性不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#attributeTag").text()!=""){
			errorstr ="1";
		}
		
		if($("#limitNum").val()==""){
			$("#limitNumTag").text("安全库存不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#limitNumTag").text()!=""){
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
