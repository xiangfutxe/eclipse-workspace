var $ = jQuery.noConflict(); 
var formSubmitted = 'false';

jQuery(document).ready(function($) {	

	$('#formSuccessMessageWrap').hide(0);
	$('.formValidationError').fadeOut(0);
	
	// fields focus function starts
	$('input[type="text"], input[type="password"], textarea').focus(function(){
		if($(this).val() == $(this).attr('data-dummy')){
			$(this).val('');
		};	
	});
	// fields focus function ends
		
	// fields blur function starts
	$('input, textarea').blur(function(){
    	if($(this).val() == ''){		    
			$(this).val($(this).attr('data-dummy'));				
		};			
	});
	// fields blur function ends
		
	// submit form data starts	   
    function submitData(currentForm, formType){     
		formSubmitted = 'true';		
		var formInput = $('#' + currentForm).serialize();		
		$.post($('#' + currentForm).attr('action'),formInput, function(data){			
			$('#' + currentForm).hide();
			$('#formSuccessMessageWrap').fadeIn(500);			
		});

	};
	// submit form data function starts	
	// validate form function starts
	function validateForm(currentForm, formType){		
		// hide any error messages starts
		var error = 0;
	    $('.formValidationError').hide();
		$('.fieldHasError').removeClass('fieldHasError');
	    // hide any error messages ends	
		$('#' + currentForm + ' .requiredField').each(function(i){	
			if($(this).val() == '' || $(this).val() == $(this).attr('data-dummy')){				
				$(this).val($(this).attr('data-dummy'));	
				$(this).focus();
				$(this).addClass('fieldHasError');
				$('#' + $(this).attr('id') + 'Error').fadeIn(300);
				error++;
				return false;
			}
			
			 if($(this).hasClass('requiredAmountField')){				  
				var reg = /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/;
				var tempField = '#' + $(this).attr('id');				
				if(!reg.test($(tempField).val())) {
					$(tempField).focus();
					$(tempField).addClass('fieldHasError');
					$(tempField + 'Error2').fadeIn(300);
					error++;	
					return false;
				};			
			};	
		});	
			if(error>0){
				return false;
			}else{
				$("#myForm").submit();
				return true;
			}		  
   		
	};
	// validate form function ends	
	
	// contact button function starts
	$('#submitButton').click(function() {	
		if (!confirm("确认提交操作？")) {
			window.event.returnValue = false;
		}else{
		validateForm($(this).attr('data-formId'));	
		window.event.returnValue = false;	
		}
	});
	// contact button function ends
	
});
/*////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/
/*//////////////////// Document Ready Function Ends                                                                       */
/*////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/
