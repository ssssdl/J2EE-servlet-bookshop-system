package dlnu.sdl.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dlnu.sdl.service.MyCart;

@SuppressWarnings("serial")
public class ShoppingClServlet extends HttpServlet {

	/**
	 * 这里包含了对购物车中图书的一些操作
	 */
	public ShoppingClServlet() {
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
		/*
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("</HTML>");
		out.flush();
		out.close();
		*/
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		String type = request.getParameter("type");
		if("del".equals(type)){
			String id = request.getParameter("id");
			MyCart myCart = (MyCart) request.getSession().getAttribute("myCart");
			myCart.delBook(id);
			request.setAttribute("bookList", myCart.showMyCart());
	 		request.setAttribute("totalPrice", myCart.getTotalPrice());
			request.getRequestDispatcher("/WEB-INF/jspView/ShowMyCart.jsp").forward(request, response);

			
		}else if("add".equals(type)){
			String id = request.getParameter("id");
			MyCart myCart = (MyCart)request.getSession().getAttribute("myCart");
	 		myCart.addBook2(id);
	 		request.setAttribute("totalPrice", myCart.getTotalPrice());
			request.setAttribute("bookList", myCart.showMyCart());
			request.getRequestDispatcher("/WEB-INF/jspView/ShowMyCart.jsp").forward(request, response);
			
		}else if("update".equals(type)){
			
			String bookIds[] = request.getParameterValues("id");
			String bookNums[] = request.getParameterValues("booknum");
			MyCart myCart = (MyCart) request.getSession().getAttribute("myCart");
			for(int i = 0;i<bookIds.length;i++){
				myCart.updateBook(bookIds[i], bookNums[i]);
			}
			request.setAttribute("totalPrice", myCart.getTotalPrice());
			request.setAttribute("bookList", myCart.showMyCart());
			request.getRequestDispatcher("/WEB-INF/jspView/ShowMyCart.jsp").forward(request, response);
		}else if("show".equals(type)){ //查看购物车功能
			MyCart myCart = (MyCart)request.getSession().getAttribute("myCart");
			request.setAttribute("bookList", myCart.showMyCart());
	 		request.setAttribute("totalPrice", myCart.getTotalPrice());
			request.getRequestDispatcher("/WEB-INF/jspView/ShowMyCart.jsp").forward(request, response);
			
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
