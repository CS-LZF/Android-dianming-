package com.web;

import java.io.IOException;  
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.user.User;
import com.web.user.UserDao;
 


public class UpdatePasswordServlet extends HttpServlet {
 
 
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

	 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	 
		boolean is_admin = false;
		if( req.getSession().getAttribute("admin") != null ){
			is_admin = true ;
		}
		
		UserDao userDao = new UserDao(); 
		User user = null ;
		
		String oldPassword = req.getParameter("oldPassword").trim();
		String newPassword = req.getParameter("newPassword").trim();
		
		String id = (String) req.getSession().getAttribute("sid") ;
		
		if( ! is_admin ){
			
		}else{
//			管理员
			user = userDao.getById( "admin" , id ) ;
			if( oldPassword.equals( user.getPassword().trim() ) ){
				user.setPassword(newPassword);
				userDao.update( "admin" , user) ;
			}else{
				try {
					req.setAttribute("result", "0");
					req.getRequestDispatcher("/updatePassword.jsp").forward(req, resp);
					return ;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		  
		try {
			req.setAttribute("result", "1");
			req.getRequestDispatcher("/updatePassword.jsp").forward(req, resp);
		} catch (IOException e) {
			e.printStackTrace();
		}

		 
	}
	

 

}
