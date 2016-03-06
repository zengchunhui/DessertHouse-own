<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="dessert.rvo.employee.EmploeeInfoResultVO"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Manage</title>
<link rel="stylesheet" href="../css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="../css/reset.css">
<link rel="stylesheet" type="text/css" href="../css/main.css">
<%
	ServletContext sc = request.getServletContext();
Map<Integer, String> sotreList = (Map<Integer, String>) sc.getAttribute("store_list");
	List<EmploeeInfoResultVO> headList = (List<EmploeeInfoResultVO>) sc.getAttribute("head_server");
	List<EmploeeInfoResultVO> serverList = (List<EmploeeInfoResultVO>) sc.getAttribute("server");
%>
</head>
<body>
	<div class="left-bar">
		<div class="logo">
			<img src="../img/logo.png">
		</div>
		<div class="nav-btn-group">
			<a class="nav-btn" href="/Desserthouse/ManageStore">店面管理</a>
			<a class="nav-btn nav-btn-active" href="javascript:void(0)">店员管理</a>
		</div>

		<a class="manage-btn" href="javascript:void(0)"> <span>登出</span> <img
			src="../img/signout.png">
			<div class="clear"></div>
		</a>
	</div>
	<div class="right-site">
		<div class="tab-btn-group">
			<a class="tab-btn tab-btn-active" href="javascript:void(0)">服务员管理</a> 
		</div>
		<div class="content">
			<div class="wrapper">
				<div class="tool-bar">
					<a class="btn tool-btn" id="tool-btn-employee" href="javascript:void(0)">添加服务员</a>
					<div class="clear"></div>
				</div>
				<%
					if ((headList.size()+serverList.size()) <= 0) {
				%>
				<form class="info-form">
					<ul>
						<li><span>当前无任何服务员</span></li>
					</ul>
				</form>
				<%
					} else {
				%>
				<table class="book-table" id="store-table" border="1">
					<tr>
						<th>用户名</th>
						<th>所属分店</th>
						<th>工作类型</th>
						<th>修改</th>
						<th>删除</th>
					</tr>
					<%
						for (int i = 0; i < headList.size(); i++) {
					%>
					<tr>
						<td class="employee-name-td"><%=headList.get(i).getName()%></td>
						<td class="employee-store-td">总</td>
						<td class="employee-work-td"><%=headList.get(i).getTypeString()%></td>
						<td><a class="employee-btn-edit" id="<%=headList.get(i).getName() + "-edit"%>"><img
								src="../img/edit.png"></a></td>
						<td><a class="employee-btn-delete" id="<%=headList.get(i).getName()  + "-delete"%>"><img
								src="../img/delete.png"></a></td>
					</tr>
					<%
						    }
						}
					%>
					
					<%
						for (int i = 0; i < serverList.size(); i++) {
					%>
					<tr>
						<td><%=serverList.get(i).getName()%></td>
						<td><%=sotreList.get(serverList.get(i).getS_id()) %></td>
						<td><%=serverList.get(i).getTypeString()%></td>
						<td><a class="employee-btn-edit" id="<%=serverList.get(i).getName() + "-edit"%>"><img
								src="../img/edit.png"></a></td>
						<td><a class="employee-btn-delete" id="<%=serverList.get(i).getName()  + "-delete"%>"><img
								src="../img/delete.png"></a></td>
					</tr>
					<%
						    }
					%>
				</table>
				<div class="message"></div>
			</div>
		</div>
	</div>
	<div class="clear"></div>
	<div class="modal-wrapper" id="employee-edit" style="display: none;">
		<div class="dialog cart-dialog">
			<div class="dialog-title">修改</div>
			<a class="close-btn" href="javascript:void(0)"><b></b></a>
			<form class="info-form">
			    <ul>
						<li><span id="edit-name"></span></li>
						<li><select id="edit-s-id">
						<option value="0">无</option>
						<%for(Map.Entry<Integer,String> entry:sotreList.entrySet()){ %>
							<option value="<%=entry.getKey()%>"><%=entry.getValue()%></option>
						<%} %>
						</select>
						</li>
						<li><select id="edit-type">
						     <option value="2">总店服务员</option>
						     <option value="3">分店服务员</option>
						</select>
						</li>
			    </ul>
			</form>
			<a class="btn confirm-btn" id="confirm-edit" href="javascript:void(0)">添加</a>
			<div class="clear"></div>
			<div class="form-message" id="form-edit"></div>
		</div>
	</div>
	<div class="clear"></div>
	<div class="modal-wrapper" id="employee-add" style="display: none;">
		<div class="dialog cart-dialog">
			<div class="dialog-title">新增</div>
			<a class="close-btn" href="javascript:void(0)"><b></b></a>
			<form class="info-form">
			    <ul>
						<li><input type="text" id="add-name" placeholder="用户名"></li>
						<li><select id="add-s-id">
						<option value="0">无</option>
						<%for(Map.Entry<Integer,String> entry:sotreList.entrySet()){ %>
							<option value="<%=entry.getKey()%>"><%=entry.getValue()%></option>
						<%} %>
						</select>
						</li>
						<li><select id="add-type">
						     <option value="2">总店服务员</option>
						     <option value="3">分店服务员</option>
						</select>
						<li><input type="password" id="add-password" placeholder="请输入密码"></li>
						<li><input type="password" id="add-password-twice" placeholder="请再次输入密码"></li>
			    </ul>
			</form>
			<a class="btn confirm-btn" id="confirm-add" href="javascript:void(0)">添加</a>
			<div class="form-message" id="form-add"></div>
			<div class="clear"></div>
		</div>
	</div>
	<script src="../js/jquery-2.1.4.min.js"></script>
	<script src="../js/bootstrap.js"></script>
	<script src="../js/employeeManage.js"></script>
</body>
</html>