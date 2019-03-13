<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>管理员界面</title>
    
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
    <center>
    <br><br><br><br><br><br>
   		<h1>选择操作</h1><br><br><br><br><br>
   		<h2>用户管理</h2>
   		<hr>
   		<a class="btn btn-default"  href="UserClServlet?flag=paging">管理用户</a><br/>
   		<a class="btn btn-default"  href="UserClServlet?flag=addUsershow">添加用户</a><br/>
   		<a class="btn btn-default"  href="UserClServlet?flag=cancleUser">注销用户</a><br/>
   		<br><br><br><br><br><br>
   		<h2>图书管理</h2>
   		<hr>
   		<a class="btn btn-default"  href="BookClServlet?flag=paging">图书管理</a><br/>
   		<a class="btn btn-default"  href="BookClServlet?flag=addBookshow">添加图书</a><br/>
   	</center>
  </body>
</html>
