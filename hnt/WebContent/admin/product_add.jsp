<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.ProductType" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.pojo.Admin" %>
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
		if(rankstr[3][2].equals("1")||admin.getState().equals("1")){
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
	window.editor=K.create('#features', {
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
                <form action="admin/ProductServlet?method=saveProduct" method="post" id="myform" name="myform">
                 <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
                    <table class="insert-tab" width="97%">
                        <tbody><tr>
                            <th width="150px"><i class="require-red">*</i>产品分类：</th>
                            <td>
 <%	 
							List<ProductType> coll_pt =  (List<ProductType>)request.getAttribute("coll_pt");
							if(coll_pt!=null){%>
                                    <%
								for(int i=0;i<coll_pt.size();i++){
									ProductType pt = coll_pt.get(i);
							%>
                                     <input name="productType" type="checkbox" value="<%=pt.getTypeName()%>"/>
                                   <%=pt.getTypeName()%>
                                  <%} %>
<%}else{%>
                               未定义产品类型，请点击<a href="admin/ProductTypeServlet?method=addProductType">新增产品分类</a>!<input type="hidden" name="productType"/>&nbsp;<span id="typeTag"></span>
                            <%}%></td>
                        </tr>
                       <tr>  <th><i class="require-red">*</i>产品类型：</th><td>
                       <select class="common-text" id="type" name="type">
                        <option value='1'>单品</option>
                         <option value='2'>套餐</option>
                       </select><span id="typeTag1"></span>
                       </td></tr>
                        <tr>  <th><i class="require-red">*</i>赠品类型：</th><td>
                       <select class="common-text" id="giveType" name="giveType">
                        <option value='0'>--不属于赠品--</option>
                         <option value='1'>一类赠品</option>
                          <option value='2'>二类赠品</option>
                           <option value='3'>三类赠品</option>
                       </select><span id="giveTypeTag"></span>
                       </td></tr>
                            <tr>
                                <th><i class="require-red">*</i>产品代码：</th>
                                <td>
                                    <input class="common-text required" id="productId" name="productId"  value="" type="text">&nbsp;<span id="productIdTag"></span>
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
                                <th><i class="require-red">*</i>产品单价：</th>
                                <td><input class="common-text" name="price" id="price" type="text">&nbsp;<span id="priceTag"></span></td>
                            </tr>
                             <tr>
                                <th><i class="require-red">*</i>产品PV值：</th>
                                <td><input class="common-text" name="pv" id="pv" type="text">&nbsp;<span id="pvTag"></span></td>
                            </tr>
                            
                             <tr>
                                <th><i class="require-red">*</i>创业金购买价：</th>
                                <td><input class="common-text" name="price_cy" id="price_cy" type="text">&nbsp;<span id="price_cyTag"></span></td>
                            </tr>
                               <tr>
                                <th><i class="require-red">*</i>创业金购买pv：</th>
                                <td><input class="common-text" name="pv_cy" id="pv_cy" type="text">&nbsp;<span id="pv_cyTag"></span></td>
                            </tr>
                              <tr>
                                <th><i class="require-red">*</i>最大购买总量：</th>
                                <td><input class="common-text" name="maxNum" id="maxNum"  type="text" value="">&nbsp;<span id="maxTag"></span></td>
                            </tr>
                            <!--  
                            <tr>
                                <th><i class="require-red">*</i>赠送标准：</th>
                                <td><input class="common-text" name="give_num" id="give_num"  type="text" value="0">&nbsp;<span id="giveNumTag"></span>
                                <br>注：不参与活动设置为0，如果参与买几送一活动，填入相应数字(如买四送一，填4)。</td>
                            </tr>
                            -->
                              <tr>
                                <th><i class="require-red">*</i>产品介绍：</th>
 <td><textarea id="features" name="features" style="width:700px;height:300px;">
</textarea><span id="featuresTag"></span>
								 </td>                            </tr>
                          
                            <tr>
                                <th></th>
                                <td>
                                    <input id="sbtn" class="btn btn-primary mr10" value="提交" type="button">
                                    <input class="btn" onClick="history.go(-1)" value="返回" type="button">
                                </td>
                            </tr>
                             <tr>
                               
                                <td colspan="2">
                                   <i class="require-red"> 备注：如果选择定义套餐产品，请随意输入大于零的产品单价和产品PV。</i>
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
$("#productId").blur(function() {
		$.ajax({
				type : "post",
				url : "admin/ProductServlet?method=isExit",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
				data : {//设置数据源
					productId : $("#productId").val()
				//这里不要加","  不然会报错，而且根本不会提示错误地方

				},

				dataType : "json",//设置需要返回的数据类型
				success : function(data) {
					var d = data;//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
					//得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
					
					if(d.tag=="1")  $("#productIdTag").text("产品编号已存在！").css({"color":"red","font-size":"12px"});
					else if(d.tag=="2")  $("#productIdTag").text("此产品编号可用！").css({"color":"green","font-size":"12px"});
					else $("#productIdTag").text("");
					if(getByteLen($("#productId").val())>20) $("#proudctIdTag").text("产品编号字数不能大于20！").css({"color":"red","font-size":"12px"});
				
				},
				error : function() {
					alert("系统异常，请稍后重试！");
				}//这里不要加","
				
			});
	});
	
	$("#productType").blur(function() {
		if($("#productType").val()=="")$("#typeTag").text("产品分类不能为空！").css({"color":"red","font-size":"12px"});
		else $("#typeTag").text("");
	});
	
	$("#type").blur(function() {
		if($("#type").val()=="")$("#typeTag1").text("产品类型不能为空！").css({"color":"red","font-size":"12px"});
		else $("#typeTag1").text("");
	});
	$("#productName").blur(function() {
		if($("#productName").val()=="")$("#nameTag").text("产品名称不能为空！").css({"color":"red","font-size":"12px"});
		else if(getByteLen($("#productName").val())>100) $("#nameTag").text("产品名称字数不能大于50！").css({"color":"red","font-size":"12px"});
		else $("#nameTag").text("");
	});
	$("#specification").blur(function() {
		if(getByteLen($("#specification").val())>50) $("#specificationTag").text("产品规格字数不能大于25！").css({"color":"red","font-size":"12px"});
		else $("#specificationTag").text("");
	});
	$("#features").blur(function() {
		if(getByteLen($("#features").val())>200) $("#featuresTag").text("产品介绍字数不能大于100个字！").css({"color":"red","font-size":"12px"});
		else $("#featuresTag").text("");
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
	
	$("#price_cy").blur(function() {
		if($("#price_cy").val()=="")$("#price_cyTag").text("价格不能为空！").css({"color":"red","font-size":"12px"});
		else if(!reg.test($(this).val())) $("#price_cyTag").text("数值格式有误，价格应为实数！").css({"color":"red","font-size":"12px"});
		else $("#price_cyTag").text("");
	});
	$("#pv_cy").blur(function() {
		if($("#pv_cy").val()=="")$("#pv_cyTag").text("PV不能为空！").css({"color":"red","font-size":"12px"});
		else if(!reg.test($(this).val())) $("#pv_cyTag").text("数值格式有误，PV应为实数！").css({"color":"red","font-size":"12px"});
		else $("#pv_cyTag").text("");
	});
	
	$("#sbtn").click(function(){
		if (!confirm("确认保存产品信息？")) {
								return false;
							} else {
							editor.sync();
		var errorstr = "";
		if($("#productType").val()==""){
			$("#typeTag").text("产品分类不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#typeTag").text()!=""){
			errorstr ="1";
		}
		
		if($("#type").val()==""){
			$("#typeTag1").text("产品类型不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#typeTag1").text()!=""){
			errorstr ="1";
		}
		
		if($("#productName").val()==""){
			$("#nameTag").text("产品名称不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#nameTag").text()!=""){
			errorstr ="1";
		}
		
		if($("#productId").val()==""){
			$("#productIdTag").text("产品编号不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}else if($("#productIdTag").text()!="此产品编号可用！"){
			errorstr ="1";
		}
		
		if($("#price").val()==""){
			$("#priceTag").text("单价不能为空！").css({"color":"red","font-size":"12px"}); 
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
		
		if($("#pv").val()==""){
			$("#pvTag").text("pv值不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#pvTag").text()!=""){
			errorstr ="1";
		}
		if($("#price_cy").val()==""){
			$("#price_cyTag").text("价格不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#price_cyTag").text()!=""){
			errorstr ="1";
		}
		if($("#pv_cy").val()==""){
			$("#pv_cyTag").text("pv值不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#pv_cyTag").text()!=""){
			errorstr ="1";
		}
		/*
		if($("#purchase_price_1").val()==""){
			$("#purchase_price_1Tag").text("资格店进货价不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#purchase_price_1Tag").text()!=""){
			errorstr ="1";
		}
		if($("#purchase_pv_1").val()==""){
			$("#purchase_pv_1Tag").text("资格店进货pv值不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}else if($("#purchase_pv_1Tag").text()!=""){
			errorstr ="1";
		}
		if($("#purchase_price_2").val()==""){
			$("#purchase_price_2Tag").text("实体店进货价不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#purchase_price_2Tag").text()!=""){
			errorstr ="1";
		}
		if($("#purchase_pv_2").val()==""){
			$("#purchase_pv_2Tag").text("实体店进货pv值不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}else if($("#purchase_pv_2Tag").text()!=""){
			errorstr ="1";
		}
		*/
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
