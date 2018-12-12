<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.ssm.pojo.Admin"%>
<%@ page import="com.ssm.utils.Constants"%>
<%@page import="com.ssm.utils.StringUtil" %>


<%
Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
if(admin==null) {
out.println("<script>");
out.println("parent.window.location.href='login.jsp';");
out.println("</script>");
}else {
	  String[][] ranks = StringUtil.getRankStr(admin.getRank());
			if(ranks[1][2].equals("1")||admin.getState()-1==0){
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改合同</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/tip.css" rel="stylesheet" type="text/css" />
 <script charset="utf-8" src="js/jquery.js"></script>
 <script charset="utf-8" src="../kindeditor/kindeditor-all.js"></script>
<script src="js/calendar.js"></script>
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="index.jsp">首页</a></li>
    <li><a href="#">合同管理</a></li>
    <li><a href="#">合同新增</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
  
     <form action="ContractServlet?method=update" id="myform" method="post">
     <input type="hidden" name="token" id="token" value="<c:out value='${sessionScope.token}' escapeXml='true' default=''/>"/> 
        <input type="hidden" name="id" id="id" value="${contract.id}"/> 
   
    <ul class="forminfo">
     <li><label>密级<b>*</b></label>
     <select name="level" id="level" class="dfinput">
     <option value="0">--请选择密级--</option>
      <option value="1" <c:if test="${contract.level==1}">selected="selected"</c:if>>--保密--</option>
       <option value="2" <c:if test="${contract.level==2}">selected="selected"</c:if>>--可公开--</option>
     </select>
     </li>
     <li><label>合同编号<b>*</b></label>
    <input name="contractId" id="contractId" type="text" class="dfinput" value="${contract.contractId}" readonly="readonly"/><i id="contractIdTag"></i><i id="contractIdTag1"></i></li>
    
         <li><label>合同名称<b>*</b></label>
    <input name="contractName" id="contractName" type="text" class="dfinput" value="${contract.contractName}" /><i id="contractNameTag"></i></li>
    <li><label>承办人<b>*</b></label><input name="adminName" id="adminName" type="text" class="dfinput"  value="${contract.adminName}" />
   <i id="adminNameTag"></i><i id="adminNameTag1"></i></li>
    <li><label>合同位置<b>*</b></label><input name="code" id="code" type="text" class="dfinput"  value="${contract.code}"/><i id="codeTag"></i></li>
    <li><label>合同甲方<b>*</b></label><input name="partyA" id="partyA" type="text" class="dfinput"  value="${contract.partyA}"/><i id="partyATag"></i></li>
     <li><label>合同乙方<b>*</b></label><input name="partyB" id="partyB" type="text" class="dfinput" value="${contract.partyB}"/><i id="partyBTag"></i></li>
     <li><label>签订时间<b>*</b></label><input name="startTime" id="startTime" type="text" class="dfinput" value="${contract.endTime}" onfocus="new Calendar().show(this);"/><i id="startTimeTag"></i></li>
       <li><label>结束时间<b>*</b></label><input name="endTime" id="endTime" type="text" class="dfinput" value="${contract.endTime}" onfocus="new Calendar().show(this);"/><i id="endTimeTag"></i></li>
      <li><label>所属部门<b>*</b></label>
      <select name="dept" id="dept"  class="dfinput">
       <option value="" >--请选择所属部门--</option>
      <c:if test="${depts!=null}">
        <c:forEach items="${depts}" var="dt">
     <option value="${dt.name}" <c:if test="${contract.dept==dt.name}">selected="selected"</c:if>>${dt.name}</option>
     </c:forEach>
     </c:if></select><i id="deptTag"></i>
     </li>
    <li><label>&nbsp;</label><input id="save_click"  type="button" class="btn" value="保存修改"/><i id="contentsTag"></i></li>
    </ul>
    </form>
     <div class="save_tip">
    	<div class="save_tiptop"><span>提示信息</span><a></a></div>
        
      <div class="save_tipinfo">
        <span><img src="images/ticon_save.png" /></span>
        <div class="save_tipright">
        <p>是否确认对信息的进行修改保存 ？</p>
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

	$("#contractId").blur(function() {
		if($("#contractId").val()=="")$("#userNameTag").text("合同编号不能为空！").css({"color":"red","font-size":"12px"});
		else $("#contractIdTag").text("");
	});
	$("#contractName").blur(function() {
		if($("#contractName").val()=="")$("#nameTag").text("合同名称不能为空！").css({"color":"red","font-size":"12px"});
		else $("#nameTag").text("");
	});
	
	$("#contractId").blur(function() {
		if($("#contractId").val()=="")$("#userNameTag").text("合同编号不能为空！").css({"color":"red","font-size":"12px"});
		else $("#contractIdTag").text("");
	});
	$("#adminName").blur(function() {
		if($("#adminName").val()=="")$("#nameTag").text("承办人不能为空！").css({"color":"red","font-size":"12px"});
		else $("#adminNameTag").text("");
	});
	
	$("#partyA").blur(function() {
		if($("#partyA").val()=="")$("#partyBTag").text("甲方不能为空！").css({"color":"red","font-size":"12px"});
		else $("#partyATag").text("");
	});
	$("#partyB").blur(function() {
		if($("#partyB").val()=="")$("#partyBTag").text("乙方不能为空！").css({"color":"red","font-size":"12px"});
		else $("#partyBTag").text("");
	});
	$("#dept").blur(function() {
		if($("#dept").val()=="")$("#deptTag").text("所属部门不能为空！").css({"color":"red","font-size":"12px"});
		else $("#deptTag").text("");
	});
	$("#code").blur(function() {
		if($("#code").val()=="")$("#deptTag").text("存储位置不能为空！").css({"color":"red","font-size":"12px"});
		else $("#codeTag").text("");
	});
	$("#level").blur(function() {
		if($("#level").val()=="")$("#levelTag").text("密级不能为空！").css({"color":"red","font-size":"12px"});
		else $("#levelTag").text("");
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
		if($("#level").val()==""){
			$("#levelTag").text("密级不能为空！").css({"color":"red","font-size":"12px"});
			$("#levelTag").text("");
				errorstr ="密级有误!";
			}else if($("#levelTag").text()!=""){
				errorstr ="密级有误!";
			}
		if($("#contractId").val()==""){
			$("#contractIdTag").text("合同编号不能为空！").css({"color":"red","font-size":"12px"});
			$("#contractIdTag").text("");
				errorstr ="合同编号有误!";
			}else if($("#contractIdTag").text()!=""){
				errorstr ="合同编号有误!";
			}
		if($("#contractName").val()==""){
		$("#contractNameTag").text("合同名称不能为空！").css({"color":"red","font-size":"12px"});
			errorstr ="合同名称有误！";
		}else if($("#contractNameTag").text()!=""){
			errorstr ="合同名称有误！";
		}
		if($("#adminName").val()==""){
			$("#adminNameTag").text("承办人不能为空！").css({"color":"red","font-size":"12px"});
				errorstr ="承办人有误";
			}else if($("#adminNameTag").text()!=""){
				errorstr ="承办人有误";
			}
		if($("#code").val()==""){
			$("#codeTag").text("存储位置不能为空！").css({"color":"red","font-size":"12px"});
				errorstr ="存储位置有误";
			}else if($("#codeTag").text()!=""){
				errorstr ="存储位置有误";
			}
		
		if($("#startTime").val()==""){
			$("#startTimeTag").text("签订日期不能为空！").css({"color":"red","font-size":"12px"});
				errorstr ="签订日期有误";
			}else if($("#startTimeTag").text()!=""){
				errorstr ="签订日期有误";
			}
		
		if($("#endTime").val()==""){
			$("#endTimeTag").text("结束日期不能为空！").css({"color":"red","font-size":"12px"});
				errorstr ="结束日期有误";
			}else if($("#endTimeTag").text()!=""){
				errorstr ="结束日期有误";
			}
		
		if($("#dept").val()==""){
			$("#deptTag").text("所属部门不能为空！").css({"color":"red","font-size":"12px"});
				errorstr ="所属部门有误";
			}else if($("#deptTag").text()!=""){
				errorstr ="所属部门有误";
			}
		
		if($("#partA").val()==""){
			$("#partATag").text("甲方不能为空！").css({"color":"red","font-size":"12px"});
				errorstr ="甲方有误";
			}else if($("#partATag").text()!=""){
				errorstr ="甲方有误";
			}
		
		if($("#partB").val()==""){
			$("#partBTag").text("乙方不能为空！").css({"color":"red","font-size":"12px"});
				errorstr ="乙方有误";
			}else if($("#partBTag").text()!=""){
				errorstr ="乙方有误";
			}
		
		if(errorstr!=""){
			alert(errorstr);
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