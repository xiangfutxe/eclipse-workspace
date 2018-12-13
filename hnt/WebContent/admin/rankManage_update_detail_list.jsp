<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.RankManageUpdateDetail" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.Constants" %>

<%@page import="com.ssm.utils.DateUtil" %>
<%@page import="com.ssm.utils.Pager" %>
<%@page import="com.ssm.pojo.Admin" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
String userId = (String)request.getAttribute("userId");
String oldRankManage = (String)request.getAttribute("oldRankManage");
String newRankManage = (String)request.getAttribute("newRankManage");
String tag = (String)request.getAttribute("tag");
String startTime = (String)request.getAttribute("startTime");
String endTime = (String)request.getAttribute("endTime");
if(admin==null) {
out.println("<script>");
out.println("parent.window.location.href='login.jsp';");
out.println("</script>");
}else{
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[1][23].equals("1")||admin.getState().equals("1")){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>调整列表</title>
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
	       		$("#searchForm").attr("action", "admin/RankManageUpdateDetailServlet?method=exportExcel_rankManage_update");
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
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span>调整列表</div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
                <form action="admin/RankManageUpdateDetailServlet?method=admin_list" id="searchForm" method="post">
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
                            <td>原级别：<select name="oldRankManage" id="oldRankManage">
                            <option value="" <%if(StringUtil.notNull(oldRankManage).equals("")){ %>selected<%} %>>全部</option>
                              <option value="1" <%if(StringUtil.notNull(oldRankManage).equals("1")){ %>selected<%} %>><%=Constants.RANK_1 %></option>
							 <option value="2" <%if(StringUtil.notNull(oldRankManage).equals("2")){ %>selected<%} %>><%=Constants.RANK_2 %></option>
							<option value="3" <%if(StringUtil.notNull(oldRankManage).equals("3")){ %>selected<%} %>><%=Constants.RANK_3 %></option>
							<option value="4" <%if(StringUtil.notNull(oldRankManage).equals("4")){ %>selected<%} %>><%=Constants.RANK_4 %></option>
							<option value="5" <%if(StringUtil.notNull(oldRankManage).equals("5")){ %>selected<%} %>><%=Constants.RANK_5 %></option>
                            </select>
                            &nbsp; 新级别：<select name="newRankManage" id="newRankManage">
                            <option value="" <%if(StringUtil.notNull(newRankManage).equals("")){ %>selected<%} %>>全部</option>
                              <option value="1" <%if(StringUtil.notNull(newRankManage).equals("1")){ %>selected<%} %>><%=Constants.RANK_1 %></option>
							 <option value="2" <%if(StringUtil.notNull(newRankManage).equals("2")){ %>selected<%} %>><%=Constants.RANK_2 %></option>
							<option value="3" <%if(StringUtil.notNull(newRankManage).equals("3")){ %>selected<%} %>><%=Constants.RANK_3 %></option>
							<option value="4" <%if(StringUtil.notNull(newRankManage).equals("4")){ %>selected<%} %>><%=Constants.RANK_4 %></option>
							<option value="5" <%if(StringUtil.notNull(newRankManage).equals("5")){ %>selected<%} %>><%=Constants.RANK_5 %></option>
                            </select>
                            &nbsp;标识：<select name="tag" id="tag">
                            <option value="" <%if(StringUtil.notNull(tag).equals("")){ %>selected<%} %>>全部</option>
                              <option value="0" <%if(StringUtil.notNull(tag).equals("0")){ %>selected<%} %>>降级</option>
							 <option value="1" <%if(StringUtil.notNull(tag).equals("1")){ %>selected<%} %>>升级</option>
							
                            </select>
                           
                            <input class="btn btn-primary btn2" name="sub" value="查询" type="submit">
                             <%		if(rankstr[1][24].equals("1")||admin.getState().equals("1")){
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
                            <th>标识</th>
                        </tr>
                        <%
 						Pager pager = (Pager)request.getAttribute("pager");
						if(pager!=null){
							Collection coll = pager.getResultList();
							if(coll!=null){
									Iterator it1 = coll.iterator();
									while(it1.hasNext()){
									RankManageUpdateDetail p = (RankManageUpdateDetail)it1.next();
							%>
					  	<tr>
						 <td> <%=StringUtil.parseToString(p.getEntryTime(), DateUtil.yyyyMMddHHmmss)%> </td>
						  <td> <%=StringUtil.notNull(p.getUserId())%> </td>
						   <td> <%=StringUtil.notNull(p.getUserName())%></td>
					  <td> <%if(p.getOldRankManage()==0) {%>-
					  <%}else if(p.getOldRankManage()==1) {%><%=Constants.RANK_1 %>
					  <%}else if(p.getOldRankManage()==2){ %><%=Constants.RANK_2 %>
					  <%}else if(p.getOldRankManage()==3){ %><%=Constants.RANK_3 %>
					  <%}else if(p.getOldRankManage()==4){ %><%=Constants.RANK_4 %>
					  <%}else if(p.getOldRankManage()==5){ %><%=Constants.RANK_5 %><%} %></td>
					    <td> <%if(p.getNewRankManage()==1) {%><%=Constants.RANK_1 %>
					    <%}else if(p.getNewRankManage()==2){ %><%=Constants.RANK_2 %>
					  <%}else if(p.getNewRankManage()==3){ %><%=Constants.RANK_3 %>
					  <%}else if(p.getNewRankManage()==4){ %><%=Constants.RANK_4 %>
					  <%}else if(p.getNewRankManage()==5){ %><%=Constants.RANK_5 %><%} %></td>
						  
						   <td>
						 <%if(p.getTag()==1){ %>升级<%}else if(p.getTag()==0){ %>降级<%}else{ %>-<%} %></td>
						  
						 </tr>
							<%}}
						}else{ %>
						 <tr>
						 <td colspan="6">暂无记录。</td>
						  </tr>
						 <%} %>
                    </table>
                   
                   
                     </div>
                     </form>
                     <%if(pager!=null){ %>
				 <div class="list-page"> 
                    <div class="list-page-left">
                   <form name="pageSizeForm"  action="admin/RankManageUpdateDetailServlet?method=admin_list" method="post">
						<input type="hidden" name="userId" value="<%=StringUtil.notNull(userId)%>"/>
<input type="hidden" name="startTime" value="<%=StringUtil.notNull(startTime)%>"/>
<input type="hidden" name="endTime" value="<%=StringUtil.notNull(endTime)%>"/>
						<input type="hidden" name="oldRankManage" value="<%=StringUtil.notNull(oldRankManage)%>"/>
						<input type="hidden" name="newRankManage" value="<%=StringUtil.notNull(newRankManage)%>"/>
<input type="hidden" name="tag" value="<%=StringUtil.notNull(tag)%>"/>
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
			<form name="pageNoForm"  action="admin/RankManageUpdateDetailServlet?method=admin_list" method="post">
					<input type="hidden" name="userId" value="<%=StringUtil.notNull(userId)%>"/>
					<input type="hidden" name="startTime" value="<%=StringUtil.notNull(startTime)%>"/>
<input type="hidden" name="endTime" value="<%=StringUtil.notNull(endTime)%>"/>
						<input type="hidden" name="pageSizeStr" value="<%=pager.getPageSize()%>"/>
					第<font color="red"><%=pager.getPageNo()%>
					</font>页&nbsp; 共<font color="red"><%=pager.getPageCount()%></font>页&nbsp; 共<font color="red"><%=pager.getRowCount()%></font>条记录&nbsp; &nbsp; &nbsp; 
					<%if(pager.getPageNo()==1) {%> 首页  | 上一页  |<%}else{%>
					<a
						href="admin/RankManageUpdateDetailServlet?method=admin_list&userId=<%=StringUtil.notNull(userId)%>&oldRankManage=<%=StringUtil.notNull(oldRankManage)%>&newRankManage=<%=StringUtil.notNull(newRankManage)%>&tag=<%=StringUtil.notNull(tag)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=1">首页</a> | <a
						href="admin/RankManageUpdateDetailServlet?method=admin_list&userId=<%=StringUtil.notNull(userId)%>&oldRankManage=<%=StringUtil.notNull(oldRankManage)%>&newRankManage=<%=StringUtil.notNull(newRankManage)%>&tag=<%=StringUtil.notNull(tag)%>&startTime=<%=StringUtil.notNull(startTime)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()-1%>">上一页</a>  | <%} %>
                 <%if(pager.getPageNo()!=pager.getPageCount()) {%>
					<a
						href="admin/RankManageUpdateDetailServlet?method=admin_list&userId=<%=StringUtil.notNull(userId)%>&oldRankManage=<%=StringUtil.notNull(oldRankManage)%>&newRankManage=<%=StringUtil.notNull(newRankManage)%>&tag=<%=StringUtil.notNull(tag)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()+1%>">
                    下一页</a> | <a
						href="admin/RankManageUpdateDetailServlet?method=admin_list&userId=<%=StringUtil.notNull(userId)%>&oldRankManage=<%=StringUtil.notNull(oldRankManage)%>&newRankManage=<%=StringUtil.notNull(newRankManage)%>&tag=<%=StringUtil.notNull(tag)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageCount()%>">尾页</a>
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
