package com.web;

import java.io.IOException;  
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.web.user.User;
import com.web.user.UserDao;
 


public class LoginServlet extends HttpServlet {
 
 
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

	 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String name  = req.getParameter("name");
		String password  = req.getParameter("password"); 
		String type  = req.getParameter("type");
		HttpSession session = req.getSession();

		UserDao userDao = new UserDao(); 
		User user = null ;
		
		if(type.equals("3")){
//			教师
			if( userDao.userLogin("user_tb", name,  password , type ) ){
				user = userDao.getUserTbByName("user_tb", name, password ) ;
				session.setAttribute("sid", user.getId() ) ;
				session.setAttribute("screen_name", user.getScreen_name() ) ;
				session.setAttribute("type", "3") ;
				req.getRequestDispatcher("/index.jsp").forward(req, resp);
				return ;
			}
		}else if(type.equals("2")){
//			学生
				if( userDao.userLogin("user_tb", name,  password  , type ) ){
					user = userDao.getUserTbByName("user_tb", name, password ) ;
					req.getSession().setAttribute("sid", user.getId() ) ;
					req.getSession().setAttribute("screen_name", user.getScreen_name() ) ;
					req.getSession().setAttribute("type", "2") ;
					req.getRequestDispatcher("/index.jsp").forward(req, resp);
					return ;
				}
		
		}else{
//			管理员
			if( userDao.login("admin", name,  password ) ){
				user = userDao.getUserByName("admin", name, password ) ;
				req.getSession().setAttribute("admin", user.getId() ) ;
				req.getSession().setAttribute("screen_name", user.getScreen_name() ) ;
				req.getSession().setAttribute("sid", user.getId() ) ;
				req.getSession().setAttribute("type", user.type ) ;
				req.getRequestDispatcher("/index.jsp").forward(req, resp);
				return ;
			}
		}
		 
		req.setAttribute("s", "0");
		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}
	

 

}
