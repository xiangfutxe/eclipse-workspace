<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>demo</title>
</head>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" >

	function get_mobile_code(){
        $.post('sms.jsp', {mobile:jQuery.trim($('#mobile').val())}, function(msg) {
			if($.trim(msg)!='0'){
				 alert("验证码发送成功，请查收！");
				 $("#code").attr("value",$.trim(msg));
				 alert("code:"+$("#code").val());
				RemainTime();
			}else{
				alert("验证码发送失败，请核对手机号码！");
			}
        });
	};
	var iTime = 59;
	var Account;
	function RemainTime(){
		document.getElementById('zphone').disabled = true;
		var iSecond,sSecond="",sTime="";
		if (iTime >= 0){
			iSecond = parseInt(iTime%60);
			iMinute = parseInt(iTime/60)
			if (iSecond >= 0){
				if(iMinute>0){
					sSecond = iMinute + "分" + iSecond + "秒";
				}else{
					sSecond = iSecond + "秒";
				}
			}
			sTime=sSecond;
			if(iTime==0){
				clearTimeout(Account);
				sTime='获取手机验证码';
				iTime = 59;
				document.getElementById('zphone').disabled = false;
			}else{
				Account = setTimeout("RemainTime()",1000);
				iTime=iTime-1;
			}
		}else{
			sTime='没有倒计时';
		}
		document.getElementById('zphone').value = sTime;
	}
	
	function register(){
	if($("#code").val()!=0){
	alert($("#mobile_code").val());
	alert($("#code").val());
		if($("#code").val()==$("#mobile_code").val()){
			alert("验证成功！验证码："+$("#code").val());
		}else{
			alert("验证码输入有误！");
		}
	}else{
			alert("未发送验证码！");
		}
	}
</script>
<body>
<form action="reg.jsp" method="post" name="formUser">
	<table width="100%"  border="0" align="left" cellpadding="5" cellspacing="3">
		<tr>
			<td align="right">手机<td>
		<input id="mobile" name="mobile" type="text" size="25" class="inputBg" /><span style="color:#FF0000"> *</span> 
        <input id="zphone" type="button" value="发送手机验证码 " onClick="get_mobile_code();"></td>
        </tr>
		<tr>
			<td align="right">验证码</td>
			<td><input type="text" size="8" name="mobile_code" id="mobile_code" class="inputBg" />
			<input type="hidden" name="code" id="code" value="0"/>
			</td>
		</tr>
		<tr>
			<td colspan="2" >
			<input type="button"  value="提交" onclick="return register();"/>
			</td>
		</tr>
	</table>
</form>
</body>
</html>