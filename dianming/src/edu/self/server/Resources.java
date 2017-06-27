package edu.self.server;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Resources
 * 
 * @author mzba
 */
public class Resources extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Resources() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("gb2312");
		
		int order = 0 ;
		
		if( request.getParameter("order" ) != null && !"".equals( request.getParameter("order" ) )){
			order = Integer.valueOf( request.getParameter("order" ) ).intValue() ;
		}
	 
		response.getWriter().write(DBControl.getAllResources( request.getParameter("name") , request.getParameter("resource_type")  , order ).toString() );
	}

}
