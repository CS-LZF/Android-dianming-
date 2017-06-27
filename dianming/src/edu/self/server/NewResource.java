package edu.self.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Servlet implementation class NewResource
 * 
 * @author mzba
 */
public class NewResource extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewResource() {
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
	protected void doPost(HttpServletRequest req,
			HttpServletResponse resp ) throws ServletException, IOException {
		 

        String fileName = null;

 	   	JSONObject resObject = null;
 	   
		if(ServletFileUpload.isMultipartContent(req)) {
 
			   DiskFileItemFactory factory = new DiskFileItemFactory();   
			   String dir_path =  req.getRealPath("/") ;

			   factory.setRepository(new File( dir_path + "/tmp"));  
			   //内存最大占用   
	           factory.setSizeThreshold(1024000);   
	           ServletFileUpload sfu = new ServletFileUpload(factory);   
	           sfu.setHeaderEncoding("utf-8");
	           
	           //单个文件最大值byte   
	           sfu.setFileSizeMax(102400000);   
	           //所有上传文件的总和最大值byte   
	           sfu.setSizeMax(204800000);   
	           List items = null;   
	           try {   
	               items = sfu.parseRequest(req);   
	           } catch (SizeLimitExceededException e) {   
	               System.out.println("size limit exception!");   
	           } catch(Exception e) {   
	               e.printStackTrace();   
	           }   
	           
	           Iterator iter = items==null?null:items.iterator();   
	           while(iter != null && iter.hasNext()) {   
	               FileItem item = (FileItem)iter.next();   
	               //简单的表单域   
	               if(item.isFormField()) {   
	                   String   str =   iso2gb ( item.getString() ) ;
	                   System.out.print("form field:");   
	                   System.out.print(item.getFieldName() + "  ");   
	                   System.out.print( str );   
	                   
	                   if( item.getFieldName().equals( "resource")  ){
		   	        		try {
		   	        			resObject = new JSONObject( item.getString() );
		   	        		} catch (JSONException e) {
		   	        			e.printStackTrace();
		   	        		}
	                   }
	           		  
	               }    
	               //文件域   
	               else if(!item.isFormField()) {    
                     System.out.println("上传文件的名称:" + item.getName());
	                   	if(item.getName().lastIndexOf("/") != -1){
	                   		fileName = item.getName().substring(item.getName().lastIndexOf("/")+1,item.getName().length());
	                   	}else if(item.getName().lastIndexOf("\\") != -1){
	                   		fileName = item.getName().substring(item.getName().lastIndexOf("\\")+1,item.getName().length());
	                   	}else{
	                   		fileName = item.getName();
	                   	}
	                   BufferedInputStream in = new BufferedInputStream(item.getInputStream());   
	                   //文件存储在工程的upload目录下,这个目录也得存在   
	                   BufferedOutputStream out = new BufferedOutputStream(   
	                            new FileOutputStream(new File( dir_path + "/upload/" + fileName)));   
	                   Streams.copy(in, out, true);   
	               }   
	           }    
	           resp.getWriter().println( DBControl.insertResource(resObject , fileName ) == true ? "true" : "false"); 
		}
		 
	}

	
	/**
	 * 
	 *  表单提交乱码解决
	 * @param str
	 * @return
	 */	
	private String iso2gb(String str){
	 
		try {
			return  new  String( str.getBytes("iso-8859-1"),"utf-8" );
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
  
	
	
	
}
