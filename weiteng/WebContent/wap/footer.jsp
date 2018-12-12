<%@ page language="java" pageEncoding="UTF-8"%>

<footer class="footer">
  <div class="foot-con">
	<div class="foot-con_2">
		<a href="UserServlet?method=index" <%if(atag==1){%> class="active"<%} %>>
			<i class="navIcon home"></i>
			<span class="text">主页</span>
		</a>
		
		<a href="ProductServlet?method=product_stock"  <%if(atag==2){%> class="active"<%} %>>
			<i class="navIcon sort"></i>
			<span class="text">云仓</span>		
		</a>
		<a href="OrderServlet?method=orders"  <%if(atag==3){%> class="active"<%} %>>
			<i class="navIcon shop"></i>
			<span class="text">订单</span>
		</a>
		<a href="UserServlet?method=user_home" <%if(atag==4){%> class="active"<%} %>>
			<i class="navIcon member"></i>
			<span class="text">我的</span>
		</a>
	</div>
  </div>
</footer>
