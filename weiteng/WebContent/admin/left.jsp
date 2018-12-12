<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.Admin"%>
<%@page import="com.ssm.utils.StringUtil"%>
<%@page import="com.ssm.utils.Constants"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin1 = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
	if (admin1 == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
	String[][] rank_left = StringUtil.getRankStr(admin1.getRank());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>导航栏</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>

<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
		}else{
			$(this).next('ul').slideDown();
		}
	});
});	
</script>


</head>

<body style="background:#f0f9fd;" class="leftbody">
	<a href="index.jsp" target="rightFrame"><span class="lefttop"><span></span>首页导航</span></a>
    <dl class="leftmenu">
     <%
				if(rank_left[0][0].equals("1")||rank_left[0][1].equals("1")
				||rank_left[0][2].equals("1")||rank_left[0][3].equals("1")
				||rank_left[0][4].equals("1")||rank_left[0][5].equals("1")
				||rank_left[0][6].equals("1")||rank_left[0][7].equals("1")
				||rank_left[0][10].equals("1")||rank_left[0][11].equals("1")
				||admin1.getState()-1==0){
		%>
    <dd>
    <div class="title">
    <span><img src="images/icons/16/message.png"/></span>信息管理
    </div>
    	<ul class="menuson">
    	 <%if(rank_left[0][1].equals("1")||admin1.getState()-1==0){%>
        <li><cite></cite><a href="NewsServlet?method=admin_news_list" target="rightFrame">新闻查询</a><i></i></li>
         <%}if(rank_left[0][0].equals("1")||admin1.getState()-1==0){%>
        <li><cite></cite><a href="NewsServlet?method=add" target="rightFrame">新闻新增</a><i></i></li>
 			<%}if(rank_left[0][5].equals("1")||admin1.getState()-1==0){%>
         <li><cite></cite><a href="ModuleServlet?method=list" target="rightFrame">模块查询</a><i></i></li>
         <%}if(rank_left[0][6].equals("1")||admin1.getState()-1==0){%>
        <li><cite></cite><a href="ModuleServlet?method=add" target="rightFrame">模块新增</a><i></i></li>
        <%} %>
        </ul>    
    </dd>
        <%} %>
     <%
				if(rank_left[1][0].equals("1")||rank_left[1][1].equals("1")
				||rank_left[1][2].equals("1")||rank_left[1][3].equals("1")
				||rank_left[1][4].equals("1")||rank_left[1][5].equals("1")
				||rank_left[1][6].equals("1")||rank_left[1][7].equals("1")
				||rank_left[1][10].equals("1")||rank_left[1][11].equals("1")
				||admin1.getState()-1==0){
		%>
    <dd>
    <div class="title">
    <span><img src="images/icons/16/user.png" /></span>会员管理
    </div>
    <ul class="menuson">
  		 <%if(rank_left[1][1].equals("1")||admin1.getState()-1==0){%>
        <li><cite></cite><a href="UserServlet?method=admin_list" target="rightFrame">会员列表</a><i></i></li>
             <%}if(rank_left[1][0].equals("1")||admin1.getState()-1==0){%>
    		 <li><cite></cite><a href="UserServlet?method=admin_user_add" target="rightFrame">会员新增</a><i></i></li>
         <%}if(rank_left[1][15].equals("1")||admin1.getState()-1==0){%>
			<li><cite></cite><a href="AgentApplyServlet?method=admin_list" target="rightFrame">代理申请</a><i></i></li>
          <%}if(rank_left[1][5].equals("1")||admin1.getState()-1==0){%>
 			<li><cite></cite><a href="user_modify.jsp" target="rightFrame">会员调整</a><i></i></li>
           <%}if(rank_left[1][6].equals("1")||admin1.getState()-1==0){%>
  			<li><cite></cite><a href="user_update_referee.jsp" target="rightFrame">邀请绑定</a><i></i></li>
 
       <%} %>
        </ul>     
    </dd> 
     <%} %>
      <%
				if(rank_left[2][0].equals("1")||rank_left[2][1].equals("1")
				||rank_left[2][2].equals("1")||rank_left[2][3].equals("1")
				||admin1.getState()-1==0){
		%>
     <dd>
    <div class="title">
    <span><img src="images/icons/16/network.png" /></span>网络管理
    </div>
    <ul class="menuson">
     <%if(rank_left[2][0].equals("1")||admin1.getState()-1==0){%>
        <li><cite></cite><a href="user_referee_json.jsp" target="rightFrame">关系查询</a><i></i></li>
       <%} %>
        </ul>     
    </dd> 
     <%	}
				if(rank_left[3][0].equals("1")||rank_left[3][1].equals("1")
				||rank_left[3][2].equals("1")||rank_left[3][3].equals("1")
				||rank_left[3][4].equals("1")||rank_left[3][5].equals("1")
				||rank_left[3][6].equals("1")||rank_left[3][7].equals("1")
				||rank_left[3][10].equals("1")||rank_left[3][11].equals("1")
				||admin1.getState()-1==0){
	%>
    <dd>
    <div class="title">
    <span><img src="images/icons/16/product.png" /></span>产品管理
    </div>
    <ul class="menuson">
        <%if(rank_left[3][0].equals("1")||admin1.getState()-1==0){%>
        <li><cite></cite><a href="ProductServlet?method=list"  target="rightFrame">产品查询</a><i></i></li>
         <%}if(rank_left[3][1].equals("1")||admin1.getState()-1==0){%>
        <li><cite></cite><a href="ProductServlet?method=add"  target="rightFrame">产品新增</a><i></i></li>
        <%}if(rank_left[3][6].equals("1")||admin1.getState()-1==0){%>
        <li><cite></cite><a href="ProductTypeServlet?method=list"  target="rightFrame">分类查询</a><i></i></li>
        <%}if(rank_left[3][7].equals("1")||admin1.getState()-1==0){%>
        <li><cite></cite><a href="ProductTypeServlet?method=addProductType"  target="rightFrame">分类新增</a><i></i></li>
       <%}if(rank_left[3][10].equals("1")||admin1.getState()-1==0){%>
        <li><cite></cite><a href="ProductSortServlet?method=list"  target="rightFrame">类型查询</a><i></i></li>
        <%}if(rank_left[3][11].equals("1")||admin1.getState()-1==0){%>
        <li><cite></cite><a href="ProductSortServlet?method=add"  target="rightFrame">类型新增</a><i></i></li>
        <%} %>
        </ul>     
    </dd> 
   <%	}
				if(rank_left[4][0].equals("1")||rank_left[4][1].equals("1")
				||rank_left[4][2].equals("1")||rank_left[4][3].equals("1")
				||rank_left[4][4].equals("1")||rank_left[4][5].equals("1")
				||rank_left[4][6].equals("1")||rank_left[4][7].equals("1")
				||rank_left[4][10].equals("1")||rank_left[4][11].equals("1")
				||admin1.getState()-1==0){
	%>
     <dd>
    <div class="title">
    <span><img src="images/icons/16/store.png" /></span>仓库管理
    </div>
    <ul class="menuson">
    <%if(rank_left[4][1].equals("1")||admin1.getState()-1==0){%>
        <li><cite></cite><a href="InventoryServlet?method=list"  target="rightFrame">仓库查询</a><i></i></li>
       <%}if(rank_left[4][0].equals("1")||admin1.getState()-1==0){%>
        <li><cite></cite><a href="InventoryServlet?method=add"  target="rightFrame">仓库新增</a><i></i></li>
 <%}if(rank_left[4][2].equals("1")||admin1.getState()-1==0){%>      
 <li><cite></cite><a href="InventoryServlet?method=apply_in"  target="rightFrame">入库查询</a><i></i></li>
 <%}if(rank_left[4][2].equals("1")||admin1.getState()-1==0){%>      
<li><cite></cite><a href="InventoryServlet?method=apply_in_add"  target="rightFrame">入库新增</a><i></i></li>
 <%}if(rank_left[4][3].equals("1")||admin1.getState()-1==0){%> 
<li><cite></cite><a href="InventoryServlet?method=apply_in_review"  target="rightFrame">入库审核</a><i></i></li>
 <%}if(rank_left[4][5].equals("1")||admin1.getState()-1==0){%>   
<li><cite></cite><a href="InventoryServlet?method=apply_out"  target="rightFrame">出库查询</a><i></i></li>
 <%}if(rank_left[4][5].equals("1")||admin1.getState()-1==0){%>
<li><cite></cite><a href="InventoryServlet?method=apply_out_add"  target="rightFrame">出库新增</a><i></i></li>
 <%}if(rank_left[4][6].equals("1")||admin1.getState()-1==0){%>
 <li><cite></cite><a href="InventoryServlet?method=apply_out_review"  target="rightFrame">出库审核</a><i></i></li>
  <%}if(rank_left[4][10].equals("1")||admin1.getState()-1==0){%>
 <li><cite></cite><a href="ProductStockServlet?method=admin_list"  target="rightFrame">云仓查询</a><i></i></li>
  <%}if(rank_left[4][11].equals("1")||admin1.getState()-1==0){%>
 <li><cite></cite><a href="ProductStockServlet?method=admin_transfers_list"  target="rightFrame">云仓调拨</a><i></i></li>
        <%} %>
        </ul>     
    </dd> 
     <%	}
				if(rank_left[5][0].equals("1")||rank_left[5][1].equals("1")
				||rank_left[5][2].equals("1")||rank_left[5][3].equals("1")
				||rank_left[5][4].equals("1")||rank_left[5][5].equals("1")
				||rank_left[5][6].equals("1")||rank_left[5][7].equals("1")
				||rank_left[5][10].equals("1")||rank_left[5][11].equals("1")
				||admin1.getState()-1==0){
	%>
     <dd>
    <div class="title">
    <span><img src="images/icons/16/orders.png" /></span>订单管理
    </div>
    <ul class="menuson">
     <%if(rank_left[5][0].equals("1")||admin1.getState()-1==0){%>
        <li><cite></cite><a href="OrderServlet?method=admin_orders" target="rightFrame">订单查询</a><i></i></li>
      <%}if(rank_left[5][1].equals("1")||admin1.getState()-1==0){%>
       <li><cite></cite><a href="OrderServlet?method=admin_order_review" target="rightFrame">订单确认</a><i></i></li>
     <%}if(rank_left[5][2].equals("1")||admin1.getState()-1==0){%> 
	 <li><cite></cite><a href="OrderDeliveryServlet?method=admin_orders" target="rightFrame">配货查询</a><i></i></li>
        <%}if(rank_left[5][3].equals("1")||admin1.getState()-1==0){%>
       <li><cite></cite><a href="OrderDeliveryServlet?method=admin_order_confirm" target="rightFrame">配货确认</a><i></i></li>
 <%}if(rank_left[5][4].equals("1")||admin1.getState()-1==0){%>       
 <li><cite></cite><a href="OrderDeliveryServlet?method=admin_order_review" target="rightFrame">配货出库</a><i></i></li>
       <%} %>
        </ul>     
    </dd> 
      <%	}
				if(rank_left[6][0].equals("1")||rank_left[6][1].equals("1")
				||rank_left[6][2].equals("1")||rank_left[6][3].equals("1")
				||rank_left[6][4].equals("1")||rank_left[6][5].equals("1")
				||rank_left[6][6].equals("1")||rank_left[6][7].equals("1")
				||rank_left[6][10].equals("1")||rank_left[6][11].equals("1")
				||admin1.getState()-1==0){
	%>
     <dd>
    <div class="title">
    <span><img src="images/icons/16/money.png" /></span>财务管理
    </div>
    <ul class="menuson">
    <%if(rank_left[6][2].equals("1")||admin1.getState()-1==0){%>
        <li><cite></cite><a href="WithDrewServlet?method=admin_list"  target="rightFrame">提现申请</a><i></i></li>
     <%}if(rank_left[6][6].equals("1")||admin1.getState()-1==0){%>
        <li><cite></cite><a href="AccountSupplementServlet?method=list"  target="rightFrame">增补管理</a><i></i></li>
       <%}if(rank_left[6][7].equals("1")||admin1.getState()-1==0){%>
        <li><cite></cite><a href="AccountServlet?method=admin_money_detail"  target="rightFrame">账户明细</a><i></i></li>
       <%} %>
        </ul>     
    </dd> 
     <%	}
				if(rank_left[7][0].equals("1")||rank_left[7][1].equals("1")
				||rank_left[7][2].equals("1")||rank_left[7][3].equals("1")
				||rank_left[7][4].equals("1")||rank_left[7][5].equals("1")
				||rank_left[7][6].equals("1")||rank_left[7][7].equals("1")
				||rank_left[7][10].equals("1")||rank_left[7][11].equals("1")
				||admin1.getState()-1==0){
	%>
     <dd>
    <div class="title">
    <span><img src="images/icons/16/reward.png" /></span>奖金管理
    </div>
    <ul class="menuson">
      <%if(rank_left[7][0].equals("1")||admin1.getState()-1==0){%>
        <li><cite></cite><a href="RewardDetailServlet?method=admin_list"  target="rightFrame">佣金明细</a><i></i></li>
          <%}if(rank_left[7][3].equals("1")||admin1.getState()-1==0){%>
        <li><cite></cite><a href="RewardServlet?method=admin_reward_month"  target="rightFrame">佣金结算</a><i></i></li>
         <%}if(rank_left[7][4].equals("1")||admin1.getState()-1==0){%>
        <li><cite></cite><a href="RewardServlet?method=admin_reward_month_list"  target="rightFrame">结算明细</a><i></i></li>
       <%} %>
        </ul>     
    </dd> 
    <%} %>
    <!-- 
     <dd>
    <div class="title">
    <span><img src="images/icons/16/report-form.png" /></span>报表管理
    </div>
    <ul class="menuson">
        <li><cite></cite><a href="#">编辑内容</a><i></i></li>
        <li><cite></cite><a href="#">发布信息</a><i></i></li>
        <li><cite></cite><a href="#">档案列表显示</a><i></i></li>
        </ul>     
    </dd> 
     -->
      <%
			     if(rank_left[9][0].equals("1")||rank_left[9][1].equals("1")
				||rank_left[9][2].equals("1")||rank_left[9][3].equals("1")
				||rank_left[9][4].equals("1")||rank_left[9][5].equals("1")
				||rank_left[9][6].equals("1")||rank_left[9][7].equals("1")
				||rank_left[9][8].equals("1")||rank_left[9][9].equals("1")
				||rank_left[9][10].equals("10")||rank_left[9][11].equals("11")
					||admin1.getState()-1==0){%>
     <dd>
    <div class="title">
    <span><img src="images/icons/16/job.png" /></span>员工管理
    </div>
    <ul class="menuson">
    	 <%if(rank_left[9][0].equals("1")||admin1.getState()-1==0){%>
        <li><cite></cite><a href="AdminServlet?method=list"  target="rightFrame">员工查询</a><i></i></li>
         <%}if(rank_left[9][1].equals("1")||admin1.getState()-1==0){%>
        <li><cite></cite><a href="AdminServlet?method=addAdmin"  target="rightFrame">员工新增</a><i></i></li>
        <%}if(rank_left[9][6].equals("1")||admin1.getState()-1==0){%>
        <li><cite></cite><a href="DeptServlet?method=list"  target="rightFrame">部门查询</a><i></i></li>
       	 <%}if(rank_left[9][7].equals("1")||admin1.getState()-1==0){%>
        <li><cite></cite><a href="DeptServlet?method=addDept"  target="rightFrame">部门新增</a><i></i></li>
		 <%}if(rank_left[9][10].equals("1")||admin1.getState()-1==0){%>
 <li><cite></cite><a href="JobServlet?method=list"  target="rightFrame">职务查询</a><i></i></li>
       	<%}if(rank_left[9][11].equals("1")||admin1.getState()-1==0){%>
               <li><cite></cite><a href="JobServlet?method=addJob"  target="rightFrame">职务新增</a><i></i></li>
       <%} %>
        </ul>     
    </dd> 
    <%}
			     if(rank_left[9][15].equals("1")||rank_left[9][16].equals("1")
				||rank_left[9][17].equals("1")||rank_left[9][18].equals("1")
				||rank_left[9][19].equals("1")||rank_left[9][20].equals("1")
				||rank_left[9][21].equals("1")||rank_left[9][22].equals("1")
				||rank_left[9][23].equals("1")||rank_left[9][24].equals("1")
				||rank_left[9][25].equals("10")||admin1.getState()-1==0){%>
    <dd><div class="title"><span><img src="images/icons/16/settings.png" /></span>其他设置</div>
    <ul class="menuson">
     <%if(admin1.getState()-1==0){%> 
        <li><cite></cite><a href="RewardServlet?method=initSettle" target="rightFrame">结算初始化</a><i></i></li>
        <%}if(rank_left[9][17].equals("10")||admin1.getState()-1==0){%> 
         <li><cite></cite><a href="ParamServlet?method=list" target="rightFrame">参数设置</a><i></i></li>
         <%} %>
         <li><cite></cite><a href="AdminServlet?method=admin_psw_edit" target="rightFrame">密码修改</a><i></i></li>
        <%if(rank_left[9][19].equals("10")||admin1.getState()-1==0){%> 
        <li><cite></cite><a href="AdminLogServlet?method=list" target="rightFrame">日志查询</a><i></i></li>
        <%} %>
          <li><cite></cite><a href="#" target="rightFrame">数据备份</a><i></i></li>
        
    </ul>    
    </dd>  
    <%} %>
    <dd><div class="title"><span><img src="images/icons/16/exit.png" /></span><a href="AdminServlet?method=logout"  target="rightFrame">退出系统</a></div>
    </dd>   
    </dl>
</body>
</html>
<%}%>