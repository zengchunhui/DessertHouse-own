/**
 * 预定界面
 */

$(".nav-btn").on("click",function(e){
     var visited_id=$(e.target).attr("id");//取得id
     var store_name=visited_id.split("-")[1];
     var date;

	$(".tab-btn-active").each(function(){//取得日期
		date="2016-"+$(this).html();
		console.log($(this).html());
	}); 
	// alert(store_name);
	// alert(date);
    $("#store-name").val(store_name);
	 $("#date").val(date);
	 $("#store-inventory").submit();
});

//三个表格的切换
//第一个表格
$("#tab-firstday").on("click",function(){
	if ($("#tab-secondday").hasClass("tab-btn-active")) {
		$("#tab-secondday").removeClass("tab-btn-active");
		$("#table-second").hide();
	}

	if ($("#tab-thirdday").hasClass("tab-btn-active")) {
		$("#tab-thirdday").removeClass("tab-btn-active");
		$("#table-third").hide();
	}

	$("#tab-firstday").removeClass();
	$("#tab-firstday").addClass("tab-btn tab-btn-active");
	$("#table-first").show();
});
//第二个表格
$("#tab-secondday").on("click",function(){
	if ($("#tab-firstday").hasClass("tab-btn-active")) {
		$("#tab-firstday").removeClass("tab-btn-active");
		$("#table-first").hide();
	}

	if ($("#tab-thirdday").hasClass("tab-btn-active")) {
		$("#tab-thirdday").removeClass("tab-btn-active");
		$("#table-third").hide();
	}

	$("#tab-secondday").removeClass();
	$("#tab-secondday").addClass("tab-btn tab-btn-active");
	$("#table-second").show();
});
//第三个表格
$("#tab-thirdday").on("click",function(){
	if ($("#tab-secondday").hasClass("tab-btn-active")) {
		$("#tab-secondday").removeClass("tab-btn-active");
		$("#table-second").hide();
	}

	if ($("#tab-firstday").hasClass("tab-btn-active")) {
		$("#tab-firstday").removeClass("tab-btn-active");
		$("#table-first").hide();
	}

	$("#tab-thirdday").removeClass();
	$("#tab-thirdday").addClass("tab-btn tab-btn-active");
	$("#table-third").show();
});

$(".book-btn-td").on("click",function(){//添加预约对象
	$(this).children("img").attr("src","../img/check.png");
	var storeName=visited;
	var send_date="2016-"+$(".tab-btn-active").html();
	var p_name=$(this).parent().siblings(".dessert-name-td").html();
	var p_num=1;
	var left_num=$(this).parent().siblings(".dessert-num-td").html();
	var price=$(this).parent().siblings(".dessert-price-td").html();
	$.ajax({
                type:"POST",
                url:"/Desserthouse/api/AddCart",
                data:{'store_name':storeName,'s_date':send_date,'p_name':p_name,'p_num':p_num,'left_num':left_num,'price':price},
                success:function(result,textStatus){
                    alert(result.message);
                }
            });
});

$("#tool-btn-cart").on("click",function(){
	$("#book-table-cart").empty();
	$("#book-table-cart").append("<tr><th>日期</th><th>店面</th><th>名称</th><th>价格</th><th>剩余数量</th><th>预定数量</th></tr>");
	$.ajax({
                type:"POST",
                url:"/Desserthouse/api/GetCart",
                data:{ },
                success:function(result,textStatus){
//                    alert(result.root);
                    var cart_list=result.cart_list;
                    $.each(cart_list,function(idx,item){
                    	$("#book-table-cart").append("<tr><td class=\"book-sdate-td\">"+item.s_date+"</td><td class=\"book-sname-td\">"+item.store_name+"</td>");
                    	$("#book-table-cart").append("<td class=\"book-pname-td\">"+item.p_name+"</td><td class=\"book-price-td\">"+item.price+"</td><td class=\"book-lnum-td\">"+item.left_num+"</td>");
                    	$("#book-table-cart").append("<td><a class=\"btn round-btn sub-btn\" style=\"line-height: 20px;\" href=\"javascript:void(0)\">-</a><input class=\"num-input\" type=\"text\" value=\""+item.p_num+"\"><a class=\"btn round-btn add-btn\" href=\"javascript:void(0)\">+</a></td></tr>");
                    });
                }
            });
	$(".modal-wrapper").show();
	$("body").css("overflow","hide");
});

$(".sub-btn").on("click",function(){
	 var num=$(this).siblings("input").val();
	 num=num-1;
	 $(this).siblings("input").val(num);
	 var send_date=$(this).parent().siblings(".book-sdate-td").html();
	 var storeName=$(this).parent().siblings(".book-sname-td").html();
	 var p_name=$(this).parent().siblings(".book-pname-td").html();
	 var p_num=num;
	 var left_num=$(this).parent().siblings(".book-lnum-td").html();
	 var price=$(this).parent().siblings(".book-price-td").html();
	 $.ajax({
                type:"POST",
                url:"/Desserthouse/api/ChangeCart",
                data:{'store_name':storeName,'s_date':send_date,'p_name':p_name,'p_num':p_num,'left_num':left_num,'price':price},
                success:function(result,textStatus){
                    alert(result.message);
                }
            });
});

$(".add-btn").on("click",function(){
	 var num=$(this).siblings("input").val();
	 num=num+1;
	 $(this).siblings("input").val(num);
	 var send_date=$(this).parent().siblings(".book-sdate-td").html();
	 var storeName=$(this).parent().siblings(".book-sname-td").html();
	 var p_name=$(this).parent().siblings(".book-pname-td").html();
	 var p_num=num;
	 var left_num=$(this).parent().siblings(".book-lnum-td").html();
	 var price=$(this).parent().siblings(".book-price-td").html();
	 $.ajax({
                type:"POST",
                url:"/Desserthouse/api/ChangeCart",
                data:{'store_name':storeName,'s_date':send_date,'p_name':p_name,'p_num':p_num,'left_num':left_num,'price':price},
                success:function(result,textStatus){
                    alert(result.message);
                }
            });
});

$(".close-btn").on("click",function(){
   $(".modal-wrapper").hide();
   $("body").css("overflow","auto");
});

$(".confirm-btn").on("click",function(){
   $.ajax({
                type:"POST",
                url:"/Desserthouse/api/Reservate",
                data:{ },
                success:function(result,textStatus){
                    alert(result.message);
                    $("#message").html("<div class=\"alert alert-danger alert-dismissable\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>"+result.root.message+"</div>");
                	setTimeout(function (){
	    				 $(".modal-wrapper").hide();
  						 $("body").css("overflow","auto");
					}, 3999);
                }
            });
});