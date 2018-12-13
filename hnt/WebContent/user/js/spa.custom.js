WebFontConfig = { google: { families: [ 'Niconne', 'Norican', 'Oswald::latin' ] } };
function mainmenu(){
	$("ul.menu ul").css({display: "none"}); // Opera Fix
	$("ul.menu li").hover(function(){
		$(this).find('ul:first').css({visibility: "visible",display: "none"}).show(200);
	},function(){
		$(this).find('ul:first').css({visibility: "hidden"});
	});
}


$(document).ready(function(){

	var wf = document.createElement('script');
	wf.src = ('https:' == document.location.protocol ? 'https' : 'http') +
	'://ajax.googleapis.com/ajax/libs/webfont/1/webfont.js';
	wf.type = 'text/javascript';
	wf.async = 'true';
	var s = document.getElementsByTagName('script')[0];
	s.parentNode.insertBefore(wf, s);
	
	mainmenu(); //Menu
	
	//Homepage Nivoslider
	if($('#slider').length){
		$('#slider').nivoSlider();
	}
	
	if($("ul.cat-menu").length){
		
		var t_container = $("ul.cat-menu"),
            t_items = t_container.find("li"),
            t_anchor = t_items.find("a");
       		t_anchor.click(function(){
            	t_items.find('.active').removeClass('active');
                $(this).find("span.arrow-down").addClass("active");        
       		});
	   
		//For Top slider menu at blog page and single post page
		animatedcollapse.init();	  
		//For Top slider menu at blog page and single post page
		animatedcollapse.addDiv('categories', 'fade=0,speed=400,group=srv,hide=1')
		animatedcollapse.addDiv('archives', 'fade=0,speed=400,group=srv,hide=1')
		animatedcollapse.ontoggle=function($, divobj, state){ //fires each time a DIV is expanded/contracted
			//$: Access to jQuery
			//divobj: DOM reference to DIV being expanded/ collapsed. Use "divobj.id" to get its ID
			//state: "block" or "none", depending on state
		}
		
	}


	//Gallery page isotope
	var $container = $('.gallery-container');
	if($container.length){
		$($container).find("> div").each(function(){
				$(this).addClass("column no-margin all-sort");
				$(this).find(".gallery-image img").after('<span class="image-overlay"> <span class="image-overlay-inside"> </span> </span>');
		});
		
		$container.isotope({
			filter: '*',
			animationOptions: { duration: 750, easing: 'linear', queue: false  }
		});
		
		if($("div#sorting-container").length){
			$("div#sorting-container a").click(function(){
				$("div#sorting-container a").removeClass("active_sort");
				var selector = $(this).attr('data-filter');
				$(this).addClass("active_sort");
				$container.isotope({ filter: selector, animationOptions: { duration: 750, easing: 'linear',  queue: false }});
				return false;
			});		
		}
	}
	
	//Testimonial Carousel
	if($(".testimonial-carousel").length){
		$('ul.testimonial-carousel').jcarousel({ scroll: 1 });
	}
	
	//Related Post Carousel
	if($("#mycarousel").length){
	
	$ul = $("#mycarousel ul:first ");
	$li = $("#mycarousel ul:first li");
	
	function mycarousel_initCallback(carousel) {
		
		$('.jcarousel-control a:first').addClass("active");
		$($ul).find("li:first").addClass("active");
		$("a#mycarousel-prev").addClass("disabled");
		
		
		$('.jcarousel-control a').bind('click', function() {
			
			//To add & remove active class to ELEMENTS
			$($li).removeClass("active");
			$($li).eq($(this).index()).addClass("active");
			
			//TO add & remove active class to current page element
			$(".jcarousel-control a").removeClass("active");
			$(this).addClass("active");
			
			//To add & remove disabled class to PREV PAGINTION
			if( $(this).index() == 0){
			 $("a#mycarousel-prev").addClass("disabled");
			}else{
			 $("a#mycarousel-prev").removeClass("disabled");
			}
			
			//To add & remove disabled class to NEXT PAGINTION
			if($(this).next("a").length==0){
			 $("a#mycarousel-next").addClass("disabled");
			}else{
			 $("a#mycarousel-next").removeClass("disabled");
			}
			
			carousel.scroll($.jcarousel.intval($(this).text()));
			return false;
		});
	
		$('.jcarousel-scroll select').bind('change', function() {
			carousel.options.scroll = $.jcarousel.intval(this.options[this.selectedIndex].value);
			return false;
		});
	
		$('#mycarousel-next').bind('click', function() {
			
			//C
			$current =  $('.jcarousel-control a.active');
			$($current).removeClass("active");
			if($($current).next("a").length){
				$($current).next("a").addClass("active");
			}else{
				$('.jcarousel-control a:last').addClass("active");
			}
			
	
			//To add & remove active class to ELEMENTS
			$($li).removeClass("active");
			$($li).eq($('.jcarousel-control a.active').index()).addClass("active");
			//END
			
			
			if($('.jcarousel-control a.active').next("a").length == 0){
				$(this).addClass("disabled");
			}
			$("a#mycarousel-prev").removeClass("disabled");
			//C - END 
			
			if(!$(this).hasClass("disabled")){
				carousel.next();
			}
			return false;
		});
	
		$('#mycarousel-prev').bind('click', function() {
			//C
			$current =  $('.jcarousel-control a.active');
	
			
			if($($current).index() != 0) {
				$($current).removeClass("active");
				$($current).prev("a").addClass("active");
			}
			
			//To add & remove active class to ELEMENTS
			$($li).removeClass("active");
			$($li).eq($('.jcarousel-control a.active').index()).addClass("active");
			//END
	
	
			
			if($('.jcarousel-control a.active').prev("a").length == 0){
				$(this).addClass("disabled");
			}
	
			
			$("a#mycarousel-next").removeClass("disabled");
			//C-End
			
			if(!$(this).hasClass("disabled")){
				carousel.prev();
			}
			
			return false;
		});
	}
	
	//Related Post
	$("#mycarousel").jcarousel({
	   scroll: 1,
	   initCallback: mycarousel_initCallback,
	   buttonNextHTML: null,
	   buttonPrevHTML: null
	});
	}	
	//End Related Post carousel
	
	//Shortcodes
		//Tab
		if($(".tabs").length)
			$(".tabs").organicTabs({"speed": 200});
		
		//Tooltip
		if($(".tooltip-bottom").length){
			$(".tooltip-bottom").each(function(){
				$(this).tipTip({maxWidth: "auto"});
			});
		}

		if($(".tooltip-top").length){		
			$(".tooltip-top").each(function(){
				$(this).tipTip({maxWidth: "auto",defaultPosition: "top"});
			});
		}
		if($(".tooltip-left").length){
			$(".tooltip-left").each(function(){
				$(this).tipTip({maxWidth: "auto",defaultPosition: "left"});
			});
		}
		
		if($(".tooltip-right").length){
			$(".tooltip-right").each(function(){
				$(this).tipTip({maxWidth: "auto",defaultPosition: "right"});	
			});
		}
	//Shortcodes END	
	

	
	//Footer Sociallinks
	$(".social-widget img").hover(function() {
		$(this).stop().animate({"opacity": "0"}, "slow");
	},function() {
		$(this).stop().animate({"opacity": "1"}, "slow");
	});	 

	
});

