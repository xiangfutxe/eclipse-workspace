<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>导航栏</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>

<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
		}else{
			$(this).next('ul').slideDown();
		}
	});
});	
</script>


</head>

<body style="background:#f0f9fd;" class="leftbody">
	<a href="index.jsp" target="rightFrame"><span class="lefttop"><span></span>首页导航</span></a>
    <dl class="leftmenu">
    <dd>
    <div class="title">
    <span><img src="images/icons/16/message.png"/></span>信息管理
    </div>
    	<ul class="menuson">
        <li><cite></cite><a href="index.jsp" target="rightFrame">首页导航</a><i></i></li>
        <li><cite></cite><a href="news.do" target="rightFrame">新闻列表</a><i></i></li>
        <li><cite></cite><a href="imgtable.html" target="rightFrame">图片数据表</a><i></i></li>
        <li><cite></cite><a href="news_add.jsp" target="rightFrame">添加新闻</a><i></i></li>
        <li><cite></cite><a href="imglist.html" target="rightFrame">图片列表</a><i></i></li>
        <li><cite></cite><a href="imglist1.html" target="rightFrame">自定义</a><i></i></li>
        <li><cite></cite><a href="tools.html" target="rightFrame">常用工具</a><i></i></li>
        <li><cite></cite><a href="filelist.html" target="rightFrame">信息管理</a><i></i></li>
        <li><cite></cite><a href="tab.html" target="rightFrame">Tab页</a><i></i></li>
        <li><cite></cite><a href="error.html" target="rightFrame">404页面</a><i></i></li>
        </ul>    
    </dd>
        
    
    <dd>
    <div class="title">
    <span><img src="images/icons/16/user.png" /></span>会员管理
    </div>
    <ul class="menuson">
        <li><cite></cite><a href="member_list.do" target="rightFrame">会员列表</a><i></i></li>
        <li><cite></cite><a href="member_add.do" target="rightFrame">新增会员</a><i></i></li>
        </ul>     
    </dd> 
    
     <dd>
    <div class="title">
    <span><img src="images/icons/16/network.png" /></span>网络管理
    </div>
    <ul class="menuson">
        <li><cite></cite><a href="#">编辑内容</a><i></i></li>
        <li><cite></cite><a href="#">发布信息</a><i></i></li>
        <li><cite></cite><a href="#">档案列表显示</a><i></i></li>
        </ul>     
    </dd> 
    
    <dd>
    <div class="title">
    <span><img src="images/icons/16/product.png" /></span>产品管理
    </div>
    <ul class="menuson">
        <li><cite></cite><a href="#">产品管理</a><i></i></li>
        <li><cite></cite><a href="#">产品分类</a><i></i></li>
        <li><cite></cite><a href="attribute.do"  target="rightFrame">产品属性</a><i></i></li>
        <li><cite></cite><a href="unit.do"  target="rightFrame">计量单位</a><i></i></li>
        </ul>     
    </dd> 
     <dd>
    <div class="title">
    <span><img src="images/icons/16/store.png" /></span>仓库管理
    </div>
    <ul class="menuson">
        <li><cite></cite><a href="#">编辑内容</a><i></i></li>
        <li><cite></cite><a href="#">发布信息</a><i></i></li>
        <li><cite></cite><a href="#">档案列表显示</a><i></i></li>
        </ul>     
    </dd> 
     <dd>
    <div class="title">
    <span><img src="images/icons/16/orders.png" /></span>订单管理
    </div>
    <ul class="menuson">
        <li><cite></cite><a href="#">编辑内容</a><i></i></li>
        <li><cite></cite><a href="#">发布信息</a><i></i></li>
        <li><cite></cite><a href="#">档案列表显示</a><i></i></li>
        </ul>     
    </dd> 
     <dd>
    <div class="title">
    <span><img src="images/icons/16/money.png" /></span>财务管理
    </div>
    <ul class="menuson">
        <li><cite></cite><a href="#">编辑内容</a><i></i></li>
        <li><cite></cite><a href="#">发布信息</a><i></i></li>
        <li><cite></cite><a href="#">档案列表显示</a><i></i></li>
        </ul>     
    </dd> 
     <dd>
    <div class="title">
    <span><img src="images/icons/16/reward.png" /></span>奖金管理
    </div>
    <ul class="menuson">
        <li><cite></cite><a href="#">编辑内容</a><i></i></li>
        <li><cite></cite><a href="#">发布信息</a><i></i></li>
        <li><cite></cite><a href="#">档案列表显示</a><i></i></li>
        </ul>     
    </dd> 
     <dd>
    <div class="title">
    <span><img src="images/icons/16/report-form.png" /></span>报表管理
    </div>
    <ul class="menuson">
        <li><cite></cite><a href="#">编辑内容</a><i></i></li>
        <li><cite></cite><a href="#">发布信息</a><i></i></li>
        <li><cite></cite><a href="#">档案列表显示</a><i></i></li>
        </ul>     
    </dd> 
     <dd>
    <div class="title">
    <span><img src="images/icons/16/job.png" /></span>员工管理
    </div>
    <ul class="menuson">
        <li><cite></cite><a href="#">编辑内容</a><i></i></li>
        <li><cite></cite><a href="#">发布信息</a><i></i></li>
        <li><cite></cite><a href="#">档案列表显示</a><i></i></li>
        </ul>     
    </dd> 
     <dd><div class="title"><span><img src="images/icons/16/settings.png" /></span>基本资料</div>
    <ul class="menuson">
        <li><cite></cite><a href="admin_list.do" target="rightFrame">管理员管理</a><i></i></li>
        <li><cite></cite><a href="#" target="rightFrame">密码修改</a><i></i></li>
        <li><cite></cite><a href="#" target="rightFrame">日志查询</a><i></i></li>
          <li><cite></cite><a href="#" target="rightFrame">数据备份</a><i></i></li>
        
    </ul>    
    </dd>  
    
    <dd><div class="title"><span><img src="images/icons/16/settings.png" /></span>其他设置</div>
    <ul class="menuson">
        <li><cite></cite><a href="admin_list.do" target="rightFrame">管理员管理</a><i></i></li>
        <li><cite></cite><a href="#" target="rightFrame">密码修改</a><i></i></li>
        <li><cite></cite><a href="#" target="rightFrame">日志查询</a><i></i></li>
          <li><cite></cite><a href="#" target="rightFrame">数据备份</a><i></i></li>
        
    </ul>    
    </dd>  
    <dd><div class="title"><span><img src="images/icons/16/exit.png" /></span><a href="logout.do"  target="rightFrame">退出系统</a></div>
    </dd>   
    </dl>
</body>
</html>
