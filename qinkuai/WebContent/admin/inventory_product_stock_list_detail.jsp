<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.Admin" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.ArithUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.pojo.InventoryProductStock" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
String token = (String)request.getSession().getAttribute("token");
String startTime = (String)request.getAttribute("startTime");
String endTime = (String)request.getAttribute("endTime");

	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[3][15].equals("1")||admin.getState().equals("1")){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>库存盘点</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/main.css" />
<script type="text/javascript" src="js/jquery.js"></script>
 <script type="text/javascript" src="js/lhgcalendar.min.js"></script>
	 <script type="text/javascript">
	 $(function(){
    $('#endTime').calendar({ format:'yyyy-MM-dd HH:mm:ss' });
});
</script>
  </head>
  
  <body>
   <div class="container clearfix">
   <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a>
            <span class="crumb-step">&gt;</span>仓库管理
            <span class="crumb-step">&gt;</span><span>盘点明细</span>
            <span class="crumb-step">&gt;</span><span>详情</span></div>
        </div>
        
 <% Collection coll = (Collection) request.getAttribute("coll");
 if(coll!=null){
   %>
  <div class="result-wrap">
   <!--startprint-->
         <form id="myform1" name="myform1"  method="post">
        
		  <div id="printContainer">
					<div class="result-content">
						<table  width="97%" 
						style="font_family:'微软雅黑';color:#000 !important;font-weight:normal;border:0px;border-collapse:separate; border-spacing:0px 10px;">
							<tbody>
							<tr style="font-size:20px;">
							<td colspan="3" align="center">
							<strong>广州秦筷餐饮服务有限公司盘点表</strong>
							<br>
							</td>
							</tr>
							<tr style="font-size:16px;">
									<td  align="center">日期：<%=StringUtil.notNull(startTime)%>到<%=StringUtil.notNull(endTime)%></td>
								</tr>
							</tbody>
						</table>
					</div> 
       	<div class="result-content">

						<table class="result-tab-2" width="100%">	
             <tr> <td width="8%">产品代码</td>
              <td width="12%">产品名称</td> 
              <td width="10%">规格</td>
              <td width="6%">单位</td>
               <td width="7%">单价</td>
                <td width="8%">所属仓库</td>
             <td width="8%">期初库存</td>
              <td width="8%">当期入库</td>
              <td width="8%">当期出库</td>
               <td width="8%">理论库存</td>
              <td width="8%">期末结存</td>
               <td width="8%">实际库存</td>
               </tr>
            
             <%	Iterator it = coll.iterator();
									int t =0;
									while(it.hasNext()){
									InventoryProductStock ps = (InventoryProductStock)it.next(); %>
             <tr> <td><%=ps.getProductId() %> </td>
             <td><%=ps.getProductName() %> </td>
             <td><%=ps.getSpecification()%> </td>
               <td><%=ps.getUnit()%> </td>
                  <td><%=ps.getPrice()%> </td>
                  <td><%=ps.getInventoryThree()%> </td>
                   <td><%=ps.getStartNum()%> </td>
                   <td><%=ps.getInNum()%> </td>
                   <td><%=ps.getOutNum()%> </td>
                    <td><%=ArithUtil.sub(ArithUtil.add(ps.getStartNum(),ps.getInNum()),ps.getOutNum())%></td>
              <td><%=ps.getSysNum()%> </td>
               <td><%=ps.getCurNum()%></td>
             </tr>
             <%} %>
			
 
</table>
 </div>
 </div>
 	<!--endprint-->
 	<div class="result-content">
						<table  width="100%" 
						style="font_family:'微软雅黑';color:#000 !important;font-weight:normal;border:0px;border-collapse:separate; border-spacing:0px 10px;">
							<tbody>
							<tr>
							<td>
							
                <input class="btn" value="返回上一页" type="button" onclick="javascript:history.go(-1);"/>&nbsp;
                 &nbsp;<input type="button" class="btn" value="打印盘点表"  onclick="preview()" /> 
             
             </td>
             </tr>
							</tbody>
						</table>
					</div> 
 </form>
 
 </div>
 <%} %>
 </div>
 </div>
 <script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="js/jquery.jqprint-0.3.js"></script>
<script language="Javascript">
function preview()
{
var b = false;
    bdhtml=window.document.body.innerHTML;
    sprnstr="<!--startprint-->";
    eprnstr="<!--endprint-->";
    prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+17);
    prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));
    window.document.body.innerHTML=prnhtml;
    window.print();
     window.document.body.innerHTML=bdhtml;
    
}
</script>
      <script type="text/javascript" src="js/custom.js"></script>
 <script type="text/javascript">
	$(function() {
  
  	 	 $("#btn1").click(function(){
	  	  if (!confirm("确认提交开始进行盘点！")) {
				return false;
			}
			else{
				$("#myform").attr("action", "admin/InventoryServlet.do?method=settle_stock_start");
	       		 $("#myform").submit();
	       		 return  true;
			}
		 });
		 
		  $("#btn2").click(function(){
	  	  if (!confirm("确认初始化至本次结算前状态！")) {
				return false;
			}
			else{
			$('#myform').attr("action", "admin/InventoryServlet.do?method=settle_stock_reset");
	       		 $('#myform').submit();
	       		return true;
			}
		 });
		 
		  $("#btn3").click(function(){
	  	  if (!confirm("确认提交盘点确认，盘点确认成功后将结束本期盘点！")) {
				return false;
			}
			else{
			$('#myform').attr("action", "admin/InventoryServlet.do?method=settle_stock_cfm");
	       		 $('#myform').submit();
	       		 return true;
			}
		 });
		  $("#btn4").click(function(){
	  	  if (!confirm("确认更新盘点产品数据？")) {
				 return false;
			}
			else{
			$('#myform1').attr("action", "admin/InventoryServlet.do?method=settle_stock_save");
	       		 $('#myform1').submit();
	       		 return true;
			}
		 });
   });
        
   </script>
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
