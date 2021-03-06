package com.action;

 
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;  
import com.dao.QingjiaDao;
import com.model.Qingjia;
import org.json.JSONObject;
import org.json.JSONException;


@SuppressWarnings("serial")
public class QingjiaAction extends HttpServlet {

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String method = req.getParameter("method");

		QingjiaDao dao = new QingjiaDao();
 
  
		if (method.equals("list")) {

			String type = req.getParameter("type");
		    
		    if (type != null && type.equals("json")) {
		    	
		    	JSONArray jsonArray  =  JSONArray.fromObject( dao.getAll(req.getParameter("id") , req.getParameter("f") )  );
		    	resp.setCharacterEncoding("gb2312");
		    	
		    	try {
		    		resp.getWriter().write(jsonArray.toString());
		    	} catch (IOException e) {
		    		e.printStackTrace();
		    	}
		    	return ;		    	
			}  else {
				getAll(req, dao);
				req.getRequestDispatcher("/qingjialist.jsp").forward(req, resp);
			}
		    
		} else if (   method.equals("goUpdate") ){

			String id = req.getParameter("id");

			req.setAttribute("map", dao.getObjectById(id) ); 
			req.getRequestDispatcher("/qingjia_update.jsp").forward(req, resp);	    
		    
		} else if (   method.equals("update") ){
 
			String id = req.getParameter("id");
			Qingjia object  = dao.getObjectById(id) ;
			object.biaoti = req.getParameter("biaoti") ; 
			object.miaoshu = req.getParameter("miaoshu") ; 
			object.shijian = req.getParameter("shijian") ; 
			object.zhuangtai = req.getParameter("zhuangtai") ; 
			object.xusheng = req.getParameter("xusheng") ; 
			object.kecheng = req.getParameter("kecheng") ; 
			dao.update( req.getParameter("id") , object ) ;  

			getAllForUpdate(req, dao);
			req.getRequestDispatcher("/qingjialist.jsp").forward(req, resp);
	
		} else if (method.equals("saveJson")) {
			
			String result = "" ;
			String parameter = req.getParameter("qingjia");
			try {
				JSONObject jsonObject = new JSONObject(parameter);  

			   	Qingjia object  = new Qingjia();  
				object.biaoti = jsonObject.getString("biaoti") ; 
				object.miaoshu = jsonObject.getString("miaoshu") ; 
				object.shijian = jsonObject.getString("shijian") ; 
				object.zhuangtai = jsonObject.getString("zhuangtai") ; 
				object.xusheng = jsonObject.getString("xusheng") ; 
				object.kecheng = jsonObject.getString("kecheng") ; 
			     
			   	int i = dao.save( object ) ;
				if ( i > 0) { 
					result = "SUCCESS";
				} else {
					result = "ERROR";
				} 
			} catch (JSONException e) {
				e.printStackTrace();
			}
			resp.getWriter().println(result);
			return ;
			
		} else if (method.equals("save")) { 
  
			   	Qingjia object  = new Qingjia(); 
			 
				object.biaoti = req.getParameter("biaoti") ; 
				object.miaoshu = req.getParameter("miaoshu") ; 
				object.shijian = req.getParameter("shijian") ; 
				object.zhuangtai = req.getParameter("zhuangtai") ; 
				object.xusheng = req.getParameter("xusheng") ; 
				object.kecheng = req.getParameter("kecheng") ; 
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
			req.getRequestDispatcher("/news.do?method=list").forward(req, resp);
		}

	}

	private void getAllForUpdate(HttpServletRequest req, QingjiaDao dao) {

		List list = dao.getAll( req.getParameter("id")  , req.getParameter("t")  );
		req.setAttribute("list", list); 
	}
	
	
	private void getAll(HttpServletRequest req, QingjiaDao dao) {

		List list = dao.getAll( req.getParameter("id")  , req.getParameter("t")  );

		req.setAttribute("list", list); 
	}
  
	
}
