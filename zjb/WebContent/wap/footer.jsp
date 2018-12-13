<%@ page language="java" pageEncoding="UTF-8"%>

<footer class="footer">
  <div class="foot-con">
	<div class="foot-con_2">
		<a href="usermain.jsp"  <%if(atag==1){%> class="active"<%} %>>
			<i class="navIcon home"></i>
			<span class="text">主页</span>
		</a>
		
		<a href="ProductServlet?method=list"  <%if(atag==2){%> class="active"<%} %>>
			<i class="navIcon shop"></i>
			<span class="text">购物</span>		
		</a>
		<a href="OrderServlet?method=orders"  <%if(atag==3){%> class="active"<%} %>>
			<i class="navIcon sort"></i>
			<span class="text">订单</span>
		</a>
		<a href="userhome.jsp" <%if(atag==4){%> class="active"<%} %>>
			<i class="navIcon member"></i>
			<span class="text">我的</span>
		</a>
	</div>
  </div>
</footer>
