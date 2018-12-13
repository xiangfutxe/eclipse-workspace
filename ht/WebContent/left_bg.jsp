<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.Admin"%>
<%@page import="com.utils.StringUtil"%>
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
					 <li><a href="admin/NewsServlet.do?method=admin_news_add" target="main"><i class="icon-font">&#xe026;</i>添加新闻</a></li>
						  <%}if(rankstr[0][1].equals("1")||admin1.getState().equals("1")){%>
						  <li><a href="admin/NewsServlet.do?method=admin_news_list" target="main"><i class="icon-font">&#xe02f;</i>新闻列表</a></li>
						  <%}if(rankstr[0][2].equals("1")||admin1.getState().equals("1")){%>
						  <li><a href="admin/MessageServlet.do?method=admin_list" target="main"><i class="icon-font">&#xe026;</i>留言簿</a></li>
						    <%}if(rankstr[0][3].equals("1")||admin1.getState().equals("1")){%>
						  <li><a href="admin/ModularServlet.do?method=list" target="main"><i class="icon-font">&#xe026;</i>模块管理</a></li>
	                       <%}%>
	                    </ul>
                </li>
                <%} 
				if(rankstr[1][0].equals("1")||rankstr[1][1].equals("1")||rankstr[1][2].equals("1")||rankstr[1][3].equals("1")||admin1.getState().equals("1")){%>
				  <li>
                    <a onclick="expand(2)" href="javascript:void(0);"><i class="icon-font">&#xe014;</i>会员管理</a>
	                    <ul id="child2" style="DISPLAY: none" class="sub-menu">
	                   <%if(rankstr[1][1].equals("1")||admin1.getState().equals("1")){%>
						   <li><a href="admin/UserServlet.do?method=admin_user_list" target="main"><i class="icon-font">&#xe003;</i>会员列表</a></li>
						<%}if(rankstr[1][5].equals("1")||admin1.getState().equals("1")){%>
							<li><a href="admin/BranchServlet.do?method=admin_branch_list" target="main"><i class="icon-font">&#xe021;</i>协会列表</a></li>
							<%}if(rankstr[1][6].equals("1")||admin1.getState().equals("1")){%>
							<li><a href="admin/branch_add.jsp" target="main"><i class="icon-font">&#xe021;</i>协会新增</a></li>
							
	                       <%}%>
	                    </ul>
                </li>
                <%} 
				if(rankstr[2][0].equals("1")||rankstr[2][1].equals("1")||admin1.getState().equals("1")){%>
				 <li>
                    <a onclick="expand(3)" href="javascript:void(0);"><i class="icon-font">&#xe05e;</i>视频管理</a>
	                    <ul id="child3" style="DISPLAY: none" class="sub-menu">
	                    <%if(rankstr[2][0].equals("1")||admin1.getState().equals("1")){%>
						<li><a href="admin/VideoServlet.do?method=admin_add" target="main"><i class="icon-font">&#xe041;</i>上传视频</a></li>
						  <%}if(rankstr[2][1].equals("1")||admin1.getState().equals("1")){%>
							  <li><a href="admin/VideoServlet.do?method=admin_list" target="main"><i class="icon-font">&#xe041;</i>视频列表</a></li>
	                        <%}%>
	                    </ul>
                </li>
                <%} 
				if(rankstr[3][0].equals("1")||rankstr[3][1].equals("1")||admin1.getState().equals("1")){%>
				 <li>
                    <a onclick="expand(4)" href="javascript:void(0);"><i class="icon-font">&#xe05f;</i>产品管理</a>
                    <ul id="child4" style="DISPLAY: none" class="sub-menu">
                    <%if(rankstr[3][0].equals("1")||admin1.getState().equals("1")){%>
					   <li><a href="admin/ProductServlet?method=admin_product_list" target="main"><i class="icon-font">&#xe005;</i>产品列表</a></li>
					    <%}if(rankstr[3][1].equals("1")||admin1.getState().equals("1")){%>
					     <li><a href="admin/ProductTypeServlet?method=type_list" target="main"><i class="icon-font">&#xe048;</i>分类管理</a></li>
					      <%}if(rankstr[3][2].equals("1")||admin1.getState().equals("1")){%>
					     <li><a href="admin/ProductTypeServlet?method=attribute_list" target="main"><i class="icon-font">&#xe048;</i>属性管理</a></li>
						<%}%>
                    </ul>
                </li>
                <%} 
				if(rankstr[4][0].equals("1")||rankstr[4][1].equals("1")||rankstr[4][2].equals("1")||rankstr[4][3].equals("1")||admin1.getState().equals("1")){%>
				 <li>
                    <a onclick="expand(5)" href="javascript:void(0);"><i class="icon-font">&#xe041;</i>仓库管理</a>
                    <ul id="child5" style="DISPLAY: none" class="sub-menu">
                    <%if(rankstr[4][0].equals("1")||admin1.getState().equals("1")){%>
						 <li><a href="admin/InventoryServlet.do?method=add" target="main"><i class="icon-font">&#xe001;</i>新增仓库</a></li>
						<%}if(rankstr[4][1].equals("1")||admin1.getState().equals("1")){%>
						 <li><a href="admin/InventoryServlet.do?method=list" target="main"><i class="icon-font">&#xe005;</i>仓库列表</a></li>
					<%}if(rankstr[4][2].equals("1")||admin1.getState().equals("1")){%>
					 <li><a href="admin/InventoryServlet.do?method=apply_in" target="main"><i class="icon-font">&#xe046;</i>入库管理</a></li>
					 <%}if(rankstr[4][3].equals("1")||admin1.getState().equals("1")){%>
					  <li><a href="admin/InventoryServlet.do?method=apply_in_review" target="main"><i class="icon-font">&#xe01f;</i>入库审核</a></li>
						 <%}if(rankstr[4][4].equals("1")||admin1.getState().equals("1")){%>
					  <li><a href="admin/InventoryServlet.do?method=inventory_product" target="main"><i class="icon-font">&#xe01f;</i>库存查询</a></li>
						 <%}%>
                    </ul>
                </li>
                <%} 
				if(rankstr[5][0].equals("1")||rankstr[5][1].equals("1")||rankstr[5][2].equals("1")||admin1.getState().equals("1")){%>
				  <li>
                    <a onclick="expand(6)" href="javascript:void(0);"><i class="icon-font">&#xe005;</i>订单管理</a>
                    <ul id="child6" style="DISPLAY: none" class="sub-menu">
                    <%if(rankstr[5][0].equals("1")||admin1.getState().equals("1")){%>
				  <li><a href="admin/OrderServlet.do?method=admin_orders" target="main"><i class="icon-font">&#xe032;</i>订单列表</a></li>
				  <%}if(rankstr[5][1].equals("1")||admin1.getState().equals("1")){%>
				    <li><a href="admin/OrderServlet.do?method=admin_order_confirm" target="main"><i class="icon-font">&#xe033;</i>订单确认</a></li>
					
				  <%}if(rankstr[5][2].equals("1")||admin1.getState().equals("1")){%>
				    <li><a href="admin/OrderServlet.do?method=admin_order_delivery" target="main"><i class="icon-font">&#xe034;</i>发货管理</a></li>
					<%}if(rankstr[5][3].equals("1")||admin1.getState().equals("1")){%>
					   <li><a href="admin/OrderServlet.do?method=admin_order_review" target="main"><i class="icon-font">&#xe042;</i>发货审核</a></li>
					   	<%}if(rankstr[5][4].equals("1")||admin1.getState().equals("1")){%>
					   <li><a href="admin/OrderCenterServlet.do?method=admin_order_confirm" target="main"><i class="icon-font">&#xe042;</i>店铺订单确认</a></li>
					   	<%}if(rankstr[5][5].equals("1")||admin1.getState().equals("1")){%>
					   <li><a href="admin/OrderCenterServlet.do?method=admin_order_delivery" target="main"><i class="icon-font">&#xe042;</i>店铺订单发货</a></li>
					   	<%}if(rankstr[5][6].equals("1")||admin1.getState().equals("1")){%>
					   <li><a href="admin/OrderCenterServlet.do?method=admin_order_review" target="main"><i class="icon-font">&#xe042;</i>店铺订单审核</a></li>
					   	<%}if(rankstr[5][7].equals("1")||admin1.getState().equals("1")){%>
					   <li><a href="admin/OrderCenterServlet.do?method=admin_orders" target="main"><i class="icon-font">&#xe042;</i>店铺订单管理</a></li>
						 <%}%>
                    </ul>
               </li> 
                 <%} 
				if(rankstr[6][0].equals("1")||rankstr[6][1].equals("1")||rankstr[6][2].equals("1")||rankstr[6][3].equals("1")||rankstr[6][4].equals("1")||rankstr[6][5].equals("1")||rankstr[6][6].equals("1")
					||rankstr[6][7].equals("1")||rankstr[6][8].equals("1")||rankstr[6][9].equals("1")||admin1.getState().equals("1")){%>
            <li>
                    <a onclick="expand(7)" href="javascript:void(0);"><i class="icon-font">&#xe053;</i>财务管理</a>
                    <ul id="child7" style="DISPLAY: none" class="sub-menu">
                    <%if(rankstr[6][0].equals("1")||admin1.getState().equals("1")){%>
					 <li><a href="admin/ChargeApplyServlet.do?method=emoney_quick_add" target="main"><i class="icon-font">&#xe035;</i>现金充值</a></li>
					 <%}if(rankstr[6][9].equals("1")||admin1.getState().equals("1")){%>
					 <li><a href="admin/ChargeApplyServlet.do?method=admin_chargeApply_list" target="main"><i class="icon-font">&#xe01f;</i>充值列表</a></li>
							<%}if(rankstr[6][2].equals("1")||admin1.getState().equals("1")){%>
							 <li><a href="admin/WithDrewServlet.do?method=admin_list" target="main"><i class="icon-font">&#xe011;</i>提现审核</a></li>
							<%}if(rankstr[6][3].equals("1")||admin1.getState().equals("1")){%>
							<li><a href="admin/BankAccountServlet.do?method=list" target="main"><i class="icon-font">&#xe032;</i>账号管理</a></li>
							<%}if(rankstr[6][4].equals("1")||admin1.getState().equals("1")){%>
							<li><a href="admin/AccountServlet.do?method=admin_balance_summary" target="main"><i class="icon-font">&#xe033;</i>余额汇总</a></li>
						 	<%}if(rankstr[6][5].equals("1")||admin1.getState().equals("1")){%>
						 	<li><a href="admin/AccountSupplementServlet.do?method=add" target="main"><i class="icon-font">&#xe034;</i>账户补扣</a></li>
						  <%}if(rankstr[6][6].equals("1")||admin1.getState().equals("1")){%>
						   <li><a href="admin/AccountSupplementServlet.do?method=list" target="main"><i class="icon-font">&#xe035;</i>补扣明细</a></li>
						   	  <%}if(rankstr[6][7].equals("1")||admin1.getState().equals("1")){%>
						   	   <li><a href="admin/money_detail.jsp" target="main"><i class="icon-font">&#xe032;</i>资金明细</a></li>
						   	   <%}if(rankstr[6][8].equals("1")||admin1.getState().equals("1")){%>
						   	   <li><a href="admin/AccountServlet.do?method=pmoney_list" target="main"><i class="icon-font">&#xe01f;</i>促销券管理</a></li>
							   <%}%>
						
                    </ul>
               </li> 
			   <%} 
				if(rankstr[7][0].equals("1")||rankstr[7][1].equals("1")||admin1.getState().equals("1")){%>
				<li>
                    <a onclick="expand(8)" href="javascript:void(0);"><i class="icon-font">&#xe054;</i>奖金管理</a>
                    <ul id="child8" style="DISPLAY: none" class="sub-menu">
                      <%if(rankstr[7][0].equals("1")||admin1.getState().equals("1")){%>
                    <li><a href="admin/RewardServlet.do?method=admin_reward_week" target="main"><i class="icon-font">&#xe048;</i>奖金结算</a></li>
<%}if(rankstr[7][1].equals("1")||admin1.getState().equals("1")){%>				
 <li><a href="admin/RewardServlet.do?method=admin_reward_week_list" target="main"><i class="icon-font">&#xe02f;</i>奖金明细</a></li>
						<%}if(rankstr[7][2].equals("1")||admin1.getState().equals("1")){%>
						  <li><a href="admin/RewardServlet.do?method=admin_reward_week_summary" target="main"><i class="icon-font">&#xe013;</i>奖金汇总</a></li>
						  <%}%>
                    </ul>
               </li> 
                   <%} 
				
				if(rankstr[8][0].equals("1")||rankstr[8][1].equals("1")||rankstr[8][2].equals("1")||rankstr[8][3].equals("1")||rankstr[8][4].equals("1")||admin1.getState().equals("1")){%>
				 <li>
                    <a onclick="expand(9)" href="javascript:void(0);"><i class="icon-font">&#xe055;</i>报表统计</a>
                    <ul id="child9" style="DISPLAY: none" class="sub-menu">
                     <%if(rankstr[8][0].equals("1")||admin1.getState().equals("1")){%>
                     <li><a href="admin/ReportServlet.do?method=admin_report_order" target="main"><i class="icon-font">&#xe035;</i>销售业绩查询</a></li>
                     <%}if(rankstr[8][1].equals("1")||admin1.getState().equals("1")){%>
						  <li><a href="admin/ReportServlet.do?method=admin_report_wsettle_1" target="main"><i class="icon-font">&#xe035;</i>待结业绩查询</a></li>
						    <%}if(rankstr[8][2].equals("1")||admin1.getState().equals("1")){%>
						  <li><a href="admin/ReportServlet.do?method=admin_report_wsettle" target="main"><i class="icon-font">&#xe035;</i>已结业绩查询</a></li>
    <%}if(rankstr[8][3].equals("1")||admin1.getState().equals("1")){%>
						  <li><a href="admin/ReportServlet.do?method=admin_report_rankJoin_up_summary" target="main"><i class="icon-font">&#xe035;</i>促销升级汇总</a></li>
 
 <%}%>
                    </ul>
               </li>  
			   <%}%>
                <li>
                    <a onclick="expand(10)" href="javascript:void(0);"><i class="icon-font">&#xe018;</i>系统管理</a>
                    <ul id="child10" style="DISPLAY: none" class="sub-menu">
					 <%if(rankstr[9][0].equals("1")||admin1.getState().equals("1")){%>
                        <li><a href="admin/AdminServlet.do?method=list" target="main"><i class="icon-font">&#xe005;</i>管理员管理</a></li>
					 <%}if(rankstr[9][1].equals("1")||admin1.getState().equals("1")){%>
						 <li><a href="admin/PromotionServlet.do?method=edit" target="main"><i class="icon-font">&#xe017;</i>网站设置</a></li>
						 <%}if(rankstr[9][2].equals("1")||admin1.getState().equals("1")){%>
                        <li><a href="admin/ParamServlet.do?method=list" target="main"><i class="icon-font">&#xe018;</i>基础设置</a></li>
						  <%}if(rankstr[9][3].equals("1")||admin1.getState().equals("1")){%>
                        <li><a href="admin/AdminServlet.do?method=sql_list" target="main"><i class="icon-font">&#xe03f;</i>数据库备份</a></li>
					   <%}if(rankstr[9][5].equals("1")||admin1.getState().equals("1")){%>
                        <li><a href="admin/LogServlet.do?method=admin_log_list" target="main"><i class="icon-font">&#xe03f;</i>日志管理</a></li>
						 <%}if(admin1.getState().equals("1")){%>
						 <li><a href="admin/TimeParamServlet.do?method=init" target="main"><i class="icon-font">&#xe005;</i>初始化时间参数</a></li>
						  <%}if(admin1.getState().equals("1")){%>
						  <li><a href="admin/TimeParamServlet.do?method=edit" target="main"><i class="icon-font">&#xe005;</i>时间参数</a></li>
 <li><a href="admin/UserServlet.do?method=init" target="main"><i class="icon-font">&#xe005;</i>初始化会员</a></li>						
                          <%}%>
                        <li><a href="admin/admin_password_edit.jsp" target=main><i class="icon-font">&#xe016;</i>修改密码</a></li>
                        
                        <li><a href="admin/AdminServlet.do?method=loginout" onclick="if(confirm('确定退出系统?')==false)return false;" target=main><i class="icon-font">&#xe046;</i>退出系统</a></li>
                  
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