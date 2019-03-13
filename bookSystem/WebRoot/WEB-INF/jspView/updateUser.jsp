<%@ page language="java" import="java.util.*,dlnu.sdl.domain.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改用户</title>
    
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
     <h1 class="text-center"><br>修 改 用 户<br></h1>
     <h2 class="page-header"></h2>
     
     <%
							ArrayList<?> al = (ArrayList<?>) request.getAttribute("userAl");
							for (int i = 0; i < al.size(); i++) {
								Users user = (Users) al.get(i);
						%>
    <form action="UserClServlet?flag=updateUser&id=<%=user.getId()%>"  method="post" >
     	<div class="row">
    		<div class="col-md-6 col-sm-6 text-right"><label for="exampleInputuserID">用户ID：</label></div>
    		<div class="col-md-1 col-sm-3"><input type="text" class="form-control" id="exampleInputuserID" name="id" disabled="disabled" value="<%=user.getId()%>"/></div>
    	</div>
    	<div class="row">
    		<div class="col-md-6 col-sm-6 text-right"><label for="exampleInputusername">用户名：</label></div>
    		<div class="col-md-1 col-sm-3"><input type="text" class="form-control" id="exampleInputusername"  name="name" value="<%=user.getName()%>"/></div>
   		</div>
    	<div class="row">
    		<div class="col-md-6 col-sm-6 text-right"><label for="exampleInputuserpass">密　码：</label></div>
    		<div class="col-md-1 col-sm-3"><input type="password" class="form-control" id="exampleInputuserpass"  name="password" value="<%=user.getPassword()%>"/></div>
   		</div>
   		<div class="row">
    		<div class="col-md-6 col-sm-6 text-right"><label for="exampleInputuseremail">邮    箱：</label></div>
    		<div class="col-md-1 col-sm-3"><input type="text" class="form-control" id="exampleInputuseremail"  name="email" value="<%=user.getEmail()%>"/></div>
   		</div>
     	<div class="row">
    		<div class="col-md-6 col-sm-6 text-right"><label for="exampleInputusertelephone">电	话：</label></div>
    		<div class="col-md-1 col-sm-3"><input type="text" class="form-control" id="exampleInputusertelephone"  name="telephone" value="<%=user.getTelephone()%>"/></div>
   		</div>
   		<div class="row">
    		<div class="col-md-6 col-sm-6 text-right"><label for="exampleInputusergrade">等	级：</label></div>
    		<div class="col-md-1 col-sm-3"><input type="text" class="form-control" id="exampleInputusergrade"  name="grade" value="<%=user.getGrade()%>"/></div>
   		</div>
   		<div class="row">
     		<div class="col-md-4 col-sm-4 text-right"><input type="submit" class="btn btn-default" value="确认修改"/></div>
     		<div class="col-md-4 col-sm-4"><a class="btn btn-default" href="UserClServlet?flag=paging">返回用户管理界面</a></div>
     		<div class="col-md-4 col-sm-4"><a class="btn btn-default" href="index.jsp">切换用户登录界面</a></div>
    	</div>
    </form>
    <%
							}
						%>
   </div>
  </body>
</html>


