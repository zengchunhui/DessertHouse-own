<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="dessert.rvo.store.StoreRVO"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Book</title>
<link rel="stylesheet" href="../css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="../css/reset.css">
<link rel="stylesheet" type="text/css" href="../css/main.css">
<%
	ServletContext sc = request.getServletContext();
	List<StoreRVO> sotreList = (List<StoreRVO>) sc.getAttribute("store_list");
	
%>
</head>
<body>
	<div class="left-bar">
		<div class="logo">
			<img src="../img/logo.png">
		</div>
		<div class="nav-btn-group">
			<a class="nav-btn nav-btn-active" href="javascript:void(0)">店面管理</a>
			<a class="nav-btn" href="/Desserthouse/ManageEmployee">店员管理</a>
		</div>

		<a class="manage-btn" href="javascript:void(0)"> <span>登出</span> <img
			src="../img/signout.png">
			<div class="clear"></div>
		</a>
	</div>
	<div class="right-site">
		<div class="tab-btn-group">
			<a class="tab-btn tab-btn-active" href="javascript:void(0)">所有分店</a> 
		</div>
		<div class="content">
			<div class="wrapper">
				<div class="tool-bar">
					<a class="btn tool-btn" id="tool-btn-store" href="javascript:void(0)">添加分店</a>
					<div class="clear"></div>
				</div>
				<%
					if (sotreList.size() <= 0) {
				%>
				<form class="info-form">
					<ul>
						<li><span>当前无任何分店</span></li>
					</ul>
				</form>
				<%
					} else {
				%>
				<table class="book-table" id="store-table" border="1">
					<tr>
						<th>编号</th>
						<th>店名</th>
						<th>地址</th>
						<th>联系电话</th>
						<th>修改</th>
						<th>删除</th>
					</tr>
					<%
						for (int i = 0; i < sotreList.size(); i++) {
					%>
					<tr>
						<td><%=sotreList.get(i).getId()%></td>
						<td><input type="text" class="short-input-td" id="<%=sotreList.get(i).getId() + "-name"%>" value="<%=sotreList.get(i).getName() %>"></td>
						<td><input type="text" class="long-input-td" id="<%=sotreList.get(i).getId() + "-addr"%>" value="<%=sotreList.get(i).getAddress() %>"></td>
						<td><input type="text" class="long-input-td" id="<%=sotreList.get(i).getId() + "-tel"%>" value="<%=sotreList.get(i).getTelphone() %>"></td>
					    <td><a class="store-btn-edit" id="<%=sotreList.get(i).getId() + "-edit"%>"><img
								src="../img/edit.png"></a></td>
						<td><a class="store-btn-delete" id="<%=sotreList.get(i).getId()  + "-delete"%>"><img
								src="../img/delete.png"></a></td>
					</tr>
					<%
						    }
						}
					%>
				</table>
				<div class="message"></div>
			</div>
		</div>
	</div>
	<div class="clear"></div>
	<div class="modal-wrapper" style="display: none;">
		<div class="dialog cart-dialog">
			<div class="dialog-title">分店</div>
			<a class="close-btn" href="javascript:void(0)"><b></b></a>
			<form class="info-form">
			    <ul>
						<li><input type="text" id="name" placeholder="店名"></li>
						<li><input type="text" id="addr" placeholder="地址"></li>
						<li><input type="text" id="tel" placeholder="联系方式"></li>
			    </ul>
			</form>
			<a class="btn confirm-btn" href="javascript:void(0)">添加</a>
			<div class="form-message"></div>
			<div class="clear"></div>
		</div>
	</div>
	<script src="../js/jquery-2.1.4.min.js"></script>
	<script src="../js/bootstrap.js"></script>
	<script src="../js/storeManage.js"></script>
</body>
</html>