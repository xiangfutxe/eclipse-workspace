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
            <h4><a href="admin/index.jsp" target="main">控制面板</a></h4>
        </div>
        <div class="sidebar-content">
            <ul class="sidebar-list">
          <%if(rankstr[0][0].equals("1")||rankstr[0][1].equals("1")||rankstr[0][2].equals("1")||rankstr[0][3].equals("1")||admin1.getState().equals("1")){%>
			 <li>
                    <a onclick="expand(0)" href="javascript:void(0);"><i class="icon-font">&#xe004;</i>信息管理</a>
	                    <ul id="child0" style="DISPLAY: none" class="sub-menu">
	                     <%if(rankstr[0][0].equals("1")||admin1.getState().equals("1")){%>
					 <li><a href="admin/NewsServlet.do?method=admin_news_add" target="main"><i class="icon-font">&#xe026;</i>添加新闻</a></li>
						  <%}if(rankstr[0][1].equals("1")||admin1.getState().equals("1")){%>
						  <li><a href="admin/NewsServlet.do?method=admin_news_list" target="main"><i class="icon-font">&#xe02f;</i>新闻列表</a></li>
						  <%}if(rankstr[0][2].equals("1")||admin1.getState().equals("1")){%>
						  <li><a href="admin/MessageServlet.do?method=admin_list" target="main"><i class="icon-font">&#xe026;</i>留言簿</a></li>
	                        <%}if(rankstr[0][3].equals("1")||admin1.getState().equals("1")){%>
						  <li><a href="admin/ShortMessageServlet.do?method=admin_list" target="main"><i class="icon-font">&#xe004;</i>短信模板</a></li>
	                       <%}%>
	                    </ul>
                </li>
                <%} 
				if(rankstr[1][0].equals("1")||rankstr[1][1].equals("1")||rankstr[1][8].equals("1")
				||rankstr[1][9].equals("1")||rankstr[1][12].equals("1")||rankstr[1][14].equals("1")||admin1.getState().equals("1")){%>
				  <li>
                    <a onclick="expand(1)" href="javascript:void(0);"><i class="icon-font">&#xe003;</i>分店管理</a>
	                    <ul id="child1" style="DISPLAY: none" class="sub-menu">
	                    <%if(rankstr[1][0].equals("1")||admin1.getState().equals("1")){%>
						  <li><a href="admin/BranchServlet.do?method=admin_list" target="main"><i class="icon-font">&#xe004;</i>分店列表</a></li>
						  <%}if(rankstr[1][1].equals("1")||admin1.getState().equals("1")){%>
						   <li><a href="admin/BranchServlet.do?method=admin_add" target="main"><i class="icon-font">&#xe003;</i>分店新增</a></li>
	        			 <%}if(rankstr[1][8].equals("1")||admin1.getState().equals("1")){%>
	                      	<li><a href="admin/BranchServlet.do?method=admin_lock_list" target="main"><i class="icon-font">&#xe005;</i>锁定分店</a></li>
	                      
	                       <%}%>
	                    </ul>
                </li>
                <%} 
				if(rankstr[2][0].equals("1")||rankstr[2][1].equals("1")
				||rankstr[2][6].equals("1")||rankstr[2][7].equals("1")
				||rankstr[2][9].equals("1")||rankstr[2][10].equals("1")
				||rankstr[2][12].equals("1")||rankstr[2][13].equals("1")
				||admin1.getState().equals("1")){%>
				
				 <li>
                    <a onclick="expand(2)" href="javascript:void(0);"><i class="icon-font">&#xe041;</i>产品管理</a>
                    <ul id="child2" style="DISPLAY: none" class="sub-menu"> 
                     <%if(rankstr[2][0].equals("1")||admin1.getState().equals("1")){%>
					   <li><a href="admin/ProductServlet.do?method=admin_list" target="main"><i class="icon-font">&#xe005;</i>产品列表</a></li>
					    <%}if(rankstr[2][1].equals("1")||admin1.getState().equals("1")){%>
					     <li><a href="admin/ProductServlet.do?method=admin_add" target="main"><i class="icon-font">&#xe048;</i>产品添加</a></li>
					    <%}if(rankstr[2][6].equals("1")||admin1.getState().equals("1")){%>
					     <li><a href="admin/ProductTypeServlet.do?method=list" target="main"><i class="icon-font">&#xe048;</i>分类管理</a></li>
					      <%}if(rankstr[2][9].equals("1")||admin1.getState().equals("1")){%>
					     <li><a href="admin/ProductUnitServlet.do?method=list" target="main"><i class="icon-font">&#xe048;</i>单位管理</a></li>
					       <%}if(rankstr[2][12].equals("1")||admin1.getState().equals("1")){%>
					     <li><a href="admin/ProductAttributeServlet.do?method=list" target="main"><i class="icon-font">&#xe048;</i>属性管理</a></li>
                   
						 <%}%>
                    </ul>
                </li>
                  <%} 
				if(rankstr[3][0].equals("1")||rankstr[3][1].equals("1")
				||rankstr[3][3].equals("1")||rankstr[3][4].equals("1")
				||rankstr[3][5].equals("1")||rankstr[3][2].equals("1")
				||rankstr[3][6].equals("1")||rankstr[3][7].equals("1")
				||rankstr[3][8].equals("1")||rankstr[3][9].equals("1")
				||rankstr[3][10].equals("1")||rankstr[3][11].equals("1")
				||rankstr[3][12].equals("1")||rankstr[3][13].equals("1")
				||rankstr[3][14].equals("1")||rankstr[3][5].equals("1")
				||rankstr[3][16].equals("1")||rankstr[3][17].equals("1")
				||rankstr[3][18].equals("1")||rankstr[3][19].equals("1")||admin1.getState().equals("1")){%>
				
				 <li>
                    <a onclick="expand(3)" href="javascript:void(0);"><i class="icon-font">&#xe043;</i>仓库管理</a>
                    <ul id="child3" style="DISPLAY: none" class="sub-menu"> 
                   <%if(rankstr[3][1].equals("1")||admin1.getState().equals("1")){%>
						 <li><a href="admin/InventoryServlet.do?method=list" target="main"><i class="icon-font">&#xe005;</i>区域仓库</a></li>
						 <%}if(rankstr[3][1].equals("1")||admin1.getState().equals("1")){%>
						 <li><a href="admin/InventoryServlet.do?method=two_list" target="main"><i class="icon-font">&#xe005;</i>逻辑仓库</a></li>
						 <%}if(rankstr[3][1].equals("1")||admin1.getState().equals("1")){%>
						 <li><a href="admin/InventoryServlet.do?method=three_list" target="main"><i class="icon-font">&#xe005;</i>物理仓库</a></li>
					<%}if(rankstr[3][3].equals("1")||admin1.getState().equals("1")){%>
					 <li><a href="admin/InventoryServlet.do?method=apply_in_all" target="main"><i class="icon-font">&#xe046;</i>入库查询</a></li>
					  <%}if(rankstr[3][4].equals("1")||admin1.getState().equals("1")){%>
					  <li><a href="admin/InventoryServlet.do?method=apply_in" target="main"><i class="icon-font">&#xe01f;</i>入库申请</a></li>
					  <%}if(rankstr[3][6].equals("1")||admin1.getState().equals("1")){%>
					  <li><a href="admin/InventoryServlet.do?method=apply_in_check" target="main"><i class="icon-font">&#xe01f;</i>入库质检验收</a></li>
					   <%}if(rankstr[3][7].equals("1")||admin1.getState().equals("1")){%>
					  <li><a href="admin/InventoryServlet.do?method=apply_in_cfm" target="main"><i class="icon-font">&#xe01f;</i>入库仓管确认</a></li>
					 <%}if(rankstr[3][8].equals("1")||admin1.getState().equals("1")){%>
					  <li><a href="admin/InventoryServlet.do?method=apply_in_review" target="main"><i class="icon-font">&#xe01f;</i>入库财务审核</a></li>
					  <%}if(rankstr[3][9].equals("1")||admin1.getState().equals("1")){%>
					 <li><a href="admin/InventoryServlet.do?method=apply_out_all" target="main"><i class="icon-font">&#xe046;</i>出库查询</a></li>
					  <%}if(rankstr[3][10].equals("1")||admin1.getState().equals("1")){%>
					  <li><a href="admin/InventoryServlet.do?method=apply_out" target="main"><i class="icon-font">&#xe01f;</i>出库申请</a></li>
					   <%}if(rankstr[3][11].equals("1")||admin1.getState().equals("1")){%>
					  <li><a href="admin/InventoryServlet.do?method=apply_out_check" target="main"><i class="icon-font">&#xe01f;</i>出库质检确认</a></li>
					   <%}if(rankstr[3][12].equals("1")||admin1.getState().equals("1")){%>
					  <li><a href="admin/InventoryServlet.do?method=apply_out_cfm" target="main"><i class="icon-font">&#xe01f;</i>出库仓管确认</a></li>
					 <%}if(rankstr[3][13].equals("1")||admin1.getState().equals("1")){%>
					  <li><a href="admin/InventoryServlet.do?method=apply_out_review" target="main"><i class="icon-font">&#xe01f;</i>出库财务审核</a></li>
						 <%}if(rankstr[3][14].equals("1")||admin1.getState().equals("1")){%>
					  <li><a href="admin/InventoryServlet.do?method=settle_stock" target="main"><i class="icon-font">&#xe01f;</i>库存盘点</a></li>
						  <%}if(rankstr[3][15].equals("1")||admin1.getState().equals("1")){%>
					  <li><a href="admin/InventoryServlet.do?method=settle_stock_list" target="main"><i class="icon-font">&#xe01f;</i>盘点明细</a></li>
						   <%}if(rankstr[3][16].equals("1")||admin1.getState().equals("1")){%>
					  <li><a href="admin/InventoryServlet.do?method=apply_in_4" target="main"><i class="icon-font">&#xe01f;</i>盘盈入库</a></li>
						  <%}if(rankstr[3][17].equals("1")||admin1.getState().equals("1")){%>
					  <li><a href="admin/InventoryServlet.do?method=apply_in_4_review" target="main"><i class="icon-font">&#xe01f;</i>盘盈复核</a></li>
					  <%}if(rankstr[3][18].equals("1")||admin1.getState().equals("1")){%>
					  
					   <li><a href="admin/InventoryServlet.do?method=apply_out_4" target="main"><i class="icon-font">&#xe01f;</i>盘亏出库</a></li>
						  <%}if(rankstr[3][19].equals("1")||admin1.getState().equals("1")){%>
					  <li><a href="admin/InventoryServlet.do?method=apply_out_4_review" target="main"><i class="icon-font">&#xe01f;</i>盘亏复核</a></li>
					   <%}if(rankstr[3][20].equals("1")||admin1.getState().equals("1")){%>
					  <li><a href="admin/inventory_apply_in_product_detail.jsp" target="main"><i class="icon-font">&#xe01f;</i>入库明细</a></li>
						  <%}if(rankstr[3][21].equals("1")||admin1.getState().equals("1")){%>
					  <li><a href="admin/inventory_apply_out_product_detail.jsp" target="main"><i class="icon-font">&#xe01f;</i>出库明细</a></li>
						 <%}%>
                    </ul>
                </li>
                 <%} 
				if(rankstr[4][0].equals("1")||rankstr[4][1].equals("1")
        			||rankstr[4][2].equals("1")||rankstr[4][3].equals("1")||rankstr[4][4].equals("1")||admin1.getState().equals("1")){%>
				  <li>
                    <a onclick="expand(5)" href="javascript:void(0);"><i class="icon-font">&#xe034;</i>采购管理</a>
                    <ul id="child5" style="DISPLAY: none" class="sub-menu">
                    <%if(rankstr[4][0].equals("1")||admin1.getState().equals("1")){%>
				  <li><a href="admin/PurchaseApplyServlet.do?method=admin_list" target="main"><i class="icon-font">&#xe032;</i>采购列表</a></li>
				  <%}if(rankstr[4][1].equals("1")||admin1.getState().equals("1")){%>
				    <li><a href="admin/PurchaseApplyServlet.do?method=admin_apply_list" target="main"><i class="icon-font">&#xe033;</i>采购申请</a></li>
				  <%}if(rankstr[4][2].equals("1")||admin1.getState().equals("1")){%>
				    <li><a href="admin/PurchaseApplyServlet.do?method=admin_review_list" target="main"><i class="icon-font">&#xe034;</i>采购审批</a></li>
					  <%}if(rankstr[4][3].equals("1")||admin1.getState().equals("1")){%>
				    <li><a href="admin/purchase_history_list.jsp" target="main"><i class="icon-font">&#xe05b;</i>历史采购</a></li>
				      <%}if(rankstr[4][4].equals("1")||admin1.getState().equals("1")){%>
				    <li><a href="admin/purchase_product_detail_list.jsp" target="main"><i class="icon-font">&#xe04b;</i>单品汇总</a></li>
					
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
					   <li><a href="admin/OrderServlet.do?method=admin_order_review" target="main"><i class="icon-font">&#xe042;</i>出库审核</a></li>
					   <%}if(rankstr[5][4].equals("1")||admin1.getState().equals("1")){%>
					   <li><a href="admin/OrderServlet.do?method=admin_order_payoff" target="main"><i class="icon-font">&#xe045;</i>订单支付</a></li>
					    <%}if(rankstr[5][5].equals("1")||admin1.getState().equals("1")){%>
					   <li><a href="admin/order_delivery_summary_list.jsp" target="main"><i class="icon-font">&#xe049;</i>配送汇总</a></li>
					     <%}if(rankstr[5][6].equals("1")||admin1.getState().equals("1")){%>
					   <li><a href="admin/order_branch_summary_list.jsp" target="main"><i class="icon-font">&#xe048;</i>分店汇总</a></li>
					      <%}if(rankstr[5][7].equals("1")||admin1.getState().equals("1")){%>
					   <li><a href="admin/OrderServlet.do?method=admin_order_new_branch_shop" target="main"><i class="icon-font">&#xe048;</i>新店配货</a></li>
					   
						 <%}%>
                    </ul>
               </li> 
                 <%} 
				if(rankstr[6][0].equals("1")||rankstr[6][1].equals("1")||rankstr[6][2].equals("1")
				||rankstr[6][3].equals("1")||rankstr[6][4].equals("1")||rankstr[6][5].equals("1")||admin1.getState().equals("1")){%>
				  <li>
                    <a onclick="expand(7)" href="javascript:void(0);"><i class="icon-font">&#xe040;</i>财务管理</a>
                    <ul id="child7" style="DISPLAY: none" class="sub-menu">
                    <%if(rankstr[6][0].equals("1")||admin1.getState().equals("1")){%>
				  <li><a href="admin/ChargeApplyServlet.do?method=emoney_quick_add" target="main"><i class="icon-font">&#xe026;</i>现金充值</a></li>
				  <%}if(rankstr[6][1].equals("1")||admin1.getState().equals("1")){%>
				    <li><a href="admin/ChargeApplyServlet.do?method=admin_list" target="main"><i class="icon-font">&#xe048;</i>充值列表</a></li>
				     <%}if(rankstr[6][11].equals("1")||admin1.getState().equals("1")){%>
				    <li><a href="admin/AccountSupplementServlet.do?method=add" target="main"><i class="icon-font">&#xe05a;</i>账户增扣</a></li>
				     <%}if(rankstr[6][12].equals("1")||admin1.getState().equals("1")){%>
				    <li><a href="admin/AccountSupplementServlet.do?method=list" target="main"><i class="icon-font">&#xe02f;</i>增扣列表</a></li>
				 <%}if(rankstr[6][14].equals("1")||admin1.getState().equals("1")){%>
				    <li><a href="admin/AccountServlet.do?method=admin_money_detail" target="main"><i class="icon-font">&#xe049;</i>资金明细</a></li>
				
						 <%}%>
                    </ul>
               </li> 
			    <%} 
				if(rankstr[8][0].equals("1")||rankstr[8][1].equals("1")||rankstr[8][2].equals("1")
				||rankstr[8][3].equals("1")||rankstr[8][4].equals("1")||rankstr[8][5].equals("1")||admin1.getState().equals("1")){%>
				  <li>
                    <a onclick="expand(9)" href="javascript:void(0);"><i class="icon-font">&#xe014;</i>供应商管理</a>
                    <ul id="child9" style="DISPLAY: none" class="sub-menu">
                    <%if(rankstr[8][0].equals("1")||admin1.getState().equals("1")){%>
				  <li><a href="admin/SupplierServlet.do?method=admin_list" target="main"><i class="icon-font">&#xe048;</i>供应商列表</a></li>
				  <%}if(rankstr[8][1].equals("1")||admin1.getState().equals("1")){%>
				    <li><a href="admin/SupplierServlet.do?method=admin_add" target="main"><i class="icon-font">&#xe026;</i>供应商新增</a></li>
				
						 <%}%>
                    </ul>
               </li> 
                <%} 
				if(rankstr[7][0].equals("1")||rankstr[7][1].equals("1")||rankstr[7][2].equals("1")
				||rankstr[7][3].equals("1")||rankstr[7][4].equals("1")||rankstr[7][5].equals("1")||admin1.getState().equals("1")){%>
				  <li>
                    <a onclick="expand(8)" href="javascript:void(0);"><i class="icon-font">&#xe048;</i>报表统计</a>
                    <ul id="child8" style="DISPLAY: none" class="sub-menu">
                    <%if(rankstr[7][0].equals("1")||admin1.getState().equals("1")){%>
				  <li><a href="admin/report_order_summary_list.jsp" target="main"><i class="icon-font">&#xe049;</i>订单统计</a></li>
				  <%}if(rankstr[7][1].equals("1")||admin1.getState().equals("1")){%>
				    <li><a href="admin/report_apply_in_summary.jsp" target="main"><i class="icon-font">&#xe049;</i>入库统计</a></li>
				     <%}if(rankstr[7][2].equals("1")||admin1.getState().equals("1")){%>
				    <li><a href="admin/report_apply_out_summary.jsp" target="main"><i class="icon-font">&#xe049;</i>出库统计</a></li>
				 <%}if(rankstr[7][3].equals("1")||admin1.getState().equals("1")){%>
				    <li><a href="admin/ReportServlet.do?method=report_apply_check_summary" target="main"><i class="icon-font">&#xe049;</i>库存检测</a></li>
	  <%}if(rankstr[7][4].equals("1")||admin1.getState().equals("1")){%>
				    <li><a href="admin/report_product_detail_summary.jsp" target="main"><i class="icon-font">&#xe049;</i>单品总统计</a></li>
						 <%}if(rankstr[7][5].equals("1")||admin1.getState().equals("1")){%>
				    <li><a href="admin/report_branch_detail_summary.jsp" target="main"><i class="icon-font">&#xe049;</i>单品店统计</a></li>
						  <%}if(rankstr[7][6].equals("1")||admin1.getState().equals("1")){%>
				    <li><a href="admin/report_branch_price_summary.jsp" target="main"><i class="icon-font">&#xe049;</i>店销汇总</a></li>
						   <%}if(rankstr[7][7].equals("1")||admin1.getState().equals("1")){%>
				    <li><a href="admin/report_order_delivery_summary.jsp" target="main"><i class="icon-font">&#xe049;</i>配送总统计</a></li>
						 <%}%>
                    </ul>
               </li> 
                
			   
			   <%}%>
                <li>
                    <a onclick="expand(10)" href="javascript:void(0);"><i class="icon-font">&#xe018;</i>系统管理</a>
                    <ul id="child10" style="DISPLAY: none" class="sub-menu">
					 <%if(rankstr[9][0].equals("1")||admin1.getState().equals("1")){%>
                        <li><a href="admin/AdminServlet.do?method=list" target="main"><i class="icon-font">&#xe005;</i>管理员管理</a></li>
					
						  <%}if(rankstr[9][8].equals("1")||admin1.getState().equals("1")){%>
                        <li><a href="admin/AdminServlet.do?method=sql_list" target="main"><i class="icon-font">&#xe03f;</i>数据库备份</a></li>
					   <%}if(rankstr[9][7].equals("1")||admin1.getState().equals("1")){%>
                        <li><a href="admin/LogServlet.do?method=admin_log_list" target="main"><i class="icon-font">&#xe03f;</i>日志管理</a></li>
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