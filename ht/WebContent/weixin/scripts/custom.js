// JavaScript Document

$(document).ready(function(){
	
	$('.go-menu').click(function(){
		$(this).fadeOut(200);
		$('.go-back').fadeIn(200);
		$('.navigation').fadeIn(200);
		return false;
	});
	
	$('.go-back').click(function(){
		$(this).fadeOut(200);
		$('.go-menu').fadeIn(200);
		$('.navigation').fadeOut(200);
		return false;
	});
	
	$('.has-submenu').click(function(){
		$(this).parent().find('.dropdown-menu').toggleClass('dropup-menu');
		$(this).parent().find('.submenu').toggle(150);
		return false;
	});
	
	$('.wide-image a').click(function(){
		$(this).parent().parent().find('.wide-active').toggle(100);
	});
	

	
	$('.shortcut-search').click(function(){
		$('.sidebar-shortcuts').hide();
		$('.sidebar-search').show();
	});
	
	$('.search-close').click(function(){
		$('.sidebar-search').hide();
		$('.sidebar-shortcuts').show();
	});

	$('.open-nav').click(function(){
		//$(this).toggleClass('remove-sidebar');
		if( snapper.state().state=="left" ){
			snapper.close();
		} else {
			snapper.open('left');
		}
		return false;
	});

	
	$('.wide-image').click(function(){
		$(this).parent().find('.wide-item-content').toggle(50);
		return false;
	});
	
	
});