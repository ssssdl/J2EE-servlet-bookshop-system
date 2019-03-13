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

<title>购物大厅</title>

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
	<div class="container-fluid ">
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
		<br>

		<h1 class="text-center">欢迎光临购物大厅</h1>
		<br>
		<br>
		<br>
		<br>
		<br>
		<div class="center-block">
			<div class="row">
				<div class="col-md-2 col-sm-2"></div>
				<div class="col-md-8 col-sm-8">
					<table class="table">
						<tr>
							<th>书名</th>
							<th>价格</th>
							<th>出版社</th>
							<th>点击购买</th>
						</tr>
						<%
							ArrayList<?> al = (ArrayList<?>) request.getAttribute("bookAl");
							for (int i = 0; i < al.size(); i++) {
								Book book = (Book) al.get(i);
						%>
						<tr>
							<td><%=book.getName()%></td>
							<td><%=book.getPrice()%></td>
							<td><%=book.getPublishHouse()%></td>
							<td><a
								href="ShoppingClServlet?type=add&id=<%=book.getId()%>">购买</a></td>
						</tr>

						<%
							}
						%>
					</table>
				</div>
				<div class="col-md-2 col-sm-2"></div>
			</div>
			<div class="row">
				<div class="col-md-6 col-sm-6 text-right">
					<a class="btn btn-default" href="ShoppingClServlet?type=show">查看购物车</a>
				</div>
				<div class="col-md-6 col-sm-6">
					<a class="btn btn-default" href="index.jsp">返回登录</a>
				</div>
			</div>
		</div>
		</div>
</body>
</html>
