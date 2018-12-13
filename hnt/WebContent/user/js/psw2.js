$(function() {
	if ($("#psw2").val() == $("#psw1").val()){
		$("#content-psw").hide();
		$("#content-all").show();
	}else{
		$("#content-all").hide();
		$("#content-psw").show();
	}
	
	$("#psw2").blur(function() {
	$.ajax({
                  type: "POST",
 
                  url: "UserServlet?method=getMD5",
 
                  data: {psw2:$("#psw2").val()},
 
                  dataType: "json",
 
                  success: function(json){
 					var psw2= json.psw2;
                    if (psw2 == $("#psw1").val()){
				$("#content-all").show();
	    		$("#content-psw").hide();
	    		$("#psw2Tag").text("");
	    		$.session.set('psw2', 'psw2');
			}else{
				$("#content-all").hide();
	    		$("#content-psw").show();
	    		$("#psw2Tag").text("请输入正确的支付密码。").css({"color" : "red","font-size" : "12px"});
			}
			},
 
                  error:function(){
 
                     $("#psw2Tag").text("连接超时。").css({"color" : "red",
					"font-size" : "12px"});
					}
 
			});
		});
});