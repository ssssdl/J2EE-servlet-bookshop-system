package dlnu.sdl.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dlnu.sdl.domain.Book;
import dlnu.sdl.service.BookService;

@SuppressWarnings("serial")
public class BookClServlet extends HttpServlet {

	/**
	 * 图书管理的servlet
	 */
	public BookClServlet() {
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
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		if (request.getSession().getAttribute("admin") != null) {
			String flag = request.getParameter("flag");
			BookService bookService = new BookService();
			//显示所有书籍
			if (flag.equals("paging")) {
				// 后期可以做分页显示
				// String s_pageNow = request.getParameter("pageNow");
				ArrayList<?> bookal = bookService.getAllBook();
				request.setAttribute("bookAl", bookal);
				request.getRequestDispatcher("/WEB-INF/jspView/showAllBook.jsp")
				.forward(request, response);
			}
			// 删除书籍
			else if (flag.equals("delete")) {
				String id = request.getParameter("id");
				int[] bookid = { Integer.parseInt(id) };
				if (bookService.deleteBook(bookid)) {
					// 删除成功 从新获取页面
					ArrayList<?> bookal = bookService.getAllBook();
					request.setAttribute("bookAl", bookal);
					request.getRequestDispatcher("/WEB-INF/jspView/showAllBook.jsp")
					.forward(request, response);
				} else {
					// 删除失败
					response.setContentType("text/html");
					PrintWriter out = response.getWriter();
					out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
					out.println("<HTML>");
					out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
					out.println("  <BODY>");
					out.print("<script>alert(\"delete book Fail\");window.location.href='BookClServlet?flag=paging';</script>");
					out.println("  </BODY>");
					out.println("</HTML>");
					out.flush();
					out.close();
				}
			}
			// 显示添加书籍界面
			else if (flag.equals("addBookshow")) {
				request.getRequestDispatcher(
						"/WEB-INF/jspView/addBook.jsp").forward(request,
						response);
			}
			// 添加书籍
			else if (flag.equals("addBook")) {
				if (request.getParameter("id") != null) {
					Book addbook = new Book();
					addbook.setId(Integer.parseInt(request.getParameter("id").toString()));
					addbook.setName(request.getParameter("name").toString());
					addbook.setAuthor(request.getParameter("author").toString());
					addbook.setPublishHouse(request.getParameter("publishHouse").toString());
					addbook.setPrice(Integer.parseInt(request.getParameter("price").toString()));
					addbook.setNums(Integer.parseInt(request.getParameter("nums").toString()));
					// 然后把user传入数据库
					if (bookService.adduser(addbook)) {
						// 添加成功
						response.setContentType("text/html");
						PrintWriter out = response.getWriter();
						out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
						out.println("<HTML>");
						out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
						out.println("  <BODY>");
						out.print("<script>alert(\"add book success\");window.location.href='BookClServlet?flag=addBookshow';</script>");
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
						out.print("<script>alert(\"add book Fail\");window.location.href='BookClServlet?flag=addBookshow';</script>");
						out.println("  </BODY>");
						out.println("</HTML>");
						out.flush();
						out.close();
					}
				}
			}
			// 修改书籍界面显示
			else if (flag.equals("updateBookshow")) {
				//获取ID，查询具体数据，显示
				int id = 1;
				if(request.getParameter("id")!=null)
					id = Integer.parseInt(request.getParameter("id").toString());
				ArrayList<?> book = bookService.searchIDBook(id);//这个方法使用查询方法
				request.setAttribute("bookAl", book);
				request.getRequestDispatcher("/WEB-INF/jspView/updateBook.jsp")
						.forward(request, response);
			}
			
			//修改书籍操作
			else if (flag.equals("updateBook")) {
				//获取数据并转入数据库修改
				Book book = new Book();
				book.setId(Integer.parseInt(request.getParameter("id").toString()));
				book.setName(request.getParameter("name").toString());
				book.setAuthor(request.getParameter("author").toString());
				book.setPublishHouse(request.getParameter("publishHouse").toString());
				book.setPrice(Float.parseFloat(request.getParameter("price").toString()));
				book.setNums(Integer.parseInt(request.getParameter("nums").toString()));
				if (bookService.updateBook(book)) {
					// 修改成功 从新获取页面
					ArrayList<?> bookal = bookService.getAllBook();
					request.setAttribute("bookAl", bookal);
					request.getRequestDispatcher("/WEB-INF/jspView/showAllBook.jsp")
					.forward(request, response);
				} else {
					// 修改失败
					response.setContentType("text/html");
					PrintWriter out = response.getWriter();
					out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
					out.println("<HTML>");
					out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
					out.println("  <BODY>");
					out.print("<script>alert(\"update user Fail\");window.location.href='BookClServlet?flag=paging';</script>");
					out.println("  </BODY>");
					out.println("</HTML>");
					out.flush();
					out.close();
				}
			}
			//查询书籍 使用showallbook显示
			else if (flag.equals("searchBook")) {
				if (request.getParameter("name") != null) {
					// 执行查询返回到showalluser
					String name = request.getParameter("name");
					ArrayList<?> bookAl = bookService.searchNameBook(name);
					request.setAttribute("bookAl", bookAl);
					request.getRequestDispatcher("/WEB-INF/jspView/showAllBook.jsp").forward(request, response);
				} else {
					// 显示所有用户
					ArrayList<?> bookAl = bookService.getAllBook();
					request.setAttribute("bookAl", bookAl);
					request.getRequestDispatcher("/WEB-INF/jspView/showAllBook.jsp").forward(request, response);
				}

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
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
