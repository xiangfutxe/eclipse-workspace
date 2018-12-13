<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ page import="com.pojo.User"%>
<%
User user_1 = (User)request.getSession().getAttribute("ht_user");
%>


<footer class="footer">
  <div class="foot-con">
	<div class="foot-con_2">
		<a href="index.jsp" class="active">
			<i class="navIcon home"></i>
			<span class="text">店铺主页</span>
		</a>
		<a href="userhome.jsp">
			<i class="navIcon member"></i>
			<span class="text">会员主页</span>
		</a>
		<a href="shopcart.jsp">
			<i class="navIcon sort"></i>
			<span class="text">所有商品</span>		
		</a>
		<a href="category.jsp" >
			<i class="navIcon shop"></i>
			<span class="text">分销申请</span>
		</a>
	</div>
  </div>
</footer>


