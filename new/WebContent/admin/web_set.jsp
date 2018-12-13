<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.Admin" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.utils.Pager" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
String userId = (String)request.getAttribute("userId");
if(admin==null) {
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
		if(rankstr[9][1].equals("1")||admin.getState().equals("1")){
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>网站设置</title>
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
      <script type="text/javascript" src="js/jquery.cityselect.js"></script>
  </head>
  
  <body>
  <div class="container clearfix">
   <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.action">首页</a><span class="crumb-step">&gt;</span>系统管理
            <span class="crumb-step">&gt;</span>
            <span><a href="admin/user_add.jsp">网站设置</a></span>
            <span class="crumb-step">&gt;</span><span><a href="javascript:history.go(-1);">返回上一页</a></span>
            </div>
        </div>
        <div class="result-wrap">
         <form  action="admin/web_set_save.action"  id="myform" name="myform" method="post" enctype="multipart/form-data">
          <s:token></s:token>
		   <input type="hidden" id="id"  name="id" value='<s:property value="#request.data.id"/>'/>
            <div class="result-content">
                    <table class="insert-tab" width="97%">
                        <tbody>
                          <tr>
                                <td colspan="2"><h4><i class="require-red">*</i>网站信息：</h4></td>
                                </tr>
                            <tr>
                                <th width="150px"><i class="require-red">*</i>网站开关：</th>
                                <td>
                                    <select id="level1" name="level1" class="common-text required">
										  <option value="" >--请选择--</option>
										  <option value=1 <s:if test='#request.data.level1==1'>selected</s:if>>开通</option>
										   <option value=0 <s:if test='#request.data.level1==0'>selected</s:if>>关闭</option>
										  </select>&nbsp;<span id="level1Tag"></span>
                                </td>
                                
                                </tr>
								  <tr>
                                <td colspan="2"><h4><i class="require-red">*</i>奖励信息：</h4></td>
                                </tr>
                            <tr>
                                <th><i class="require-red">*</i>直推奖：</th>
                                <td>
                                  <input class="common-text required"  type="text" id="level2"  name="level2"  value='<s:property value="#request.data.level2"/>'/>元 &nbsp;<span id="level2Tag"></span>
                                </td>
                                
                                </tr>
								   <tr>
                                <th><i class="require-red">*</i>见点奖：</th>
                                <td>
                                  <input class="common-text required"  type="text" id="level3"  name="level3"  value='<s:property value="#request.data.level3"/>'/>元 &nbsp;<span id="level3Tag"></span>
                                </td>
                                
                                </tr>
								 <tr>
                                <th><i class="require-red">*</i>团购领导奖：</th>
                                <td>
                                   <input class="common-text required"  type="text" id="level4"  name="level4"  value='<s:property value="#request.data.level4"/>'/>元
								   &nbsp;<span id="level4Tag"></span>
                                </td>
                                
                                </tr>
								 <tr>
								 <th><i class="require-red">*</i>组团奖励：</th>
                                <td>
                                   <input class="common-text required"  type="text" id="level5" name="level5"  value='<s:property value="#request.data.level5"/>'/>元
								   &nbsp;<span id="level5Tag"></span>
                                </td>
                                
                                </tr>
								 <tr>
								 <th><i class="require-red">*</i>销售奖励：</th>
                                <td>
                                   <input class="common-text required" type="text" id="level6" name="level6"  value='<s:property value="#request.data.level6"/>'/>元
								   &nbsp;<span id="level6Tag"></span>
                                </td>
                                </tr>
								 <tr>
								 <th><i class="require-red">*</i>达成奖励：</th>
                                <td>
                                   <input class="common-text required" type="text" id="level7" name="level7"  value='<s:property value="#request.data.level7"/>'/>元
								   &nbsp;<span id="level7Tag"></span>
                                </td>
                                
                                </tr>
								<tr>
                                <td colspan="2"><h4><i class="require-red">*</i>费率信息：</h4></td>
                                </tr>
                          <tr>
                                <th><i class="require-red">*</i>回馈提取：</th>
                                <td>
                                   <input class="common-text required" type="text" id="level8" name="level8" value='<s:property value="#request.data.level8*100"/>'/>%&nbsp;<span id="level8Tag"></span>
                                </td>
								 </tr>
								   <tr>
                                <th><i class="require-red">*</i>提现手续费：</th>
                                <td>
                                    <input class="common-text required" type="text" id="level9"  name="level9" value='<s:property value="#request.data.level9*100"/>'/>%&nbsp;<span id="level9Tag"></span>
                                </td>
                                
                                </tr>
								 <tr>
								 <tr>
                                <th><i class="require-red">*</i>个人扣税：</th>
                                <td>
                                    <input class="common-text required" type="text" id="level10" name="level10" value='<s:property value="#request.data.level10*100"/>'/>%&nbsp;<span id="level10Tag"></span>
                                </td>
                                </tr>
								  <tr>
                       <tr>
                                <th></th>
                                <td>
                                  <input id="btn1" class="btn btn-primary" value="保存" type="button">
                                </td>
                            </tr>
                             </tbody>
                            </table>
            </div>
               </form>
        </div>

    </div>
    <!--/main-->
    </div>
  </body>
   <script type="text/javascript" src="js/custom.js"></script>
 <script type="text/javascript">
	$(function() {
   var reg = /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/;
   
   $("#level1").blur(function() {
		if($("#level1").val()=="")
		$("#level1Tag").text("未标示网站开关标志！").css({"color":"red","font-size":"9px"}); 
		else $("#level1Tag").text("");
	});	
 
	$("#level2").blur(function() {
		if($("#level2").val()==""){
		$("#level2Tag").text("参数值不能为空！").css({"color":"red","font-size":"9px"}); 
		}else if(!reg.test($("#level2").val())) $("#level2Tag").text("参数值格式有误！").css({"color":"red","font-size":"9px"}); 
		else $("#level2Tag").text("");
	});	
	$("#level3").blur(function() {
		if($("#level3").val()==""){
		$("#level3Tag").text("参数值不能为空！").css({"color":"red","font-size":"9px"}); 
		}else if(!reg.test($("#level3").val())) $("#level3Tag").text("参数值格式有误！").css({"color":"red","font-size":"9px"}); 
		else $("#level3Tag").text("");
	});	
	$("#level4").blur(function() {
		if($("#level4").val()==""){
		$("#level4Tag").text("参数值不能为空！").css({"color":"red","font-size":"9px"}); 
		}else if(!reg.test($("#level4").val())) $("#level4Tag").text("参数值格式有误！").css({"color":"red","font-size":"9px"}); 
		else $("#level4Tag").text("");
	});	
	$("#level5").blur(function() {
		if($("#level5").val()==""){
		$("#level5Tag").text("参数值不能为空！").css({"color":"red","font-size":"9px"}); 
	}else if(!reg.test($("#level5").val())) $("#level5Tag").text("参数值格式有误！").css({"color":"red","font-size":"9px"}); 
	else $("#level5Tag").text("");
	});	
	$("#level6").blur(function() {
		if($("#level6").val()==""){
		$("#level6Tag").text("参数值不能为空！").css({"color":"red","font-size":"9px"}); 
	}else if(!reg.test($("#level6").val())) $("#level6Tag").text("参数值格式有误！").css({"color":"red","font-size":"9px"}); 
	else $("#level6Tag").text("");
	});	
	$("#level7").blur(function() {
		if($("#level7").val()==""){
		$("#level6Tag").text("参数值不能为空！").css({"color":"red","font-size":"9px"}); 
	}else if(!reg.test($("#level7").val())) $("#level7Tag").text("参数值格式有误！").css({"color":"red","font-size":"9px"}); 
	else $("#level7Tag").text("");
	});	
	$("#level8").blur(function() {
		if($("#level8").val()==""){
		$("#level8Tag").text("参数值不能为空！").css({"color":"red","font-size":"9px"}); 
	}else if(!reg.test($("#level8").val())) $("#level8Tag").text("参数值格式有误！").css({"color":"red","font-size":"9px"}); 
	else $("#level8Tag").text("");
	});	
	$("#level9").blur(function() {
		if($("#level9").val()==""){
		$("#level9Tag").text("参数值不能为空！").css({"color":"red","font-size":"9px"}); 
	}else if(!reg.test($("#level9").val())) $("#level9Tag").text("参数值格式有误！").css({"color":"red","font-size":"9px"}); 
	else $("#level9Tag").text("");
	});	
	
	$("#level10").blur(function() {
		if($("#level10").val()==""){
		$("#level10Tag").text("参数值不能为空！").css({"color":"red","font-size":"9px"}); 
	}else if(!reg.test($("#level10").val())) $("#level10Tag").text("参数值格式有误！").css({"color":"red","font-size":"9px"}); 
	else $("#level10Tag").text("");
	});	
	
  	 
  	  $("#btn1").click(function(){
  	  if (!confirm("确认提交修改网站参数信息？")) {
				window.event.returnValue = false;
			}else{
		  	 var errorstr = "";
		  	
	   	if($("#level1").val()==""){
			$("#level1Tag").text("未选择网站开关标示！").css({"color":"red","font-size":"9px"}); 
			errorstr ="1";
		}else if($("#level1Tag").text()!=""){
			errorstr ="1";
		}
		
		if($("#level2").val()==""){
			$("#level2Tag").text("参数值不能为空！").css({"color":"red","font-size":"9px"}); 
			errorstr ="1";
		}else if($("#level2Tag").text()!=""){
			errorstr ="1";
		}
		if($("#level3").val()==""){
			$("#level3Tag").text("参数值不能为空！").css({"color":"red","font-size":"9px"}); 
			errorstr ="1";
		}else if($("#level3Tag").text()!=""){
			errorstr ="1";
		}
		
		if($("#level4").val()==""){
			$("#level4Tag").text("参数值不能为空！").css({"color":"red","font-size":"9px"}); 
			errorstr ="1";
		}else if($("#level4Tag").text()!=""){
			errorstr ="1";
		}
		if($("#level5").val()==""){
			$("#level5Tag").text("参数值不能为空！").css({"color":"red","font-size":"9px"}); 
			errorstr ="1";
		}else if($("#level5Tag").text()!=""){
			errorstr ="1";
		}
		
		if($("#level6").val()==""){
			$("#level6Tag").text("参数值不能为空！").css({"color":"red","font-size":"9px"}); 
			errorstr ="1";
		}else if($("#level6Tag").text()!=""){
			errorstr ="1";
		}
		if($("#level7").val()==""){
			$("#level7Tag").text("参数值不能为空！").css({"color":"red","font-size":"9px"}); 
			errorstr ="1";
		}else if($("#level7Tag").text()!=""){
			errorstr ="1";
		}
		
		if($("#level8").val()==""){
			$("#level8Tag").text("参数值不能为空！").css({"color":"red","font-size":"9px"}); 
			errorstr ="1";
		}else if($("#level8Tag").text()!=""){
			errorstr ="1";
		}
		
		if($("#level9").val()==""){
			$("#level9Tag").text("参数值不能为空！").css({"color":"red","font-size":"9px"}); 
			errorstr ="1";
		}else if($("#level9Tag").text()!=""){
			errorstr ="1";
		}
		
		if($("#level10").val()==""){
			$("#level10Tag").text("参数值不能为空！").css({"color":"red","font-size":"9px"}); 
			errorstr ="1";
		}else if($("#level10Tag").text()!=""){
			errorstr ="1";
		}
				if(errorstr!=""){
					window.event.returnValue = false;
				}else{
		       		 $("#myform").submit();
		       		 window.event.returnValue = true;
				
		}
		 }   
	   });
   
   
        });
    
        
   </script>
</html>

<%
}else{
			out.println("<script>");
			out.println("alert('你没有权限进行该操作！')");
			out.println("</script>");
		}
}
%>