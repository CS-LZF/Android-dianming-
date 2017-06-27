package com.web.order;

 
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.info.Info;

import net.sf.json.JSONArray;



@SuppressWarnings("serial")
public class OrderEditServlet extends HttpServlet {

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
 
		OrderDao orderDao = new OrderDao();
 
	 	if ( req.getParameter("method") != null &&  req.getParameter("method").equals("save")  ){
	 			
	 			Order order = new Order(); 

	 			order.info_id =  req.getParameter("order.info_id")  ;
	 			order.setSeat( req.getParameter("order.seat")  ); 
	 			order.setDesc( req.getParameter("order.info_name") ) ;
	 			order.beizhu =  req.getParameter("order.beizhu") ;
	 			order.price =  req.getParameter("order.price") ;
	 			
	 			orderDao.save(order);
	 			
	 			String info_ids = req.getParameter("info_ids") ;
	 			String[] info_id = info_ids.split("_") ;
	 			
	 			for(int i=0; i<info_id.length ; i++){
	 				if( info_id[i] !=null && !"".equals(info_id[i]) ){
		 				Info food = orderDao.getFoodById( info_id[i] );
		 				long info_count = Long.valueOf(food.info_count);
		 				info_count = info_count+1 ;
		 				orderDao.updateFoodCount(  info_id[i]   , info_count ) ;
	 				}
	 			}
	 			 
	 			resp.setCharacterEncoding("utf-8");
				
		    	try {
		    		resp.getWriter().write( "1");
		    	} catch (IOException e) {
		    		e.printStackTrace();
		    	}
	 	}

	}
 
  
	
}
