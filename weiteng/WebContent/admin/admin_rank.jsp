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
										<input
										name="rankstr" type="checkbox" value="005" 
										<%if(rankstr[0][5].equals("1")) {%>checked="checked"<%} %> />模块查询
											&nbsp;
										<input
										name="rankstr" type="checkbox" value="006" 
										<%if(rankstr[0][6].equals("1")) {%>checked="checked"<%} %> />模块新增
											&nbsp;
										<input
										name="rankstr" type="checkbox" value="007" 
										<%if(rankstr[0][7].equals("1")) {%>checked="checked"<%} %> />模块修改
											&nbsp;
										<input
										name="rankstr" type="checkbox" value="008" 
										<%if(rankstr[0][8].equals("1")) {%>checked="checked"<%} %> />模块删除
										</td>
								</tr>
								<tr>
									<th>会员管理：</th>
									<td><input name="rankstr" type="checkbox" value="100"
										<%if(rankstr[1][0].equals("1")) {%>checked="checked"<%} %> />会员新增&nbsp;<input
										name="rankstr" type="checkbox" value="101"
										<%if(rankstr[1][1].equals("1")) {%>checked="checked"<%} %> />会员查询
										&nbsp;<input
										name="rankstr" type="checkbox" value="102"
										<%if(rankstr[1][2].equals("1")) {%>checked="checked"<%} %> />会员修改&nbsp;<input
										name="rankstr" type="checkbox" value="103"
										<%if(rankstr[1][3].equals("1")) {%>checked="checked"<%} %> />初始化密码
										&nbsp;<input
										name="rankstr" type="checkbox" value="104"
										<%if(rankstr[1][4].equals("1")) {%>checked="checked"<%} %> />会员导出
										&nbsp;<input
										name="rankstr" type="checkbox" value="105"
										<%if(rankstr[1][5].equals("1")) {%>checked="checked"<%} %> />会员调整
										&nbsp;<input
										name="rankstr" type="checkbox" value="106"
										<%if(rankstr[1][6].equals("1")) {%>checked="checked"<%} %> />邀请绑定
										&nbsp;<input
										name="rankstr" type="checkbox" value="115"
										<%if(rankstr[1][15].equals("1")) {%>checked="checked"<%} %> />代理查询
										&nbsp;<input
										name="rankstr" type="checkbox" value="116"
										<%if(rankstr[1][16].equals("1")) {%>checked="checked"<%} %> />代理新增
										&nbsp;<input
										name="rankstr" type="checkbox" value="117"
										<%if(rankstr[1][17].equals("1")) {%>checked="checked"<%} %> />代理审批
										
									
									</td>
								</tr>
								<tr>
									<th>网络管理：</th>
									<td><input name="rankstr" type="checkbox" value="200"
										<%if(rankstr[2][0].equals("1")) {%>checked="checked"<%} %> />服务关系(树形)&nbsp;<input
										name="rankstr" type="checkbox" value="201"
										<%if(rankstr[2][1].equals("1")) {%>checked="checked"<%} %> />销售关系(树形)&nbsp;&nbsp;
										<input name="rankstr" type="checkbox" value="202"
										<%if(rankstr[2][2].equals("1")) {%>checked="checked"<%} %> />服务关系(反向)&nbsp;<input
										name="rankstr" type="checkbox" value="203"
										<%if(rankstr[2][3].equals("1")) {%>checked="checked"<%} %> />销售关系(反向)&nbsp;<input
										name="rankstr" type="checkbox" value="204"
										<%if(rankstr[2][4].equals("1")) {%>checked="checked"<%} %> />远端会员
										&nbsp;<input
										name="rankstr" type="checkbox" value="205"
										<%if(rankstr[2][5].equals("1")) {%>checked="checked"<%} %> />团队会员奖衔
										
										</td>
								</tr>
								<tr>
									<th>产品管理：</th>
									<td><input name="rankstr" type="checkbox" value="300"
										<%if(rankstr[3][0].equals("1")) {%>checked<%} %> />产品查询&nbsp;<input
										name="rankstr" type="checkbox" value="301"
										<%if(rankstr[3][1].equals("1")) {%>checked<%} %> />产品新增&nbsp;
										<input name="rankstr" type="checkbox" value="302"
										<%if(rankstr[3][2].equals("1")) {%>checked<%} %> />产品修改&nbsp;
										<input name="rankstr" type="checkbox" value="303"
										<%if(rankstr[3][3].equals("1")) {%>checked<%} %> />产品删除&nbsp;
										<input name="rankstr" type="checkbox" value="304"
										<%if(rankstr[3][4].equals("1")) {%>checked<%} %> />产品导出&nbsp;
										
										<input name="rankstr" type="checkbox" value="306"
										<%if(rankstr[3][6].equals("1")) {%>checked<%} %> />分类查询&nbsp;
										<input name="rankstr" type="checkbox" value="307"
										<%if(rankstr[3][7].equals("1")) {%>checked<%} %> />分类新增&nbsp;
										<input name="rankstr" type="checkbox" value="308"
										<%if(rankstr[3][8].equals("1")) {%>checked<%} %> />分类修改&nbsp;
										<input name="rankstr" type="checkbox" value="309"
										<%if(rankstr[3][9].equals("1")) {%>checked<%} %> />分类删除&nbsp;
										<input name="rankstr" type="checkbox" value="310"
										<%if(rankstr[3][10].equals("1")) {%>checked<%} %> />类型查询&nbsp;
										<input name="rankstr" type="checkbox" value="311"
										<%if(rankstr[3][11].equals("1")) {%>checked<%} %> />类型新增&nbsp;
										<input name="rankstr" type="checkbox" value="312"
										<%if(rankstr[3][12].equals("1")) {%>checked<%} %> />类型修改&nbsp;
										<input name="rankstr" type="checkbox" value="313"
										<%if(rankstr[3][13].equals("1")) {%>checked<%} %> />类型删除&nbsp;
										</td>
								</tr>
								<tr>
									<th>仓库管理：</th>
									<td><input name="rankstr" type="checkbox" value="400"
									<%if(rankstr[4][0].equals("1")) {%>checked="checked"<%} %>/>新增仓库&nbsp;<input
										name="rankstr" type="checkbox" value="401"
										<%if(rankstr[4][1].equals("1")) {%>checked="checked"<%} %> />仓库查询&nbsp;<input
										name="rankstr" type="checkbox" value="402"
										<%if(rankstr[4][2].equals("1")) {%>checked="checked"<%} %> />入库管理
										&nbsp;<input name="rankstr" type="checkbox" value="403"
										<%if(rankstr[4][3].equals("1")) {%>checked="checked"<%} %> />入库审核
										&nbsp;<input
										name="rankstr" type="checkbox" value="405"
										<%if(rankstr[4][5].equals("1")) {%>checked="checked"<%} %> />出库管理
										&nbsp;<input name="rankstr" type="checkbox" value="406"
										<%if(rankstr[4][6].equals("1")) {%>checked="checked"<%} %> />出库审核
										&nbsp;<input name="rankstr" type="checkbox" value="404"
										<%if(rankstr[4][4].equals("1")) {%>checked="checked"<%} %> />库存汇总
										&nbsp;<input name="rankstr" type="checkbox" value="408"
										<%if(rankstr[4][8].equals("1")) {%>checked="checked"<%} %> />调库管理
										&nbsp;<input name="rankstr" type="checkbox" value="409"
										<%if(rankstr[4][9].equals("1")) {%>checked="checked"<%} %> />调库审核
										&nbsp;<input name="rankstr" type="checkbox" value="410"
										<%if(rankstr[4][10].equals("1")) {%>checked="checked"<%} %> />云仓查询
										&nbsp;<input name="rankstr" type="checkbox" value="411"
										<%if(rankstr[4][11].equals("1")) {%>checked="checked"<%} %> />云仓调拨
										&nbsp;<input name="rankstr" type="checkbox" value="412"
										<%if(rankstr[4][12].equals("1")) {%>checked="checked"<%} %> />调拨新增
										
										</td>
								</tr>
								<tr>
									<th>订单管理：</th>
									<td><input name="rankstr" type="checkbox" value="500"
										<%if(rankstr[5][0].equals("1")) {%>checked="checked"<%} %> />订单查询&nbsp;<input
										name="rankstr" type="checkbox" value="501"
										<%if(rankstr[5][1].equals("1")) {%>checked="checked"<%} %> />订单确认&nbsp;<input
										name="rankstr" type="checkbox" value="502"
										<%if(rankstr[5][2].equals("1")) {%>checked="checked"<%} %> />发货查询&nbsp;<input
										name="rankstr" type="checkbox" value="503"
										<%if(rankstr[5][3].equals("1")) {%>checked="checked"<%} %> />发货确认
										&nbsp;<input
										name="rankstr" type="checkbox" value="504"
										<%if(rankstr[5][4].equals("1")) {%>checked="checked"<%} %> />发货审核
										&nbsp;<input
										name="rankstr" type="checkbox" value="505"
										<%if(rankstr[5][5].equals("1")) {%>checked="checked"<%} %> />订单汇总
										&nbsp;<input
										name="rankstr" type="checkbox" value="506"
										<%if(rankstr[5][6].equals("1")) {%>checked="checked"<%} %> />订单导出
										&nbsp;<input
										name="rankstr" type="checkbox" value="507"
										<%if(rankstr[5][7].equals("1")) {%>checked="checked"<%} %> />明细导出
										</td>
								</tr>
								<tr>
									<th>财务管理：</th>
									<td><input name="rankstr" type="checkbox" value="600"
										<%if(rankstr[6][0].equals("1")) {%>checked="checked"<%} %> />现金充值&nbsp;<input
										name="rankstr" type="checkbox" value="601"
										<%if(rankstr[6][1].equals("1")) {%>checked="checked"<%} %> />充值审核&nbsp;<input
										name="rankstr" type="checkbox" value="602"
										<%if(rankstr[6][2].equals("1")) {%>checked="checked"<%} %> />提现审核&nbsp;<input
										name="rankstr" type="checkbox" value="603"
										<%if(rankstr[6][3].equals("1")) {%>checked="checked"<%} %> />账号管理
										&nbsp;<input
										name="rankstr" type="checkbox" value="604"
									<%if(rankstr[6][4].equals("1")) {%>checked="checked"<%} %> />余额汇总
										&nbsp;<input
										name="rankstr" type="checkbox" value="605"
										<%if(rankstr[6][5].equals("1")) {%>checked="checked"<%} %> />账户补扣
										&nbsp;<input
										name="rankstr" type="checkbox" value="606"
										<%if(rankstr[6][6].equals("1")) {%>checked="checked"<%} %> />补扣明细&nbsp;<input
										name="rankstr" type="checkbox" value="607"
										<%if(rankstr[6][7].equals("1")) {%>checked="checked"<%} %> />资金明细&nbsp;<input
										name="rankstr" type="checkbox" value="609"
										<%if(rankstr[6][9].equals("1")) {%>checked="checked"<%} %> />余额查询</td>
								</tr>
								<tr>
									<th>奖金管理：</th>
									<td><input name="rankstr" type="checkbox" value="700"
										<%if(rankstr[7][0].equals("1")) {%>checked="checked"<%} %> />佣金明细&nbsp;<input
										name="rankstr" type="checkbox" value="701"
										<%if(rankstr[7][1].equals("1")) {%>checked="checked"<%} %> />佣金发放&nbsp;<input
										name="rankstr" type="checkbox" value="702"
										<%if(rankstr[7][2].equals("1")) {%>checked="checked"<%} %> />佣金导出&nbsp;<input
										name="rankstr" type="checkbox" value="703"
										<%if(rankstr[7][3].equals("1")) {%>checked="checked"<%} %> />佣金结算&nbsp;
										&nbsp;<input
										name="rankstr" type="checkbox" value="704"
										<%if(rankstr[7][4].equals("1")) {%>checked="checked"<%} %> />结算明细&nbsp;
										&nbsp;<input
										name="rankstr" type="checkbox" value="705"
										<%if(rankstr[7][5].equals("1")) {%>checked="checked"<%} %> />结算导出&nbsp;
										</td>
								</tr>
								<tr>
									<th>报表统计：</th>
									<td><input name="rankstr" type="checkbox" value="800"
										<%if(rankstr[8][0].equals("1")) {%>checked="checked"<%} %> />销售业绩查询&nbsp;
										<input
										name="rankstr" type="checkbox" value="801"
										<%if(rankstr[8][1].equals("1")) {%>checked="checked"<%} %> />区域业绩汇总&nbsp;
										<input
										name="rankstr" type="checkbox" value="802"
										<%if(rankstr[8][2].equals("1")) {%>checked="checked"<%} %> />区域业绩查询
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
									<td><input name="rankstr" type="checkbox" value="915"
										<%if(rankstr[9][15].equals("1")) {%>checked="checked"<%} %> />管理员管理&nbsp;<input
										name="rankstr" type="checkbox" value="916"
										<%if(rankstr[9][16].equals("1")) {%>checked="checked"<%} %> />网站设置
										&nbsp;<input
										name="rankstr" type="checkbox" value="917"
										<%if(rankstr[9][17].equals("1")) {%>checked="checked"<%} %> />参数设置
										&nbsp;<input
										name="rankstr" type="checkbox" value="918"
										<%if(rankstr[9][18].equals("1")) {%>checked="checked"<%} %> />数据备份
										&nbsp;<input
										name="rankstr" type="checkbox" value="919"
										<%if(rankstr[9][19].equals("1")) {%>checked="checked"<%} %> />日志管理
										&nbsp;<input
										name="rankstr" type="checkbox" value="920"
										<%if(rankstr[9][20].equals("1")) {%>checked="checked"<%} %> />拓展政策
										&nbsp;<input
										name="rankstr" type="checkbox" value="921"
										<%if(rankstr[9][21].equals("1")) {%>checked="checked"<%} %> />新增政策
										&nbsp;<input
										name="rankstr" type="checkbox" value="922"
										<%if(rankstr[9][22].equals("1")) {%>checked="checked"<%} %> />特殊权限
										&nbsp;<input
										name="rankstr" type="checkbox" value="923"
										<%if(rankstr[9][23].equals("1")) {%>checked="checked"<%} %> />会员日志
										&nbsp;<input
										name="rankstr" type="checkbox" value="924"
										<%if(rankstr[9][24].equals("1")) {%>checked="checked"<%} %> />会员日志删除
										&nbsp;<input
										name="rankstr" type="checkbox" value="925"
										<%if(rankstr[9][25].equals("1")) {%>checked="checked"<%} %> />会员日志导出</td>
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