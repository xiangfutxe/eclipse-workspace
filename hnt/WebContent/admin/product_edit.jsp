<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.Product" %>
<%@page import="com.ssm.pojo.ProductType" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.pojo.Admin" %>
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
		if(rankstr[3][3].equals("1")||admin.getState().equals("1")){
		String[] productType = StringUtil.notNull(product.getProductType()).split(",");
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
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="admin/ProductServlet.do?method=list">产品管理</a><span class="crumb-step">&gt;</span><span>产品修改</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form action="admin/ProductServlet?method=updateProduct" method="post" id="myform" name="myform">
               <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
                <input type="hidden" name="id" id="id" value="<%=product.getId()%>"/> 
                <input type="hidden" name="type" id="type" value="<%=product.getType()%>"/> 
                    <table class="insert-tab" width="99%">
                        <tbody><tr>
                            <th width="150px"><i class="require-red">*</i>产品分类：</th><td>
                            <% 	List<ProductType> coll_pt =  (List<ProductType>)request.getAttribute("coll_pt");
							if(coll_pt!=null){%>
                                   <%
								for(int i=0;i<coll_pt.size();i++){
									ProductType pt = coll_pt.get(i);
									boolean b  = false;
									for(int j=0;j<productType.length;j++){
										if(productType[j].equals(pt.getTypeName())) b = true;
									}
							%>
                       <input name="productType" type="checkbox" value="<%=pt.getTypeName()%>" <%if(b){ %>checked<%} %>/>
                                   <%=pt.getTypeName()%>
                                  <%} %>
                             <%}else{%>
                               未定义产品类型，请点击<a href="admin/ProductTypeServlet?method=addProductType">新增产品分类</a>!<input type="hidden" name="productType"/>&nbsp;<span id="typeTag"></span>
                            <%}%></td>
                        </tr>
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
                             <tr>  <th><i class="require-red">*</i>赠品类型：</th><td>
                       <select class="common-text" id="giveType" name="giveType">
                        <option value='0' <%if(product.getGiveType()==0){ %>selected<%} %>>--不属于赠品--</option>
                         <option value='1' <%if(product.getGiveType()==1){ %>selected<%} %>>一类赠品</option>
                          <option value='2' <%if(product.getGiveType()==2){ %>selected<%} %>>二类赠品</option>
                           <option value='3' <%if(product.getGiveType()==3){ %>selected<%} %>>三类赠品</option>
                       </select><span id="giveTypeTag"></span>
                       </td></tr>
                            <tr>
                                <th><i class="require-red">*</i>产品单价：</th>
                                <td><input class="common-text" name="price" id="price" size="50" type="text" value="<%=product.getPrice()%>">&nbsp;<span id="priceTag"></span></td>
                            </tr>
                             <tr>
                                <th><i class="require-red">*</i>产品PV值：</th>
                                <td><input class="common-text" name="pv" id="pv" size="50" type="text" value="<%=product.getPv()%>">&nbsp;<span id="pvTag"></span></td>
                            </tr>
                            <tr>
                                <th><i class="require-red">*</i>创业金购买价：</th>
                                <td><input class="common-text" name="price_cy" id="price_cy" size="50" type="text" value="<%=product.getPriceCy()%>">&nbsp;<span id="price_cyTag"></span></td>
                            </tr>
                             <tr>
                                <th><i class="require-red">*</i>创业金购买PV：</th>
                                <td><input class="common-text" name="pv_cy" id="pv_cy" size="50" type="text" value="<%=product.getPvCy()%>">&nbsp;<span id="pv_cyTag"></span></td>
                            </tr>
                             <tr>
                                <th><i class="require-red">*</i>最大购买总量：</th>
                                <td><input class="common-text" name="maxNum" id="maxNum" size="50" type="text" value="<%=product.getMaxNum()%>">&nbsp;<span id="maxTag"></span></td>
                            </tr>
                            <!--  
                             <tr>
                                <th><i class="require-red">*</i>赠送标准：</th>
                                <td><input class="common-text" name="give_num" id="give_num"  type="text" value="<%=product.getGiveNum()%>">&nbsp;<span id="giveNumTag"></span>
                                <br>注：不参与活动设置为0，如果参与买几送一活动，填入相应数字(如买四送一，填4)。</td>
                            </tr>
                            -->
							  <tr>
                                <th><i class="require-red">*</i>产品介绍：</th>
 <td><textarea id="features" name="features" style="width:700px;height:300px;">
 <%=product.getFeatures() %>
</textarea><span id="featuresTag"></span>
								 </td>                            </tr>
                          
                            <tr>
                            <tr>
                                <th></th>
                                <td>
                                    <input class="btn btn-primary mr10" value="确定修改" type="submit">
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
   <script type="text/javascript" src="js/custom.js"></script>
 <script language="JavaScript">
 var reg = /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/;
$(function() {
	
	$("#productType").blur(function() {
		if($("#productType").val()=="")$("#typeTag").text("产品名称不能为空！").css({"color":"red","font-size":"12px"});
		else $("#typeTag").text("");
	});
	$("#productName").blur(function() {
		if($("#productName").val()=="")$("#nameTag").text("产品名称不能为空！").css({"color":"red","font-size":"12px"});
		else if(getByteLen($("#productName").val())>50) $("#nameTag").text("产品名称字数不能大于25！").css({"color":"red","font-size":"12px"});
		else $("#nameTag").text("");
	});
	$("#specification").blur(function() {
		if(getByteLen($("#specification").val())>50) $("#specificationTag").text("产品规格字数不能大于25！").css({"color":"red","font-size":"12px"});
		else $("#specificationTag").text("");
	});
	
		$("#features").blur(function() {
		if($("#features")=="") $("#featuresTag").text("产品介绍不能为空！").css({"color":"red","font-size":"12px"});
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
		editor.sync();
		var errorstr = "";
		if($("#productType").val()==""){
			$("#typeTag").text("产品名称不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#typeTag").text()!=""){
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
		
		if($("#features").val()==""){
			$("#featuresTag").text("产品介绍不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#featuresTag").text()!=""){
			errorstr ="1";
		}
		
		if(errorstr!=""){
			return false;
		}else{
			
       		 $('#myform').submit();
       		 return true;
		
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