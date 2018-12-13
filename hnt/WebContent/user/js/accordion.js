$(document).ready(function($){
	//Accordin Menu...
	initDo();
}); 

function initDo(){
	$(".accordion div.holder").hide();
	$(".accordion div.holder:first").show();
	$('.accordion li a:first').addClass("active");
	
	$(".accordion li a").click(function(){
		var checkElement = $(this).next();									  
		if((checkElement.is('div.holder')) && (checkElement.is(':visible'))) {
	        return false;
        }
		if((checkElement.is('div.holder')) && (!checkElement.is(':visible'))) {
			 $('.accordion div.holder:visible').slideUp('normal');
			 checkElement.slideDown('normal');
			 $('.accordion li a').removeClass('active');		
	 		 $(this).addClass('active');
			 return false;
		}
    });
}

