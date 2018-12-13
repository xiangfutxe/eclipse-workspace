<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.User" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>
<%@page import="com.ssm.utils.Pager" %>
<%@page import="com.ssm.pojo.Message" %>
<%@ include file="check.jsp" %>  

<%
User user = (User)request.getSession().getAttribute("sys_user");
String token = (String)request.getSession().getAttribute("token");

	if (user == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>地址管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="Assets/css/reset.css"/>
<script type="text/javascript" src="Assets/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="Assets/js/js_z.js"></script>
<script type="text/javascript" src="Assets/js/main.js"></script>
<link rel="stylesheet" type="text/css" href="css/thems.css">
<link rel="stylesheet" type="text/css" href="css/table.css">
<link rel="stylesheet" type="text/css" href="css/form.css">
<script type="text/javascript" src="js/other.js"></script>
<script src="js/jquery.slider.js"></script>
<link rel="stylesheet" href="fonts/fontCss.css">
	<link rel="stylesheet" href="css/jquery.slider.css">
	      <script type="text/javascript" src="js/jquery.cityselect.js"></script>
	<script>
	window.onload = function(){
		$('.testSlider').slider({
			originType:'tuoyuan'
		});
		$('.testSlider1').slider({
			width:300,
			height:300,
			showSlider: false,
			showOrigin: false,
			originPosition:'center'
		});

		$('.testSlider3').slider({
			width:1094,
			height:300,
			originPosition:'center',
			source: [
							{
								src: 'img/slider1.jpg',									// 幻灯片图片地址		
								hasHref: true,												// 是否有链接
								href: '#',			// 链接地址					
								hrefTarget: '_self'											// 链接打开方式  _self, _blank, _top等
 							},
						]
		});
	};
</script>
  </head>
  
 <body>
 <!--头部-->
 <jsp:include page="header.jsp" />
<div class="main_bg">
    <!--幻灯片-->
    <div class="banner1">
            <span class="testSlider3" style="display: inline-block;"></span>
    </div>
  
	<!--幻灯片END-->
	<div class="main_bg">
	<div class="second">   
        <div class="scd_m clearfix">
        	<div class="scd_ml">
            	<ul class="sidenav">
          		  <li ><a href="NewsServlet?method=list">最新资讯</a></li>
                    <li class="now" ><a href="MessageServlet?method=list">留言信箱</a></li>
                </ul>
                <div style="height:300px;"></div>
                </div>
		 <div class="scd_mr">
            	<div class="ht_table">
            	<h2>当前位置：新闻资讯-&gt;<a>留言信箱</a></h2>
            	</div>
            		<div class="content content-full-width">
  				
                	<div class="notice">
         <h2>添加留言</h2><span class="required_notification"></span>
   </div>
   <div class="p-li">
     <form name="savefrom" id="saveform" action=MessageServlet?method=save method="post">
			 <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
  	 <table class ="form-table-one">
  			 <tr><td>
   <textarea id="userMsg" name="userMsg" style="width: 800px; height: 100px"></textarea>
   
   </td>
   </tr>
   <tr>
   <td>
   <input name="button" id="sbtn"
									type="button" class="button blue-sky" value="发布留言" /><span id="msg"></span>
   </td>
   </tr>
   </table>
   </form>
   <p>&nbsp;</p>
   </div>
 
    <div class="notice">
         <h2>留言记录</h2><span class="required_notification"></span>
   </div>
			 <div class="p-li">
			<table class="result-tab" width="100%">
							<thead>
							<tr>
							<th width="5%">序号</th>
							<th>留言时间</th>
							<th>留言内容</th>
							<th>回复内容</th>
							<th>回复时间</th>
							</tr>
							</thead>
							<tbody>
					 <%
								 Pager pager = (Pager)request.getAttribute("pager");
						if(pager!=null){
							Collection coll = pager.getResultList();
							if(coll!=null){
									Iterator it = coll.iterator();
									int t =0;
									while(it.hasNext()){
									Message ca = (Message)it.next();
									t++;
							%>
							
 <tr><td><%=t %></td><td><%=StringUtil.parseToString(ca.getEntryTime(), DateUtil.yyyyMMddHHmmss)%></td><td><%=StringUtil.notNull(ca.getUserMsg()) %></td>
 <td><%=StringUtil.notNull(ca.getAdminMsg()) %></td>
 <td><%=StringUtil.parseToString(ca.getReplyTime(), DateUtil.yyyyMMddHHmmss)%></td></tr>
							<%}}
						}%>
					</table>
				  </div>
              <div class="pager">
                	 <% if(pager!=null){ %>
                	<span class="left">
                   <form name="pageSizeForm"  action=MessageServlet?method=list method="post" class="add_form">
							<input type="hidden" name="pageNoStr" value="1"/>
					当前每页显示<select name="pageSizeStr" id="pageSizeStr" onchange="javascript:pageSizeForm.submit();">
						<option value="10"
							<%if(pager.getPageSize()==10) {%>selected<%}%>>10</option>
						<option value="20"
							<%if(pager.getPageSize()==20) {%> selected<%}%>>20</option>
						<option value="40"
							<%if(pager.getPageSize()==40) {%> selected<%}%>>40</option>
						<option value="60"
							<%if(pager.getPageSize()==60) {%> selected<%}%>>60</option>
						<option value="80"
							<%if(pager.getPageSize()==80) {%>selected<%}%>>80</option>
						<option value="100"
							<%if(pager.getPageSize()==100) {%> selected<%}%>>100</option>
							<option value="200"
							<%if(pager.getPageSize()==200) {%> selected<%}%>>200</option>
					</select>条记录
					
					</form>
					</span>
					<span class="right">
			<form name="pageNoForm"  action="MessageServlet?method=list" method="post"  class="add_form">
						<input type="hidden" name="pageSizeStr" value="<%=pager.getPageSize()%>"/>
					第<font color="red"><%=pager.getPageNo()%>
					</font>页&nbsp; 共<font color="red"><%=pager.getPageCount()%></font>页共<font color="red"><%=pager.getRowCount()%></font>条记录&nbsp;
					<%if(pager.getPageNo()==1) {%> 首页 | 上一页  |<%}else{%>
					<a
						href="MessageServlet?method=list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=1">首页</a> | <a
						href="MessageServlet?method=list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()-1%>">上一页</a>  | <%} %>
                 <%if(pager.getPageNo()!=pager.getPageCount()) {%>
					<a
						href="MessageServlet?method=list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()+1%>">
                    下一页</a> | <a
						href="MessageServlet?method=list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageCount()%>">尾页</a>
				<%}else{ %>下一页 | 尾页<%} %>&nbsp; 跳转到第<select
						name="pageNoStr" onchange="javascript:pageNoForm.submit();">
						<%for(int i=0;i<pager.getPageCount();i++){ %>
							<option value="<%=i+1 %>" <%if(pager.getPageNo()==(i+1)){%>selected<%} %>>
								<%=i+1 %>
							</option>
							<%} %>
					</select>页
						</form>
						</span>
                <%} %>
					</div>
         </div> 
         </div>
               </div>
               </div>
            </div>
</div>
<jsp:include page="footer.jsp" />
</body>
 <script type="text/javascript" src="../js/custom.js"></script>
 <script type="text/javascript">
$(function() {
	
   $("#userMsg").blur(function(){
   	 if($("#userMsg").val()==""){
				$("#msg").text("发表内容不能为空！").css({"color":"red","font-size":"12px"});
	}
	else if(getByteLen($("#userMsg").val())>200) $("#msg").text("发表内容字数不能大于100！").css({"color":"red","font-size":"12px"});
	else $("#msg").text("");
   });
   
	$("#sbtn").click(function() {
	if (!confirm("确认要发布新的留言？")) {
								return false;
							} else {
	var errorstr ="";
			if($("#userMsg").val()==""){
					$("#nameTag").text("发表内容不能为空！").css({"color":"red","font-size":"12px"}); 
					errorstr =errorstr+"1";
				
			}	else if(getByteLen($("#userMsg").val())>200) {
				$("#msg").text("发表内容字数不能大于100！").css({"color":"red","font-size":"12px"});
				errorstr =errorstr+"1";
			}
			else if($("#msg").text()!=""){
				errorstr =errorstr+"1";
			}
		
		if(errorstr!=""){
			return false;
		}else{
       		 $('#saveform').submit();
       		 return true;
		
		}
		}

	});	
});
</script>
</html>
<%
}
%>