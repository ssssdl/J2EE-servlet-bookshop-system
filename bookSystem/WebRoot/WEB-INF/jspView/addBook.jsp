<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加图书</title>
    
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
     <h1 class="text-center"><br>添 加 书 籍<br></h1>
     <h2 class="page-header"></h2>
    <form action="BookClServlet?flag=addBook"  method="post" >
     	<div class="row">
    		<div class="col-md-6 col-sm-6 text-right"><label for="exampleInputuserID">书籍ID：</label></div>
    		<div class="col-md-1 col-sm-3"><input type="text" class="form-control" id="exampleInputuserID" name="id" /></div>
    	</div>
    	<div class="row">
    		<div class="col-md-6 col-sm-6 text-right"><label for="exampleInputusername">书	  名：</label></div>
    		<div class="col-md-1 col-sm-3"><input type="text" class="form-control" id="exampleInputusername"  name="name" /></div>
   		</div>
    	<div class="row">
    		<div class="col-md-6 col-sm-6 text-right"><label for="exampleInputuserpass">作	者：</label></div>
    		<div class="col-md-1 col-sm-3"><input type="text" class="form-control" id="exampleInputuserpass"  name="author" /></div>
   		</div>
   		<div class="row">
    		<div class="col-md-6 col-sm-6 text-right"><label for="exampleInputuseremail">出版社：</label></div>
    		<div class="col-md-1 col-sm-3"><input type="text" class="form-control" id="exampleInputuseremail"  name="publishHouse"/></div>
   		</div>
     	<div class="row">
    		<div class="col-md-6 col-sm-6 text-right"><label for="exampleInputusertelephone">价	格：</label></div>
    		<div class="col-md-1 col-sm-3"><input type="text" class="form-control" id="exampleInputusertelephone"  name="price" /></div>
   		</div>
   		<div class="row">
    		<div class="col-md-6 col-sm-6 text-right"><label for="exampleInputusergrade">数   量：</label></div>
    		<div class="col-md-1 col-sm-3"><input type="text" class="form-control" id="exampleInputusergrade"  name="nums" /></div>
   		</div>
   		<div class="row">
     		<div class="col-md-4 col-sm-4 text-right"><input type="submit" class="btn btn-default" value="确认添加"/></div>
     		<div class="col-md-4 col-sm-4"><a class="btn btn-default" href="BookClServlet?flag=paging">返回图书管理界面</a></div>
     		<div class="col-md-4 col-sm-4"><a class="btn btn-default" href="AdminLoginClServlet">返回主界面</a></div>
    	</div>
    </form>
   </div>
  </body>
</html>