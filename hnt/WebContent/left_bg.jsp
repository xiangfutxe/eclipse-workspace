<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.Admin"%>
<%@page import="com.ssm.utils.StringUtil"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin1 = (Admin) request.getSession().getAttribute("sys_admin");
	if (admin1 == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
	String[][] rankstr = StringUtil.getRankStr(admin1.getRank());
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
<SCRIPT language=javascript>
	function expand(el)
	{
		childObj = document.getElementById("child" + el);

		if (childObj.style.display == 'none')
		{
			childObj.style.display = 'block';
		}
		else
		{
			childObj.style.display = 'none';
		}
		return;
	}
</SCRIPT>
</HEAD>
<BODY class="body">
<div class="container clearfix">
    <div class="sidebar-wrap">
        <div class="sidebar-title">
            <h1><a href="admin/index.jsp" target="main">管理中心</a></h1>
        </div>
        <div class="sidebar-content">
            <ul class="sidebar-list">
          <%if(rankstr[0][0].equals("1")||rankstr[0][1].equals("1")||rankstr[0][2].equals("1")||rankstr[0][3].equals("1")||admin1.getState().equals("1")){%>
			 <li>
                    <a onclick="expand(1)" href="javascript:void(0);"><i class="icon-font">&#xe049;</i>信息管理</a>
	                    <ul id="child1" style="DISPLAY: none" class="sub-menu">
	                     <%if(rankstr[0][0].equals("1")||admin1.getState().equals("1")){%>
					 <li><a href="admin/NewsServlet?method=admin_news_add" target="main"><i class="icon-font">&#xe026;</i>添加新闻</a></li>
						  <%}if(rankstr[0][1].equals("1")||admin1.getState().equals("1")){%>
						  <li><a href="admin/NewsServlet?method=admin_news_list" target="main"><i class="icon-font">&#xe02f;</i>新闻列表</a></li>
						  <%}if(rankstr[0][2].equals("1")||admin1.getState().equals("1")){%>
						  <li><a href="admin/MessageServlet?method=admin_list" target="main"><i class="icon-font">&#xe026;</i>留言簿</a></li>
	                       <%}%>
	                    </ul>
                </li>
                <%} 
				if(rankstr[1][0].equals("1")||rankstr[1][1].equals("1")||rankstr[1][2].equals("1")
				||rankstr[1][3].equals("1")||rankstr[1][4].equals("1")||rankstr[1][5].equals("1")
				||rankstr[1][6].equals("1")||rankstr[1][7].equals("1")||rankstr[1][8].equals("1")
				||rankstr[1][9].equals("1")||rankstr[1][10].equals("1")||rankstr[1][11].equals("1")
				||rankstr[1][12].equals("1")||rankstr[1][13].equals("1")||rankstr[1][14].equals("1")
				||rankstr[1][15].equals("1")||rankstr[1][16].equals("1")||rankstr[1][17].equals("1")
				||rankstr[1][8].equals("1")||rankstr[1][19].equals("1")||rankstr[1][20].equals("1")
				||rankstr[1][21].equals("1")||rankstr[1][22].equals("1")||rankstr[1][23].equals("1")
				||rankstr[1][24].equals("1")||rankstr[1][25].equals("1")||admin1.getState().equals("1")){%>
				  <li>
                    <a onclick="expand(2)" href="javascript:void(0);"><i class="icon-font">&#xe014;</i>会员管理</a>
	                    <ul id="child2" style="DISPLAY: none" class="sub-menu">
	                    <%if(rankstr[1][0].equals("1")||admin1.getState().equals("1")){%>
						  <li><a href="admin/UserServlet?method=admin_user_add" target="main"><i class="icon-font">&#xe026;</i>空单注册</a></li>
						  <%}if(rankstr[1][1].equals("1")||admin1.getState().equals("1")){%>
						   <li><a href="admin/UserServlet?method=admin_list" target="main"><i class="icon-font">&#xe003;</i>会员列表</a></li>
						 <%}if(rankstr[1][5].equals("1")||admin1.getState().equals("1")){%>
						   <li><a href="admin/UserServlet?method=admin_user_hide_list" target="main"><i class="icon-font">&#xe003;</i>公司会员</a></li>
						<%}if(rankstr[1][6].equals("1")||admin1.getState().equals("1")){%>
							<li><a href="admin/UserServlet?method=admin_user_money_list" target="main"><i class="icon-font">&#xe032;</i>会员账户</a>
							</li>
							<%}if(rankstr[1][25].equals("1")||admin1.getState().equals("1")){%>
							<li><a href="admin/UserServlet?method=admin_special_list" target="main"><i class="icon-font">&#xe032;</i>高级查询</a>
							</li>
						<%}if(rankstr[1][10].equals("1")||admin1.getState().equals("1")){%>
							<li><a href="admin/JoinInfoServlet?method=admin_list" target="main"><i class="icon-font">&#xe007;</i>升级管理</a></li>
						<%}if(rankstr[1][7].equals("1")||admin1.getState().equals("1")){%>
							<li><a href="admin/rankJoin_up.jsp" target="main"><i class="icon-font">&#xe013;</i>等级变更</a></li>
	                     <%}if(rankstr[1][8].equals("1")||admin1.getState().equals("1")){%>
							<li><a href="admin/user_referee_update.jsp" target="main"><i class="icon-font">&#xe045;</i>服务商变更</a></li>
							<%}if(rankstr[1][9].equals("1")||admin1.getState().equals("1")){%>
							<li><a href="admin/user_belong_update.jsp" target="main"><i class="icon-font">&#xe021;</i>销售商变更</a></li>
							<%}if(rankstr[1][12].equals("1")||admin1.getState().equals("1")){%>
							<li><a href="admin/user_empty_update.jsp" target="main"><i class="icon-font">&#xe035;</i>实单升级</a></li>
	                        <%}if(rankstr[1][15].equals("1")||admin1.getState().equals("1")){%>
							<li><a href="admin/CenterServlet?method=list" target="main"><i class="icon-font">&#xe034;</i>服务中心</a>
							</li>
							<%}if(rankstr[1][13].equals("1")||admin1.getState().equals("1")){%>
							<li><a href="admin/EmptyNumServlet?method=list" target="main"><i class="icon-font">&#xe008;</i>空单名额</a>
							</li>
							<%}if(rankstr[1][14].equals("1")||admin1.getState().equals("1")){%>
							<li><a href="admin/EmptyNumServlet?method=admin_add" target="main"><i class="icon-font">&#xe026;</i>新增名额</a>
							
							</li>
							<%}if(rankstr[1][19].equals("1")||admin1.getState().equals("1")){%>
							<li><a href="admin/rankJoin_update_detail_1.jsp" target="main"><i class="icon-font">&#xe026;</i>级别调整</a>
							
							</li>
							<%}if(rankstr[1][20].equals("1")||admin1.getState().equals("1")){%>
							<li><a href="admin/JoinUpdateDetailServlet?method=admin_list" target="main"><i class="icon-font">&#xe008;</i>调整列表</a>
							</li>
							<%}if(rankstr[1][22].equals("1")||admin1.getState().equals("1")){%>
							<li><a href="admin/rankManage_update_detail_1.jsp" target="main"><i class="icon-font">&#xe026;</i>管理级别调整</a>
							
							</li>
							<%}if(rankstr[1][23].equals("1")||admin1.getState().equals("1")){%>
							<li><a href="admin/RankManageUpdateDetailServlet?method=admin_list" target="main"><i class="icon-font">&#xe008;</i>管理级别调整列表</a>
							</li>
	                       <%}%>
	                    </ul>
               		 </li>
                <%} 
				if(rankstr[2][0].equals("1")||rankstr[2][1].equals("1")
				||rankstr[2][2].equals("1")||rankstr[2][3].equals("1")
				||rankstr[2][4].equals("1")||rankstr[2][5].equals("1")||admin1.getState().equals("1")){%>
				 <li>
                    <a onclick="expand(3)" href="javascript:void(0);"><i class="icon-font">&#xe05e;</i>网络管理</a>
	                    <ul id="child3" style="DISPLAY: none" class="sub-menu">
	                    <%if(rankstr[2][0].equals("1")||admin1.getState().equals("1")){%>
						<li><a href="admin/user_referee_json.jsp" target="main"><i class="icon-font">&#xe041;</i>服务关系(树形)</a></li>
						<%}if(rankstr[2][1].equals("1")||admin1.getState().equals("1")){%>
							  <li><a href="admin/user_belong_json.jsp" target="main"><i class="icon-font">&#xe042;</i>销售关系(树形)</a></li>
						 <%}if(rankstr[2][2].equals("1")||admin1.getState().equals("1")){%>
						<li><a href="admin/user_referee_reback_list.jsp" target="main"><i class="icon-font">&#xe041;</i>服务关系(反向)</a></li>
						<%}if(rankstr[2][3].equals("1")||admin1.getState().equals("1")){%>
							  <li><a href="admin/user_belong_reback_list.jsp" target="main"><i class="icon-font">&#xe042;</i>销售关系(反向)</a></li>
						<%}if(rankstr[2][4].equals("1")||admin1.getState().equals("1")){%>
							  <li><a href="admin/user_furthest_node.jsp" target="main"><i class="icon-font">&#xe042;</i>远端会员</a></li>
							  <%}if(rankstr[2][5].equals("1")||admin1.getState().equals("1")){%>
							  <li><a href="admin/user_team_rankmanage.jsp" target="main"><i class="icon-font">&#xe042;</i>团队会员奖衔</a></li>
	                     <%}%>
	                    </ul>
               		 </li>
               		 <%} 
				if(rankstr[3][0].equals("1")||rankstr[3][1].equals("1")
				||rankstr[3][2].equals("1")||rankstr[3][3].equals("1")
				||rankstr[3][4].equals("1")||rankstr[3][5].equals("1")
				||rankstr[3][6].equals("1")||rankstr[3][7].equals("1")
				||rankstr[3][10].equals("1")||admin1.getState().equals("1")){%>
				 <li>
                    <a onclick="expand(4)" href="javascript:void(0);"><i class="icon-font">&#xe05f;</i>产品管理</a>
                    <ul id="child4" style="DISPLAY: none" class="sub-menu">
                    <%if(rankstr[3][0].equals("1")||admin1.getState().equals("1")){%>
					   <li><a href="admin/ProductServlet?method=list" target="main"><i class="icon-font">&#xe005;</i>产品列表</a></li>
					<%}if(rankstr[3][1].equals("1")||admin1.getState().equals("1")){%>
					     <li><a href="admin/ProductTypeServlet?method=list" target="main"><i class="icon-font">&#xe048;</i>分类管理</a></li>
					  <%}if(rankstr[3][10].equals("1")||admin1.getState().equals("1")){%>
					     <li><a href="admin/ProductSortServlet?method=list" target="main"><i class="icon-font">&#xe048;</i>类型管理</a></li>
					<%}%>
                    </ul>
                </li>
                <%} 
				if(rankstr[4][0].equals("1")||rankstr[4][1].equals("1")
				||rankstr[4][2].equals("1")||rankstr[4][3].equals("1")
				||rankstr[4][4].equals("1")||rankstr[4][5].equals("1")
				||rankstr[4][6].equals("1")||admin1.getState().equals("1")){%>
				 <li>
                    <a onclick="expand(5)" href="javascript:void(0);"><i class="icon-font">&#xe041;</i>仓库管理</a>
                    <ul id="child5" style="DISPLAY: none" class="sub-menu">
                    <%if(rankstr[4][0].equals("1")||admin1.getState().equals("1")){%>
						 <li><a href="admin/InventoryServlet?method=add" target="main"><i class="icon-font">&#xe001;</i>新增仓库</a></li>
					<%}if(rankstr[4][1].equals("1")||admin1.getState().equals("1")){%>
						 <li><a href="admin/InventoryServlet?method=list" target="main"><i class="icon-font">&#xe005;</i>仓库列表</a></li>
					<%}if(rankstr[4][2].equals("1")||admin1.getState().equals("1")){%>
					 <li><a href="admin/InventoryServlet?method=apply_in" target="main"><i class="icon-font">&#xe046;</i>入库管理</a></li>
					<%}if(rankstr[4][3].equals("1")||admin1.getState().equals("1")){%>
					  <li><a href="admin/InventoryServlet?method=apply_in_review" target="main"><i class="icon-font">&#xe01f;</i>入库审核</a></li>
					<%}if(rankstr[4][5].equals("1")||admin1.getState().equals("1")){%>
					 <li><a href="admin/InventoryServlet?method=apply_out" target="main"><i class="icon-font">&#xe046;</i>出库管理</a></li>
					 <%}if(rankstr[4][6].equals("1")||admin1.getState().equals("1")){%>
					  <li><a href="admin/InventoryServlet?method=apply_out_review" target="main"><i class="icon-font">&#xe01f;</i>出库审核</a></li>
					<%}if(rankstr[4][4].equals("1")||admin1.getState().equals("1")){%>
					  <li><a href="admin/InventoryServlet?method=admin_inventory_order_summary" target="main"><i class="icon-font">&#xe05b;</i>库存查询</a></li>
					 <%}%>
                    </ul>
                </li>
                <%} 
				if(rankstr[5][0].equals("1")||rankstr[5][1].equals("1")||rankstr[5][2].equals("1")
				||rankstr[5][3].equals("1")||rankstr[5][4].equals("1")||admin1.getState().equals("1")){%>
				  <li>
                    <a onclick="expand(6)" href="javascript:void(0);"><i class="icon-font">&#xe005;</i>订单管理</a>
                    <ul id="child6" style="DISPLAY: none" class="sub-menu">
                    <%if(rankstr[5][0].equals("1")||admin1.getState().equals("1")){%>
				  <li><a href="admin/OrderServlet?method=admin_orders" target="main"><i class="icon-font">&#xe032;</i>订单列表</a></li>
				 <%}if(rankstr[5][4].equals("1")||admin1.getState().equals("1")){%>
				   <li><a href="admin/OrderServlet?method=admin_order_check" target="main"><i class="icon-font">&#xe034;</i>订单列表(客服)</a></li>
				   <%}if(rankstr[5][1].equals("1")||admin1.getState().equals("1")){%>
				   <li><a href="admin/OrderServlet?method=admin_order_confirm" target="main"><i class="icon-font">&#xe034;</i>订单确认</a></li>
				   <%}if(rankstr[5][2].equals("1")||admin1.getState().equals("1")){%>
					   <li><a href="admin/OrderServlet?method=admin_order_delivery_wait" target="main"><i class="icon-font">&#xe042;</i>发货管理</a></li>
						 <li><a href="admin/OrderServlet?method=admin_order_delivery" target="main"><i class="icon-font">&#xe042;</i>订单配货</a></li>
							
				  <%}if(rankstr[5][3].equals("1")||admin1.getState().equals("1")){%>
					   <li><a href="admin/OrderServlet?method=admin_order_review" target="main"><i class="icon-font">&#xe042;</i>发货审核</a></li>
							<%}%>
                    </ul>
               </li> 
                 <%} 
				if(rankstr[6][0].equals("1")||rankstr[6][1].equals("1")||rankstr[6][2].equals("1")||rankstr[6][3].equals("1")||rankstr[6][4].equals("1")||rankstr[6][5].equals("1")||rankstr[6][6].equals("1")
					||rankstr[6][7].equals("1")||rankstr[6][8].equals("1")||admin1.getState().equals("1")){%>
            <li>
                    <a onclick="expand(7)" href="javascript:void(0);"><i class="icon-font">&#xe053;</i>财务管理</a>
                    <ul id="child7" style="DISPLAY: none" class="sub-menu">
                    <%if(rankstr[6][0].equals("1")||admin1.getState().equals("1")){%>
					 <li><a href="admin/ChargeApplyServlet?method=emoney_quick_add" target="main"><i class="icon-font">&#xe035;</i>现金充值</a></li>
					 <%}if(rankstr[6][1].equals("1")||admin1.getState().equals("1")){%>
					 <li><a href="admin/ChargeApplyServlet?method=admin_list" target="main"><i class="icon-font">&#xe01f;</i>充值审核</a></li>
					<%}if(rankstr[6][2].equals("1")||admin1.getState().equals("1")){%>
						<li><a href="admin/WithDrewServlet?method=admin_list" target="main"><i class="icon-font">&#xe011;</i>提现审核</a></li>
					<%}if(rankstr[6][3].equals("1")||admin1.getState().equals("1")){%>
						<li><a href="admin/BankAccountServlet?method=list" target="main"><i class="icon-font">&#xe032;</i>账号管理</a></li>
					<%}if(rankstr[6][4].equals("1")||admin1.getState().equals("1")){%>
						<li><a href="admin/AccountServlet?method=admin_money_balance" target="main"><i class="icon-font">&#xe033;</i>余额汇总</a></li>
					<%}if(rankstr[6][9].equals("1")||admin1.getState().equals("1")){%>
						<li><a href="admin/AccountServlet?method=admin_money_summary" target="main"><i class="icon-font">&#xe036;</i>余额查询</a></li>
					<%}if(rankstr[6][5].equals("1")||admin1.getState().equals("1")){%>
						 <li><a href="admin/AccountSupplementServlet?method=add" target="main"><i class="icon-font">&#xe034;</i>账户补扣</a></li>
					<%}if(rankstr[6][6].equals("1")||admin1.getState().equals("1")){%>
						  <li><a href="admin/AccountSupplementServlet?method=list" target="main"><i class="icon-font">&#xe035;</i>补扣明细</a></li>
					<%}if(rankstr[6][7].equals("1")||admin1.getState().equals("1")){%>
						  <li><a href="admin/money_detail.jsp" target="main"><i class="icon-font">&#xe032;</i>资金明细</a></li>
					<%}if(rankstr[6][8].equals("1")||admin1.getState().equals("1")){%>
						 <li><a href="admin/AccountServlet?method=pmoney_list" target="main"><i class="icon-font">&#xe01f;</i>支持币管理</a></li>
					<%}%>
                    </ul>
               </li> 
			   <%} 
				if(rankstr[7][0].equals("1")||rankstr[7][1].equals("1")||admin1.getState().equals("1")
				||rankstr[7][2].equals("1")||admin1.getState().equals("1")||rankstr[7][3].equals("1")||admin1.getState().equals("1")
				||rankstr[7][4].equals("1")||admin1.getState().equals("1")||rankstr[7][5].equals("1")||admin1.getState().equals("1")
				||rankstr[7][6].equals("1")||rankstr[7][7].equals("1")
				||rankstr[7][8].equals("1")||rankstr[7][9].equals("1")
				||rankstr[7][10].equals("1")||rankstr[7][11].equals("1")
				||rankstr[7][12].equals("1")||rankstr[7][13].equals("1")
				||rankstr[7][20].equals("1")||rankstr[7][21].equals("1")
				||rankstr[7][22].equals("1")||rankstr[7][23].equals("1")
				||rankstr[7][24].equals("1")||rankstr[7][25].equals("1")
				||rankstr[7][26].equals("1")||rankstr[7][27].equals("1")
				||rankstr[7][28].equals("1")||rankstr[7][29].equals("1")||admin1.getState().equals("1")){%>
				<li>
                    <a onclick="expand(8)" href="javascript:void(0);"><i class="icon-font">&#xe054;</i>奖金管理</a>
                    <ul id="child8" style="DISPLAY: none" class="sub-menu">
                     <%if(rankstr[7][20].equals("1")||admin1.getState().equals("1")){%>
					<li><a href="admin/DRewardServlet?method=admin_reward_day" target="main"><i class="icon-font">&#xe048;</i>奖金结算(日)</a></li>					
					<%}if(rankstr[7][21].equals("1")||admin1.getState().equals("1")){%>				
					 <li><a href="admin/DRewardServlet?method=admin_reward_day_list" target="main"><i class="icon-font">&#xe02f;</i>奖金明细(日)</a></li>
					 <%}if(rankstr[7][28].equals("1")||admin1.getState().equals("1")){%>
						 <li><a href="admin/DRewardServlet?method=admin_reward_day_hide_list" target="main"><i class="icon-font">&#xe02f;</i>公司明细(日)</a></li>
					<%}if(rankstr[7][29].equals("1")||admin1.getState().equals("1")){%>
						 <li><a href="admin/DRewardServlet?method=admin_reward_day_paytag_list" target="main"><i class="icon-font">&#xe02f;</i>冻点明细(日)</a></li>
					<%}if(rankstr[7][22].equals("1")||admin1.getState().equals("1")){%>
					<li><a href="admin/DRewardServlet?method=admin_reward_day_summary" target="main"><i class="icon-font">&#xe013;</i>奖金汇总(日)</a></li>
						  
					<%}if(rankstr[7][26].equals("1")||admin1.getState().equals("1")){%>
						 <li><a href="admin/DRewardServlet?method=admin_reward_day_hide_summary" target="main"><i class="icon-font">&#xe02f;</i>公司奖金(日)</a></li>
						<%}if(rankstr[7][23].equals("1")||admin1.getState().equals("1")){%>
						 <li><a href="admin/DRewardServlet?method=admin_reward_day_empty_summary" target="main"><i class="icon-font">&#xe02f;</i>空单奖金(日)</a></li>
					
                      <%}if(rankstr[7][0].equals("1")||admin1.getState().equals("1")){%>
                    <li><a href="admin/RewardServlet?method=admin_reward_week" target="main"><i class="icon-font">&#xe048;</i>奖金结算</a></li>
					<%}if(rankstr[7][1].equals("1")||admin1.getState().equals("1")){%>				
					 <li><a href="admin/RewardServlet?method=admin_reward_week_list" target="main"><i class="icon-font">&#xe02f;</i>奖金明细</a></li>
					 <%}if(rankstr[7][8].equals("1")||admin1.getState().equals("1")){%>
						 <li><a href="admin/RewardServlet?method=admin_reward_week_hide_list" target="main"><i class="icon-font">&#xe02f;</i>公司明细</a></li>
					<%}if(rankstr[7][9].equals("1")||admin1.getState().equals("1")){%>
						 <li><a href="admin/RewardServlet?method=admin_reward_week_paytag_list" target="main"><i class="icon-font">&#xe02f;</i>冻点明细</a></li>
					<%}if(rankstr[7][2].equals("1")||admin1.getState().equals("1")){%>
						  <li><a href="admin/RewardServlet?method=admin_reward_week_summary" target="main"><i class="icon-font">&#xe013;</i>奖金汇总</a></li>						  
					<%}if(rankstr[7][6].equals("1")||admin1.getState().equals("1")){%>
						 <li><a href="admin/RewardServlet?method=admin_reward_week_hide_summary" target="main"><i class="icon-font">&#xe02f;</i>公司奖金</a></li>
						<%}if(rankstr[7][3].equals("1")||admin1.getState().equals("1")){%>
						 <li><a href="admin/RewardServlet?method=admin_reward_week_empty_summary" target="main"><i class="icon-font">&#xe02f;</i>空单奖金</a></li>
					<%}if(rankstr[7][10].equals("1")||admin1.getState().equals("1")){%>				
					 <li><a href="admin/RewardServlet?method=admin_reward_extreme_week_list" target="main"><i class="icon-font">&#xe02f;</i>至尊明细</a></li>
							<%}if(admin1.getState().equals("1")){%>				
					 <li><a href="admin/RewardServlet?method=admin_reward_extra_import_add" target="main"><i class="icon-font">&#xe02f;</i>奖金补差</a></li>
					 <%}%>
                    </ul>
               </li> 
              <%} 
				if(rankstr[8][0].equals("1")||rankstr[8][1].equals("1")
				||rankstr[8][2].equals("1")||rankstr[8][3].equals("1")
				||rankstr[8][4].equals("1")||rankstr[8][5].equals("1")
				||rankstr[8][6].equals("1")||rankstr[8][7].equals("1")
				||rankstr[8][8].equals("1")||rankstr[8][9].equals("1")
					||admin1.getState().equals("1")){%>
				 <li>
                    <a onclick="expand(9)" href="javascript:void(0);"><i class="icon-font">&#xe055;</i>报表统计</a>
                    <ul id="child9" style="DISPLAY: none" class="sub-menu">
                     <%if(rankstr[8][0].equals("1")||admin1.getState().equals("1")){%>
                     <li><a href="admin/ReportServlet?method=admin_report_order" target="main"><i class="icon-font">&#xe05b;</i>销售业绩查询</a></li>
                     
                      <%}if(rankstr[8][2].equals("1")||admin1.getState().equals("1")){%>
						  <li><a href="admin/ReportServlet?method=admin_report_settle_day" target="main"><i class="icon-font">&#xe05b;</i>已结业绩查询(日)</a></li>
                     <%}if(rankstr[8][1].equals("1")||admin1.getState().equals("1")){%>
						  <li><a href="admin/ReportServlet?method=admin_report_wsettle_1" target="main"><i class="icon-font">&#xe05b;</i>待结业绩查询</a></li>
						    <%}if(rankstr[8][2].equals("1")||admin1.getState().equals("1")){%>
						  <li><a href="admin/ReportServlet?method=admin_report_wsettle" target="main"><i class="icon-font">&#xe05b;</i>已结业绩查询</a></li>
						     <%}if(rankstr[8][3].equals("1")||admin1.getState().equals("1")){%>
						  <li><a href="admin/ReportServlet?method=admin_report_wsettle_summary" target="main"><i class="icon-font">&#xe05b;</i>已结业绩汇总</a></li>
						   <%}if(rankstr[8][5].equals("1")||admin1.getState().equals("1")){%>
						  <li><a href="admin/report_segment_performance.jsp" target="main"><i class="icon-font">&#xe05b;</i>分段业绩汇总</a></li>
						  
						    <%}if(rankstr[8][10].equals("1")||admin1.getState().equals("1")){%>
						  <li><a href="admin/report_export_excel.jsp" target="main"><i class="icon-font">&#xe05b;</i>伞下信息导出</a></li>
						    <%}if(rankstr[8][11].equals("1")||admin1.getState().equals("1")){%>
						  <li><a href="admin/report_referee_performance.jsp" target="main"><i class="icon-font">&#xe05b;</i>直推业绩查询</a></li>
						  <%}if(rankstr[8][12].equals("1")||admin1.getState().equals("1")){%>
						  <li><a href="admin/report_referee_performance_summary.jsp" target="main"><i class="icon-font">&#xe05b;</i>伞下推荐汇总</a></li>
						   <%}if(rankstr[8][13].equals("1")||admin1.getState().equals("1")){%>
						  <li><a href="admin/report_referee_user_summary.jsp" target="main"><i class="icon-font">&#xe05b;</i>伞下会员汇总</a></li>
						  <%}if(rankstr[8][14].equals("1")||admin1.getState().equals("1")){%>
						  <li><a href="admin/ReportServlet?method=report_finance_excel" target="main"><i class="icon-font">&#xe05b;</i>财务汇总导出</a></li>
 <%}%>
                    </ul>
               </li>  
			     <%} 
			     if(rankstr[9][0].equals("1")||rankstr[9][1].equals("1")
				||rankstr[9][2].equals("1")||rankstr[9][3].equals("1")
				||rankstr[9][4].equals("1")||rankstr[9][5].equals("1")
				||rankstr[9][6].equals("1")||rankstr[9][7].equals("1")
				||rankstr[9][8].equals("1")||rankstr[9][9].equals("1")
				||rankstr[9][10].equals("10")||rankstr[9][11].equals("11")
					||admin1.getState().equals("1")){%>
			    <li>
                    <a onclick="expand(11)" href="javascript:void(0);"><i class="icon-font">&#xe018;</i>员工管理</a>
                    <ul id="child11" style="DISPLAY: none" class="sub-menu">
					  <%if(rankstr[9][0].equals("1")||admin1.getState().equals("1")){%>
                        <li><a href="admin/AdminServlet?method=list" target="main"><i class="icon-font">&#xe005;</i>员工列表</a></li>
					  
					 <%}if(rankstr[9][6].equals("1")||admin1.getState().equals("1")){%>
                        <li><a href="admin/DeptServlet?method=list" target="main"><i class="icon-font">&#xe005;</i>部门列表</a></li>
					  <%}if(rankstr[9][7].equals("1")||admin1.getState().equals("1")){%>
                        <li><a href="admin/DeptServlet?method=addDept" target="main"><i class="icon-font">&#xe005;</i>新增部门</a></li>
					 
					 <%}if(rankstr[9][10].equals("1")||admin1.getState().equals("1")){%>
						 <li><a href="admin/JobServlet?method=list" target="main"><i class="icon-font">&#xe017;</i>职务列表</a></li>
						 <%}if(rankstr[9][11].equals("1")||admin1.getState().equals("1")){%>
						 <li><a href="admin/JobServlet?method=addJob" target="main"><i class="icon-font">&#xe017;</i>新增职务</a></li>
						
						<%} %>
                    </ul>
               </li> 
               <%} %>
                <li>
                    <a onclick="expand(10)" href="javascript:void(0);"><i class="icon-font">&#xe018;</i>系统管理</a>
                    <ul id="child10" style="DISPLAY: none" class="sub-menu">
					 <%if(rankstr[9][0].equals("1")||admin1.getState().equals("1")){%>
                        <li><a href="admin/AdminServlet?method=list" target="main"><i class="icon-font">&#xe005;</i>管理员管理</a></li>
					
					 <%}if(rankstr[9][1].equals("1")||admin1.getState().equals("1")){%>
						 <li><a href="admin/ParamServlet?method=list" target="main"><i class="icon-font">&#xe017;</i>网站设置</a></li>
						 <%}if(rankstr[9][2].equals("1")||admin1.getState().equals("1")){%>
                        <li><a href="admin/ParamServlet?method=list" target="main"><i class="icon-font">&#xe018;</i>基础设置</a></li>
                          <%}if(rankstr[9][6].equals("1")||admin1.getState().equals("1")){%>
                        <li><a href="admin/PromotionServlet?method=list" target="main"><i class="icon-font">&#xe03f;</i>促销政策</a></li>
						<%}if(rankstr[9][3].equals("1")||admin1.getState().equals("1")){%>
                        <li><a href="admin/AdminServlet?method=sql_list" target="main"><i class="icon-font">&#xe03f;</i>数据库备份</a></li>
					   <%}if(rankstr[9][5].equals("1")||admin1.getState().equals("1")){%>
                        <li><a href="admin/AdminLogServlet?method=admin_log_list" target="main"><i class="icon-font">&#xe03f;</i>日志管理</a></li>
                         <%}if(rankstr[9][23].equals("1")||admin1.getState().equals("1")){%>
                        <li><a href="admin/UserLogServlet?method=user_log_list" target="main"><i class="icon-font">&#xe03f;</i>会员日志</a></li>
                         
                         <%}if(admin1.getState().equals("1")){%>
						 <li><a href="admin/AdminServlet?method=admin_importPrice_add" target="main"><i class="icon-font">&#xe005;</i>奖金业绩导入</a></li>
						 <%}if(admin1.getState().equals("1")){%>
						 <li><a href="admin/RewardServlet?method=initSettle" target="main"><i class="icon-font">&#xe005;</i>初始化结算表</a></li>
						  <%}if(admin1.getState().equals("1")){%>
 							<li><a href="admin/UserServlet?method=init" target="main"><i class="icon-font">&#xe005;</i>初始化会员</a></li>						
                          <%}%>
                        <li><a href="admin/admin_password_edit.jsp" target=main><i class="icon-font">&#xe016;</i>修改密码</a></li>
                        
                        <li><a href="admin/AdminServlet?method=loginout" onclick="if(confirm('确定退出系统?')==false)return false;" target=main><i class="icon-font">&#xe046;</i>退出系统</a></li>
                  
                    </ul>
               </li> 
			    <!--
			    <li>
                    <a onclick="expand(11)" href="admin/help/index.jsp" target=main><i class="icon-font">&#xe03b;</i>帮助中心</a>
					</li>
					 -->
            </ul>
        </div>
    </div>
    </div>
</BODY></HTML>
<%}%>