$(document).on("click",".plan-btn-pass",function(){
	var button_id=$(this).attr("id");
	var p_id=button_id.split("-")[0];
	$.ajax({
        type:"POST",
        url:"/Desserthouse/api/PassPlan",
        data:{'p_id':p_id},
        success:function(result,textStatus){
            	var isSuccess=result.success;
            	if(isSuccess){
            		$("#"+button_id).children("img").attr("src","../img/check.png");
            		$("#"+button_id).attr("disable","true");
            	}else{
            		alert(result.message);
            	}
        }
    });
});

$("#sale-record").click(function(){
	var month=2;
	var id=1;
	
	$("#input-month-s").val(month);
	$("#input-sid-s").val(id);
	$("#form-record").submit();
});

$("#member-record").click(function(){
	var month=2;
	
	$("#input-month-m").val(month);
	$("#form-statistics").submit();
});