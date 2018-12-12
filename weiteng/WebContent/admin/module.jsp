<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="com.ssm.pojo.Admin" %>
<%@ page import="com.ssm.utils.Constants" %>
<%@ page import="com.ssm.utils.StringUtil" %>
<% 
Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
if (admin == null) {
	out.println("<script>");
	out.println("parent.window.location.href='login.jsp';");
	out.println("</script>");
}else{
 String[][] ranks = StringUtil.getRankStr(admin.getRank());
	if(ranks[0][5].equals("1")||admin.getState()-1==0){
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>模块列表</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/tip.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>

<script type="text/javascript">
$(document).ready(function(){
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});

$(".del_click").click(function(){
  $(".del_tip").fadeIn(200);
  });
  
  $(".del_tiptop a").click(function(){
  $(".del_tip").fadeOut(200);
});

  $(".del_sure").click(function(){
  $(".del_tip").fadeOut(100);
  return true;
});

  $(".del_cancel").click(function(){
  $(".del_tip").fadeOut(100);
  return false;
});

$(".remove_click").click(function(){
  $(".remove_tip").fadeIn(200);
  });
  
  $(".remove_tiptop a").click(function(){
  $(".remove_tip").fadeOut(200);
});

  $(".remove_sure").click(function(){
  $(".remove_tip").fadeOut(100);
   $("#myform").attr("action","ModuleServlet?method=delAll");
  $("#myform").submit();
});

  $(".remove_cancel").click(function(){
  $(".remove_tip").fadeOut(100);
});
  
  $(".add_click").click(function(){
	  $(".add_tip").fadeIn(200);
	  });
	  
	  $(".add_tiptop a").click(function(){
	  $(".add_tip").fadeOut(200);
	});

	  $(".add_sure").click(function(){
	  $(".add_tip").fadeOut(100);
	   $("#myform").attr("action","ModuleServlet?method=add");
	  $("#myform").submit();
	});

	  $(".add_cancel").click(function(){
	  $(".add_tip").fadeOut(100);
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
});
</script>


</head>


<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="index.jsp">首页</a></li>
    <li><a href="#">信息管理</a></li>
    <li><a href="#">模块管理</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    <form  id="myform" method="post">
    <div class="tools">
    	<ul class="toolbar">
    	<%if(ranks[0][6].equals("1")||admin.getState()-1==0){ %>
 <li class="add_click"><span><img src="images/t01.png" /></span>添加</li>
        <%} %>
        </ul>
    </div>
    
    
    <table class="tablelist">
    	<thead>
    	<tr>
        <th><input  id="ckAll"  name="" type="checkbox" value=""/></th>
        <th>序号<i class="sort"><img src="images/px.gif" /></i></th>
        <th>模块编号</th>
        <th>模块标题</th>
        <th>发布时间</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${pager!=null}">
        <c:forEach items="${pager.resultList}" var="news" varStatus="status">
        <tr>
        <td><input  name="ids" type="checkbox" value="${news.id}" /></td>
        <td>${status.index+1}</td>
        <td>${news.code}</td>
        <td>${news.title}</td>
        <td><fmt:formatDate value="${news.entryTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        <td>
          	<%if(ranks[0][7].equals("1")||admin.getState()-1==0){ %>
        <a href="ModuleServlet?method=edit&id=${news.id}" class="tablelink">详情</a> 
        <%} %>&nbsp;
        <%if(ranks[0][8].equals("1")||admin.getState()-1==0){ %>
        <a href="ModuleServlet?method=del&id=${news.id}" class="tablelink del_click">删除</a>
        <%} %>
        </td>
        </tr>
        </c:forEach>
        </c:if>    
        </tbody>
    </table>
    </form>
   
   
       <c:if test="${pager!=null}">
    <div class="pagin">
    
    	<div class="message">
    	<form  name="pageSizeForm"  action="ModuleServlet?method=list" method="post">
    	<input type="hidden" name="pageNoStr" value="1"/>
    	第&nbsp;<i class="blue">${pager.pageNo}&nbsp;</i>页&nbsp;共&nbsp;<i class="blue">${pager.pageCount}&nbsp;</i>页&nbsp;共<i class="blue">${pager.rowCount}</i>条记录，当前每页显示
    	<select  name="pageSizeStr" id="pageSizeStr" onchange="javascript:pageSizeForm.submit();" class="dfinput2">
    	<option value="10" <c:if test="${pager.pageSize==10}">selected</c:if>>10</option>
    	<option value="20"  <c:if test="${pager.pageSize==20}">selected</c:if>>20</option>
    	<option value="40"  <c:if test="${pager.pageSize==40}">selected</c:if>>40</option>
    	<option value="80"  <c:if test="${pager.pageSize==80}">selected</c:if>>80</option>
    	<option value="100"  <c:if test="${pager.pageSize==100}">selected</c:if>>100</option>
    	</select>条记录
    	</form>
    	</div>
       
        <ul class="paginList">
       <c:if test="${pager.pageNo>1}">
        <li class="paginItem"><a href="ModuleServlet?method=list&pageSizeStr=${pager.pageSize}&pageNoStr=${pager.pageNo-1}"><span class="pagepre"></span></a></li>
        </c:if>
         <c:if test="${pager.pageNo<=1}">
        <li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>
        </c:if>
        <c:if test="${pager.pageCount>3}">
         <c:forEach var="p" begin="1" end="3" step="1">
         <c:if test="${p==pager.pageNo}"><li class="paginItem current" ><a href="javascript:;">${p}</a></li></c:if>
         <c:if test="${p!=pager.pageNo}"><li class="paginItem" ><a href="ModuleServlet?method=list&pageSizeStr=${pager.pageSize}&pageNoStr=${p}">${p}</a></li></c:if>
         
         </c:forEach>
         <li class="paginItem more"><a href="javascript:;">...</a></li>
            <c:if test="${p==pager.pageNo}"><li class="paginItem current" ><a href="javascript:;">${pager.pageCount}</a></li></c:if>
         <c:if test="${p!=pager.pageNo}"><li class="paginItem" ><a href="ModuleServlet?method=list&pageSizeStr=${pager.pageSize()}&pageNoStr=${pager.pageCount}">${pager.pageCount}</a></li></c:if>
         
       </c:if>
       <c:if test="${pager.pageCount<4}">
          <c:forEach var="p" begin="1" end="${pager.pageCount}" step="1">
        	 <c:if test="${p==pager.pageNo}"><li class="paginItem current" ><a href="javascript:;">${p}</a></li></c:if>
        	 <c:if test="${p!=pager.pageNo}"><li class="paginItem" ><a href="ModuleServlet?method=list&pageSizeStr=${pager.pageSize}&pageNoStr=${p}">${p}</a></li></c:if>
        </c:forEach>
       </c:if>
              <c:if test="${pager.pageCount-pager.pageNo==0}">
        <li class="paginItem"><a href="javascript:;"><span class="pagenxt"></span></a></li>
        </c:if>
           <c:if test="${pager.pageCount-pager.pageNo>0}">
       
        <li class="paginItem"><a href="ModuleServlet?method=list&pageSizeStr=${pager.pageSize}&pageNoStr=${pager.pageNo+1}"><span class="pagenxt"></span></a></li>
        </c:if>
        </ul>
    </div>
    </c:if>
    
    
    <div class="del_tip">
    	<div class="del_tiptop"><span>提示信息</span><a></a></div>
      <div class="del_tipinfo">
        <span><img src="images/ticon_del.png" /></span>
        <div class="del_tipright">
        <p>是否确认要删除所选信息？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        <div class="del_tipbtn">
        <input name="" type="button"  class="del_sure" value="确定" />&nbsp;
        <input name="" type="button"  class="del_cancel" value="取消" />
        </div>
    </div>
    
    
     <div class="remove_tip">
    	<div class="remove_tiptop"><span>提示信息</span><a></a></div>
      <div class="remove_tipinfo">
        <span><img src="images/ticon_del.png" /></span>
        <div class="remove_tipright">
        <p>是否确认要对所选信息进行下架？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        <div class="remove_tipbtn">
        <input name="" type="button"  class="remove_sure" value="确定" />&nbsp;
        <input name="" type="button"  class="remove_cancel" value="取消" />
        </div>
    </div>
    
     <div class="add_tip">
    	<div class="add_tiptop"><span>提示信息</span><a></a></div>
      <div class="add_tipinfo">
        <span><img src="images/ticon_add.png" /></span>
        <div class="add_tipright">
        <p>是否确认进入新增页面？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        <div class="add_tipbtn">
        <input name="" type="button"  class="add_sure" value="确定" />&nbsp;
        <input name="" type="button"  class="add_cancel" value="取消" />
        </div>
    </div>
    
    </div>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
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