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

<title>我的购物车</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css" href="css/comm.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">

</head>

<body>
	<div class="container-fluid">
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
		<h1 class="text-center">我的购物车</h1>
		<br> <br> <br> <br> <br>
		<div class="center-block">
			<div class="row">
				<div class="col-md-2 col-sm-2"></div>
				<div class="col-md-8 col-sm-8">
					<form action="ShoppingClServlet?type=update"
						method="post">
						<table class="table">
							<tr>
								<th>bookID</th>
								<th>书名</th>
								<th>价格</th>
								<th>出版社</th>
								<th>数量</th>
								<th>删除</th>
							</tr>
							<%
								ArrayList<?> al = (ArrayList<?>) request.getAttribute("bookList");
								for (int i = 0; i < al.size(); i++) {
									Book book = (Book) al.get(i);
							%>
							<tr>
								<td><%=book.getId()%><input type="hidden" name="id"
									value="<%=book.getId()%>" /></td>
								<td><%=book.getName()%></td>
								<td><%=book.getPrice()%></td>
								<td><%=book.getPublishHouse()%></td>
								<td><input type="text" name="booknum"
									value="<%=book.getShoppingNum()%>" /> 本</td>
								<td><a
									href="ShoppingClServlet?type=del&id=<%=book.getId()%>">删除</a>
								</td>
							</tr>

							<%
								}
							%>



							<tr>
								<td colspan="6"><input type="submit"
									class="btn btn-default" value="update" /></td>
							</tr>
							<tr>
								<td colspan="6">购物车的总价格：${totalPrice}</td>
							</tr>
						</table>
					</form>
				</div>
				<div class="col-md-2 col-sm-2"></div>
			</div>
			<div class="row">
				<div class="col-md-6 col-sm-6 text-right">
					<a class="btn btn-default" href="GoHallUI">返回购物大厅</a>
				</div>
				<div class="col-md-6 col-sm-6">
					<a class="btn btn-default" href="GoMyOrderServlet">提交订单</a>
				</div>
			</div>
		</div>
		</div>
</body>
</html>
