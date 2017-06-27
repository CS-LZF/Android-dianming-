package com.web.user;



import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

import com.web.util.Constants;
 


@SuppressWarnings("serial")
public class UserServlet extends HttpServlet {
 
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String method = req.getParameter("method");

		UserDao userDao = new UserDao();

		if (method.equals("list")) {

			getAll(req, userDao);
			req.getRequestDispatcher("/user_list.jsp").forward(req, resp);
			
		} else if (method.equals("goEdit")) {

			String id = req.getParameter("id"); 
			req.setAttribute("map", userDao.getById("admin" , id)   ) ;
			req.getRequestDispatcher("/user_add.jsp").forward(req, resp);
			
		} else if (method.equals("save")) { 

			String id = req.getParameter("id");
			String name = req.getParameter("name");
			String password = req.getParameter("password");

//			String password  = MD5.getMD5(  req.getParameter("password").trim().getBytes() ) ; 
			String screen_name = req.getParameter("screen_name") ;
			String type = req.getParameter("type") ;

			User user = new User();

			user.setName(name);
			user.setPassword(password);
			user.setScreen_name(screen_name);
			user.setType( type );

			if( id != null && !id.equals("") ){
//				编辑
				user.setId(id);
				userDao.update("admin" , user) ; 
				getAll(req, userDao);
				req.getRequestDispatcher("/user_list.jsp").forward(req, resp);
			}else{
//				新增
				userDao.save(user);	
				req.setAttribute("s", "0") ; 
				req.getRequestDispatcher("/user_add.jsp").forward(req, resp);
			}

		} else if (method.equals("del")) {
			String id = req.getParameter("id");
			userDao.delete(id);
			getAll(req, userDao);
			req.getRequestDispatcher("/user_list.jsp").forward(req, resp);
		}

	}

	private void getAll(HttpServletRequest req, UserDao userDao) { 
		
		int pageNo = 0 ; 
		int start =  0 ;

		String pageName = new ParamEncoder("element").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		
		if( req.getParameter(pageName) != null  && !req.getParameter(pageName).equals("")){
			pageNo = Integer.parseInt( req.getParameter(pageName) );
		}else{
			pageNo = 1 ;
		}

		start = (pageNo-1) * Constants.PAGE_SIZE  ;
 
		List list = userDao.getAll( start , Constants.PAGE_SIZE ); 
 
		req.setAttribute("total",  userDao.getTotalCount() );
		req.setAttribute("list", list);  
	}
  
	
}
