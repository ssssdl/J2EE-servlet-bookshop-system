<%@ page language="java" import="java.util.*,dlnu.sdl.domain.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户管理界面</title>
    
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

		<h1 class="text-center">用 户 管 理</h1>
		<br>
		<br>
		<form action="UserClServlet?flag=searchUser" method="POST">
			<div class="row">
				<div class="col-md-6 col-sm-6 text-right">
					<label for="exampleInputusername">输入用户名搜索：</label>
				</div>
				<div class="col-md-1 col-sm-3">
					<input type="text" class="form-control" id="exampleInputuserID"
						name="name" />
				</div>
				<div class="col-md-1 col-sm-3">
					<input type="submit" class="btn btn-default" value="搜索" />
				</div>

			</div>
		</form>
		<br>
		<br>
		<div class="center-block">
			<div class="row">
				<div class="col-md-2 col-sm-2"></div>
				<div class="col-md-8 col-sm-8">
					<table class="table">
						<tr>
							<th>ID</th>
							<th>姓名</th>
							<th>密码</th>
							<th>邮箱</th>
							<th>电话</th>
							<th>会员等级</th>
							<th>点击修改</th>
							<th>点击删除</th>
						</tr>
						<%
							ArrayList<?> al = (ArrayList<?>) request.getAttribute("userAl");
							for (int i = 0; i < al.size(); i++) {
								Users user = (Users) al.get(i);
						%>
						<tr>
							<td><%=user.getId()%></td>
							<td><%=user.getName()%></td>
							<td><%=user.getPassword()%></td>
							<td><%=user.getEmail()%></td>
							<td><%=user.getTelephone()%></td>
							<td><%=user.getGrade()%></td>
							<td><a
								href="UserClServlet?flag=updateUsershow&id=<%=user.getId()%>">修改</a></td>
							<td><a
								href="UserClServlet?flag=delete&id=<%=user.getId()%>">删除</a></td>
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
					<a class="btn btn-default" href="AdminLoginClServlet">返回主界面</a>
				</div>
				<div class="col-md-6 col-sm-6">
					<a class="btn btn-default" href="index.jsp">切换用户登录界面</a>
				</div>
			</div>
		</div>
		</div>
</body>
</html>

