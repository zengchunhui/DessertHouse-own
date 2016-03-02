/**
 * 个人信息界面
 */
$(".modify-btn").click(function(){
	var compellation=$("#compellation").val();
	var gender=$('input:radio[name="gender"]:checked').val();
	var birthday=$("#birthday").val();
	var address=$("#address").val();
	var phone=$("#phone").val();
	if (compellation=="") {
		$(".message").html("<div class=\"alert alert-danger alert-dismissable\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>请输入称呼</div>");
		return;
	}
	if (gender=="") {
		$(".message").html("<div class=\"alert alert-danger alert-dismissable\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>请选择性别</div>");
		return;
	}
	if (birthday=="") {
		$(".message").html("<div class=\"alert alert-danger alert-dismissable\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>请选择生日</div>");
		return;
	}
	if (address=="") {
		$(".message").html("<div class=\"alert alert-danger alert-dismissable\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>请输入地址</div>");
		return;
	}
	if (phone=="") {
		$(".message").html("<div class=\"alert alert-danger alert-dismissable\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>请输入联系电话</div>");
		return;
	}
	var temp=birthday.split("/")
	birthday=temp[0]+"-"+temp[1]+"-"+temp[2];
	alert(compellation+" "+gender);
	$.ajax({
                type:"POST",
                url:"/Desserthouse/api/ChangeInfo",
                data:{'compellation':compellation,'gender':gender,'birthday':birthday,'address':address,'phone':phone},
                success:function(result,textStatus){
                   alert(result.message);
                }
            });
});