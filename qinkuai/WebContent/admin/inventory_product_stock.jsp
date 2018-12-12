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
	}
	String message = (String) request.getAttribute("message");
	Integer stateStr = (Integer) request.getAttribute("state");
	String weekTag = (String) request.getAttribute("weekTag");
	String message1 = (String) request.getAttribute("message1");
	int state =0;
	if(stateStr!=null) state = stateStr;
	
	if (!StringUtil.notNull(message1).equals("")) {
		out.println("<script>");
		out.println("alert('"+message1+"')");
		out.println("</script>");
	}else{
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[3][14].equals("1")||admin.getState().equals("1")){
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
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span>仓库管理<span class="crumb-step">&gt;</span><span>库存盘点</span></div>
        </div>
        <div class="result-wrap">
         <form id="myform" name="myform"  method="post">
		  <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
		   <input type="hidden" name="weekTag" id="weekTag" value="<%=weekTag%>"/> 
		   <input type="hidden" name="startTime" id="startTime" value="<%=startTime%>"/> 
		   <input type="hidden" name="endTime" id="startTime" value="<%=endTime%>"/> 
		   
            <div class="content-list">
              <table class="center-tab" width="100%">
                        <tbody>
             <tr> <td>请按以下步骤进行盘点，如果发现盘点与实际库存有出入，请在当前库存中进行修改。<br>
             如果确认盘点没有问题，可以点击盘点确认进行结束档次盘点。
             <br>如发现盘点有误，在盘点确认前，可通过点击“初始化至本次盘点前状态”按钮进行恢复，然后按照步骤重新盘点：</td> </tr>
            
             <tr>
             <td> <%if(state==0||state==2){ %>盘点起始日为：<%=startTime%>&nbsp;<input id="btn1" class="btn" value="开始盘点" type="button" />
              <%}else if(state>0){ %>
              盘点起止日为：<%=startTime%>到<%=endTime%><br>
                <input id="btn3" class="btn" value="盘点确认" type="button"/>&nbsp;
              
              <input id="btn2" class="btn" value="初始化至本次结算前状态" type="button" />
            <%} %>
             </td>
             </tr>
 
  </tbody>
</table>
 </div>
 </form>
 
 </div>
 <% Collection coll = (Collection) request.getAttribute("coll");
 if(coll!=null){
   %>
  <div class="result-wrap">
   <!--startprint-->
         <form id="myform1" name="myform1"  method="post">
         
		  <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
		   <input type="hidden" name="weekTag" id="weekTag" value="<%=weekTag%>"/> 
		   <input type="hidden" name="startTime" id="startTime" value="<%=startTime%>"/> 
		   		   <input type="hidden" name="endTime" id="startTime" value="<%=endTime%>"/> 
		  <div id="printContainer">
					<div class="result-content">
						<table  width="100%" 
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
              <td width="5%">单位</td>
               <td width="7%">单价</td>
                 <td width="6%">所属仓库</td>
             <td width="8%">期初库存</td>
              <td width="8%">当期入库</td>
              <td width="8%">当期出库</td>
               <td width="8%">理论库存</td>
              <td width="8%">期末结存</td>
               <td width="8%">实际库存</td>
                <td width="6%">盘点数</td>
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
               <td><input type="text" size="7" name="num<%=ps.getPid()%>" value="<%=ps.getCurNum()%>" 
               onblur="javascript:if(!(/^\d+(\.\d+)?$/.test(this.value))) this.value=<%=ps.getCurNum()%>;"/></td>
               <td>&nbsp; </td>
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
							
                <input id="btn4" class="btn" value="更新盘点表信息" type="button" />&nbsp;
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
