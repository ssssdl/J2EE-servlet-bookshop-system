package dlnu.sdl.controller;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dlnu.sdl.service.UsersService;
import dlnu.sdl.service.BookService;
import dlnu.sdl.service.MyCart;
import dlnu.sdl.domain.Users;

public class GoHallUI extends HttpServlet {

	/**
	 * 验证登录，跳转商店主页面
	 */
	private static final long serialVersionUID = 4615455981467954773L;

	/**
	 * Constructor of the object.
	 */
	public GoHallUI() {
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
		this.doPost(request, response);
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

		// 调整编码
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		//如果登录过在访问这个界面
		if ((request.getSession().getAttribute("loginUser")) != null) {
			// 如果登陆的话应该跳转到hall.jsp
			BookService bookService = new BookService();
			ArrayList<?> bookAl = bookService.getAllBook();
			request.setAttribute("bookAl", bookAl);
			request.getRequestDispatcher("/WEB-INF/jspView/hall.jsp").forward(
					request, response);
			return;
		}

		if (request.getParameter("id") != "" && request.getParameter("password") != "") {
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			Users loginUser = new Users(Integer.parseInt(id), password);
			UsersService usersService = new UsersService();
			if (usersService.checkUser(loginUser)) {

				request.getSession().setAttribute("loginUser", loginUser);

				MyCart myCart = new MyCart();
				request.getSession().setAttribute("myCart", myCart);

				BookService bookService = new BookService();
				ArrayList<?> bookAl = bookService.getAllBook();
				request.setAttribute("bookAl", bookAl);
				request.getRequestDispatcher("/WEB-INF/jspView/hall.jsp")
						.forward(request, response);
			} else {
				request.getRequestDispatcher("/WEB-INF/jspView/Login.jsp")
						.forward(request, response);

			}
		}else{
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<script>alert(\"请输入用户名和密码\");</script>");
			//request.getRequestDispatcher("/WEB-INF/jspView/Login.jsp").forward(request, response);
			out.flush();
			out.close();
		}
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
