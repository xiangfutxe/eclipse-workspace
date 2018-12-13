$(document).ready(function(){
	$.validator.addMethod("notEqual", function(value, element, param) {
	  return this.optional(element) || value != param;
	}, "Please choose a value!");

	$.validator.addMethod("phoneUS", function(phone_number, element) {
	    phone_number = phone_number.replace(/\s+/g, ""); 
		return this.optional(element) || phone_number.length > 9 &&
			phone_number.match(/^(1-?)?(\([2-9]\d{2}\)|[2-9]\d{2})-?[2-9]\d{2}-?\d{4}$/);
	}, "Please specify a valid phone number");
	
	$("#booknow-form").validate({
		rules:{ 
			fname:{required:true,minlength:3}
			,lname:{required:true,minlength:3}
			,phone:{required:true,phoneUS:true}
			,email:{required:true,email:true}
			,treatment_day:{required:true,notEqual:"Day"}
			,treatment_month:{required:true,notEqual:"Month"}
			,treatment_year:{required:true,notEqual:"Year"}
			,PreferredTime:{required:true,notEqual:"Time"}
			,treatment:{required:true,notEqual:"Please Select a Treatment"}
			,persons:{required:true, number: true}
		}
	});
	
	$("#booknow-form").submit(function (){
		var action = $(this).attr('action');
		if(	$("[name='fname']").is('.valid') && $("[name='lname']").is('.valid') && $("[name='phone']").is('.valid') && $("[name='email']").is('.valid')
		&& $("[name='treatment_day']").is('.valid') && $("[name='treatment_month']").is('.valid') && $("[name='treatment_year']").is('.valid') 
		&& $("[name='PreferredTime']").is('.valid') && $("[name='treatment']").is('.valid') && $("[name='persons']").is('.valid') ){
			
			$("#ajax_message").slideUp(750, function () {
			$('#ajax_message').hide();

			$.post(action, {
				fname	: $("[name='fname']").val(),
				lname	: $("[name='lname']").val(),
				gender	: $("[name='Gender']").val(),
				phone	: $("[name='phone']").val(),
				email	: $("[name='email']").val(),
				address : $("[name='address']").val(),
				treatment_day: $("[name='treatment_day']").val(),
				
				treatment_month: $("[name='treatment_month']").val(),
				treatment_year: $("[name='treatment_year']").val(),
				time: $("[name='PreferredTime']").val(),
				treatment: $("[name='treatment']").val(),
				persons: $("[name='persons']").val(),
				message: $("[name='requests']").val(),
			}, function (data) {
				document.getElementById('ajax_message').innerHTML = data;
				$('#ajax_message').slideDown('slow');
				if (data.match('success') != null) $('#frmcontact').slideUp('slow');
			});
		});
			
		}
		return false;
	});

});
