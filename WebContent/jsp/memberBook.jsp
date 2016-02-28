<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Book</title>
	<link rel="stylesheet" type="text/css" href="../css/reset.css">
	<link rel="stylesheet" type="text/css" href="../css/main.css">
</head>
<body>
	<div class="left-bar">
		<div class="logo">
			<img src="../img/logo.png">
		</div>
		<div class="nav-btn-group">
			<a class="nav-btn" href="javascript:void(0)">仙林分店</a>
			<a class="nav-btn" href="javascript:void(0)">上海路分店</a>
			<a class="nav-btn" href="javascript:void(0)">广州路分店</a>
			<a class="nav-btn" href="javascript:void(0)">白下分店</a>
			<a class="nav-btn" href="javascript:void(0)">仙林分店</a>
			<a class="nav-btn" href="javascript:void(0)">仙林分店</a>
			<a class="nav-btn" href="javascript:void(0)">仙林分店</a>
		</div>

		<a class="manage-btn" href="javascript:void">
			<span>个人管理</span>
			<img src="../img/signout.png">
			<div class="clear"></div>
		</a>
	</div>

	<div class="right-site">
		<div class="tab-btn-group">
			<a class="tab-btn" href="javascript:void(0)">今天</a>
			<a class="tab-btn tab-btn-active" href="javascript:void(0)">明天</a>
			<a class="tab-btn" href="javascript:void(0)">后天</a>
		</div>
		<div class="content">
			<div class="wrapper">
				<div class="tool-bar">
					<a class="btn tool-btn" href="javascript:void(0)">我的预订单</a>
					<div class="clear"></div>
				</div>
				<table class="book-table" border="1">
					<tr>
						<th>名称</th>
						<th>价格</th>
						<th>剩余数量</th>
						<th>预定</th>
					</tr>
					<tr>
						<td>马卡龙</td>
						<td>100</td>
						<td>40</td>
						<td><img src="../img/check transparent.png"></td>
					</tr>
				</table>
			</div>
		</div>


	</div>
	<div class="clear"></div>
</body>
</html>