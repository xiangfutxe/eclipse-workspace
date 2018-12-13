<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.ssm.pojo.User"%>
<%@ page import="com.ssm.utils.StringUtil"%>
<%@page import="com.ssm.utils.DateUtil" %>
<%@page import="com.ssm.utils.Pager" %>
<%@page import="com.ssm.pojo.WithDrew" %>

<%
User user = (User)request.getSession().getAttribute("sys_user");
String psw2 = (String)request.getSession().getAttribute("psw2");
String state = (String)request.getAttribute("state");
String startTime = (String)request.getAttribute("startTime");
String endTime = (String)request.getAttribute("endTime");
String message = (String)request.getAttribute("message");
if(!StringUtil.notNull(message).equals("")){
	out.println("<script>");
		out.println("alert('"+message+"')");
		out.println("</script>");
}		
if (user == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
}else{

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>奖金提现</title>
    
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
<script type="text/javascript" src="js/psw2.js"></script>
<script src="js/jquery.slider.js"></script>
<link rel="stylesheet" href="fonts/fontCss.css">
	<link rel="stylesheet" href="css/jquery.slider.css">
  <script src="../js/calendar.js"></script>
	      
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
          		<li><a href="AccountServlet?method=money_balance">电子钱包</a></li> 
				<li><A href="money_detail.jsp">账户明细</a></li> 
				<li  class="now"><a   href="WithDrewServlet?method=apply_list">奖金提现</a></li>
				<li><a href="ChargeApplyServlet?method=apply_list">账户充值</a></li>
				<li><a  href="AccountServlet?method=money_transfer">转账交易</a></li>
				<li><a href="AccountServlet?method=to_emoney">转账报单券</a></li>
                </ul>
                 <div style="height:300px;"></div>
                </div>
		 <div class="scd_mr">
            	<div class="ht_table">
            	<h2>当前位置：财务管理-&gt;<a>提现申请</a></h2>
            	</div>
            	<div class="content content-full-width" id="content-psw">
   		<input type="hidden" id="psw1" name="psw1" value="<%=user.getPassword2()%>"/>
		<div class="notice">
         <h2>支付密码</h2><span class="required_notification"></span>
         </div>
         	 <table class ="form-table">
  			 <tr>
  			 <td width="15%"><label><span class="required">*</span>请输入支付密码：</label></td>
  			 <td ><input id="psw2" name="psw2"  type="password" value="<%=psw2%>"/>&nbsp;<span id="psw2Tag"></span></td>
  			 </tr>
  			 </table>
		</div>
		<div class="content content-full-width" id="content-all">
       <div class="notice">
         <h2>奖金提现</h2><span class="required_notification"><a href="WithDrewServlet?method=apply_add" >新增提现</a></span>
   </div>
     <form  action="WithDrewServlet?method=apply_list"  id="sform" name="sform" method="post">
  <div class="p-li">
  <div class="form-table-one">
 申请状态：<select id="state" name="state">
										<option value="">--请选择状态--</option>
									<option value="1" <%if(StringUtil.notNull(state).equals("1")){ %>selected<%} %>
											>--待审核--</option>
										<option value="2" <%if(StringUtil.notNull(state).equals("2")){ %>selected<%} %>
											>--审核通过--</option>
										<option value="3" <%if(StringUtil.notNull(state).equals("3")){ %>selected<%} %>>--审核不通过--</option>
								<option value="0" <%if(StringUtil.notNull(state).equals("0")){ %>selected<%} %>
											>--已删除--</option>
								</select>
								&nbsp;申请时间：
									<input name="startTime" id="startTime" size="15"
									type="text" value='<%=StringUtil.notNull(startTime) %>' onfocus="new Calendar().show(this);">
								-
								<input  name="endTime" id="endTime" size="15"
									type="text" value='<%=StringUtil.notNull(endTime) %>' onfocus="new Calendar().show(this);">
								&nbsp;<input class="button-search blue-sky" id="search"
									name="search" value="查询" type="button"><span
									id="timeTag" style="color:red"></span>
			</div>	
  </div>
  </form>
  </div>
  			 <div class="p-li">
			<table class="result-tab" width="100%">
						<thead><tr>
							<th>申请编号</th><th>提现金额</th><th>手续费</th><th>到账金额</th><th>账户信息</th><th>申请时间</th><th>备注</th><th>状态</th>
						</tr></thead><tbody>
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
 <tr <%if(t%2==1){%>class="even"<%}%>>
 <td><%=StringUtil.notNull(ca.getApplyId())%>
 	<td><%=StringUtil.decimalFormat(ca.getAmount()) %> </td>
 		<td><%=StringUtil.decimalFormat(ca.getFee()) %> 
		<td><%=StringUtil.decimalFormat(ca.getActualAmount()) %> 
	 
<td><%=StringUtil.notNull(ca.getAccountName()) %> | <%=StringUtil.notNull(ca.getAccountId()) %> <br>
<%=StringUtil.notNull(ca.getBankName()) %><%=StringUtil.notNull(ca.getBankAdr()) %>
<td><%=StringUtil.parseToString(ca.getApplyTime(), DateUtil.yyyyMMddHHmmss)%></td>
 <td><%=StringUtil.notNull(ca.getComments())%></td>

 <td><%if(ca.getState()==1) {%>待审批<%}else if(ca.getState()==2){ %>
										审核通过<%}else if(ca.getState()==3){ %>
										审核不通过<%}else { %>已删除<%} %>
										
									</td>
 </tr>
		<%}}%><tr>
							<td colspan="8">暂无记录！</td>
						 </tr>
						 <%} %>
						 </tbody>
					</table>
				  </div>
              <div class="pager">
                	 <% if(pager!=null){ %>
                	<span class="left">
                   <form name="pageSizeForm"  action=WithDrewServlet?method=apply_list method="post" class="add_form">
							<input type="hidden" name="pageNoStr" value="1"/>
								<input type="hidden" name=state value="<%=StringUtil.notNull(state)%>"/>
							<input type="hidden" name="startTime" value="<%=StringUtil.notNull(startTime)%>"/>
							<input type="hidden" name="endTime" value="<%=StringUtil.notNull(endTime)%>"/>
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
								<form name="pageNoForm"  action="WithDrewServlet?method=apply_list" method="post"  class="add_form">
					
					<span class="right">
						<input type="hidden" name="pageSizeStr" value="<%=pager.getPageSize()%>"/>
							<input type="hidden" name=state value="<%=StringUtil.notNull(state)%>"/>
							<input type="hidden" name="startTime" value="<%=StringUtil.notNull(startTime)%>"/>
							<input type="hidden" name="endTime" value="<%=StringUtil.notNull(endTime)%>"/>
					第<font color="red"><%=pager.getPageNo()%>
					</font>页&nbsp; 共<font color="red"><%=pager.getPageCount()%></font>页共<font color="red"><%=pager.getRowCount()%></font>条记录&nbsp;
					<%if(pager.getPageNo()==1) {%> 首页 | 上一页  |<%}else{%>
					<a
						href="WithDrewServlet?method=apply_list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=1&state=<%=StringUtil.notNull(state) %>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>">首页</a> | <a
						href="WithDrewServlet?method=apply_list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()-1%>&state=<%=StringUtil.notNull(state) %>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>">上一页</a>  | <%} %>
                 <%if(pager.getPageNo()!=pager.getPageCount()) {%>
					<a
						href="WithDrewServlet?method=apply_list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()+1%>&state=<%=StringUtil.notNull(state) %>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>">
                    下一页</a> | <a
						href="WithDrewServlet?method=apply_list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageCount()%>&state=<%=StringUtil.notNull(state) %>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>">尾页</a>
				<%}else{ %>下一页 | 尾页<%} %>&nbsp; 跳转到第<select
						name="pageNoStr" onchange="javascript:pageNoForm.submit();">
						<%for(int i=0;i<pager.getPageCount();i++){ %>
							<option value="<%=i+1 %>" <%if(pager.getPageNo()==(i+1)){%>selected<%} %>>
								<%=i+1 %>
							</option>
							<%} %>
					</select>页
						
						</span>
						</form>
                <%} %>
					</div>
			
				  </div>
					</div>
         </div> 
               </div>
               </div>
<jsp:include page="footer.jsp" />
</body>
 <script language="JavaScript">
	function delcfm() {
		if (!confirm("确认要进行删除操作，该操作执行后将不可逆转？")) {
			return false;
		}
	}

	$(function() {
	
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
</html>
<%
}
%>