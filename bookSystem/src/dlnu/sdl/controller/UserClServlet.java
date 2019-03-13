package dlnu.sdl.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dlnu.sdl.domain.Users;
import dlnu.sdl.service.UsersService;

@SuppressWarnings("serial")
public class UserClServlet extends HttpServlet {

	/**
	 * 用于用户管理的servlet
	 */
	public UserClServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		if (request.getSession().getAttribute("admin") != null) {
			// 获得标识符
			String flag = request.getParameter("flag");
			// 会一直调用UsersService的方法
			UsersService usersService = new UsersService();
			// 显示所有用户
			if (flag.equals("paging")) {
				// 得到用户希望显示的pageNow
				// String s_pageNow = request.getParameter("pageNow");

				ArrayList<?> userAl = usersService.getAllUsers();
				request.setAttribute("userAl", userAl);
				request.getRequestDispatcher("/WEB-INF/jspView/showAllUser.jsp")
						.forward(request, response);

			}
			// 删除用户
			else if (flag.equals("delete")) {
				String id = request.getParameter("id");
				int[] users = { Integer.parseInt(id) };
				if (usersService.deleteUser(users)) {
					// 删除成功 从新获取页面
					ArrayList<?> userAl = usersService.getAllUsers();
					request.setAttribute("userAl", userAl);
					request.getRequestDispatcher(
							"/WEB-INF/jspView/showAllUser.jsp").forward(
							request, response);
				} else {
					// 删除失败
					response.setContentType("text/html");
					PrintWriter out = response.getWriter();
					out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
					out.println("<HTML>");
					out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
					out.println("  <BODY>");
					out.print("<script>alert(\"delete user Fail\");window.location.href='UserClServlet?flag=paging';</script>");
					out.println("  </BODY>");
					out.println("</HTML>");
					out.flush();
					out.close();
				}
			}
			// 显示添加用户界面
			else if (flag.equals("addUsershow")) {
				request.getRequestDispatcher(
						"/WEB-INF/jspView/adminadduser.jsp").forward(request,
						response);
			}
			// 添加用户
			else if (flag.equals("addUser")) {
				if (request.getParameter("id") != null
						&& request.getParameter("password") != null) {
					// 如果变量全部传入
					Users adduser = new Users(Integer.parseInt(request
							.getParameter("id").toString()),
							request.getParameter("password"));
					adduser.setEmail(request.getParameter("email").toString());
					adduser.setGrade(Integer.parseInt(request.getParameter(
							"grade").toString()));
					adduser.setName(request.getParameter("name").toString());
					adduser.setTelephone(request.getParameter("telephone")
							.toString());
					// 然后把user传入数据库
					if (usersService.adduser(adduser)) {
						// 添加成功
						response.setContentType("text/html");
						PrintWriter out = response.getWriter();
						out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
						out.println("<HTML>");
						out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
						out.println("  <BODY>");
						out.print("<script>alert(\"add user success\");window.location.href='UserClServlet?flag=addUsershow';</script>");
						out.println("  </BODY>");
						out.println("</HTML>");
						out.flush();
						out.close();
					} else {
						// 添加失败
						response.setContentType("text/html");
						PrintWriter out = response.getWriter();
						out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
						out.println("<HTML>");
						out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
						out.println("  <BODY>");
						out.print("<script>alert(\"add user Fail\");window.location.href='UserClServlet?flag=addUsershow';</script>");
						out.println("  </BODY>");
						out.println("</HTML>");
						out.flush();
						out.close();
					}
				}
			}
			// 修改用户界面显示
			else if (flag.equals("updateUsershow")) {
				//获取ID，查询具体数据，显示
				int id = 1;
				if(request.getParameter("id")!=null)
					id = Integer.parseInt(request.getParameter("id").toString());
				ArrayList<?> userAl = usersService.searchIDUser(id);//这个方法使用查询方法
				request.setAttribute("userAl", userAl);
				request.getRequestDispatcher("/WEB-INF/jspView/updateUser.jsp")
						.forward(request, response);
			}
			
			//修改用户操作
			else if (flag.equals("updateUser")) {
				//获取数据并转入数据库修改
				Users user = new Users(Integer.parseInt(request.getParameter("id").toString()),request.getParameter("password").toString());
				user.setName(request.getParameter("name").toString());
				user.setEmail(request.getParameter("email").toString());
				user.setTelephone(request.getParameter("telephone").toString());
				user.setGrade(Integer.parseInt(request.getParameter("grade").toString()));
				if (usersService.updateUser(user)) {
					// 修改成功 从新获取页面
					ArrayList<?> userAl = usersService.getAllUsers();
					request.setAttribute("userAl", userAl);
					request.getRequestDispatcher(
							"/WEB-INF/jspView/showAllUser.jsp").forward(
							request, response);
				} else {
					// 修改失败
					response.setContentType("text/html");
					PrintWriter out = response.getWriter();
					out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
					out.println("<HTML>");
					out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
					out.println("  <BODY>");
					out.print("<script>alert(\"update user Fail\");window.location.href='UserClServlet?flag=paging';</script>");
					out.println("  </BODY>");
					out.println("</HTML>");
					out.flush();
					out.close();
				}
			}
			// 查询用户 使用用户名查找 使用showalluser显示
			else if (flag.equals("searchUser")) {
				if (request.getParameter("name") != null) {
					// 执行查询返回到showalluser
					String name = request.getParameter("name");
					/*
					 * 这里调用查询方法接下来写查询方法
					 * */
					//searchUser(Users user)
					ArrayList<?> userAl = usersService.searchUser(name);
					request.setAttribute("userAl", userAl);
					request.getRequestDispatcher("/WEB-INF/jspView/showAllUser.jsp").forward(request, response);
				} else {
					// 显示所有用户
					ArrayList<?> userAl = usersService.getAllUsers();
					request.setAttribute("userAl", userAl);
					request.getRequestDispatcher("/WEB-INF/jspView/showAllUser.jsp").forward(request, response);
				}

			}

			// 注销用户
			else if (flag.equals("cancleUser")) {
				/*
				 * 清除session 跳转到正常用户登录的界面
				 * */
				request.getSession().removeAttribute("admin");
				request.getRequestDispatcher("/WEB-INF/jspView/Login.jsp").forward(request, response);
			}
		} else {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<script>alert(\"此链接不可以直接访问\");</script>");
			out.flush();
			out.close();
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
