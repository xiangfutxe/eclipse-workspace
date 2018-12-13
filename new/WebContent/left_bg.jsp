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
	                       <%}%>
	                    </ul>
                </li>
                <%} 
				if(rankstr[1][0].equals("1")||rankstr[1][1].equals("1")||rankstr[1][8].equals("1")
				||rankstr[1][9].equals("1")||rankstr[1][12].equals("1")||rankstr[1][14].equals("1")||admin1.getState().equals("1")){%>
				  <li>
                    <a onclick="expand(2)" href="javascript:void(0);"><i class="icon-font">&#xe014;</i>会员管理</a>
	                    <ul id="child2" style="DISPLAY: none" class="sub-menu">
	                    <%if(rankstr[1][1].equals("1")||admin1.getState().equals("1")){%>
						   <li><a href="admin/UserServlet.do?method=adminList" target="main"><i class="icon-font">&#xe003;</i>会员列表</a></li>
							<%}if(rankstr[1][9].equals("1")||admin1.getState().equals("1")){%>
							<li><a href="admin/RankJoinUpdateServlet.do?method=rankJoin_list" target="main"><i class="icon-font">&#xe021;</i>等级变更</a></li>
							<%}if(rankstr[1][8].equals("1")||admin1.getState().equals("1")){%>
							<li><a href="admin/rankJoin_up.jsp" target="main"><i class="icon-font">&#xe021;</i>新增变更</a></li>
	                       <%}%>
	                    </ul>
                </li>
              
                <%}
	                if(rankstr[4][0].equals("1")||rankstr[4][6].equals("1")||rankstr[4][9].equals("1")
					||rankstr[4][10].equals("1")||rankstr[4][11].equals("1")||rankstr[4][12].equals("1")
					||admin1.getState().equals("1")){
				%>
				 <li>
                    <a onclick="expand(5)" href="javascript:void(0);"><i class="icon-font">&#xe041;</i>产品管理</a>
                    <ul id="child5" style="DISPLAY: none" class="sub-menu"> 
                     <%if(rankstr[4][0].equals("1")||admin1.getState().equals("1")){%>
					   <li><a href="admin/ProductServlet.do?method=list" target="main"><i class="icon-font">&#xe005;</i>产品管理</a></li>
					    <%}if(rankstr[4][6].equals("1")||admin1.getState().equals("1")){%>
					     <li><a href="admin/ProductTypeServlet.do?method=list" target="main"><i class="icon-font">&#xe048;</i>分类管理</a></li>
                   <%}%>
                    </ul>
                </li>
                  <%} 
				if(rankstr[3][0].equals("1")||rankstr[3][1].equals("1")
				||rankstr[3][2].equals("1")||rankstr[3][3].equals("1")
				||rankstr[3][4].equals("1")||rankstr[3][5].equals("1")||admin1.getState().equals("1")){%>
				 <li>
                    <a onclick="expand(4)" href="javascript:void(0);"><i class="icon-font">&#xe05f;</i>库存管理</a>
                    <ul id="child4" style="DISPLAY: none" class="sub-menu">
                   <%if(rankstr[3][0].equals("1")||admin1.getState().equals("1")){%>
						 <li><a href="admin/InventoryServlet.do?method=add" target="main"><i class="icon-font">&#xe001;</i>新增仓库</a></li>
						<%}if(rankstr[3][1].equals("1")||admin1.getState().equals("1")){%>
						 <li><a href="admin/InventoryServlet.do?method=list" target="main"><i class="icon-font">&#xe005;</i>仓库列表</a></li>
					<%}if(rankstr[3][3].equals("1")||admin1.getState().equals("1")){%>
					 <li><a href="admin/InventoryServlet.do?method=apply_in_all" target="main"><i class="icon-font">&#xe046;</i>入库查询</a></li>
					<%}if(rankstr[3][4].equals("1")||admin1.getState().equals("1")){%>
					 <li><a href="admin/InventoryServlet.do?method=apply_in" target="main"><i class="icon-font">&#xe046;</i>入库申请</a></li>
					  <%}if(rankstr[3][5].equals("1")||admin1.getState().equals("1")){%>
					  <li><a href="admin/InventoryServlet.do?method=apply_in_review" target="main"><i class="icon-font">&#xe01f;</i>入库审核</a></li>
						 <%}%>
                    </ul>
                </li>
                <%} 
				if(rankstr[5][0].equals("1")||rankstr[5][1].equals("1")||rankstr[5][2].equals("1")
				||rankstr[5][3].equals("1")||rankstr[5][4].equals("1")||rankstr[5][5].equals("1")||admin1.getState().equals("1")){%>
				  <li>
                    <a onclick="expand(6)" href="javascript:void(0);"><i class="icon-font">&#xe005;</i>订单管理</a>
                    <ul id="child6" style="DISPLAY: none" class="sub-menu">
                    <%if(rankstr[5][0].equals("1")||admin1.getState().equals("1")){%>
				  <li><a href="admin/OrderServlet.do?method=admin_orders" target="main"><i class="icon-font">&#xe032;</i>订单列表</a></li>
				  <%}if(rankstr[5][1].equals("1")||admin1.getState().equals("1")){%>
				    <li><a href="admin/OrderServlet.do?method=admin_order_confirm" target="main"><i class="icon-font">&#xe033;</i>订单确认</a></li>
					
				  <%}if(rankstr[5][2].equals("1")||admin1.getState().equals("1")){%>
				    <li><a href="admin/OrderServlet.do?method=admin_order_delivery" target="main"><i class="icon-font">&#xe034;</i>订单配货</a></li>
					<%}if(rankstr[5][3].equals("1")||admin1.getState().equals("1")){%>
					   <li><a href="admin/OrderServlet.do?method=admin_order_review" target="main"><i class="icon-font">&#xe042;</i>订单出库</a></li>
					  <%}%>
                    </ul>
               </li> 
                <%} 
				if(rankstr[7][0].equals("1")||rankstr[7][1].equals("1")||rankstr[7][2].equals("1")||admin1.getState().equals("1")){%>
				 <li>
                    <a onclick="expand(8)" href="javascript:void(0);"><i class="icon-font">&#xe055;</i>奖金管理</a>
                    <ul id="child8" style="DISPLAY: none" class="sub-menu">
                     <%if(rankstr[7][0].equals("1")||admin1.getState().equals("1")){%>
                     <li><a href="admin/RewardServlet.do?method=admin_list" target="main"><i class="icon-font">&#xe035;</i>奖金明细</a></li>
                      <%}if(rankstr[7][1].equals("1")||admin1.getState().equals("1")){%>
                     <li><a href="admin/RewardServlet.do?method=admin_reward_week" target="main"><i class="icon-font">&#xe036;</i>工资结算</a></li>
                     <%}if(rankstr[7][2].equals("1")||admin1.getState().equals("1")){%>
                     <li><a href="admin/RewardServlet.do?method=admin_reward_week_summary" target="main"><i class="icon-font">&#xe038;</i>工资明细</a></li>
 <%}%>
                    </ul>
               </li>  
                   <%} 
				if(rankstr[8][0].equals("1")||rankstr[8][1].equals("1")||rankstr[8][2].equals("1")||rankstr[8][3].equals("1")||rankstr[8][4].equals("1")||admin1.getState().equals("1")){%>
				 <!--  <li>
                    <a onclick="expand(9)" href="javascript:void(0);"><i class="icon-font">&#xe055;</i>报表统计</a>
                    <ul id="child9" style="DISPLAY: none" class="sub-menu">
                     <%if(rankstr[8][0].equals("1")||admin1.getState().equals("1")){%>
                     <li><a href="admin/ReportServlet.do?method=admin_report_order" target="main"><i class="icon-font">&#xe035;</i>销售业绩查询</a></li>
 
 <%}%>
                    </ul>
               </li>  
                -->
			   <%}%>
                <li>
                    <a onclick="expand(10)" href="javascript:void(0);"><i class="icon-font">&#xe018;</i>系统管理</a>
                    <ul id="child10" style="DISPLAY: none" class="sub-menu">
					 <%if(rankstr[9][0].equals("1")||admin1.getState().equals("1")){%>
                        <li><a href="admin/AdminServlet.do?method=list" target="main"><i class="icon-font">&#xe005;</i>管理员管理</a></li>
                        <%}if(rankstr[9][1].equals("1")||admin1.getState().equals("1")){%>
                        <li><a href="admin/ParamServlet.do?method=list" target="main"><i class="icon-font">&#xe033;</i>参数设置</a></li>
						<%}if(rankstr[9][7].equals("1")||admin1.getState().equals("1")){%>
                        <li><a href="admin/AdminServlet.do?method=sql_list" target="main"><i class="icon-font">&#xe034;</i>数据库备份</a></li>
					   <%}if(rankstr[9][8].equals("1")||admin1.getState().equals("1")){%>
                        <li><a href="admin/LogServlet.do?method=admin_log_list" target="main"><i class="icon-font">&#xe032;</i>日志管理</a></li>
						<%}if(admin1.getState().equals("1")){%>
                        <li><a href="admin/UserServlet.do?method=init" target="main"><i class="icon-font">&#xe038;</i>初始化会员</a></li>
						
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