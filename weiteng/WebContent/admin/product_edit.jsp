<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.ssm.pojo.Admin"%>
<%@ page import="com.ssm.pojo.Product"%>
<%@ page import="com.ssm.pojo.ProductType"%>
<%@ page import="com.ssm.utils.Constants"%>
<%@page import="com.ssm.utils.StringUtil" %>
<%@ page import="java.util.List"%>


<%
Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
Product product = (Product) request.getAttribute("product");
if(admin==null) {
out.println("<script>");
out.println("parent.window.location.href='login.jsp';");
out.println("</script>");
}else {
	  String[][] ranks = StringUtil.getRankStr(admin.getRank());
			if(ranks[3][2].equals("1")||admin.getState()-1==0){
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>产品详情</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/tip.css" rel="stylesheet" type="text/css" />
 <script charset="utf-8" src="js/jquery.js"></script>
  <script charset="utf-8" src="../kindeditor/kindeditor-all.js"></script>
<script>
KindEditor.ready(function(K) {
	window.editor=K.create('#features', {
		  uploadJson : '../kindeditor/jsp/upload_json.jsp',
                fileManagerJson : '../kindeditor/jsp/file_manager_json.jsp',
                allowFileManager : true
	});
});
        
</script>
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="index.jsp">首页</a></li>
    <li><a href="#">产品管理</a></li>
    <li><a href="#">产品新增</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
  
     <form action="ProductServlet?method=update" id="myform" method="post">
         <input type="hidden" name="token" id="token" value="<c:out value='${sessionScope.token}' escapeXml='true' default=''/>"/>
       <input type="hidden" name="id" id="id" value="${product.id}"/>
    <ul class="forminfo">
     <li><label>产品分类<b>*</b></label>
    
      <%
      List<ProductType> coll_pt = (List<ProductType>)request.getAttribute("coll_pt");
      if(coll_pt!=null){
    	  
      for(int i=0;i<coll_pt.size();i++){
    	  ProductType pt = coll_pt.get(i);
      	int checked= 0;
	      if(!product.getProductType().equals("")){ 
	    	  
		      String[] str = product.getProductType().split(",");
		      if(str!=null){
			      for(int j=0;j<str.length;j++){
			    	  if(str[j].equals(pt.getTypeName())){
			    		  checked = 1;
			    		  break;
			    	  }
			      }
		      }
	      }
      %>
      		<input type="checkbox" name="productType" value="<%=pt.getTypeName() %>" <%if(checked==1){%>checked="checked"<%}%>  class="dfbox"/><%=pt.getTypeName() %>&nbsp;
     <%}} %>
     </li>  
     <li><label>所属类型<b>*</b></label>
     <select id="productSort" name="productSort" class="dfinput">
     <option value="">--请选择所属类型--</option>
       <c:if test="${ coll_ps!=null}">
      <c:forEach items="${coll_ps}" var="ps">
      	 <option value="${ps.name }" <c:if test="${ps.name==product.productSort}">selected</c:if>>--${ps.name }--</option>
      </c:forEach>
     </c:if>
     </select>
     </li>  
    <li><label>产品编号<b>*</b></label>
    <input name="productId" id="productId" type="text" value="${product.productId}" class="dfinput" maxlength="10" readonly="readonly"/><i id="productIdTag"></i><i id="productIdTag1"></i></li>
     <li><label>产品名称<b>*</b></label>
    <input name="productName" id="productName"  type="text" value="${product.productName}" class="dfinput" /><i id="nameTag"></i></li>
      <li><label>产品规格<b>*</b></label>
    <input name="specification" id="specification" value="${product.specification}"  type="text" class="dfinput" /><i id="specificationTag"></i></li>
      <li><label>零售价格<b>*</b></label>
    <input name="price" id="price" value="${product.price}"  type="text" class="dfinput" /><i id="priceTag"></i></li>
     <li><label>原价<b>*</b></label>
    <input name="price1" id="price1" value="${product.price1}"  type="text" class="dfinput" /><i id="price1Tag"></i></li>
     
      <li><label>是否上架<b>*</b></label>
  <select id="isHide" name="isHide" class="dfinput">
     <option value="0" <c:if test="${product.isHide=='0'}">selected</c:if>>--立即上架--</option>
      <option value="1"  <c:if test="${product.isHide=='1'}">selected</c:if>>--放入仓库--</option>
     </select>   
</li>
 <li><label>赠送特卖权<b>*</b></label>
    <input name="cashNum" id="cashNum"  type="text" class="dfinput" value="${product.cashNum}"/><i id="cashNumTag"></i></li>
      <li><label>赠送代金券<b>*</b></label>
    <input name="cash" id="cash"  type="text" class="dfinput" value="${product.cash}"/><i id="cashTag"></i></li>
       <li><label>赠送积分<b>*</b></label>
    <input name="integral" id="integral"  type="text" class="dfinput" value="${product.integral}"/><i id="integralTag"></i></li>
     
 <li><label>产品介绍<b>*</b></label>
    <textarea id="features" name="features" style="width:500px;height:300px;">${product.features}
</textarea><i id="featuresTag"></i></li>
			<% if(ranks[3][2].equals("1")||admin.getState()-1==0){ %>
    <li><label>&nbsp;</label><input id="save_click"  type="button" class="btn" value="确认保存"/><i id="contentsTag"></i></li>
    <%}%>
    </ul>
    </form>
     <div class="save_tip">
    	<div class="save_tiptop"><span>提示信息</span><a></a></div>
        
      <div class="save_tipinfo">
        <span><img src="images/ticon_save.png" /></span>
        <div class="save_tipright">
        <p>是否确认对信息的进行保存信息？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
       
        <div class="tipbtn">
        <input name="" type="button"  class="save_sure" value="确定" />&nbsp;
        <input name="" type="button"  class="save_cancel" value="取消" />
        </div>
     </div>
    </div>
  <script language="JavaScript">
$(function() {
  var reg_tel = /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/; //电话号码与手机号码同时验证
  var reg = /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/;
  
	$("#productName").blur(function() {
		if($("#productName").val()=="")$("#nameTag").text("产品名称不能为空！").css({"color":"red","font-size":"12px"});
		else if($("#productName").val().length>100) $("#nameTag").text("产品名称字数不能大于50！").css({"color":"red","font-size":"12px"});
		else $("#nameTag").text("");
	});
	$("#specification").blur(function() {
		if($("#specification").val().length>50) $("#specificationTag").text("产品规格字数不能大于25！").css({"color":"red","font-size":"12px"});
		else $("#specificationTag").text("");
	});
	$("#features").blur(function() {
		if($("#features").val()=="") $("#featuresTag").text("产品介绍不能为空！").css({"color":"red","font-size":"12px"});
		else $("#featuresTag").text("");
	});
	$("#price").blur(function() {
		if($("#price").val()=="")$("#priceTag").text("产品单价不能为空！").css({"color":"red","font-size":"12px"});
		else if(!reg.test($(this).val())) $("#priceTag").text("数值格式有误，产品单价应为实数！").css({"color":"red","font-size":"12px"});
		else $("#priceTag").text("");
	});
	
	$("#save_click").click(function(){
		  $(".save_tip").fadeIn(200);
		  });
		  
		  $(".save_tiptop a").click(function(){
		  $(".save_tip").fadeOut(200);
		});

	$(".save_sure").click(function(){
		 		 $(".save_tip").fadeOut(100);
		var errorstr = "";
		editor.sync();
		if($("#productName").val()==""){
			$("#nameTag").text("产品名称不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#nameTag").text()!=""){
			errorstr ="1";
		}
		
		if($("#price").val()==""){
			$("#priceTag").text("单价不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#priceTag").text()!=""){
			errorstr ="1";
		}
		alert(errorstr);
		if($("#features").val()==""){
			$("#featuresTag").text("产品介绍不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#featuresTag").text()!=""){
			errorstr ="1";
		}
		
		alert(errorstr);
			if(errorstr!=""){
				return false;
			}else{
			
	       		 $('#myform').submit();
	       		 return true;
			
			}
	});
		  
		  $(".save_cancel").click(function(){
			  $(".save_tip").fadeOut(100);
			});
	
});
</script>
</body>

</html>
<%
	}else{
		out.println("<script>");
		out.println("window.location.href='error_rank.jsp';");
		out.println("</script>");
	}
}
%>