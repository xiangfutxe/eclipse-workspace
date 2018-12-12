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
	if(ranks[3][0].equals("1")||admin.getState()-1==0){
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>威腾后台-产品查询</title>
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

 $(".add_click").click(function(){
  $(".add_tip").fadeIn(200);
  });
  
  $(".add_tiptop a").click(function(){
  $(".add_tip").fadeOut(200);
});

  $(".add_sure").click(function(){
  $(".add_tip").fadeOut(100);
  $("#myform").attr("action","ProductServlet?method=add");
  $("#myform").submit();
});

  $(".add_cancel").click(function(){
  $(".add_tip").fadeOut(100);
});

$(".del_click").click(function(){
  $(".del_tip").fadeIn(200);
  });
  
  $(".del_tiptop a").click(function(){
  $(".del_tip").fadeOut(200);
});

  $(".del_sure").click(function(){
  $(".del_tip").fadeOut(100);
  $("#myform").attr("action","ProductServlet?method=remove");
  $("#myform").submit();
});

  $(".del_cancel").click(function(){
  $(".del_tip").fadeOut(100);
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
    <li><a href="#">产品管理</a></li>
    <li><a href="#">类型查询</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    	<div id="serch" class="tabson">
    	<form  id ="seachform" action="ProductServlet?method=list" method="post">
    <ul class="seachform">
    <li><label>产品名称：</label><input name="productName" type="text" class="scinput" value="${productName}" /></li>
     <li><label>状态:</label>
     <select id="isHide" name="isHide" class="dfinput2">
       <option value="">全部</option>
      <option value='0' <c:if test="${isHide=='0'}">selected</c:if>>热销中</option>
	<option value='1' <c:if test="${isHide=='1'}">selected</c:if>>已下架</option>
                                    </select>
                                </li>
     <li><input name="sub" id="search_sub" type="submit" class="scbtn" value="查询"/></li>
    
    </ul>
    </form>
    </div>
        <form id="myform" method="post">
    <div class="tools">
    	<ul class="toolbar">
    	<%if(ranks[3][1].equals("1")||admin.getState()-1==0){ %>
        <li class="add_click"><span><img src="images/t01.png" /></span>添加</li>
        <%} %>
        <%if(ranks[3][3].equals("1")||admin.getState()-1==0){ %>
        <li  class="del_click"><span><img src="images/t03.png" /></span>批量删除</li>
        <%} %>
        </ul>
    </div>
    <table class="tablelist">
    	<thead>
    	<tr>
        <th><input  id="ckAll"  name="" type="checkbox" value=""/></th>
        <th>序号<i class="sort"><img src="images/px.gif" alt=""/></i></th>
      <th>图片</th>
      <th>编号</th>
      <th>名称</th>
       <th>规格</th>
       <th>分类</th>
        <th>类型</th>
        <th>零售价</th>
         <th>当前库存</th>
        <th>累计库存</th>
         <th>状态</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
       
        <c:if test="${pager!=null}">
        <c:forEach items="${pager.resultList}" var="result" varStatus="status">
        <tr>
        <td><input name="ids" type="checkbox" value="${result.id}" /></td>
        <td>${status.index+1}</td>
        <td><img src="../upload/product/${result.imageUrl}" alt="暂无图片"  width="100px" height="100px"></img></td>
         <td>${result.productId}</td>
          <td>${result.productName}</td>
           <td>${result.specification}</td>
           <td>${result.productType}</td>
            <td>${result.productSort}</td>
            <td>${result.price}</td>
              <td>${result.num}</td>
               <td>${result.totalNum}</td>
                  <td><c:if test="${result.isHide==0}">热销中</c:if><c:if test="${result.isHide==1}">已下架</c:if></td>
      
        <td>
       
        <a href="ProductServlet?method=edit&id=${result.id}" class="tablelink" >详情</a>
         <%	if(ranks[3][2].equals("1")||admin.getState()-1==0){
 %>
      	<c:if test="${result.isHide==1}">
       	    <a href="ProductServlet?method=is_hide&id=${result.id}" class="tablelink" onclick="javascript:if(confirm('确定上架该产品?')==false)return false;">上架</a>
        </c:if>
       	 <c:if test="${result.isHide==0}">
        	 <a href="ProductServlet?method=is_hide_no&id=${result.id}" class="tablelink" onclick="javascript:if(confirm('确定下架该产品?')==false)return false;">下架</a>
        </c:if>
       		 <a href="ProductServlet?method=img_edit&id=${result.id}" class="tablelink">上传图片</a>
        	 <a href="ProductServlet?method=editPrice&id=${result.id}" class="tablelink">价格</a>
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
    	<form  name="pageSizeForm"  action="ProductServlet?method=list" method="post" >
    	<input type="hidden" name="pageNoStr" value="1"/>
    	<input type="hidden" name="productName" value="${productName}"/>  
   		 <input type="hidden" name="isHide" value="${isHide}"/> 
    	第&nbsp;<i class="blue">${pager.pageNo}&nbsp;</i>页&nbsp;共&nbsp;<i class="blue">${pager.pageCount}&nbsp;</i>页&nbsp;共<i class="blue">${pager.rowCount}</i>条记录，当前每页显示
  <select  name="pageSizeStr" id="pageSizeStr" onchange="javascript:pageSizeForm.submit();" class="dfinput3">
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
        <li class="paginItem"><a href="ProductServlet?method=list&productName=${productName}&isHide=${isHide}&pageSizeStr=${pager.pageSize}&pageNoStr=${pager.pageNo-1}"><span class="pagepre"></span></a></li>
        </c:if>
         <c:if test="${pager.pageNo<=1}">
        <li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>
        </c:if>
        <c:if test="${pager.pageCount>3}">
         <c:forEach var="p" begin="1" end="3" step="1">
         <c:if test="${p==pager.pageNo}"><li class="paginItem current" ><a href="javascript:;">${p}</a></li></c:if>
         <c:if test="${p!=pager.pageNo}"><li class="paginItem" ><a href="ProductServlet?method=list&productName=${productName}&isHide=${isHide}&pageSizeStr=${pager.pageSize}&pageNoStr=${p}">${p}</a></li></c:if>
         
         </c:forEach>
         <li class="paginItem more"><a href="javascript:;">...</a></li>
            <c:if test="${p==pager.pageNo}"><li class="paginItem current" ><a href="javascript:;">${pager.pageCount}</a></li></c:if>
         <c:if test="${p!=pager.pageNo}"><li class="paginItem" ><a href="ProductServlet?method=list&productName=${productName}&isHide=${isHide}&pageSizeStr=${pager.pageSize}&pageNoStr=${pager.pageCount}">${pager.pageCount}</a></li></c:if>
         
       </c:if>
       <c:if test="${pager.pageCount<4}">
          <c:forEach var="p" begin="1" end="${pager.pageCount}" step="1">
        	 <c:if test="${p==pager.pageNo}"><li class="paginItem current" ><a href="javascript:;">${p}</a></li></c:if>
        	 <c:if test="${p!=pager.pageNo}"><li class="paginItem" ><a href="ProductServlet?method=list&productName=${productName}&isHide=${isHide}&pageSizeStr=${pager.pageSize}&pageNoStr=${p}">${p}</a></li></c:if>
        </c:forEach>
       </c:if>
              <c:if test="${pager.pageCount-pager.pageNo==0}">
        <li class="paginItem"><a href="javascript:;"><span class="pagenxt"></span></a></li>
        </c:if>
           <c:if test="${pager.pageCount-pager.pageNo>0}">
       
        <li class="paginItem"><a href="ProductServlet?method=list&productName=${productName}&isHide=${isHide}&pageSizeStr=${pager.pageSize}&pageNoStr=${pager.pageNo+1}"><span class="pagenxt"></span></a></li>
        </c:if>
        </ul>
    </div>
    </c:if>
    
    <div class="tip">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
      <div class="tipinfo">
        <span><img src="images/ticon_edit.png" /></span>
        <div class="tipright">
        <p>是否确认对信息的修改 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        <div class="tipbtn">
        <input name="" type="button"  class="sure" value="确定" />&nbsp;
        <input name="" type="button"  class="cancel" value="取消" />
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
    
    
    </div>
    
  	<script type="text/javascript"> 
      $("#usual1 ul").idTabs(); 
    </script>
    
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