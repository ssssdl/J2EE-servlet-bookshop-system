<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>管理登录界面</title>
    
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
<br><br><br><br><br><br><br><br><br><br><br><br><br>  
  <div class="container-fluid center-block">
     <h1 class="text-center"><br>管 理 登 录<br></h1>
     <h2 class="page-header"></h2>
    <form action="AdminLoginClServlet"  method="post" >
     	<div class="row">
    		<div class="col-md-6 col-sm-6 text-right"><label for="exampleInputuserID">用户ID：</label></div>
    		<div class="col-md-1 col-sm-3"><input type="text" class="form-control" id="exampleInputuserID" name="id"/></div>
    	</div>
    	<div class="row">
    		<div class="col-md-6 col-sm-6 text-right"><label for="exampleInputuserpass">密　码：</label></div>
    		<div class="col-md-1 col-sm-3"><input type="password" class="form-control" id="exampleInputuserpass"  name="password"/></div>
   		</div>
   		<div class="row">
     		<div class="col-md-6 col-sm-6 text-right"><input type="submit" class="btn btn-default" value="登陆"/></div>
     		<div class="col-md-6 col-sm-6"><input type="reset" class="btn btn-default" value="清空"/></div>
    	</div>
    </form>
   </div>
  </body>
</html>
