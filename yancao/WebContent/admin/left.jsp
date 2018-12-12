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
      <dd><div class="title"><span><img src="images/icons/16/product.png" /></span><a href="ContractServlet?method=list"  target="rightFrame">合同查询</a></div>
      </dd>
         <dd><div class="title"><span><img src="images/icons/16/orders.png" /></span><a href="BorrowApplyServlet?method=list"  target="rightFrame">借阅记录</a></div>
    </dd>   
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
    <span><img src="images/icons/16/message.png"/></span>新闻管理
    </div>
    	<ul class="menuson">
    	 <%if(rank_left[0][1].equals("1")||admin1.getState()-1==0){%>
        <li><cite></cite><a href="NewsServlet?method=admin_news_list" target="rightFrame">新闻查询</a><i></i></li>
         <%}if(rank_left[0][0].equals("1")||admin1.getState()-1==0){%>
        <li><cite></cite><a href="NewsServlet?method=add" target="rightFrame">新闻新增</a><i></i></li>
 			<%}if(rank_left[0][5].equals("1")||admin1.getState()-1==0){%>
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
    <span><img src="images/icons/16/user.png" /></span>合同管理
    </div>
    <ul class="menuson">
  		 <%if(rank_left[1][1].equals("1")||admin1.getState()-1==0){%>
        <li><cite></cite><a href="ContractServlet?method=admin_list" target="rightFrame">合同查询</a><i></i></li>
             <%}if(rank_left[1][0].equals("1")||admin1.getState()-1==0){%>
    		 <li><cite></cite><a href="ContractServlet?method=add" target="rightFrame">合同新增</a><i></i></li>
         <%}if(rank_left[1][4].equals("1")||admin1.getState()-1==0){%>
			<li><cite></cite><a href="BorrowApplyServlet?method=admin_list" target="rightFrame">借阅记录</a><i></i></li>
         <%}if(rank_left[1][5].equals("1")||admin1.getState()-1==0){%>
			<li><cite></cite><a href="BorrowApplyServlet?method=admin_review_list" target="rightFrame">借阅审批</a><i></i></li>
			 <%}if(rank_left[1][6].equals("1")||admin1.getState()-1==0){%>
			<li><cite></cite><a href="BorrowApplyServlet?method=admin_confirm_list" target="rightFrame">借阅确认</a><i></i></li>
			 <%}if(rank_left[1][7].equals("1")||admin1.getState()-1==0){%>
			<li><cite></cite><a href="BorrowApplyServlet?method=admin_end_list" target="rightFrame">归还确认</a><i></i></li>
       <%} %>
        </ul>     
    </dd> 
     <%} %>
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