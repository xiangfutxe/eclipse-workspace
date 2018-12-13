<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.RankJoinUpdate" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.utils.Pager" %>
<%@page import="com.pojo.Admin" %>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
String userId = (String)request.getAttribute("userId");
String startTime = (String)request.getAttribute("startTime");
String endTime = (String)request.getAttribute("endTime");
if(admin==null) {
out.println("<script>");
out.println("parent.window.location.href='login.jsp';");
out.println("</script>");
}else{
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[1][9].equals("1")||admin.getState().equals("1")){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>奖衔变更</title>
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
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">会员管理</span><span class="crumb-step">&gt;</span><span class="crumb-name">等级变更</span></div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
                <form action="admin/RankJoinUpdateServlet.do?method=rankJoin_list" method="post">
                    <table class="search-tab">
                        <tr>
                            <th width="120">会员编号:</th><td>
                             <input class="common-text"  name="userId" id="userId" type="text" value='<%=StringUtil.notNull(userId)%>'></td>
                            <th width="70">起止日期:</th>
								<td><input class="common-text" name="startTime" id="startTime" size="15"
									type="text" value='<%=StringUtil.notNull(startTime)%>' onfocus="new Calendar().show(this);">
								-
								<td><input class="common-text" name="endTime" id="endTime" size="15"
									type="text" value='<%=StringUtil.notNull(endTime)%>' onfocus="new Calendar().show(this);">
								</td>
                            <td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div class="result-wrap">
					<div class="result-title">
					
					</div>
              
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th>日期</th>
                            <th>会员编号</th>
                            <th>会员信息</th>
                            <th>原级别</th>
                            <th>新级别</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                        <%
                        	Pager pager = (Pager)request.getAttribute("pager");
                                                				if(pager!=null){
                                                					Collection coll = pager.getResultList();
                                                					if(coll!=null){
                                                							Iterator it1 = coll.iterator();
                                                							while(it1.hasNext()){
                                                							RankJoinUpdate p = (RankJoinUpdate)it1.next();
                        %>
					  	<tr>
						 <td> <%=StringUtil.parseToString(p.getEntryTime(), DateUtil.yyyyMMddHHmmss)%> </td>
						  <td> <%=StringUtil.notNull(p.getUserId())%> </td>
						   <td> <%=StringUtil.notNull(p.getUserName())%></td>
					  <td>  <% if (p.getRankJoinOld() == 1) {
									%>银级代理商<%
										} else if (p.getRankJoinOld() == 2) {
									%>
									金级代理商<%
										} else if (p.getRankJoinOld() == 3) {
									%>钻级代理商
									<%
										} else {
									%> -<%
										}
									%></td>
					    <td>    <% if (p.getRankJoinNew() == 1) {
									%>银级代理商<%
										} else if (p.getRankJoinNew() == 2) {
									%>
									金级代理商<%
										} else if (p.getRankJoinNew() == 3) {
									%>钻级代理商
									<%
										} else {
									%> -<%
										}
									%></td>
						  
						   <td><%if(p.getState()==1){ %>待审核
						   <%}else if(p.getState()==2){ %>已审核
						  
						   <%}else{ %>已删除<%} %></td>
						<td>
						 <%if(p.getState()==2&&p.getPrice()>0){ %>
						   	<a href="admin/RankJoinUpdateServlet.do?method=rankJoin_reset_reward&id=<%=p.getId()%>"
						   	onclick="javascript:if(!confirm('确认要将业绩归零，归零同时将会删除对应的订单和奖金，请谨慎操作？'))return false;">归零</a>
						   <%} %>
						</td>
						 </tr>
							<%}}
						}else{ %>
						 <tr>
						 <td colspan="6">暂无记录。</td>
						  </tr>
						 <%} %>
                    </table>
                   
                   
                     </div>
                     <%if(pager!=null){ %>
				 <div class="list-page"> 
                    <div class="list-page-left">
                   <form name="pageSizeForm"  action="admin/RankJoinUpdateServlet.do?method=rankJoin_list" method="post">
						<input type="hidden" name="userId" value="<%=StringUtil.notNull(userId)%>"/>
<input type="hidden" name="startTime" value="<%=StringUtil.notNull(startTime)%>"/>
<input type="hidden" name="endTime" value="<%=StringUtil.notNull(endTime)%>"/>
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
			<form name="pageNoForm"  action="admin/RankJoinUpdateServlet.do?method=rankJoin_list" method="post">
					<input type="hidden" name="userId" value="<%=StringUtil.notNull(userId)%>"/>
					<input type="hidden" name="startTime" value="<%=StringUtil.notNull(startTime)%>"/>
<input type="hidden" name="endTime" value="<%=StringUtil.notNull(endTime)%>"/>
						<input type="hidden" name="pageSizeStr" value="<%=pager.getPageSize()%>"/>
					第<font color="red"><%=pager.getPageNo()%>
					</font>页&nbsp; 共<font color="red"><%=pager.getPageCount()%></font>页&nbsp; 共<font color="red"><%=pager.getRowCount()%></font>条记录&nbsp; &nbsp; &nbsp; 
					<%if(pager.getPageNo()==1) {%> 首页  | 上一页  |<%}else{%>
					<a
						href="admin/RankJoinUpdateServlet.do?method=rankJoin_list&userId=<%=StringUtil.notNull(userId)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=1">首页</a> | <a
						href="admin/RankJoinUpdateServlet.do?method=rankJoin_list&userId=<%=StringUtil.notNull(userId)%>&startTime=<%=StringUtil.notNull(startTime)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()-1%>">上一页</a>  | <%} %>
                 <%if(pager.getPageNo()!=pager.getPageCount()) {%>
					<a
						href="admin/RankJoinUpdateServlet.do?method=rankJoin_list&userId=<%=StringUtil.notNull(userId)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()+1%>">
                    下一页</a> | <a
						href="admin/RankJoinUpdateServlet.do?method=rankJoin_list&userId=<%=StringUtil.notNull(userId)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageCount()%>">尾页</a>
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