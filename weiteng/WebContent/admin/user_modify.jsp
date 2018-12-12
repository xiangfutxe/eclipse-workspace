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
			if(ranks[1][5].equals("1")||admin.getState()-1==0){
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加员工</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/tip.css" rel="stylesheet" type="text/css" />
 <script charset="utf-8" src="js/jquery.js"></script>
 <script charset="utf-8" src="../kindeditor/kindeditor-all.js"></script>

</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="index.jsp">首页</a></li>
    <li><a href="#">会员管理</a></li>
    <li><a href="#">会员调整</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
  
     <form action="UserServlet?method=user_modify" id="myform" method="post">
     <input type="hidden" name="token" id="token" value="<c:out value='${sessionScope.token}' escapeXml='true' default=''/>"/> 
    <ul class="forminfo">
    <li>温馨提示：<b>无需操作的不要进行填写。</b></li>
     <li><label>会员编号<b>*</b></label>
    <input name="userId" id="userId" type="text" class="dfinput" /><i id="nameTag"></i></li>
     <li><label>会员等级<b>*</b></label>
     <select name="rankJoin" id="rankJoin" class="dfinput">
      <option value="">--无需调整--</option>
     <option value="0">--关注会员--</option>
      <option value="1">--普通会员--</option>
       <option value="2">--天使会员--</option>
        <option value="3">--资深会员--</option>
         <option value="4">--高级合伙人--</option>
          <option value="5">--城市合伙人--</option>
           <option value="6">--联创合伙人--</option>
            <option value="7">--官方合伙人--</option>
     </select>
     </li>
     <li><label>特卖权数<b>*</b></label>
    <input name="cashNum" id="cashNum" type="text" class="dfinput" /></li>
     <li><label>代金券<b>*</b></label>
    <input name="cash" id="cash" type="text" class="dfinput" /></li>
     <li><label>积分<b>*</b></label>
    <input name="integral" id="integral" type="text" class="dfinput" /></li>
     <li><label>佣金<b>*</b></label>
    <input name="rmoney" id="rmoney" type="text" class="dfinput" /></li>
     <li><label>产品库存数<b>*</b></label>
    <input name="num" id="num" type="text" class="dfinput" /></li>
    <li><label>关注日期<b>*</b></label>
    <input name="entryTime" id="entryTime" type="text" class="dfinput" /><i>时间格式：2018-09-11 00:00:00</i></li>
      <li><label>代理资格<b>*</b></label>
     <select name="agentTag" id="agentTag" class="dfinput">
      <option value="">--无需调整--</option>
     <option value="1">--市级代理商--</option>
      <option value="2">--省级代理商--</option>
     </select>
     </li>
      
       <li><label>省<b>*</b></label>
          <select id="s_province" name="province" class="dfinput"></select></li>
          <li><label>市<b>*</b></label>
		  <select id="s_city" name="city" class="dfinput"></select></li>
		<li><label>县区<b>*</b></label>
		 <select id="s_county" name="county" class="dfinput"></select>
		 </li>
    <li><label>&nbsp;</label><input id="save_click"  type="button" class="btn" value="确认保存"/><i id="contentsTag"></i></li>
    </ul>
    </form>
     <div class="save_tip">
    	<div class="save_tiptop"><span>提示信息</span><a></a></div>
        
      <div class="save_tipinfo">
        <span><img src="images/ticon_save.png" /></span>
        <div class="save_tipright">
        <p>是否确认对信息的进行保存 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
       
        <div class="tipbtn">
        <input name="" type="button"  class="save_sure" value="确定" />&nbsp;
        <input name="" type="button"  class="save_cancel" value="取消" />
        </div>
     </div>
    </div>
<script type="text/javascript" src="../wap/js/area.js"></script>
 <script type="text/javascript">_init_area();</script>
  <script language="JavaScript">
$(function() {
  var reg_tel = /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/; //电话号码与手机号码同时验证

	
	$("#save_click").click(function(){
		  $(".save_tip").fadeIn(200);
		  });
		  
		  $(".save_tiptop a").click(function(){
		  $(".save_tip").fadeOut(200);
		});

		  $(".save_sure").click(function(){
		 		 $(".save_tip").fadeOut(100);
		var errorstr = "";
		
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