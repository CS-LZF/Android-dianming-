package com.web.order;

 
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;



@SuppressWarnings("serial")
public class OrderServlet extends HttpServlet {

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
 
		OrderDao orderDao = new OrderDao();
 
	 	if ( req.getParameter("method") != null ){
	 		
	 		if( req.getParameter("method").equals("del") ) {
	 	        String id = req.getParameter("id") ;
	 	        orderDao.delete(id);
	 	        
	 		}else if( req.getParameter("method").equals("goAlert") ) {

	 	        String id = req.getParameter("id") ;
	 	        
	 			if(  req.getParameter("type") != null &&  req.getParameter("type").equals("json")  ){
	 				 List list =   orderDao.getAllByAlert(id);
	 			     JSONArray jsonArray = null;
	 			     jsonArray = JSONArray.fromObject( list );
	 			     resp.setCharacterEncoding("gb2312");
	 			     resp.getWriter().write(jsonArray.toString());
	 			     return ;
	 			}else{
		 	        String state = req.getParameter("state") ;
		 	        orderDao.updateAlert(id, state) ;
	 			}
	 	        
	 		}else if( req.getParameter("method").equals("update") ) {
	 			
	 	        String id = req.getParameter("id") ;
	 	        String state = req.getParameter("state") ;
	 	        if( state.equals("1") ){
	 	        	state = "已结账" ;
	 	        }else if(  state.equals("0") ){
	 	        	state = "未结账" ;
	 	        }
	 	        orderDao.updateOrder(id, state) ;
	 		}else if( req.getParameter("method").equals("tongji") ) {

	 			String startDate = req.getParameter("startDate") ; 
	 			String endDate = req.getParameter("endDate") ;

	 			String type = req.getParameter("type") ;
	 			 
	 			if( type.equals("1") ){
	 				startDate += "-01" ;
	 				endDate += "-31" ;
	 			}
	 			startDate += " 00:00:00" ;
	 			endDate += " 23:59:59" ;
	 			
	 	 		req.setAttribute("startDate" ,  startDate ); 
	 	 		req.setAttribute("endDate",   endDate ); 
	 	 		req.setAttribute("sum_price",  orderDao.tongji( startDate , endDate ) ); 
	 			req.getRequestDispatcher("/tongji_result.jsp").forward(req, resp);
	 			return ;
	 		}
	 	}
	 	
 		req.setAttribute("list",  orderDao.getAll() ); 
		req.getRequestDispatcher("/orderList.jsp").forward(req, resp);
	}
 
  
	
}
