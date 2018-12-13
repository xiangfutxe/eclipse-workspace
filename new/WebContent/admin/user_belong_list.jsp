<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.Admin" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.pojo.User" %>
<%	
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
String userId = (String)request.getAttribute("userId");
List<User> ulist = (List<User>) request.getAttribute("ulist");
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
		String[] strarray =admin.getRank().split(",");
	    			String[][] rankstr = new String[10][10];
	    			for(int i=0;i<10;i++){
	    				for(int j=0;j<10;j++){
	    					if(strarray.length>i){
	    						if(strarray[i].length()==10)
	    						rankstr[i][j] =  strarray[i].substring(j, j+1);
	    						else rankstr[i][j]="0";
	    					}
	    					else rankstr[i][j]="0";
	    				}
	    			}
		if(rankstr[2][2].equals("1")||admin.getState().equals("1")){
%>
<!DOCTYPE html>
<HTML>
 <HEAD>
 <base href="<%=basePath%>">
  <TITLE> 安置结构图</TITLE>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  
  <link rel="stylesheet" href="ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
  <script type="text/javascript" src="ztree/js/jquery-1.4.4.min.js"></script>
  <script type="text/javascript" src="ztree/js/jquery.ztree.core.js"></script>
   <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
    <style type="text/css">
/*Now the CSS*/
* {margin: 0; padding: 0;}

.tree ul {
	padding-top: 20px; position: relative;
	
	transition: all 0.5s;
	-webkit-transition: all 0.5s;
	-moz-transition: all 0.5s;
}

.tree li {
	float: left; text-align: center;
	list-style-type: none;
	position: relative;
	padding: 20px 5px 0 5px;
	
	transition: all 0.5s;
	-webkit-transition: all 0.5s;
	-moz-transition: all 0.5s;
}

/*We will use ::before and ::after to draw the connectors*/

.tree li::before, .tree li::after{
	content: '';
	position: absolute; top: 0; right: 50%;
	border-top: 1px solid #ccc;
	width: 50%; height: 20px;
}
.tree li::after{
	right: auto; left: 50%;
	border-left: 1px solid #ccc;
}

/*We need to remove left-right connectors from elements without 
any siblings*/
.tree li:only-child::after, .tree li:only-child::before {
	display: none;
}

/*Remove space from the top of single children*/
.tree li:only-child{ padding-top: 0;}

/*Remove left connector from first child and 
right connector from last child*/
.tree li:first-child::before, .tree li:last-child::after{
	border: 0 none;
}
/*Adding back the vertical connector to the last nodes*/
.tree li:last-child::before{
	border-right: 1px solid #ccc;
	border-radius: 0 5px 0 0;
	-webkit-border-radius: 0 5px 0 0;
	-moz-border-radius: 0 5px 0 0;
}
.tree li:first-child::after{
	border-radius: 5px 0 0 0;
	-webkit-border-radius: 5px 0 0 0;
	-moz-border-radius: 5px 0 0 0;
}

/*Time to add downward connectors from parents*/
.tree ul ul::before{
	content: '';
	position: absolute; top: 0; left: 50%;
	border-left: 1px solid #ccc;
	width: 0; height: 20px;
}

.tree li table{
	border: 0px solid #ccc;

	text-decoration: none;
	color: #000000;
	font-family: arial, verdana, tahoma;
	font-size: 12px;
	display: inline-block;
	
	border-radius: 5px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	
	transition: all 0.5s;
	-webkit-transition: all 0.5s;
	-moz-transition: all 0.5s;
}

.tree li table td{
border:1px solid #ccc;
	padding: 5px 10px;
	text-decoration: none;
} 


/*Time for some hover effects*/
/*We will apply the hover effect the the lineage of the element also*/
.tree li a:hover, .tree li a:hover+ul li a {
	background: #c8e4f8; color: #000; border: 1px solid #94a0b4;
}
/*Connector styles on hover*/
.tree li a:hover+ul li::after, 
.tree li a:hover+ul li::before, 
.tree li a:hover+ul::before, 
.tree li a:hover+ul ul::before{
	border-color:  #94a0b4;
}

/*Thats all. I hope you enjoyed it.
Thanks :)*/
</style>
    </HEAD>
<body>
 <div class="container clearfix">
   <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span>网络管理<span class="crumb-step">&gt;</span>
			安置结构图</div>
        </div>
          <div class="search-wrap">
				<div class="search-content">
<form action="admin/UserServlet.do?method=admin_belong_list" id="searchForm" method="post">
					<table class="search-tab">
							<tr>
								<th>&nbsp;用户编号：</th>
								<td><input class="common-text" name="userId" id="userId"
									type="text" value='<%=StringUtil.notNull(userId)%>'>
								</td>
								<td><input class="btn btn-primary btn2" id="search"
									name="search" value="查询" type="submit"></td>
									<td>*备注：<img src="images/rankJoin.png"></td>
							</tr>
							
						</table>
					</form>
						</div>
			</div>
			 
			
					<!--
We will create a family tree using just CSS(3)
The markup will be simple nested lists
-->
<%if(ulist!=null){if(ulist.size()>0){ %>
<div class="tree">
	<ul>
		<li>
			<%if(ulist.get(0)!=null){
				String str = "#FFF";
				if(ulist.get(0).getRankJoin()==2) str ="#fc8634";
				else if(ulist.get(0).getRankJoin()==3) str ="#fff45c";
				else if(ulist.get(0).getRankJoin()==4) str ="#31f531";
				else if(ulist.get(0).getRankJoin()==5) str ="#05cff3";
			 %>
			
			<table style="background-color:<%=str%>">
			<tr><td colspan="3"><a href="admin/UserServlet.do?method=admin_belong_list">返回顶层</a></td></tr>
			<tr><td colspan="3"><a href='#' onclick='history.go(-1);'>返回上一层</a></td></tr>
			<tr><td colspan="3"><span style="color:black;font-weight:bold;"><%=ulist.get(0).getUserId() %>(<%=ulist.get(0).getUserIdOld() %>)
			</span></td></tr>
			<tr><td colspan="3">
			<%if(ulist.get(0).getRankManage()==1){ %>见习员
			<%}else if(ulist.get(0).getRankManage()==2){ %>主管
			<%}else if(ulist.get(0).getRankManage()==3){ %>经理
			<%}else if(ulist.get(0).getRankManage()==4){ %>高经
			<%}else if(ulist.get(0).getRankManage()==5){ %>总监
			<%}else if(ulist.get(0).getRankManage()>=6){ %>董事
		
			<%}else{ %>-<%} %></td></tr>
			<tr><td style="width:40px">&nbsp;</td><td style="width:70px">A区</td><td style="width:70px">B区</td></tr>
			<tr><td colspan=""><span style="color:black;font-weight:bold;">业绩:</span></td><td><%=StringUtil.decimalFormat1(ulist.get(0).getApv())%></td><td><%=StringUtil.decimalFormat1(ulist.get(0).getBpv())%></td></tr>
			<tr><td>总收入</td><td colspan="2"><%=StringUtil.decimalFormat1(ulist.get(0).getTotalIncome())%></td></tr>
			<tr><td>余额</td><td colspan="2"><%=StringUtil.decimalFormat1(ulist.get(0).getRmoney())%></td></tr>
			<tr><td colspan="3"><%=StringUtil.parseToString(ulist.get(0).getEntryTime(), DateUtil.yyyyMMddHHmmss) %></td></tr>
			</table>
		<%}else{ %>
		<table>
		<tr><td colspan="3"><a href="admin/AdminServlet.do?method=admin_belong_list">返回顶层</a></td></tr>
			<tr><td colspan="3"><a href='#' onclick='history.go(-1);'>返回上一层</a></td></tr>
		<tr><td colspan="3"><span style="color:black;font-weight:bold;">期待您的加入</span></td></tr>
		
		<tr><td colspan="3"><span style="color:black;font-weight:bold;">－</span></td></tr>
		<tr><td style="width:40px">&nbsp;</td><td style="width:70px">A区</td><td style="width:70px">B区</td></tr>
			<tr><td colspan=""><span style="color:black;font-weight:bold;">业绩:</span></td><td>0</td><td>0</td></tr>
			<tr><td>总收入</td><td colspan="2">0</td></tr>
			<tr><td>余额</td><td colspan="2">0</td></tr>
		<tr><td colspan="3"><span style="color:black;font-weight:bold;">期待您的加入</span></td></tr>
		</table><%}%>
		
			<ul>
				<li>
					<%if(ulist.get(1)!=null){
					 String str = "#FFF";
			if(ulist.get(1).getRankJoin()==2) str ="#fc8634";
			else if(ulist.get(1).getRankJoin()==3) str ="#fff45c";
			else if(ulist.get(1).getRankJoin()==4) str ="#31f531";
			else if(ulist.get(1).getRankJoin()==5) str ="#05cff3";
			 %>
			
			<table style="background-color:<%=str%>">
			<tr><td colspan="3">
			<a href="admin/UserServlet.do?method=admin_belong_list&userId=<%=ulist.get(1).getUserId()%>"><span style="color:black;font-weight:bold;"><%=ulist.get(1).getUserId() %>(<%=ulist.get(1).getUserIdOld() %>)
			</span></a></td></tr>
			<tr><td colspan="3">
			<%if(ulist.get(1).getRankManage()==1){ %>见习员
			<%}else if(ulist.get(1).getRankManage()==2){ %>主管
			<%}else if(ulist.get(1).getRankManage()==3){ %>经理
			<%}else if(ulist.get(1).getRankManage()==4){ %>高经
			<%}else if(ulist.get(1).getRankManage()==5){ %>总监
			<%}else if(ulist.get(1).getRankManage()>=6){ %>董事
		
			<%}else{ %>-<%} %></td></tr>
			<tr><td style="width:40px">&nbsp;</td><td style="width:70px">A区</td><td style="width:70px">B区</td></tr>
			<tr><td colspan=""><span style="color:black;font-weight:bold;">业绩:</span></td><td><%=StringUtil.decimalFormat1(ulist.get(1).getApv())%></td><td><%=StringUtil.decimalFormat1(ulist.get(1).getBpv())%></td></tr>
			<tr><td>总收入</td><td colspan="2"><%=StringUtil.decimalFormat1(ulist.get(1).getTotalIncome())%></td></tr>
			<tr><td>余额</td><td colspan="2"><%=StringUtil.decimalFormat1(ulist.get(1).getRmoney())%></td></tr>
			<tr><td colspan="3"><%=StringUtil.parseToString(ulist.get(1).getEntryTime(), DateUtil.yyyyMMddHHmmss) %></td></tr>
			</table>
		<%}else{ %><table><tr><td colspan="3"><span style="color:black;font-weight:bold;">期待您的加入</span></td></tr>
		<tr><td colspan="3"><span style="color:black;font-weight:bold;">－</span></td></tr>
		<tr><td style="width:40px">&nbsp;</td><td style="width:70px">A区</td><td style="width:70px">B区</td></tr>
			<tr><td colspan=""><span style="color:black;font-weight:bold;">业绩:</span></td><td>0</td><td>0</td></tr>
			<tr><td>总收入</td><td colspan="2">0</td></tr>
			<tr><td>余额</td><td colspan="2">0</td></tr>
		<tr><td colspan="3"><span style="color:black;font-weight:bold;">期待您的加入</span></td></tr>
		</table><%}%>
					<ul>
						<li>
							<%if(ulist.get(2)!=null){String str = "#FFF";
				if(ulist.get(2).getRankJoin()==2) str ="#fc8634";
				else if(ulist.get(2).getRankJoin()==3) str ="#fff45c";
				else if(ulist.get(2).getRankJoin()==4) str ="#31f531";
				else if(ulist.get(2 ).getRankJoin()==5) str ="#05cff3";
			 %>
			
			<table style="background-color:<%=str%>">
			<tr><td colspan="3">
			<a href="admin/UserServlet.do?method=admin_belong_list&userId=<%=ulist.get(2).getUserId()%>"><span style="color:black;font-weight:bold;"><%=ulist.get(2).getUserId() %>(<%=ulist.get(2).getUserIdOld() %>)
			</span></a></td></tr>
			<tr><td colspan="3">
			<%if(ulist.get(2).getRankManage()==1){ %>见习员
			<%}else if(ulist.get(2).getRankManage()==2){ %>主管
			<%}else if(ulist.get(2).getRankManage()==3){ %>经理
			<%}else if(ulist.get(2).getRankManage()==4){ %>高经
			<%}else if(ulist.get(2).getRankManage()==5){ %>总监
			<%}else if(ulist.get(2).getRankManage()>=6){ %>董事
		
			<%}else{ %>-<%} %></td></tr>
			<tr><td style="width:40px">&nbsp;</td><td style="width:70px">A区</td><td style="width:70px">B区</td></tr>
			<tr><td colspan=""><span style="color:black;font-weight:bold;">业绩:</span></td><td><%=StringUtil.decimalFormat1(ulist.get(2).getApv())%></td><td><%=StringUtil.decimalFormat1(ulist.get(2).getBpv())%></td></tr>
			
				<tr><td>总收入</td><td colspan="2"><%=StringUtil.decimalFormat1(ulist.get(2).getTotalIncome())%></td></tr>
			<tr><td>余额</td><td colspan="2"><%=StringUtil.decimalFormat1(ulist.get(2).getRmoney())%></td></tr>
			<tr><td colspan="3"><%=StringUtil.parseToString(ulist.get(2).getEntryTime(), DateUtil.yyyyMMddHHmmss) %></td></tr>
			</table>
		<%}else{ %><table><tr><td colspan="3"><span style="color:black;font-weight:bold;">期待您的加入</span></td></tr>
		<tr><td colspan="3"><span style="color:black;font-weight:bold;">－</span></td></tr>
		<tr><td style="width:40px">&nbsp;</td><td style="width:70px">A区</td><td style="width:70px">B区</td></tr>
			<tr><td colspan=""><span style="color:black;font-weight:bold;">业绩:</span></td><td>0</td><td>0</td></tr>
			<tr><td>总收入</td><td colspan="2">0</td></tr>
			<tr><td>余额</td><td colspan="2">0</td></tr>
		<tr><td colspan="3"><span style="color:black;font-weight:bold;">期待您的加入</span></td></tr>
		</table><%}%>
						</li>
						<li>
							<%if(ulist.get(3)!=null){  String str = "#FFF";
			if(ulist.get(3).getRankJoin()==2) str ="#fc8634";
			else if(ulist.get(3).getRankJoin()==3) str ="#fff45c";
			else if(ulist.get(3).getRankJoin()==4) str ="#31f531";
			else if(ulist.get(3).getRankJoin()==5) str ="#05cff3";
			 %>
			
			<table style="background-color:<%=str%>">
			<tr><td colspan="3">
			<a href="admin/UserServlet.do?method=admin_belong_list&userId=<%=ulist.get(3).getUserId()%>"><span style="color:black;font-weight:bold;"><%=ulist.get(3).getUserId() %>(<%=ulist.get(3).getUserIdOld() %>)
			</span></a></td></tr>
			<tr><td colspan="3">
			<%if(ulist.get(3).getRankManage()==1){ %>见习员
			<%}else if(ulist.get(3).getRankManage()==2){ %>主管
			<%}else if(ulist.get(3).getRankManage()==3){ %>经理
			<%}else if(ulist.get(3).getRankManage()==4){ %>高经
			<%}else if(ulist.get(3).getRankManage()==5){ %>总监
			<%}else if(ulist.get(3).getRankManage()>=6){ %>董事
		
			<%}else{ %>-<%} %></td></tr>
			<tr><td style="width:40px">&nbsp;</td><td style="width:70px">A区</td><td style="width:70px">B区</td></tr>
			<tr><td colspan=""><span style="color:black;font-weight:bold;">业绩:</span></td><td><%=StringUtil.decimalFormat1(ulist.get(3).getApv())%></td><td><%=StringUtil.decimalFormat1(ulist.get(3).getBpv())%></td></tr>
			<tr><td>总收入</td><td colspan="2"><%=StringUtil.decimalFormat1(ulist.get(3).getTotalIncome())%></td></tr>
			<tr><td>余额</td><td colspan="2"><%=StringUtil.decimalFormat1(ulist.get(3).getRmoney())%></td></tr>
			<tr><td colspan="3"><%=StringUtil.parseToString(ulist.get(3).getEntryTime(), DateUtil.yyyyMMddHHmmss) %></td></tr>
			</table>
		<%}else{ %><table><tr><td colspan="3"><span style="color:black;font-weight:bold;">期待您的加入</span></td></tr>
		<tr><td colspan="3"><span style="color:black;font-weight:bold;">－</span></td></tr>
		<tr><td style="width:40px">&nbsp;</td><td style="width:70px">A区</td><td style="width:70px">B区</td></tr>
			<tr><td colspan=""><span style="color:black;font-weight:bold;">业绩:</span></td><td>0</td><td>0</td></tr>
			<tr><td>总收入</td><td colspan="2">0</td></tr>
			<tr><td>余额</td><td colspan="2">0</td></tr>
		<tr><td colspan="3"><span style="color:black;font-weight:bold;">期待您的加入</span></td></tr>
		</table><%}%>
						</li>
						
						</li>
					</ul>
				</li>
				
				<li><%if(ulist.get(4)!=null){ String str = "#FFF";
				if(ulist.get(4).getRankJoin()==2) str ="#fc8634";
				else if(ulist.get(4).getRankJoin()==3) str ="#fff45c";
				else if(ulist.get(4).getRankJoin()==4) str ="#31f531";
				else if(ulist.get(4).getRankJoin()==5) str ="#05cff3";
			 %>
			
			<table style="background-color:<%=str%>">
			<tr><td colspan="3">
			<a href="admin/UserServlet.do?method=admin_belong_list&userId=<%=ulist.get(4).getUserId()%>"><span style="color:black;font-weight:bold;"><%=ulist.get(4).getUserId() %>(<%=ulist.get(4).getUserIdOld() %>)
			</span></a></td></tr>
			<tr><td colspan="3">
			<%if(ulist.get(4).getRankManage()==1){ %>见习员
			<%}else if(ulist.get(4).getRankManage()==2){ %>主管
			<%}else if(ulist.get(4).getRankManage()==3){ %>经理
			<%}else if(ulist.get(4).getRankManage()==4){ %>高经
			<%}else if(ulist.get(4).getRankManage()==5){ %>总监
			<%}else if(ulist.get(4).getRankManage()>=6){ %>董事
		
			<%}else{ %>-<%} %></td></tr>
			<tr><td style="width:40px">&nbsp;</td><td style="width:70px">A区</td><td style="width:70px">B区</td></tr>
			<tr><td colspan=""><span style="color:black;font-weight:bold;">业绩:</span></td><td><%=StringUtil.decimalFormat1(ulist.get(4).getApv())%></td><td><%=StringUtil.decimalFormat1(ulist.get(4).getBpv())%></td></tr>
			<tr><td>总收入</td><td colspan="2"><%=StringUtil.decimalFormat1(ulist.get(4).getTotalIncome())%></td></tr>
			<tr><td>余额</td><td colspan="2"><%=StringUtil.decimalFormat1(ulist.get(4).getRmoney())%></td></tr>
			<tr><td colspan="3"><%=StringUtil.parseToString(ulist.get(4).getEntryTime(), DateUtil.yyyyMMddHHmmss) %></td></tr>
			</table>
		<%}else{ %><table><tr><td colspan="3"><span style="color:black;font-weight:bold;">期待您的加入</span></td></tr>
		<tr><td colspan="3"><span style="color:black;font-weight:bold;">－</span></td></tr>
		<tr><td style="width:40px">&nbsp;</td><td style="width:70px">A区</td><td style="width:70px">B区</td></tr>
			<tr><td colspan=""><span style="color:black;font-weight:bold;">业绩:</span></td><td>0</td><td>0</td></tr>
			<tr><td>总收入</td><td colspan="2">0</td></tr>
			<tr><td>余额</td><td colspan="2">0</td></tr>
		<tr><td colspan="3"><span style="color:black;font-weight:bold;">期待您的加入</span></td></tr>
		</table><%}%>
					<ul>
						<li>
						<%if(ulist.get(5)!=null){ 
						 String str = "#FFF";
			if(ulist.get(5).getRankJoin()==2) str ="#fc8634";
			else if(ulist.get(5).getRankJoin()==3) str ="#fff45c";
			else if(ulist.get(5).getRankJoin()==4) str ="#31f531";
			else if(ulist.get(5).getRankJoin()==5) str ="#05cff3";
			 %>
			
			<table style="background-color:<%=str%>">
			<tr><td colspan="3">
			<a href="admin/UserServlet.do?method=admin_belong_list&userId=<%=ulist.get(5).getUserId()%>"><span style="color:black;font-weight:bold;"><%=ulist.get(5).getUserId() %>
			</span></a></td></tr>
			<tr><td colspan="3">
			<%if(ulist.get(5).getRankManage()==1){ %>见习员
			<%}else if(ulist.get(5).getRankManage()==2){ %>主管
			<%}else if(ulist.get(5).getRankManage()==3){ %>经理
			<%}else if(ulist.get(5).getRankManage()==4){ %>高经
			<%}else if(ulist.get(5).getRankManage()==5){ %>总监
			<%}else if(ulist.get(5).getRankManage()>=6){ %>董事
		
			<%}else{ %>-<%} %></td></tr>
			<tr><td style="width:40px">&nbsp;</td><td style="width:70px">A区</td><td style="width:70px">B区</td></tr>
			<tr><td style="width:50px"><span style="color:black;font-weight:bold;">业绩:</span></td><td><%=StringUtil.decimalFormat1(ulist.get(5).getApv())%></td><td><%=StringUtil.decimalFormat1(ulist.get(5).getBpv())%></td></tr>
			<tr><td>总收入</td><td colspan="2"><%=StringUtil.decimalFormat1(ulist.get(5).getTotalIncome())%></td></tr>
			<tr><td>余额</td><td colspan="2"><%=StringUtil.decimalFormat1(ulist.get(5).getRmoney())%></td></tr>
			<tr><td colspan="3"><%=StringUtil.parseToString(ulist.get(5).getEntryTime(), DateUtil.yyyyMMddHHmmss) %></td></tr>
			</table>
		<%}else{ %><table><tr><td colspan="3"><span style="color:black;font-weight:bold;">期待您的加入</span></td></tr>
		<tr><td colspan="3"><span style="color:black;font-weight:bold;">－</span></td></tr>
		<tr><td style="width:40px">&nbsp;</td><td style="width:70px">A区</td><td style="width:70px">B区</td></tr>
			<tr><td colspan=""><span style="color:black;font-weight:bold;">业绩:</span></td><td>0</td><td>0</td></tr>
			<tr><td>总收入</td><td colspan="2">0</td></tr>
			<tr><td>余额</td><td colspan="2">0</td></tr>
		<tr><td colspan="3"><span style="color:black;font-weight:bold;">期待您的加入</span></td></tr>
		</table><%}%>
						</li>
						<li>
							<%if(ulist.get(6)!=null){ 
		 			String str = "#FFF";
					if(ulist.get(6).getRankJoin()==2) str ="#fc8634";
					else if(ulist.get(6).getRankJoin()==3) str ="#fff45c";
					else if(ulist.get(6).getRankJoin()==4) str ="#31f531";
					else if(ulist.get(6).getRankJoin()==5) str ="#05cff3";
			 %>
			
			<table style="background-color:<%=str%>">
			<tr><td colspan="3">
			<a href="admin/UserServlet.do?method=admin_belong_list&userId=<%=ulist.get(6).getUserId()%>"><span style="color:black;font-weight:bold;"><%=ulist.get(6).getUserId() %>(<%=ulist.get(6).getUserIdOld() %>)
			</span></a></td></tr>
			<tr><td colspan="3">
			<%if(ulist.get(6).getRankManage()==1){ %>见习员
			<%}else if(ulist.get(6).getRankManage()==2){ %>主管
			<%}else if(ulist.get(6).getRankManage()==3){ %>经理
			<%}else if(ulist.get(6).getRankManage()==4){ %>高经
			<%}else if(ulist.get(6).getRankManage()==5){ %>总监
			<%}else if(ulist.get(6).getRankManage()>=6){ %>董事
		
			<%}else{ %>-<%} %></td></tr>
			<tr><td style="width:40px">&nbsp;</td><td style="width:70px">A区</td><td style="width:70px">B区</td></tr>
			<tr><td colspan=""><span style="color:black;font-weight:bold;">业绩:</span></td><td><%=StringUtil.decimalFormat1(ulist.get(6).getApv())%></td><td><%=StringUtil.decimalFormat1(ulist.get(6).getBpv())%></td></tr>
			<tr><td>总收入</td><td colspan="2"><%=StringUtil.decimalFormat1(ulist.get(6).getTotalIncome())%></td></tr>
			<tr><td>余额</td><td colspan="2"><%=StringUtil.decimalFormat1(ulist.get(6).getRmoney())%></td></tr>
			<tr><td colspan="3"><%=StringUtil.parseToString(ulist.get(6).getEntryTime(), DateUtil.yyyyMMddHHmmss) %></td></tr>
			</table>
		<%}else{ %><table><tr><td colspan="3"><span style="color:black;font-weight:bold;">期待您的加入</span></td></tr>
		<tr><td colspan="3"><span style="color:black;font-weight:bold;">－</span></td></tr>
		<tr><td style="width:40px">&nbsp;</td><td style="width:70px">A区</td><td style="width:70px">B区</td></tr>
			<tr><td colspan=""><span style="color:black;font-weight:bold;">业绩:</span></td><td>0</td><td>0</td></tr>
			<tr><td>总收入</td><td colspan="2">0</td></tr>
			<tr><td>余额</td><td colspan="2">0</td></tr>
		<tr><td colspan="3"><span style="color:black;font-weight:bold;">期待您的加入</span></td></tr>
		</table><%}%>
						</li>
					</ul>
		</li>
	</ul>
</div>
				
			
				
<%}} %>

    </div>
			</div>
   
</BODY>
</HTML>
<%
}else{
			out.println("<script>");
			out.println("alert('你没有权限进行该操作！')");
			out.println("</script>");
		}
}
%>