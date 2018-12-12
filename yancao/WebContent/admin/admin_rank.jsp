<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.ssm.pojo.Admin"%>
<%@ page import="com.ssm.utils.Constants"%>
<%@page import="com.ssm.utils.StringUtil" %>

<%
Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
Admin admin1 = (Admin) request.getAttribute("admin1");
String message = (String)request.getAttribute("message");
if(admin==null) {
out.println("<script>");
out.println("parent.window.location.href='login.jsp';");
out.println("</script>");
}else {
	  String[][] ranks = StringUtil.getRankStr(admin.getRank());
			if(ranks[9][3].equals("1")||admin.getState()-1==0){
				 String[][] rankstr = StringUtil.getRankStr(admin1.getRank());
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>员工权限</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/tip.css" rel="stylesheet" type="text/css" />
 <script charset="utf-8" src="js/jquery.js"></script>
 <style>
table{
border:1px;
}
table tr td{
	 margin-top:10px;    
	 margin-bottom:10px;  
	}
 </style>
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="index.jsp">首页</a></li>
    <li><a href="#">员工管理</a></li>
    <li><a href="#">员工权限</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
  
     <form action="AdminServlet?method=updateRank" id="myform" method="post">
      <input type="hidden" name="token" id="token" value="<c:out value='${sessionScope.token}' escapeXml='true' default=''/>"/>
       <input type="hidden" name="id" id="id" value="${admin1.id }"/>
       <table  width="100%" border="1px" >
								<tr>
									<th width="150px">信息管理：</th>
									<td><input name="rankstr" type="checkbox" value="000" 
								<%if(rankstr[0][0].equals("1")) {%>checked="checked"<%}%>/>添加新闻&nbsp;
										<input
										name="rankstr" type="checkbox" value="001" 
										<%if(rankstr[0][1].equals("1")) {%>checked="checked"<%} %> />新闻列表
										&nbsp;
										<input
										name="rankstr" type="checkbox" value="002" 
										<%if(rankstr[0][2].equals("1")) {%>checked="checked"<%} %> />留言簿
										&nbsp;
										<input
										name="rankstr" type="checkbox" value="003" 
										<%if(rankstr[0][3].equals("1")) {%>checked="checked"<%} %> />留言回复
										&nbsp;
										<input
										name="rankstr" type="checkbox" value="004" 
										<%if(rankstr[0][4].equals("1")) {%>checked="checked"<%} %> />留言删除
											&nbsp;
										
										</td>
								</tr>
								<tr>
									<th>合同管理：</th>
									<td><input name="rankstr" type="checkbox" value="100"
										<%if(rankstr[1][0].equals("1")) {%>checked="checked"<%} %> />合同新增&nbsp;<input
										name="rankstr" type="checkbox" value="101"
										<%if(rankstr[1][1].equals("1")) {%>checked="checked"<%} %> />合同查询
										&nbsp;<input
										name="rankstr" type="checkbox" value="102"
										<%if(rankstr[1][2].equals("1")) {%>checked="checked"<%} %> />合同修改
										&nbsp;<input
										name="rankstr" type="checkbox" value="103"
										<%if(rankstr[1][3].equals("1")) {%>checked="checked"<%} %> />合同删除
										&nbsp;<input
										name="rankstr" type="checkbox" value="104"
										<%if(rankstr[1][4].equals("1")) {%>checked="checked"<%} %> />借阅记录
										&nbsp;<input
										name="rankstr" type="checkbox" value="105"
										<%if(rankstr[1][5].equals("1")) {%>checked="checked"<%} %> />借阅审核
										&nbsp;<input
										name="rankstr" type="checkbox" value="106"
										<%if(rankstr[1][6].equals("1")) {%>checked="checked"<%} %> />借阅确认
										&nbsp;<input
										name="rankstr" type="checkbox" value="107"
										<%if(rankstr[1][7].equals("1")) {%>checked="checked"<%} %> />借阅归还
									
										
									
									</td>
								</tr>
							
								<tr>
									<th>员工管理：</th>
									<td><input name="rankstr" type="checkbox" value="900"
										<%if(rankstr[9][0].equals("1")) {%>checked="checked"<%} %> />员工列表&nbsp;<input
										name="rankstr" type="checkbox" value="901"
										<%if(rankstr[9][1].equals("1")) {%>checked="checked"<%} %> />新增员工
										&nbsp;<input
										name="rankstr" type="checkbox" value="902"
										<%if(rankstr[9][2].equals("1")) {%>checked="checked"<%} %> />员工详情
										&nbsp;<input
										name="rankstr" type="checkbox" value="903"
										<%if(rankstr[9][3].equals("1")) {%>checked="checked"<%} %> />员工权限
										&nbsp;<input
										name="rankstr" type="checkbox" value="904"
										<%if(rankstr[9][4].equals("1")) {%>checked="checked"<%} %> />员工密码
										&nbsp;<input
										name="rankstr" type="checkbox" value="905"
										<%if(rankstr[9][5].equals("1")) {%>checked="checked"<%} %> />员工删除
										&nbsp;<input
										name="rankstr" type="checkbox" value="906"
										<%if(rankstr[9][6].equals("1")) {%>checked="checked"<%} %> />部门查询
										&nbsp;<input
										name="rankstr" type="checkbox" value="907"
										<%if(rankstr[9][7].equals("1")) {%>checked="checked"<%} %> />新增部门
										&nbsp;<input
										name="rankstr" type="checkbox" value="908"
										<%if(rankstr[9][8].equals("1")) {%>checked="checked"<%} %> />部门修改
										&nbsp;<input
										name="rankstr" type="checkbox" value="909"
										<%if(rankstr[9][9].equals("1")) {%>checked="checked"<%} %> />部门删除
										&nbsp;<input
										name="rankstr" type="checkbox" value="910"
										<%if(rankstr[9][10].equals("1")) {%>checked="checked"<%} %> />职务查询
										&nbsp;<input
										name="rankstr" type="checkbox" value="911"
										<%if(rankstr[9][11].equals("1")) {%>checked="checked"<%} %> />新增职务
										&nbsp;<input
										name="rankstr" type="checkbox" value="912"
										<%if(rankstr[9][12].equals("1")) {%>checked="checked"<%} %> />职务修改
										&nbsp;<input
										name="rankstr" type="checkbox" value="913"
										<%if(rankstr[9][13].equals("1")) {%>checked="checked"<%} %> />职务删除
										</td>
								</tr>
								<tr>
									<th>系统管理：</th>
									<td><input
										name="rankstr" type="checkbox" value="918"
										<%if(rankstr[9][18].equals("1")) {%>checked="checked"<%} %> />数据备份
										&nbsp;<input
										name="rankstr" type="checkbox" value="919"
										<%if(rankstr[9][19].equals("1")) {%>checked="checked"<%} %> />日志管理
									
										&nbsp;<input
										name="rankstr" type="checkbox" value="923"
										<%if(rankstr[9][23].equals("1")) {%>checked="checked"<%} %> />会员日志
										&nbsp;<input
										name="rankstr" type="checkbox" value="924"
										<%if(rankstr[9][24].equals("1")) {%>checked="checked"<%} %> />日志删除
										&nbsp;<input
										name="rankstr" type="checkbox" value="925"
										<%if(rankstr[9][25].equals("1")) {%>checked="checked"<%} %> />日志导出</td>
								</tr>
								<tr>
									<th></th>
									<td><input id="sbtn" class="btn btn-primary mr6"
										value="保存" type="button"/> <input class="btn mr6"
										onclick='javascript:window.location.href="AdminServlet?method=list";' value="返回" type="button"/>
										<input class="btn mr6" id="ckAll" value="全选" type="button"/>&nbsp;<input class="btn mr6" id="ckAllNot" value="全不选" type="button"/></td>
								</tr>
						</table>
    </form>
     <div class="save_tip">
    	<div class="save_tiptop"><span>提示信息</span><a></a></div>
        
      <div class="save_tipinfo">
        <span><img src="images/ticon_save.png" /></span>
        <div class="save_tipright">
        <p>是否确认对信息的进行保存？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
       
      
        <div class="tipbtn">
        <input name="" type="button"  class="save_sure" value="确定" />&nbsp;
        <input name="" type="button"  class="save_cancel" value="取消" />
        </div>
     </div>
      <%if(!StringUtil.notNull(message).equals("")){%>
     <script language="JavaScript">
	$(function() {
     $(".tip").fadeIn(200);
	});
     </script>
     <div class="tip">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
        
      <div class="tipinfo">
        <span><img src="images/ticon.png" /></span>
        <div class="stipright">
        <p>${message}</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
       
        <div class="tipbtn">
        <input name="" type="button"  class="sure" value="确定" />&nbsp;
        <input name="" type="button"  class="cancel" value="取消" />
        </div>
     </div>
     <%} %>
    </div>
  <script language="JavaScript">
	$(function() {
		
		$("#ckAll").click(function() {
				
					$("input[name='rankstr']").each(function(){this.checked=true;}); 
				
		  });
		  $("#ckAllNot").click(function() {
					$("input[name='rankstr']").each(function(){this.checked=false;}); 
		  });

	
	$("#sbtn").click(function(){
		  $(".save_tip").fadeIn(200);
		  });
		  
		  $(".save_tiptop a").click(function(){
		  $(".save_tip").fadeOut(200);
		});

		  $(".save_sure").click(function(){
		 		 $(".save_tip").fadeOut(100);
		
       		 $('#myform').submit();
       		 return true;
		
		});
		  
		  $(".save_cancel").click(function(){
			  $(".save_tip").fadeOut(100);
			});
		  
		 

			  $(".sure").click(function(){
			 		 $(".tip").fadeOut(100);
			
			});
			  
			  $(".cancel").click(function(){
				  $(".tip").fadeOut(100);
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