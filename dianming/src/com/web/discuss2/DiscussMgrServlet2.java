package com.web.discuss2;

 
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray; 
import com.web.util.Tool; 



@SuppressWarnings("serial")
public class DiscussMgrServlet2 extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String method = req.getParameter("method");

		DiscussMgrDao2 dao = new DiscussMgrDao2();
 
		if (method.equals("list")) {
			String type = req.getParameter("type");		    
		    if (type != null && type.equals("json")) {
		    	
		    	JSONArray jsonArray  =  JSONArray.fromObject( dao.getAll( req.getParameter("id") , req.getParameter("f") )  );
		    	resp.setCharacterEncoding("gb2312");
		    	
		    	try {
		    		resp.getWriter().write(jsonArray.toString());
		    	} catch (IOException e) {
		    		e.printStackTrace();
		    	}
		    	return ;		    	
			}  else {
				getAll(req, dao);
				req.getRequestDispatcher("/discuss_list2.jsp").forward(req, resp);
			}
		    
		} else if (   method.equals("tongji") ){
			
			
			req.setAttribute("list", dao.tongji( ) ); 
			req.getRequestDispatcher("/tongji.jsp").forward(req, resp);
			
		} else if (   method.equals("goUpdate") ){

			String id = req.getParameter("id");

			req.setAttribute("map", dao.getObjectById(id) ); 
			req.getRequestDispatcher("/discuss_update2.jsp").forward(req, resp);	    
		    
		} else if (   method.equals("update") ){
 
			dao.update( req.getParameter("id") , req.getParameter("answer")  ) ;  

			getAllForUpdate(req, dao);
			req.getRequestDispatcher("/discuss_list2.jsp").forward(req, resp);
			
			
		} else if (method.equals("save")) { 
  
			   	Discuss2 object  = new Discuss2(); 
				String  content =  req.getParameter("content"); 
			 

				object.uid =  req.getParameter("uid"); 
				object.username =  req.getParameter("username");  
				 
			   	object.setContent( content ) ;
			   	object.setNews_id( Integer.valueOf( req.getParameter("news_id") ) ) ;
			   	object.setType( Integer.valueOf( req.getParameter("type") )  ) ;
			   	object.setTbl_date( Tool.getNowTime() ) ;
			   	 
			   	object.setAnswer( req.getParameter("answer") ) ;
			
			   	dao.save( object ) ;
			    
			   	resp.setCharacterEncoding("utf-8");
				
		    	try {
		    		resp.getWriter().write( "1");
		    	} catch (IOException e) {
		    		e.printStackTrace();
		    	}
			
		} else if (method.equals("del")) {
			String id = req.getParameter("id");
			dao.delete(id);
			getAll(req, dao);
			req.getRequestDispatcher("/discuss2.do?method=list").forward(req, resp);
		}

	}

	private void getAllForUpdate(HttpServletRequest req, DiscussMgrDao2 dao) {

		List list = dao.getAll( req.getParameter("news_id")  , req.getParameter("t")  );

		req.setAttribute("list", list); 
	}
	
	
	private void getAll(HttpServletRequest req, DiscussMgrDao2 dao) {

		List list = dao.getAll( req.getParameter("id")  , req.getParameter("t")  );

		req.setAttribute("list", list); 
	}
	
  
	
}
