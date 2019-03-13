<%@ page language="java" import="java.util.*,dlnu.sdl.domain.*"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>我的订单</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
</head>

<body>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<h1 class="text-center">我的订单</h1>
	<div class="row">
		<div class="col-md-2 col-sm-2"></div>
		<div class="col-md-8 col-sm-8">
			<table class="table">
				<tr>
					<td colspan="2">用户个人信息</td>
				</tr>
				<tr>
					<td>用户名</td>
					<td><%=((Users) session.getAttribute("loginUser")).getName()%></td>
				</tr>
				<tr>
					<td>电子邮件</td>
					<td><%=((Users) session.getAttribute("loginUser")).getEmail()%></td>
				</tr>
				<tr>
					<td>用户级别</td>
					<td><%=((Users) session.getAttribute("loginUser")).getGrade()%></td>
				</tr>
			</table>
		</div>
		<div class="col-md-2 col-sm-2"></div>
	</div>
	<hr>
	<div class="row">
		<div class="col-md-2 col-sm-2"></div>
		<div class="col-md-8 col-sm-8">
			<table class="table">
				<tr>
					<th>bookId</th>
					<th>书名</th>
					<th>价格</th>
					<th>出版社</th>
					<th>数量</th>
				</tr>
				<%
					ArrayList<?> al = (ArrayList<?>) request.getAttribute("orderInfo");
					for (int i = 0; i < al.size(); i++) {
						Book book = (Book) al.get(i);
				%>
				<tr>
					<td><%=book.getId()%></td>
					<td><%=book.getName()%></td>
					<td><%=book.getPrice()%></td>
					<td><%=book.getPublishHouse()%></td>
					<td><%=book.getShoppingNum()%></td>
				</tr>
				<%
					}
				%>
				<tr>
					<td colspan="5">总价格：<%=request.getAttribute("totalPrice") %></td>
				</tr>
			</table>
		</div>
		<div class="col-md-2 col-sm-2"></div>
	</div>
</body>
</html>
