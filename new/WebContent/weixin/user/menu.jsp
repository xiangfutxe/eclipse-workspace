<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
            <div class="navigation-item">
                <a href="main.jsp" class="block home-icon">首页</a>
                <em class="inactive-menu"></em>
            </div>
             
            <div class="navigation-item">
                <a href="#" class="block user-icon has-submenu">个人信息</a>
                <em class="dropdown-menu"></em>
              	<div class="submenu">
                    <a class="block" href="UserServlet.do?method=user_detail">个人资料<em></em></a>
                    <a class="block" href="password1_edit.jsp">登陆密码<em></em></a>
                    <a class="block" href="password2_edit.jsp">支付密码<em></em></a>
                </div>
            </div>
            <div class="navigation-item">
                <a  href="#" class="block order-icon has-submenu">业务管理</a>
                <em class="dropdown-menu"></em>
                <div class="submenu">
                		<a href="OrderServlet.do?method=order_emoney_add" class="block">再次购物<em></em></a>
                     <a href="OrderServlet.do?method=orders" class="block">订单管理<em></em></a>
                     <a href="AddressServlet.do?method=list" class="block">地址管理<em></em></a>
                      <a href="receiver_info.jsp" class="block">结账信息<em></em></a>
                       <a href="RewardServlet.do?method=reward_week_summary" class="block">积分明细<em></em></a>
                </div>
            </div>
            
             <div class="navigation-item">
                <a href="ProductServlet.do?method=index" class="block smoney-icon">产品展示</a>
            </div>
              <div class="navigation-item">
                <a  href="contact.jsp" class="block contact-icon">联系我们</a>
            </div>
           <div class="navigation-item">
                <a  href="UserServlet.do?method=login_out" class=" block features-icon">退出系统</a>
            </div>
       