<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.Admin" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>
<%@page import="com.ssm.utils.Pager" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
String userId = (String)request.getAttribute("userId");
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
		if(rankstr[2][1].equals("1")||admin.getState().equals("1")){
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<base href="<%=basePath%>">
<TITLE>服务关系</TITLE>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/style.min.css" type="text/css">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jstree.min.js"></script>
<script type="text/javascript" src="js/json2.js"></script>
<script type="text/javascript" src="js/json_parse.js"></script>
 <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>

</HEAD>

<BODY>
  <div class="container clearfix">
   <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span>网络管理<span class="crumb-step">&gt;</span>
			服务关系</div>
        </div>
          <div class="search-wrap">
				<div class="search-content">
					<form action="admin/UserServlet.do?method=admin_getRefereeJson" id="searchForm" method="post">
						<table class="search-tab">
							<tr>
								<th width="70">用户编号：</th>
								<td><input class="common-text" name="userId" id="userId"
									type="text" value='<%=StringUtil.notNull(userId)%>'>
								</td>
								<td><input class="btn btn-primary btn2" id="search"
									name="search" value="查询" type="submit">(备注：仅显示10代关系图)</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
        <div class="result-wrap">
            <div class="result-content">
			<div id="jstree" class="demo">
 			 </div>
  		</div>
            </div>
        </div>
    </div>
</BODY>
<SCRIPT type="text/javascript">
	 $(function () {
    // 6 create an instance when the DOM is ready
    var strJSON =<%=request.getAttribute("userJson")%>;
    $('#jstree').jstree({
     'core' : {
	  'data' :strJSON
	  }
	 
	});
  });
</SCRIPT>
</HTML>

<%
}else{
			out.println("<script>");
			out.println("alert('你没有权限进行该操作！')");
			out.println("</script>");
		}
}
%>