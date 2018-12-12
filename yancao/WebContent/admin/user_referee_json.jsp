<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import="com.ssm.pojo.Admin"%>
<%@page import="com.ssm.utils.StringUtil"%>
<%@page import="com.ssm.utils.Constants"%>

<%
Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
if (admin == null) {
	out.println("<script>");
	out.println("parent.window.location.href='login.jsp';");
	out.println("</script>");
}else{
 String[][] ranks = StringUtil.getRankStr(admin.getRank());
	if(ranks[2][0].equals("1")||admin.getState()-1==0){
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>威腾后台-关系查询</title>
<link href="css/style1.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
  <link rel="stylesheet" href="../ztree/css/zTreeStyle/zTreeStyle.css" type="text/css" />
  <script type="text/javascript" src="../ztree/js/jquery.ztree.core.js"></script>

</head>


<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="index.jsp">首页</a></li>
    <li><a href="#">网络管理</a></li>
    <li><a href="#">关系查询</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    		<div id="serch" class="tabson">
    	<form  id ="searchForm" action="UserServlet?method=admin_getReferee" method="post">
    <ul class="seachform">
     
     
    <li><label>会员编号:</label>
     <input name="userId" type="text" class="scinput" value="${userId}" /></li>
                                
     <li><input name="sub" id="search_sub" type="submit" class="scbtn" value="查询"/></li>
    
    </ul>
    </form>
    <div>
     <div class="zTreeDemoBackground left">
    <%if(request.getAttribute("userJson")!=null){ %>
        <ul id="treeDemo" class="ztree" style="height:93%"></ul>
        <%} %>
    </div>
    </div>
    </div>
   
     
    
  
   
    
    </div>
     
    <script type="text/javascript">
        var setting = {  
                data: {  
                    simpleData: {  
                        enable: true  
                    }  
                } ,
                async: {
                    enable: true,
                    url:"UserServlet?method=admin_refereeJson",
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
         if(strJSON==null) strJSON=[{id:1, pId:0, name:"AA000000",level:0, isParent:true,iconOpen:"ztree/css/zTreeStyle/img/diy/1_open.png", iconClose:"ztree/css/zTreeStyle/img/diy/1_close.png"}];
        var zNodes =strJSON;
  
        $(document).ready(function(){  
            $.fn.zTree.init($("#treeDemo"), setting, zNodes);  
        });  
    </script>
</body>
</html>
<%
	}else{
		out.println("<script>");
		out.println("window.location.href='error_rank.jsp';");
		out.println("</script>");
	}
}
%>