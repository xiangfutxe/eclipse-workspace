<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.JoinInfo" %>
<%@page import="com.ssm.pojo.ProductType" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>
<%@page import="com.ssm.utils.Pager" %>
<%@page import="com.ssm.pojo.Admin" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
String userId = (String)request.getAttribute("userId");
String oldRankJoin = (String)request.getAttribute("oldRankJoin");
String newRankJoin = (String)request.getAttribute("newRankJoin");
String isEmpty = (String)request.getAttribute("isEmpty");
String startTime = (String)request.getAttribute("startTime");
String endTime = (String)request.getAttribute("endTime");
if(admin==null) {
out.println("<script>");
out.println("parent.window.location.href='login.jsp';");
out.println("</script>");
}else{
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[1][10].equals("1")||admin.getState().equals("1")){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>升级管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	  <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
    <script type="text/javascript" src="js/jquery.js"></script>
    <script src="js/calendar.js"></script>
    <script language="JavaScript">
     function delcfm() {
        if (!confirm("确认要进行升级退单操作？")) {
            return false;
        }
    }
    
	$(function() {
	
	$("#batchDel").click(function(){
	
		if (!confirm("确认要删除？")) {
            return  false;
        }else{
       		 $('#delform').submit();
        }
	
	});
	
	 $("#exportExcel").click(function(){
	  	  if (!confirm("确认提交导出Excel？")) {
				return false;
			}
			else{
	       		$("#searchForm").attr("action", "admin/JoinInfoServlet?method=exportExcel_rankJoin_up");
				$("#searchForm").submit();
	       		 return true;
			}
		 });
	
	$("#ckAll").click(function() {
		if(this.checked){ 
			$("input[name='ids']").each(function(){this.checked=true;}); 
		}else{ 
			$("input[name='ids']").each(function(){this.checked=false;}); 
		} 
	});
	
	$("input[name='ids']").click(function() {
    var $ids = $("input[name='ids']");
    $("#ckAll").prop("checked" , $ids.length == $subs.filter(":checked").length ? true :false);
  });
  
   $("#pageSize").bind("propertychange",function() {
    });
});
	</script>
  </head>
  
  <body>
  <div class="container clearfix">
   <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span>升级管理</div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
                <form action="admin/JoinInfoServlet?method=admin_list" id="searchForm" method="post">
                    <table class="search-tab">
                        <tr>
                            <td>会员编号:
                             <input class="common-text"  name="userId" id="userId" type="text" value='<%=StringUtil.notNull(userId)%>'>
                           &nbsp;起止日期:
								<input class="common-text" name="startTime" id="startTime" size="15"
									type="text" value='<%=StringUtil.notNull(startTime) %>' onfocus="new Calendar().show(this);">
								-<input class="common-text" name="endTime" id="endTime" size="15"
									type="text" value='<%=StringUtil.notNull(endTime) %>' onfocus="new Calendar().show(this);">
								</td>
								</tr>
								<tr>
                            <td>原级别：<select name="oldRankJoin" id="oldRankJoin">
                            <option value="" <%if(StringUtil.notNull(oldRankJoin).equals("")){ %>selected<%} %>>全部</option>
                            <option value="" <%if(StringUtil.notNull(oldRankJoin).equals("0")){ %>selected<%} %>>消费会员</option>
                              <option value="1" <%if(StringUtil.notNull(oldRankJoin).equals("1")){ %>selected<%} %>>银卡会员</option>
							 <option value="2" <%if(StringUtil.notNull(oldRankJoin).equals("2")){ %>selected<%} %>>金卡会员</option>
							<option value="3" <%if(StringUtil.notNull(oldRankJoin).equals("3")){ %>selected<%} %>>白金会员</option>
							<option value="4" <%if(StringUtil.notNull(oldRankJoin).equals("4")){ %>selected<%} %>>VIP会员</option>
							<option value="5" <%if(StringUtil.notNull(oldRankJoin).equals("5")){ %>selected<%} %>>至尊会员</option>
                            </select>
                            &nbsp; 新级别：<select name="newRankJoin" id="newRankJoin">
                            <option value="" <%if(StringUtil.notNull(newRankJoin).equals("")){ %>selected<%} %>>全部</option>
                              <option value="1" <%if(StringUtil.notNull(newRankJoin).equals("1")){ %>selected<%} %>>银卡会员</option>
							 <option value="2" <%if(StringUtil.notNull(newRankJoin).equals("2")){ %>selected<%} %>>金卡会员</option>
							<option value="3" <%if(StringUtil.notNull(newRankJoin).equals("3")){ %>selected<%} %>>白金会员</option>
							<option value="4" <%if(StringUtil.notNull(newRankJoin).equals("4")){ %>selected<%} %>>VIP会员</option>
							<option value="5" <%if(StringUtil.notNull(newRankJoin).equals("5")){ %>selected<%} %>>至尊会员</option>
                            </select>
                            &nbsp; 优惠升级：<select name="isEmpty" id="isEmpty">
                            <option value="" <%if(StringUtil.notNull(isEmpty).equals("")){ %>selected<%} %>>全部</option>
                              <option value="0" <%if(StringUtil.notNull(isEmpty).equals("0")){ %>selected<%} %>>实单升级</option>
							 <option value="1" <%if(StringUtil.notNull(isEmpty).equals("1")){ %>selected<%} %>>优惠升级</option>
							
                            </select>
                           
                            <input class="btn btn-primary btn2" name="sub" value="查询" type="submit">
                             <%		if(rankstr[1][11].equals("1")||admin.getState().equals("1")){
                              %>&nbsp; <input class="btn btn-info" name="exportExcel" id="exportExcel"  value="导出EXCEL" type="button">
                            <%} %>
                            </td>
                            
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div class="result-wrap">
           <form name="delform" id="delform"  method="post">
              
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th>日期</th>
                            <th>会员编号</th>
                            <th>会员信息</th>
                            <th>原等级</th>
                            <th>新等级</th>
                            <th>升级金额</th>
                            <th>升级PV</th>
                            <th>当前状态</th>
                            <th>操作</th>
                        </tr>
                        <%
 						Pager pager = (Pager)request.getAttribute("pager");
						if(pager!=null){
							Collection coll = pager.getResultList();
							if(coll!=null){
									Iterator it1 = coll.iterator();
									while(it1.hasNext()){
									JoinInfo p = (JoinInfo)it1.next();
							%>
					  	<tr>
						 <td> <%=StringUtil.parseToString(p.getEntryTime(), DateUtil.yyyyMMddHHmmss)%> </td>
						  <td> <%=StringUtil.notNull(p.getUserId())%> </td>
						   <td> <%=StringUtil.notNull(p.getUserName())%></td>
					  <td> <%if(p.getOldRankJoin()==0) {%>消费会员<%}else if(p.getOldRankJoin()==1) {%>银卡会员<%}else if(p.getOldRankJoin()==2){ %>金卡会员
					  <%}else if(p.getOldRankJoin()==3){ %>白金会员
					  <%}else if(p.getOldRankJoin()==4){ %>VIP会员
					  <%}else if(p.getOldRankJoin()==5){ %>至尊会员<%} %></td>
					    <td> <%if(p.getNewRankJoin()==1) {%>银卡会员<%}else if(p.getNewRankJoin()==2){ %>金卡会员
					  <%}else if(p.getNewRankJoin()==3){ %>白金会员
					  <%}else if(p.getNewRankJoin()==4){ %>VIP会员
					  <%}else if(p.getNewRankJoin()==5){ %>至尊会员<%} %></td>
						   <td>
						 <%=StringUtil.decimalFormat(p.getPrice()) %></td>
						  <td>
						 <%=StringUtil.decimalFormat(p.getPv()) %></td>
						   <td>
						 <%if(p.getState()==1){ %>待激活<%}else if(p.getState()==2){ %>正常<%}else{ %>已退单<%} %></td>
						  <td> <%if(p.getState()>0){ %>
<a class="link-update" href="admin/JoinInfoServlet?method=rankJoin_up_back&id=<%=p.getId()%>" onclick="javascript:if(!confirm('确认要进行升级退单操作？'))return false;">退单</a> <%} %></td>
						 </tr>
							<%}}
						}else{ %>
						 <tr>
						 <td colspan="9">暂无记录。</td>
						  </tr>
						 <%} %>
                    </table>
                   
                   
                     </div>
                     </form>
                     <%if(pager!=null){ %>
				 <div class="list-page"> 
                    <div class="list-page-left">
                   <form name="pageSizeForm"  action="admin/JoinInfoServlet?method=admin_list" method="post">
						<input type="hidden" name="userId" value="<%=StringUtil.notNull(userId)%>"/>
<input type="hidden" name="startTime" value="<%=StringUtil.notNull(startTime)%>"/>
<input type="hidden" name="endTime" value="<%=StringUtil.notNull(endTime)%>"/>
						<input type="hidden" name="oldRankJoin" value="<%=StringUtil.notNull(oldRankJoin)%>"/>
						<input type="hidden" name="newRankJoin" value="<%=StringUtil.notNull(newRankJoin)%>"/>
<input type="hidden" name="isEmpty" value="<%=StringUtil.notNull(isEmpty)%>"/>
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
                    
			</div> <div class="list-page-right">
			<form name="pageNoForm"  action="admin/JoinInfoServlet?method=admin_list" method="post">
					<input type="hidden" name="userId" value="<%=StringUtil.notNull(userId)%>"/>
					<input type="hidden" name="startTime" value="<%=StringUtil.notNull(startTime)%>"/>
<input type="hidden" name="endTime" value="<%=StringUtil.notNull(endTime)%>"/>
						<input type="hidden" name="pageSizeStr" value="<%=pager.getPageSize()%>"/>
					第<font color="red"><%=pager.getPageNo()%>
					</font>页&nbsp; 共<font color="red"><%=pager.getPageCount()%></font>页&nbsp; 共<font color="red"><%=pager.getRowCount()%></font>条记录&nbsp; &nbsp; &nbsp; 
					<%if(pager.getPageNo()==1) {%> 首页  | 上一页  |<%}else{%>
					<a
						href="admin/JoinInfoServlet?method=admin_list&userId=<%=StringUtil.notNull(userId)%>&oldRankJoin=<%=StringUtil.notNull(oldRankJoin)%>&newRankJoin=<%=StringUtil.notNull(newRankJoin)%>&isEmpty=<%=StringUtil.notNull(isEmpty)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=1">首页</a> | <a
						href="admin/JoinInfoServlet?method=admin_list&userId=<%=StringUtil.notNull(userId)%>&oldRankJoin=<%=StringUtil.notNull(oldRankJoin)%>&newRankJoin=<%=StringUtil.notNull(newRankJoin)%>&isEmpty=<%=StringUtil.notNull(isEmpty)%>&startTime=<%=StringUtil.notNull(startTime)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()-1%>">上一页</a>  | <%} %>
                 <%if(pager.getPageNo()!=pager.getPageCount()) {%>
					<a
						href="admin/JoinInfoServlet?method=admin_list&userId=<%=StringUtil.notNull(userId)%>&oldRankJoin=<%=StringUtil.notNull(oldRankJoin)%>&newRankJoin=<%=StringUtil.notNull(newRankJoin)%>&isEmpty=<%=StringUtil.notNull(isEmpty)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()+1%>">
                    下一页</a> | <a
						href="admin/JoinInfoServlet?method=admin_list&userId=<%=StringUtil.notNull(userId)%>&oldRankJoin=<%=StringUtil.notNull(oldRankJoin)%>&newRankJoin=<%=StringUtil.notNull(newRankJoin)%>&isEmpty=<%=StringUtil.notNull(isEmpty)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageCount()%>">尾页</a>
				<%}else{ %>下一页 | 尾页<%} %>&nbsp;&nbsp; &nbsp; 跳转到第<select
						name="pageNoStr" onchange="javascript:pageNoForm.submit();">
						<%for(int i=0;i<pager.getPageCount();i++){ %>
							<option value="<%=i+1 %>" <%if(pager.getPageNo()==(i+1)){%>selected<%} %>>
								<%=i+1 %>
							</option>
							<%} %>
					</select>页&nbsp;
			</form>
				</div>
                </div>
                <%}%>
			</div>
		</div>
		<!--/main-->
	</div>
</body>
</html>
<%
}else{
			out.println("<script>");
			out.println("alert('你没有权限进行该操作！')");
			out.println("</script>");
		}
}
%>
