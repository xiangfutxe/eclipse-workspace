$(document).ready(function(){

	$.validator.addMethod("notEqual", function(value, element, param) {
	  return this.optional(element) || value != param;
	}, "Please choose a value!");

	
	$("#frmcontact").validate({ 
	   	onfocusout: function(element) {	
			$(element).valid();
		},		   
	    rules:{ 
	      name  	: {required: true,minlength: 3,notEqual:'Name'},
	  	  email 	: {required: true,email: true,notEqual:'Email'},		  
		  message	: {required: true,minlength: 10,notEqual:'Message'}
        	}
	});
	
	$('#frmcontact').submit(function () {
	if($('#name').is('.valid') && $('#email').is('.valid') && $('#message').is('.valid')) {
		
		var action = $(this).attr('action');

		$('#frmcontact #send').attr('disabled', 'disabled').after('');

		$("#ajax_message").slideUp(750, function () {
			$('#ajax_message').hide();

			$.post(action, {
				name: $('#name').val(),
				email: $('#email').val(),
				message: $('#message').val()
			}, function (data) {
				document.getElementById('ajax_message').innerHTML = data;
				$('#ajax_message').slideDown('slow');
				$('#frmcontact #send').attr('disabled', '');
				if (data.match('success') != null) $('#frmcontact').slideUp('slow');
			});
		});
	  }
      return false;		
    });
});