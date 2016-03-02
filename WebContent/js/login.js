$(document).ready(function (){
	// $('.welcome-macarons').css("display","none");
	// $('.welcome p').css("display","none");

	// setTimeout(function() {
	// 	$('.welcome-macarons').fadeIn(100);
	// },10);
	// setTimeout(function() {
	// 	$('.welcome p').fadeIn(100);
	// },11);

	setTimeout(function ()
	{
	    $('.top-part').animate(
	    {
	        top: "-=65%"
	    }, 1000,function() {
	    	$('.welcome').remove();
	    });

	}, 2000);
	setTimeout(function ()
	{
	    $('.bottom-part').animate(
	    {
	        top: "+=35%"
	    }, 1000);

	}, 1999);
});