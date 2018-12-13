<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.Admin" %>
<%@page import="com.utils.StringUtil" %>
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
	String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[2][1].equals("1")||admin.getState().equals("1")){
%>
<!DOCTYPE html>
<HTML>
 <HEAD>
 <base href="<%=basePath%>">
  <TITLE> 安置关系</TITLE>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  
  <link rel="stylesheet" href="ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
  <script type="text/javascript" src="ztree/js/jquery-1.4.4.min.js"></script>
  <script type="text/javascript" src="ztree/js/jquery.ztree.core.js"></script>
   <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
<body>
 <div class="container clearfix">
   <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span>网络管理<span class="crumb-step">&gt;</span>
			安置关系</div>
        </div>
          <div class="search-wrap">
				<div class="search-content">
<form action="admin/UserServlet.do?method=admin_getBelong" id="searchForm" method="post">
					<table class="search-tab">
							<tr>
								<th>&nbsp;用户编号：</th>
								<td><input class="common-text" name="userId" id="userId"
									type="text" value='<%=StringUtil.notNull(userId)%>'>
								</td>
								<td><input class="btn btn-primary btn2" id="search"
									name="search" value="查询" type="submit"></td>
							</tr>
						</table>
							
					</form>
						</div>
			</div>
			
					<div class="content_wrap">
    <div class="zTreeDemoBackground left">
        <ul id="treeDemo" class="ztree"></ul>
    </div>
    </div>
    </div>
			</div>
    <SCRIPT type="text/javascript">
        var setting = {  
                data: {  
                    simpleData: {  
                        enable: true  
                    }  
                } ,
                async: {
                    enable: true,
                    url:"admin/UserServlet.do?method=admin_belongJson",
                    autoParam:["id", "name", "level"],
                    otherParam:{"otherParam":"zTreeAsyncTest"},
                    dataFilter: filter
                }
        };  
        function filter(treeId, parentNode, childNodes) {
            if (!childNodes) return null;
            for (var i=0, l=childNodes.length; i<l; i++) {
                childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
            }
            return childNodes;
        }
         var strJSON =<%=request.getAttribute("userJson")%>;
         if(strJSON==null) strJSON=[{id:1, pId:0, name:"AA000000", isParent:true,iconOpen:"ztree/css/zTreeStyle/img/diy/1_open.png", iconClose:"ztree/css/zTreeStyle/img/diy/1_close.png"}];
        var zNodes =strJSON;
  
        $(document).ready(function(){  
            $.fn.zTree.init($("#treeDemo"), setting, zNodes);  
        });  
    </SCRIPT>
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