<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Person</title>
	<link rel="stylesheet" href="../css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="../css/reset.css">
	<link rel="stylesheet" type="text/css" href="../css/main.css">
	<%
		ServletContext sc = request.getServletContext();
	    int success = (int) sc.getAttribute("success");
		String message=(String)sc.getAttribute("message");
	%>
</head>
<body>
	
	<div class="left-bar">
		<div class="logo">
			<img src="../img/logo.png">
		</div>
		<div class="nav-btn-group">
			<a class="nav-btn" id="nav-btn-active" href="javascript:void(0)">个人资料</a>
			<a class="nav-btn" href="/Desserthouse/ShowCardInfo">账户管理</a>
			<a class="nav-btn" href="/Desserthouse/MemberRecord">账单</a>
		</div>
		<a class="manage-btn" href="/Desserthouse/ShowInventory"> <span>预定商品</span> <img
			src="../img/signout.png">
			<div class="clear"></div>
		</a>
	</div>

	<div class="right-site">
		<div class="tab-btn-group">
			<a class="tab-btn tab-btn-active" href="javascript:void(0)">个人资料</a>
		</div>
		<div class="content">
			<div class="wrapper">
				<div style="height: 1px"></div>
				<form class="info-form">
				<ul>
					<%if(success==0){   
						%><li><span>称呼:</span><input type="text" id="compellation" placeholder="用于派送预定商品"></li>
						<li><span>性别:</span> 男<input type="radio" name="gender" value="1">女<input type="radio" name="gender" value="0" checked="checked"></li>
						<li><span>生日:</span><input type="date" id="birthday" placeholder="生日"></li>
						<li><span>送货地址:</span><input type="text" id="address" placeholder="送货地址"></li>
						<li><span>联系电话:</span><input type="text" id="phone" placeholder="联系方式"></li>
					<%}else{ 
						String compellation = (String) sc.getAttribute("compellation");
						String birthday = (String) sc.getAttribute("birthday");
						int gender = (int) sc.getAttribute("gender");
						String address = (String) sc.getAttribute("address");
						String phone = (String) sc.getAttribute("phone");
						%><li><span>称呼:</span><input type="text" id="compellation" placeholder="<%=compellation%>"></li>
						<%if(gender==1){
						%><li><span>性别:</span> 男<input type="radio" name="gender" value="1" checked="checked">女<input type="radio" name="gender" value="0"></li>
						<%}else { %>
						<li><span>性别:</span> 男<input type="radio" name="gender" value="1">女<input type="radio" name="gender" value="0" checked="checked"></li>
						<%} %>
						<li><span>生日:</span><input type="date" id="birthday" placeholder="<%=birthday%>"></li>
						<li><span>送货地址:</span><input type="text" id="address" placeholder="<%=address%>"></li>
						<li><span>联系电话:</span><input type="text" id="phone" placeholder="<%=phone%>"></li>
					<%} %>
						<li><input class="btn modify-btn" value="修改"></li>
					</ul>
				        <div class="message"></div>
				</form>
			</div>
		</div>
	</div>
	<div class="clear"></div>
	<script src="../js/jquery-2.1.4.min.js"></script>
	<script src="../js/bootstrap.js"></script>
    <script src="../js/memberinfo.js"></script>
</body>
</html>