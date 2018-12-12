<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import="com.ssm.pojo.Admin"%>
<%@page import="com.ssm.utils.StringUtil"%>
<%@page import="com.ssm.utils.Constants"%>

<%
Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
if (admin == null) {
	out.println("<script>");
	out.println("parent.window.location.href='login.jsp';");
	out.println("</script>");
}else{
 String[][] ranks = StringUtil.getRankStr(admin.getRank());
	if(ranks[4][2].equals("1")||admin.getState()-1==0){
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>威腾后台-入库新增</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/tip.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
</head>


<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="index.jsp">首页</a></li>
    <li><a href="#">仓库管理</a></li>
    <li><a href="#">入库新增</a></li>
    </ul>
    </div>
            	
   <form  id ="myform" action="InventoryServlet?method=apply_in_save" method="post">
    <div class="rightinfo">
    
    		<div id="serch" class="tabson">
    <ul class="seachform">
     <li><label>选择仓库:</label>
     <select id="inventoryId" name="inventoryId" class="dfinput1">
    <option value="">--所有状态--</option>
    <c:if test="${coll_1!=null}">
     <c:forEach items="${coll_1}" var="inv">
    <option value="${inv.id }">--${inv.name }--</option>
	</c:forEach>
	</c:if>
   </select>
                                </li>
                                
                                <li><label>选择入库类型:</label>
     <select id="type" name="type" class="dfinput1">
    <option value="">--请选择类型--</option>
                            <option value="1">--采购入库--</option>
                             <option value="2">--加工入库--</option>
                              <option value="3">--退货入库--</option>
                             <option value="4">--盘盈入库--</option>
   </select>
                                </li>
                                 <li><label>备注:</label>
<input name="remark" id="remark"type="text" class="dfinput" /></li> 
                                 <li><i id="msg"></i></li>
    
    </ul>
    </div>
    <table class="tablelist">
   
    	<thead>
    	<tr>
        <th>序号<i class="sort"><img src="images/px.gif" /></i>
         <input type="hidden" name="token" id="token" value="${sessionScope.token}"/></th>
    	<th>产品编号</th>
							<th>产品名称</th>
							<th>规格</th>
							<th>入库单价</th>
							<th>入库数量</th>
        </tr>
        </thead>
        <tbody>
       
        <c:if test="${coll!=null}">
        <c:forEach items="${coll}" var="result" varStatus="status">
        <tr>
        <td>${status.index+1}<input name="ids" type="hidden" value="${result.id}" /></td>
        <td>${result.productId}<input type="hidden" name="pids" value="${result.productId}"/>
        </td>
         <td>${result.productName}
          <input type="hidden" name="names" value="${result.productName}"/></td>
          <td>${result.specification}
        <input type="hidden" name="specifications" value="${result.specification}"/>
          </td>
          <td><input type="text" size="10" name="prices" class="scinput" value="0" /> </td>
		<td><input type="text" size="10" name="nums" class="scinput" value="0" /></td>
         
        </tr>
        </c:forEach>
        </c:if>
      
      
        </tbody>
    </table>
    <div  class="tabson">
     <ul class="forminfo">
        <li><input id="save_click"  type="button" class="btn" value="确认保存"/>
        <input id="back_click"  type="button" class="btn grey" value="返回上一页"
         onclick="javascript:history.go(-1);"/></li>
    </ul>
    </div>
    
      <div class="save_tip">
    	<div class="save_tiptop"><span>提示信息</span><a></a></div>
      <div class="save_tipinfo">
        <span><img src="images/ticon_add.png" /></span>
        <div class="save_tipright">
        <p>是否确认提交入库申请？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        <div class="save_tipbtn">
        <input name="" type="button"  class="save_sure" value="确定" />&nbsp;
        <input name="" type="button"  class="save_cancel" value="取消" />
        </div>
    </div>
    
    </div>
    
     </form>
	<script type="text/javascript">
$(document).ready(function(){
	$('.tablelist tbody tr:odd').addClass('odd');
	
	$("#save_click").click(function(){
		  $(".save_tip").fadeIn(200);
		  });
		  
  $(".save_tiptop a").click(function(){
  $(".save_tip").fadeOut(200);
});

  $(".save_sure").click(function(){
	  $(".save_tip").fadeOut(100);
  var reg = /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/;
	var reg1 = /^[1-9]+[0-9]*]*$/;
	var error = "";
	var n = 0;
	var p = 0;
	if($("#inventoryId").val()=="") error=error+"仓库不能为空;";
	 if($("#type").val()=="") error=error+"入库类型不能为空;";
	
	$("input[name=nums]").each(function(i){ 
		if($(this).val()!="") 
			if($(this).val()!=0)	
			if(!reg1.test($(this).val())) n=n+1;
	});
	if(n!=0)
	error=error+"入库数量数必须为整数；";
	
	$("input[name=prices]").each(function(i){ 
		if($(this).val()!="") {
		 if(!reg.test($(this).val())) p=p+1;
		}
	});
	
	if(p!=0)
	error=error+"进货价数值格式不正确，";
	if(error==""){
		$('#myform').attr("action", "InventoryServlet?method=apply_in_save");
		 	$('#myform').submit();
		 	return true;
		 }else{
		 	$("#msg").text(error).css({"color":"red","font-size":"12px"});
		 	 return false;
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