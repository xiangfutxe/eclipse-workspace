<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

			<div class="header"><!--header-->	
				<nav class="navbar navbar-default">
					<div class="navbar-header wow fadeInLeft animated animated" data-wow-delay=".5s" style="visibility: visible; animation-delay: 0.5s; animation-name: fadeInLeft;">
						<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
							<span class="sr-only">智华科创科技</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
						<h1><a href="index.jsp">智华科创</a></h1>
					</div>
					<!--navbar-header-->
					<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav navbar-right">
							<li><a href="index.jsp" <% if(class_val==1){%>class="active"<%}%>>首页</a></li>
							<li><a href="about.jsp" <% if(class_val==2){%>class="active"<%}%>>公司介绍</a></li>
							<li><a href="corporate_culture.jsp" <% if(class_val==3){%>class="active"<%}%>>企业文化</a></li>
							<li><a href="news.jsp" <% if(class_val==4){%>class="active"<%}%>>最新动态</a></li>
							<li><a href="professional_field.jsp" <% if(class_val==5){%>class="active"<%}%>>专业领域</a></li>
							<li><a href="talent_strategy.jsp" <% if(class_val==6){%>class="active"<%}%>>人才战略</a></li>
							<li><a href="contact.jsp" <% if(class_val==7){%>class="active"<%}%>>联系我们</a></li>
						</ul>	
						<div class="clearfix"> </div>	
					</div>
				</nav>
			<div class="cd-main-header">
				<ul class="cd-header-buttons">
					<li><a class="cd-search-trigger" href="#cd-search"> <span></span></a></li>
				</ul> <!-- cd-header-buttons -->
			</div>
			<div id="cd-search" class="cd-search">
				<form action="#" method="post">
					<input name="Search" type="search" placeholder="Search...">
				</form>
			</div>
		</div>
		
	