<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>计量单位</title>
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
  return false;
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
});

$(".click_add").click(function(){
  $(".tip_add").fadeIn(200);
  });
  
  $(".tiptop_add a").click(function(){
  $(".tip_add").fadeOut(200);
});

  $(".sure_add").click(function(){
  $(".tip_add").fadeOut(100);
   $("#add_form").attr("action","unit_save.do");
  $("#add_form").submit();
});

  $(".cancel_add").click(function(){
  $(".tip_add").fadeOut(100);
});

$(".edit_click").click(function() {
$(".tip_edit").fadeIn(200);
$("#edit_id").attr("value", $(this).attr("rel"));
$("#edit_name").attr("value", $(this).attr("rev"));
});

 $(".tiptop_edit a").click(function(){
  $(".tip_edit").fadeOut(200);
});

  $(".sure_edit").click(function(){
  $(".tip_edit").fadeOut(100);
   $("#edit_form").attr("action","unit_update.do");
  $("#edit_form").submit();
});

  $(".cancel_edit").click(function(){
  $(".tip_edit").fadeOut(100);
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
    <li><a href="#">计量单位</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    <form  id="myform" method="post">
    <div class="tools">
    	<ul class="toolbar">
        </ul>
        
         <ul class="toolbar1">
        <li class="click_add"><span><img src="images/t01.png" /></span>新增单位</li>
        </ul>
 
    
    </div>
    
    
    <table class="tablelist">
    	<thead>
    	<tr>
        <th><input  id="ckAll"  name="" type="checkbox" value=""/></th>
        <th>序号<i class="sort"><img src="images/px.gif" /></i></th>
        <th>名称</th>
        <th>发布时间</th>
        <th>状态</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${pager!=null}">
        <c:forEach items="${pager.resultList}" var="unit" varStatus="status">
        <tr>
        <td><input  name="ids" type="checkbox" value="${unit.id}" /></td>
        <td>${status.index+1}</td>
        <td>${unit.name}</td>
        <td><fmt:formatDate value="${unit.entryTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
         <td>  <c:if test="${unit.state==1}">正常</c:if><c:if test="${unit.state==0}"><i>已删除</i></c:if></td>
        <td><a href="javascript:void(0);"  class="edit_click tablelink" rel="${unit.id}" rev="${unit.name}">编辑</a> <c:if test="${unit.state==1}"> <a href="unit_del.do?id=${unit.id}" class="del_click tablelink">删除</a></c:if></td>
        </tr>
        </c:forEach>
        </c:if>    
        </tbody>
    </table>
    </form>
   
   
       <c:if test="${pager!=null}">
    <div class="pagin">
    
    	<div class="message">
    	<form  name="pageSizeForm"  action="unit.do" method="post">
    	<input type="hidden" name="pageNoStr" value="1"/>
    	第&nbsp;<i class="blue">${pager.pageNo}&nbsp;</i>页&nbsp;共&nbsp;<i class="blue">${pager.pageCount}&nbsp;</i>页&nbsp;共<i class="blue">${pager.rowCount}</i>条记录，当前每页显示
    	<select  name="pageSizeStr" id="pageSizeStr" onchange="javascript:pageSizeForm.submit();">
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
        <li class="paginItem"><a href="unit.do?pageSizeStr=${pager.pageSize}&pageNoStr=${pager.pageNo-1}"><span class="pagepre"></span></a></li>
        </c:if>
         <c:if test="${pager.pageNo<=1}">
        <li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>
        </c:if>
        <c:if test="${pager.pageCount>3}">
         <c:forEach var="p" begin="1" end="3" step="1">
         <c:if test="${p==pager.pageNo}"><li class="paginItem current" ><a href="javascript:;">${p}</a></li></c:if>
         <c:if test="${p!=pager.pageNo}"><li class="paginItem" ><a href="unit.do?pageSizeStr=${pager.pageSize}&pageNoStr=${p}">${p}</a></li></c:if>
         
         </c:forEach>
         <li class="paginItem more"><a href="javascript:;">...</a></li>
            <c:if test="${p==pager.pageNo}"><li class="paginItem current" ><a href="javascript:;">${pager.pageCount}</a></li></c:if>
         <c:if test="${p!=pager.pageNo}"><li class="paginItem" ><a href="unit.do?pageSizeStr=${pager.pageSize()}&pageNoStr=${pager.pageCount}">${pager.pageCount}</a></li></c:if>
         
       </c:if>
       <c:if test="${pager.pageCount<4}">
          <c:forEach var="p" begin="1" end="${pager.pageCount}" step="1">
        	 <c:if test="${p==pager.pageNo}"><li class="paginItem current" ><a href="javascript:;">${p}</a></li></c:if>
        	 <c:if test="${p!=pager.pageNo}"><li class="paginItem" ><a href="unit.do?pageSizeStr=${pager.pageSize}&pageNoStr=${p}">${p}</a></li></c:if>
        </c:forEach>
       </c:if>
              <c:if test="${pager.pageCount-pager.pageNo==0}">
        <li class="paginItem"><a href="javascript:;"><span class="pagenxt"></span></a></li>
        </c:if>
           <c:if test="${pager.pageCount-pager.pageNo>0}">
       
        <li class="paginItem"><a href="unit.do?pageSizeStr=${pager.pageSize}&pageNoStr=${pager.pageNo+1}"><span class="pagenxt"></span></a></li>
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
    
    <form id="add_form" action="unit_save.do" method="post">
    <input type="hidden" name="token" value="${sessionScope.token}"/>
    <input type="hidden" name="pageNoStr" value="${pager.pageNo}"/>
    <input type="hidden" name="pageSizeStr" value="${pager.pageSize}"/>
     <div class="tip_add">
    	<div class="tiptop_add"><span>新增单位</span><a></a></div>
      <div class="tipinfo_add">
        <div class="tipright_add">
       <p><label>名称:</label><input name="name" id="name" type="text" /></p>       
        </div>
        </div>
        <div class="tipbtn_add">
        <input name="" type="button"  class="sure_add" value="确定" />&nbsp;
        <input name="" type="button"  class="cancel_add" value="取消" />
        </div>
    </div>
    </form>
    
    <form id="edit_form" action="unit_update.do" method="post">
    <input type="hidden" name="token" value="${sessionScope.token}"/>
    <input type="hidden" name="pageNoStr" value="${pager.pageNo}"/>
    <input type="hidden" name="pageSizeStr" value="${pager.pageSize}"/>
     <input type="hidden" id="edit_id" name="id" />     
     <div class="tip_edit">
    	<div class="tiptop_edit"><span>修改单位</span><a></a></div>
      <div class="tipinfo_edit">
        <div class="tipright_edit">
       <p><label>名称:</label><input name="name" id="edit_name" type="text" /></p>       
        </div>
        </div>
        <div class="tipbtn_edit">
        <input name="" type="button"  class="sure_edit" value="确定" />&nbsp;
        <input name="" type="button"  class="cancel_edit" value="取消" />
        </div>
    </div>
    </form>
    
    </div>
     
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');

	</script>
</body>
</html>
