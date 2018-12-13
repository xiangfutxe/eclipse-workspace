<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.User" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.utils.Pager" %>
<%@page import="com.pojo.WithDrew" %>
<%@ include file="check.jsp" %>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User user = (User)request.getSession().getAttribute("sys_user");
String psw2 = (String)request.getSession().getAttribute("psw2");
String state = (String)request.getAttribute("state");
String startTime = (String)request.getAttribute("startTime");
String endTime = (String)request.getAttribute("endTime");
double[] sum = (double[])request.getAttribute("sum");
	if (user == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else if(StringUtil.notNull(psw2).equals("")){
		out.println("<script>");
		out.println("window.location.href='psw_login.jsp';");
		out.println("</script>");
	}else if(!StringUtil.notNull(psw2).equals(user.getPassword2())){
		out.println("<script>");
		out.println("window.location.href='psw_login.jsp';");
		out.println("</script>");
	}else{

%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<link rel="apple-touch-icon-precomposed" sizes="114x114" href="../images/splash/splash-icon.png">
<link rel="apple-touch-startup-image" href="../images/splash/splash-screen.png" 			media="screen and (max-device-width: 320px)" />  
<link rel="apple-touch-startup-image" href="../images/splash/splash-screen_402x.png" 		media="(max-device-width: 480px) and (-webkit-min-device-pixel-ratio: 2)" /> 
<link rel="apple-touch-startup-image" sizes="640x1096" href="../images/splash/splash-screen_403x.png" />
<link rel="apple-touch-startup-image" sizes="1024x748" href="../images/splash/splash-screen-ipad-landscape" media="screen and (min-device-width : 481px) and (max-device-width : 1024px) and (orientation : landscape)" />
<link rel="apple-touch-startup-image" sizes="768x1004" href="../images/splash/splash-screen-ipad-portrait.png" media="screen and (min-device-width : 481px) and (max-device-width : 1024px) and (orientation : portrait)" />
<link rel="apple-touch-startup-image" sizes="1536x2008" href="../images/splash/splash-screen-ipad-portrait-retina.png"   media="(device-width: 768px)	and (orientation: portrait)	and (-webkit-device-pixel-ratio: 2)"/>
<link rel="apple-touch-startup-image" sizes="1496x2048" href="../images/splash/splash-screen-ipad-landscape-retina.png"   media="(device-width: 768px)	and (orientation: landscape)	and (-webkit-device-pixel-ratio: 2)"/>

<title>提现记录</title>

<link href="../styles/style.css"     		rel="stylesheet" type="text/css">
<link href="../styles/framework.css" 		rel="stylesheet" type="text/css">
<link href="../styles/owl.carousel.css" 	 rel="stylesheet" type="text/css">
<link href="../styles/owl.theme.css" 		rel="stylesheet" type="text/css">
<link href="../styles/swipebox.css"		 rel="stylesheet" type="text/css">
<link href="../styles/colorbox.css"		 rel="stylesheet" type="text/css">


<script type="text/javascript" src="../scripts/jquery.js"></script>
<script type="text/javascript" src="../scripts/jqueryui.js"></script>
<script type="text/javascript" src="../scripts/owl.carousel.min.js"></script>
<script type="text/javascript" src="../scripts/jquery.swipebox.js"></script>
<script type="text/javascript" src="../scripts/colorbox.js"></script>
<script type="text/javascript" src="../scripts/snap.js"></script>
<script type="text/javascript" src="../scripts/contact.js"></script>
<script type="text/javascript" src="../scripts/custom.js"></script>
<script type="text/javascript" src="../scripts/framework.js"></script>
<script type="text/javascript" src="../scripts/framework.launcher.js"></script>
</head>
<body>

<div id="preloader">
	<div id="status">
    	<p class="center-text">
			欢迎进入直销管理系统！！！
        </p>
    </div>
</div>

<div class="top-deco"></div>

<div class="content">
	<div class="header">
    	<a href="#" class="homepage-logo">
        	<img src="../images/misc/index-logo.png" alt="img">
        </a>
        <p class="go-title-ch">
        	提现记录</p><p class="go-title-en">WITHDREW DETAIL
        </p>
         <a href="javascript:history.go(-1);" class="go-logo">返回</a>
        <a href="main.jsp" class="go-home">首页</a>
        <a href="#" class="go-menu">菜单</a>
        <a href="#" class="go-back">关闭</a>
    </div>
    <div class="decoration"></div>
    <div class="navigation">
    	<div class="corner-deco"></div>
    	<div class="navigation-wrapper">
          <%@ include file="menu.jsp" %>  
        </div>
    </div>
</div>
<div class="content">
    <div class="container">
    <div class="container no-bottom">
     <div class="container">
            <a href="WithDrewServlet.do?method=add" class="button  button-blue">申请提现</a>
        </div>
       <table  class="table-detail">
            <tr>
                <th>申请编号/当前状态</th>
                <th>充值金额/手续费</th>
                <th>到账金额/申请时间</th>
                 <th>操作</th>
            </tr>
           	 <%
								 Pager pager = (Pager)request.getAttribute("pager");
						if(pager!=null){
							Collection coll = pager.getResultList();
							if(coll!=null){
									Iterator it = coll.iterator();
									int t =0;
									while(it.hasNext()){
									WithDrew ca = (WithDrew)it.next();
									t++;
							%>
            <tr>
                <td><%=StringUtil.notNull(ca.getApplyId())%>
                <br><%if(ca.getState()==1) {%>待审批<%}else if(ca.getState()==2){ %>
										审核通过<%}else if(ca.getState()==3){ %>
										审核不通过<%}else { %>已删除<%} %></td>
                <td><%=StringUtil.decimalFormat(ca.getAmount()) %>
                <br><%=StringUtil.decimalFormat(ca.getFee()) %> 
                </td>
                <td><%=StringUtil.decimalFormat(ca.getActualAmount()) %> 
                <br><%=StringUtil.parseToString(ca.getApplyTime(), DateUtil.yyyyMMddHHmmss)%> 
                </td>
                <td><%if(ca.getState()==1) {%><a href="WithDrewServlet.do?method=apply_del&id=<%=StringUtil.notNull(ca.getApplyId()) %>" onclick="delcfm();">删除 </a><%} %></td>
 </tr>
		<%}%>
							<tr>
							<td colspan="4" style="text-align:left;">提现合计：<%=StringUtil.decimalFormat(sum[0])%></td>
						 </tr>
							<%}
						}else{ %><tr>
							<td colspan="4">暂无记录！</td>
						 </tr>
						 <%} %>
        </table>
        </div>
   </div>      
     <% if(pager!=null){ %>
     <div class="one-half-responsive">
        <div class="container">
       <%if(pager.getPageNo()!=1){ %>
      <a href="WithDrewServlet.do?method=list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=1&state=<%=StringUtil.notNull(state) %>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>" class="button-icon icon-home button-orange">首页</a>
        
       <a href="WithDrewServlet.do?method=list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()-1%>&state=<%=StringUtil.notNull(state) %>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>" class="button-icon icon-left button-blue">上一页</a>
        <%} if(pager.getPageNo()!=pager.getPageCount()){ %>
   		 <a href="WithDrewServlet.do?method=list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()+1%>
						&state=<%=StringUtil.notNull(state) %>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>" class="button-icon icon-right button-green">下一页</a>
       <a href="WithDrewServlet.do?method=list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageCount()%>&state=<%=StringUtil.notNull(state) %>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>" class="button-icon icon-end button-magenta">尾页</a><%} %>
      </div>
      </div>
      <%} %>
       <div class="decoration"></div>
        <div class="container no-bottom">
    <p>温馨提示：如上述明细没有包含当日最新发生明细，请您稍后重查。</p>
      </div>
     
</div>
 <div class="decoration"></div>
 <%@ include file="footer.jsp" %>  
<div style="height:28px;"></div>
<div class="bottom-deco"></div>

<script type="text/javascript">
	function delcfm() {
		if (!confirm("确认要进行删除操作，该操作执行后将不可逆转？")) {
			return false;
		}
	}

	$(function() {

		$("#batchDel").click(function() {

			if (!confirm("确认要删除？")) {
				return false;
			} else {
				$('#delform').attr("action", "apply_deleteAll.action");
				$('#delform').submit();
			}

		});
		
		$("#userBack").click(function() {

			if (!confirm("是否要进行退单操作？")) {
				window.event.returnValue = false;
			} else {
				$('#delform').attr("action", "user_back.action");
				$('#delform').submit();
				window.event.returnValue = true;
			}

		});

		$("#search")
				.click(
						function() {
											var startTime = $("#startTime")
													.val();
											var endTime = $("#endTime").val();
											var str = "";
											var a = /((^((1[8-9]\d{2})|([2-9]\d{3}))(-)(10|12|0?[13578])(-)(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(11|0?[469])(-)(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(0?2)(-)(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)(-)(0?2)(-)(29)$)|(^([3579][26]00)(-)(0?2)(-)(29)$)|(^([1][89][0][48])(-)(0?2)(-)(29)$)|(^([2-9][0-9][0][48])(-)(0?2)(-)(29)$)|(^([1][89][2468][048])(-)(0?2)(-)(29)$)|(^([2-9][0-9][2468][048])(-)(0?2)(-)(29)$)|(^([1][89][13579][26])(-)(0?2)(-)(29)$)|(^([2-9][0-9][13579][26])(-)(0?2)(-)(29)$))/;
											
											if (startTime == "") {
												if(endTime!=""){
													str = str + "开始时间不能为空！\n";
													$("#startTime").focus();
												}
											}else if (endTime == "") {
												if(startTime!=""){
													str = str + "结束时间不能为空！\n";
													$("#endTime").focus();
												}
											} else if (startTime.match(a) == null) {
												str = str + "开始时间格式有误！\n";
												$("#startTime").focus();
											} else if (endTime.match(a) == null) {
												str = str + "结束时间格式有误！\n";
												$("#endTime").focus();
											}else if(startTime>endTime)
												{
												str = str + "开始时间不能超过结束时间！\n";
												$("#endTime").focus();
											}
											
											if (str == "") {
												$('#sform').submit();
											} else {
												alert(str);
												return false;
											}
						});
	});
</script>
</body>
</html>
<%}%>