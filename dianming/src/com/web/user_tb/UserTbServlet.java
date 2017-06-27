package com.web.user_tb;



import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 

import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.json.JSONException;
import org.json.JSONObject;

import com.web.util.Constants;




@SuppressWarnings("serial")
public class UserTbServlet extends HttpServlet {
 
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String method = req.getParameter("method");

		UserTbDao userDao = new UserTbDao();

		if (method.equals("login")) {

			String userId = req.getParameter("userId");
			String password = req.getParameter("password");
			boolean result = userDao.login( userId , password ) ;
			resp.getWriter().print(result);
		
		}else if (method.equals("RegisterAccount")) {

			String parameter = req.getParameter("register");
			String result = "";
			try {
				JSONObject userObject = new JSONObject(parameter);
				if (!userDao.isExistsUser(userObject.getString("userId"))) {
					boolean userResult = userDao.insertUser(userObject);
					if (userResult) { 
						result = "SUCCESS";
					} else {
						result = "ERROR";
					}
				} else {
					result = "EXISTSUSER";
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			resp.getWriter().println(result);
				
		}else if (method.equals("list")) {

			getAll(req, userDao);
			req.getRequestDispatcher("/user_tb_list.jsp").forward(req, resp);
	 
		} else if (method.equals("del")) {
			String id = req.getParameter("id");
			userDao.delete(id);
			getAll(req, userDao);
			req.getRequestDispatcher("/user_tb_list.jsp").forward(req, resp);
		}

	}

	private void getAll(HttpServletRequest req, UserTbDao userDao) { 
		
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
